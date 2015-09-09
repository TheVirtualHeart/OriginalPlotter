


import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.lang.*;
import java.text.*; 

        /*
         <applet code="k0d" width=520 height=350>
         </applet>
          */

public class k0d extends Applet 
    implements ActionListener, ItemListener {
//         implements   ActionListener {
        
//         private Panel buttonPanel, checkPanel;
//	 private Label prompt1, prompt2, prompt3, prompt4, prompt5;
//         private Label promptg,promptgg,promptg2,promptg3;
//	 private TextField input1, input2, input3, input4, input5;
 //        private Label prompts1, prompttime;
//	 private TextField  inputs1, inputtime;
//	 private double result;
//	Button butt,butp;
    private Panel buttonPanel,checkPanel;
    private Label promptNS1, promptS2, promptS1;
    private TextField inputNS1, inputS2, inputS1;
    private double result;
    Button butt,butp;
    double a=0.1;
    double gama=1;
    double b=0.5;
    double eps=0.01;
    double delta=0;
    boolean firsttime= true;
//-----------stimulation stuff-----
    double stims1,stims2,sdur,xnstims1,xnstims2,ttime;
    int   nswitch,ncounts1,ns1s;
    int napdd=0;
    double apdtime[] = new double[10];
    double APDD,DII;
//--------------------------

//-----------graphh-------------------------------
    DataWin da; 
    Graphs sg1,sg2,sg3,sg4,sg5,sg6,sg7,sg8,sg9,sg10,sg11,sg12,sg13;


    Button bgraph,bgraph2;  //graphh
    Frame frame;           //graphh
    Checkbox plot_xna; 
    Checkbox plot_xk1; 
    Checkbox plot_xto; 
    Checkbox plot_xkpk; 
    Checkbox plot_xkr; 
    Checkbox plot_xks; 
    Checkbox plot_xcal; 
    Checkbox plot_xpca;
    Checkbox plot_xnak;
    Checkbox plot_xnaca;
    Checkbox plot_xbna;
    Checkbox plot_xbca;
    Checkbox plot_xtotal;
    int dimension=90000;
    float xna_of_t[] = new float[90000];
    float xk1_of_t[] = new float[90000];
    float xto_of_t[] = new float[90000];
    float xkpk_of_t[] = new float[90000];
    float xkr_of_t[] = new float[90000];  
    float xks_of_t[] = new float[90000];  
    float xcal_of_t[] = new float[90000];  
    float xpca_of_t[] = new float[90000];  
    float xnak_of_t[] = new float[90000];  
    float xnaca_of_t[] = new float[90000];  
    float xbna_of_t[] = new float[90000];  
    float xbca_of_t[] = new float[90000];  
    float xtotal_of_t[] = new float[90000];  
    float t_of_t[] = new float[90000];  
    
    int nt_of_t=-1;
    int ksen=0;
    int ksen2;
    double xtotal;
    int iplotcurrent=0;
    String optionepi="Epicell";
    String optionendo="Endocell";
    String optionm="Mcell";




//-------------graphh--------------------

    NumberFormat nf = NumberFormat.getInstance(); 
    Color  background,background2;

    double vlo=-100.,vhi=70.,vmeklo=-10.,clo=0.00001,chi=0.00201;
    int nvt=17000,nct=20000;
    double INaKcoefft[] = new double[nvt];
    double IpKcoefft[]  = new double[nvt];
    double exptaumt[]   = new double[nvt];
    double m_inft[]     = new double[nvt];
    double exptauht[]   = new double[nvt];
    double h_inft[]     = new double[nvt];
    double exptaujt[]   = new double[nvt];
    double xr1_inft[]   = new double[nvt];
    double exptauxr1t[] = new double[nvt];
    double xr2_inft[]   = new double[nvt];
    double exptauxr2t[] = new double[nvt];
    double xs_inft[]    = new double[nvt];
    double exptauxst[]  = new double[nvt];
    double INaCa1t[]    = new double[nvt];
    double INaCa2t[]    = new double[nvt];
    double r_inft[]     = new double[nvt];
    double s_inft[]     = new double[nvt];
    double exptaurt[]   = new double[nvt];
    double exptaust[]   = new double[nvt];
    double d_inft[]     = new double[nvt];
    double exptaudt[]   = new double[nvt];
    double f_inft[]     = new double[nvt];
    double exptauft[]   = new double[nvt];
    double exp2vfortt[] = new double[nvt];
    double fcainft[]    = new double[nct];
    double ginft[]      = new double[nct];
// table indexed by V-EK; V-EK has roughly same range as V but is shifted
// so that min is >0; here specifying min of -10 to be safe
    double IK1t[]       = new double[nvt];

// variables
    double svoltt,svolt,svolt2,Cai,CaSR,Nai,Ki,sItot;
    double sm,sh,sj,sxr1,sxr2,sxs,ss,sr,sd,sf,sfca,sg;
    double dvdt;
// currents
    double IKr,IKs,IK1,Ito,INa,IbNa,ICaL,IbCa,INaCa,IpCa;
    double IpK,INaK,Irel,Ileak,Istim;


    double  Ko=5.4,Cao=2.0,Nao=140.0,Vc=0.016404,Vsr=0.001094;
    double  Bufc=0.15,Kbufc=0.001,Bufsr=10.,Kbufsr=0.3;
    double  taufca=2.,taug=2.,Vmaxup=0.000425,Kup=0.00025;
    double  R=8314.472,F=96485.3415,T=310.0,CAPACITANCE=0.185;
    double  Gkr=0.096,pKNa=0.03,GK1=5.405;
    double  GNa=14.838,GbNa=0.00029,KmK=1.0,KmNa=40.0,knak=1.362;
    double  GCaL=0.000175,GbCa=0.000592,knaca=1000,KmNai=87.5;
    double  KmCa=1.38,ksat=0.1,n=0.35,GpCa=0.825,KpCa=0.0005;
    double  GpK=0.0146;
    double  Gks,Gto;
    double  V_init=-86.2,Cai_init=0.0002,CaSR_init=0.2;
    double  Nai_init=11.6,Ki_init=138.3;
    double  stimdur=1.0,stimstrength=-52.0,cyclelength=500.0;
        
    double  HT,endtime,RTONF;
    int     iendtime,ixs11,ixs12,ixs13,ixs14,ixs15,ixs16,ixs17,ixs18;
    int     ixs19,ixs110,ixs21;



    double  dNai,dKi,dCai,dCaSR,A,BufferFactorc,BufferFactorsr;
    double  SERCA,Caisquare,CaSRsquare,CaCurrent,CaSRCurrent;
    double  fcaold,gold,Ek,Ena,Eks,Eca,CaCSQN,bjsr,cjsr,CaBuf;
    double  bc,cc,Ak1,Bk1,rec_iK1,rec_ipK,rec_iNaK,AM,BM;
    double  AH_1,BH_1,AH_2,BH_2,AJ_1,BJ_1,AJ_2,BJ_2,M_INF,H_INF;
    double  J_INF,expTAU_M,expTAU_H,expTAU_J,axr1,bxr1,axr2,bxr2;
    double  Xr1_INF,Xr2_INF,expTAU_Xr1,expTAU_Xr2,Axs,Bxs,Xs_INF;
    double  expTAU_Xs,R_INF,expTAU_R,S_INF,expTAU_S,Ad,Bd,Cd,expTAU_D;
    double  D_INF,expTAU_F,F_INF,FCa_INF,G_INF;
    double  inverseVcF2,inverseVcF,Kupsquare,BufcKbufc;
    double  Kbufcsquare,Kbufc2,BufsrKbufsr,Kbufsrsquare;
    double  Kbufsr2,exptaufca,exptaug;
//        double  stimdur,stimstrength,cyclelength;
    double  zindexv,q1,q2,vv,dvt;
    double  zindexvmek,qvmek1,qvmek2;
    double  zindexc,qc1,qc2,ccai,dct;
    double  INaKcoeff,IpKcoeff,sqrtko54,temp,factor,Nao3,KmNai3;
    double  INaCa1,INaCa2,ICaLcoeff,exp2vfort;
    double  cc2,cc4,cc6,cc8,cc16,tempc;

    int   ntime,nend,istimdur,icycle,itype,iv1,iv2,indexv,i;
    int   ivmek1,ivmek2,indexvmek,ic1,ic2,indexc;
    int   skip,ncount;

//------------check boxes for variables----------------
    Checkbox checkCai;
    Checkbox checkCaSR;
    Checkbox checkNai;
    Checkbox checkKi;
    Checkbox checksm;
    Checkbox checksh;
    Checkbox checksj;
    Checkbox checksxr1;
    Checkbox checksxr2;
    Checkbox checksxs;
    Checkbox checksr;
    Checkbox checkss;
    Checkbox checksd;
    Checkbox checksf;
    Checkbox checksfca;
    Checkbox checksg;
    Checkbox checkvolt;

    int senCai=0;
    int senCaSR=0;
    int senNai=0;
    int senKi=0;
    int sensm=0;
    int sensh=0;
    int sensj=0;
    int sensxr1=0;
    int sensxr2=0;
    int sensxs=0;
    int sensr=0;
    int senss=0;
    int sensd=0;
    int sensf=0;
    int sensfca=0;
    int sensg=0;
    int senvolt=10;

//      Cai
//      CaSR
//      Nai
//      Ki
//      sm
//      sh
//      sj
//      sxr1
//      sxr2
//      sxs
//      sr
//      ss
//      sd
//      sf
//      sfca
//      sg



//-------------init----------
//        public void init() {
//    background = new Color (192, 192, 192);
//    background2 = new Color (110, 159, 86);

    public void init() {
	setSize(590,330);
	background= new Color(192,192,192);
	setBackground(background);



// set itype=1 for epi, =2 for M, =3 for endo
// affects conductances for IKs and Ito
	itype=1;

	if(itype==1){
	    Gks=0.245;
	    Gto=0.294; }
	if(itype==3){
	    Gks=0.245;
	    Gto=0.073; }
	if(itype==2){
	    Gks=0.062;
	    Gto=0.294; }

// numerical parameters

// time step dt, here HT
	HT=0.02;
//      endtime=1700.0;
//      nend=(int)(endtime/HT);
// integer-ized versions of stimulus duration, cycle length
	istimdur=(int)(stimdur/HT);
	icycle=(int)(cyclelength/HT);

//-----------stimulation stuff-----
// initial CL for S1, S2
	stims1=300;
	stims2=900;
	nswitch=0;
	ncounts1=1;
	ns1s=7;
	sdur=stimdur;
	xnstims1=stims1+sdur;
	xnstims2=stims1*(ns1s-1)+stims2+sdur;
	endtime=xnstims2+400;
	nend=(int)(endtime/HT);
	ttime=0;
         
//--------------------------



// useful values
// model-specific
	RTONF=(R*T)/F;
	inverseVcF2=1/(2*Vc*F);
	inverseVcF=1./(Vc*F);
	Kupsquare=Kup*Kup;
	BufcKbufc=Bufc*Kbufc;
	Kbufcsquare=Kbufc*Kbufc;
	Kbufc2=2*Kbufc;
	BufsrKbufsr=Bufsr*Kbufsr;
	Kbufsrsquare=Kbufsr*Kbufsr;
	Kbufsr2=2*Kbufsr;
	exptaufca=Math.exp(-HT/taufca);
	exptaug=Math.exp(-HT/taug);
	sqrtko54=Math.sqrt(Ko/5.4);
	Nao3=Nao*Nao*Nao;
	KmNai3=KmNai*KmNai*KmNai;
	ICaLcoeff=GCaL*4.0*F/RTONF;
	
// initial values
// model-specific
	svolt=V_init;
	svoltt=V_init;
	svolt2=V_init;
	Cai=Cai_init;
	CaSR=CaSR_init;
	Nai=Nai_init;
	Ki=Ki_init;
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
// set up tables
// model-specific

// voltage-dependent tables
	dvt=(vhi-vlo)/nvt;
	for(int i=0; i<nvt; i++){
	    vv=vlo+dvt*(double)(i);
	    
	    INaKcoefft[i]=knak*(Ko/(Ko+KmK))*(1./(1.+0.1245*Math.exp(-0.1*vv*F/(R*T))+0.0353*Math.exp(-vv*F/(R*T))));

	    IpKcoefft[i]=GpK/(1.+Math.exp((25-vv)/5.98));

	    AM=1./(1.+Math.exp((-60.-vv)/5.));
	    BM=0.1/(1.+Math.exp((vv+35.)/5.))+0.10/(1.+Math.exp((vv-50.)/200.));
	    exptaumt[i]=Math.exp(-HT/(AM*BM));
	    m_inft[i]=1./((1.+Math.exp((-56.86-vv)/9.03))*(1.+Math.exp((-56.86-vv)/9.03)));

	    if(vv >= -40.){
		AH_1=0.;
		BH_1=(0.77/(0.13*(1.+Math.exp(-(vv+10.66)/11.1))));
		exptauht[i]= Math.exp(-HT/(1.0/(AH_1+BH_1)));
		AJ_1=0.;
		BJ_1=(0.6*Math.exp((0.057)*vv)/(1.+Math.exp(-0.1*(vv+32.))));
		exptaujt[i]= Math.exp(-HT/(1.0/(AJ_1+BJ_1)));
	    }
	    else{
		AH_2=(0.057*Math.exp(-(vv+80.)/6.8));
		BH_2=(2.7*Math.exp(0.079*vv)+(3.1e5)*Math.exp(0.3485*vv));
		exptauht[i]=Math.exp(-HT/(1.0/(AH_2+BH_2)));
		AJ_2=(((-2.5428e4)*Math.exp(0.2444*vv)-(6.948e-6)*Math.exp(-0.04391*vv))*(vv+37.78)/(1.+Math.exp(0.311*(vv+79.23))));
		
		BJ_2=(0.02424*Math.exp(-0.01052*vv)/(1.+Math.exp(-0.1378*(vv+40.14))));
		exptaujt[i]= Math.exp(-HT/(1.0/(AJ_2+BJ_2)));
	    } 


	    h_inft[i]=1./((1.+Math.exp((vv+71.55)/7.43))*(1.+Math.exp((vv+71.55)/7.43)));

	    xr1_inft[i]=1./(1.+Math.exp((-26.-vv)/7.));
	    exptauxr1t[i]=Math.exp(-HT/((450./(1.+Math.exp((-45.-vv)/10.)))*(6./(1.+Math.exp((vv-(-30.))/11.5)))));

	    xr2_inft[i]=1./(1.+Math.exp((vv-(-88.))/24.));
	    exptauxr2t[i]=Math.exp(-HT/((3./(1.+Math.exp((-60.-vv)/20.)))*(1.12/(1.+Math.exp((vv-60.)/20.)))));


	    xs_inft[i]=1./(1.+Math.exp((-5.-vv)/14.));
	    exptauxst[i]=Math.exp(-HT/((1100./(Math.sqrt(1.+Math.exp((-10.-vv)/6))))*(1./(1.+Math.exp((vv-60.)/20.)))));


	    if(itype==1){
		r_inft[i]=1./(1.+Math.exp((20-vv)/6.));
		s_inft[i]=1./(1.+Math.exp((vv+20)/5.));
		exptaurt[i]=Math.exp(-HT/(9.5*Math.exp(-(vv+40.)*(vv+40.)/1800.)+0.8));
		exptaust[i]=Math.exp(-HT/(85.*Math.exp(-(vv+45.)*(vv+45.)/320.)+5./(1.+Math.exp((vv-20.)/5.))+3.));
	    }
	    if(itype==3){
		r_inft[i]=1./(1.+Math.exp((20-vv)/6.));
		s_inft[i]=1./(1.+Math.exp((vv+28)/5.));
		exptaurt[i]=Math.exp(-HT/(9.5*Math.exp(-(vv+40.)*(vv+40.)/1800.)+0.8));
		exptaust[i]=Math.exp(-HT/(1000.*Math.exp(-(vv+67)*(vv+67)/1000.)+8.));
	    }
	    if(itype==2) {
		r_inft[i]=1./(1.+Math.exp((20-vv)/6.));
		s_inft[i]=1./(1.+Math.exp((vv+20)/5.));
		exptaurt[i]=Math.exp(-HT/(9.5*Math.exp(-(vv+40.)*(vv+40.)/1800.)+0.8));
		exptaust[i]=Math.exp(-HT/(85.*Math.exp(-(vv+45.)*(vv+45.)/320.)+5./(1.+Math.exp((vv-20.)/5.))+3.));
	    }

	    d_inft[i]=1./(1.+Math.exp((-5.-vv)/7.5));
	    exptaudt[i]=Math.exp(-HT/((1.4/(1.+Math.exp((-35.-vv)/13.))+0.25)*(1.4/(1.+Math.exp((vv+5.)/5.)))+1./(1.+Math.exp((50-vv)/20.))));
	    f_inft[i]=1./(1.+Math.exp((vv+20.)/7.));
	    exptauft[i]=Math.exp(-HT/(1125*Math.exp(-(vv+27)*(vv+27.)/240)+80.+165/(1.+Math.exp((25.-vv)/10))));


	    temp=Math.exp((n-1)*vv*F/(R*T));
	    factor=knaca/(KmNai3+Nao3)/(KmCa+Cao)/(1.+ksat*temp);
	    INaCa1t[i]=factor*Math.exp(n*vv*F/(R*T))*Cao;
	    INaCa2t[i]=factor*temp*Nao3;

	    exp2vfortt[i]=Math.exp(2*vv/RTONF);
	    
	}     //enddo

// (V-Ek)-dependent table for IK1
        for(int i=0; i<nvt ; i++){
//  here vv is actually v - ek
	    vv=vmeklo+dvt*i;

	    Ak1=0.1/(1.+Math.exp(0.06*(vv-200.)));
	    Bk1=(3.*Math.exp(0.0002*(vv+100.))+Math.exp(0.1*(vv-10.)))/(1.+Math.exp(-0.5*vv));
	    IK1t[i]=GK1*vv*Ak1/(Ak1+Bk1);
	} //enddo 
	
// Ca_i -dependent tables for g_infinity and fca_infinity
	dct=(chi-clo)/nct;
	for( int i=0; i<nct; i++) {
	    ccai=clo+dct*i;

	    fcainft[i]=(1./(1.+((ccai/0.000325)*(ccai/0.000325)*(ccai/0.000325)*(ccai/0.000325)*(ccai/0.000325)*(ccai/0.000325)*(ccai/0.000325)*(ccai/0.000325)))+0.1/(1.+Math.exp((ccai-0.0005)/0.0001))+0.20/(1.+Math.exp((ccai-0.00075)/0.0008))+0.23 )/1.46;

	    tempc=ccai/0.00035;
	    cc2=tempc*tempc;
	    cc4=cc2*cc2;
	    cc6=cc2*cc4;
	    cc8=cc4*cc4;
	    cc16=cc8*cc8;

	    if(ccai<0.00035){
		ginft[i]=1./(1.+cc6);  }
	    else{
		ginft[i]=1./(1.+cc16); }

	}  //enddo

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
	inputNS1.setText("7");
	inputS1.setText("400");
	inputS2.setText("900");

//             repaint();

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


// one checkbox for each variable, default to false except true for voltage
	checkCai = new Checkbox("[Ca]i", false); 
	checkCaSR = new Checkbox("[Ca]SR", false);
	checkNai= new Checkbox("[Na]i",false );
	checkKi= new Checkbox("[K]i",false );
	checksm= new Checkbox("m gate", false);
	checksh= new Checkbox("h gate", false);
	checksj= new Checkbox("j gate", false);
	checksxr1= new Checkbox("Xr1 gate", false);
	checksxr2= new Checkbox("Xr2 gate",false );
	checksxs= new Checkbox("Xs gate", false);
	checksr= new Checkbox("r gate", false);
	checkss= new Checkbox("s gate",  false);
	checksd= new Checkbox("d gate", false);
	checksf= new Checkbox("f gate", false );
	checksfca= new Checkbox("fca gate", false );
	checksg= new Checkbox("g gate", false);
	checkvolt= new Checkbox("Voltage", true);


// add listeners
	checkCai.addItemListener(this );
	checkCaSR.addItemListener(this );
	checkNai.addItemListener(this );
	checkKi.addItemListener(this );
	checksm.addItemListener(this );
	checksh.addItemListener(this );
	checksj.addItemListener(this );
	checksxr1.addItemListener(this );
	checksxr2.addItemListener(this );
	checksxs.addItemListener(this );
	checksr.addItemListener(this );
	checkss.addItemListener(this );
	checksd.addItemListener(this );
	checksf.addItemListener(this );
	checksfca.addItemListener(this );
	checksg.addItemListener(this );
	checkvolt.addItemListener(this );

// set layout of buttons
	checkPanel.setLayout(new GridLayout(9,2));
	checkPanel.add(butp);
	checkPanel.add(checkvolt);
	checkPanel.add(checksm);
	checkPanel.add(checksh);
	checkPanel.add(checksj);
	checkPanel.add(checksxr1);
	checkPanel.add(checksxr2);
	checkPanel.add(checksxs);
	checkPanel.add(checksr);
	checkPanel.add(checkss);
	checkPanel.add(checksd);
	checkPanel.add(checksf);
	checkPanel.add(checksfca);
	checkPanel.add(checksg);
	checkPanel.add(checkCai);
	checkPanel.add(checkCaSR);
	checkPanel.add(checkNai);
	checkPanel.add(checkKi);


//      graphh
	bgraph = new Button ("Plot Currents");
        bgraph.addActionListener( this );
        add(bgraph);
        bgraph.reshape(150,295,135,28);
        bgraph.setBackground(background2);
	

//  graphh
        Choice options= new Choice();
        options.addItem("Epicell");
        options.addItem("Endocell");
        options.addItem("Mcell");
        options.addItemListener(this);
        add(options);
        options.reshape(0,295,110,28);




	setLayout(new BorderLayout() );
	add(checkPanel, BorderLayout.EAST);
	add(buttonPanel, BorderLayout.NORTH);

    }


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
        else{                                        //begin else
	    int volto=240;
	    int xo=34;
	    int sensmo=240;
	    int senCaio=240;
	    int senCaSRo=240;
	    int senNaio=240;
	    int senKio=170;
	    int sensho=240;
	    int sensjo=240;
	    int sensxr1o=240;
	    int sensxr2o=240;
	    int sensxso=240;
	    int sensro=240;
	    int sensso=240;
	    int sensdo=240;
	    int sensfo=240;
	    int sensfcao=240;
	    int sensgo=240;
        
	    int xpos=40;

//          if(ns1s < 10){
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

//                                      } 

         


           




///------------letters of gates plotted on screen--------------
	    if(senvolt==10){
		g.setColor(Color.red);
		g.drawString("V,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensm==10){
		g.setColor(new Color(12,100,12));
		g.drawString("m,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensh==10){
		g.setColor(Color.blue);
		g.drawString("h,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensj==10){
		g.setColor(Color.darkGray);
		g.drawString("j,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensxr1==10){
		g.setColor(Color.magenta);
		g.drawString("Xr1,",xpos,55);
		xpos=xpos+25;
	    }
	    if(sensxr2==10){
		g.setColor(new Color(234,161,0));
		g.drawString("Xr2,",xpos,55);
		xpos=xpos+25;
	    }

	    if(sensxs==10){
		g.setColor(new Color(128,128,0));
		g.drawString("Xs,",xpos,55);
		xpos=xpos+20;
	    }
	    if(sensr==10){
		g.setColor(Color.orange);
		g.drawString("r,",xpos,55);
		xpos=xpos+15;
	    }
	    if(senss==10){
		g.setColor(Color.cyan);
		g.drawString("s,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensd==10){
		g.setColor(new Color(213,240,133));
		g.drawString("d,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensf==10){
		g.setColor(new Color(141,21,255));
		g.drawString("f,",xpos,55);
		xpos=xpos+15;
	    }
	    if(sensfca==10){
		g.setColor(new Color(159,21,63));
		g.drawString("fca,",xpos,55);
		xpos=xpos+25;
		
	    }
	    if(sensg==10){
		g.setColor(new Color(235,48,68));
		g.drawString("g",xpos,55);
		xpos=xpos+14;
	    }
	    if(senCai==10){
		g.setColor(new Color(128,109,48));
		g.drawString("Cai,",xpos,55);
		xpos=xpos+24;
	    }
	    if(senCaSR==10){
		g.setColor(new Color(128,148,0));
		g.drawString("Casr,",xpos,55);
		xpos=xpos+30;
	    }
	    if(senNai==10){
		g.setColor(new Color(106,9,3));
		g.drawString("Nai,",xpos,55);
		xpos=xpos+24;
	    }
	    if(senKi==10){
		g.setColor(Color.green);
		g.drawString("Ki,",xpos,55);
		xpos=xpos+24;
	    }

//------------------end of letters---------------




// initial values
	    svolt=V_init;
	    svoltt=V_init;
	    svolt2=V_init;
	    Cai=Cai_init;
	    CaSR=CaSR_init;
	    Nai=Nai_init;
	    Ki=Ki_init;
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
	    
	    ttime=0;
	    nswitch=0;
	    ncounts1=1;
	    napdd=0;

	    nt_of_t=-1;
	    int mmtime=0;
	    for(int ntime=1; ntime<nend; ntime++){
		mmtime=mmtime+1;
		ttime=ttime+HT;

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



		if(ntime<60){
		    skip=1;
		    ncount=0;
		    if(ntime>10)Istim=stimstrength;
		}


//-----------skip check for speedup------------------
		ncount=ncount+1;
		if(ncount==skip){
		    ncount=0;

// Model implementation follows

// Needed to compute currents
		    Ek=RTONF*(Math.log((Ko/Ki)));
		    Ena=RTONF*(Math.log((Nao/Nai)));
		    Eks=RTONF*(Math.log((Ko+pKNa*Nao)/(Ki+pKNa*Nai)));
		    Eca=0.5*RTONF*(Math.log((Cao/Cai)));

// find indices into tables
		    zindexv=((svolt-vlo)/dvt);
		    indexv=(int)(zindexv);
//         if(indexv.gt.nvt) write(6,*) 'V outside table range ',svolt
		    if(zindexv > indexv){
			iv1=indexv;
			iv2=indexv+1;
		    }
		    else{
			iv1=indexv-1;
			iv2=indexv;
		    }
		    q1=zindexv-iv1;
		    q2=iv2-zindexv;
		    
		    zindexvmek=((svolt-Ek-vmeklo)/dvt);
		    indexvmek=(int)(zindexvmek);
//         if(indexvmek.gt.nvt) write(6,*) 'V-Ek outside table range ',
//     &        svolt,Ek
		    if(zindexvmek > indexvmek){
			ivmek1=indexvmek;
			ivmek2=indexvmek+1;     }
		    else{
			ivmek1=indexvmek-1;
			ivmek2=indexvmek;
		    }
		    qvmek1=zindexvmek-ivmek1;
		    qvmek2=ivmek2-zindexvmek;
		    
		    zindexc=((Cai-clo)/dct);
		    indexc=(int)(zindexc);
//         if(indexc.gt.nct) write(6,*) 'Cai outside table range ',Cai
		    if(zindexc > indexc){
			ic1=indexc;
			ic2=indexc+1;    }
		    else{
			ic1=indexc-1;
			ic2=indexc ; 
		    }
		    qc1=zindexc-ic1;
		    qc2=ic2-zindexc;


// Compute currents
		    INa=GNa*sm*sm*sm*sh*sj*(svolt-Ena);
//         exp2vfort=exp2vfortt[iv1]*q2+exp2vfortt[iv2]*q1;
		    exp2vfort=exp2vfortt[iv1]*q2+exp2vfortt[iv2]*q1;

		    ICaL=ICaLcoeff*sd*sf*sfca*svolt*(exp2vfort*Cai-0.341*Cao)/(exp2vfort-1.0);
		    Ito=Gto*sr*ss*(svolt-Ek);
		    IKr=Gkr*sqrtko54*sxr1*sxr2*(svolt-Ek);
		    IKs=Gks*sxs*sxs*(svolt-Eks);
		    IK1=IK1t[ivmek1]*qvmek2+IK1t[ivmek2]*qvmek1;
		    INaCa1=INaCa1t[iv1]*q2+INaCa1t[iv2]*q1;
		    INaCa2=INaCa2t[iv1]*q2+INaCa2t[iv2]*q1;
		    INaCa=INaCa1*Nai*Nai*Nai-INaCa2*Cai*2.5;
		    INaKcoeff=INaKcoefft[iv1]*q2+INaKcoefft[iv2]*q1;
		    INaK=INaKcoeff*(Nai/(Nai+KmNa));
		    IpCa=GpCa*Cai/(KpCa+Cai);
		    IpKcoeff=IpKcoefft[iv1]*q2+IpKcoefft[iv2]*q1;
		    IpK=IpKcoeff*(svolt-Ek);
		    IbNa=GbNa*(svolt-Ena);
		    IbCa=GbCa*(svolt-Eca);

// Determine total current
		    sItot = IKr+IKs+IK1+Ito+INa+IbNa+ICaL+IbCa+INaK+INaCa+IpCa+IpK+Istim;

     

// compute steady state values and time constants 
		    expTAU_M=exptaumt[iv1]*q2+exptaumt[iv2]*q1;
		    M_INF=m_inft[iv1]*q2+m_inft[iv2]*q1;

		    expTAU_H=exptauht[iv1]*q2+exptauht[iv2]*q1;
		    H_INF=h_inft[iv1]*q2+h_inft[iv2]*q1;

		    expTAU_J=exptaujt[iv1]*q2+exptaujt[iv2]*q1;
		    J_INF=H_INF;

		    Xr1_INF=xr1_inft[iv1]*q2+xr1_inft[iv2]*q1;
		    expTAU_Xr1=exptauxr1t[iv1]*q2+exptauxr1t[iv2]*q1;

		    Xr2_INF=xr2_inft[iv1]*q2+xr2_inft[iv2]*q1;
		    expTAU_Xr2=exptauxr2t[iv1]*q2+exptauxr2t[iv2]*q1;

		    Xs_INF=xs_inft[iv1]*q2+xs_inft[iv2]*q1;
		    expTAU_Xs=exptauxst[iv1]*q2+exptauxst[iv2]*q1;
    
		    R_INF=r_inft[iv1]*q2+r_inft[iv2]*q1;
		    S_INF=s_inft[iv1]*q2+s_inft[iv2]*q1;
		    expTAU_R=exptaurt[iv1]*q2+exptaurt[iv2]*q1;
		    expTAU_S=exptaust[iv1]*q2+exptaust[iv2]*q1;

		    D_INF=d_inft[iv1]*q2+d_inft[iv2]*q1;
		    expTAU_D=exptaudt[iv1]*q2+exptaudt[iv2]*q1;
		    F_INF=f_inft[iv1]*q2+f_inft[iv2]*q1;
		    expTAU_F=exptauft[iv1]*q2+exptauft[iv2]*q1;
     
		    FCa_INF=fcainft[ic1]*q2+fcainft[ic2]*q1;
		    G_INF=ginft[ic1]*qc2+ginft[ic2]*qc1;
 
		}      //endif of skip

// update concentrations
		Caisquare=Cai*Cai;
		CaSRsquare=CaSR*CaSR;
		CaCurrent=-(ICaL+IbCa+IpCa-2*INaCa)*inverseVcF2*CAPACITANCE;
		A=0.016464*CaSRsquare/(0.0625+CaSRsquare)+0.008232;
		Irel=A*sd*sg;
		Ileak=0.00008*(CaSR-Cai);
		SERCA=Vmaxup/(1.+(Kupsquare/Caisquare));
		CaSRCurrent=SERCA-Irel-Ileak;
		CaCSQN=Bufsr*CaSR/(CaSR+Kbufsr);
		dCaSR=HT*(Vc/Vsr)*CaSRCurrent;
		bjsr=Bufsr-CaCSQN-dCaSR-CaSR+Kbufsr;
		cjsr=Kbufsr*(CaCSQN+dCaSR+CaSR);
		CaSR=(Math.sqrt(bjsr*bjsr+4*cjsr)-bjsr)/2;
		CaBuf=Bufc*Cai/(Cai+Kbufc);
		dCai=HT*(CaCurrent-CaSRCurrent);
		bc=Bufc-CaBuf-dCai-Cai+Kbufc;
		cc=Kbufc*(CaBuf+dCai+Cai);
		Cai=(Math.sqrt(bc*bc+4*cc)-bc)/2;

		dNai=-(INa+IbNa+3*INaK+3*INaCa)*inverseVcF*CAPACITANCE;
		Nai=Nai+HT*dNai;

		dKi=-(Istim+IK1+Ito+IKr+IKs-2*INaK+IpK)*inverseVcF*CAPACITANCE;
// LATER CHANGE Ki!!!!
		Ki=Ki+HT*dKi;

// Update gates
		sm = M_INF-(M_INF-sm)*expTAU_M;
		sh = H_INF-(H_INF-sh)*expTAU_H;
		sj = J_INF-(J_INF-sj)*expTAU_J;
		sxr1 = Xr1_INF-(Xr1_INF-sxr1)*expTAU_Xr1;
		sxr2 = Xr2_INF-(Xr2_INF-sxr2)*expTAU_Xr2;
		sxs = Xs_INF-(Xs_INF-sxs)*expTAU_Xs;
		ss= S_INF-(S_INF-ss)*expTAU_S;
		sr= R_INF-(R_INF-sr)*expTAU_R;
		sd = D_INF-(D_INF-sd)*expTAU_D;
		sf =F_INF-(F_INF-sf)*expTAU_F;
		fcaold=sfca;
		sfca =FCa_INF-(FCa_INF-sfca)*exptaufca;
		if(sfca > fcaold && svolt > -60) {
		    sfca=fcaold;                   }
		gold=sg;
		sg =G_INF-(G_INF-sg)*exptaug;
		if(sg > gold && svolt > -60) {
		    sg=gold;                  }
     

// update voltage
		svoltt= svolt + HT*(-sItot);
		dvdt=(svoltt-svolt)/HT;
		skip=15;
   
		if(dvdt < -.1 || dvdt > .4){
		    skip=1;
		    ncount=0;
		} 

//         if(mod(ntime,100).eq.1) then
//          write(60,*) ntime*HT,svoltt,dvdt,skip*.1
//            write(61,*) ntime*HT,Cai,CaSR,Nai,Ki
//            write(62,*) ntime*HT,IKr,IKs,IK1,Ito
//            write(63,*) ntime*HT,INa,IbNa,ICaL,IbCa
//           write(64,*) ntime*HT,INaCa,IpCa,IpK,INaK
//           write(65,*) ntime*HT,Irel,Ileak,SERCA
//           write(66,*) ntime*HT,sm,sh,sj
//           write(67,*) ntime*HT,sxr1,sxr2,sxs
//           write(68,*) ntime*HT,ss,sr,sg
//           write(69,*) ntime*HT,sd,sf,sfca
//         endif
		if(ttime > stims1*(ns1s-1)+sdur){
        
		    if((svolt+78)*(svoltt+78)< 0){
			napdd=napdd+1;
			apdtime[napdd]=ttime;
		    } 
             
		    if(napdd==3){DII=apdtime[2]-apdtime[1];
                        APDD=apdtime[3]-apdtime[2];
		    }
		}

		svolt=svoltt; 

//---------plot on the screen and save for plotting currents -------------------

		if(mmtime==50){
		    mmtime=0;
		    int xf=(int)(35+ttime*395/(endtime));
		    
		    if(senvolt==10){
			int voltf=(int)((130-svolt*1.3));
			g.setColor(Color.red);
			g.drawLine(xo,volto,xf,voltf);
			volto=voltf;
		    }
		    if(sensm==10){
			int sensmf=(int)((240-sm*145));
			g.setColor(new Color(12,100,12));
			g.drawLine(xo,sensmo,xf,sensmf);
			sensmo=sensmf;
		    }

		    if(sensh==10){
			int senshf=(int)((240- sh*145));
			g.setColor(Color.blue);
			g.drawLine(xo, sensho,xf, senshf);
			sensho= senshf;
		    }

		    if(sensj==10){
			int sensjf=(int)((240-sj*145));
			g.setColor(Color.darkGray);
			g.drawLine(xo,sensjo,xf, sensjf);
			sensjo= sensjf;
		    }
		    if(sensxr1==10){
			int sensxr1f=(int)((240- sxr1*145));
			g.setColor(Color.magenta);
			g.drawLine(xo, sensxr1o,xf, sensxr1f);
			sensxr1o= sensxr1f;
		    }
		    if(sensxr2==10){
			int sensxr2f=(int)((240- sxr2*145));
			g.setColor(new Color(234,161,0));
			g.drawLine(xo, sensxr2o,xf, sensxr2f);
			sensxr2o= sensxr2f;
		    }

		    if(sensxs==10){
			int sensxsf=(int)((240- sxs*145));
			g.setColor(new Color(128,128,0));
			g.drawLine(xo, sensxso,xf, sensxsf);
			sensxso= sensxsf;
		    }
		    if(sensr==10){
			int sensrf=(int)((240- sr*145));
			g.setColor(Color.orange);
			g.drawLine(xo, sensro,xf, sensrf);
			sensro= sensrf;
		    }
		    if(senss==10){
			int senssf=(int)((240- ss*145));
			g.setColor(Color.cyan);
			g.drawLine(xo, sensso,xf, senssf);
			sensso= senssf;
		    }
		    if(sensd==10){
			int sensdf=(int)((240- sd*145));
			g.setColor(new Color(213,240,133));
			g.drawLine(xo, sensdo,xf, sensdf);
			sensdo= sensdf;
		    }
		    if(sensf==10){
			int sensff=(int)((240-sf*145));
			g.setColor(new Color(141,21,255));
			g.drawLine(xo, sensfo,xf, sensff);
			sensfo= sensff;
		    }
		    if(sensfca==10){
			int sensfcaf=(int)((240- sfca*145));
			g.setColor(new Color(159,21,63));
			g.drawLine(xo, sensfcao,xf, sensfcaf);
			sensfcao= sensfcaf;
		    }
		    if(sensg==10){
			int sensgf=(int)((240-sg*145));
			g.setColor(new Color(235,48,68));
			g.drawLine(xo,sensgo,xf,sensgf);
			sensgo= sensgf;
		    }
		    if(senCai==10){
			int senCaif=(int)((240- Cai*135*1000));
			g.setColor(new Color(128,109,48));
			g.drawLine(xo, senCaio,xf, senCaif);
			senCaio= senCaif;
		    }
		    if(senCaSR==10){
			int senCaSRf=(int)((240- CaSR*120));
			g.setColor(new Color(128,148,0));
			
			g.drawLine(xo, senCaSRo,xf, senCaSRf);
			senCaSRo= senCaSRf;
		    }
		    if(senNai==10){
			int senNaif=(int)((240- (Nai-11.5)*140));
			g.setColor(new Color(106,9,3));
			g.drawLine(xo, senNaio,xf, senNaif);
			senNaio= senNaif;
		    }
		    if(senKi==10){
			int senKif=(int)((170- (Ki-137)*10));
			g.setColor(Color.green);
			g.drawLine(xo, senKio,xf, senKif);
			senKio= senKif;
		    }

		    xo=xf;

//---------------graphhh

		    if(nt_of_t<90000){

			nt_of_t=nt_of_t+1;
			xna_of_t[nt_of_t]=(float)(INa);
			xk1_of_t[nt_of_t]=(float)(IK1);
			t_of_t[nt_of_t]=(float)(ttime);
			xbca_of_t[nt_of_t]=(float)(IbCa);
			xcal_of_t[nt_of_t]=(float)(ICaL);
			xto_of_t[nt_of_t] =(float)(Ito); 
			xkpk_of_t[nt_of_t] =(float)(IpK);
			xkr_of_t[nt_of_t] = (float)(IKr);
			xks_of_t[nt_of_t] = (float)(IKs);
			xpca_of_t[nt_of_t] = (float)(IpCa);
			xnak_of_t[nt_of_t] = (float)(INaK);
			xnaca_of_t[nt_of_t] = (float)(INaCa);
			xbna_of_t[nt_of_t] = (float)(IbNa);
			xtotal_of_t[nt_of_t] = (float)(sItot);

		    }

//--------------graphh-------------




		}   //end of saving for plotting


	    }   //enddo of time iteration
          
//            g.setColor(Color.white);
//            g.drawLine(35, 95,400, 95);


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

//    String asy=nf.format((double)(nt_of_t)); 
//    g.drawString("time="+String.valueOf(asy),270,95);

	} //end of else for not firstime


                                        }


    public void itemStateChanged( ItemEvent ae){
	//         if(checkm.getState()==true){ msen=10;}
	//         else{ msen=0;}
	if(checkvolt.getState()==true){ senvolt=10;}
	else{senvolt=0;}
	if(checkCai.getState()==true){ senCai=10;}
	else{senCai=0;}
	if(checkCaSR.getState()==true){senCaSR =10;}
	else{senCaSR =0;}
	if(checkNai.getState()==true){senNai =10;}
	else{senNai =0;}
	if(checkKi.getState()==true){senKi =10;}
	else{senKi =0;}
	if(checksm.getState()==true){sensm =10;}
	else{sensm =0;}
	if(checksh.getState()==true){sensh =10;}
	else{sensh =0;}
	if(checksj.getState()==true){sensj =10;}
	else{sensj =0;}
	if(checksxr1.getState()==true){sensxr1 =10;}
	else{sensxr1 =0;}
	if(checksxr2.getState()==true){sensxr2 =10;}
	else{sensxr2 =0;}
	if(checksxs.getState()==true){sensxs =10;}
	else{sensxs =0;}
	if(checksr.getState()==true){sensr =10;}
	else{sensr =0;}
	if(checkss.getState()==true){senss =10;}
	else{senss =0;}
	if(checksd.getState()==true){sensd =10;}
	else{sensd =0;}
	if(checksf.getState()==true){sensf =10;}
	else{sensf =0;}
	if(checksfca.getState()==true){sensfca =10;}
	else{sensfca =0;}
	if(checksg.getState()==true){sensg =10;}
	else{sensg =0;}

	if(ae.getItem().toString()== optionepi){
	    Gks=0.245;
	    Gto=0.294;
	    dvt=(vhi-vlo)/nvt;
	    for(int i=0; i<nvt; i++){
		vv=vlo+dvt*(double)(i);
		r_inft[i]=1./(1.+Math.exp((20-vv)/6.));
		s_inft[i]=1./(1.+Math.exp((vv+20)/5.));
		exptaurt[i]=Math.exp(-HT/(9.5*Math.exp(-(vv+40.)*(vv+40.)/1800.)+0.8));
		exptaust[i]=Math.exp(-HT/(85.*Math.exp(-(vv+45.)*(vv+45.)/320.)+5./(1.+Math.exp((vv-20.)/5.))+3.));
	    }

	}
	if(ae.getItem().toString()== optionendo){
	    Gks=0.245;
	    Gto=0.073;
	    dvt=(vhi-vlo)/nvt;
	    for(int i=0; i<nvt; i++){
		vv=vlo+dvt*(double)(i);
		r_inft[i]=1./(1.+Math.exp((20-vv)/6.));
		s_inft[i]=1./(1.+Math.exp((vv+20)/5.));
		exptaurt[i]=Math.exp(-HT/(9.5*Math.exp(-(vv+40.)*(vv+40.)/1800.)+0.8));
		exptaust[i]=Math.exp(-HT/(85.*Math.exp(-(vv+45.)*(vv+45.)/320.)+5./(1.+Math.exp((vv-20.)/5.))+3.));
	    }




	}
	if(ae.getItem().toString()== optionm){
	    Gks=0.062;
	    Gto=0.294;
	    dvt=(vhi-vlo)/nvt;
	    for(int i=0; i<nvt; i++){
		vv=vlo+dvt*(double)(i);
		r_inft[i]=1./(1.+Math.exp((20-vv)/6.));
		s_inft[i]=1./(1.+Math.exp((vv+28)/5.));
		exptaurt[i]=Math.exp(-HT/(9.5*Math.exp(-(vv+40.)*(vv+40.)/1800.)+0.8));
		exptaust[i]=Math.exp(-HT/(1000.*Math.exp(-(vv+67)*(vv+67)/1000.)+8.));
	    }
	    
	}





//--------------for plotting currents-------------------
//------------------  I_na  ----------------------
   
	if(plot_xna.getState()==true) { 
	    if(sg1 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;

		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
//       if(t_of_t[i]>=nnt_min && t_of_t[i]<=nnt_max){
		    jjj=jjj+1;
		    data[jjj]=xna_of_t[i];
//       data[jjj]=t_of_t[i];

//        data[jjj]=xcal_of_t[i];
//        data[jjj]=xbca_of_t[i];
		    
		    datat[jjj]=t_of_t[i];                      }  
//                                    }

		sg1 = new Graphs(data,datat,Graphs.NA,jjj); 
		sg1.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xna.setState(false); 
			    sg1 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xna.getState()==false) { 
	    if(sg1 != null) { 
		sg1.dispose(); 
		sg1 = null; 
	    } 
	} 
//--------------------------I_K1---------------
 
	if(plot_xk1.getState()==true) { 
	    if(sg2 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xk1_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  
		
		sg2 = new Graphs(data,datat,Graphs.K1,jjj); 
		sg2.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xk1.setState(false); 
			    sg2 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xk1.getState()==false) { 
	    if(sg2 != null) { 
		sg2.dispose(); 
		sg2 = null; 
	    } 
	} 

//------------------I_to----------------

	if(plot_xto.getState()==true) { 
	    if(sg3 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xto_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  

		sg3 = new Graphs(data,datat,Graphs.TO,jjj); 
		sg3.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xto.setState(false); 
			    sg3 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xto.getState()==false) { 
	    if(sg3 != null) { 
		sg3.dispose(); 
		sg3 = null; 
	    } 
	} 

//------------------I_pK----------------

	if(plot_xkpk.getState()==true) { 
	    if(sg4 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];
		
		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xkpk_of_t[i];
		    
		    datat[jjj]=t_of_t[i];                      }  

		sg4 = new Graphs(data,datat,Graphs.KUR,jjj); 
		sg4.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xkpk.setState(false); 
			    sg4 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xkpk.getState()==false) { 
	    if(sg4 != null) { 
		sg4.dispose(); 
		sg4 = null; 
	    } 
	} 

//------------------I_Kr----------------

	if(plot_xkr.getState()==true) { 
	    if(sg5 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xkr_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  
		
		sg5 = new Graphs(data,datat,Graphs.KR,jjj); 
		sg5.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xkr.setState(false); 
			    sg5 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xkr.getState()==false) { 
	    if(sg5 != null) { 
		sg5.dispose(); 
		sg5 = null; 
	    } 
	} 

//-----------------
//------------------I_Ks----------------

	if(plot_xks.getState()==true) { 
	    if(sg6 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xks_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  

		sg6 = new Graphs(data,datat,Graphs.KS,jjj); 
		sg6.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xks.setState(false); 
			    sg6 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xks.getState()==false) { 
	    if(sg6 != null) { 
		sg6.dispose(); 
		sg6 = null; 
	    } 
	} 

//-----------------

//------------------I_Kcal----------------

	if(plot_xcal.getState()==true) { 
	    if(sg7 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xcal_of_t[i];
		    
		    datat[jjj]=t_of_t[i];                      }  
		
		sg7 = new Graphs(data,datat,Graphs.CAL,jjj); 
		sg7.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xcal.setState(false); 
			    sg7 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xcal.getState()==false) { 
	    if(sg7 != null) { 
		sg7.dispose(); 
		sg7 = null; 
	    } 
	} 

//------------------I_pca----------------

	if(plot_xpca.getState()==true) { 
	    if(sg8 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xpca_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  
		
		sg8 = new Graphs(data,datat,Graphs.PCA,jjj); 
		sg8.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xpca.setState(false); 
			    sg8 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xcal.getState()==false) { 
	    if(sg8 != null) { 
		sg8.dispose(); 
		sg8 = null; 
	    } 
	} 

//------------------I_nak----------------

	if(plot_xnak.getState()==true) { 
	    if(sg9 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xnak_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  
		
		sg9 = new Graphs(data,datat,Graphs.NAK,jjj); 
		sg9.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xnak.setState(false); 
			    sg9 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xnak.getState()==false) { 
	    if(sg9 != null) { 
		sg9.dispose(); 
		sg9 = null; 
	    } 
	} 

//------------------I_naca----------------

	if(plot_xnaca.getState()==true) { 
	    if(sg10 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;

		
		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xnaca_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  
		
		sg10 = new Graphs(data,datat,Graphs.NACA,jjj); 
		sg10.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xnaca.setState(false); 
			    sg10 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xnaca.getState()==false) { 
	    if(sg10 != null) { 
		sg10.dispose(); 
		sg10 = null; 
	    } 
	} 
//------------------I_bna----------------

	if(plot_xbna.getState()==true) { 
	    if(sg11 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];
		
		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xbna_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  

		sg11 = new Graphs(data,datat,Graphs.BNA,jjj); 
		sg11.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xbna.setState(false); 
			    sg11 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xbna.getState()==false) { 
	    if(sg11 != null) { 
		sg11.dispose(); 
		sg11 = null; 
	    } 
	} 

//------------------I_bca----------------

	if(plot_xbca.getState()==true) { 
	    if(sg12 == null) { 

		float nnt_max=ntime;
		if(nnt_max>=90000)nnt_max=90000;
		float nnt_min=0;


		if(nt_of_t>=90000)nt_of_t=90000;


		float data[] = new float[nt_of_t];
		float datat[] = new float[nt_of_t];

		int jjj=-1;
		for(int i=0; i < nt_of_t; i++) {
		    jjj=jjj+1;
		    data[jjj]=xbca_of_t[i];
		    datat[jjj]=t_of_t[i];                      }  

		sg12 = new Graphs(data,datat,Graphs.BCA,jjj); 
		sg12.addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
			    plot_xbca.setState(false); 
			    sg12 = null; 
			} 
		    }); 
	    } 
	} 
	if(plot_xbca.getState()==false) { 
	    if(sg12 != null) { 
		sg12.dispose(); 
		sg12 = null; 
	    } 
	} 

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

//----------------end plotting--------------------






                                                    }

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
	endtime=xnstims2+400;
	nend=(int)(endtime/HT);
	ttime=0;

//         checksf.setState(true);


	inputS1.setText(inputS1.getText());
	inputS2.setText(inputS2.getText());
	inputNS1.setText(inputNS1.getText());

	iplotcurrent=0;
         
//---------------------graphhh----------------------------
	if(ae.getSource()==bgraph2){
	    iplotcurrent=1;
	    frame.dispose();
	    //            butp.setLabel("Stop");
                                       }
	if(ae.getSource()==bgraph){
	    frame= new Frame();
	    frame.setLayout(null);
	    frame.setResizable(true);
	    frame.setTitle("Currents");
	    frame.reshape(10,10,150,490);

	    bgraph2 = new Button ("Dismiss Window");
	    bgraph2.addActionListener( this );
	    frame.add(bgraph2);
	    bgraph2.reshape(5,60,140,35);

	    plot_xna= new Checkbox("Plot I_na", false);
	    plot_xna.addItemListener(this );
	    frame.add(plot_xna);
	    plot_xna.reshape(20,90,100,40);
	    
	    plot_xk1= new Checkbox("Plot  I_K1", false);
	    plot_xk1.addItemListener(this );
	    frame.add(plot_xk1);
	    plot_xk1.reshape(20,120,100,40);

	    plot_xto= new Checkbox("Plot  I_to", false);
	    plot_xto.addItemListener(this );
	    frame.add(plot_xto);
	    plot_xto.reshape(20,150,100,40);

	    plot_xkpk= new Checkbox("Plot  I_pK", false);
	    plot_xkpk.addItemListener(this );
	    frame.add(plot_xkpk);
	    plot_xkpk.reshape(20,180,100,40);

	    plot_xkr= new Checkbox("Plot  I_Kr", false);
	    plot_xkr.addItemListener(this );
	    frame.add(plot_xkr);
	    plot_xkr.reshape(20,210,100,40);

	    plot_xks= new Checkbox("Plot  I_Ks", false);
	    plot_xks.addItemListener(this );
	    frame.add(plot_xks);
	    plot_xks.reshape(20,240,100,40);

	    plot_xcal= new Checkbox("Plot  I_Ca", false);
	    plot_xcal.addItemListener(this );
	    frame.add(plot_xcal);
	    plot_xcal.reshape(20,270,100,40);

	    plot_xpca= new Checkbox("Plot  I_pCa", false);
	    plot_xpca.addItemListener(this );
	    frame.add(plot_xpca);
	    plot_xpca.reshape(20,300,100,40);

	    plot_xnak= new Checkbox("Plot  I_NaK", false);
	    plot_xnak.addItemListener(this );
	    frame.add(plot_xnak);
	    plot_xnak.reshape(20,330,100,40);

	    plot_xnaca= new Checkbox("Plot  I_NaCa", false);
	    plot_xnaca.addItemListener(this );
	    frame.add(plot_xnaca);
	    plot_xnaca.reshape(20,360,100,40);

	    plot_xbna= new Checkbox("Plot  I_bNa", false);
	    plot_xbna.addItemListener(this );
	    frame.add(plot_xbna);
	    plot_xbna.reshape(20,390,100,40);

	    plot_xbca= new Checkbox("Plot  I_bCa", false);
	    plot_xbca.addItemListener(this );
	    frame.add(plot_xbca);
	    plot_xbca.reshape(20,420,100,40);

	    plot_xtotal= new Checkbox("Plot  I_total", false);
	    plot_xtotal.addItemListener(this );
	    frame.add(plot_xtotal);
	    plot_xtotal.reshape(20,450,110,40);


            frame.setBackground(background2);
//           frame.repaint();
	    frame.show();

	}


//------------graphh----------------------------------





          
	if(ae.getSource()==butt){
//------initial conditions
	    svolt=V_init;
	    svoltt=V_init;
	    svolt2=V_init;
	    Cai=Cai_init;
	    CaSR=CaSR_init;
	    Nai=Nai_init;
	    Ki=Ki_init;
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
//--------------------------------
            
	    if(butt.getLabel().equals("Start")){
		butt.setBackground(background);
		butt.setLabel("Reset");
//      senCai=0;
//      senCaSR=0;
//      senNai=0;
//      senKi=0;
//      sensm=0;
//      sensh=0;
//      sensj=0;
//      sensxr1=0;
//      sensxr2=0;
//      sensxs=0;
//      sensr=0;
//      senss=0;
//      sensd=0;
//      sensf=0;
//      sensfca=0;
//      sensg=0;
//      senvolt=10;
//        frame.dispose();
		if(sg1 != null) { 
		    sg1.dispose(); 
		    sg1 = null; 
		} 
		if(sg2 != null) { 
		    sg2.dispose(); 
		    sg2 = null; 
		} 
		if(sg3 != null) { 
		    sg3.dispose(); 
		    sg3 = null; 
		} 
		if(sg4 != null) { 
		    sg4.dispose(); 
		    sg4 = null; 
		} 
		if(sg5 != null) { 
		    sg5.dispose(); 
		    sg5 = null; 
		} 
		if(sg6 != null) { 
		    sg6.dispose(); 
		    sg6 = null; 
		} 
		if(sg7 != null) { 
		    sg7.dispose(); 
		    sg7 = null; 
		} 
		if(sg8 != null) { 
		    sg8.dispose(); 
		    sg8 = null; 
		} 
		if(sg9 != null) { 
		    sg9.dispose(); 
		    sg9 = null; 
		} 
		if(sg10 != null) { 
		    sg10.dispose(); 
		    sg10 = null; 
		} 
		if(sg11 != null) { 
		    sg11.dispose(); 
		    sg11 = null; 
		} 
		if(sg12 != null) { 
		    sg12.dispose(); 
		    sg12 = null; 
		} 
		if(sg13 != null) { 
		    sg13.dispose(); 
		    sg13 = null; 
		} 
		if(frame != null) { 
		    frame.dispose(); 
		    frame = null; 
		} 






		firsttime=false;
		stims1=300;
		stims2=900;
		nswitch=0;
		ncounts1=1;
		ns1s=7;
		sdur=stimdur;
		xnstims1=stims1+sdur;
		xnstims2=stims1*(ns1s-1)+stims2+sdur;
		endtime=xnstims2+400;
		nend=(int)(endtime/HT);
		ttime=0;
		inputS1.setText("300");
		inputS2.setText("900");
		inputNS1.setText("7");

//	  repaint();
                                              }
	    else{
		if(sg1 != null) { 
		    sg1.dispose(); 
		    sg1 = null; 
		    plot_xna.setState(false);
		} 
		if(sg2 != null) { 
		    sg2.dispose(); 
		    sg2 = null; 
		    plot_xk1.setState(false);
		} 
		if(sg3 != null) { 
		    sg3.dispose(); 
		    sg3 = null; 
		    plot_xto.setState(false);
		} 
		if(sg4 != null) { 
		    sg4.dispose(); 
		    sg4 = null; 
		    plot_xkpk.setState(false);
		} 
		if(sg5 != null) { 
		    sg5.dispose(); 
		    sg5 = null; 
		    plot_xkr.setState(false);
		} 
		if(sg6 != null) { 
		    sg6.dispose(); 
		    sg6 = null; 
		    plot_xks.setState(false);
		} 
		if(sg7 != null) { 
		    sg7.dispose(); 
		    sg7 = null; 
		    plot_xcal.setState(false);
		} 
		if(sg8 != null) { 
		    sg8.dispose(); 
		    sg8 = null; 
		    plot_xpca.setState(false);
		} 
		if(sg9 != null) { 
		    sg9.dispose(); 
		    sg9 = null; 
		    plot_xnak.setState(false);
		} 
		if(sg10 != null) { 
		    sg10.dispose(); 
		    sg10 = null; 
		    plot_xnaca.setState(false);
		} 
		if(sg11 != null) { 
		    sg11.dispose(); 
		    sg11 = null; 
		    plot_xbna.setState(false);
		} 
		if(sg12 != null) { 
		    sg12.dispose(); 
		    sg12 = null; 
		    plot_xbca.setState(false);
		} 
		if(sg13 != null) { 
		    sg13.dispose(); 
		    sg13 = null; 
		    plot_xtotal.setState(false);
		} 
		if(frame != null) { 
		    frame.dispose(); 
		    frame = null; 
		} 


		butt.setLabel("Start");
		butt.setBackground(Color.green);

//         checksf.setState(false);


		firsttime=true;
		stims1=300;
		stims2=900;
		nswitch=0;
		ncounts1=1;
		ns1s=7;
		sdur=stimdur;
		xnstims1=stims1+sdur;
		xnstims2=stims1*(ns1s-1)+stims2+sdur;
		endtime=xnstims2+400;
		nend=(int)(endtime/HT);
		ttime=0;
		inputS1.setText("300");
		inputS2.setText("900");
		inputNS1.setText("7");

//           repaint();
	    }
                                    } 
	if(iplotcurrent==0)repaint();
    }

}
