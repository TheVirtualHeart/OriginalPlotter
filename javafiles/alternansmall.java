	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="alternansmall" width=870 height=650>
         </applet>
*/

	public class alternansmall extends Applet
	implements MouseListener, ActionListener, ItemListener, Runnable{
        Point p;
         private Panel buttonPanel, checkPanel, stimPanel;

//        Label label;
        Button butt,butf,buts,butr,butrs,butla,butsm,butstop,butres;
        Image Buffer;
        Checkbox chplot;

        Graphics gBuffer;
        Scrollbar  slider1, horiz;
        int nx=195;
        int nstop=1;
        int nspeed=20;
        double pp;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[610];
        double v[] = new double[610];
        double w[] = new double[610];
        double s[] = new double[610];
        double ss[] = new double[610];
        
      int ir[]= new int[260];
      int ig[]= new int[260];
      int ib[]= new int[260];
      int nchplot=0;

         int n=0;
         int nn=0;

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
//	 double tvm1=15;
//	 double tvm2=15;
	 double tvm1=20;
	 double tvm2=20;

         double xm,xp,xq,xr;
         double tso,to,twm,tvm;
         double ds,dv,dw,ts,vinf,winf;
         int vy1;
         int vy2;
         int xntime1;
         int xntime2;

        

//        double h[] = new double[210];
//        double f[] = new double[210];
        double ut[]= new double[610];
        double nu[][]= new double[610][2100];
        double lapd1[] = new double[610];
        double lapd2[] = new double[610];
        double lapd11[] = new double[610];
        double lapd22[] = new double[610];
        double cv1[] = new double[610];
        double cv2[] = new double[610];


        int ul[] = new int[610];
        int nflag[]= new int[610];
        int ncv1[]= new int[610];
        int nncv1[]= new int[610];
        int ncv2[]= new int[610];
        int nncv2[]= new int[610];
        int dapd[] =new int[610];




        int izoom=20;
        double tpa[][] = new double[610][50];
        int ntpa[]= new int[610];
        double tpb[][] = new double[610][50];
        int ntpb[]= new int[610];

        int napd[] = new int[610];
        int ntime=0;
        int nntime=0;
        int inntime=0;

        int firstime=0;
        int deltap=0;
        int deltapp=0;        
         double deltat=0;
         double lapdn=0;
         double lapdold=0;
         int iperiod;
          int ifirst=0;
          int ncounter=0;
 
        String controla="period 297"; 
        String controla1="period 225";
        String controla2="period 165";
        String controla3="period 150";
        String controla4="period 135";
//        String controlb="gamma .4";
//        String controlc="gamma .4";
//        String controld="gamma .4";
//        String controle="gamma .4";
        String controlf= "gamma .5"; 
        String controlf1="gamma .6";
        String controlf2="gamma .7";
        String controlf3="gamma 1";

 
         Font fon= null;
         Font fon2= null;

        int mmm=2;
        int imm=1;
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
 
        double dx=.0479;
//        double dx=.025 ;
        double dx2=dx*dx;
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
        int iicv,iiicv,ncv;



	public void init() {

//--------other color code -------
//         for(int i=0; i<97; i++){
//         ir[i]=(int)(1.134*n);
//         ig[i]=(int)(1.45*n);
//         ib[i]=255-(int)(1.5*n);

         for(int i=0; i<45; i++){
         ir[i]=(int)(1.777*n);
         ig[i]=(int)(2.888*n);
         ib[i]=255;
         n=n+1;
         }
          n=0;
         for(int i=45; i<97; i++){
         ir[i]=80;
         ig[i]=130+(int)(2.4*n);
         ib[i]=255;
         n=n+1;
         }

         ir[97]=255;
         ig[97]=255;
         ib[97]=255;

         n=1;
        for(int i=98; i<196; i++){
//        for(int i=196; i<98; i--){

         ir[i]=255; // +(int)(.55*n);
         ig[i]=97-n; //ig[97]-(int)(.55*nn);
         ib[i]=134-(int)(n*1.381);
         n=n+1;
         nn=nn+1;
         }
        for(int i=196; i<200; i++){
         ir[i]=ir[195];
         ig[i]=ig[195];
         ib[i]=ib[195];
         }

///-----------------------------------


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
         ss[i]=0;
         ut[i]=0;
         ul[i]=0;
         nflag[i]=0;
         nncv1[i]=0; 
         nncv2[i]=0; 
         dapd[i]=0;
              //   lapd11[i]=299;
              //   lapd22[i]=299;

         ntpa[i]=0;
         ntpb[i]=0;

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
        Buffer=createImage(1140,720);
        gBuffer=Buffer.getGraphics();



//	butt = new Button ("Start  ");
//        butt.addActionListener( this );
//        add (butt);
        buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(2,3));


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
        options.addItem("period 135");

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

	butres = new Button ("Reset");
        butres.addActionListener( this );
        buttonPanel.add (butres);

        butstop= new Button ("  Stop  ");
        butstop.addActionListener( this );
        buttonPanel.add (butstop);

      chplot = new Checkbox("Plot AP(t) vs AP(L)", false);
      chplot.addItemListener( this );
        buttonPanel.add (chplot);


//	butt = new Button ("Start  ");
//        butt.addActionListener( this );
//        add (butt);

	slider1 = new Scrollbar(Scrollbar.HORIZONTAL, 270, 10, 40, 280);
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

         if(chplot.getState()==true){
           nchplot=1;
         //  nntime=0;
           }
         if(chplot.getState()==false){
           nchplot=2;
           }




          if(ie.getItem().toString()== controla){ mmm=4;
                                                  imm=1;
                                                  gama=0;
                                                  npp=1980;
                                                   nppc=0;}
 
          if(ie.getItem().toString()== controla1){ mmm=4;
                                                  imm=2;

                                                  gama=0;
                                                   npp=1370;
             if(nppc>=npp+deltap)nppc=0;

                                                            }

          if(ie.getItem().toString()== controla2){ mmm=4;
                                                  imm=3;

                                                  gama=0;
                                                  npp=1100;
//                                                    npp=1350;
             if(nppc>=npp+deltap)nppc=0;

                                                           }
          if(ie.getItem().toString()== controla3){ mmm=4;
                                                  imm=4;

                                                  gama=0;
                                                   npp=1000;
             if(nppc>=npp+deltap)nppc=0;

                                                           }
          if(ie.getItem().toString()== controla4){ mmm=4;
                                                  imm=5;

                                                  gama=0.4;
                                                  npp=1200;
             if(nppc>=npp+deltap)nppc=0;


                                                          }
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

         if(ae.getSource()==butres){
//     ie.setItemText("period 297");

           for(int kk=0; kk<301; kk++){
                  u[kk]=0;
         v[kk]=1;
         w[kk]=1;
         s[kk]=0;
         ss[kk]=0;
         ut[kk]=0;

                  napd[kk]=0;
                 lapd1[kk]=0;
                 lapd2[kk]=0;
                 lapd11[kk]=297;
                 lapd22[kk]=297;
                 cv1[kk]=0;
                 cv2[kk]=0;
                 dapd[kk]=0;

                 ntpa[kk]=0;
                 ntpb[kk]=0;
         nncv1[kk]=0; 
         nncv2[kk]=0; 

                ifirst=0;
         ul[kk]=0;
         nflag[kk]=0;
         ntpa[kk]=0;
         napd[kk]=0;
         dapd[kk]=0;
         imm=1;
                                                  npp=1980;
                                                   nppc=0;
         


                                        }


        //  butf.setLabel("Faster"); 
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

      fon = new Font("Times New Roman", Font.PLAIN, 12);
      fon2 = new Font("Times New Roman", Font.PLAIN, 22);

 

          gBuffer.setColor(new Color(192,192,192));
          gBuffer.fillRect(0,0,360,750);
          gBuffer.setFont(fon);

        Dimension d = getSize();
        int width = d.width;
        int height = d.height;

         gBuffer.setColor(Color.black);
//         gBuffer.fillRect(50,340,nx*2,500);
         gBuffer.fillRect(50,250,nx,180);
         gBuffer.fillRect(50,440,nx,80);
         gBuffer.fillRect(50,530,nx,80);





        gBuffer.setColor(Color.white);
        if(nntime==0 && nchplot==1) gBuffer.fillRect(380,58,502,357);
         gBuffer.fillRect(380,58,160,57);

 
        gBuffer.fillRect(50,58,nx,187);
        gBuffer.setColor(Color.blue);

        gBuffer.drawRect(50,58,nx,187);
        gBuffer.setColor(Color.black);






//        gBuffer.drawString("L= "+String.valueOf(nx*dx)+ " ("+String.valueOf(nx)+") points",140,325);
//        gBuffer.drawString("T= "+String.valueOf(iperiod)+"ms",60,325);
//           int iicv=(int)((1100-cv1[50]*1000.*12));
//           int iiicv=(int)((1100-cv1[50]*100.*12));
//            iicv=(int)((cv1[50]*1000.*12));
//            iiicv=(int)((cv1[50]*100.*12));
//            iiicv=iiicv*10;
//            ncv=iicv-iiicv;
//           if(ncv > 2.5)iiicv=iiicv+5;
//           if(ncv > 7.5)iiicv=iiicv+5;



//             iicv=iicv;
//             iiicv=iiicv;


           

//        gBuffer.drawString(" "+String.valueOf(cv2[50]),0,565);
//        gBuffer.drawString(" "+String.valueOf(cv1[50]),0,575);


//        gBuffer.drawString("T= T_o *gama*(APD_n+1-APD_n)",130,320);


//        gBuffer.drawString("--40",50+nx,695);
        gBuffer.drawString("--50",50+nx,417);
        gBuffer.drawString("60--",20,405);
        gBuffer.drawString("--70",50+nx,392);
        gBuffer.drawString("80--",20,380);
        gBuffer.drawString("--90",50+nx,367);
//        gBuffer.drawString("100--",15,355);
        gBuffer.drawString("--110",50+nx,342);
//        gBuffer.drawString("120--",15,330);
        gBuffer.drawString("--130",50+nx,317);
        gBuffer.drawString("140--",15,305);
        gBuffer.drawString("--150",50+nx,292);
        gBuffer.drawString("160--",15,280);
        gBuffer.drawString("--170",50+nx,267);
        gBuffer.drawString("APD",15,259);

         if(imm==4){
        gBuffer.drawString("--153",50+nx,458);
        gBuffer.drawString("--150",50+nx,482);
        gBuffer.drawString("--147",50+nx,506);
                   }

         if(imm==1){
        gBuffer.drawString("--295",50+nx,458);
        gBuffer.drawString("--297",50+nx,482);
        gBuffer.drawString("--299",50+nx,506);
                   }

        if(imm==2){
        gBuffer.drawString("--223",50+nx,458);
        gBuffer.drawString("--225",50+nx,482);
        gBuffer.drawString("--227",50+nx,506);
                   }

        if(imm==3){
        gBuffer.drawString("--163",50+nx,458);
        gBuffer.drawString("--165",50+nx,482);
        gBuffer.drawString("--167",50+nx,506);
                   }

        if(imm==5){
        gBuffer.drawString("--133",50+nx,458);
        gBuffer.drawString("--135",50+nx,482);
        gBuffer.drawString("--147",50+nx,506);
                   }





        gBuffer.drawString("--46",50+nx,550);
        gBuffer.drawString("--45",50+nx,562);
        gBuffer.drawString("--44",50+nx,574);
        gBuffer.drawString("--43",50+nx,586);

          gBuffer.setFont(fon2);
        gBuffer.setColor(Color.orange);
        gBuffer.drawString("Odd  beats",415,82);
        gBuffer.fillRect(383,74,25,2);  

               gBuffer.setColor(Color.green);
        gBuffer.drawString("Even beats",415,106);
        gBuffer.fillRect(383,98,25,2);  

              gBuffer.setColor(Color.black);
     if(nchplot==1){   gBuffer.drawString("Time (ms)                >",455,437);
        gBuffer.drawString("L",358,120);
        gBuffer.drawString("V",359,237);
                  }

    

        gBuffer.drawString("V",16,155);
        gBuffer.drawString("APD",2,335);

//        gBuffer.drawString("(mV)",2,169);  
        gBuffer.drawString("T",13,485);
//        gBuffer.drawString("(ms)",2,499);
        gBuffer.drawString("CV",8,570);
//        gBuffer.drawString("cm/s",1,569);


          gBuffer.setFont(fon);
        gBuffer.drawString("(mV)",9,173);  
        gBuffer.drawString("(ms)",10,350);  
     if(nchplot==1){
        gBuffer.drawString("(mm)",346,130);  
        gBuffer.fillRect(364,140,2,90);  
        gBuffer.fillRect(578,430,100,2);  
                    }

        gBuffer.drawString("(ms)",8,503);
        gBuffer.drawString("cm/s",6,588);











//        gBuffer.drawString("APD",57+nx,320);
//        gBuffer.drawString("(ms)",57+nx,330);


//        gBuffer.drawString("--30",50+2*nx,720);
        gBuffer.setColor(Color.white);
        gBuffer.fillRect(55,414,nx-8,15);
    
           for(int i=5; i< nx-4; i++){
                 dapd[i]=(int)(lapd1[i]-lapd2[i]);

           int ii=dapd[i]+97;
           if(ii <= 5)ii=5;
           if(ii >= 190)ii=190;
           gBuffer.setColor(new Color(ir[ii],ig[ii],ib[ii]));
           gBuffer.fillRect(50+i,415,1,13);                            
           if(ii==97)gBuffer.fillRect(50+i,415,3,13); 
                                       }


        gBuffer.setColor(Color.black);
     


         for(int i=0; i< nx-1; i++){


        gBuffer.setColor(Color.black);




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
          int iyp=(int)(225-.25*120);
          gBuffer.drawLine(i+50,iyp,i+51,iyp);
                     }

         gBuffer.setColor(Color.green);
          if(ul[i]==2){
          int iyp=(int)(225-.25*120);
          gBuffer.drawLine(i+50,iyp,i+51,iyp);
                     }




        if(nps > 2){

//         gBuffer.setColor(Color.blue);
//         int nyy1=(int)(115-nu[i][npc]*2.0);
//         int nyy2=(int)(115-nu[i+1][npc]*2.lapd10);
//         gBuffer.drawLine(2*i+20,nyy1,2*i+20,nyy2);
                      }
          gBuffer.setColor(Color.black);
         int yy1=(int)(225-u[i]*120.0);
         int yy2=(int)(225-u[i+1]*120.0);
         gBuffer.drawLine(i+50,yy1,i+51,yy2);

        if(i>5 && lapd1[i+1] > 10){
        gBuffer.setColor(Color.orange);
         int yyy1=(int)((480-lapd1[i]*1.25));
         int yyy2=(int)((480-lapd1[i+1]*1.25));
         gBuffer.drawLine(i+50,yyy1,i+51,yyy2);


          yyy1=nncv1[i];
          yyy2=nncv1[i+1];

         if(i > 11 && i< nx-6 && yyy2 > 530)gBuffer.drawLine(i+50,yyy1,i+51,yyy2);

          if(imm==1){
          yyy1=(int)((2856-lapd11[i]*8.));
          yyy2=(int)((2856-lapd11[i+1]*8.));
                    }

          if(imm==3){
          yyy1=(int)((1800-lapd11[i]*8.));
          yyy2=(int)((1800-lapd11[i+1]*8.));
                    }
          if(imm==2){
          yyy1=(int)((2280-lapd11[i]*8.));
          yyy2=(int)((2280-lapd11[i+1]*8.));
                    }

          if(imm==5){
          yyy1=(int)((1560-lapd11[i]*8.));
          yyy2=(int)((1560-lapd11[i+1]*8.));
                    }



          if(imm==4){
          yyy1=(int)((1680-lapd11[i]*8.));
          yyy2=(int)((1680-lapd11[i+1]*8.));
                    }
          if(yyy2 > 440 && yyy1 > 440 && yyy2 < 520 && yyy1 < 520 )gBuffer.drawLine(i+50,yyy1,i+51,yyy2);
                     

                                }

        if(i>5 && lapd2[i+1] > 10){
        gBuffer.setColor(Color.green);
         int yyx1=(int)((480-lapd2[i]*1.25));
         int yyx2=(int)((480-lapd2[i+1]*1.25));
         gBuffer.drawLine(i+50,yyx1,i+51,yyx2);

         if(imm==1){
          yyx1=(int)((2856-lapd22[i]*8.));
          yyx2=(int)((2856-lapd22[i+1]*8.));
                    }
         if(imm==2){
          yyx1=(int)((2280-lapd22[i]*8.));
          yyx2=(int)((2280-lapd22[i+1]*8.));
                    }

         if(imm==5){
          yyx1=(int)((1560-lapd22[i]*8.));
          yyx2=(int)((1560-lapd22[i+1]*8.));
                    }



         if(imm==3){
          yyx1=(int)((1800-lapd22[i]*8.));
          yyx2=(int)((1800-lapd22[i+1]*8.));
                    }


         if(imm==4){
          yyx1=(int)((1680-lapd22[i]*8.));
          yyx2=(int)((1680-lapd22[i+1]*8.));
                    }
        if(yyx2 > 440 && yyx1 > 440 && yyx2 < 520 && yyx1 < 520 ) gBuffer.drawLine(i+50,yyx1,i+51,yyx2);
                   
          yyx1=nncv2[i];
          yyx2=nncv2[i+1];
        if(i > 11 && i< nx-6 && yyx2 > 530) gBuffer.drawLine(i+50,yyx1,i+51,yyx2);





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
           if(nchplot==1) nntime=nntime+1;
             if(nntime > 17500 ) nntime=0;
        if(nntime==0 && nchplot==1) {
            gBuffer.setColor(Color.white);
            gBuffer.fillRect(380,68,502,350);
            gBuffer.setColor(Color.black); }

//         if(nchplot==2){
//          gBuffer.setColor(new Color(192,192,192));
//            gBuffer.fillRect(380,60,502,390);
//            gBuffer.fillRect(330,60,100,210);
//
//            gBuffer.setColor(Color.black); }


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
                 lapd11[kk]=297;
                 lapd22[kk]=297;
                 cv1[kk]=0;
                 cv2[kk]=0;
                 dapd[kk]=0;

                 ntpa[kk]=0;
                 ntpb[kk]=0;

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

           
            ut[j]=u[j]+(u[j+1]+u[j-1]-2*u[j])*dt*.001/dx2-dt*(xfi+xso+xsi);  

             if(j%mmm==1 && nps > 2 && j > 4 && j < nx-2 && gama > 0){
              if(ut[j] < nu[j][npc]){
//              ut[j]=ut[j]-gama*(u[j]-nu[j][npc])*dt;
                                      }
                       }
           
                                  }          

//         gBuffer.setColor(Color.black);
//         int vy1=(int)(u[20]*30);
//         int vy2=(int)(ut[20]*30);
//         int xntime1=(int)(nntime/35);
//         int xntime2=(int)((nntime+1)/35);

//          gBuffer.drawLine(650+xntime1,110-vy1,xntime2+651,110-vy2);  
          


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

            if((u[j]-.25)*(ut[j]-.25) < 0.  ){
             ntpb[j]=ntpb[j]+1;
             tpb[j][ntpb[j]]=ntime*dt-((-.25+u[j])/(ut[j]-u[j]))*dt;




              if(ntpb[j]==3){
                 lapd11[j]=tpb[j][3]-tpb[j][1];
                            }
              if(ntpb[j]==5){
                 lapd22[j]=tpb[j][5]-tpb[j][3];
                 ntpb[j]=1;
                 tpb[j][1]=tpb[j][5];
                            }

              if(ntpb[j]==1 && j > 6){
                cv1[j]=(dx)/(tpb[j][ntpb[j]]-tpb[j-1][ntpb[j]]);
                ncv1[j]=1100-(int)((cv1[j]*1000.*12));
               if(j>10 && j<nx-5){
                 nncv1[j]=(ncv1[j-1]+ncv1[j-2]+ncv1[j-3]+ncv1[j-4]+ncv1[j-5]+ncv1[j+1]+
       ncv1[j+2]+ncv1[j+3]+ncv1[j+4]+ncv1[j+5]+ncv1[j])/11;
                                 }


                                         }
              if(ntpb[j]==3 && j > 6){
                cv2[j]=(dx)/(tpb[j][ntpb[j]]-tpb[j-1][ntpb[j]]);
                ncv2[j]=1100-(int)((cv2[j]*1000.*12));
               if(j>10 && j<nx-5){
                 nncv2[j]=(ncv2[j-1]+ncv2[j-2]+ncv2[j-3]+ncv2[j-4]+ncv2[j-5]+ncv2[j+1]+
       ncv2[j+2]+ncv2[j+3]+ncv2[j+4]+ncv2[j+5]+ncv2[j])/11;
                                 }



                                         }


              
                                             }



            if((u[j]-.25)*(ut[j]-.25) < 0. && napd[j]==1 ){
              ss[j]=ss[j]+1 ;
             if(ss[j]==6)ss[j]=0;
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


              
//                if(ntpa[j]==3){
//                 lapd11[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]-2];
//                              }
//                if(ntpa[j]==1){
//                 lapd22[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]+2];
//                              }

                 



              
                if(ntpa[j]==2){
                  napd[j]=0;
                 lapd1[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]-1];
//                 dapd[j]=(int)(lapd1[j]-lapd2[j]);
             //    dapd[j]=dapd[j]

                 if(j==27){lapdold=lapdn; 
                             lapdn=lapd1[j];
                            deltat=gama*(lapdn-lapdold)/dt;
                            deltap=(int)(deltat);
                             }
                                 }

               if(ntpa[j]==4){
                  napd[j]=0;
                 lapd2[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]-1];
//                  dapd[j]=(int)(lapd2[j]-lapd1[j]);
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
        if(j > 5 && nchplot==1) {
           ncounter=ncounter+1;
         if(ncounter==3){
        if(nflag[j] <=2) gBuffer.setColor(Color.orange);
        if(nflag[j] >2)gBuffer.setColor(Color.green);

         int vy1=(int)(u[j]*30);
         int vy2=(int)(ut[j]*30);
         int xntime1=(int)(nntime/35);
         int xntime2=(int)((nntime+1)/35);

          gBuffer.drawLine(380+xntime1,142+j*1-vy1,xntime2+381,142+j*1-vy2);  
          ncounter=0;
                        }
             
        }


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
	
