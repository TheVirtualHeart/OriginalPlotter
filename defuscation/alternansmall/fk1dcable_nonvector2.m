% Fenton-Karma 3-variable model in 1d ring

% parameter values
tvp=3.33;
tv1m=15.6; 
tv2m=1250.;
twp=870.0;
twm=41.0;
td=0.25;
to=12.5;
tr=33.33;
tsi=29.0;
xk=10.0;
ucsi=0.85;
uc=0.13;
uv=0.04;

% numerical parameters
dt = 0.1;               % time step
dx = 0.025;             % spatial resolution
diff = 0.001;           % diffusion coefficient
endtime=2000;           % physical duration of simulation in ms
nsteps=ceil(endtime/dt);
outputevery=1;          % save output every __ ms
iout = round(outputevery/dt);
nout=ceil(endtime/outputevery);
kout=0; 
nx = 400;               % cable length in computational cells; physical length is nx * dx
ddt_o_dx2 = diff*dt/(dx^2); % this gets used a lot in the diffusion calculation; pre-calculate

% stimulation period and strength
period=500.0;
stimmag=0.4;

% initial values 
% preallocate space
u=zeros(nx,1);
v=zeros(nx,1);
w=zeros(nx,1);

% set values
for j=1:nx
    u(j,1)=0;
    v(j,1)=1;
    w(j,1)=1;
end

% output
usave=zeros(nout,nx);       % preallocate space: u defined in both space and time
%t = dt:dt:endtime;          
tout = outputevery:outputevery:endtime; % set up an array of equally-spaced times, for plotting
xx=dx:dx:nx*dx;                         % set up an array of equally-spaced spatial locations, for plotting

for ntime=1:nsteps           % outer loop for time
    
    for j=1:nx               % inner loop for space

        % Heaviside functions; using a logical statement rather than
        % if-then
        p=(u(j,1)>=uc);
        q=(u(j,1)>=uv);
        
        % v and w derivatives
        dv=(1.-p)*(1.-v(j,1))/((1.-q)*tv1m+tv2m*q) - p*v(j,1)/tvp;
        dw=(1.-p)*(1.-w(j,1))/twm - p*w(j,1)/twp;
        
        % update v and w using forward Euler
        v(j,1)=v(j,1)+dt*dv;
        w(j,1)=w(j,1)+dt*dw;
        
        % calculate currents
        jfi=-v(j,1)*p*(u(j,1)-uc)*(1.-u(j,1))/td;
        jso=u(j,1)*(1-p)/to+p/tr;
        jsi=-w(j,1)*(1.+tanh(xk*(u(j,1)-ucsi)))/(2.*tsi);
        
        % calculate coupling and apply boundary conditions
        if(j==1)
            coupling = ddt_o_dx2 * ( - 2*u(j,1) + 2*u(j+1,1) );
        elseif(j==nx)
            coupling= ddt_o_dx2 * ( 2*u(j-1,1) - 2*u(j,1) );
        else
            coupling= ddt_o_dx2 * ( u(j-1,1) - 2*u(j,1) + u(j+1,1) );
        end
        
        % stimulus
        istim = 0;
        if(mod(ntime,period/dt)<1/dt && j<=5)
            istim=stimmag;
        end
        
        % update u using forward Euler
        u_updated(j,1) = u(j,1) - (jfi+jso+jsi-istim)*dt + coupling;
        
    end % end of spatial loop
    
    % save updated u values in regular u array
    u = u_updated;

    % if it's time, save u values in the appropriate slot in the array
    % this is saving a whole "column" at a time through the magic of
    % Matlab!
    if(mod(ntime,iout)==0)
        kout=kout+1;
        usave(kout,:) = u;
    end
    
end

% plot action potential traces from several locations
figure(5)
plot(tout,usave(:,nx/4),'k',tout,usave(:,nx/2),'r',tout,usave(:,3*nx/4),'g','linewidth',2)
xlabel('Time (ms)','fontsize',16), ylabel('u','fontsize',16)
set(gca,'fontsize',16)

% plot wave profiles in space at several points in time
figure(6)
plot(xx',usave(15,:),'k',xx',usave(30,:),'r',xx',usave(45,:),'g','linewidth',2)
xlabel('Space (cm)','fontsize',16), ylabel('u','fontsize',16)
set(gca,'fontsize',16)

% 3D: flat
figure(7)
surf(tout,xx,usave'),shading interp,xlabel('Time (ms)','fontsize',16),ylabel('Space (cm)','fontsize',16),colorbar
set(gca,'fontsize',16)

% space-time plot: flattended version of 3D plot with color representing
% the "height" (u-variable)
figure(8)
pcolor(tout,xx,usave'),shading interp,xlabel('Time (ms)','fontsize',16),ylabel('Space (cm)','fontsize',16),colorbar
set(gca,'fontsize',16)
