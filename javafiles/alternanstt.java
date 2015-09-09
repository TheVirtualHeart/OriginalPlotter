	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="alternanstt" width=650 height=750>
         </applet>
*/

	public class alternanstt extends Applet
	implements MouseListener, ActionListener, ItemListener, Runnable{
        Point p;
         private Panel buttonPanel, checkPanel, stimPanel;

//        Label label;
        Button butt,butf,buts,butr,butrs,butla,butsm,butstop;
        Image Buffer;
        Graphics gBuffer;
        Scrollbar  slider1, horiz;
        int nx=195;
        int nstop=1;
        int nspeed=20;
        double pp;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[310];
        double v[] = new double[310];
        double w[] = new double[310];
        double s[] = new double[310];


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
	 double tvm1=20;
	 double tvm2=20;
         double xm,xp,xq,xr;
         double tso,to,twm,tvm;
         double ds,dv,dw,ts,vinf,winf;

        

//        double h[] = new double[210];
//        double f[] = new double[210];
        double ut[]= new double[310];
        double nu[][]= new double[310][2100];
        double lapd1[] = new double[310];
        double lapd2[] = new double[310];
        int ul[] = new int[310];
        int nflag[]= new int[310];

        int izoom=20;
        double tpa[][] = new double[310][50];
        int ntpa[]= new int[310];
        int napd[] = new int[310];
        int ntime=0;
        int firstime=0;
        int deltap=0;
        int deltapp=0;        
         double deltat=0;
         double lapdn=0;
         double lapdold=0;
         int iperiod;
          int ifirst=0;
 
        String controla="period 297"; 
        String controla1="period 225";
        String controla2="period 165";
        String controla3="period 150";
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
        int nperiod=1980;
        int nperiod1=0;
        int nperiod2=0;
//        int npp=890;


          int npp=1980;
//          int npp=1520;
//        int npp=1000;
        int nppc=0;
        int nfront=0;
        double a=0.1;
        double gama=0;
        double b=0.5;
        double eps=0.01;
        double delta=0;
        double dt=0.15;
        double dx2=2.3;
        double xpp,xqq,xfi,xso,xsi;

// table stuff-------------
        double vlo=-.2;
        double vhi=2;
        int nvt=17000;
        double dvt;
        double vv;
        double table1[]= new double[17050];
        double table2[]= new double[17050];
        double table3[]= new double[17050];

        double windex;
        int    iwindex;
        double vindex;
        int    ivindex;
        int p1=0,p2=0,p3=0,p4=0;
        int p1o,p2o,p3o,p4o;



	public void init() {

         dvt=(vhi-vlo)/((double)(nvt));

        for(int i=0; i<nvt-1; i++){

             vv=vlo+dvt*(double)(i);
         table1[i]=97.+(170.-97.)*(1- ((Math.exp((vv-.325)*20)-
          Math.exp(-(vv-.325)*20))/(Math.exp((vv-.325)*20)
         +Math.exp(-(vv-.325)*20))  ) )  / 2 ;



           table2[i]= tso1 + (tso2-tso1)*(1.+
         ( ( Math.exp((vv-uso)*xkso)-Math.exp(-(vv-uso)*xkso))/
         (Math.exp((vv-uso)*xkso)+Math.exp(-(vv-uso)*xkso)) ) ) /2. ;





           table3[i]=(1.+
          ( (Math.exp((vv-us)*xks)-Math.exp(-(vv-us)*xks))/
          (Math.exp((vv-us)*xks)+Math.exp(-(vv-us)*xks)) ) ) /2. ;




                                   }


        for(int i=0; i<300; i++){
         u[i]=0;
         v[i]=1;
         w[i]=1;
         s[i]=0;
         ut[i]=0;
         ul[i]=0;
         nflag[i]=0;
         

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
        Buffer=createImage(660,720);
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
        options.addItem("period 297");
        options.addItem("period 225");
        options.addItem("period 165");
        options.addItem("period 150");
//        options.addItem("gamma .4");
//        options.addItem("25 control electrodes, gamma .4");
//        options.addItem("20 control electrodes, gamma .4");
//        options.addItem("10 control electrodes, gamma .4");
//        options.addItem(" 6 control electrodes, gamma .4");
//        options.addItem("gamma .5"); 
//        options.addItem("gamma .6");
//        options.addItem("gamma .7");
//        options.addItem("gamma 1");

        options.addItemListener(this);
        buttonPanel.add(options);

        butstop= new Button ("  Stop  ");
        butstop.addActionListener( this );
        buttonPanel.add (butstop);

//	butt = new Button ("Start  ");
//        butt.addActionListener( this );
//        add (butt);

	slider1 = new Scrollbar(Scrollbar.HORIZONTAL, 270, 10, 40, 300);
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
                                                  gama=0;
                                                  npp=1980;
                                                   nppc=0;}
 
          if(ie.getItem().toString()== controla1){ mmm=4;
                                                  gama=0;
                                                   npp=1500;
             if(nppc>=npp+deltap)nppc=0;

                                                            }

          if(ie.getItem().toString()== controla2){ mmm=4;
                                                  gama=0;
                                                   npp=1100;
             if(nppc>=npp+deltap)nppc=0;

                                                           }
          if(ie.getItem().toString()== controla3){ mmm=4;
                                                  gama=0;
                                                   npp=1000;
             if(nppc>=npp+deltap)nppc=0;

                                                           }
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
          nspeed=nspeed+15;
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
          gBuffer.fillRect(0,0,650,750);

        Dimension d = getSize();
        int width = d.width;
        int height = d.height;

         gBuffer.setColor(Color.black);
         gBuffer.fillRect(50,340,nx*2,500);


        gBuffer.setColor(Color.white);
        gBuffer.fillRect(50,68,2*nx,237);
        gBuffer.setColor(Color.blue);
        gBuffer.drawRect(50,68,2*nx,237);
        gBuffer.setColor(Color.black);



        gBuffer.drawString("V",16,155);
        gBuffer.drawString("(mV)",8,165);  
        gBuffer.drawString("APD",11,505);
        gBuffer.drawString("(ms)",10,515);


        gBuffer.drawString("L= ("+String.valueOf(nx)+") points",140,325);
//        gBuffer.drawString("T= T_o *gama*(APD_n+1-APD_n)",130,320);
        gBuffer.drawString("T= "+String.valueOf(iperiod)+"ms",60,325);


        gBuffer.drawString("--40",590,695);
        gBuffer.drawString("--50",590,670);
        gBuffer.drawString("--60",590,645);
        gBuffer.drawString("--70",590,620);
        gBuffer.drawString("--80",590,595);
        gBuffer.drawString("--90",590,570);
        gBuffer.drawString("--100",590,545);
        gBuffer.drawString("--110",590,520);
        gBuffer.drawString("--120",590,495);
        gBuffer.drawString("--130",590,470);
        gBuffer.drawString("--140",590,445);
        gBuffer.drawString("--150",590,420);
        gBuffer.drawString("--160",590,395);
        gBuffer.drawString("--170",590,370);
        gBuffer.drawString("--180",590,345);
        gBuffer.drawString("--30",590,720);



         for(int i=0; i< nx-1; i++){
         gBuffer.setColor(Color.red);
//             if(i%mmm==1 && nps > 2 && i > 4 && i < nx-2 && gama > 0.){
//          gBuffer.drawLine(2*i+20,69,2*i+20,78);
//                                                                        }
         if(gama > 0 ) gBuffer.drawLine(2*27+50,69,2*27+50,78);

                      
         gBuffer.setColor(Color.black);

//         gBuffer.drawString("Control",65,52);
          
//        if(gama < 0.1)gBuffer.drawString("OFF",115,52);
//         gBuffer.setColor(Color.red);
//        if(gama > 0)gBuffer.drawString("ON",115,52);

        gBuffer.setColor(Color.orange);
          if(ul[i]==4){
//           int ip1=(int)(2*p1+50);
//          int ip2=(int)(2*p2+50);
          int iyp=(int)(275-.25*120);
          gBuffer.drawLine(2*i+50,iyp,2*i+51,iyp);
                     }

         gBuffer.setColor(Color.green);
          if(ul[i]==2){
          int iyp=(int)(275-.25*120);
          gBuffer.drawLine(2*i+50,iyp,2*i+51,iyp);
                     }




        if(nps > 2){

//         gBuffer.setColor(Color.blue);
//         int nyy1=(int)(115-nu[i][npc]*2.0);
//         int nyy2=(int)(115-nu[i+1][npc]*2.lapd10);
//         gBuffer.drawLine(2*i+20,nyy1,2*i+20,nyy2);
                      }
          gBuffer.setColor(Color.black);
         int yy1=(int)(275-u[i]*120.0);
         int yy2=(int)(275-u[i+1]*120.0);
         gBuffer.drawLine(2*i+50,yy1,2*i+51,yy2);

        if(i>5 && lapd1[i+1] > 10){
        gBuffer.setColor(Color.orange);
         int yyy1=(int)(795-lapd1[i]*2.5);
         int yyy2=(int)(795-lapd1[i+1]*2.5);
         gBuffer.drawLine(2*i+50,yyy1,2*i+51,yyy2);
                                }

        if(i>5 && lapd2[i+1] > 10){
        gBuffer.setColor(Color.green);
         int yyx1=(int)(795-lapd2[i]*2.5);
         int yyx2=(int)(795-lapd2[i+1]*2.5);
         gBuffer.drawLine(2*i+50,yyx1,2*i+51,yyx2);

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
           for(int k=1; k<50; k++){

              ntime=ntime+1;
              nppc=nppc+1;
               deltapp=0;
              if(deltap < 0 )deltapp=deltap;
               iperiod=(int)((npp+deltap)*dt);
             if(nppc==npp+deltap || ifirst == 20){
             //  ifirst=1;
              u[1]=1;
              u[2]=1;
              u[3]=1;
              u[4]=1;
              u[5]=1;
               nppc=0;
                          }

       izoom=slider1.getValue();
           if(izoom > nx){
           for(int kk=0; kk<301; kk++){
                  u[kk]=0;
                  napd[kk]=0;
                 lapd1[kk]=0;
                 lapd2[kk]=0;
                 ntpa[kk]=0;
                ifirst=0;
         ul[kk]=0;
         nflag[kk]=0;
         ntpa[kk]=0;
         napd[kk]=0;
                                                  npp=1980;
                                                   nppc=0;


                                        }

                         }
         nx=izoom;

           npc=npc+1;
           if(nps == 0)npc=2000;

           ifirst=ifirst+1;
//        if(ifirst==100){
//        for(int i=0; i<25; i++){
//         u[i]=0.8;
//                                 }
           //ifirst=0;
//                     }
  
           for(int j=1; j<nx; j++){  


            windex=(w[j]-vlo)/dvt;
           iwindex=(int)(windex);

            vindex=(u[j]-vlo)/dvt;
           ivindex=(int)(vindex);



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
         if(w[j] > .14)twm=table1[iwindex];
//         97.+(170.-97.)*(1- ((Math.exp((w[j]-.325)*20)-
//          Math.exp(-(w[j]-.325)*20))/(Math.exp((w[j]-.325)*20)
//         +Math.exp(-(w[j]-.325)*20))  ) )  / 2 ;

           if(w[j] > .40) twm=92;
           if(w[j] > .45) twm=180;
 
          tso = table2[ivindex];
//        tso1 + (tso2-tso1)*(1.+
//         ( ( Math.exp((u[j]-uso)*xkso)-Math.exp(-(u[j]-uso)*xkso))/
//         (Math.exp((u[j]-uso)*xkso)+Math.exp(-(u[j]-uso)*xkso)) ) ) /2. ;


           ts  = (1.-xp)*ts1 + xp*ts2 ;
           to  = (1.-xr)*to1 + xr*to2 ;
    
           vinf = (1.-xq) ;
           winf = (1.-xr)*(1.-u[j]/twinf)+xr*winfast ;
           if(winf > 1.)winf=1. ;

           dv  = (1.-xm)*(vinf-v[j])/tvm-xm*v[j]/tvp;
           dw  = (1.-xp)*(winf-w[j])/twm-xp*w[j]/twp;
           ds  = 
         (table3[ivindex]
//         ((1.+
//          ( (Math.exp((u[j]-us)*xks)-Math.exp(-(u[j]-us)*xks))/
//          (Math.exp((u[j]-us)*xks)+Math.exp(-(u[j]-us)*xks)) ) ) /2.
                 -s[j])/ts ;
           v[j] = v[j] + dt*dv ;
           w[j] = w[j] + dt*dw ;
           s[j] = s[j] + dt*ds ;
    
           xfi = -v[j]*xm*(u[j]-um)*(uu-u[j])/tfi ;
           xso = (u[j]-uo)*(1-xp)/to + xp/tso ;
           xsi = -xp*w[j]*s[j]/tsi ;

           
            ut[j]=u[j]+(u[j+1]+u[j-1]-2*u[j])*dt/dx2-dt*(xfi+xso+xsi);  

             if(j%mmm==1 && nps > 2 && j > 4 && j < nx-2 && gama > 0){
              if(ut[j] < nu[j][npc]){
//              ut[j]=ut[j]-gama*(u[j]-nu[j][npc])*dt;
                                      }
                       }
           
                                  }          

               np=np+1;
               if(nperiod < 1)np=0;
               if(np== nperiod){
                  if(nperiod > 0){
                    npc=0;
                    nps=nps+1;
                                 }
                              }

               if(np == nperiod+1)np=0;


            for(int j=0; j<nx; j++){

/////////////////////////////////////////////////////////////////////

               if(j>5){
               if(ut[j] > u[j])napd[j]=1;
  //             ul[j]=0;
            if((u[j]-.25)*(ut[j]-.25) < 0. && napd[j]==1 ){
              ul[j]=nflag[j];
              nflag[j]=nflag[j]+1;
             if(nflag[j]==5)nflag[j]=1;
               
             ntpa[j]=ntpa[j]+1;
             tpa[j][ntpa[j]]=ntime*dt-((-.25+u[j])/(ut[j]-u[j]))*dt;

                     
 
              if(ut[j] > u[j]){
                 p1o=p1;
                if(j!=p3 &&  p1o+13 > j){
                 if(p2 < nx-1 && j > p2)p1=(int)(j);
                          }    }

              if(ut[j] < u[j]){
                  p2o=p2;
                  if(j!=p4 && p2o+13 > j){
                   if(j > p2)p2=(int)(j);
               if(p2==p1 ){p1=0;
                          p2=0; }
                            }
                               }

///

             if(ut[j] > u[j]){
                 p3o=p3;
                  if(j!=p1  && p3o+13 > j){
                 if(p4 < nx-1 && j > p4)p3=(int)(j);
                            }  
                             }

              if(ut[j] < u[j]){
                  p4o=p4;
                 if(j!=p2 && p4o+13 > j){
                   if(j > p4)p4=(int)(j);
               if(p4==p3){p4=0;
                          p3=0; }
                         }
                               }





              
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
	
