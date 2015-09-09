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
	 <applet code="restitution" width=450 height=620>
	 </applet>
	*/

        public class restitution extends Applet
        implements MouseListener, MouseMotionListener, ItemListener {
        Image buffer;
	Graphics gbuff;        
        Graphics g1;
        Point p;
        Point pi;
        double a0,a1,a2,a3,a4,b0,b1,b2,b3,b4,f1,f2,fppi;
        double apd1,apd2,bcl;
        int iapd1,iapd2,ibcl,jj;
        int apds1[]= new int[9000];
        int apds2[]= new int[9000];
        int nh=0;
        int nres=0;
        Color background;
		String opt0="Beeler-Reuter APD restitution";
		String opt1="Exponential APD restitution";
		String opt2="biphasic APD restitution";
                String opt3="Sigmoidal APD restitution"; 
                String opt4="border collision APD restitution";    


        int apdcounter=0;

         public void init() {
          addMouseListener(this);
          addMouseMotionListener(this);
         Choice options= new Choice();     
		options.addItem("Beeler-Reuter APD restitution");
		options.addItem("Exponential APD restitution");
		options.addItem("biphasic APD restitution");
                options.addItem("Sigmoidal APD restitution");    
                options.addItem("border collision APD restitution");    
		options.addItemListener(this);
		add(options);

//		setLayout(new BorderLayout() );
//		add(checkPanel, BorderLayout.NORTH);


          for(int i=0; i<9000; i++){apds1[i]=0;}
          for(int i=0; i<9000; i++){apds2[i]=0;}

	//	setBackground( Color.red );
               setSize(600,600);
		background= new Color(192,192,192);
		setBackground(background);

		buffer = this.createImage(570,570);
		gbuff = buffer.getGraphics();

	   repaint();

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
         repaint();
         }
         public void mouseDragged(MouseEvent me){
         p=me.getPoint();
         repaint();
         }
         public void mouseMoved(MouseEvent me){
         }
       	public void itemStateChanged( ItemEvent ae){
          for(int i=0; i<9000; i++){apds1[i]=0;}
          for(int i=0; i<9000; i++){apds2[i]=0;}

            
	if(ae.getItem().toString()== opt0){
			nres=0;
		                          }
	if(ae.getItem().toString()== opt1){
			nres=1;
		                          }
	if(ae.getItem().toString()== opt2){
			nres=2;
		                          }
	if(ae.getItem().toString()== opt3){
			nres=3;
		                          }
	if(ae.getItem().toString()== opt4){
			nres=4;
		                          }



	  repaint();
                                     }

         

	 public void paint(Graphics g) {
   
        gbuff.setColor(Color.white);
        gbuff.fillRect(61,21,340,298);
        gbuff.fillRect(61,351,550,198);

        gbuff.setColor(Color.black);


       // Draw restitutions 
          int sen=0;
         double dx=1.0;
         double x=40.0;

       if(nres==0){                // option 0  BR restitution

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
         gbuff.drawLine(i+60+30,y1,i+61+30,y2);
         x+=dx;
         }

                                    } //end of nres=0

         if(nres==4){                // option 4  biphasic restitution
         for(int i=0; i< 300; i++){
          
        double x2=x+dx;
        if(x > 104.11545){
         f1=0.1985829*x+185.4276;
         f2=0.1985829*x2+185.4276;
      
                  }
         if(x<=104.11545){
         f1=2.78611*x-83.97293;
         f2=2.78611*x2-83.97293;
                  }

         int y1=(int)(320-f1);
         int y2=(int)(320-f2);
         g.drawLine(i+60+30,y1,i+61+30,y2);
         x+=dx;
         }
                                    } //end of nres=4        

        gbuff.setColor(Color.blue);
         int per = p.x-20+320-p.y;
        gbuff.drawString("T=",210,38);
        gbuff.drawString(String.valueOf((per-42))+"ms",225,38);
        gbuff.drawString("T=",320,390);
        gbuff.drawString(String.valueOf((per-42))+"ms",335,390);

         int perd = 2*(p.x-20+320-p.y);
         int yp1=320 - per;
         int xp2= per+20;
         gbuff.drawLine(20,yp1,xp2,320);
          double ppi=pi.x-50;

       if(nres==0){                // option 0  BR restitution

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
          gbuff.setColor(Color.darkGray);
          gbuff.setColor(Color.red);

          int perb = pi.x-20+320-pi.y;
          int yp1b = 320 - perb;
          int xp2b = perb+20;
          gbuff.drawLine(20,yp1b,xp2b,320);
          gbuff.setColor(Color.red);

          gbuff.drawLine(pi.x,pi.y,pi.x,yppi);
           if(pi.x < 90)
                 sen+=1;
          int yppf=yppi;
          if(yppf > 320)yppf=320;
          int xppi=(int)(ppi);
          gbuff.drawLine(pi.x,yppi,xppf+20,yppf);
           if(pi.x < 90) sen+=1;
          for(int i=0; i< 900; i++){
            ppi=xppf-30;
            xppi=xppf;
            yppi=yppf;
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
        if(ppi>=88){
         fppi=b0*(1-b1*Math.exp(-ppi/b2)-b3*Math.exp(-ppi/b4));
                 }

            if(i==888)apd1=fppi;
            if(i==889)apd2=fppi;


            yppf=(int)(320-fppi);
          if(yppf > 320)yppf=320;

            xppf=(int)(per-fppi);
            if(sen < 1)
            gbuff.drawLine(xppi+20,yppi,xppi+20,yppf);
            if(xppi+20 < 90) sen+=1;
            xppf=(int)(per-fppi);
            if(sen < 1)
            gbuff.drawLine(xppi+20,yppf,xppf+20,yppf);            
            if(xppf+20 < 90) sen+=1;
         
             }
           
            iapd1=(int)(apd1/1.5);
            iapd2=(int)(apd2/1.5);
            apdcounter=apdcounter+1;
            apds1[per]=iapd1;
            apds2[per]=iapd2;


            gbuff.setColor(Color.black);
             for(int j=0;j<9000; j++){
             if(apds1[j] > 0){
               jj=(int)(j*1.2);
              gbuff.drawOval(jj-150,555-apds1[j],2,2);
                              }
             if(apds2[j] > 0){
                jj=(int)(j*1.2);
              gbuff.drawOval(jj-150,555-apds2[j],2,2);
                              }

                                             }
              jj=(int)(per*1.2);
              gbuff.setColor(Color.blue);
            iapd1=(int)(apd1/1.5);
            iapd2=(int)(apd2/1.5);
              gbuff.drawLine(jj-150,554,jj-150,554-iapd1);
              gbuff.drawLine(jj-150,554,jj-150,554-iapd2);


 
          if(sen<1){
            iapd1=(int)(apd1/1.5);
            iapd2=(int)(apd2/1.5);
              gbuff.setColor(Color.red);
               jj=(int)(per*1.2);
              gbuff.fillOval(jj-153,554-iapd1,6,7);
              gbuff.fillOval(jj-153,554-iapd2,6,7);
                     }
              else{ 
              gbuff.setColor(Color.red);

        gbuff.drawString("Block 2:1",120,430);
                  }

                                    } //end of nres=0        


        gbuff.setColor(background);
        gbuff.fillRect(400,0,200,340);
        gbuff.fillRect(0,0,400,21);
        gbuff.fillRect(0,0,60,600);
        gbuff.fillRect(0,550,600,60);


       // Draw axes
        gbuff.setColor(Color.blue);
        gbuff.setColor(Color.orange);
        gbuff.setColor(Color.black);
        gbuff.drawLine(60,20,60,320);
        gbuff.drawLine(60,320,380,320);

        gbuff.drawString("|",160,322);
        gbuff.drawString("100",149,335);

        gbuff.drawString("|",260,322);
        gbuff.drawString("200",249,335);
        gbuff.drawString("|",310,322);
        gbuff.drawString("250",299,335);

        gbuff.drawString("|",360,322);
        gbuff.drawString("300",349,335);

        gbuff.drawString("|",110,322);
        gbuff.drawString("50",104,335);

        gbuff.drawString("100-",30,220);
        gbuff.drawString("150-",30,170);
        gbuff.drawString("200-",30,120);
        gbuff.drawString("250-",30,70);
        gbuff.drawString("300-",30,20);
        gbuff.drawString("50--",30,270);

        gbuff.drawString("50--",30,529);

        gbuff.drawString("100-",30,491);
        gbuff.drawString("150-",30,454);
        gbuff.drawString("200-",30,416);
        gbuff.drawString("250-",30,379);

        gbuff.drawString("|",200-120,552);
        gbuff.drawString("150",190-120,568);

        gbuff.drawString("|",200-60,552);
        gbuff.drawString("200",190-60,568);

        gbuff.drawString("|",200,552);
        gbuff.drawString("250",190,568);

        gbuff.drawString("|",260,552);
        gbuff.drawString("300",250,568);

        gbuff.drawString("|",320,552);
        gbuff.drawString("350",310,568);

       gbuff.drawString("|",380,552);
        gbuff.drawString("400",370,568);

       gbuff.drawString("|",440,552);
        gbuff.drawString("450",430,568);


       gbuff.drawString("|",500,552);
        gbuff.drawString("500",490,568);

        gbuff.drawString("APD",2,185);
        gbuff.drawString("DI",210,338);
        gbuff.drawString("Period",210,588);



        gbuff.drawLine(60,350,60,550);
        gbuff.drawLine(60,550,550,550);


		g.drawImage(buffer, 0, 20, this);	
                 
		repaint();

          }


     public void update(Graphics g) 
     { 
          paint(g); 
     } 



          }





