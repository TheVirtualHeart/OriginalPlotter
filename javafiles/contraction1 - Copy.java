   /*
      <applet code="contraction1" width=600 height=400>
      </applet>
    */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.*;


public class contraction1 extends Applet
  implements ActionListener, AdjustmentListener,  // MouseListener, 
Runnable {

/*---- define gloval -----*/

  Button bt_reset;
  Scrollbar sc_g,sc_k;
  Thread thread = null;

  Dimension dim;
  Image imgOff;
  Graphics gOff;

  int sleepTime = 0;
  double cm = 0.01;             /* unit of length (m)     */
  double dispScale = 10.0/cm;

  double xMax = 60.0*cm;       /* x-Box size in (m)      */
  double yMax = 40.0*cm;       /* y-Box size in (m)      */

  int Nmt = 3000;               /* number of molecules    */
  int NN = Nmt + 1;
  double t = 0.0;               /* time (s)               */
  double dt = 0.00020;           /* time division (s)      */

  int Nsx = 30; /* xMax/5.0  */
  int Nsy = 20; /* yMax/5.0  */

  double ra = 1.0*cm;
  double ra1 = 1.2*ra;
  double ra2 = 1.5*ra;
  double ram = (ra1+ra2)/2.0;
  double fa = 2.0/((ra2-ra1)*(ra2-ra1));

  double rc = 1.05*ra2;
  double rc2 = rc*rc;

  double scale = 0.8;
  double gravity = 1.5;
  double kConstant = 300.0/cm;

  
  int kind[] = new int[NN];       /* i-th molec kind */
  int bond[][] = new int[NN][NN]; /* i-th bond       */
  double mm[] = new double[NN];   /* i-th mass       */
  double xx[] = new double[NN];   /* i-th x-position */
  double yy[] = new double[NN];   /* i-th y-position */
  double vx[] = new double[NN];   /* i-th x-velocity */
  double vy[] = new double[NN];   /* i-th y-velocity */
  double ffx[] = new double[NN];  /* i-th x-force    */
  double ffy[] = new double[NN];  /* i-th y-force    */

  int reg[][] = new int[NN][200];   /* registration */
  int section[][][] = new int[Nsx][Nsy][200];


/*----------------------------- applet control functions -----*/

  public void init() {
    resize(630,400);
    setBackground(Color.white);

    dim = getSize();
    imgOff = createImage(dim.width,dim.height);
    gOff = imgOff.getGraphics();

    sc_g= new Scrollbar(Scrollbar.HORIZONTAL,10,10,0,110);
    sc_g.addAdjustmentListener(this);

    sc_k= new Scrollbar(Scrollbar.HORIZONTAL,30,10,1,110);
    sc_k.addAdjustmentListener(this);

    bt_reset = new Button("reset");
    bt_reset.addActionListener(this); 

    setLayout(new BorderLayout());
    Panel pnl = new Panel();
    pnl.setLayout(new GridLayout(1,6,5,0));
    pnl.add(bt_reset);
    pnl.add(sc_g);
    pnl.add(new Label("  "));
    pnl.add(new Label("  "));
    pnl.add(new Label("  "));
    pnl.add(sc_k);
    add(pnl,"North");

    setInitialPosition();
  }

  public void start() {
    if (thread == null) {
      thread = new Thread(this);
      thread.start();
    }
  }

  public void stop() {
    if (thread != null) {
      thread.stop();
      thread = null;
    }
  }

  public void actionPerformed(ActionEvent ev){ 
    if(ev.getSource() == bt_reset) {
      t = 0.0;
      setInitialPosition();
      repaint();
    }
  }

  public void adjustmentValueChanged(AdjustmentEvent ev){
    if(ev.getSource() == sc_g) { 
      gravity = (double)(sc_g.getValue())/10.0;
    } else if(ev.getSource() == sc_k) { 
      kConstant = (10.0/cm)*(double)(sc_k.getValue());
    }
  }

  public void run() {
    while (thread != null) {
      try {
        calcposition();
        offPaint();
        repaint();
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) { }
    }
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void paint(Graphics g) {
    g.drawImage(imgOff,0,0,this);
  }

/* ----------------------------- offPaint -------------------- */

  void offPaint() {
    int i,j,ix,iy,ir,ixx,iyy;
    int gbx,gby,gb;
    double sc,d;

    gOff.setColor(Color.white);
    gOff.fillRect(0,0,dim.width,dim.height);

    gbx = 30;
    gby = 60;
    sc = scale*dispScale;
    ir = (int)(8.0*scale+0.5);

    ix = (int)(sc*xMax);
    iy = (int)(sc*yMax);
    gOff.setColor(Color.black);
    gOff.drawRect(gbx-ir/2,gby-ir/2,ix+ir,iy+ir);

    gOff.setColor(Color.lightGray);
    for (i=0; i<Nmt; i++) {
      ix = (int)(sc*xx[i])+gbx;
      iy = (int)(sc*yy[i])+gby;
      gOff.fillOval(ix-ir/2,iy-ir/2, ir, ir);
    }

    for (i=0; i<Nmt; i++) {
      ix = (int)(sc*xx[i])+gbx;
      iy = (int)(sc*yy[i])+gby;
      for (j=i+1; j<Nmt; j++) {
        if (bond[i][j]>0) {
          d = Math.sqrt((xx[i]-xx[j])*(xx[i]-xx[j])+(yy[i]-yy[j])*(yy[i]-yy[j]));
          if (d<0.97*ra) {
            gOff.setColor(Color.yellow);
          } else if (d<1.03*ra) {
            gOff.setColor(Color.gray);
          } else if (d<ra1) {
            gOff.setColor(Color.blue);
          } else {
            gOff.setColor(Color.red);
          }
          ixx = (int)(sc*xx[j])+gbx;
          iyy = (int)(sc*yy[j])+gby;
          gOff.drawLine(ix,iy,ixx,iyy);
        }
      }
    }


    gOff.setColor(Color.black);
    gOff.drawString("t="+Math.floor(t*1.0e3)+"(ms)",630/6*0+10,40);
    gOff.setColor(Color.black);
    gOff.drawString("g="+gravity+"(g)",630/6*1+10,40);
    gOff.drawString("k="+kConstant*cm+"(N/m)",630/6*5+10,40);


    gOff.drawString("Lx="+(int)(xMax*100.0+0.5)+" x Ly="+(int)(yMax*100.0+0.5)+"(cm)",630/6*2+20,40);

  }


/*------------------------ set initial position   ---------*/

  void setInitialPosition(){
    int i,j,ix,iy,mt; 
    double a,x,y,r;

    a = ra;

//    i = 0;
      Nmt=0;
    for (ix=0; ix<60; ix++) {
      for (iy=0; iy<50; iy++) {
        x = a*(ix+0.5*(iy%2));
        y = a*0.866*iy;

/* This is to make a rectangle tissue */        
           if(x>0.0 && x<0.6 && y>0.18 && y<0.3){
          xx[Nmt] = x;
          yy[Nmt] = y;
          kind[Nmt] = 0;
          mm[Nmt] = 0.01;
          vx[Nmt] = 0.0;   
          vy[Nmt] = 0.0;  
          ffx[Nmt] = 0.0;
          ffy[Nmt] = 0.0;
          Nmt += 1;
                                                 }
/* This is to make the smaller rectangle tissue  */        
           if(y>0.04 && y<0.1 && ((x-.3)*(x-.3))<.04*.04) {
          xx[Nmt] = x;
          yy[Nmt] = y;
          kind[Nmt] = 0;
          mm[Nmt] = 0.01;
          vx[Nmt] = 0.0;   
          vy[Nmt] = 0.0;  
          ffx[Nmt] = 0.0;
          ffy[Nmt] = 0.0;
          Nmt += 1;
                                                 }
      }
    }

// Here we bound the elements that are close to each other with springs 

    for (i=0; i<Nmt; i++) {
      for (j=i+1; j<Nmt; j++) {
        r = Math.sqrt((xx[i]-xx[j])*(xx[i]-xx[j])+(yy[i]-yy[j])*(yy[i]-yy[j]));
        if (r<ra2) {
          bond[i][j]=1;
        }
      }
    }
  }

/*----------------------------  registration   -------------*/

  void registration() {
    int i,j,ip,jp,kp,i0,i1,j0,j1,iq;
    double r2,rreg,rreg2;
    double x,y,z;

    preRegistration();

    rreg = rc+2.0*20.0*dt;
    rreg2 = rreg*rreg;

    for(ip=0;ip<Nmt;ip++) {
      kp = 1;

      i0 = (int)(Nsx*(xx[ip]-rreg)/xMax); if (i0<0) i0 = 0;
      i1 = (int)(Nsx*(xx[ip]+rreg)/xMax); if (i1>=Nsx) i1 = Nsx-1;
      j0 = (int)(Nsy*(yy[ip]-rreg)/yMax); if (j0<0) j0 = 0;
      j1 = (int)(Nsy*(yy[ip]+rreg)/yMax); if (j1>=Nsy) j1 = Nsy-1;

      for(i=i0;i<=i1;i++) {
        for(j=j0;j<=j1;j++) {
          for(iq=1;iq<=section[i][j][0];iq++) {
            jp = section[i][j][iq];
            if (jp>ip) {
              r2=(xx[ip]-xx[jp])*(xx[ip]-xx[jp])+(yy[ip]-yy[jp])*(yy[ip]-yy[jp]);
              if (r2<rreg2) {
                reg[ip][kp]=jp;
                kp += 1;
				            if (r2>ra2*ra2) {
				            //  bond[ip][jp] = 0;bond[jp][ip] = 0;
                }
              }
            }
          }
        }
      }
      reg[ip][0]=kp;

    }
  }

  void preRegistration() {
    int i,j,ip,iq;

    for(i=0;i<Nsx;i++) {
      for(j=0;j<Nsy;j++) {
        section[i][j][0] = 0;
      }
    }

    for(ip=0;ip<Nmt;ip++) {
      i = (int)(Nsx*xx[ip]/xMax); if (i>=Nsx) i = Nsx-1;
      j = (int)(Nsy*yy[ip]/yMax); if (j>=Nsy) j = Nsy-1;
      iq = section[i][j][0]+1;
      section[i][j][0] = iq;
      section[i][j][iq] = ip;
    }
  }

/* ---------------------------------------------- */

  void calcposition(){
    int i;

    registration();
//    for (i=0; i<2; i++) {
      timeEvolution();
//    }
  }


  void timeEvolution() {
    int i; 
    double dtv2, a2, rr;

    dtv2 = dt/2.0;
    t = t + dt;

    rr = 0.95;

    for (i=0; i<Nmt; i++) {
      a2 = dtv2/mm[i];
      vx[i] += a2*ffx[i];
      vy[i] += a2*ffy[i];
      xx[i] += vx[i]*dt;
      yy[i] += vy[i]*dt;
    }
    forceCalc();

    for (i=0; i<Nmt; i++) {
      a2 = dtv2/mm[i];
      vx[i] += a2*ffx[i];
      vy[i] += a2*ffy[i];
    }

    for (i=0; i<Nmt; i++) {
      if (xx[i] < 0.0) { 
        xx[i] = 0.0; vx[i] = -rr*vx[i]; vy[i] = rr*vy[i]; 
      }
      if (xx[i] > xMax) {
        xx[i] = xMax; vx[i] = -rr*vx[i]; vy[i] = rr*vy[i]; 
      }
      if (yy[i] < 0.0) { 
        yy[i] = 0.0; vx[i] = rr*vx[i]; vy[i] = -rr*vy[i]; 
      }
      if (yy[i] > yMax) {
        yy[i] = yMax; vx[i] = rr*vx[i]; vy[i] = -rr*vy[i];
      }

    }

  }


  void forceCalc() {
    int i,j,k;
    double r2,rij,f,fxij,fyij;

    for(i=0;i<Nmt;i++) {
      ffx[i] = 0.0;
      ffy[i] = mm[i]*gravity*9.8;
    }

    for(i=0;i<Nmt;i++) {
      for(k=1;k<reg[i][0];k++) {
        j = reg[i][k];
        r2 = (xx[i]-xx[j])*(xx[i]-xx[j])+(yy[i]-yy[j])*(yy[i]-yy[j]);
        if (r2<rc2) {
          rij = Math.sqrt(r2);
          f = force(rij,i,j);
          fxij = f*(xx[i]-xx[j])/rij;
          fyij = f*(yy[i]-yy[j])/rij;

          ffx[i] +=  fxij;
          ffy[i] +=  fyij;

          ffx[j] += -fxij;
          ffy[j] += -fyij;
        }
      }
    }
  }


  double force(double r,int ii, int jj) {
    int i,j;
    double f,r1,r3;    

    if (ii<jj) {
      i= ii; j=jj;
    } else {
      i= jj; j=ii;
    }
    if (r>ra2) {
      // bond[i][j]=0; bond[j][i]=0;
    } 

    f = 0.0;
    if (bond[i][j]==0) {
      if (r<ra) {
        f = kConstant*(r-ra);
      }
    } else {
      if (r<=ra1) {
        f = kConstant*(r-ra);
      } else if (r>=ra2) {
        f = 0.0;
      } else if (ra1<=r && r<ram) {
        f = kConstant*(r-ra)*(1.0-fa*(r-ra1)*(r-ra1));
      } else if (ram<=r && r<ra2) {
        f = kConstant*(r-ra)*fa*(r-ra2)*(r-ra2);
      }
    }

    return ( -f );
  }

/*----- end of molecules motion -----*/

}  

/*----- end of applet -----*/

