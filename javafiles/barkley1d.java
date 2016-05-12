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

/*      <applet code="barkley1d" width=280 height=400>
         </applet>
*/

	public class barkley1d extends Applet
	implements MouseListener, ActionListener, Runnable{
        Point p;
        Button butt,butf,buts,butr,butrs,butla,butsm; // Setting varibles to become buttons
        int nx=200;
        int nstop=1;
        int nspeed=20;
        double pp;
//        int angle=0;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[300];
        double v[] = new double[300];
        double ut[] = new double[300];
        double vt[]= new double[300];
        double a1 = 1.02;
        double dd = 0.25;
//        double a = 2.0;
        double a = 1.02;
        double h2 = 100.0;
        double dt = 0.005;
        double dx = 0.5;
        double du,dv,xlap;
        double diff = 1.0;
        double b = 0.15;
        double eps = 0.02;

	public void init() {
        for(int i=1; i<nx; i++){
         u[i] = 0.0;
         v[i] = 0.0;
        }
//         for(int i=30; i<50; i++){
//          u[i]=0.0;
//          h[i+20]=0.0;
//                               }

	 Thread thread = new Thread(this);
	 thread.start();
	 addMouseListener(this);
//----Button Start----
	butt = new Button ("Start");
        butt.addActionListener( this );
        add (butt);
//----Button Faster----
	butf = new Button ("Faster");
        butf.addActionListener( this );
        add (butf);
//----Button Slower----
	buts = new Button ("Slower");
        buts.addActionListener( this );
        add (buts);
//----Button Reset----
	butr = new Button ("Reset");
        butr.addActionListener( this );
        add (butr);
//----Button Restart----
    butrs= new Button ("Restart");
        butrs.addActionListener(this );
        add (butrs);
//----Button Larger Ring----
    butla= new Button ("Larger ring");
        butla.addActionListener(this );
        add (butla);
//----Button Smaller Ring----
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
//          u[p.x-20]=(95.0-pp)/2.0;
//          u[p.x-19]=(95.0-pp)/2.0;
//          u[p.x-18]=(95.0-pp)/2.0;
//          u[p.x-21]=(95.0-pp)/2.0;
//          u[p.x-22]=(95.0-pp)/2.0;
          u[p.x-20]=(300.0-pp)/100.0;
          u[p.x-19]=(300.0-pp)/100.0;
          u[p.x-18]=(300.0-pp)/100.0;
          u[p.x-21]=(300.0-pp)/100.0;
          u[p.x-22]=(300.0-pp)/100.0;
         down=true;
//=(int)(300-u[i]*100.0)
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
            u[i] = 0.0;
            v[i] = 0.0;
         nstop=1;
        						}
//         for(int i=0; i<25; i++){
         for(int i=0; i<5; i++){
          u[i]=0.2;
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
            u[i] = 0.0;
            v[i] = 0.0;          }
                               }
       if(ae.getSource()==butrs){
        for(int i=0; i<300; i++){
         u[i] = 0.0;
         v[i] = 0.0;
                                }
//         for(int i=0; i<25; i++){
         for(int i=0; i<5; i++){
          u[i]=0.2;
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
            v[i]=v[nx-1];
            }
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
        g.drawRect(20,54,nx,320);
//        g.setColor(Color.red);
//        g.drawLine(20,200,320,200);
        g.setColor(Color.black);
        g.drawString("V",3,155);
        g.drawString("(mV)",0,165);
//        g.drawString("L=",110,275);
        g.drawString("L= ("+String.valueOf(nx)+") points",100,280);
//        g.drawString("points",119,275);
         for(int i=0; i< nx-1; i++){
         int yy1=(int)(300-u[i]*100.0);
         int yy2=(int)(300-u[i+1]*100.0);
         g.drawLine(i+20,yy1,i+21,yy2);
         }



         if(nstop < 5){

       // Draw restitution 
//         g.drawLine(10,10,p.x,p.y);
          int sen=0;
         double x=0.0;

           for (int i=0; i<nx-1; i++) {
              du=(1/eps)*u[i]*(1.0-u[i])*(u[i]-(b+v[i])/a);
              dv=u[i]-v[i];
              v[i]=v[i]+dt*dv;
              if(i==0) {
                 xlap=u[i+1]-2.*u[i]+u[i+1];
              }
              else if(i==nx-1) {
                 xlap=u[i-1]-2.*u[i]+u[i-1];
              }
              else {
                 xlap=u[i+1]-2.*u[i]+u[i-1];
              }
              ut[i]=u[i]+dt*(xlap*diff/(dx*dx)+du);
           }
           
           for (int i=0; i<nx-1; i++) {
               u[i]=ut[i];
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
	
