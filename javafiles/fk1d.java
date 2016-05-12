//    Java program FHN 1d
// 
// Copyright (c) 1998 Flavio H Fenton.
//
// Your are welcome to use, change and redistribute this progrma, we only request
// to please ratain the below information for future references 
// and to let me know each time you give the program away 
//
// send e-mail at fhf3@cornell.edu  (Flavio H Fenton) 
// 
// if this program helps on any work for publication please use as
// citation :
//    Real-time Computer Simulations of Excitable Media: 
//    Java as a Scientific Language and as a Wrapper for C and Fortran Programs. BioSystems 2002; 64: 73-96. 
//    Fenton FH, Cherry EM, Hastings HM, Evans SJ. 
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either version 2
//of the License, or (at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//for  copy of the GNU General Public License
//write to the Free Software
//Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//






	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="fk1d" width=250 height=280>
         </applet>
*/

	public class fk1d extends Applet
	implements MouseListener, ActionListener, Runnable{
        Point p;
        Button butt,butf,buts,butr,butrs,butla,butsm;
        int nx=200;
        int nstop=1;
        int nspeed=20;
        double pp;
//        int angle=0;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[300];
        double h[] = new double[300];
        double f[] = new double[300];
        double ut[]= new double[300];
        double a=0.1;
        double gama=1;
        double b=0.5;
        double eps=0.01;
        double delta=0;
        double dt=0.2;
        double dx2=1.0;
        double xpp,xqq,ifi,iso,isi;

	public void init() {
        for(int i=0; i<300; i++){
         u[i]=-85;
         h[i]=1;
         f[i]=1;
         ut[i]=0;
                                }
//         for(int i=30; i<50; i++){
//          u[i]=0.0;
//          h[i+20]=0.0;
//                               }

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
        butla= new Button ("Larger ring");
        butla.addActionListener(this );
        add (butla);
        butsm= new Button ("Smaller ring");
        butsm.addActionListener(this );
        add (butsm);
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
          u[p.x-20]=(95.0-pp)/2.0;
          u[p.x-19]=(95.0-pp)/2.0;
          u[p.x-18]=(95.0-pp)/2.0;
          u[p.x-21]=(95.0-pp)/2.0;
          u[p.x-22]=(95.0-pp)/2.0;
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
        for(int i=0; i<300; i++){
         u[i]=-85.0;
         h[i]=1.0;
         f[i]=1.0;
         nstop=1;
                                }
         for(int i=30; i<50; i++){
          u[i]=0.0;
          h[i+20]=0.0;
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
        for(int i=0; i<300; i++){
         u[i]=-85.0;
         h[i]=1.0;
         f[i]=1.0;
                                }
                               }
       if(ae.getSource()==butrs){
        for(int i=0; i<300; i++){
         u[i]=-85.0;
         h[i]=1.0;
         f[i]=1.0;
                                }
         for(int i=30; i<50; i++){
          u[i]=0.0;
          h[i+20]=0.0;
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
         if(ae.getSource()==butla){
            if(nx >295){
             nx=295;
           butla.setLabel("             ");
                       }
           for(int i=nx; i<nx+5; i++){
            u[i]=u[nx-1];
            h[i]=h[nx-1];
            f[i]=f[nx-1];            }
           nx=nx+5;
           butsm.setLabel("Smaller ring"); 
                                 }
        if(ae.getSource()==butsm){
             nx=nx-5;
            if(nx < 100){
              nx=100;
             butsm.setLabel("           ");
                        }
             butla.setLabel("Larger ring");
                                 }
                                                      }
//          if(butr.getLabel().equals("Reset")){
//           butr.setLabel("Reset");
//        for(int i=0; i<200; i++){
//         u[i]=0;
//         v[i]=0;
//         ut[i]=0;
//                                }
//                                              }
//           if(butt.getLabel().equals("Stop")){
//           butt.setLabel("Start");
//           nstop=10;
//                                              }
//            
//           if(butf.getLabel().equals("Faster")){
//           butf.setLabel("Faster");
//           nspeed=nspeed*2;
//                                                }
//             if(buts.getLabel().equals("Slower")){
//           buts.setLabel("Slower");
//           nspeed=nspeed/2;
//                                                  }
//  
//                                                        } 
//
//	public void update(Graphics g){
        public void paint(Graphics g){
       // Draw axes
        g.setColor(Color.blue);
        Dimension d = getSize();
        int width = d.width;
        int height = d.height;
        g.drawRect(20,54,nx,217);
//        g.setColor(Color.red);
//        g.drawLine(20,200,320,200);
        g.setColor(Color.black);
        g.drawString("V",3,155);
        g.drawString("(mV)",0,165);
//        g.drawString("L=",110,275);
        g.drawString("L= ("+String.valueOf(nx)+") points",100,280);
//        g.drawString("points",119,275);
         for(int i=0; i< nx-1; i++){
         int yy1=(int)(95-u[i]*2.0);
         int yy2=(int)(95-u[i+1]*2.0);
         g.drawLine(i+20,yy1,i+21,yy2);
         }



         if(nstop < 5){

       // Draw restitution 
//         g.drawLine(10,10,p.x,p.y);
          int sen=0;
         double dx=1.0;
         double x=0.0;
           for(int k=1; k<10; k++){
//          v[0]=v[0]+dt*eps*(b*u[0]-gama*v[0]-delta);
//    ut[0]=u[0]+dt*(u[0]*(a-u[0])*(u[0]-1.0)-v[0])+(u[198]+u[1]-2*u[0])*dt/dx2;
            xpp=0;
            xqq=0;
           if(u[0] > -72.0)xpp=1.0;
           if(u[0] > -79.5)xqq=1.0;
           h[0]=h[0]+dt*((1.0-xpp)*(1-h[0])/(1000.0*xqq+19.2*(1-xqq))-0.3*xpp*h[0]);
           f[0]=f[0]+dt*((1.0-xpp)*(1.0-f[0])/11.0-xpp*f[0]/667.0);
           ifi=-0.04*h[0]*(15-u[0])*(u[0]+72)*xpp;
           iso=(u[0]+85.0)*(1.0-xpp)/8.3+2.0*xpp;
           isi=-(1.0+((Math.exp(u[0]*0.1)-Math.exp(-u[0]*0.1))/(Math.exp(u[0]*0.1)+Math.exp(-u[0]*0.1))))*f[0]/0.897;
           ut[0]=u[0]+(u[nx-2]+u[1]-2*u[0])*dt/dx2-dt*(ifi+iso+isi);  

            xpp=0;
            xqq=0;
           if(u[299] > -72.0)xpp=1.0;
           if(u[299] > -79.5)xqq=1.0;
           h[nx-1]=h[nx-1]+dt*((1.0-xpp)*(1-h[nx-1])/(1000.0*xqq+19.2*(1-xqq))-0.3*xpp*h[nx-1]);
           f[nx-1]=f[nx-1]+dt*((1.0-xpp)*(1.0-f[nx-1])/11.0-xpp*f[nx-1]/667.0);
           ifi=-0.04*h[nx-1]*(15-u[nx-1])*(u[nx-1]+72)*xpp;
           iso=(u[nx-1]+85.0)*(1.0-xpp)/8.3+2.0*xpp;
           isi=-(1.0+((Math.exp(u[nx-1]*0.1)-Math.exp(-u[nx-1]*0.1))/(Math.exp(u[nx-1]*0.1)+Math.exp(-u[nx-1]*0.1))))*f[nx-1]/0.897;
           ut[nx-1]=u[nx-1]+(u[nx-2]+u[1]-2*u[nx-1])*dt/dx2-dt*(ifi+iso+isi);  

  
           for(int j=1; j<nx-1; j++){  
            xpp=0;
            xqq=0;
           if(u[j] > -72.0)xpp=1.0;
           if(u[j] > -79.5)xqq=1.0;
           h[j]=h[j]+dt*((1.0-xpp)*(1-h[j])/(1000.0*xqq+19.2*(1-xqq))-0.3*xpp*h[j]);
           f[j]=f[j]+dt*((1.0-xpp)*(1.0-f[j])/11.0-xpp*f[j]/667.0);
           ifi=-0.04*h[j]*(15-u[j])*(u[j]+72)*xpp;
           iso=(u[j]+85.0)*(1.0-xpp)/8.3+2.0*xpp;
           isi=-(1.0+((Math.exp(u[j]*0.1)-Math.exp(-u[j]*0.1))/(Math.exp(u[j]*0.1)+Math.exp(-u[j]*0.1))))*f[j]/0.897;
           ut[j]=u[j]+(u[j+1]+u[j-1]-2*u[j])*dt/dx2-dt*(ifi+iso+isi);  

//           v[j]=v[j]+dt*eps*(b*u[j]-gama*v[j]-delta);
//   ut[j]=u[j]+dt*(u[j]*(a-u[j])*(u[j]-1.0)-v[j])+(u[j-1]+u[j+1]-2*u[j])*dt/dx2;
                                  }          
            for(int j=0; j<nx; j++){
            u[j]=ut[j];
                                    }
             h[nx-1]=h[0];
             f[nx-1]=f[0];
             u[nx-1]=u[0];
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
	
