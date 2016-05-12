//    Java program that shows Hopf bifurcation on restitution with slope
//    grater than one.
// 
// Copyright (c) 1998 Flavio H Fenton.
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
// Your are welcome to use, change and redistribute this progrma, we only request
// to please ratain the above information for future references. 



	import java.applet.*;
        import java.awt.*;
        import java.awt.event.*;

        /*
	 <applet code="Hopfapdapd" width=650 height=620>
	 </applet>
	*/

        public class Hopfapdapd extends Applet
        implements MouseListener, MouseMotionListener {
        Point p;
        Point pi;
        double a0,a1,a2,a3,a4,b0,b1,b2,b3,b4,f1,f2,fppi;
        double apd1,apd2,bcl;
        int iapd1,iapd2,ibcl;
        int apds1[]= new int[9000];
        int apds2[]= new int[9000];
        int apdd1[]= new int[9000];
        int apdd2[]= new int[9000];
        int perr[]= new int[9000];
        int ncounter=0;
        int nh=0;
        double tper;
        double xx;
        double xx2;
        double x;
        int ixx;
        int iyy;
        int ix;
        int y1;
        int iix; 
        int y1old;
        int ir1,ir2,ir3,ir4;
        int firsttime=1;
        int aapd1,aapd2;

        int apdcounter=0;

         public void init() {

          addMouseListener(this);
          addMouseMotionListener(this);
          for(int i=0; i<9000; i++){apds1[i]=0;}
          for(int i=0; i<9000; i++){apds2[i]=0;}
          for(int i=0; i<9000; i++){apdd1[i]=0;}
          for(int i=0; i<9000; i++){apdd2[i]=0;}
          for(int i=0; i<9000; i++){perr[i]=0;}

         }

         public void mouseClicked(MouseEvent me){
         }
         public void mouseEntered(MouseEvent me){
         }
         public void mouseExited(MouseEvent me){
         }
         public void mousePressed(MouseEvent me){
         p=me.getPoint();
         pi=me.getPoint();
         firsttime=2;
         repaint();
         } 
         public void mouseReleased(MouseEvent me){
         p=null;
//         repaint();
         }
         public void mouseDragged(MouseEvent me){
         p=me.getPoint();
         repaint();
         }
         public void mouseMoved(MouseEvent me){
         }
         
         

	 public void paint(Graphics g) {
   
          if(firsttime==1){g.drawString("Click on the window",180,48);}
          if(firsttime==1){g.drawString("to select a period",185,68);}

          g.setColor(Color.red);

          g.drawLine(60,320,360,20);
        

       // Draw axes
        g.setColor(Color.blue);
//        Dimension d = getSize();
//        int width = d.width;
//        int height = d.height;
//        g.drawRect(0,0,width-1,height-1);
//        g.setColor(Color.yellow);
//        g.drawOval(110,320-247,2,2);
//        g.setColor(Color.orange);
//        g.drawOval(112,320-250,2,2);
        g.setColor(Color.black);
        g.drawLine(60,20,60,320);
        g.drawLine(60,320,550,320);

        g.drawString("|",160,322);
        g.drawString("100",149,335);

        g.drawString("|",260,322);
        g.drawString("200",249,335);
        g.drawString("|",310,322);
        g.drawString("250",299,335);

        g.drawString("|",360,322);
        g.drawString("300",349,335);

        g.drawString("|",110,322);
        g.drawString("50",104,335);


//        g.drawString("200",240,335);
//        g.drawString("300",340,335);

        g.drawString("100-",30,220);
        g.drawString("150-",30,170);
        g.drawString("200-",30,120);
        g.drawString("250-",30,70);
        g.drawString("300-",30,20);
        g.drawString("50--",30,270);

        g.drawString("50--",30,529);

        g.drawString("100-",30,491);
        g.drawString("150-",30,454);
        g.drawString("200-",30,416);
//        g.drawString("300-",30,404);
//        g.drawString("250-",30,429);

        g.drawString("250-",30,379);
//        g.drawString("400-",30,479);


        g.drawString("|",140,552);
        g.drawString("250",130,568);

        g.drawString("|",190,552);
        g.drawString("300",180,568);

        g.drawString("|",240,552);
        g.drawString("350",230,568);

       g.drawString("|",290,552);
        g.drawString("400",280,568);

       g.drawString("|",340,552);
        g.drawString("450",330,568);


       g.drawString("|",390,552);
        g.drawString("500",380,568);

        g.drawString("APD_n+1",2,185);
        g.drawString("APD_n",205,338);
        g.drawString("Period",210,588);



        g.drawLine(60,350,60,550);
        g.drawLine(60,550,550,550);

        g.setColor(Color.white);
//        g.fillRect(61,21,340,298);
//        g.fillRect(61,351,340,198);

        g.setColor(Color.black);


       // Draw restitution 
          int sen=0;
         double dx=1.0;
         
         xx=30;
        g.setColor(Color.blue);
//        if(p !=null){
         int per = p.x-20+320-p.y;
         int perd = 2*(p.x-20+320-p.y);
         int yp1=320 - per;
         int xp2= per+20;
//         g.drawLine(20,yp1,xp2,320);
           g.drawLine(p.x,552,p.x,330);
         tper=(double)(p.x+109);
        g.drawString("T="+String.valueOf((tper))+"ms",275,368);
//        g.drawString(String.valueOf(tper-x
           g.setColor(Color.black);
          
         

         for(int i=0; i< 500; i++){
          x=tper-xx;
         b0=255.377;
         b1=0.41592;
         b2=149.173;
         b3=298.2;
         b4=11.1681;

        if(x >105.7) {
       a0= 134.1; 
       a1= 1.041 ;
       a2= -0.004848; 
       a3= 1.242e-05 ;
       a4= -1.292e-08; } 
        if(x<= 105.7 && x > 90){
        a0= 565.7; 
        a1= -21.34; 
        a2= 0.2987; 
        a3= -0.001223; 
        a4=0          ;         }
        
        if(x <= 90  && x > 65) {
        a0= 837.8 ;
        a1= -34.16 ;
        a2= 0.4834 ;
        a3= -0.002065; 
        a4=0          ;         }
        
        if(x <= 65 ){
        a0= -647.8 ;
        a1= 34.1 ;
        a2= -0.567; 
        a3= 0.003349;
        a4=0      ;  }

       
        double x2=x+dx;
        if(x < 88){
         f1=a0+a1*x+a2*x*x+a3*x*x*x+a4*x*x*x*x;
         f2=a0+a1*x2+a2*x2*x2+a3*x2*x2*x2+a4*x2*x2*x2*x2;
                  }
        if(x>=88){
         f1=b0*(1-b1*Math.exp(-x/b2)-b3*Math.exp(-x/b4));
         f2=b0*(1-b1*Math.exp(-x2/b2)-b3*Math.exp(-x2/b4));
                 }

//         double f1=0.06*(1-3000*Math.exp(-x/50)-1500*Math.exp(-x/10))+300;
//         double f2=0.06*(1-3000*Math.exp(-(x+dx)/50)-1500*Math.exp(-(x+dx)/10))+300;
          y1=(int)(320-f1);
         int y2=(int)(320-f2);
         ixx=(int)(xx);
        if(y1<300)g.drawLine(ixx+60,y1,ixx+61,y2);
         xx+=dx;
         }        











        g.setColor(Color.orange);
      
              g.fillOval(150-10,250,20,20);

          x=tper-(float)(150-61);

          for(int i=0; i< 900; i++){
          if( i > 1 )ixx=(int)(360-y1+20);

         b0=255.377;
         b1=0.41592;
         b2=149.173;
         b3=298.2;
         b4=11.1681;

        if(x >105.7) {
       a0= 134.1; 
       a1= 1.041 ;
       a2= -0.004848; 
       a3= 1.242e-05 ;
       a4= -1.292e-08; } 
        if(x<= 105.7 && x > 90){
        a0= 565.7; 
        a1= -21.34; 
        a2= 0.2987; 
        a3= -0.001223; 
        a4=0          ;         }
        
        if(x <= 90  && x > 65) {
        a0= 837.8 ;
        a1= -34.16 ;
        a2= 0.4834 ;
        a3= -0.002065; 
        a4=0          ;         }
        
        if(x <= 65 ){
        a0= -647.8 ;
        a1= 34.1 ;
        a2= -0.567; 
        a3= 0.003349;
        a4=0      ;  }       
        double x2=x+dx;
        if(x < 88){
         f1=a0+a1*x+a2*x*x+a3*x*x*x+a4*x*x*x*x;
         f2=a0+a1*x2+a2*x2*x2+a3*x2*x2*x2+a4*x2*x2*x2*x2;
                  }
        if(x>=88){
         f1=b0*(1-b1*Math.exp(-x/b2)-b3*Math.exp(-x/b4));
         f2=b0*(1-b1*Math.exp(-x2/b2)-b3*Math.exp(-x2/b4));
                 }
         y1=(int)(320-f1);
         int y2=(int)(320-f2);
         ixx=(int)(xx);
//        if(y1<300)g.drawLine(ixx+60,y1,ixx+61,y2);
         xx+=dx;
         ix=(int)(tper-x+61);

       if(tper>273){
     if(i==0){ g.drawLine(ix,250,ix,y1);  }
        else{ g.drawLine(ix,y1old,ix,y1);  }
       g.drawLine(ix,y1,360-y1+20,y1);
       x=tper-(float)(360-y1+20-61);
        y1old=y1;  
         if(i==890){              g.setColor(Color.red);
             aapd1=(int)(f1);
             iyy=554-(int)(f1/2.);
              ir1=(int)(tper-109);
              ir2=iyy;  
            g.fillOval((int)(tper-109)-5,iyy-50,10,10);
            if(ncounter < 8000){ apdd1[ncounter]=ir2;
                              //   perr[ncounter]=ir1;
                              //   ncounter=ncounter+1;
                                }
                           
                    }
         if(i==891){              g.setColor(Color.red);
              aapd2=(int)(f2);
             iyy=554-(int)(f1/2.);
               ir3=(int)(tper-109);
               ir4=iyy;
              g.fillOval((int)(tper-109)-5,iyy-50,10,10);
            if(ncounter < 8000){ apdd2[ncounter]=ir4;
                                 perr[ncounter]=ir1;
                                 ncounter=ncounter+1;
                                }

                    }


                    }
         else{
     if(i==0){ g.drawLine(ix,250,ix,y1);  
               g.drawLine(ix,y1,360-y1+20,y1);
       x=tper-(float)(360-y1+20-61);
        y1old=y1;  
             }
      if(i==1){ if(y1 > 310) y1=310;
           g.drawLine(ix,y1old,ix,y1);  }
        g.drawString("Out of Range",255,268);



             }

                                   }

        g.setColor(Color.orange);
        if(aapd1==aapd2){
        g.drawString("APD="+String.valueOf((aapd1))+"ms",345,58);
                         }
         else{
        g.drawString("APD_1="+String.valueOf((aapd1))+"ms",345,58);
        g.drawString("APD_2="+String.valueOf((aapd2))+"ms",345,78);

                         }


















          
 
          
   //           g.setColor(Color.red);
   //           g.fillOval(ir1,ir2,10,10);
   //           g.fillOval(ir3,ir4,10,10);

            g.setColor(Color.black);
              for(int j=0;j<ncounter; j++){
              g.drawOval(perr[j],apdd1[j]-50,2,2);
              g.drawOval(perr[j],apdd2[j]-50,2,2);
                                           }


 

          }
          }





