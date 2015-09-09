


 	import java.awt.*;
	import java.applet.Applet;
	import java.awt.event.*;
        import java.lang.*;
        import java.text.*; 

        /*
         <applet code="threev0d" width=450 height=350>
         </applet>
          */

	public class threev0d extends Applet 
//         implements ActionListener, ItemListener {
         implements   ActionListener, ItemListener {
        
//         private Panel buttonPanel, checkPanel;
//	 private Label prompt1, prompt2, prompt3, prompt4, prompt5;
//         private Label promptg,promptgg,promptg2,promptg3;
//	 private TextField input1, input2, input3, input4, input5;
 //        private Label prompts1, prompttime;
//	 private TextField  inputs1, inputtime;
//	 private double result;
//	Button butt,butp;
         private Panel buttonPanel, checkPanel;
  private Label prompt0, prompt1, prompt2, prompt3, prompt4;
  private Label prompt5, prompt6, prompt7;
    	 private Label promptNS1, promptS2, promptS1;
	 private TextField inputNS1, inputS2, inputS1;
     
	 private TextField input1, input2, input3, input4, input5;
	 private double result;
	Button butt,butp;
        boolean firsttime= true;
        double a=0.1;
        double gama=1;
        double b=0.5;
        double eps=0.01;
        double delta=0;
     
//-----------stimulation stuff-----
        double stims1,stims2,sdur,xnstims1,xnstims2,ttime;
         int   nswitch,ncounts1,ns1s;
         int napdd=0;
         double apdtime[] = new double[10];
         double APDD,DII;
         double ut,Itot;

//--------------------------

        double  zindexv,q1,q2,vv,dvt;

        NumberFormat nf = NumberFormat.getInstance(); 
        Color  background,background2;

        double vlo=-.2,vhi=2.,vmeklo=-10.,clo=0.00001,chi=0.00201;
        int nvt=17000,nct=20000;
        double tsot[]   = new double[nvt];
        double twmmt[]  = new double[nvt];
        double tanhs[]  = new double[nvt];
        double tanhxa;

//-----variables-------
        double   u=0.0;
        double   v=1;
        double   w=1;
        double   s=0;
        double   f=0;
        double  qq=0;
        double  pp=0;

//-----------graphh-------------------------------
        DataWin da; 
        Graphs sg13;
	Button bgraph,bgraph2;  //graphh
        Frame frame;           //graphh
        Checkbox plot_xtotal;
        Checkbox dplot_vt;



	

        float t_of_t[] = new float[90010];  
        float v_of_t[] = new float[90010];
        float xtotal_of_t[] = new float[90010];

        int nt_of_t=-1;
        int ksen=0;
        int ksen2;
        int igraph=0;
        double xtotal;
        String optionepi="Beeler-Reuter";
        String optionendo="MBR";
        String optionm="Luo-Rudy1";





//c --- Unknown parameters ---

//c SOME CORRECTIONS
        double up=1.3000000e-001;
        double tvp=1.4505802;
        double tsm=2.7342505;
        double us=  9.0868279e-001;
        double xk=  2.0994107;
        double tsii=  1.6118516;
        double xarg;

        double tvm2;
        double uo;
        double tfi;
        double uu;
        double um;
        double tvm1;
        double to;
        double twma;
        double twmb;
        double uwm;
        double xkwm;
        double uq;
        double tsp;
        double tsi;
        double twp;
        double tsoa;
        double tsob;
        double uso;
        double xtso;

        double twm;
        double td;
        double tr;
        double ucsi;
        double uc;
        double uv;

         double dv,dw,ds,m,p,vinf,winf,q,xfi,xso,xsi;
         double too,twmm,tso,ts,tvm;
// variables
        double svoltt,svolt,svolt2,Cai,CaSR,Nai,Ki,sItot;
        double sm,sh,sj,sxr1,sxr2,sxs,ss,sr,sd,sf,sfca,sg;
        double dvdt;
// currents
        double IKr,IKs,IK1,Ito,INa,IbNa,ICaL,IbCa,INaCa,IpCa;
        double IpK,INaK,Irel,Ileak,Istim;


        double  stimdur=1.0,stimstrength=-.5,cyclelength=500.0;
        
        double  HT,endtime,RTONF,dt;
      int     iendtime,ixs11,ixs12,ixs13,ixs14,ixs15,ixs16,ixs17,ixs18;
      int     ixs19,ixs110,ixs21;


        double  cc2,cc4,cc6,cc8,cc16,tempc;

         int   ntime,nend,istimdur,icycle,itype,iv1,iv2,indexv,i;
         int   ivmek1,ivmek2,indexvmek,ic1,ic2,indexc;
         int   skip,ncount;

//-------------------------------------

      Checkbox  checkvolt;
      Checkbox  checkv;
      Checkbox  checkw;
 //     Checkbox  checks;

      int  senvolt=10;
      int  senv=0;
      int  senw=0;
      int  sens=0;




//-------------init----------
//        public void init() {
//    background = new Color (192, 192, 192);
//    background2 = new Color (110, 159, 86);

        public void init() {
       setSize(580,350);
       background= new Color(192,192,192);
       setBackground(background);
       background2 = new Color (141, 54, 63);


// set itype=1 for epi, =2 for M, =3 for endo
         itype=1;


// numerical parameters

      HT=0.1;
      dt=HT;
//      endtime=1700.0;
//      nend=(int)(endtime/HT);
      istimdur=(int)(stimdur/HT);
      icycle=(int)(cyclelength/HT);

//-----------stimulation stuff-----
         stims1=470;
         stims2=470;
//         nswitch=0;
//         ncounts1=1;
         ns1s=5;
//         sdur=stimdur;
//         xnstims1=stims1+sdur;
//         xnstims2=stims1*ns1s+stims2+sdur;
//         endtime=xnstims2+400;
//         nend=(int)(endtime/HT);

         ttime=0;
         nswitch=0;
         ncounts1=1;
         napdd=0;
         nt_of_t=-1;
         int mmtime=0;
         nswitch=0;
         ncounts1=1;
         sdur=stimdur;
         xnstims1=stims1+sdur;
         xnstims2=stims1*(ns1s-1)+stims2+sdur;
          if(itype==3){
         endtime=xnstims2+700; 
                       }
          else{
         endtime=xnstims2+400; }

         nend=(int)(endtime/dt);
         DII=0;
         APDD=0;

         
//--------------------------

// useful values


// initial values
      sm= 0.;
      sh= 0.75;
      sj= 0.75;
      sxr1= 0.;
      sxr2= 1.;
      sxs= 0.;
      sr= 0.;
      ss= 1.;
      sd= 0.;
      sf= 1.;
      sfca= 1.;
      sg= 1.;
//=============================================================
//=================================================================






        buttonPanel = new Panel();
	butt = new Button ("Start");
        butt.addActionListener( this );

        butt.setBackground(Color.green);
	   promptNS1= new Label("  N (s1's)");
	   inputNS1= new TextField(3);
           inputNS1.addActionListener( this);
	   promptS1= new Label("  S1 (bcl)");
           inputS1= new TextField(3);
	   inputS1.addActionListener( this);
           promptS2=new Label ("  S2 (bcl)");
           inputS2= new TextField(3);
           inputS2.addActionListener( this); 
	   inputNS1.setText("3");
           inputS1.setText("350");
	   inputS2.setText("700");

           buttonPanel.setLayout(new GridLayout(1,7));
           buttonPanel.add(butt);
           buttonPanel.add(promptNS1);
           buttonPanel.add(inputNS1);
           buttonPanel.add(promptS1);
           buttonPanel.add(inputS1);
           buttonPanel.add(promptS2);
           buttonPanel.add(inputS2);

       checkPanel = new Panel();
       
       butp = new Button("Replot");
       butp.addActionListener(this );
       prompt0 = new Label ("   ");
       prompt1 = new Label ("   ");
       prompt2 = new Label ("   ");
       prompt3 = new Label ("   ");
       prompt4 = new Label ("   ");
       prompt5 = new Label ("   ");
       prompt6 = new Label ("   ");

     
      checkvolt= new Checkbox("Voltage", true);
      checkv = new Checkbox("v gate", false);
      checkw = new Checkbox("f gate", false);
//      checks = new Checkbox("s gate", false);

      checkvolt.addItemListener(this); 
      checkv.addItemListener(this); 
      checkw.addItemListener(this); 
//      checks.addItemListener(this); 


           checkPanel.setLayout(new GridLayout(6,2));
           checkPanel.add(butp);
           checkPanel.add(prompt0);
           checkPanel.add(checkvolt);
           checkPanel.add(prompt1);

           checkPanel.add(checkv);
           checkPanel.add(prompt2);

           checkPanel.add(checkw);
           checkPanel.add(prompt3);

//           checkPanel.add(checks);
//           checkPanel.add(prompt4);

           checkPanel.add(prompt5);
           checkPanel.add(prompt6);



//      graphh
	bgraph = new Button ("Current & ascii");
        bgraph.addActionListener( this );
        add(bgraph);
        bgraph.reshape(250,295,155,28);
//        bgraph.setBackground(background2);


//  graphh



        prompt7 = new Label ("Model fitted to:-- >");
        prompt7.setForeground(background2);
        add(prompt7);
        prompt7.reshape(8,295,100,32);
        Choice options= new Choice();
        options.addItem("Beeler-Reuter");
        options.addItem("MBR");
        options.addItem("Luo-Rudy1");
        options.addItemListener(this);
        add(options);
        options.setBackground(background2);
        options.reshape(120,295,110,28);




           setLayout(new BorderLayout() );
            add(checkPanel, BorderLayout.EAST);
            add(buttonPanel, BorderLayout.NORTH);

        }   //end init


	public void paint(Graphics g) {

          g.setColor(Color.blue);
          g.drawRect(0,35,431,256);
          g.setColor(Color.black);
          g.fillRect(1,36,430,255);

          g.setColor(Color.white);
          g.drawLine(30,10+35,30,220+35);
          g.drawLine(30,220+35,420,220+35);
          g.drawString("Time (ms)",185,245+40);
          g.drawString("V",13,120+35);
          g.drawString("(mV)",4,130+35);
        if(firsttime){
                      }
        else{ 



          iendtime=300;
          ixs11=31;
          ixs21=(int)(xnstims2*395/endtime)+32;
          double qstims1=stims1*395/endtime;
 

          g.drawString("S1",ixs11-3,270);
          g.drawString("S2",ixs21-3,270);
          g.drawString("|",ixs11,260);
          g.drawString("|",ixs21,260);
    String finaltime=nf.format((double)(endtime/1000)); 
    g.drawString("Total Time="+String.valueOf(finaltime)+" sec.",iendtime-15,285);

///------------letters of gates plotted on screen--------------
        int  xpos=75;

        if(senvolt==10){
            g.setColor(Color.red);
            g.drawString("V,",xpos,60);
            xpos=xpos+23;
                       }
        if(senw==10){
            g.setColor(Color.orange);
            g.drawString("f",xpos,60);
            xpos=xpos+23;
                     }
        if(senv==10){
            g.setColor(Color.cyan);
            g.drawString("v",xpos,60);
            xpos=xpos+23;
                      }
   //     if(sens==10){
   //         g.setColor(Color.blue);
   //         g.drawString("s",xpos,60);
   //         xpos=xpos+23;
   //                 }



      if(itype==1){
        tvp=3.33;
        tvm1=1250.0; 
        tvm2=19.6;
        twp=870.0;
        twm=41.0;
        td=0.25;
        to=12.5;
        tr=33.33;
        tsi=29;
        xk=10.0;
        ucsi=0.85;
        uc=0.13;
        uv=0.04;

//        tvm2=1150;
//        uo=0;
//        tfi=0.11;
//        uu=1.58;
//        um=0.3;
//        tvm1=60;
//        to=6.0;
//        twma=60.0;
//        twmb=12.5;
//        uwm=.03;
//        xkwm=65;
//        uq=0.007;
//        tsp=14;
//        tsi=tsii*0.99;
//        twp=265;
//        tsoa=  6.7058536e+001;
//        tsob=  1.0001404e-001;
//        uso=1.5582037e-003;
//        xtso=  1.3151681e+000;
                    }

      if(itype==2){
        tvp=3.33;
        tvm1=1000.0;
        tvm2=19.2;
        twp=667.0;
        twm=11.0;
        td=0.25;
        to=8.3;
        tr=50;
        tsi=44.84;
        xk=10.0;
        ucsi=0.85;
        uc=0.13;
        uv=0.055;
                 } 

      if(itype==3){
       tvp=10.0;
       tvm1=18.2; 
       tvm2=18.2;
       twp=1020.0; 
       twm=80.0;
       td=0.172;
       to=12.5;
       tr=130;
       tsi=127;
       xk=10.0;
       ucsi=0.85;
       uc=0.13;
       uv=0.04;
                  } 


//-----------  table -----------

      dvt=(vhi-vlo)/nvt;

      for(int i=0; i<nvt; i++){
         vv=vlo+dvt*(double)(i);

        xarg=(vv-uso)*xtso;
        tsot[i]=tsoa+(tsob-tsoa)*(1+
       (Math.exp(xarg)-Math.exp(-xarg))/
       (Math.exp(xarg)+Math.exp(-xarg)))/2;


        if(itype==2 && vv > 1){
         twmmt[i]=140;        }
        else{

        xarg=(vv-uwm)*xkwm;

//        aaa1=dexp(yarg)-dexp(-yarg)
//        bbb1=dexp(yarg)+dexp(-yarg)
        twmmt[i]=twma+(twmb-twma)*(1+
       (Math.exp(xarg)-Math.exp(-xarg))/
       (Math.exp(xarg)+Math.exp(-xarg)))/2;
             }


        xarg=(vv-us)*xk;
        tanhs[i]=(1+
       (Math.exp(xarg)-Math.exp(-xarg))/
       (Math.exp(xarg)+Math.exp(-xarg)))/2;
            
                               }  //end table


//        g.setColor(Color.blue);
//          g.drawRect(0,35+35,290,185);
//          g.setColor(Color.black);
//          g.drawLine(30,40+35,30,180+35);
//          g.drawLine(30,180+35,290,180+35);
//          g.drawString("Time",140,195+35);
//          g.drawString("V",13,110+35);
//          g.drawString("(mV)",5,120+35);
         int volto=245;
         int xo=34;
         int senwo=245-106;
         int senvo=245-106;
         int senso=245;


               u=0.00;
               v=1;
               w=1;
               s=0;
               f=1;
//c              dt=.1
//c              t=0

         ttime=0;
         nswitch=0;
         ncounts1=1;
         napdd=0;
         nt_of_t=-1;
         int mmtime=0;
         nswitch=0;
         ncounts1=1;
         sdur=stimdur;
         xnstims1=stims1+sdur;
         xnstims2=stims1*(ns1s-1)+stims2+sdur;
          if(itype==3){
         endtime=xnstims2+700; 
                      }
          else{
         endtime=xnstims2+400; }

         nend=(int)(endtime/dt);
         DII=0;
         APDD=0;


        for(int ntime=1; ntime<nend; ntime++){
           mmtime=mmtime+1;
           ttime=ttime+dt;

           Istim=0.0;
           if(ttime >= xnstims1-sdur && ttime <= xnstims1){
           Istim=stimstrength;
           nswitch=1;
            skip=1;
            ncount=0;

         if(ns1s < 13){

          ixs11=(int)(xnstims1*395/endtime)+34;
          g.setColor(Color.white);
          g.drawString("S1",ixs11-5,270);
          g.drawString("|",ixs11,260);
                       }

                                                           }
           if(ttime >= xnstims1 && ncounts1 < ns1s-1 && nswitch==1){
           nswitch=0;
           ncounts1=ncounts1+1;
           xnstims1=ncounts1*stims1+sdur;
            skip=1;
            ncount=0;

//           write(6,*)ncounts1,xnstims1
                                                                 } 
           if(ttime >= xnstims2-sdur && ttime < xnstims2){
           Istim=stimstrength;
             skip=1;
            ncount=0;

                                                         }
//c        if(ntime<40){
         if(ttime < sdur+.1){
            skip=1;
            ncount=0;
           Istim=stimstrength*1.0;
                   }



//-----------skip check for speedup------------------
         ncount=ncount+1;
         if(ncount==skip){
         ncount=0;


// find indices into tables
         zindexv=((svolt-vlo)/dvt);
         indexv=(int)(zindexv);
            iv1=indexv;

            p=0.0;
            qq=0.0;
            pp=0.0;
            if(u > uc)p=1.0;
            if(u > uv)qq=1.0;
            if(u > uc) {
               v=v/(1.0+dt/tvp);
               f=f/(1.0+dt/twp);
                        }
            else{
               tvm=(1.0-qq)*tvm1+qq*tvm2;
               v=(v+dt/tvm)/(1.0+dt/tvm);
               f=(f+dt/twm)/(1.0+dt/twm);
                         }
            
            xfi=-v*p*(u-uc)*(1.0-u)/td;
            xso=u*(1.0-p)/to+p/tr;
 
            xarg=xk*(u-ucsi);
            tanhxa=(Math.exp(xarg)-Math.exp(-xarg))/
                   (Math.exp(xarg)+Math.exp(-xarg));


            xsi=-f*(1.0+tanhxa )/(2.0*tsi);

             Itot=xfi+xso+xsi+Istim;
            ut=u-HT*(Itot);

          if(ttime > stims1*(ns1s-1)+sdur){
        
          if((ut-.25)*(u-.25)< 0){
            napdd=napdd+1;
            apdtime[napdd]=ttime;
                                        } 
             
           if(napdd==3){DII=apdtime[2]-apdtime[1];
                        APDD=apdtime[3]-apdtime[2];
                       }
                                        }

             u=ut;


           svolt=u; 

         if(mmtime==10){
            mmtime=0;
          int xf=(int)(35+ttime*395/(endtime));

        if(senvolt==10){
          int voltf=(int)((245-svolt*106));
            g.setColor(Color.red);
            g.drawLine(xo,volto,xf,voltf);
           volto=voltf;
                       }

        if(senw==10){
          int senwf=(int)((245- f*106));
            g.setColor(Color.orange);
            g.drawLine(xo, senwo,xf, senwf);
            senwo= senwf;
                       }
        if(senv==10){
          int senvf=(int)((245- v*106));
            g.setColor(Color.cyan);
            g.drawLine(xo, senvo,xf, senvf);
            senvo= senvf;
                       }
        if(sens==10){
          int sensf=(int)((245-s*106));
            g.setColor(Color.blue);
            g.drawLine(xo,senso,xf,sensf);
            senso= sensf;
                       }


           xo=xf;

//---------------graphhh

        if(nt_of_t<90000){

         nt_of_t=nt_of_t+1;
         xtotal_of_t[nt_of_t] = (float)(Itot);
         v_of_t[nt_of_t]=(float)(u);
         t_of_t[nt_of_t]=(float)(ttime);
                          }

                         }


                            } //end skip   


//    nf.setMaximumFractionDigits(2); 
//    String ass=nf.format((double)(ttime)); 
//    g.setColor(Color.magenta);
//    g.drawString("time="+String.valueOf(ass),270,75);
//    String ast=nf.format((double)(xnstims1-sdur)); 
//    g.drawString("time="+String.valueOf(ast),270,85);
//    String asy=nf.format((double)(xnstims1)); 
//    g.drawString("time="+String.valueOf(asy),270,95);

       }   //enddo of time iteration


       int xdii=(int)(35+apdtime[1]*395/endtime);
       int xdif=(int)(35+apdtime[2]*395/endtime);
       int xapdi=(int)(35+apdtime[2]*395/endtime);
       int xapdf=(int)(35+apdtime[3]*395/endtime);
       int xbasee=(int)(130+78*1.3);

    nf.setMaximumFractionDigits(2); 
    String asss=nf.format((double)(APDD)); 
    g.setColor(Color.magenta);
    g.drawString("APD="+String.valueOf(asss)+"ms",300,55);
    g.drawLine(xapdi,xbasee,xapdf,xbasee);

    String astt=nf.format((double)(DII)); 
    g.setColor(Color.yellow);
    g.drawString("DI ="+String.valueOf(astt)+"ms",300,70);
    g.drawLine(xdii,xbasee,xdif,xbasee);

        }//  end if firstime

       } //end graph



        public void itemStateChanged( ItemEvent ae){

         if(checkvolt.getState()==true){ senvolt=10;}
         else{senvolt=0;}
         if(checkv.getState()==true){senv =10;}
         else{senv =0;}
         if(checkw.getState()==true){senw =10;}
         else{senw =0;}
//         if(checks.getState()==true){sens =10;}
//         else{sens =0;}

          if(ae.getItem().toString()== optionepi){
         itype=1;
          repaint();

                                                  }

          if(ae.getItem().toString()== optionendo){
         itype=2;
           repaint();

                                                  }


          if(ae.getItem().toString()== optionm){
         itype=3;
          repaint();
                                                  }

//--------------for plotting currents-------------------
//------------------I_total----------------

    if(plot_xtotal.getState()==true) { 
      if(sg13 == null) { 

    float nnt_max=ntime;
          if(nnt_max>=90000)nnt_max=90000;
    float nnt_min=0;


        if(nt_of_t>=90000)nt_of_t=90000;


    float data[] = new float[nt_of_t];
    float datat[] = new float[nt_of_t];

         int jjj=-1;
     for(int i=0; i < nt_of_t; i++) {
        jjj=jjj+1;
        data[jjj]=xtotal_of_t[i];
        datat[jjj]=t_of_t[i];                      }  

        sg13 = new Graphs(data,datat,Graphs.TOTAL,jjj); 
        sg13.addWindowListener(new WindowAdapter() { 
          public void windowClosing(WindowEvent we) { 
            plot_xtotal.setState(false); 
            sg13 = null; 
          } 
        }); 
      } 
    } 
    if(plot_xtotal.getState()==false) { 
      if(sg13 != null) { 
        sg13.dispose(); 
        sg13 = null; 
      } 
    } 

    if(dplot_vt.getState()==true) { 
      if(da == null) { 

    float data[] = new float[nt_of_t];
    float datat[] = new float[nt_of_t];

         int jjj=-1;
     for(int i=0; i < nt_of_t; i++) {
//       if(t_of_t[i]>=nnt_min && t_of_t[i]<=nnt_max){
        jjj=jjj+1;

    nf.setMaximumFractionDigits(2); 
    float  valuenff=(float)(v_of_t[i]*100);
    int    valueni=(int)(valuenff);
    float  valuenf=(float)(valueni)/100;
        data[jjj]=valuenf;

//        data[jjj]=v_of_t[i];
        datat[jjj]=t_of_t[i];                      }
//                                      }

//        da = new DataWin(data,datat,jjj); 
        da.addWindowListener(new WindowAdapter() { 
          public void windowClosing(WindowEvent we) { 
            dplot_vt.setState(false); 
            da = null; 
          } 
        }); 
      } 
    } 
    else { 
      if(da != null) { 
        da.dispose(); 
        da = null; 
      } 
    }  





                                            } //end of itemevent changed


        public void  actionPerformed( ActionEvent ae)
	{

            stims1= Double.valueOf(inputS1.getText()).doubleValue();
            ns1s= Double.valueOf(inputNS1.getText()).intValue();
            stims2= Double.valueOf(inputS2.getText()).doubleValue();
         nswitch=0;
         ncounts1=1;
         sdur=stimdur;
         xnstims1=stims1+sdur;
         xnstims2=stims1*(ns1s-1)+stims2+sdur;
          if(itype==3){
         endtime=xnstims2+700; }
          else{
         endtime=xnstims2+400; }
         nend=(int)(endtime/dt);
         ttime=0;

	   inputS1.setText(inputS1.getText());
           inputS2.setText(inputS2.getText());
	   inputNS1.setText(inputNS1.getText());

//---------------------graphhh----------------------------
           if(ae.getSource()==bgraph2){
             frame.dispose();
//            butp.setLabel("Stop");
                                       }

          if(ae.getSource()==bgraph){
           frame= new Frame();
           frame.setLayout(null);
           frame.setResizable(true);
           frame.setTitle("Currents");
           frame.reshape(10,10,160,225);

           bgraph2 = new Button ("Dismiss Window");
           bgraph2.addActionListener( this );
           frame.add(bgraph2);
           bgraph2.reshape(10,65,140,35);
       plot_xtotal= new Checkbox("Plot  I_total", false);
       plot_xtotal.addItemListener(this );
       frame.add(plot_xtotal);
       plot_xtotal.reshape(20,115,110,40);

       dplot_vt= new Checkbox("Ascii data (t,V)", false);
       dplot_vt.addItemListener(this );
       dplot_vt.setForeground(background2);
       frame.add(dplot_vt);
        dplot_vt.reshape(20,160,110,40);       




//            frame.setBackground(background2);
//           frame.repaint();
           frame.show();

                                    }


//------------graphh----------------------------------


          
           if(ae.getSource()==butt){
            
          if(butt.getLabel().equals("Start")){
        butt.setBackground(background);
           butt.setLabel("Reset");

      if(sg13 != null) { 
        sg13.dispose(); 
        sg13 = null; 
      } 
      if(frame != null) { 
        frame.dispose(); 
        frame = null; 
      } 
      if(da != null) { 
        da.dispose(); 
        da = null; 
      } 


          firsttime=false;
         stims1=350;
         stims2=700;
         nswitch=0;
         ncounts1=1;
         ns1s=3;
         sdur=stimdur;
         xnstims1=stims1+sdur;
         xnstims2=stims1*(ns1s-1)+stims2+sdur;
          if(itype==3){
         endtime=xnstims2+700; }
          else{
         endtime=xnstims2+400; }

         nend=(int)(endtime/dt);
         ttime=0;
	   inputS1.setText("350");
           inputS2.setText("700");
	   inputNS1.setText("3");

//	  repaint();
                                              }
          else{
           butt.setLabel("Start");
           firsttime=true;
        butt.setBackground(Color.green);


      if(sg13 != null) { 
        sg13.dispose(); 
        sg13 = null; 
      } 
      if(frame != null) { 
        frame.dispose(); 
        frame = null; 
      } 
      if(da != null) { 
        da.dispose(); 
        da = null; 
      } 


	   inputS1.setText("350");
           inputS2.setText("700");
	   inputNS1.setText("3");
         stims1=350;
         stims2=700;
         nswitch=0;
         ncounts1=1;
         ns1s=3;
         sdur=stimdur;
         xnstims1=stims1+sdur;
         xnstims2=stims1*(ns1s-1)+stims2+sdur;
          if(itype==3){
         endtime=xnstims2+700; }
          else{
         endtime=xnstims2+400; }

         nend=(int)(endtime/dt);
         ttime=0;



//           repaint();
              }
                                    } 
         repaint();
        }

        }













































