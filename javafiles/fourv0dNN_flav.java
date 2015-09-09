

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.lang.*;
import java.text.*; 

/*
         <applet code="fourv0dNN" width=450 height=350>
         </applet>
 */

public class fourv0dNN extends Applet 
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
	
	private Label button1, button2, button3, button4, button5 , button6, button7, button8, button9;
	private Label button1a, button2a, button3a, button4a, button5a , button6a, button7a, button8a, button9a;
	private Label button1b, button2b, button3b, button4b, button5b , button6b, button7b, button8b, button9b, button28;
	
	private TextField Tbutton1, Tbutton2, Tbutton3, Tbutton4, Tbutton5, Tbutton6, Tbutton7, Tbutton8, Tbutton9;
	private TextField Tbutton1a, Tbutton2a, Tbutton3a, Tbutton4a, Tbutton5a, Tbutton6a, Tbutton7a, Tbutton8a, Tbutton9a;
	private TextField Tbutton1b, Tbutton2b, Tbutton3b, Tbutton4b, Tbutton5b, Tbutton6b, Tbutton7b, Tbutton8b, Tbutton9b, Tbutton28;
	private Panel parameterPanel;

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
        double  customcell=0;                         //NEW ADDED
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

	//-----variables-------
	double   u=0.0;
	double   v=1;
	double   w=1;
	double   s=0;

	//-----------graphh-------------------------------
	DataWin da; 
	Graphs sg13;
	Button bgraph,bgraph2;  //graphh
	Frame frame;           //graphh
	Checkbox plot_xtotal;
	Checkbox dplot_vt;


        Choice options= new Choice();     //NEW ADDED (global now)



	float t_of_t[] = new float[90010];  
	float v_of_t[] = new float[90010];
	float xtotal_of_t[] = new float[90010];

	int nt_of_t=-1;
	int ksen=0;
	int ksen2;
	int igraph=0;
	double xtotal;
	String optionepi="Epicell";
	String optionendo="Endocell";
	String optionm="Mcell";
        String optionopen="Arbitrarycell";      //NEW ADDED




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
	double winstar;
	double twinf;
	double to1;
	double theta_o;

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
	Checkbox  checks;

	int  senvolt=10;
	int  senv=0;
	int  senw=0;
	int  sens=0;




	//-------------init----------
	//        public void init() {
	//    background = new Color (192, 192, 192);
	//    background2 = new Color (110, 159, 86);

	public void init() {
		setSize(580,525);
		background= new Color(192,192,192);
		setBackground(background);
		background2 = new Color (141, 54, 63);


		// set itype=1 for epi, =2 for M, =3 for endo
                //  set itype=4 for arbitrary cell  NEW ADDED
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
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		//************************************************************************************
		
		//change "button name" to "text name"
		parameterPanel = new Panel();

		button1 = new Label(" u_o ");
		Tbutton1 = new TextField(3);
		Tbutton1.addActionListener( this);
		Tbutton1.setText("0");
		
		button2 = new Label(" u_u ");
		Tbutton2 = new TextField(3);
		Tbutton2.addActionListener(this);
		Tbutton2.setText("1.58");
		
		button3 = new Label(" theta_v ");
		Tbutton3 = new TextField(3);
		Tbutton3.addActionListener(this);
		Tbutton3.setText("0.3");
		
		button4 = new Label(" theta_w ");
		Tbutton4 = new TextField(3);
		Tbutton4.addActionListener(this);
		Tbutton4.setText("0.13");
		
		button5 = new Label(" theta_vm ");
		Tbutton5 = new TextField(3);
		Tbutton5.addActionListener(this);
		Tbutton5.setText("0.007");
		
		button6 = new Label(" theta_o ");
		Tbutton6 = new TextField(3);
		Tbutton6.addActionListener(this);
		Tbutton6.setText("0.006");
		
		button7 = new Label(" tau_vm1 ");
		Tbutton7 = new TextField(3);
		Tbutton7.addActionListener(this);
		Tbutton7.setText("60");
		
		button8 = new Label(" tau_vm2 ");
		Tbutton8 = new TextField(3);
		Tbutton8.addActionListener(this);
		Tbutton8.setText("1150");
		
		button9 = new Label(" tau_vp ");
		Tbutton9 = new TextField(3);
		Tbutton9.addActionListener(this);
		Tbutton9.setText("1.4506");
		
		button1a = new Label(" tau_wm1 ");
		Tbutton1a = new TextField(3);
		Tbutton1a.addActionListener(this);
		Tbutton1a.setText("190");

		button2a = new Label(" tau_wm2 ");
		Tbutton2a = new TextField(3);
		Tbutton2a.addActionListener(this);
		Tbutton2a.setText("190");

		button3a = new Label(" k_wm ");
		Tbutton3a = new TextField(3);
		Tbutton3a.addActionListener(this);
		Tbutton3a.setText("65");
		
		button4a = new Label(" u_wm ");
		Tbutton4a = new TextField(3);
		Tbutton4a.addActionListener(this);
		Tbutton4a.setText(".03");
		
		button5a = new Label(" tau_wp ");
		Tbutton5a = new TextField(3);
		Tbutton5a.addActionListener(this);
		Tbutton5a.setText("400");
		
		button6a = new Label(" tau_fi ");
		Tbutton6a = new TextField(3);
		Tbutton6a.addActionListener(this);
		Tbutton6a.setText(".11");
		
		button7a = new Label(" tau_o1 ");
		Tbutton7a = new TextField(3);
		Tbutton7a.addActionListener(this);
		Tbutton7a.setText("650");
		
		button8a = new Label(" tau_o2 ");
		Tbutton8a = new TextField(3);
		Tbutton8a.addActionListener(this);
		Tbutton8a.setText("6");
		
		button9a = new Label(" tau_so1 ");
		Tbutton9a = new TextField(3);
		Tbutton9a.addActionListener(this);
		Tbutton9a.setText("67.06");
		
		button1b = new Label(" tau_so2 ");
		Tbutton1b = new TextField(3);
		Tbutton1b.addActionListener(this);
		Tbutton1b.setText(".10001404");
		
		button2b = new Label(" k_so ");
		Tbutton2b = new TextField(3);
		Tbutton2b.addActionListener(this);
		Tbutton2b.setText("1.3151");
		
		button3b = new Label(" u_so ");
		Tbutton3b = new TextField(3);
		Tbutton3b.addActionListener(this);
		Tbutton3b.setText(".0015582");

		button4b = new Label(" tau_s1 ");
		Tbutton4b = new TextField(3);
		Tbutton4b.addActionListener(this);
		Tbutton4b.setText("2.7342");
		
		button5b = new Label(" tau_s2 ");
		Tbutton5b = new TextField(3);
		Tbutton5b.addActionListener(this);
		Tbutton5b.setText("14");
		
		button6b = new Label(" k_s ");
		Tbutton6b = new TextField(3);
		Tbutton6b.addActionListener(this);
		Tbutton6b.setText("2.0994");
		
		button7b = new Label(" u_s ");
		Tbutton7b = new TextField(3);
		Tbutton7b.addActionListener(this);
		Tbutton7b.setText("0.9087");
		
		button8b = new Label(" tau_si ");
		Tbutton8b = new TextField(3);
		Tbutton8b.addActionListener(this);
		Tbutton8b.setText("1.8875");
		
		button9b = new Label(" tau_wInf ");
		Tbutton9b = new TextField(3);
		Tbutton9b.addActionListener(this);
		Tbutton9b.setText("0.1");
		
		button28 = new Label(" tau_wInf* ");
		Tbutton28 = new TextField(3);
		Tbutton28.addActionListener(this);
		Tbutton28.setText("0.94");
		

		parameterPanel.setLayout(new GridLayout(7,8));
		parameterPanel.add(button1);
		parameterPanel.add(Tbutton1);
		
		parameterPanel.add(button2);
		parameterPanel.add(Tbutton2);
		
		parameterPanel.add(button3);
		parameterPanel.add(Tbutton3);
		
		parameterPanel.add(button4);
		parameterPanel.add(Tbutton4);
		
		parameterPanel.add(button5);
		parameterPanel.add(Tbutton5);
		
		parameterPanel.add(button6);
		parameterPanel.add(Tbutton6);
		
		parameterPanel.add(button7);
		parameterPanel.add(Tbutton7);
		
		parameterPanel.add(button8);
		parameterPanel.add(Tbutton8);
		
		parameterPanel.add(button9);
		parameterPanel.add(Tbutton9);
		
		parameterPanel.add(button1a);
		parameterPanel.add(Tbutton1a);
		
		parameterPanel.add(button2a);
		parameterPanel.add(Tbutton2a);
		
		parameterPanel.add(button3a);
		parameterPanel.add(Tbutton3a);
		
		parameterPanel.add(button4a);
		parameterPanel.add(Tbutton4a);
		
		parameterPanel.add(button5a);
		parameterPanel.add(Tbutton5a);
		
		parameterPanel.add(button6a);
		parameterPanel.add(Tbutton6a);
		
		parameterPanel.add(button7a);
		parameterPanel.add(Tbutton7a);
		
		parameterPanel.add(button8a);
		parameterPanel.add(Tbutton8a);
		
		parameterPanel.add(button9a);
		parameterPanel.add(Tbutton9a);
		
		parameterPanel.add(button1b);
		parameterPanel.add(Tbutton1b);
		
		parameterPanel.add(button2b);
		parameterPanel.add(Tbutton2b);
		
		parameterPanel.add(button3b);
		parameterPanel.add(Tbutton3b);
		
		parameterPanel.add(button4b);
		parameterPanel.add(Tbutton4b);
		
		parameterPanel.add(button5b);
		parameterPanel.add(Tbutton5b);
		
		parameterPanel.add(button6b);
		parameterPanel.add(Tbutton6b);
		
		parameterPanel.add(button7b);
		parameterPanel.add(Tbutton7b);
		
		parameterPanel.add(button8b);
		parameterPanel.add(Tbutton8b);
		
		parameterPanel.add(button9b);
		parameterPanel.add(Tbutton9b);
		
		parameterPanel.add(button28);
		parameterPanel.add(Tbutton28);
		
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
		//***********************************************************************************
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
		checkw = new Checkbox("w gate", false);
		checks = new Checkbox("s gate", false);

		checkvolt.addItemListener(this); 
		checkv.addItemListener(this); 
		checkw.addItemListener(this); 
		checks.addItemListener(this); 


		checkPanel.setLayout(new GridLayout(25,10));
		checkPanel.add(butp);
		checkPanel.add(prompt0);
		checkPanel.add(checkvolt);
		checkPanel.add(prompt1);

		checkPanel.add(checkv);
		checkPanel.add(prompt2);

		checkPanel.add(checkw);
		checkPanel.add(prompt3);

		checkPanel.add(checks);
		checkPanel.add(prompt4);

		checkPanel.add(prompt5);
		checkPanel.add(prompt6);



		//      graphh
		bgraph = new Button ("Current & ascii");
		bgraph.addActionListener( this );
		add(bgraph);
		bgraph.reshape(250,295,155,28);
		//        bgraph.setBackground(background2);


		//  graphh



		prompt7 = new Label ("Cell types: -->");
		prompt7.setForeground(background2);
		add(prompt7);
		prompt7.reshape(15,295,90,28);
//		Choice options= new Choice();  moved up  NEW ADDED
		options.addItem("Epicell");
		options.addItem("Endocell");
		options.addItem("Mcell");
                options.addItem("Arbitrarycell");    //NEW ADDED
		options.addItemListener(this);
		add(options);
		options.setBackground(background2);
		options.reshape(110,295,110,28);
               


		setLayout(new BorderLayout() );
		add(checkPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.NORTH);
		add(parameterPanel, BorderLayout.SOUTH);

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
				g.drawString("w",xpos,60);
				xpos=xpos+23;
			}
			if(senv==10){
				g.setColor(Color.cyan);
				g.drawString("v",xpos,60);
				xpos=xpos+23;
			}
			if(sens==10){
				g.setColor(Color.blue);
				g.drawString("s",xpos,60);
				xpos=xpos+23;
			}



			if(itype==1){
				
				tvm2=1150;
				Tbutton8.setText("1150");
				
				uo=0;
				Tbutton1.setText("0");
				
				tfi=0.11;
				Tbutton6a.setText("0.11");
				
				uu=1.55;
				Tbutton2.setText("1.55");
				
				um=0.3;
				Tbutton3.setText("0.3");
				
				tvm1=60;
				Tbutton7.setText("60");
				
				to=6.0;
				Tbutton8a.setText("6.0");
				
				twma=60.0;
				Tbutton1a.setText("60.0");
				//        twma=60.0;
				twmb=15;
				Tbutton2a.setText("15");
				//        twmb=15;
				
				uwm=.03;
				Tbutton4a.setText("0.03");
				
				xkwm=65;
				Tbutton3a.setText("65");
				
				uq=0.006;
				Tbutton5.setText("0.006");
				
				tsp=16;
				Tbutton5b.setText("16.0");
				
				tsi=1.8875;
				Tbutton8b.setText("1.8875");
				
				twp=200;
				Tbutton5a.setText("200.0");
				
				tsoa = 30.0181;
				Tbutton9a.setText("30.0181");
				
				tsob = 0.9957;				
				Tbutton1b.setText("0.9957");
				
				uso=0.65;
				Tbutton3b.setText("0.65");
				
				xtso=  2.0458;
				Tbutton2b.setText("2.0458");
				
				winstar = 0.94;
				Tbutton28.setText("0.94");
				
				twinf = 0.07;
				Tbutton9b.setText("0.07");
				
				to1 = 400.0;
				Tbutton7a.setText("400.0");
				
				theta_o = 0.006;
				Tbutton6.setText("0.006");
				
				up = 0.13;
				Tbutton4.setText("0.13");
				
				tvp = 1.4506;
				Tbutton9.setText("1.4506");
				
				tsm = 2.7342;
				Tbutton4b.setText("2.7342");
				
				xk = 2.0994;
				Tbutton6b.setText("2.0994");
				
				us = 0.9087;
				Tbutton7b.setText("0.9087");
			}

			if(itype==2){
				tvm2=10;
				Tbutton8.setText("10");
				
				uo=0;
				Tbutton1.setText("0");
				
				tfi=0.1;
				Tbutton6a.setText("0.1");
				
				uu=1.56;
				Tbutton2.setText("1.56");
				
				um=0.3;
				Tbutton3.setText("0.3");
				
				tvm1=75;
				Tbutton7.setText("75");
				
				to=6.0;
				Tbutton8a.setText("6.0");
				
				twma=6;
				Tbutton1a.setText("6");
				
				twmb=140;
				Tbutton2a.setText("140");
				
				uwm=.016;
				Tbutton4a.setText("0.016");
				
				xkwm=200;
				Tbutton3a.setText("200");
				
				uq=0.2;
				Tbutton5.setText("0.2");
				
				tsp=2.0;
				Tbutton5b.setText("2.0");
				
				tsi=2.9013;
				Tbutton8b.setText("2.9013");
				
				twp=280;
				Tbutton5a.setText("280");
				
				tsoa=40;
				Tbutton9a.setText("40");
				
				tsob=1.2;
				Tbutton1b.setText("1.2");
				
				uso=.65;
				Tbutton3b.setText("0.65");
				
				xtso=2;
				Tbutton2b.setText("2");
				
				winstar = 0.78;
				Tbutton28.setText("0.78");
				
				twinf = 0.0273;
				Tbutton9b.setText("0.0273");
				
				to1 = 470.0;
				Tbutton7a.setText("470.0");
				
				theta_o = 0.006;
				Tbutton6.setText("0.006");
		        
				up = 0.13;
				Tbutton4.setText("0.13");
				
				tvp = 1.4506;
				Tbutton9.setText("1.4506");
				
				tsm = 2.7342;
				Tbutton4b.setText("2.7342");
				
				xk = 2.0994;
				Tbutton6b.setText("2.0994");
				
				us = 0.9087;
				Tbutton7b.setText("0.9087");
			} 

			if(itype==3){
				tvm2=tvp;
				Tbutton8.setText("1.4506");
				
				uo=0;
				Tbutton1.setText("0");
				
				tfi=0.078;
				Tbutton6a.setText("0.078");
				
				uu=1.61;
				Tbutton2.setText("1.61");
				
				um=0.3;
				Tbutton3.setText("0.3");
				
				tvm1=80;
				Tbutton7.setText("80");
				
				to=7.0;
				Tbutton8a.setText("7.0");
				
				twma=70;
				Tbutton1a.setText("70");
				
				twmb=8;
				Tbutton2a.setText("8");
				
				uwm=.016;
				Tbutton4a.setText("0.016");
				
				xkwm=200;
				Tbutton3a.setText("200");
				
				uq=0.1;
				Tbutton5.setText("0.1");
				
				tsp=4.0;
				Tbutton5b.setText("4.0");
				
				tsi=3.3849;
				Tbutton8b.setText("3.3849");
				
				twp=280;
				Tbutton5a.setText("280");
				
				tsoa=91;
				Tbutton9a.setText("91");
				
				tsob=0.8;
				Tbutton1b.setText("0.8");
				
				uso=0.6;
				Tbutton3b.setText("0.6");
				
				xtso=2.1;
				Tbutton2b.setText("2.1");
				
				winstar = 0.5;
				Tbutton28.setText("0.5");
				
				twinf = 0.01;
				Tbutton9b.setText("0.01");
				
				to1 = 410.0;
				Tbutton7a.setText("410.0");
				
				theta_o = 0.005;
				Tbutton6.setText("0.005");
				
				up = 0.13;
				Tbutton4.setText("0.13");
				
				tvp = 1.4506;
				Tbutton9.setText("1.4506");
				
				tsm = 2.7342;
				Tbutton4b.setText("2.7342");
				
				xk = 2.0994;
				Tbutton6b.setText("2.0994");
				
				us = 0.9087;
				Tbutton7b.setText("0.9087");
			} 

			if(itype==4){                    //NNEW ADDED below

                twp= Double.valueOf(Tbutton5a.getText()).doubleValue();


		//		tvm2=tvp;
		//		Tbutton8.setText("1.4506");
                tvm2= Double.valueOf(Tbutton8.getText()).doubleValue();
				
		//		uo=0;
		//		Tbutton1.setText("0");

                uo= Double.valueOf(Tbutton1.getText()).doubleValue();

				
	//			tfi=0.077;
	//			Tbutton6a.setText("0.077");
                tfi= Double.valueOf(Tbutton6a.getText()).doubleValue();

				
		//		uu=1.62;
		//		Tbutton2.setText("1.62");
                uu= Double.valueOf(Tbutton2.getText()).doubleValue();

				
//				um=0.3;
//				Tbutton3.setText("0.3");
                um= Double.valueOf(Tbutton3.getText()).doubleValue();

				
//				tvm1=80;
//				Tbutton7.setText("80");
                tvm1= Double.valueOf(Tbutton7.getText()).doubleValue();

				
//				to=7.0;
//				Tbutton8a.setText("7.0");
                to= Double.valueOf(Tbutton8a.getText()).doubleValue();

				
//				twma=70;
//				Tbutton1a.setText("70");
                twma= Double.valueOf(Tbutton1a.getText()).doubleValue();

				
//				twmb=8;
//				Tbutton2a.setText("8");
                twmb= Double.valueOf(Tbutton2a.getText()).doubleValue();

				
		//		uwm=.016;
		//		Tbutton4a.setText(".016");
                uwm= Double.valueOf(Tbutton4a.getText()).doubleValue();

				
//				xkwm=200;
//				Tbutton3a.setText("200");
                xkwm= Double.valueOf(Tbutton3a.getText()).doubleValue();

				
		//		uq=0.25;
		//		Tbutton5.setText(".25");
                uq= Double.valueOf(Tbutton5.getText()).doubleValue();

				
//				tsp=4.0;
//				Tbutton5b.setText("4.0");
                tsp= Double.valueOf(Tbutton5b.getText()).doubleValue();

				
//				tsi=3.3849;
//				Tbutton8b.setText("3.3849");
                tsi= Double.valueOf(Tbutton8b.getText()).doubleValue();

//				tsoa=68;
//				Tbutton9a.setText("68");
                tsoa= Double.valueOf(Tbutton9a.getText()).doubleValue();

				//tsob=.1;
				//Tbutton1b.setText("0.1");
                tsob= Double.valueOf(Tbutton1b.getText()).doubleValue();
		
		//		uso=.74;
		//		Tbutton3b.setText("0.74");
                uso= Double.valueOf(Tbutton3b.getText()).doubleValue();

	//			xtso=2.5;
	//			Tbutton2b.setText("2.5");
                xtso= Double.valueOf(Tbutton2b.getText()).doubleValue();

//				winstar = 0.5;
//				Tbutton28.setText("0.5");
                winstar= Double.valueOf(Tbutton28.getText()).doubleValue();

//				twinf = 0.01;
//				Tbutton9b.setText("0.01");
                twinf= Double.valueOf(Tbutton9b.getText()).doubleValue();

//				to1 = 410.0;
//				Tbutton7a.setText("410.0");
                to1= Double.valueOf(Tbutton7a.getText()).doubleValue();

//				theta_o = 0.005;
//				Tbutton6.setText("0.005");
                theta_o= Double.valueOf(Tbutton6.getText()).doubleValue();
				
		//		up = 0.13;
		//		Tbutton4.setText("0.13");
                up= Double.valueOf(Tbutton4.getText()).doubleValue();

				
	//			tvp = 1.4506;
	//			Tbutton9.setText("1.4506");
                tvp= Double.valueOf(Tbutton9.getText()).doubleValue();
				
//				tsm = 2.7342;
//				Tbutton4b.setText("2.7342");
                tsm= Double.valueOf(Tbutton4b.getText()).doubleValue();

//				xk = 2.0994;
//				Tbutton6b.setText("2.0994");
                xk= Double.valueOf(Tbutton6b.getText()).doubleValue();

//				us = 0.9087;
//				Tbutton7b.setText("0.9087");
                us= Double.valueOf(Tbutton7b.getText()).doubleValue();

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


					m=0;
					p=0;
					q=0;
					if(u > um)m=1;
					if(u > up)p=1;
					if(u > uq)q=1;          

					//c           New definition of tso
					//c           tso=tsoa+(tsob-tsoa)*(1+tanh((u-uso)*xtso))/2
					tso=tsot[iv1];

					//c           Definition of ts
					ts=(1-p)*tsm+p*tsp;

					//c           Definition of tvm
					tvm=(1-q)*tvm1+tvm2*q;
					//c         twmm=twma+(twmb-twma)*(1+tanh((u-.03)*65))/2
					twmm=twmmt[iv1];
					//c         write(11,*)u,twmm-twmmx,twmm,twmmx
					if(u > uq){
						vinf=0;
					}
					else{
						vinf=1;
					}

					//c---------new defintion of to ---------------

					if(itype == 1){

						if(u > theta_o){
							too=to;
							winf=winstar; }
						else{ 
							too=to1;
							winf=1-u/twinf;
						}
						if(winf >= 1)winf=1;
					}   //endif

					if(itype == 2){
						if(u > theta_o){
							too=to;
							winf=winstar; }
						else{
							too=to1;
							winf=1-u/twinf;
						}
						if(winf >= 1)winf=1;
					}   //endif


					if(itype == 3){
						if(u > theta_o){
							too=to;
							winf=winstar; }
						else {
							too=to1;
							winf=1-u/twinf;
						}
						if(winf >= 1)winf=1;
					}    //endif




					dv=(1-m)*(vinf-v)/tvm-m*v/tvp;
					dw=(1-p)*(winf-w)/twmm-p*w/twp;
					//c           ds=((1+tanh((u-us)*xk))/2-s)/ts
					ds=(tanhs[iv1]-s)/ts;
					v=v+HT*dv;
					w=w+HT*dw;
					s=s+HT*ds;

					xfi=-v*m*(u-um)*(uu-u)/tfi;
					xso=(u-uo)*(1-p)/too+p/tso;
					xsi=-p*w*s/tsi;

					Itot=xfi+xso+xsi+Istim;
					ut=u-(Itot)*HT;

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
							int senwf=(int)((245- w*106));
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
		if(checks.getState()==true){sens =10;}
		else{sens =0;}

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
		if(ae.getItem().toString()== optionopen){   //NEW ADDED
			itype=4;
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

		
		tfi = Double.valueOf(Tbutton6a.getText()).doubleValue();
		
		//stims2= Double.valueOf(inputS2.getText()).doubleValue(); copy lots of times
		
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

// new for Drew to keep adding  NEW ADDED see below 
                
                   customcell=1;   //NNEW line added (and deleted all the customcells in the ifs below
 
                 // gets the value written on the field box into the variable
                 twp= Double.valueOf(Tbutton5a.getText()).doubleValue(); 
                 if(twp==200.0 || twp==280.0){ }
                 else{ customcell=1;}
                 
// **************** This is where I am adding the rest *******************************
                 tvm2 = Double.valueOf(Tbutton8.getText()).doubleValue(); 
                 if (tvm2 == 1150.0 || tvm2 == 10.0 || tvm2 == 1.4506) {}
                 else { customcell = 1; }
                 
                 uo = Double.valueOf(Tbutton1.getText()).doubleValue(); 
                 if (uo == 0) { }
                 else { customcell = 1; }
                 
                 tfi = Double.valueOf(Tbutton6a.getText()).doubleValue(); 
                 if (tfi == .11 || tfi == .1 || tfi == .078) { }
                 else { customcell = 1; }
                 
                 uu = Double.valueOf(Tbutton2.getText()).doubleValue(); 
                 if (uu == 1.55 || uu == 1.56 || uu == 1.61) {  }
                 else { customcell = 1; }
                 
                 um = Double.valueOf(Tbutton3.getText()).doubleValue(); 
                 if (um == .3) { }
                 else {customcell = 1; }
                 
                 tvm1 = Double.valueOf(Tbutton7.getText()).doubleValue(); 
                 if (tvm1 == 60.0 || tvm1 == 75.0 || tvm1 == 80.0) {  }
                 else { customcell = 1; }
                 
                 to = Double.valueOf(Tbutton8a.getText()).doubleValue(); 
                 if (to == 6.0 || to == 7.0) {  }
                 else { customcell = 1; }
                 
                 twma = Double.valueOf(Tbutton1a.getText()).doubleValue(); 
                 if (twma == 60.0 || twma == 6.0 || twma == 70.0) { }
                 else { customcell = 1; }
                 
                 twmb = Double.valueOf(Tbutton2a.getText()).doubleValue(); 
                 if (twmb == 15.0 || twmb == 140.0 || twmb == 8.0) {  }
                 else { customcell = 1; }
                 
                 uwm = Double.valueOf(Tbutton4a.getText()).doubleValue(); 
                 if (uwm == .03 || uwm == .016) {  }
                 else { customcell = 1; }
                 
                 xkwm = Double.valueOf(Tbutton3a.getText()).doubleValue(); 
                 if (xkwm == 65.0 || xkwm == 200.0) {  }
                 else { customcell = 1; }
                 
                 uq = Double.valueOf(Tbutton5.getText()).doubleValue(); 
                 if (uq == .006 || uq == .2 || uq == .1) {  }
                 else { customcell = 1; }
                 
                 tsp = Double.valueOf(Tbutton5b.getText()).doubleValue(); 
                 if (tsp == 16.0 || tsp == 2.0 || tsp == 4.0) {  }
                 else { customcell = 1; }
                 
                 tsi = Double.valueOf(Tbutton8b.getText()).doubleValue();
                 if (tsi == 1.8875 || tsi == 2.9013 || tsi == 3.3849) {  }
                 else { customcell = 1; }
                 
                 tsoa = Double.valueOf(Tbutton9a.getText()).doubleValue(); 
                 if (tsoa == 30.0181 || tsoa == 40.0 || tsoa == 91.0) {  }
                 else { customcell = 1; }
                 
                 tsob = Double.valueOf(Tbutton1b.getText()).doubleValue(); 
                 if (tsob == .9957 || tsob == 1.2 || tsob == .8) {  }
                 else { customcell = 1; }
                 
                 uso = Double.valueOf(Tbutton3b.getText()).doubleValue(); 
                 if (uso == 0.65 || uso == .6){  }
                 else { customcell = 1; }
                 
                 xtso = Double.valueOf(Tbutton2b.getText()).doubleValue(); 
                 if (xtso == 2.0458 || xtso == 2.0 || xtso == 2.1) {  }
                 else { customcell = 1; }
                 
                 winstar = Double.valueOf(Tbutton28.getText()).doubleValue(); 
                 if (winstar == .94 || winstar == .78 || winstar == .5) {  }
                 else { customcell = 1; }
                 
                 twinf = Double.valueOf(Tbutton9b.getText()).doubleValue(); 
                 if (twinf == 0.07 || twinf == .0273 || twinf == .01) {  }
                 else { customcell = 1; }
                 
                 to1 = Double.valueOf(Tbutton7a.getText()).doubleValue(); 
                 if (to1 == 400.0 || to1 == 470.0 || to1 == 410.0) {  }
                 else { customcell = 1; }
                 
                 theta_o = Double.valueOf(Tbutton6.getText()).doubleValue(); 
                 if (theta_o == 0.006 || theta_o == 0.005) {  }
                 else { customcell = 1; }
                 
                 up = Double.valueOf(Tbutton4.getText()).doubleValue(); 
                 if (up == .13) {  }
                 else { customcell = 1; }
                 
                 tvp = Double.valueOf(Tbutton9.getText()).doubleValue(); 
                 if (tvp == 1.4506) {  }
                 else { customcell = 1; }
                 
                 tsm = Double.valueOf(Tbutton4b.getText()).doubleValue(); 
                 if (tsm == 2.7342) {  }
                 else { customcell = 1; }

                 xk = Double.valueOf(Tbutton6b.getText()).doubleValue(); 
                 if (xk == 2.0994) {  }
                 else { customcell = 1; }
                 
                 us = Double.valueOf(Tbutton7b.getText()).doubleValue(); 
                 if (us == .9087) {  }
                 else { customcell = 1; }
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
// adding the rest of the values                 

                  // put the value on the field box, this may not be necesary but maybe if one does more than one change at the time. Not sure, you can test if not necessary.
               
	      //    Tbutton5a.setText(Tbutton5a.getText());

                if(customcell==1){
                  options.select (3);
                       itype=4;
                                  }


		
		//*************************************************************************

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
                             itype=1;                           //NEW ADDED
                             options.select (0);                //NEW ADDED
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
