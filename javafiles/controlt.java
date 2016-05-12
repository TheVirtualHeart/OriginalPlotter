	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="controlt" width=450 height=630>
         </applet>
*/

	public class controlt extends Applet
	implements MouseListener, ActionListener, ItemListener, Runnable{
        Point p;
         private Panel buttonPanel, checkPanel, stimPanel;

//        Label label;
        Button butt,butf,buts,butr,butrs,butla,butsm,butstop;
        Image Buffer;
        Graphics gBuffer;
        Scrollbar  slider1, horiz;
        int nx=55;
        int nstop=1;
        int nspeed=20;
        double pp;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[210];
        double v[] = new double[210];
        double w[] = new double[210];
        double s[] = new double[210];


         double uo = 0.0   ;
         double uu = 1.56;
         double um = 0.3;
         double up = 0.13;
         double ur = 0.006;
         double tvp = 1.4506;
         double tfi = 0.104;

          double xkwm  =   158.14086;
	 double twp   =   67.880750;
	 double tso1=   200.00000;
	 double tso2= 0.54494910  ;
	 double xkso  =    2.7969265;
	 double uso   =    .34233130;
	 double ts1  =    1.0000000;
	 double ts2   =    1.7721446;
	 double xks   =    3.2239589;
	 double us  = .86854118  ;
	 double tsi   =    1.3459624;
	 double twinf   =    9.397222;
	 double winfast =    2.8725006;
	 double to1 =    600.00000;
	 double to2   =    20.000000;

         double twm1=170;
         double twm2=40;
	 double uwm=0.1;

         double uq=0.2;
	 double tvm1=10;
	 double tvm2=10;
         double xm,xp,xq,xr;
         double tso,to,twm,tvm;
         double ds,dv,dw,ts,vinf,winf;

        

        double h[] = new double[210];
        double f[] = new double[210];
        double ut[]= new double[210];
        double nu[][]= new double[210][2100];
        double lapd1[] = new double[300];
        double lapd2[] = new double[300];

        int izoom=20;
        double tpa[][] = new double[300][50];
        int ntpa[]= new int[300];
        int napd[] = new int[300];
        int ntime=0;
        int firstime=0;
        int deltap=0;
        int deltapp=0;        
         double deltat=0;
         double lapdn=0;
         double lapdold=0;
         int iperiod;
 
        String controla="gamma .0"; 
        String controla1="gamma .1";
        String controla2="gamma .2";
        String controla3="gamma .3";
        String controla4="gamma .4";
//        String controlb="gamma .4";
//        String controlc="gamma .4";
//        String controld="gamma .4";
//        String controle="gamma .4";
        String controlf= "gamma .5"; 
        String controlf1="gamma .6";
        String controlf2="gamma .7";
        String controlf3="gamma 1";

 
        int mmm=2;
        int nnow=1;
        int nps=0;
        int npc=2000;
        int np=0;
        int nperiod=874;
        int nperiod1=0;
        int nperiod2=0;
//        int npp=890;
         int npp=920;
//       int npp=820;
//        int npp=1000;
        int nppc=0;
        int nfront=0;
        double a=0.1;
        double gama=0;
        double b=0.5;
        double eps=0.01;
        double delta=0;
        double dt=0.2;
        double dx2=3.3;
        double xpp,xqq,xfi,xso,xsi;

	public void init() {
        for(int i=0; i<200; i++){
         u[i]=0;
         v[i]=1;
         w[i]=.1;
         s[i]=0;
         ut[i]=0;

         ntpa[i]=0;
         napd[i]=0;
                                }
        for(int i=0; i<5; i++){
         u[i]=0.8;
                                 }
//         for(int i=30; i<50; i++){
//          u[i]=0.0;
//          h[i+20]=0.0;
//                                 }

//         for(int i=30; i<50; i++){
//          u[i]=0.0;
//          h[i+20]=0.0;
//                               }
//           for(int i=0; i<200; i++){
//            for (int j=0; j<1100; j++){
//              nu[i][j]= u[i];
//                                     }
//                                    }     

	 Thread thread = new Thread(this);
	 thread.start();
	 addMouseListener(this);
        Buffer=createImage(380,620);
        gBuffer=Buffer.getGraphics();



//	butt = new Button ("Start  ");
//        butt.addActionListener( this );
//        add (butt);
        buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1,4));


	butf = new Button ("Faster");
        butf.addActionListener( this );
        buttonPanel.add (butf);
	buts = new Button ("Slower");
        buts.addActionListener( this );
        buttonPanel.add (buts);
         Choice options = new Choice();
        options.addItem("gamma .0");
        options.addItem("gamma .1");
        options.addItem("gamma .2");
        options.addItem("gamma .3");
        options.addItem("gamma .4");
