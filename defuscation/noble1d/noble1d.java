 	import java.awt.*;
	import java.applet.Applet;
	import java.awt.event.*;
        import java.lang.*;

         /*
         <applet code="noble1d" width=600 height=450>
         </applet>
          */

	public class noble1d extends Applet 
        implements MouseListener, ActionListener, ItemListener, Runnable{
        Point p;
        private Panel buttonPanel, checkPanel, stimPanel;
        private Label prompt1, prompt2, prompt3, prompt4, prompt5;
        private Label promptg,promptgg,promptg2,promptg3;
        private Label slide1,slide2,slide3,slide4;
        private TextField input1, input2, input3, input4, input5;
        private Label prompts1, prompttime;
        private TextField  inputs1, inputtime;
        private Label sstrength,sduration,slength,sposition;
        private TextField sstrengtht,sdurationt,slengtht,spositiont;
        private double result;
        Scrollbar horiz, slider1;
	Button butt,butp,butf,buts,butr,butrs,butla,butsm,butup,butdown;
        Button bstimulus;
        Checkbox checkv,checkm,checkh,checkn,checkd,checkf,checkx,checkca;
        Color  background;
        Image Buffer;
        Graphics gBuffer;
        boolean firsttime= true;
        boolean firsttime2=true;
	boolean down;

        double dt=0.010;
        double dtt=0.010;
        double a=0.1;
        double gama=1;
        double b=0.5;
        double eps=0.01;
        double delta=0;
        double s1=20;
        double s2=320;
        double time=5;
        double pp;
        double upp;
        int is1,is2,ntime,iis1,iis2,iiss;
        int nt=1;
        int nstop=0;
        int nspeed=20;
         int so=185;
         int mo=185;
         int ho=85;
         int jo=85;
         int ddo=185;
         int fo=85;
         int x1o=185;
         int cao=185;
         int sso=185;
         int xo=20;

         double Istim,tstim,xstim,lstim;
         int stimsen=0;
         int stimesenx=0;
         int stimsenx=0;
         int nstimdur,nxs1,nxs1i,nxs1f;
         int ncounter=0;
         

//    8 variables of the model
        double m[]= new double[1101];
        double h[]= new double[1101];
        double n[]= new double[1101];
        double u[]= new double[1101];
        double ca[]= new double[1101];
        double ut[]= new double[1101];
        int nx=801;
        int mx=500;
        double xca;
//          
        double xu=0;

        double ax1,am,ah,an,ad,af;
        double bx1,bm,bh,bn,bd,bf;
        double ik1,ix1,ina,is,es,gix1;
        double xik1,xik2;
        double ik,ik2;
        double gk2=1.2;
        double gna1=400;
        double gna2=0.12;

        double gna=4.0;
        double gnac=0.003;
        double ena=50;
        double gs=0.09; 
        double msen=0;
        double hsen=0;
        double jsen=0;
        double dsen=0;
        double vsen=10;
        double casen=0;
        double xsen=0;
        double fsen=0;
        double dtime=0;
        int ndtime=0;



        double amt[] = new double[24000];
        double bmt[] = new double[24000];
        double aht[] = new double[24000];
        double bht[] = new double[24000];
        double ant[] = new double[24000];
        double bnt[] = new double[24000];
        double ik1t[] = new double[24000];
        double dvt=0.01;
        double vmin=-150;
        int ntab;
        double tab;
         double dx=.025;
         double dx2=.000625;
         double Dlap=.001;
         double xlap=0;

        int xpos;
        int izoom;
        double zoom;
        double valueee;
        int ncable=1;
        String optionc="Cable";
        String optionr="Ring";



      public void init() {
    background = new Color (192, 192, 192);
    setBackground(background);

	 Thread thread = new Thread(this);
	 thread.start();
	 addMouseListener(this);
        Buffer=createImage(520,290);
        gBuffer=Buffer.getGraphics();


//    background = new Color (255, 255, 255);
//    setBackground(background);

//   ******************TABLE**************** 
      for(int i=0; i< 24000; i++){
       xu=vmin+i*dvt;

         double antt=(Math.exp((-xu-50.0)/10.0)-1.0);
         if(antt==0){
             ant[i]=ant[i-1];
                     }
               else{
         ant[i]=0.0001*(-xu-50)/(Math.exp((-xu-50.0)/10.0)-1.0);
                   }
         bnt[i]=0.002*Math.exp((-xu-90.0)/80.0);

        double  amtt=(Math.exp((-xu-48.0)/15.0)-1.0);
          if(amtt==0){
         amt[i]=amt[i-1];
                     }
                 else{
         amt[i]=0.1*(-xu-48.0)/(Math.exp((-xu-48.0)/15.0)-1.0);
                     }
           double bmtt=(Math.exp((xu+8.)/5.0)-1.0);
           if(bmtt==0){
         bmt[i]=bmt[i-1];
                      }
             else{
         bmt[i]=0.12*(xu+8.0)/(Math.exp((xu+8.)/5.0)-1.0);
                 }

             
         aht[i]=0.17*Math.exp((-xu-90)/20.0);
         bht[i]=1.0/(Math.exp((-xu-42.0)/10.0)+1.0);

      ik1t[i]=1.2*Math.exp((-xu-90.0)/50.0)+0.015*Math.exp((xu+90.0)/60.0);
       
                                }


//    Initial conditions 
       for(int i=0; i<1101; i++) {
        u[i]=-84.5737;
        ca[i]=0.0000001;
        tab=((u[i]-vmin)/dvt);
        ntab=(int)(tab);

        m[i]= amt[ntab]/(amt[ntab]+bmt[ntab]);
        h[i]= aht[ntab]/(aht[ntab]+bht[ntab]);
        n[i]= ant[ntab]/(ant[ntab]+bnt[ntab]);
                                  }

//         u=-85;
         is2=(int)(s2);
         is1=(int)(s1);

       setSize(601,330);

//        buttonPanel = new Panel();
	butt = new Button ("Start");
        butt.setBackground(Color.green);
        butt.addActionListener( this );
        add(butt);
        butt.reshape(0,0,65,25);
      
	   prompt2= new Label("gk2");
           add(prompt2);
           prompt2.reshape(85,0,25,30);

           input2= new TextField(5);
	   input2.addActionListener( this);
           add(input2);
           input2.reshape(115,0,50,30);

           prompt3=new Label ("gna1");
           add(prompt3);
           prompt3.reshape(185,0,40,30);

           input3= new TextField(5);
           input3.addActionListener( this); 
           add(input3);
            input3.reshape(225,0,50,30); 


           prompt4=new Label ("gna2");
           add(prompt4);
           prompt4.reshape(300,0,25,30);

           input4= new TextField(3);
           input4.addActionListener( this); 
           add(input4);
           input4.reshape(325,0,50,30);

	slider1 = new Scrollbar(Scrollbar.HORIZONTAL, 800, 60, 20, 1160);
        add(slider1);
        slider1.reshape(405,14,165,15);

       horiz=new Scrollbar(Scrollbar.HORIZONTAL,1,20,1,150);
        add(horiz);
        horiz.reshape(120,302,350,15);

        slide1= new Label("Simulation Speed");
        slide2= new Label("Fast");
        slide3= new Label("Slow");
         add(slide1);
         add(slide2);
         add(slide3);
         slide2.reshape(75,299,50,21);
         slide3.reshape(480,299,50,21);
         slide1.reshape(247,312,150,20);



        prompts1=new Label ("Cable Length");
        Font font1 = new Font("Helvetica", Font.BOLD, 9);
        prompts1.setFont(font1);
          add(prompts1);
           prompts1.reshape(460,0,68,13);

        slide1.setFont(font1);




           prompt5=new Label (" S2 at ");
           input5= new TextField(5);
           input5.addActionListener( this); 
           input2.setText("1.2");
	   input3.setText("400");
           input4.setText("0.12");
           input5.setText("320");

//           prompts1=new Label ("  Length");
           inputs1= new TextField(5);
           inputs1.addActionListener( this); 
           inputs1.setText(String.valueOf(nx*dx));
           prompttime=new Label (" time ");
           inputtime= new TextField(5);
           inputtime.addActionListener( this); 
           inputtime.setText("5");
	butup = new Button ("<");
        butup.addActionListener( this );
	butdown = new Button (">");
        butdown.addActionListener( this );
 
        stimPanel = new Panel();
        bstimulus= new Button ("Apply S1");
        bstimulus.addActionListener(this);
        add(bstimulus);
        bstimulus.reshape(0,267,65,28);


        sstrength = new Label("Strength");
        sstrengtht= new TextField(4);
        add(sstrength);
        sstrength.reshape(75,270,60,25);
        sstrengtht.setText("-35.0");
        add(sstrengtht);
        sstrengtht.reshape(135,267,55,30);

        sduration= new Label("Duration");
        add(sduration);
        sduration.reshape(195,270,60,25);

        sdurationt= new TextField(4);
        sdurationt.setText("30");
        add(sdurationt);
        sdurationt.reshape(255,267,50,30);

        slength = new Label("Length");
        add(slength);
         slength.reshape(310,270,48,25);

        slengtht = new TextField(4);
        slengtht.setText("0.5");
        add(slengtht);
        slengtht.reshape(360,267,55,30);


        sposition= new Label("Position");
        add(sposition);
        sposition.reshape(420,270,55,25);
        spositiont= new TextField(4);
        spositiont.setText("0.25");
        add(spositiont);
        spositiont.reshape(477,267,50,30);

        Choice options= new Choice();
         options.addItem("Cable");
         options.addItem("Ring");
         options.addItemListener(this);
         add(options);
         options.reshape(0,302,65,28);




       checkPanel = new Panel();
       
       butp = new Button("      ");
       butp.addActionListener(this );
       promptg = new Label("       ");
       promptgg = new Label("      ");
       promptg2 = new Label("       ");
       promptg3 = new Label("      ");


       checkv = new Checkbox("Voltage", true);
       checkv.addItemListener(this );
       checkm= new Checkbox("m gate",false);
       checkm.addItemListener(this );
       checkh= new Checkbox("h gate",false);
       checkh.addItemListener(this );
       checkn= new Checkbox("n gate",false);
       checkn.addItemListener(this );
       checkd= new Checkbox("d gate",false);
       checkd.addItemListener(this );
       checkf= new Checkbox("f gate",false);
       checkf.addItemListener(this );
       checkx= new Checkbox("x1 gate",false);
       checkx.addItemListener(this );
       checkca= new Checkbox("[Ca]", false);
       checkca.addItemListener(this );


             repaint();


//           buttonPanel.setLayout(new GridLayout(1,11));
//           buttonPanel.add(butt);
//           buttonPanel.add(prompt2);
//           buttonPanel.add(input2);
//           buttonPanel.add(prompt3);
//           buttonPanel.add(input3);

//           buttonPanel.add(prompt4);
//          buttonPanel.add(input4);

//           buttonPanel.add(prompts1);
//           buttonPanel.add(inputs1);
//           buttonPanel.add(butup);
//           buttonPanel.add(butdown);



           checkPanel.setLayout(new GridLayout(7,1));
//           checkPanel.add(promptg);
           checkPanel.add(promptgg);
           checkPanel.add(butp);
           checkPanel.add(checkv);
           checkPanel.add(checkm);
           checkPanel.add(checkh);
           checkPanel.add(checkn);
           checkPanel.add(promptg);



//           checkPanel.add(checkd);
//           checkPanel.add(checkf);
//           checkPanel.add(checkx);
//           checkPanel.add(checkca);

//        stimPanel.setLayout(new GridLayout(1,9));
//        stimPanel.add(bstimulus);
//        stimPanel.add(sstrength);
//        stimPanel.add(sstrengtht);
//        stimPanel.add(sduration);
//        stimPanel.add(sdurationt);
//        stimPanel.add(slength);
//        stimPanel.add(slengtht);
//        stimPanel.add(sposition);
//        stimPanel.add(spositiont);
           


           setLayout(new BorderLayout() );
           add(stimPanel, BorderLayout.SOUTH);
           add(checkPanel, BorderLayout.EAST);
//           add(buttonPanel, BorderLayout.NORTH);
 

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
	   if(pp > 30 && pp < 266){
	  ndtime=1; 
          upp=(105.0-pp)/1.4;
          pp=p.x-20;
          if(upp < -90){upp=-90;}
          if(upp > 80){upp=79.9;} 
          double pxx1=pp*izoom/501.;
          int pxx=(int)(pxx1);
           if(pxx > 0){
          u[pxx]=upp;
          u[pxx+1]=upp;
          u[pxx+2]=upp;
          if(pxx > 1){u[pxx-1]=upp;}
          if(pxx > 2){u[pxx-2]=upp;}
                      }
		        }
         down=true;
	}
	public void mouseReleased(MouseEvent me){
          down=false;
	}

        public void update (Graphics g){ paint(g); }                                            

	public void paint(Graphics g) {
        if(firsttime){
          gBuffer.setColor(Color.black);
          gBuffer.fillRect(0,30,520,230);
          gBuffer.setColor(Color.white);
          gBuffer.drawLine(20,40,20,230);
          gBuffer.drawLine(20,230,520,230);
          gBuffer.drawString("-85",2,240);
          gBuffer.drawString("mV",1,140);
          gBuffer.drawString("30",2,50);
          gBuffer.drawString("time="+String.valueOf((float)(dtime))+"ms",400,55);
          gBuffer.drawString("Length of cable: "+String.valueOf((float)(nx*dx))+" cm ",188,249);
          gBuffer.drawString("<---------------------| ",20,247);
          gBuffer.drawString("|---------------------> ",356,247);

          g.drawImage(Buffer,0,0, this);

//          for(int ii=0; ii< 20; ii++){u[ii]=-50;}


                      }
          else{

       izoom=slider1.getValue();

       if(izoom > nx){
 
       for(int i=nx; i<izoom; i++) {
        u[i]=u[nx-1];
        ca[i]=ca[nx-1];
        m[i]= m[nx-1];
        h[i]= h[nx-1];
        n[i]= n[nx-1];
//        u[i]=-84.5737;
//        ca[i]=0.0000001;
//        tab=((u[i]-vmin)/dvt);
//        ntab=(int)(tab);

//        x1[i]=ax1t[ntab]/(ax1t[ntab]+bx1t[ntab]);
//        m[i]= amt[ntab]/(amt[ntab]+bmt[ntab]);
//        h[i]= aht[ntab]/(aht[ntab]+bht[ntab]);
//        j[i]= ajt[ntab]/(ajt[ntab]+bjt[ntab]);
//        d[i]= adt[ntab]/(adt[ntab]+bdt[ntab]);
//        f[i]= aft[ntab]/(aft[ntab]+bft[ntab]);
                                  }
                       }

        nx=izoom;
      int valuee=horiz.getValue();
       valueee=(double)(valuee);
       dt=dtt/valueee;


         if(nstop < 5){

         double x=0.0;
         double s=0.0;

         ntime=(int)(time);
         for(int i=0; i< ntime; i++){
          

           for(int k=0; k<50; k++){  
              if(ndtime==1){dtime=dtime+dt;}
            
            if(stimsen==1){ncounter=ncounter+1;
                           if(ncounter > nstimdur){stimsen=0;}
                           }
                           
  
        for(int ii=1; ii<nx-1; ii++){ 

         if(ii > nxs1i && ii < nxs1f && stimsen == 1){stimsenx=1;}
         else{stimsenx=0;}

        tab=((u[ii]-vmin)/dvt);
        ntab=(int)(tab);

        m[ii]= m[ii]+dt*(amt[ntab]*(1.0-m[ii])-bmt[ntab]*m[ii]);
        h[ii]= h[ii]+dt*(aht[ntab]*(1.0-h[ii])-bht[ntab]*h[ii]);
        n[ii]= n[ii]+dt*(ant[ntab]*(1.0-n[ii])-bnt[ntab]*n[ii]);


         ik2=gk2*n[ii]*n[ii]*n[ii]*n[ii];

        ina=(gna1*m[ii]*m[ii]*m[ii]*h[ii]+gna2)*(u[ii]-40);
        ik=(ik2+ik1t[ntab])*(u[ii]+100.);
         xlap=u[ii-1]+u[ii+1]-2.*u[ii];
        ut[ii]=u[ii]-dt*(ina+ik+stimsenx*Istim)/12.0+Dlap*dt*xlap/dx2;

                               
                                       }
// left edge 
//
         tab=((u[0]-vmin)/dvt);
         ntab=(int)(tab);

        m[0]= m[0]+dt*(amt[ntab]*(1.0-m[0])-bmt[ntab]*m[0]);
        h[0]= h[0]+dt*(aht[ntab]*(1.0-h[0])-bht[ntab]*h[0]);
        n[0]= n[0]+dt*(ant[ntab]*(1.0-n[0])-bnt[ntab]*n[0]);


         ik2=gk2*n[0]*n[0]*n[0]*n[0];

        ina=(gna1*m[0]*m[0]*m[0]*h[0]+gna2)*(u[0]-40);
        ik=(ik2+ik1t[ntab])*(u[0]+100.);
         if(ncable==1){
         xlap=2.*u[1]-2.*u[0];
                      }
         else{
          xlap=u[nx-2]+u[1]-2*u[0];
             }
        ut[0]=u[0]-dt*(ina+ik+stimsenx*Istim)/12.0+Dlap*dt*xlap/dx2;




// rigth edge 
         mx=nx-1;
        tab=((u[mx]-vmin)/dvt);
        ntab=(int)(tab);

        m[mx]= m[mx]+dt*(amt[ntab]*(1.0-m[mx])-bmt[ntab]*m[mx]);
        h[mx]= h[mx]+dt*(aht[ntab]*(1.0-h[mx])-bht[ntab]*h[mx]);
        n[mx]= n[mx]+dt*(ant[ntab]*(1.0-n[mx])-bnt[ntab]*n[mx]);


        ik2=gk2*n[mx]*n[mx]*n[mx]*n[mx];

        ina=(gna1*m[mx]*m[mx]*m[mx]*h[mx]+gna2)*(u[mx]-40);
        ik=(ik2+ik1t[ntab])*(u[mx]+100.);
         if(ncable==1){
         xlap=2.*u[mx-1]-2.*u[mx];
                       }
          else{
          xlap=u[nx-2]+u[1]-2*u[nx-1];
              }
        ut[mx]=u[mx]-dt*(ina+ik+stimsenx*Istim)/12.0+Dlap*dt*xlap/dx2;



           for(int ii=0; ii<nx; ii++){
          u[ii]=ut[ii];
                                       }
           if(ncable==0){
        m[nx-1]= m[0];
        h[nx-1]= h[0];
        n[nx-1]= n[0];
        u[nx-1]=u[0];
        ut[nx-1]=ut[0];

                        }

           
          x+=dt;
              }
          
            
           if(i%6+1 ==1){

          gBuffer.setColor(Color.black);
          gBuffer.fillRect(0,30,520,230);
          gBuffer.setColor(Color.white);
//     gBuffer.drawString("zoom"+String.valueOf(izoom),400,85);

          gBuffer.drawLine(20,40,20,230);
          gBuffer.drawLine(20,230,520,230);
          gBuffer.drawString("-85",2,240);
          gBuffer.drawString("mV",1,140);
          gBuffer.drawString("30",2,50);
          gBuffer.drawString("time="+String.valueOf((float)(dtime))+"ms",400,55);
          gBuffer.drawString("Length of cable: "+String.valueOf((float)(izoom*dx))+" cm ",188,249);
          gBuffer.drawString("<---------------------| ",20,247);
          gBuffer.drawString("|---------------------> ",356,247);
          xpos=80;
          if(vsen==10){
          gBuffer.setColor(Color.red);
           gBuffer.drawString("V",xpos,55);
           xpos=xpos+20;
           for(int ii=0; ii<nx-1; ii=ii+1){
           int yy1=(int)(105-u[ii]*1.4);
           int yy2=(int)(105-u[ii+1]*1.4);
           double xx11=ii*501.0/nx;
           double xx22=(ii+1.)*501.0/nx;
           int xx1=(int)Math.ceil(xx11);
           int xx2=(int)Math.ceil(xx22);
           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
                                           }
                       }
          if(msen==10){
          gBuffer.setColor(new Color(12,100,12));
            gBuffer.drawString("m",xpos,55);
            xpos=xpos+20;
           for(int ii=0; ii<nx-2; ii=ii+2){
           int yy1=(int)(230-m[ii]*170);
           int yy2=(int)(230-m[ii+2]*170);
           int xx1=(int)(ii*500/nx);
           int xx2=(int)((ii+2)*500/nx);
           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
                                          }
                       }
          if(hsen==10){
          gBuffer.setColor(Color.blue);
            gBuffer.drawString("h",xpos,55);
            xpos=xpos+20;
           for(int ii=0; ii<nx-2; ii=ii+2){
           int yy1=(int)(230-h[ii]*170);
           int yy2=(int)(230-h[ii+2]*170);
           int xx1=(int)(ii*500/nx);
           int xx2=(int)((ii+2)*500/nx);
           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
                                          }
                       }
          if(jsen==10){
          gBuffer.setColor(Color.orange);
            gBuffer.drawString("n",xpos,55);
            xpos=xpos+20;
           for(int ii=0; ii<nx-2; ii=ii+2){
           int yy1=(int)(230-n[ii]*170);
           int yy2=(int)(230-n[ii+2]*170);
           int xx1=(int)(ii*500/nx);
           int xx2=(int)((ii+2)*500/nx);
           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
                                          }
                       }
//          if(xsen==10){
//          gBuffer.setColor(new Color(128,128,0));
//            gBuffer.drawString("x1",xpos,55);
//            xpos=xpos+20;
//           for(int ii=0; ii<nx-1; ii=ii+2){
//           int yy1=(int)(230-x1[ii]*170);
//           int yy2=(int)(230-x1[ii+2]*170);
//           int xx1=(int)(ii*500/nx);
//           int xx2=(int)((ii+2)*500/nx);
//           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
//                                          }
//                       }
//          if(dsen==10){
//          gBuffer.setColor(Color.orange);
//            gBuffer.drawString("d",xpos,55);
//            xpos=xpos+20;
 //          for(int ii=0; ii<nx-1; ii=ii+2){
//           int yy1=(int)(230-d[ii]*170);
//           int yy2=(int)(230-d[ii+2]*170);
 //          int xx1=(int)(ii*500/nx);
//           int xx2=(int)((ii+2)*500/nx);
//           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
//                                          }
 //                      }
//          if(fsen==10){
//          gBuffer.setColor(Color.magenta);
//            gBuffer.drawString("f",xpos,55);
//            xpos=xpos+20;
//           for(int ii=0; ii<nx-1; ii=ii+2){
//           int yy1=(int)(230-f[ii]*170);
//           int yy2=(int)(230-f[ii+2]*170);
//           int xx1=(int)(ii*500/nx);
//           int xx2=(int)((ii+2)*500/nx);
//           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
//                                          }
 //                      }
 //         if(casen==10){
 //         gBuffer.setColor(Color.cyan);
//            gBuffer.drawString("[Ca]",xpos,55);
//            xpos=xpos+20;
//           for(int ii=0; ii<nx-1; ii=ii+2){
//           int yy1=(int)(230-ca[ii]*9000000*2.7);
//           int yy2=(int)(230-ca[ii+2]*9000000*2.7);
//           int xx1=(int)(ii*500/nx);
//         int xx2=(int)((ii+2)*500/nx);
//           gBuffer.drawLine(xx1+20,yy1,xx2+20,yy2);
//                                         }
//                       }
            g.drawImage(Buffer,0,0, this);


                          }  

                                 }
                   } //closes if nstop
             }
	}

  


        public void itemStateChanged( ItemEvent ae){
         if(checkm.getState()==true){ msen=10;}
         else{ msen=0;}
         if(checkv.getState()==true){ vsen=10;}
         else{vsen=0;}
         if(checkh.getState()==true){ hsen=10;}
         else{ hsen=0;}
         if(checkn.getState()==true){ jsen=10;}
         else{jsen=0;}
         if(checkf.getState()==true){ fsen=10;}
         else{ fsen=0;}
         if(checkd.getState()==true){ dsen=10;}
         else{dsen=0;}
         if(checkx.getState()==true){ xsen=10;}
         else{ xsen=0;}
         if(checkca.getState()==true){ casen=10;}
         else{casen=0;}

          if(ae.getItem().toString()== optionc){ncable=1;}
          if(ae.getItem().toString()== optionr){ncable=0;}



                                                     }

        public void  actionPerformed( ActionEvent ae)
	{

//            a= Double.valueOf(input1.getText()).doubleValue();
            gna= Double.valueOf(input2.getText()).doubleValue();
            gnac= Double.valueOf(input3.getText()).doubleValue();
            gs= Double.valueOf(input4.getText()).doubleValue();
            s2= Double.valueOf(input5.getText()).doubleValue();
            s1=Double.valueOf(inputs1.getText()).doubleValue();
          time=Double.valueOf(inputtime.getText()).doubleValue();
           Istim=Double.valueOf(sstrengtht.getText()).doubleValue();
           tstim=Double.valueOf(sdurationt.getText()).doubleValue();
           lstim=Double.valueOf(slengtht.getText()).doubleValue();
           xstim=Double.valueOf(spositiont.getText()).doubleValue();

          nt=(int)(time/651);
          nt=nt+1;

//	   input1.setText(input1.getText());
           input2.setText(input2.getText());
	   input3.setText(input3.getText());
           input4.setText(input4.getText());
	   input5.setText(input5.getText());
           inputs1.setText(inputs1.getText());
           inputtime.setText(inputtime.getText());


            if(ae.getSource()==bstimulus){
        bstimulus.setBackground(Color.lightGray);


            ndtime=1;
            stimsen=1;
            ncounter=0;
            double stimdur=tstim/dt;
            nstimdur=(int)(stimdur);
            double xs1=xstim/dx;
            nxs1=(int)(xs1);
            double xls1=lstim/dx/2.;
            nxs1i=(int)(xs1-xls1);
            nxs1f=(int)(xs1+xls1);
            if(nxs1i < 0){nxs1i = 0;}
            if(nxs1f > nx){nxs1f=nx;}
            
            
                                         }


           if(ae.getSource()==butdown){
            nx=nx+10;
            if(nx > 801){nx=801;}
           inputs1.setText(String.valueOf(nx*dx));
       for(int i=nx-10; i<nx; i++) {
        u[i]=u[nx-11];
        ca[i]=ca[nx-11];
        m[i]= m[nx-11];
        h[i]= h[nx-11];
        n[i]= n[nx-11];
                                  }

                                     }

           if(ae.getSource()==butup){
            nx=nx-10;
            if(nx < 51){nx=51;}
           inputs1.setText(String.valueOf(nx*dx));
                                     }



           if(ae.getSource()==butp){
            if(firsttime2){
//                butp.setLabel("Stop");
//              firsttime2=false;
                          }
             else{

                if(butp.getLabel().equals("Continue")){
                  butp.setLabel("Stop");
                  nstop=1;
                                                   }
                 else{
                  butp.setLabel("Continue");
                  nstop=10;
                                                    } 
                   }

//            repaint();
                                   }
          
           if(ae.getSource()==butt){
            
          if(butt.getLabel().equals("Start")){
//         butt.setTextColor(Color.black);
        butt.setBackground(Color.lightGray);
        bstimulus.setBackground(Color.green);

           butt.setLabel("Reset");
          firsttime=false;
            butp.setLabel("Stop");
            firsttime2=false;
//	  repaint();
                                              }
          else{
           butt.setLabel("Reset");
//           firsttime=true;
         dtime=0;
         ndtime=0;
         stimsen=0;
         a=0.1;
         gna=4.0;
         gnac=0.003;
         gs=0.09;
         s2=320;
         gama=1;
         b=0.5;
         eps=0.01;
         delta=0;
         time=5;
         s1=20;
         nt=1;
         nx=501;
//	   input1.setText("0.1");
           input2.setText("1.2");
	   input3.setText("400");
           input4.setText("0.12");
           input5.setText("320");
           inputs1.setText(String.valueOf(nx*dx));
           inputtime.setText("5");
        sstrengtht.setText("-35.0");
        sdurationt.setText("30");
        slengtht.setText("0.5");
        spositiont.setText("0.25");

//           repaint();
        for(int i=0; i<nx; i++) {
        u[i]=-84.5737;
        tab=((u[i]-vmin)/dvt);
        ntab=(int)(tab);

        m[i]= amt[ntab]/(amt[ntab]+bmt[ntab]);
        h[i]= aht[ntab]/(aht[ntab]+bht[ntab]);
        n[i]= ant[ntab]/(ant[ntab]+bnt[ntab]);
                                  }
//        for(int ii=0; ii< 20; ii++){u[ii]=-50;}






              }
                                    } 
         repaint();
        }

	public void run() {
	 try {
           while(true){
            Thread.sleep(nspeed);
	    if(!down) {






		repaint();
		}
	    }
	   }
	   catch(Exception e){
	   }
	  }


        }









