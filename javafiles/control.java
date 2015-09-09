	import java.applet.*;
	import java.awt.*;
	import java.awt.event.*;

/*      <applet code="control" width=250 height=300>
         </applet>
*/

	public class control extends Applet
	implements MouseListener, ActionListener, ItemListener, Runnable{
        Point p;
//        Label label;
        Button butt,butf,buts,butr,butrs,butla,butsm,butstop;
        int nx=100;
        int nstop=1;
        int nspeed=20;
        double pp;
	boolean down;
        boolean firsttime=true;
        double u[] = new double[110];
        double h[] = new double[110];
        double f[] = new double[110];
        double ut[]= new double[110];
        double nu[][]= new double[110][2100];
        String controla="50 control electrodes, gamma .0"; 
        String controla1="50 control electrodes, gamma .1";
        String controla2="50 control electrodes, gamma .2";
        String controla3="50 control electrodes, gamma .3";
        String controla4="50 control electrodes, gamma .4";
        String controlb="25 control electrodes, gamma .4";
        String controlc="20 control electrodes, gamma .4";
        String controld="10 control electrodes, gamma .4";
        String controle=" 6 control electrodes, gamma .4";
        String controlf=" 4 control electrodes, gamma .2"; 
        String controlf1=" 4 control electrodes, gamma .4";
        String controlf2=" 4 control electrodes, gamma .8";
        String controlf3=" 4 control electrodes, gamma 1";

 
        int mmm=2;
        int nnow=1;
        int nps=0;
        int npc=2000;
        int np=0;
        int nperiod=874;
        int nperiod1=0;
        int nperiod2=0;
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
        for(int i=0; i<100; i++){
         u[i]=-85;
         h[i]=1;
         f[i]=1;
         ut[i]=0;
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
	butt = new Button ("Start  ");
        butt.addActionListener( this );
        add (butt);
	butf = new Button ("Faster");
        butf.addActionListener( this );
        add (butf);
	buts = new Button ("Slower");
        buts.addActionListener( this );
        add (buts);
         Choice options = new Choice();
        options.addItem("50 control electrodes, gamma .0");
        options.addItem("50 control electrodes, gamma .1");
        options.addItem("50 control electrodes, gamma .2");
        options.addItem("50 control electrodes, gamma .3");
        options.addItem("50 control electrodes, gamma .4");
        options.addItem("25 control electrodes, gamma .4");
        options.addItem("20 control electrodes, gamma .4");
        options.addItem("10 control electrodes, gamma .4");
        options.addItem(" 6 control electrodes, gamma .4");
        options.addItem(" 4 control electrodes, gamma .2"); 
        options.addItem(" 4 control electrodes, gamma .4");
        options.addItem(" 4 control electrodes, gamma .8");
        options.addItem(" 4 control electrodes, gamma 1");

        options.addItemListener(this);
        add(options);
//        label= new Label("  ");
//        add(label);
        butstop= new Button ("  Stop  ");
        butstop.addActionListener( this );
        add (butstop);
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
          if(ie.getItem().toString()== controla){
           mmm=2;
           gama=0.0;}
          if(ie.getItem().toString()== controla1){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controla2){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controla3){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controla4){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controlb){ mmm=4;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controlc){ mmm=5;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controld){ mmm=10;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controle){ mmm=17;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controlf){ mmm=25;
                                                  gama=0.2;}
          if(ie.getItem().toString()== controlf1){ mmm=25;
                                                  gama=0.4;}
          if(ie.getItem().toString()== controlf2){ mmm=25;
                                                  gama=0.8;}
          if(ie.getItem().toString()== controlf3){ mmm=25;
                                                  gama=1.0;}



                                                  }
        public void actionPerformed( ActionEvent ae){
                      if(ae.getSource()==butt){
                         if(firsttime){
            butt.setLabel("Restart");
            firsttime=false;
                                      }
        for(int i=0; i<100; i++){
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
 
                                                }

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
//	public void update(Graphics g){

        public void paint(Graphics g){
        g.setColor(Color.blue);
        Dimension d = getSize();
        int width = d.width;
        int height = d.height;
        g.drawRect(20,68,2*nx,237);
        g.setColor(Color.black);
        g.drawString("V",3,155);
        g.drawString("(mV)",0,165);
        g.drawString("L= ("+String.valueOf(nx)+") points",100,300);
         for(int i=0; i< nx-1; i++){
         g.setColor(Color.red);
         if(i%mmm == 1){
          g.drawLine(2*i+20,69,2*i+20,78);
                      }
         g.setColor(Color.black);
         g.drawString("Control",225,220);
          
        if(gama < 0.1)g.drawString("OFF",275,220);
         g.setColor(Color.red);
        if(gama > 0)g.drawString("ON",275,220);


        if(nps > 2){
         g.setColor(Color.blue);

         int nyy1=(int)(115-nu[i][npc]*2.0);
         int nyy2=(int)(115-nu[i+1][npc]*2.0);
         g.drawLine(2*i+20,nyy1,2*i+20,nyy2);
                      }
          g.setColor(Color.black);
         int yy1=(int)(115-u[i]*2.0);
         int yy2=(int)(115-u[i+1]*2.0);
         g.drawLine(2*i+20,yy1,2*i+21,yy2);
        g.setColor(Color.black);

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
           h[nx-1]=h[nx-1]+dt*((1.0-xpp)*(1-h[nx-1])/(1000.0*xqq+19.2*(1-xqq))-0.3*xpp*h[nx-1]);
           f[nx-1]=f[nx-1]+dt*((1.0-xpp)*(1.0-f[nx-1])/11.0-xpp*f[nx-1]/667.0);
           ifi=-0.04*h[nx-1]*(15-u[nx-1])*(u[nx-1]+72)*xpp;
           iso=(u[nx-1]+85.0)*(1.0-xpp)/8.3+2.0*xpp;
           isi=-(1.0+((Math.exp(u[nx-1]*0.1)-Math.exp(-u[nx-1]*0.1))/(Math.exp(u[nx-1]*0.1)+Math.exp(-u[nx-1]*0.1))))*f[nx-1]/0.897;
           ut[nx-1]=u[nx-1]+(u[nx-2]+u[1]-2*u[nx-1])*dt/dx2-dt*(ifi+iso+isi);  

           npc=npc+1;
           if(nps == 0)npc=2000;

  
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

             if(j%mmm==1 && nps > 2){
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
            u[j]=ut[j];
            nu[j][np]=u[j];
                                    }
            h[99]=h[0];
            f[99]=f[0];
            u[99]=u[0];
            nu[99][np]=nu[0][np];
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
	