//        options.addItem("25 control electrodes, gamma .4");
//        options.addItem("20 control electrodes, gamma .4");
//        options.addItem("10 control electrodes, gamma .4");
//        options.addItem(" 6 control electrodes, gamma .4");
        options.addItem("gamma .5"); 
        options.addItem("gamma .6");
        options.addItem("gamma .7");
        options.addItem("gamma 1");

        options.addItemListener(this);
        buttonPanel.add(options);

        butstop= new Button ("  Stop  ");
        butstop.addActionListener( this );
        buttonPanel.add (butstop);

//	butt = new Button ("Start  ");
//        butt.addActionListener( this );
//        add (butt);

	slider1 = new Scrollbar(Scrollbar.HORIZONTAL, 200, 10, 40, 200);
//        add(slider1);
//        slider1.reshape(140,130,365,15);

//       horiz=new Scrollbar(Scrollbar.HORIZONTAL,1,25,1,280);
//        add(horiz);
//        horiz.reshape(120,302,350,15);

           setLayout(new BorderLayout() );
           add(slider1, BorderLayout.SOUTH);
            add(buttonPanel, BorderLayout.NORTH);

//           add(checkPanel, BorderLayout.EAST);




	}
	public void mouseClicked(MouseEvent me){
	}
	public void mouseEntered(MouseEvent me){
	}
	public void mouseExited(MouseEvent me){
	}
        public void mousePressed(MouseEvent me){
         down=true;
	}
	public void mouseReleased(MouseEvent me){
          down=false;
	}
        public void itemStateChanged(ItemEvent ie){
//          Choice c= (Choice)ie.getItemSelectable();
//          label.setText(c.getSelectedItem());
          if(ie.getItem().toString()== controla){ mmm=4;
                                                  gama=0;}
          if(ie.getItem().toString()== controla1){ mmm=4;
                                                  gama=0.1;}
          if(ie.getItem().toString()== controla2){ mmm=4;
                                                  gama=0.2;}
          if(ie.getItem().toString()== controla3){ mmm=4;
                                                  gama=0.3;}
          if(ie.getItem().toString()== controla4){ mmm=4;
                                                  gama=0.4;}
//          if(ie.getItem().toString()== controlb){ mmm=4;
//                                                  gama=0.4;}
//          if(ie.getItem().toString()== controlc){ mmm=5;
//                                                  gama=0.4;}
//          if(ie.getItem().toString()== controld){ mmm=10;
//                                                  gama=0.4;}
//          if(ie.getItem().toString()== controle){ mmm=17;
//                                                  gama=0.4;}
          if(ie.getItem().toString()== controlf){ mmm=25;
                                                  gama=0.5;}
          if(ie.getItem().toString()== controlf1){ mmm=25;
                                                  gama=0.6;}
          if(ie.getItem().toString()== controlf2){ mmm=25;
                                                  gama=0.7;}
          if(ie.getItem().toString()== controlf3){ mmm=25;
                                                  gama=1.0;}



                                                  }
        public void actionPerformed( ActionEvent ae){
//                      if(ae.getSource()==butt){
//                         if(firsttime){
//            butt.setLabel("Restart");
//            firsttime=false;
//                                      }
//        for(int i=0; i<200; i++){
//         u[i]=-85;
//         h[i]=1;
//         f[i]=1;
//         ut[i]=0;
//                                }
             
//                     for(int i=30; i<50; i++){
//          u[i]=0.0;
//          h[i+20]=0.0;
//                                              }
         nps=0;
         npc=2000;
         np=0;
 
//                                                }

           if(ae.getSource()==butstop){
                      if(butstop.getLabel().equals("Continue")){
                       butstop.setLabel(" Stop ");
                       nstop=1;
                                                        }
                       else{
                       butstop.setLabel("Continue");
                       nstop=10;
                            } 
                                }
//              firsttime=false;
//                                              }
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
	public void update(Graphics g){ paint(g); }  

        public void paint(Graphics g){

          gBuffer.setColor(new Color(192,192,192));
          gBuffer.fillRect(0,0,550,620);

        Dimension d = getSize();
        int width = d.width;
        int height = d.height;

         gBuffer.setColor(Color.black);
         gBuffer.fillRect(20,340,nx*2,300);


        gBuffer.setColor(Color.white);
        gBuffer.fillRect(20,68,2*nx,237);
        gBuffer.setColor(Color.blue);
        gBuffer.drawRect(20,68,2*nx,237);
        gBuffer.setColor(Color.black);



        gBuffer.drawString("V",3,155);
        gBuffer.drawString("(mV)",0,165);
        gBuffer.drawString("L= ("+String.valueOf(nx)+") points",20,320);
        gBuffer.drawString("T= T_o *gama*(APD_n+1-APD_n)",130,320);
        gBuffer.drawString("T= "+String.valueOf(iperiod)+"ms",130,332);


         for(int i=0; i< nx-1; i++){
         gBuffer.setColor(Color.red);
//             if(i%mmm==1 && nps > 2 && i > 4 && i < nx-2 && gama > 0.){
//          gBuffer.drawLine(2*i+20,69,2*i+20,78);
//                                                                        }
         if(gama > 0 ) gBuffer.drawLine(2*27+20,69,2*27+20,78);

                      
         gBuffer.setColor(Color.black);

         gBuffer.drawString("Control",65,52);
          
        if(gama < 0.1)gBuffer.drawString("OFF",115,52);
         gBuffer.setColor(Color.red);
        if(gama > 0)gBuffer.drawString("ON",115,52);


        if(nps > 2){

//         gBuffer.setColor(Color.blue);
//         int nyy1=(int)(115-nu[i][npc]*2.0);
//         int nyy2=(int)(115-nu[i+1][npc]*2.0);
//         gBuffer.drawLine(2*i+20,nyy1,2*i+20,nyy2);
                      }
          gBuffer.setColor(Color.black);
         int yy1=(int)(275-u[i]*120.0);
         int yy2=(int)(275-u[i+1]*120.0);
         gBuffer.drawLine(2*i+20,yy1,2*i+21,yy2);

        if(i>5 && lapd1[i+1] > 10){
        gBuffer.setColor(Color.orange);
         int yyy1=(int)(755-lapd1[i]*2.5);
         int yyy2=(int)(755-lapd1[i+1]*2.5);
         gBuffer.drawLine(2*i+20,yyy1,2*i+21,yyy2);
                                }

        if(i>5 && lapd2[i+1] > 10){
        gBuffer.setColor(Color.green);
         int yyx1=(int)(755-lapd2[i]*2.5);
         int yyx2=(int)(755-lapd2[i+1]*2.5);
         gBuffer.drawLine(2*i+20,yyx1,2*i+21,yyx2);

             }

        gBuffer.setColor(Color.black);



 

//         g.drawString(String.valueOf(nperiod),200,200);
//         g.drawString(String.valueOf(np),200,220);
//         g.drawString(String.valueOf(npc),200,240);
//         g.drawString(String.valueOf(nperiod1),200,260);
//         g.drawString(String.valueOf(mmm),200,280);
         
                                    }


         if(nstop < 5){

       // Draw restitution 
          int sen=0;
         double x=0.0;
           for(int k=1; k<30; k++){

              ntime=ntime+1;
              nppc=nppc+1;
               deltapp=0;
              if(deltap < 0 )deltapp=deltap;
               iperiod=(int)((npp+deltap)*dt);
             if(nppc==npp+deltap){
              u[1]=1;
              u[2]=1;
              u[3]=1;
              u[4]=1;
              u[5]=1;
               nppc=0;
                          }

       izoom=slider1.getValue();
           if(izoom > nx){
           for(int kk=0; kk<201; kk++){
                  u[kk]=0;
                  napd[kk]=0;
                 lapd1[kk]=0;
                 lapd2[kk]=0;
                 ntpa[kk]=0;
                                        }

                         }
         nx=izoom;

           npc=npc+1;
           if(nps == 0)npc=2000;

  
           for(int j=1; j<nx; j++){  

           xm=0;
           xp=0;
           xq=0;
           xr=0;
           if(u[j] > um) xm=1;
           if(u[j] > up) xp=1;
           if(u[j] > uq) xq=1;
           if(u[j] > ur) xr=1;
    
           tvm = (1-xq)*tvm1+xq*tvm2;
          
           twm=50;
         if(w[j] > .14)twm=97.+(170.-97.)*(1- ((Math.exp((w[j]-.325)*20)-
          Math.exp(-(w[j]-.325)*20))/(Math.exp((w[j]-.325)*20)
         +Math.exp(-(w[j]-.325)*20))  ) )  / 2 ;

           if(w[j] > .40) twm=92;
           if(w[j] > .45) twm=180;
 
          tso = tso1 + (tso2-tso1)*(1.+
         ( ( Math.exp((u[j]-uso)*xkso)-Math.exp(-(u[j]-uso)*xkso))/
         (Math.exp((u[j]-uso)*xkso)+Math.exp(-(u[j]-uso)*xkso)) ) ) /2. ;


           ts  = (1.-xp)*ts1 + xp*ts2 ;
           to  = (1.-xr)*to1 + xr*to2 ;
    
           vinf = (1.-xq) ;
           winf = (1.-xr)*(1.-u[j]/twinf)+xr*winfast ;
           if(winf > 1.)winf=1. ;

           dv  = (1.-xm)*(vinf-v[j])/tvm-xm*v[j]/tvp;
           dw  = (1.-xp)*(winf-w[j])/twm-xp*w[j]/twp;
           ds  = ((1.+
          ( (Math.exp((u[j]-us)*xks)-Math.exp(-(u[j]-us)*xks))/
          (Math.exp((u[j]-us)*xks)+Math.exp(-(u[j]-us)*xks)) ) ) /2.
                 -s[j])/ts ;
           v[j] = v[j] + dt*dv ;
           w[j] = w[j] + dt*dw ;
           s[j] = s[j] + dt*ds ;
    
           xfi = -v[j]*xm*(u[j]-um)*(uu-u[j])/tfi ;
           xso = (u[j]-uo)*(1-xp)/to + xp/tso ;
           xsi = -xp*w[j]*s[j]/tsi ;

//
//            xpp=0;
//            xqq=0;
//           if(u[j] > -72.0)xpp=1.0;
//           if(u[j] > -79.5)xqq=1.0;
//           h[j]=h[j]+dt*((1.0-xpp)*(1-h[j])/(15.6*xqq+25.2*(1-xqq))-0.3*xpp*h[j]);
//           f[j]=f[j]+dt*((1.0-xpp)*(1.0-f[j])/11.0-xpp*f[j]/667.0);
//           ifi=-0.04*h[j]*(15-u[j])*(u[j]+72)*xpp;
//           iso=(u[j]+85.0)*(1.0-xpp)/8.3+2.0*xpp;
//           isi=-(1.0+((Math.exp(u[j]*0.1)-Math.exp(-u[j]*0.1))/(Math.exp(u[j]*0.1)+Math.exp(-u[j]*0.1))))*f[j]/0.897;
           
            ut[j]=u[j]+(u[j+1]+u[j-1]-2*u[j])*dt/dx2-dt*(xfi+xso+xsi);  

             if(j%mmm==1 && nps > 2 && j > 4 && j < nx-2 && gama > 0){
              if(ut[j] < nu[j][npc]){
//              ut[j]=ut[j]-gama*(u[j]-nu[j][npc])*dt;
                                      }
                       }
           
                                  }          
//           if((u[80]+70)*(ut[80]+70)< 0){
//           if(ut[80] > u[80]){
//              nfront=nfront+1;
//             if(nperiod2 > 0)nperiod=(nperiod1+nperiod2)/2;
//               nperiod2=nperiod1;
//              nperiod1=0;
//                                }
//                                          }
//              if(nfront > 0)nperiod1=nperiod1+1;

               np=np+1;
               if(nperiod < 1)np=0;
               if(np== nperiod){
                  if(nperiod > 0){
                    npc=0;
                    nps=nps+1;
                                 }
                              }

               if(np == nperiod+1)np=0;
//             np=np+1;
//             if(np == nperiod){
//             npc=0;
//             nps=nps+1;
//                               }
//             if(np== nperiod+1){
//              np=0;
//                               }


            for(int j=0; j<nx; j++){

/////////////////////////////////////////////////////////////////////

               if(j>5){
               if(ut[j] > u[j])napd[j]=1;
            if((u[j]-.25)*(ut[j]-.25) < 0. && napd[j]==1 ){
             ntpa[j]=ntpa[j]+1;
             tpa[j][ntpa[j]]=ntime*dt-((-.25+u[j])/(ut[j]-u[j]))*dt;
              
                if(ntpa[j]==2){
                  napd[j]=0;
                 lapd1[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]-1];
                 if(j==27){lapdold=lapdn; 
                             lapdn=lapd1[j];
                            deltat=gama*(lapdn-lapdold)/dt;
                            deltap=(int)(deltat);
                             }
                                 }

               if(ntpa[j]==4){
                  napd[j]=0;
                 lapd2[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]-1];
                ntpa[j]=0; 
                 if(j==27){lapdold=lapdn; 
                             lapdn=lapd2[j];
                            deltat=gama*(lapdn-lapdold)/dt;
                            deltap=(int)(deltat);
                             }

                
                              }

               
               

//              tpa(na)=ntime*dt-((-ur+u(j))/(ut(j)-u(j)))*dt
//             write(51,*)tpa(na),ur
//             if(na.eq.3)then
//              apd=tpa(3)-tpa(2)
//              di=tpa(2)-tpa(1)
//              tpa(1)=tpa(3)
//               na=1        
//               nsen=1
//              msen=msen+1
//              write(62,*)di,apd,nperiod*dt,msen

//              endif
//             endif
                                             }
                     }
///////////////////////////////////////////////////////////////////////



            u[j]=ut[j];
            nu[j][np]=u[j];
                                    }
               u[0]=u[2];
               u[nx]=u[nx-2];
//            h[99]=h[0];
//            f[99]=f[0];
//            u[99]=u[0];
//            nu[99][np]=nu[0][np];
                                 } 
   
                            }
          g.drawImage(Buffer,0,0, this);

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
	
