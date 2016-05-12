	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="control2" width=450 height=630>
         </applet>
*/

	public class control2 extends Applet
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
 
        String controla="no control electrodes, gam .0"; 
        String controla1="control electrodes every 4, gam .1";
        String controla2="control electrodes every 4, gam .2";
        String controla3="control electrodes every 4, gam .3";
        String controla4="control electrodes every 4, gam .4";
        String controlb="25 control electrodes, gam .4";
        String controlc="20 control electrodes, gam .4";
        String controld="10 control electrodes, gam .4";
        String controle=" 6 control electrodes, gam .4";
        String controlf= "control electrodes every 25, gam .2"; 
        String controlf1="control electrodes every 25, gam .3";
        String controlf2="control electrodes every 25, gam .5";
        String controlf3="control electrodes every 25, gam 1";

 
        int mmm=2;
        int nnow=1;
        int nps=0;
        int npc=2000;
        int np=0;
        int nperiod=874;
        int nperiod1=0;
        int nperiod2=0;
        int npp=890;
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
        double xpp,xqq,ifi,iso,isi;

	public void init() {
        for(int i=0; i<200; i++){
         u[i]=-85;
         h[i]=1;
         f[i]=1;
         ut[i]=0;

         ntpa[i]=0;
         napd[i]=0;
                                }
        for(int i=0; i<5; i++){
         u[i]=0;
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
        options.addItem("no control electrodes, gamma .0");
        options.addItem("control electrodes every 4, gam .1");
        options.addItem("control electrodes every 4, gam .2");
        options.addItem("control electrodes every 4, gam .3");
        options.addItem("control electrodes every 4, gam .4");
//        options.addItem("25 control electrodes, gam .4");
//        options.addItem("20 control electrodes, gam .4");
//        options.addItem("10 control electrodes, gam .4");
//        options.addItem(" 6 control electrodes, gam .4");
        options.addItem("control electrodes every 25, gam .2"); 
        options.addItem("control electrodes every 25, gam .3");
        options.addItem("control electrodes every 25, gam .5");
        options.addItem("control electrodes every 25, gam 1");

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
        public void itemStateChanged(ItemEvent ie){
//          Choice c= (Choice)ie.getItemSelectable();
//          label.setText(c.getSelectedItem());
          if(ie.getItem().toString()== controla){ mmm=4;
                                                  gama=0;}
          if(ie.getItem().toString()== controla1){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controla2){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controla3){ mmm=4;
                                                  gama=0.4;}
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
                                                  gama=0.2;}
          if(ie.getItem().toString()== controlf1){ mmm=25;
                                                  gama=0.3;}
          if(ie.getItem().toString()== controlf2){ mmm=25;
                                                  gama=0.5;}
          if(ie.getItem().toString()== controlf3){ mmm=25;
                                                  gama=1.0;}



                                                  }
        public void actionPerformed( ActionEvent ae){
//                      if(ae.getSource()==butt){
                         if(firsttime){
//            butt.setLabel("Restart");
            firsttime=false;
                                      }
        for(int i=0; i<200; i++){
         u[i]=-85;
         h[i]=1;
         f[i]=1;
         ut[i]=0;
                                }
             
                     for(int i=30; i<50; i++){
          u[i]=0.0;
          h[i+20]=0.0;
                                              }
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
        gBuffer.drawString("L= ("+String.valueOf(nx)+") points",40,320);
         for(int i=0; i< nx-1; i++){
         gBuffer.setColor(Color.red);
             if(i%mmm==1 && nps > 2 && i > 4 && i < nx-2 && gama > 0.){

          gBuffer.drawLine(2*i+20,69,2*i+20,78);
                      }
         gBuffer.setColor(Color.black);

         gBuffer.drawString("Control",65,52);
          
        if(gama < 0.1)gBuffer.drawString("OFF",115,52);
         gBuffer.setColor(Color.red);
        if(gama > 0)gBuffer.drawString("ON",115,52);


        if(nps > 2){
         gBuffer.setColor(Color.blue);

         int nyy1=(int)(115-nu[i][npc]*2.0);
         int nyy2=(int)(115-nu[i+1][npc]*2.0);
         gBuffer.drawLine(2*i+20,nyy1,2*i+20,nyy2);
                      }
          gBuffer.setColor(Color.black);
         int yy1=(int)(115-u[i]*2.0);
         int yy2=(int)(115-u[i+1]*2.0);
         gBuffer.drawLine(2*i+20,yy1,2*i+21,yy2);

        if(i>5 && lapd1[i+1] > 10){
        gBuffer.setColor(Color.orange);
         int yyy1=(int)(655-lapd1[i]*2.5);
         int yyy2=(int)(655-lapd1[i+1]*2.5);
         gBuffer.drawLine(2*i+20,yyy1,2*i+21,yyy2);
                                }

        if(i>5 && lapd2[i+1] > 10){
        gBuffer.setColor(Color.green);
         int yyx1=(int)(655-lapd2[i]*2.5);
         int yyx2=(int)(655-lapd2[i+1]*2.5);
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
             if(nppc==npp){
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
                  u[kk]=-85;
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
            xpp=0;
            xqq=0;
           if(u[j] > -72.0)xpp=1.0;
           if(u[j] > -79.5)xqq=1.0;
           h[j]=h[j]+dt*((1.0-xpp)*(1-h[j])/(15.6*xqq+25.2*(1-xqq))-0.3*xpp*h[j]);
           f[j]=f[j]+dt*((1.0-xpp)*(1.0-f[j])/11.0-xpp*f[j]/667.0);
           ifi=-0.04*h[j]*(15-u[j])*(u[j]+72)*xpp;
           iso=(u[j]+85.0)*(1.0-xpp)/8.3+2.0*xpp;
           isi=-(1.0+((Math.exp(u[j]*0.1)-Math.exp(-u[j]*0.1))/(Math.exp(u[j]*0.1)+Math.exp(-u[j]*0.1))))*f[j]/0.897;
           ut[j]=u[j]+(u[j+1]+u[j-1]-2*u[j])*dt/dx2-dt*(ifi+iso+isi);  

             if(j%mmm==1 && nps > 2 && j > 4 && j < nx-2 && gama > 0){
              if(ut[j] < nu[j][npc]){
              ut[j]=ut[j]-gama*(u[j]-nu[j][npc])*dt;
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
                              }

               if(ntpa[j]==4){
                  napd[j]=0;
                 lapd2[j]=tpa[j][ntpa[j]]-tpa[j][ntpa[j]-1];
                ntpa[j]=0; 
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
	
