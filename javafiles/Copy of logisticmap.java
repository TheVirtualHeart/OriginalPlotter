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
//        import java.lang.*;

        /*
	 <applet code="logisticmap" width=700 height=480>
	 </applet>
	*/

        public class logisticmap extends Applet
//        implements MouseListener, ActionListener, ItemListener, Runnable{
       implements MouseListener, MouseMotionListener, ActionListener,
                  AdjustmentListener {
        Point p;
        Image Buffer;
        Graphics gBuffer;
        Point pi;
        Button butt,butf,buts,butr,butrs;
        Scrollbar horiz,slider1,horiz2;

//        LayoutManager Layout;

        private Panel buttonPanel;
        private Label prompt1, prompt2, prompt3, prompt4;
        private TextField input1;
        double a0,a1,a2,a3,a4,b0,b1,b2,b3,b4,f1,f2,fppi;
        double apd1,apd2,bcl;
        double av_min, alpha, tau, theta, av, avt, aa;
        double valuee3;
        int k,nk,m,iav,ss, valuee, valuee2;
	double rvalue;
        int jm,jmm;
        int iapd1,iapd2,ibcl;
        int q1a,q1b,q2a,q2b,q3a,q3b,q1d,q2d;
        double  yy[]= new double[19];
        double cr, xk,xinflux,sr;
        double eff,srn,an;
        double qa=2;
        double qb=3;
        int icr[]=new int[500];
//        int apds2[]= new int[9000];
        int nh=0;
        int apdcounter=0;
        int firsttime=1;
        int nfirsttime=1;
        Image ca1,ca2;

         public void init() {
//          p.x=10;
//          p.y=10;
          Buffer=createImage(700,480);
          gBuffer=Buffer.getGraphics();
         ca1=getImage(getCodeBase(),"./ca1.gif");
         ca2=getImage(getCodeBase(),"./ca2.gif");


          xk=50;
          an=9;
          xinflux=30;
          ss=600;
          valuee=ss;
          valuee2=6;
          addMouseListener(this);
          addMouseMotionListener(this);
//          for(int i=0; i<9000; i++){apds1[i]=0;}
//          for(int i=0; i<9000; i++){apds2[i]=0;}
//	butt = new Button ("Start");
//        butt.addActionListener( this );
//        add (butt);

//	slider1 = new Scrollbar(Scrollbar.HORIZONTAL, 800, 60, 20, 1160);
//        add(slider1);
//        slider1.reshape(405,14,165,15);

//        Layout= new BorderLayout();
        buttonPanel = new Panel();
        
//        input1= new TextField(3);
//        input1.addActionListener( this);
//        input1.setText("600");
        prompt1= new Label("The slider changes the efflux exponent n");
        prompt2= new Label("  ");
        prompt3= new Label("The slider changes the influx value");
        prompt4= new Label("  ");

        horiz2=new Scrollbar(Scrollbar.HORIZONTAL, 30, 5, 1, 35);
        horiz=new Scrollbar(Scrollbar.HORIZONTAL, 40, 1, 10, 41);

        buttonPanel.setLayout( new GridLayout(2,3));
        buttonPanel.add(prompt1); 
          buttonPanel.add(horiz);

        buttonPanel.add(prompt2);
        buttonPanel.add(prompt3);
           buttonPanel.add(horiz2);

        buttonPanel.add(prompt4);

//        buttonPanel.add(prompt1);
        setLayout (new BorderLayout() );
            add(buttonPanel, BorderLayout.NORTH);
          horiz.addAdjustmentListener(this);
          horiz2.addAdjustmentListener(this);


//         add(buttonPannel, BorderLayout.North);
//        horiz.reshape(405,14,165,15);


         }

         public void mouseClicked(MouseEvent me){
//          repaint();
         }
         public void mouseEntered(MouseEvent me){
         }
         public void mouseExited(MouseEvent me){
         }
         public void mousePressed(MouseEvent me){
         p=me.getPoint();
         firsttime=2;
         nfirsttime=2;
         

         pi=me.getPoint();
         repaint();
         } 
         public void mouseReleased(MouseEvent me){
//         p=null;
//         repaint();
         }
         public void mouseDragged(MouseEvent me){
         firsttime=2;
         p=me.getPoint();
  //       repaint();
         }
         public void mouseMoved(MouseEvent me){
         }
         
        public void actionPerformed( ActionEvent me){

          valuee=horiz2.getValue();
          valuee2=horiz.getValue();
	  rvalue=(double)valuee2*.1;
          
//          input1.setText(String.valueOf(valuee));
          repaint();
                                                    }

        public void adjustmentValueChanged( AdjustmentEvent me){
         int p=100;
          valuee=horiz2.getValue();
          valuee2=horiz.getValue();
	  rvalue=(double)valuee2*.1;


//           valuee=500;
//          input1.setText(String.valueOf(valuee));
          repaint();
                                                    }

                          
              

	 public void paint(Graphics g) {
        
        av_min=114;
        alpha=383;
        tau=252;
        theta=113;
        av=200;
        avt=av;
        k=1;
        nk=1;
        m=1;
        
        valuee=horiz2.getValue();
        valuee2=horiz.getValue();
        rvalue=(double)valuee2*.1;
	
   
       gBuffer.setColor(Color.red);
//          input1.setText(String.valueOf(valuee));

          

       // Draw axes
        gBuffer.setColor(Color.white);
//        Dimension d = getSize();
//        int width = d.width;
//        int height = d.height;
        gBuffer.fillRect(0,0,699,479);
//        g.setColor(Color.yellow);
//        g.drawOval(110,320-247,2,2);
        gBuffer.setColor(Color.orange);
//        g.drawOval(112,320-250,2,2);
        gBuffer.setColor(Color.black);
//        g.drawLine(60,10,60,375);
//        g.drawLine(60,375,410,375);
//        g.drawLine(60,510,410,510);
//        g.drawLine(60,510,60,410);
 
 

//        g.drawString("200",240,335);
//        g.drawString("300",340,335);

        gBuffer.drawString("1.0--",30,75-2);
        gBuffer.drawString("0.75-",30,112-2);
        gBuffer.drawString("0.50-",30,150-2);
        gBuffer.drawString("0.25-",30,187-2);
        gBuffer.drawString("0.0--",30,225-2);

        gBuffer.drawLine(60,70,60,220);
        gBuffer.drawLine(60,220,370,220);
	//x axis of top plot
        gBuffer.drawString("|",60,230);
        gBuffer.drawString("0",58,243);

        gBuffer.drawString("|",120,230);
        gBuffer.drawString("0.2",114,243);
 
        gBuffer.drawString("|",180,230);
        gBuffer.drawString("0.4",174,243);

        gBuffer.drawString("|",240,230);
        gBuffer.drawString("0.6",234,243);

        gBuffer.drawString("|",300,230);
        gBuffer.drawString("0.8",294,243);

        gBuffer.drawString("|",359,230);
        gBuffer.drawString("1.0",352,243);



        //x axis of mid plot
        gBuffer.drawString("|",60,397+20);
        gBuffer.drawString("0",57,410+20);

        gBuffer.drawString("|",120,397+20);
        gBuffer.drawString("20",113,410+20);
 
        gBuffer.drawString("|",180,397+20);
        gBuffer.drawString("40",173,410+20);

        gBuffer.drawString("|",240,397+20);
        gBuffer.drawString("60",233,410+20);

        gBuffer.drawString("|",300,397+20);
        gBuffer.drawString("80",293,410+20);

        gBuffer.drawString("|",360,397+20);
        gBuffer.drawString("100",350,410+20);

	
        gBuffer.drawString("1.0--",30,245-2+20);
        gBuffer.drawString("0.75-",30,282-2+20);
        gBuffer.drawString("0.50-",30,320-2+20);
        gBuffer.drawString("0.25-",30,357-2+20);
        gBuffer.drawString("0.0--",30,395-2+20);

        gBuffer.drawLine(60,260,60,410);
        gBuffer.drawLine(60,410,370,410);

        gBuffer.setColor(Color.white);
        gBuffer.fillRect(61,70,311,150);
        gBuffer.fillRect(230,50,110,30);
        gBuffer.fillRect(61,260,311,150);


        gBuffer.setColor(Color.blue);
        gBuffer.drawString("X_n+1",4,130);
        gBuffer.drawString("X_n",205,239);
        
        gBuffer.drawString("Efflux",2,300);
        gBuffer.drawString("SR",204,416);
        gBuffer.setColor(Color.red);

       if(nfirsttime==1){
      
       gBuffer.drawRect(120,63,270,40);
       gBuffer.drawString("Click anywhere in the graph to start", 130,80);
       gBuffer.drawString("Also use the slides to change the parameter", 130,95);


                       }
                   else{
       

                        }


        gBuffer.drawImage(ca1,395,110,this);
        gBuffer.drawImage(ca2,390,300,this);



//        gBuffer.drawString("Period",210,588);

//       gBuffer.drawString("SP="+String.valueOf((valuee))+"ms",290,310);
//           g.setColor(Color.blue);

       gBuffer.drawString(String.valueOf((valuee)),590,129);

//       valuee3=Math.pow(qa,qb);
//       g.drawString("N="+String.valueOf((valuee2))+"ms",290,280);
       gBuffer.drawString(String.valueOf((valuee2)),511+26,316);
       gBuffer.drawString(String.valueOf((valuee2)),552+26,317);
       gBuffer.drawString(String.valueOf((valuee2)),608+25,317);


       gBuffer.setColor(Color.red);
//       if(firsttime==1){
//
//        for(int i=60; i< 411; i++){
//          icr[i]=0;
//                                  }
//
//
//         firsttime=2;
//                       }
//                   else{
//       g.drawString("Click anywhere in the graph", 230,335);
//       g.drawString("to change the initial conditions.", 230,350);
//       g.setColor(Color.black);
//       g.drawString("Or use the slider to change SP",230,365);
//
//                       }

                     
            an=(double)(valuee2);
            xinflux=(double)(valuee);
         for(int i=0; i< 100; i++){
//           sr=(double)(i);

//           g.drawLine((int)(i*3)+60,220-(int)(i*1.5),1+(int)(i*3)+60,1+220-(int)(i*1.5)); 
//           g.drawLine((int)(i*3)+60,390-(int)(i*1.5),1+(int)(i*3)+60,1+390-(int)(i*1.5)); 
           
           sr=(double)(i)*.01;
           eff=sr*Math.pow(sr,an)/(Math.pow(xk,an)+Math.pow(sr,an));
 //          srn=sr-eff+xinflux;
           srn=sr*rvalue*(1.-sr); 

 
            q1a=60+(int)(sr*150*2);
//            q2a=(int)(eff);
              q2a=220-(int)(sr*150);
              q3a=220-(int)(srn*150);

           sr=(double)(i)*.01+.01;
//           eff=sr*Math.pow(sr,an)/(Math.pow(xk,an)+Math.pow(sr,an));
           srn=sr*rvalue*(1.-sr); 

            q1b=60+(int)(sr*150*2);
//            q2b=(int)(eff);
              q2b=220-(int)(sr*150);

            q3b=220-(int)(srn*150);

         gBuffer.setColor(Color.black);

  //         gBuffer.drawLine(q1a*10+60,388-(int)(q2a*5.5),q1b*10+60,388-(int)(q2b*5.5));
           gBuffer.drawLine(q1a,q3a,q1b,q3b);
           gBuffer.drawLine(q1a,q2a,q1b,q2b);
                                  }
 
         if(firsttime==1){
       //     firsttime=2;
                         }
           else{

           sr=((double)(p.x)-60)/3.;
           q2d=(int)(( 220-(double)(p.y)   )/1.5);
           q1d=(int)(sr);
         gBuffer.setColor(Color.blue);
         gBuffer.fillOval(q1d*3+60-4,220-(int)(q2d*1.5)-4,8,8);

	   
         gBuffer.setColor(Color.red);
	 sr=((double)(p.x)-60)/300.;

           srn=sr*rvalue*(1.-sr);
           q1b=220-(int)(srn*150);

           gBuffer.drawLine(q1d*3+60,220-(int)(q2d*1.5),q1d*3+60,q1b);

      
//         gBuffer.setColor(Color.green);
//         sr=((double)(p.x)-60)/300.;
//           q1a=60+(int)(sr*300);
//           srn=sr*rvalue*(1.-sr); 
//           q1b=220-(int)(srn*150);
//         gBuffer.fillOval(q1a-4,q1b-4,8,8);
	      

         for(int i=0; i< 50; i++){

           srn=sr*rvalue*(1.-sr); 

           q1a=60+(int)(sr*300);
           q2a=(int)(eff);
           q1b=220-(int)(srn*150);
            sr=srn;
           
         gBuffer.setColor(Color.red);

           q2a=60+(int)(sr*300);
	   q3b=220-(int)(150*sr*rvalue*(1.-sr));

           gBuffer.drawLine(q1a,q1b,q2a,q1b);

           gBuffer.drawLine(q2a,q1b,q2a,q3b);
           





                                  }

 
 
                                                 } //end of if 
              g.drawImage (Buffer,0,0, this);
 

          

          }


}


