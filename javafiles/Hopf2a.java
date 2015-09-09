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
	 <applet code="Hopf2a" width=450 height=620>
	 </applet>
	*/

        public class Hopf2a extends Applet
        implements MouseListener, MouseMotionListener {
        Point p;
        Point pi;
        double a0,a1,a2,a3,a4,b0,b1,b2,b3,b4,f1,f2,fppi;
        double apd1,apd2,bcl;
        int iapd1,iapd2,ibcl;
        int apds1[]= new int[9000];
        int apds2[]= new int[9000];
        int nh=0;

        int apdcounter=0;

         public void init() {
          addMouseListener(this);
          addMouseMotionListener(this);
          for(int i=0; i<9000; i++){apds1[i]=0;}
          for(int i=0; i<9000; i++){apds2[i]=0;}

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
   
          

       // Draw axes
        g.setColor(Color.blue);
//        Dimension d = getSize();
//        int width = d.width;
//        int height = d.height;
//        g.drawRect(0,0,width-1,height-1);
//        g.setColor(Color.yellow);
//        g.drawOval(110,320-247,2,2);
        g.setColor(Color.orange);
//        g.drawOval(112,320-250,2,2);
        g.setColor(Color.black);
        g.drawLine(60,20,60,320);
        g.drawLine(60,320,380,320);

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

        g.drawString("APD",2,185);
        g.drawString("DI",210,338);
        g.drawString("Period",210,588);



        g.drawLine(60,350,60,550);
        g.drawLine(60,550,380,550);

        g.setColor(Color.white);
        g.fillRect(61,21,340,298);
        g.fillRect(61,351,340,198);

        g.setColor(Color.black);


       // Draw restitution 
          int sen=0;
         double dx=1.0;
         double x=40.0;
         for(int i=0; i< 300; i++){
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
         int y1=(int)(320-f1);
         int y2=(int)(320-f2);
         g.drawLine(i+60+30,y1,i+61+30,y2);
         x+=dx;
         }

        g.setColor(Color.blue);
//        if(p !=null){
         int per = p.x-20+320-p.y;
        g.drawString("T=",210,38);
        g.drawString(String.valueOf((per-42))+"ms",225,38);
   //     g.drawString("ms"
         int perd = 2*(p.x-20+320-p.y);
         int yp1=320 - per;
         int xp2= per+20;
         g.drawLine(20,yp1,xp2,320);
          double ppi=pi.x-50;
//         double fppi=0.06*(1-3000*Math.exp(-ppi/50)-1500*Math.exp(-ppi/10))+300;
         b0=255.377;
         b1=0.41592;
         b2=149.173;
         b3=298.2;
         b4=11.1681;
        if(ppi >105.7) {
       a0= 134.1; 
       a1= 1.041 ;
       a2= -0.004848; 
       a3= 1.242e-05 ;
       a4= -1.292e-08; } 
        if(ppi<= 105.7 && ppi > 90){
        a0= 565.7; 
        a1= -21.34; 
        a2= 0.2987; 
        a3= -0.001223; 
        a4=0          ;         }
        
        if(ppi <= 90  && ppi > 65) {
        a0= 837.8 ;
        a1= -34.16 ;
        a2= 0.4834 ;
        a3= -0.002065; 
        a4=0          ;         }
        
        if(ppi <= 65 ){
        a0= -647.8 ;
        a1= 34.1 ;
        a2= -0.567; 
        a3= 0.003349;
        a4=0      ;  }

         if(ppi<88){
         fppi=a0+a1*ppi+a2*ppi*ppi+a3*ppi*ppi*ppi+a4*ppi*ppi*ppi*ppi;
                   }
        if(x>=88){
         fppi=b0*(1-b1*Math.exp(-ppi/b2)-b3*Math.exp(-ppi/b4));
                 }

          int yppi=(int)(320-fppi);
          int xppf=(int)(per-fppi);
          g.setColor(Color.darkGray);
          g.setColor(Color.red);

          int perb = pi.x-20+320-pi.y;
          int yp1b = 320 - perb;
          int xp2b = perb+20;
          g.drawLine(20,yp1b,xp2b,320);
          g.setColor(Color.red);

          g.drawLine(pi.x,pi.y,pi.x,yppi);
           if(pi.x < 90)
                 sen+=1;
          int yppf=yppi;
          if(yppf > 320)yppf=320;
          int xppi=(int)(ppi);
          g.drawLine(pi.x,yppi,xppf+20,yppf);
           if(pi.x < 90) sen+=1;
          for(int i=0; i< 900; i++){
            ppi=xppf-30;
            xppi=xppf;
            yppi=yppf;
//           fppi=0.06*(1-3000*Math.exp(-ppi/50)-1500*Math.exp(-ppi/10))+300;
         b0=255.377;
         b1=0.41592;
         b2=149.173;
         b3=298.2;
         b4=11.1681;

        if(ppi >105.7) {
       a0= 134.1; 
       a1= 1.041 ;
       a2= -0.004848; 
       a3= 1.242e-05 ;
       a4= -1.292e-08; } 
        if(ppi<= 105.7 && ppi > 90){
        a0= 565.7; 
        a1= -21.34; 
        a2= 0.2987; 
        a3= -0.001223; 
        a4=0          ;         }
        
        if(ppi <= 90  && ppi > 65) {
        a0= 837.8 ;
        a1= -34.16 ;
        a2= 0.4834 ;
        a3= -0.002065; 
        a4=0          ;         }
        
        if(ppi <= 65 ){
        a0= -647.8 ;
        a1= 34.1 ;
        a2= -0.567; 
        a3= 0.003349;
        a4=0      ;  }

//         fppi=a0+a1*ppi+a2*ppi*ppi+a3*ppi*ppi*ppi+a4*ppi*ppi*ppi*ppi;
         if(ppi<88){
         fppi=a0+a1*ppi+a2*ppi*ppi+a3*ppi*ppi*ppi+a4*ppi*ppi*ppi*ppi;
                   }
        if(ppi>=88){
         fppi=b0*(1-b1*Math.exp(-ppi/b2)-b3*Math.exp(-ppi/b4));
                 }

            if(i==888)apd1=fppi;
            if(i==889)apd2=fppi;


            yppf=(int)(320-fppi);
          if(yppf > 320)yppf=320;

            xppf=(int)(per-fppi);
            if(sen < 1)
            g.drawLine(xppi+20,yppi,xppi+20,yppf);
            if(xppi+20 < 90) sen+=1;
            xppf=(int)(per-fppi);
            if(sen < 1)
            g.drawLine(xppi+20,yppf,xppf+20,yppf);            
            if(xppf+20 < 90) sen+=1;
         
             }
           
            iapd1=(int)(apd1/1.5);
            iapd2=(int)(apd2/1.5);
            apdcounter=apdcounter+1;
            apds1[per]=iapd1;
            apds2[per]=iapd2;


            g.setColor(Color.black);
             for(int j=0;j<9000; j++){
             if(apds1[j] > 0){
              g.drawOval(j-150,555-apds1[j],2,2);
                              }
             if(apds2[j] > 0){
              g.drawOval(j-150,555-apds2[j],2,2);
                              }

                                             }


          
 
          if(sen<1){
            iapd1=(int)(apd1/1.5);
            iapd2=(int)(apd2/1.5);
              g.setColor(Color.red);
              g.fillOval(per-150,554-iapd1,6,7);
              g.fillOval(per-150,554-iapd2,6,7);
                     }
 

          }
          }





