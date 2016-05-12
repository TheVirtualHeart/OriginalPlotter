	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="threev_1dlehman" width=250 height=280>
         </applet>
*/

	public class threev_1dlehman extends Applet
	implements MouseListener, ActionListener, Runnable{
        Point p;
        Button butt,butf,buts,butr,butrs;
        int nx=0;
        int nstop=1;
        int nspeed=20;
        double pp;
//        int angle=0;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[201];
        double v[] = new double[201];
        double w[] = new double[201];
        double ut[]= new double[201];
        double vc = 0.13;
        double vv = 0.04;
        double vm = 1.0;
        double td = 0.25;
        double vo = 0 ;
        double to = 12.5;
        double tr = 33.33;
        double xk = 10.0;
        double vsi_c =0.85;
        double tsi = 29;
        double tvm1 = 19.6;
        double tvm2 = 1250;
        double tvp = 3.33;
        double twm = 41;
        double twp = 870;   
        double xxp;
        double xxq;
        double ifi,iso,isi;

        double dt=0.05;
        double dx2=.001/(0.025*0.025);

	public void init() {
	    for(int i=0; i<200; i++){
		u[i]=0;
		v[i]=1;
		w[i]=1;
		ut[i]=0;
	    }

	    Thread thread = new Thread(this);
	    thread.start();
	    addMouseListener(this);
	    butt = new Button ("Start");
	    butt.addActionListener( this );
	    add (butt);
	    butf = new Button ("Faster");
	    butf.addActionListener( this );
	    add (butf);
	    buts = new Button ("Slower");
	    buts.addActionListener( this );
	    add (buts);
	    butr = new Button ("Reset");
	    butr.addActionListener( this );
	    add (butr);
	    butrs= new Button ("Restart");
	    butrs.addActionListener(this );
	    add (butrs);

	}
	public void mouseClicked(MouseEvent me){
	}
	public void mouseEntered(MouseEvent me){
	}
	public void mouseExited(MouseEvent me){
	}
        public void mousePressed(MouseEvent me){
	    p=me.getPoint();
	    pp=p.y;
	    u[p.x-20]=(200.0-pp)/180.0;
	    u[p.x-19]=(200.0-pp)/180.0;
	    u[p.x-18]=(200.0-pp)/180.0;
	    u[p.x-21]=(200.0-pp)/180.0;
	    u[p.x-22]=(200.0-pp)/180.0;
	    down=true;
	}
	public void mouseReleased(MouseEvent me){
	    down=false;
	}
        public void actionPerformed( ActionEvent ae){
//          if(butt.getLabel().equals("Start")){
                      if(ae.getSource()==butt){
                         if(firsttime){
			     butt.setLabel("Stop");
			     for(int i=0; i<201; i++){
				 u[i]=0;
				 v[i]=1;
				 w[i]=1;
				 ut[i]=0;
				 nstop=1;
			     }
			     for(int i=45; i<50; i++){
				 u[i]=0.9;
				 v[i+5]=0.0;
			     }
			 }
			 else{
			     if(butt.getLabel().equals("Cont.")){
				 butt.setLabel("Stop");
				 nstop=1;
			     }
			     else{
				 butt.setLabel("Cont.");
				 nstop=10;
			     } 
			 }
			 firsttime=false;
		      }
		      if(ae.getSource()==butr){
			  for(int i=0; i<201; i++){
			      u[i]=0;
			      v[i]=1;
			      w[i]=1;
			      ut[i]=0;
			  }
		      }
		      if(ae.getSource()==butrs){
			  for(int i=0; i<201; i++){
			      u[i]=0;
			      v[i]=0;
			      w[i]=0;
			      ut[i]=0;
			  }
			  for(int i=45; i<50; i++){
			      u[i]=0.9;
			      v[i+5]=0.0;
			  }

		      }
		      if(ae.getSource()==butf){
			  nspeed=nspeed-5;
			  if(nspeed < 4){
			      nspeed=5;
			      butf.setLabel("      ");
			  }
		      }
		      if(ae.getSource()==buts){
			  nspeed=nspeed+5;
			  butf.setLabel("Faster"); 
		      }

                                                      }
        public void paint(Graphics g){
	    nx=nx+1;
	    if(nx > 60)nx=0;
       // Draw axes
	    g.setColor(Color.blue);
	    Dimension d = getSize();
	    int width = d.width;
	    int height = d.height;
	    g.drawRect(20,26,200,240);
	    g.setColor(Color.red);
	    g.drawLine(20,200,220,200);
	    g.setColor(Color.black);
	    g.drawString("APD",0,155);
	    g.drawString("L",110,275);
	    for(int i=0; i< 199; i++){
		int yy1=(int)(200-u[i]*180);
		int yy2=(int)(200-u[i+1]*180);
		g.drawLine(i+20,yy1,i+21,yy2);
	    }



	    if(nstop < 5){

       // Draw restitution 
//         g.drawLine(10,10,p.x,p.y);
		int sen=0;
		double dx=1.0;
		double x=0.0;
		for(int k=1; k<10; k++){
		    xxq=0;
		    xxp=0;
		    if(u[0] >= vc)xxp=1;
		    if(u[0] >= vv)xxq=1; 
		    v[0]=v[0]+dt*( (1-xxp)*(1-v[0])/((1-xxq)*tvm1+xxq*tvm2) - xxp*v[0]/tvp);
		    w[0]=w[0]+dt*( (1-xxp)*(1-w[0])/twm - xxp*w[0]/twp);
//		    ifi=-v[0]*(u[0]-vc)*(u[0]-vm)*xxp/td;
		    ifi=-v[0]*(u[0]-vc)*(vm-u[0])*xxp/td;
//		    iso=(u[0]-vo)*(1.0-xxp)/to+xxp/tr;
		    iso=u[0]*(1.0-xxp)/to+xxp/tr;
		    isi=-(1.0+((Math.exp(u[0]*0.1)-Math.exp(-u[0]*0.1))/(Math.exp(u[0]*0.1)+Math.exp(-u[0]*0.1))))*w[0]/(2*tsi);
		    ut[0]=u[0]+dt*(u[200]+u[1]-2*u[0])*dx2-dt*(ifi+iso+isi);

		    if(u[200] >= vc)xxp=1;
		    if(u[200] >= vv)xxq=1; 
		    v[200]=v[200]+dt*( (1-xxp)*(1-v[200])/((1-xxq)*tvm1+xxq*tvm2) - xxp*v[200]/tvp);
		    w[200]=w[200]+dt*( (1-xxp)*(1-w[200])/twm - xxp*w[200]/twp);
//		    ifi=-v[200]*(u[200]-vc)*(u[200]-vm)*xxp/td;
		    ifi=-v[200]*(u[200]-vc)*(vm-u[200])*xxp/td;
//		    iso=(u[200]-vo)*(1.0-xxp)/to+xxp/tr;
		    iso=u[200]*(1.0-xxp)/to+xxp/tr;
		    isi=-(1.0+((Math.exp(u[200]*0.1)-Math.exp(-u[200]*0.1))/(Math.exp(u[200]*0.1)+Math.exp(-u[200]*0.1))))*w[200]/(2*tsi);
		    ut[200]=u[200]+dt*(u[199]+u[1]-2*u[200])*dx2-dt*(ifi+iso+isi);
	     
  
		    for(int j=1; j<199; j++){  

			v[j]=v[j]+dt*( (1-xxp)*(1-v[j])/((1-xxq)*tvm1+xxq*tvm2) - xxp*v[j]/tvp);
			w[j]=w[j]+dt*( (1-xxp)*(1-w[j])/twm - xxp*w[j]/twp);
//			ifi=-v[j]*(u[j]-vc)*(u[j]-vm)*xxp/td;
			ifi=-v[j]*(u[j]-vc)*(vm-u[j])*xxp/td;
//			iso=(u[j]-vo)*(1.0-xxp)/to+xxp/tr;
			iso=u[j]*(1.0-xxp)/to+xxp/tr;
			isi=-(1.0+((Math.exp(u[j]*0.1)-Math.exp(-u[j]*0.1))/(Math.exp(u[j]*0.1)+Math.exp(-u[j]*0.1))))*w[j]/(2*tsi);

			ut[j]=u[j]+dt*(u[j+1]+u[j-1]-2*u[j])*dx2-dt*(ifi+iso+isi);

		    }          
		    for(int j=0; j<201; j++){
			u[j]=ut[j];
		    }
		} 
   
	    }
	}

	public void run() {
	    try {
//	  int angle= 0;
		while(true){
		    Thread.sleep(nspeed);
		    if(!down) {
//	     double radians = angle * Math.PI/180;
//	      int r=(int)(127);
//	      int g=(int)(127);
//		int b=(int)(127+angle);
//               Color color=new Color(r,g,b);
//		setBackground(color);
			repaint();
//  		++angle;
//		if(angle>360) angle=0;
		    }
		}
	    }
	    catch(Exception e){
	    }
	}
	}
	
