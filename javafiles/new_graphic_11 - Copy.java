/*
 *  new_graphic_11
 *
 *	Flavio - Alessio game!
 *
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.lang.*;
import java.text.*;
import java.io.*;



public 	class 	new_graphic_11
	extends	Frame                 //NEW added MouseMotionListener
	implements MouseListener, MouseMotionListener, ActionListener, ItemListener, Runnable {
	
	//Interface declarations
	private Button		start, maskb, pause, movie; //NEW Stop Changed
	private Button		nxframe, prframe, nx2frame, pr2frame, pftsk, mftsk;
	private Button		save;
	private Button		apdDiMean, apdDiRest, apdClRest, cvRest;
	private Button		close, skipframe, settpplot, resettpplot, setRestScale;
	private Button		setpoint, setshift, seterase, spaaverage, apddi, thrset;
	private Button		napddiA, napddiB;
	
	private Checkbox 	signal1, signal2, signal3, signal4;
	private Checkbox	signal5, signal6, signal7, signal8;
	private Checkbox	apdDiDist, apdDiMeanS, apdSpace, diSpace;
	
	private Label 		file, comment, point, interval, apddieval;
	
	private TextField 	filename, tnii, tnjj, tfreq, erasesx, erasedx, thr;
	private TextField 	answer, frametoskip, tminpplot, tmaxpplot;
	private TextField	nnapddi;
	
	private TextArea 	note;
	
	private Choice 		cnorm, colormap, clDuration, alternance, errorBars;
	
	private Point p;
	private Point pzoom; //NEW
	int imask=-1;            //NEW
	int imaskf=-1;           //NEW
	int mousezoom=0;         //NEW
	int iipx;              //NEW moved from mouse section
	int iipy;

        //Comands for text files             //NNEW   for window with ascii files
        String optionFiles="V(t) to save:";
        String optionOriginal="Original";
        String optionUnwrapped="Unwrapped";
        String optionTaveraged="T-averaged";
        String optionShiftLine="Shift Line";
        String optionShifted="Shifted";
        String optionSpaceAver="Space Aver";
        String optionThresholds="Thresholds";
        String optionAPDDI="DIs APDs";

        DataWin da;
	
	//Commands for analysis
	boolean beginAnalyze	= false;		//start analysis
		
	boolean firsttime	= true;			//read and run standard analysis
	boolean shiftfirsttime	= false;		//run the shifting code 
	boolean erasefirsttime	= false;		//run the erasing code
	boolean spacefirsttime	= false;		//run the space time code
	boolean threshc		= false;		//run the APD - DI code
			
	//Commands for plot
	boolean originc 	= false;	//plot the original signal
	boolean unwrapc		= false;	//plot the unwrapped signal
	boolean tavec		= false;	//plot the time average signal
	boolean shiftl		= false;	//plot the shifting line
	boolean shiftc 		= false;	//plot the shifted signal
	boolean	erasec		= false;	//plot the erased signal
	boolean spaveragec	= false;	//plot the space averaged signal
	boolean thresholdc	= false;	//plot the threshold points
	boolean apddishow	= false;	//plot the restiturion
	boolean startmovie	= false;	//start movie plot
	boolean apdDistc	= false;	//APD - DI distribution
	boolean alternancec	= false;	//APD - DI alternance
	boolean apdDiMeanc	= false;	//APD - DI mean
	boolean apdSpacec	= false;	//APD space
	boolean diSpacec	= false;	//DI space
	boolean frame11		= false;	//Plot the structure
	boolean frame1p		= false;	//Plot the next - previous frame
	
	//Loading file declarations
	TextField 	displayFilename = new TextField(45);
	FileDialog	f 		= new FileDialog(this, "Load a File", FileDialog.LOAD);
	String 	namefilel 		= " ";
	String  sigsig			= " ";
	String 	oldText 		= " ";
	Dialog	IOError			= new Dialog(this, "Error reading file", true);
	Button	openButton		= new Button("Load file");
	Button	errorButton		= new Button("Try again");
		
	//Waiting window
	private TextField text1	= new TextField(25);	
	private Button wait = new Button(" Wait for calculations progress. ");
	private Button goplot = new Button(" Continue ");
	private Dialog waitwindow = new Dialog(this, "Progress", true);
	boolean closewindow = false;
	
	//Parameters
	public int nframes, sizex, sizey;
	int nf = 128, ntimes = 3000;      
	int freq, ifreq;
	int	nii = 60, njj = 60;   //NEW changed from 5 to 60n
	int nzoomx1, nzoomx2, nzoomy1, nzoomy2;  //NEW for zoom square
	int	ertime1, ertime2;
	int ur = 2500;
	short norma = 10000, normaO;
	
	//To normalize the plot
	int origmin = -10, origmax = 10, unwmin	= -10,  unwmax = 10;
	int nor1min = -10, nor1max = 10, tavnmin = -10, tavnmax = 10; 
	int shifmin = -10, shifmax = 10, ermin = -10, ermax = 10;
	int spamin  = -10,  spamax = 10;
	
	//To normalize the APD - DI distribution
	int mindi1=0, maxdi1=100, minapd1=0, maxapd1=100; //APD-DI up to 100 ms DI
	int mindi2=0, maxdi2=200, minapd2=0, maxapd2=200; //APD-DI up to 200 ms DI
	int mindi3=0, maxdi3=300, minapd3=0, maxapd3=300; //APD-DI up to 300 ms DI
	int mindi4=0, maxdi4=400, minapd4=0, maxapd4=400; //APD-DI up to 400 ms DI
	int mindi5=0, maxdi5=500, minapd5=0, maxapd5=500; //APD-DI up to 500 ms DI
	int mindi=mindi1, maxdi=maxdi1, minapd=minapd1, maxapd=maxapd1;
	int pmindi, pmaxdi, pminapd, pmaxapd;
	int clMaxc=0, pclMaxc=0;
	
	//APD - DI mean values
	//no alternance
	int meandi[][] = new int [nf][nf];
	int meanapd[][] = new int [nf][nf];
	//with alternance
	int mdiAo[][] 	= new int [nf][nf];
	int mapdAo[][] 	= new int [nf][nf];
	int mdiAe[][] 	= new int [nf][nf];
	int mapdAe[][] 	= new int [nf][nf];

	//Screen dimension  
	short lsx 	= 1150;		//screen width
	short lsy 	= 720;		//screen height
	//Plot position-dimensions
	int pw		= 700;		//plot width
	int ph		= 200;		//plot height
	int phm		= 200;		//plot height with movie
	int phG		= ph;		//plot height parameter
	int dpx		= 55;		//Distance from x window boundary
	int dpy		= 110;		//Distance from y window boundary
	
	int dax		= 60;		//Distance from x plot boundary
	int day 	= 35;		//Distance from y plot boundary
			
	int ss		= nf;		//square movie side
	int dsx		= dpx;		//Distance from x window boundary
	int dsy		= dpy + phm + 20;	//Distance from graph boundary	
			
	int axp1	= dpx + dax;	//x axes point 1
	int ayp1	= dpy + day;	//y axes point 1
	int axp2	= dpx + dax;	//x axes point 2
	int ayp2	= dpy + ph - day;	//y axes point 2
	int axp3	= dpx + pw - dax;	//x axes point 3
	int ayp3	= dpy + ph - day;	//y axes point 3
	int axp11, ayp11, axp21, ayp21, axp31, ayp31;
	
	// coord threshold circles
	int pcx1=0, pcy1=0, pcx2=0, pcy2=0;
	int pehbx1=0, pehbx2=0, pehby1=0, pehby2=0;	
	
	//Color maps
	int ir[]	= new int [260];
	int ig[]	= new int [260];
	int ib[]	= new int [260];
	int irr[]	= new int [260];
	int igg[]	= new int [260];
	int ibb[]	= new int [260];
	int irrr[]	= new int [260];
	int iggg[]	= new int [260];
	int ibbb[]	= new int [260];
			
	int n 			= -1;
	int nn			= 0;
	int nmap		= 0;
	int maplength	= 196;
	int norig		= 0;
	int errorb      = 3;
	int uflag		= 1;
	int napddi 		= 2; // number of apd/di to plot in space
	
	short 	video[][][] = new short[ntimes][nf][nf];
	short 	u[][][]		= new short[nf][nf][ntimes];
	int 	uu[][][]	= new int[nf][nf][ntimes];
	short 	mask[][] = new short[nf+12][nf+12];   //NEW 
	short	uumovie[][][] = new short[nf][nf][ntimes];
	short 	umovie[][][]	= new short[nf][nf][ntimes];
	short 	original[][][]	= new short[nf][nf][ntimes];
	short 	originalimg[][][]	= new short[nf][nf][ntimes];
	short 	unwrapped[][][] = new short[nf][nf][ntimes];
	short 	ounwrapped[][][] = new short[nf][nf][ntimes];
	short 	taveraged[][][] = new short[nf][nf][ntimes];
	short 	shifted[][][] 	= new short[nf][nf][ntimes];
	short 	basev1[][][] 	= new short[nf][nf][ntimes];
	short 	spaceaveraged[][][]  = new short[nf][nf][ntimes];
	short 	erased[][][] 	= new short[nf][nf][ntimes];
	short 	max[][] 	= new short[nf][nf];
	short	min[][] 	= new short[nf][nf];
	int  	maxINT[][] = new int[nf][nf];
	int 	minINT[][] = new int[nf][nf];
	int		maMa[]		= new int[2];
	int 	maMaApdDi[] = new int[4];
	int  	maxAInt[][] = new int[nf][nf];
	int 	minAInt[][] = new int[nf][nf];
	short	omax[][]	= new short[nf][nf];
	short 	omin[][]	= new short[nf][nf];
	short 	ntll[][] 	= new short[nf][nf];
	short	intervsh1[][]	= new short[nf][nf];
	short	intervsh2[][]	= new short[nf][nf];
	
	short	apd[][][]	= new short[nf][nf][ntimes];
	short	di[][][]	= new short[nf][nf][ntimes];
	short 	tpb[][][] 	= new short[nf][nf][ntimes];
	
	short	minDIn[][] = new short[nf][nf];
	short	maxDIn[][] = new short[nf][nf];
	short	minAPDn[][]	= new short[nf][nf];
	short	maxAPDn[][]	= new short[nf][nf];
	// Presence of alternance
	short	minDIAe[][]	= new short[nf][nf];
	short	maxDIAe[][]	= new short[nf][nf];
	short	minAPDAe[][] = new short[nf][nf];
	short	maxAPDAe[][] = new short[nf][nf];
	short	minDIAo[][]	= new short[nf][nf];
	short	maxDIAo[][]	= new short[nf][nf];
	short	minAPDAo[][] = new short[nf][nf];
	short	maxAPDAo[][] = new short[nf][nf];
	
	int 	nb[][] = new int [nf][nf]; //number of thresholds
	int 	ntr[][]	= new int [nf][nf]; //number of apd - di
	
	
	//Movie declarations
	Thread thread;
	IndexColorModel myColorModel1 ;
	IndexColorModel myColorModel2 = null;
	IndexColorModel myColorModel3 = null;
	Image Buffer;
	Graphics gBuffer;
	Label prompthoriz;
		
	//  NumberFormat nf = NumberFormat.getInstance(); 
	byte pix[] = new byte[ss*ss];
	int pixels[];
		
	int time = 0, prFrameTime;
	int deltamovietime = 1; //number of file to skipr while playing the movie
	int mintimeplot = 10, maxtimeplot = ntimes - 10;
	int pmintimeplot = 0;
	int movieon = 0;
	int index = 0;
	int hhhh2 = 0, mmmm2 = 0;
		
	MemoryImageSource source, sourcel;
	int plx1, ply1, plx2, ply2;
	Color background = new Color(65,130,183);
	Color background2 = new Color(234,240,255);
		
	public static void main(String args[]) {
		
		new_graphic_11 a = new new_graphic_11();
		
	}//close main
	
	
	//*************************************************************	
	//This method builts the graphical interface		
	public new_graphic_11() {
				
		super("Optical Mapping Analysis");
		
		setSize(lsx,lsy);
		setFont(new Font("Serif", Font.PLAIN, 16));
		setBackground(background);
		setLayout(new BorderLayout(30,30));
		
			//INTERFACE
		{
				//Load - save file - comment
			displayFilename.setFont(new Font("Serif", Font.PLAIN, 14));
			file 		= new Label("Save as: ");	//file to load
			comment	 	= new Label("Progress: ");
			filename	= new TextField("prova.txt", 15);	//file to save
			note		= new TextArea(4,18);
			
			// --- Buttons --------------------------------
			start 		= new Button("Start");
			maskb 		= new Button("Mask off"); //NEW
			pause 		= new Button("     ");
			nxframe		= new Button(" ");
			prframe		= new Button(" ");
			nx2frame	= new Button(" ");
			pr2frame	= new Button(" ");
			pftsk		= new Button(">");
			mftsk		= new Button("<");
			napddiA		= new Button("nx");
			napddiB		= new Button("pr");
			
			save  		= new Button("Save ");
			movie 		= new Button("Movie");
			apdDiMean	= new Button("Eval. Mean Rest.");
			apdDiRest	= new Button("APD-DI Restitution");
			apdClRest	= new Button("APD-CL");
			cvRest		= new Button("CV rest");
			close 		= new Button("Close");
			setpoint	= new Button("Set");
			setshift	= new Button("Apply");
			seterase	= new Button("Set");
			spaaverage	= new Button("Space Average");
			apddi		= new Button("Threshold");
			thrset		= new Button("Set");
			skipframe	= new Button("Set");
			settpplot	= new Button("Set");
			resettpplot = new Button("Default");
			setRestScale= new Button("Set");

                        Choice options= new Choice();       //NNEW choices 
                        options.addItem("V(t) to save:");
                        options.addItem("Original");
                        options.addItem("Unwrapped");
                        options.addItem("T-averaged");
                        options.addItem("Shift Line");
                        options.addItem("Shifted");
                        options.addItem("Space Aver");
                        options.addItem("Thresholds");
                        options.addItem("DI APD");
                        options.addItemListener(this);

			
			start.addActionListener(this);	
			maskb.addActionListener(this);	//NEW
			pause.addActionListener(this);
			nxframe.addActionListener(this);
			prframe.addActionListener(this);
			nx2frame.addActionListener(this);
			pr2frame.addActionListener(this);
			pftsk.addActionListener(this);
			mftsk.addActionListener(this);
			napddiA.addActionListener(this);
			napddiB.addActionListener(this);
			
			save.addActionListener(this);
			movie.addActionListener(this);
			apdDiMean.addActionListener(this);
			apdDiRest.addActionListener(this);
			apdClRest.addActionListener(this);
			cvRest.addActionListener(this);
			close.addActionListener(this);
			setpoint.addActionListener(this);
			setshift.addActionListener(this);
			seterase.addActionListener(this);
			spaaverage.addActionListener(this);
			apddi.addActionListener(this);
			thrset.addActionListener(this);
			skipframe.addActionListener(this);
			settpplot.addActionListener(this);
			resettpplot.addActionListener(this);
			setRestScale.addActionListener(this);
			
			addMouseListener(this);
			addMouseMotionListener(this);   //NEW
			// --- Labels and Texts --------------------------------
			point 	= new Label("Test point (x;y):\t", Label.LEFT);
			tnii 	= new TextField("60", 2);
			tnjj 	= new TextField("60", 2);
			tfreq 	= new TextField("180", 3);
			cnorm	= new Choice();
			cnorm.addItem("[0:10000]");
			cnorm.addItem("[0:1000]");
			tminpplot 	= new TextField("10", 3);
			tmaxpplot 	= new TextField("2990", 3);
			nnapddi		= new TextField("2",2);
			frametoskip = new TextField("2",2);
			
			interval= new Label("Erase intervals:\t", Label.LEFT);
			erasesx	= new TextField("100", 3);
			erasedx = new TextField("100", 3);
			thr	= new TextField("2500", 3);
			
			// --- Plot conditions ----------------------------------
			signal1 	= new Checkbox("Original",false);		
			signal2 	= new Checkbox("Unwrapped",false);
			signal3 	= new Checkbox("Taveraged",false);
			signal4 	= new Checkbox("Shift Line",false);
			signal5 	= new Checkbox("Shifted",false);
			signal6 	= new Checkbox("Erased",false);
			signal7 	= new Checkbox("Space aver",false);
			signal8 	= new Checkbox("Thresholds",false);
			apdDiDist	= new Checkbox("APD-DI Dist.",false);
			apdDiMeanS	= new Checkbox("APD-DI Mean.",false);
			apdSpace	= new Checkbox("APD Space",false);
			diSpace		= new Checkbox("DI Space",false);
			
			signal1.addItemListener(this);
			signal2.addItemListener(this);
			signal3.addItemListener(this);
			signal4.addItemListener(this);	
			signal5.addItemListener(this);
			signal6.addItemListener(this);
			signal7.addItemListener(this);
			signal8.addItemListener(this);
			apdDiDist.addItemListener(this);
			apdDiMeanS.addItemListener(this);
			apdSpace.addItemListener(this);
			diSpace.addItemListener(this);
			openButton.addActionListener(this);
			openButton.setBackground(Color.green);
			
				//Color Choice
			colormap =  new Choice();
			colormap.addItem("Red-Black");
			colormap.addItem("Red-Blue");
			colormap.addItem("Gray");
			colormap.addItemListener( this );
				//CL Choice
			clDuration	= new Choice();
			clDuration.addItem("100");
			clDuration.addItem("200");
			clDuration.addItem("300");
			clDuration.addItem("400");
			clDuration.addItem("500");
				//Alternance Choice
			alternance = new Choice();
			alternance.addItem("No Altern.");
			alternance.addItem("Alternance");
			alternance.addItemListener( this );
				//Error Bars Choice
			errorBars =  new Choice();
			errorBars.addItem("Both");
			errorBars.addItem("Horizontal");
			errorBars.addItem("Vertical");
			errorBars.addItem("No Bars");
			errorBars.addItemListener( this );
			
			// --- Color maps -------------------------------------------
			{
				byte r[]= new byte[228];
				byte g[]= new byte[228];
				byte b[]= new byte[228];
				
					//Color map 1: full
				createPalette1(r, g, b);
				myColorModel1 = new IndexColorModel(1, 228, r, g, b);
				
				for(int i = 0; i < 15; i++){
					n = n + 1;
					ir[i] = 0;
					ig[i] = 0 + (int)(n*3.3);
					ib[i] = 0 + (int)(n*6.6);
				}
				
				n = -1;
				for(int i = 15; i < 53; i++){
					n = n + 1;
					ir[i] = 0;
					ig[i] = 50 + (int)(2.7*n);
					ib[i] = 100;
				}
				
				n = -1;
				for(int i = 53; i < 90; i++){
					n = n + 1;
					ir[i] = 0;
					ig[i] = 150;
					ib[i] = 100 - (int)(2.5*n);
				}
				
				n = -1;
				for(int i = 90; i < 109; i++){
					n = n + 1;
					ir[i] = 0 + (int)(n*6.58);
					ig[i] = 140 - (int)(n*2.23);
					ib[i] = 0;
				}
				
				n = -1;
				for(int i = 109; i < 128; i++){
					n = n + 1;
					ir[i] = 120 + (int)(n*4.41);
					ig[i] = 100 - (int)(n*5.52);
					ib[i] = 0;
				}
				
				n = -1;
				for(int i = 128; i < 147; i++){
					n = n + 1;
					ir[i] = 200;
					ig[i] = 0 + (int)(n*2);
					ib[i] = 0;
				}
				
				n = -1;
				for(int i = 147; i < maplength - 1; i++){
					n = n + 1;
					ir[i] = 209 + (int)(n*0.441);
					ig[i] = 36 + (int)(n*2);
					ib[i] = 0;
				}
					//Color map 2: reversed
				createPalette2(r, g, b);
				myColorModel2 = new IndexColorModel(1, 228, r, g, b);
				
				n 	= 0;
				nn 	= 0;
				
				for(int i = 0; i < 98; i++){
					irr[i] = 0;
					igg[i] = 0 + (int)(2.55*n);
					ibb[i] = 255 - (int)(2.55*n);
					n = n + 1;
				}
				n = 1;
				for(int i = 98; i < maplength; i++){
					irr[i] = 0 + (int)(2.55*n);
					igg[i] = igg[97] - (int)(2.55*nn);
					ibb[i] = 0;
					n = n + 1;
					nn = nn +1;
				}
					//Color map 3: gray scale
				createPalette3(r, g, b);
				myColorModel3 = new IndexColorModel(1, 228, r, g, b);
				//for(int i = 0; i < maplength; i++){
				for(int i = 0; i < 255; i++){
					irrr[i] = (int)(1.*i);
					iggg[i] = (int)(1.*i);
					ibbb[i] = (int)(1.*i);
				}
			}//end color map		
			
				//Elements Position
			{
				// --- North -------------------------------------------------
				Panel rowN = new Panel();
				rowN.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowN.add	( openButton );
				rowN.add	( displayFilename );
				rowN.add	( file );
				rowN.add	( filename );
				rowN.add	( save );
                                rowN.add        (options);                    //NNEW added options
				add 		( rowN, BorderLayout.NORTH );
				
				// --- South -------------------------------------------------
				Panel rowS = new Panel();
				rowS.setLayout(new GridLayout(1,3,20,20));
				// --- First column ---
				Panel rowS2 = new Panel();
				rowS2.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS2.add	( point );
				rowS2.add	( tnii );
				rowS2.add	( tnjj );
				rowS2.add	( setpoint );
				
				Panel rowS3 = new Panel();
				rowS3.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS3.add	( new Label("APD - DI Thr:\t"));
				rowS3.add 	( thr );
				rowS3.add	( signal8 );
				rowS3.add	( thrset );
				
				Panel rowS4 = new Panel();
				rowS4.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS4.add	( new Label("Frame to skip:\t"));
				rowS4.add	( frametoskip );
				rowS4.add	( mftsk );
				rowS4.add	( pftsk );
				rowS4.add	( skipframe );
				
				Panel rowS234 = new Panel();
				rowS234.setLayout(new GridLayout(3,1));
				rowS234.add	( rowS2 );
				rowS234.add	( rowS3 );
				rowS234.add	( rowS4 );
				rowS.add	( rowS234 );
				// --- Second column ---
				Panel rowS41 = new Panel();
				rowS41.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS41.add	( new Label("T1") );
				rowS41.add	( tminpplot );
				rowS41.add	( new Label("T2") );
				rowS41.add	( tmaxpplot );
				rowS41.add	( settpplot );
				rowS41.add	( resettpplot );
				
				Panel rowS42 = new Panel();
				rowS42.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS42.add	( new Label("Actual APD/DI:") );
				rowS42.add	( nnapddi );
				rowS42.add	( napddiB );
				rowS42.add	( napddiA );
				
				
				Panel rowSc1r = new Panel();
				rowSc1r.setLayout(new GridLayout(3,1) );
				rowSc1r.add	( rowS42 );
				rowSc1r.add ( rowS41 );
				
				rowS.add	( rowSc1r );
				// --- Third column --- 
				Panel rowS1 = new Panel();
				rowS1.setLayout(new FlowLayout(FlowLayout.RIGHT));
				rowS1.add	( note );
				rowS1.add	( close );
				rowS.add	( rowS1 );
				
				add 		( rowS, BorderLayout.SOUTH );
				
				// --- West -------------------------------------------------
				Panel rowW = new Panel();
				rowW.setLayout(new GridLayout(10,1,2,2));
				rowW.add	( start );
				rowW.add	( maskb ); //NEW
				rowW.add	( movie );
				rowW.add	( pause );
				
				Panel rowWfpm = new Panel();
				rowWfpm.setLayout(new GridLayout(2,2,2,2));
				rowWfpm.add	( nxframe );
				rowWfpm.add	( prframe );
				rowWfpm.add	( nx2frame );
				rowWfpm.add	( pr2frame );
				rowW.add	( rowWfpm );
				
				add 		( rowW, BorderLayout.WEST );
				
				// --- East -------------------------------------------------
				Panel rowE = new Panel();	//General west layout
				rowE.setLayout(new GridLayout(15,1));
				
				Panel rowE82 = new Panel();		//Color maps and Max CL
				rowE82.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE82.add	( new Label("Movie Color:\t"));
				rowE82.add	( colormap );
				rowE.add	( rowE82 );
				
				Panel rowE85 = new Panel();		//Max CL
				rowE85.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE85.add	( new Label("Max scaling CL:\t"));
				rowE85.add	( clDuration );
				rowE85.add	( setRestScale );
				rowE.add	( rowE85 );
				
				Panel rowE4 = new Panel();	//Test point
				rowE4.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE4.add 	( new Label("Error Bars:\t\t") );
				rowE4.add	( errorBars );
				rowE.add	( rowE4 );
				
				Panel rowE40 = new Panel();	//Erasing interval
				rowE40.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE40.add	( interval );
				rowE40.add	( erasesx );
				rowE40.add	( erasedx );
				rowE40.add	( seterase );
				rowE.add	( rowE40 );
				
				Panel rowE2 = new Panel();	//Shift freq
				rowE2.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE2.add	( new Label("Shift frequency:\t") );
				rowE2.add	( tfreq );
				rowE2.add	( setshift );
				rowE.add	( rowE2 );
				
				Panel rowE99 = new Panel();	//Performe space average 
				rowE99.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE99.add	( new Label("Performe average:\t") );
				rowE99.add	( spaaverage );
				rowE.add	( rowE99 );
				
				Panel rowE991 = new Panel(); //Evaluate thresholds and APD - DI
				rowE991.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE991.add	( new Label("Eval. Thresholds:\t\t") );
				rowE991.add	( apddi );
				rowE.add	( rowE991 );
				
				Panel rowE83 = new Panel();	//Color maps and Max CL
				rowE83.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE83.add	( alternance );
				rowE83.add	( apdDiMean );
				rowE.add	( rowE83 );
				
				Panel rowE5 = new Panel(); //Original and unwrapped
				rowE5.setLayout(new GridLayout(1,2));
				rowE5.add	( signal1 );
				rowE5.add	( signal2 );
				rowE.add	( rowE5 );
				
				Panel rowE6 = new Panel(); //Time aver and shifting line
				rowE6.setLayout(new GridLayout(1,2));
				rowE6.add	( signal3 );
				rowE6.add	( signal4 );
				rowE.add	( rowE6 );
				
				Panel rowE7 = new Panel(); //Shifted and erased
				rowE7.setLayout(new GridLayout(1,2));
				rowE7.add	( signal5 );
				rowE7.add	( signal6 );
				rowE.add	( rowE7 );
				
				Panel rowE81 = new Panel();	//Space averaged - thresholds
				rowE81.setLayout(new GridLayout(1,2));
				rowE81.add	( signal7 );
				rowE81.add	( signal8 );
				rowE.add	( rowE81 );
				
				Panel rowE84 = new Panel();	
				rowE84.setLayout(new GridLayout(1,2));
				rowE84.add	( apdDiDist );
				rowE84.add	( apdDiMeanS );
				rowE.add	( rowE84 );
				
				Panel rowE86 = new Panel();	
				rowE86.setLayout(new GridLayout(1,2));
				rowE86.add	( apdSpace );
				rowE86.add	( diSpace );
				rowE.add	( rowE86 );
				
				add		 	( rowE, BorderLayout.EAST );
				
			}//end element position
			
		}//end interface
		
		//Waiting window
		{
			waitwindow.setSize(300,150);
			waitwindow.add( wait );
				//waitwindow.setModal(true);
		}
		
		setVisible(true);
		Close c = new Close();
		addWindowListener(c);
	}//close graphic -> interfase
	//*************************************************************
	
	//*************************************************************
	//This method plots the signals
	public void paint(Graphics g) {

		Buffer=createImage(dpx+pw+30, dsy+ph+2*ss);
        gBuffer=Buffer.getGraphics();
		gBuffer.setColor(background2);
		gBuffer.fillRect(dpx-15, dpy-20, pw+dpx+30, dsy+ph+2*ss); 
		//Plot parameters
		int pp1 = 0, pp2 = 0, kk1 = 0, kk2 = 0;
		//Graph plot
		if (startmovie == false) {

			// Graph dimensions without movie and restitutions
			phG = ph;				
			ayp2 = dpy + phG - day;		
			ayp3 = dpy + phG - day;						
			gBuffer.setColor(Color.black);
			gBuffer.fillRect(dpx, dpy, pw, ph);
			gBuffer.setColor(Color.white);
			gBuffer.drawLine(axp1,ayp1,axp2,ayp2);
			gBuffer.drawLine(axp2,ayp2,axp3,ayp3);				
			gBuffer.drawString("Time (ms)", dpx+pw/2, phG+dpy-day/3);
			gBuffer.drawString("V (mV)", dpx+dax/4, dpy+day/2+2);
			
			// Restitution square
			axp11 = dsx+pw-2*ss+10;
			ayp11 = dsy+10;
			axp21 = axp11;		
			ayp21 = dsy+2*ss-10;
			axp31 = dsx+pw-10;
			ayp31 = ayp21;		
			gBuffer.setColor(Color.black);
			gBuffer.fillRect(dsx+pw-2*ss, dsy, 2*ss, 2*ss);
			gBuffer.setColor(Color.white);
			gBuffer.drawLine(axp11,ayp11,axp21,ayp21);
			gBuffer.drawLine(axp21,ayp21,axp31,ayp31);
			gBuffer.setColor(Color.black);
			
				// Original -> signal1
			if (originc) {
				gBuffer.setColor(Color.red);
				gBuffer.drawString("OrSi", pw/3, dpy+day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				origmin = omin[nii][njj];
				origmax	= omax[nii][njj];
				gBuffer.drawString(Integer.toString(origmin), dpx+dax/5, dpy+phG-day-1);
				gBuffer.drawString(Integer.toString(origmax), dpx+dax/5, dpy+day+10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = origmax - origmin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(original[nii][njj][t]-origmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(original[nii][njj][t+1]-origmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}
			}// end original	
			
				// Unwrapped -> signal2
			if (unwrapc) {
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("UnwS", pw/3 + dax + 2, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(unwmin), dpx+dax/5, dpy+phG-day-1);
				gBuffer.drawString(Integer.toString(unwmax), dpx+dax/5, dpy+day+10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = unwmax - unwmin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t+1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(unwrapped[nii][njj][t]-unwmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(unwrapped[nii][njj][t+1]-unwmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}	
			}// end unwrapped 
			
				// Time averaged -> signal3
			if (tavec) {
				gBuffer.setColor(Color.green);
				gBuffer.drawString("TASi", pw/3 + 2*dax + 4, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(tavnmin), dpx+dax/5, dpy+phG-day-1);
				gBuffer.drawString(Integer.toString(tavnmax), dpx+dax/5, dpy+day+10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = tavnmax - tavnmin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(taveraged[nii][njj][t]-tavnmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(taveraged[nii][njj][t+1]-tavnmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}
			}// end time averaged 
			
				// Shifted line -> signal4 
			if (shiftl) {
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("ShLn", pw/3 + 3*dax + 6, dpy + day/2);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = tavnmax - tavnmin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(basev1[nii][njj][t]-tavnmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(basev1[nii][njj][t+1]-tavnmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}
			}// end shifted line
			
				// Shifted -> signal5
			if (shiftc) {
				gBuffer.setColor(Color.white);
				gBuffer.drawString("ShSi", pw/3 + 4*dax + 8, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(shifmin), dpx + dax/5, dpy + phG - day - 1);
				gBuffer.drawString(Integer.toString(shifmax), dpx + dax/5, dpy + day + 10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = shifmax - shifmin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(shifted[nii][njj][t]-shifmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(shifted[nii][njj][t+1]-shifmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}	
			}//end shift signal
			
				//Erased -> signal6
			if (erasec) {
				gBuffer.setColor(Color.magenta);
				gBuffer.drawString("SeSi", pw/3 + 5*dax + 4, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(ermin), dpx + dax/5, dpy + phG - day - 1);
				gBuffer.drawString(Integer.toString(ermax), dpx + dax/5, dpy + day + 10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = ermax - ermin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(erased[nii][njj][t]-ermin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(erased[nii][njj][t+1]-ermin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}
			}// close erase
			
				// Space averaged -> signal7
			if (spaveragec) {
				gBuffer.setColor(Color.red);
				gBuffer.drawString("SpAvSi", pw/3 + 6*dax + 4, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(spamin), dpx + dax/5, dpy + phG - day - 1);
				gBuffer.drawString(Integer.toString(spamax), dpx + dax/5, dpy + day + 10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = spamax - spamin;
				for (int t = mintimeplot; t < maxtimeplot; t++) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					kk1=dpy+phG-1*day-(phG-2*day)*(spaceaveraged[nii][njj][t]-spamin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(spaceaveraged[nii][njj][t+1]-spamin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}			
			}// close space average
			
				// APD - DI thresholds-> signal8
			if (thresholdc) {
				
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("APD - DI thresholds\n", pw/4 + dax, dpy + day/2);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = max[nii][njj]-min[nii][njj];
				for (int t = 1; t < nb[nii][njj]; t++) {
					if (tpb[nii][njj][t] >= mintimeplot && tpb[nii][njj][t] <= maxtimeplot) {
						pcx1=dpx+dax+(tpb[nii][njj][t]-mintimeplot)*(pw-2*dax)/(deltaTn);	
						pcy1=dpy+phG-1*day-(phG-2*day)*(ur-min[nii][njj])/(deltaUn);
						gBuffer.fillOval(pcx1 - 0, pcy1 - 0, 2*2, 2*2);
					}
				}
				gBuffer.drawString(Integer.toString(ur), dpx + dax/4, pcy1);
								
			}//close APD - DI thresholds
			
			// ----------------- Structure -------------------
			
				// Static frame for STRUCTURE and MASK.
			if (frame11) {
				int ftime = 350; //Default frame
				for (int ii = 0; ii < maplength; ii += 1){
					gBuffer.setColor(new Color(irrr[ii],iggg[ii],ibbb[ii]));
					gBuffer.fillRect(dsx+2*ss+10,dsy+2*ss-16-ii,10,1);
				}
				int iii = 0;
				for (int i = 0; i < ss; i++) {
					for (int j = 0; j < ss; j++) {
						if(mask[i+6][j+6] == 1){ //NEW if statment
							if(original[i][j][ftime] < 0){
								iii=(int)(255*(-original[i][j][ftime]))/(32767);
							}
							else {
								iii=(int)(255*(original[i][j][ftime]))/(32767);
							}
						}
						else {
							iii = 0;
						}
						
						if (iii > 255) iii = 0;
						if (iii < 0) iii = 0;
						gBuffer.setColor(new Color(irrr[iii],iggg[iii],ibbb[iii]));
						gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
					}
				}
					// Point of interest
				gBuffer.setColor(Color.red);
				gBuffer.fillRect(dsx+nii*2-2,dsy+njj*2-2, 4,4);	
					// Map scale min - max
				gBuffer.setColor(Color.black);
				//gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString(" Vmax",dsx+2*ss,dsy+2*ss-maplength-19);
				gBuffer.drawString(" Vmin",dsx+2*ss,dsy+2*ss);
			}//end static frame
			
			// ----------------- Restitution -------------------
			
				// APD - DI distribution 
			if (apdDistc) {
				
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("DI (ms)", dsx+pw-ss-4, dsy+2*ss+15);
				gBuffer.drawString("APD", dsx+pw-2*ss-35, dsy+ss);
				gBuffer.drawString("(ms)", dsx+pw-2*ss-35, dsy+ss+10);
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("APD - DI distribution points", dsx+pw-ss-3*ss/4, dsy+14);
				
				gBuffer.setColor(Color.black);
				gBuffer.drawString(Integer.toString(mindi), dsx+pw-2*ss+4, dsy+2*ss+15);
				gBuffer.drawString(Integer.toString(maxdi), dsx+pw-20, dsy+2*ss+15);
				gBuffer.drawString(Integer.toString(minapd), dsx+pw-2*ss-25, dsy+2*ss-4);
				gBuffer.drawString(Integer.toString(maxapd), dsx+pw-2*ss-25, dsy+20);
				gBuffer.setColor(Color.yellow);
				for (int n = 1; n < ntr[nii][njj]; n++) {
					if (maxdi>mindi && maxapd>minapd) {
						pcx1=dsx+pw-2*ss+10 + (di[nii][njj][n]-mindi)*(2*ss-20)/(maxdi-mindi);
						pcy1=dsy+2*ss-10 - (apd[nii][njj][n]-minapd)*(2*ss-20)/(maxapd-minapd);
						gBuffer.fillOval(pcx1-2, pcy1-2, 2, 2);
					}
				}
			}// close APD - DI distribution
			
				// APD - DI mean value
			if (apdDiMeanc) {

				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("DI (ms)", dsx+pw-ss-4, dsy+2*ss+15);
				gBuffer.drawString("APD", dsx+pw-2*ss-35, dsy+ss);
				gBuffer.drawString("(ms)", dsx+pw-2*ss-35, dsy+ss+10);
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("APD - DI restitution curve", dsx+pw-ss-3*ss/4, dsy+14);
				
				gBuffer.setColor(Color.black);
				gBuffer.drawString(Integer.toString(mindi), dsx+pw-2*ss+4, dsy+2*ss+15);
				gBuffer.drawString(Integer.toString(maxdi), dsx+pw-20, dsy+2*ss+15);
				gBuffer.drawString(Integer.toString(minapd), dsx+pw-2*ss-25, dsy+2*ss-4);
				gBuffer.drawString(Integer.toString(maxapd), dsx+pw-2*ss-25, dsy+20);
				
				if (alternancec) {
					int rr = 2;
					// Odd
					gBuffer.setColor(Color.red);
					pcx1 = dsx+pw-2*ss+10 + mdiAo[nii][njj]*(2*ss - 20)/(maxdi - mindi);
					pcy1 = dsy+2*ss-10 - (2*ss - 20)*(mapdAo[nii][njj] - minapd)/(maxapd - minapd);
					gBuffer.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
					// Error bars
					if (errorb == 3){
							//No error bars
					}
					if (errorb == 1) {
							//Horizontal only
						pehbx1 = dsx+pw-2*ss+10 + minDIAe[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehbx2 = dsx+pw-2*ss+10 + maxDIAe[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
					}
					else if (errorb == 2){
							//Vertical only
						pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAe[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAe[nii][njj] - minapd)/(maxapd - minapd);
						gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
					else if (errorb == 0){
							//horizontal & vertical
						pehbx1 = dsx+pw-2*ss+10 + minDIAe[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehbx2 = dsx+pw-2*ss+10 + maxDIAe[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAe[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAe[nii][njj] - minapd)/(maxapd - minapd);
						
						gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
					
					// Even
					gBuffer.setColor(Color.orange);
					pcx1 = dsx+pw-2*ss+10 + mdiAe[nii][njj]*(2*ss - 20)/(maxdi - mindi);
					pcy1 = dsy+2*ss-10 - (2*ss - 20)*(mapdAe[nii][njj] - minapd)/(maxapd - minapd);
					gBuffer.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
					// Error bars
					if (errorb == 3){
							//No error bars
					}
					if (errorb == 1) {
							//Horizontal only
						pehbx1 = dsx+pw-2*ss+10 + minDIAo[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehbx2 = dsx+pw-2*ss+10 + maxDIAo[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
					}
					else if (errorb == 2){
							//Vertical only
						pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAo[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAo[nii][njj] - minapd)/(maxapd - minapd);
						gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
					else if (errorb == 0){
							//horizontal & vertical
						pehbx1 = dsx+pw-2*ss+10 + minDIAo[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehbx2 = dsx+pw-2*ss+10 + maxDIAo[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAo[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAo[nii][njj] - minapd)/(maxapd - minapd);
						
						gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
				}
				else {
					
					int rr = 2;
					gBuffer.setColor(Color.green);
					pcx1 = dsx+pw-2*ss+10 + meandi[nii][njj]*(2*ss - 20)/(maxdi - mindi);
					pcy1 = dsy+2*ss-10 - (2*ss - 20)*(meanapd[nii][njj] - minapd)/(maxapd - minapd);
					gBuffer.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
					
					//Error bars
					gBuffer.setColor(Color.red);
					if (errorb == 3){
						//No error bars
					}
					if (errorb == 1) {
						//Horizontal only
						pehbx1 = dsx+pw-2*ss+10 + minDIn[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehbx2 = dsx+pw-2*ss+10 + maxDIn[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
					}
					else if (errorb == 2){
						//Vertical only
						pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDn[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDn[nii][njj] - minapd)/(maxapd - minapd);
						gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
					else if (errorb == 0){
						//horizontal & vertical
						pehbx1 = dsx+pw-2*ss+10 + minDIn[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehbx2 = dsx+pw-2*ss+10 + maxDIn[nii][njj]*(2*ss - 20)/(maxdi - mindi);
						pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDn[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDn[nii][njj] - minapd)/(maxapd - minapd);
						
						gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
				}				
			}// close APD - DI mean value
			
				// APD space
			if (apdSpacec) {
			
				if (alternancec) {
					/*
					for (int ii = 0; ii < maplength; ii += 1){
						gBuffer.setColor(new Color(irrr[ii], iggg[ii], ibbb[ii]));
						gBuffer.fillRect(dsx+2*ss+10,dsy+2*ss-ii,10,1);
					}
					*/
					int iii = 0;
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							iii=(int)(255*(apd[i][j][napddi]))/(250);
							if (iii > 255) iii = 0;
							if (iii < 0) iii = 0;
							gBuffer.setColor(new Color(irrr[iii], iggg[iii], ibbb[iii]));
							gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
						}
					}
					/*
						// Point of interest
					gBuffer.setColor(Color.red);
					gBuffer.fillRect(dsx+nii*2-2,dsy+njj*2-2, 4,4);	
						// Map scale min - max
					gBuffer.setColor(Color.black);
					gBuffer.drawString(" V max",dsx+2*ss+20,dsy+2*ss-maplength);
					gBuffer.drawString(" V min",dsx+2*ss+20,dsy+2*ss);
					*/
				}
				else {
					int iii = 0;
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							iii=(int)(255*(apd[i][j][napddi]))/(250);
							if (iii > 255) iii = 0;
							if (iii < 0) iii = 0;
							gBuffer.setColor(new Color(irrr[iii], iggg[iii], ibbb[iii]));
							gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
						}
					}
				}
			}// close APD space
			
				// DI space
			if (diSpacec) {
				
				if (alternancec) {
					/*
					 for (int ii = 0; ii < maplength; ii += 1){
					 gBuffer.setColor(new Color(irrr[ii], iggg[ii], ibbb[ii]));
					 gBuffer.fillRect(dsx+2*ss+10,dsy+2*ss-ii,10,1);
					 }
					 */	
					int iii = 0;
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							iii=(int)(255*(di[i][j][napddi]))/(250);
							//iii=(int)(255*(di[i][j][napddi]))/(minDIAe[nii][njj]-minDIAe[nii][njj]);
							if (iii > 255) iii = 0;
							if (iii < 0) iii = 0;
							gBuffer.setColor(new Color(irrr[iii], iggg[iii], ibbb[iii]));
							gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
						}
					}
					/*
					 // Point of interest
					 gBuffer.setColor(Color.red);
					 gBuffer.fillRect(dsx+nii*2-2,dsy+njj*2-2, 4,4);	
					 // Map scale min - max
					 gBuffer.setColor(Color.black);
					 gBuffer.drawString(" V max",dsx+2*ss+20,dsy+2*ss-maplength);
					 gBuffer.drawString(" V min",dsx+2*ss+20,dsy+2*ss);
					 */
				}
				else {
					int iii = 0;
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							iii=(int)(255*(di[i][j][napddi]))/(250);
							if (iii > 255) iii = 0;
							if (iii < 0) iii = 0;
							gBuffer.setColor(new Color(irrr[iii], iggg[iii], ibbb[iii]));
							gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
						}
					}
				}
				
				
				
			}// close DI space
			
			// ----------------- ----------- -------------------

		}// close if normal plots
		// *** Plot movie ***
		else {
				
			//Change graph dimensions
			phG = phm;
			ayp2	= dpy + phG - day;	
			ayp3	= dpy + phG - day;	
			//Resized graph plot
			gBuffer.setColor(Color.black);
			gBuffer.fillRect(dpx, dpy, pw, phm);
			gBuffer.setColor(Color.white);
			gBuffer.drawLine(axp1,ayp1,axp2,ayp2);
			gBuffer.drawLine(axp2,ayp2,axp3,ayp3);
			gBuffer.drawString("Time (ms)", dpx + pw/2, phG + dpy - day/3);
			gBuffer.drawString("V (mV)", dpx + dax/4, dpy + day/2 + 2);
			if (originc) {
				gBuffer.setColor(Color.red);
				String sigsig = "Movie Signal: Original";
				umovie = unwrapped; // -> original
			}
			if (unwrapc) {
				gBuffer.setColor(Color.yellow);
				sigsig = "Movie Signal: Unwrapped";
				umovie = unwrapped;
			}
			if (tavec) {
				gBuffer.setColor(Color.green);
				sigsig = "Movie Signal: Time averaged";
				umovie = taveraged;
			}
			if (shiftl) {
				gBuffer.setColor(Color.green);
				sigsig = "Movie Signal: Shifting line";
				umovie = shifted;
			}
			if (shiftc) {
				gBuffer.setColor(Color.orange);
				sigsig = "Movie Signal: Shifted";
				umovie = shifted;
			}
			if (erasec) {
				gBuffer.setColor(Color.magenta);
				sigsig = "Movie Signal: Erased";
				umovie = erased;
			}
			if (spaveragec) {
				gBuffer.setColor(Color.red);
				sigsig = "Movie Signal: Space averaged";
				umovie = spaceaveraged;
			}
			
			
			// Movie line			
			gBuffer.drawString(sigsig, dpx + pw/3, dpy + day/2);
			gBuffer.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
			gBuffer.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
			gBuffer.drawString(Integer.toString(min[nii][njj]), dpx + dax/5, dpy + phG - day - 1);
			gBuffer.drawString(Integer.toString(max[nii][njj]), dpx + dax/5, dpy + day + 10);
			for (int ii = 0; ii < time; ii += 1){
				plx1=dpx+dax+ii*(pw-2*dax)/(ntimes-20);
				ply1=dpy+phG-day-(phG-2*day)*(umovie[nii][njj][ii]-min[nii][njj])/(max[nii][njj]-min[nii][njj]);
				plx2=dpx+dax+(ii+1)*(pw-2*dax)/(ntimes-20);
				ply2=dpy+phG-day-(phG-2*day)*(umovie[nii][njj][ii+1]-min[nii][njj])/(max[nii][njj]-min[nii][njj]);
				gBuffer.drawLine(plx1, ply1, plx2, ply2);
			}
			// Movie image
			{
				if (nmap == 0) {
					for (int ii = 0; ii < maplength; ii += 1){
						int iii = ii;
						if(iii < 30) iii = 30;
						gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
						gBuffer.fillRect(dsx+2*ss+10, dsy+2*ss-16-ii, 10, 1);
					}
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							int iii=(int)(190*umovie[i][j][time]/norma);
							if (iii>190) iii=190;
							if(iii <30) iii=30;
							gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);	
						}
					}
				}
				if (nmap == 1) {
					for (int ii = 0; ii < maplength; ii += 1){
						gBuffer.setColor(new Color(irr[ii],igg[ii],ibb[ii]));
						gBuffer.fillRect(dsx+2*ss+10, dsy+2*ss-16-ii, 10, 1);
					}
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							int iii=(int)(190*umovie[i][j][time]/norma);
							gBuffer.setColor(new Color(irr[iii],igg[iii],ibb[iii]));
							gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
						}
					}
				}
				if (nmap == 2) {
					for (int ii = 0; ii < maplength; ii += 1){
						gBuffer.setColor(new Color(irrr[ii],iggg[ii],ibbb[ii]));
						gBuffer.fillRect(dsx+2*ss+10, dsy+2*ss-16-ii, 10, 1);
					}
					for (int i = 0; i < ss; i++) {
						for (int j = 0; j < ss; j++) {
							int iii=(int)(190*umovie[i][j][time]/norma);
							gBuffer.setColor(new Color(irrr[iii], iggg[iii], ibbb[iii]));
							gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
						}
					}
				}
				gBuffer.setColor(Color.black);
				//gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString(" Vmax", dsx+2*ss, dsy+2*ss-maplength-19);
				gBuffer.drawString(" Vmin", dsx+2*ss, dsy+2*ss);
			}
			
			//plots point on the screen from where time signal is displayed
			gBuffer.setColor(Color.white);
			if(nmap ==2) gBuffer.setColor(Color.red);
			gBuffer.fillRect(dsx+nii*2-2,dsy+njj*2-2, 4,4);	
			
			firsttime = false;
			
				//Movie image
			index = 0;
				//Create image
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
			gBuffer.drawString("Time = ", dsx+ss-50, dsy-4);
			gBuffer.drawString(String.valueOf(time*2)+" ms", dsx+ss+2, dsy-4);
			
			time = time + deltamovietime;

			if (time >= ntimes-2) {
				time = 0;			//Reset timing
				startmovie = false;		//Esc movie config.
				movie.setLabel("Movie");	//Reset the movie button
				source = null;
				pix = null;
				System.gc();
				repaint();
			}
			
		}// close movie
		
		// ZOOM 
		if(mousezoom == 1){
			gBuffer.setColor(Color.white);
			// Zoom on the signal plot
			if (nzoomx1>=dpx+2*dax && nzoomx1<=dpx+pw && nzoomy1>=dpy && nzoomy1<=dpy+ph && nzoomx2>=dpx+2*dax && nzoomx2<=dpx+pw && nzoomy2>=dpy && nzoomy2<=dpy+ph && nzoomx2>nzoomx1 && nzoomy2>nzoomy1){
				gBuffer.drawRect(nzoomx1-dpx, nzoomy1, nzoomx2-nzoomx1, nzoomy2-nzoomy1);
			}
			if (nzoomx1>=dpx+2*dax && nzoomx1<=dpx+pw && nzoomy1>=dpy && nzoomy1<=dpy+ph && nzoomx2>=dpx+2*dax && nzoomx2<=dpx+pw && nzoomy2>=dpy && nzoomy2<=dpy+ph && nzoomx2<nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx2-dpx, nzoomy2, nzoomx1-nzoomx2, nzoomy1-nzoomy2);
			}
			// Zoom on the APD - DI distribution plot
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2>nzoomx1 && nzoomy2>nzoomy1){
				gBuffer.drawRect(nzoomx1-dsx, nzoomy1, nzoomx2-nzoomx1, nzoomy2-nzoomy1);
			}
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2<nzoomx1 && nzoomy2>nzoomy1){
				gBuffer.drawRect(nzoomx2-dsx, nzoomy1, nzoomx1-nzoomx2, nzoomy2-nzoomy1);
			}
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2<nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx2-dsx, nzoomy2, nzoomx1-nzoomx2, nzoomy1-nzoomy2);
			}
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2>nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx1-dsx, nzoomy2, nzoomx2-nzoomx1, nzoomy1-nzoomy2);
			}
		}
		g.drawImage(Buffer,dax,0,this);

	}//close paint
	//*************************************************************
	
	//*************************************************************
	public void run(){
		Thread me = Thread.currentThread( );
		me.setPriority(Thread.MIN_PRIORITY);
		while (true) {
			if (time >= ntimes) {
				break;
			}
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				return;
			}
			if (startmovie == true) { repaint();}
		}
	}//close run
	//*************************************************************
	
	//*************************************************************
	public void  actionPerformed( ActionEvent ae ){
	//*************************************************************
		
		// Load B:-> Loading file
		if (ae.getSource() == openButton) {
			note.append("Loading file. \n");
			f.setDirectory("2010_feb16_temp_endo_010.dat");
			f.setVisible(true);
			String s = f.getDirectory();
			s += f.getFile();
			displayFilename.setText(s);
			namefilel = s;
			note.setForeground(Color.red);
			note.append( namefilel + "\n");
			openButton.setBackground(Color.gray);
			start.setLabel("Start");
			start.setBackground(Color.red); 
		}
		else if (ae.getSource() == errorButton) IOError.dispose();
		
		//NEW button for mask
		if(ae.getSource() == maskb ){
			if(imask == -1){
				maskb.setLabel("Mask");
			}
			if(imask == 1){
				maskb.setLabel("Mask off"); 
			}
			imask=imask*imaskf;
		}
	
		// Start B:-> read -> convert -> unwrap -> t average -> normalization
		if (ae.getSource() == start && start.getLabel().equals("Start")) {
			
			beginAnalyze = true;
			start.setLabel("Reset");
			start.setBackground(Color.gray);
			note.setForeground(Color.black);
			// Take parameters
			double nnii = Double.valueOf(tnii.getText()).doubleValue();
			double nnjj = Double.valueOf(tnjj.getText()).doubleValue();
			nii = (int)(nnii);
			njj = (int)(nnjj);
			// Call method to READ video ---------------------------------------
			note.append( "\nReading....\n");
			video = readArray(namefilel);
			note.append( "\nNumber of frames: " + nframes);
			note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
			note.append("\nVideo"+"["+1000+"]"+"["+nii+"]"+"["+njj+"]: "+video[1000][nii][njj]); 
			note.append("\nVideo"+"["+1100+"]"+"["+nii+"]"+"["+njj+"]: "+video[1100][nii][njj]+"\n");
			// Check that this works ok for the CV strip case
			// Do MASK 
			for (int i = 0; i < (sizex + 12); i++){
				for (int j = 0; j < (sizey + 12); j++){
					if (i <= 6 || i >= (sizex + 6) || j <= 6 || j >= (sizey + 6)) {
						mask[i][j] = 0;
					}
					else {
						mask[i][j] = 1;
					}
				}
			}
			// Call method to CONVERT data -------------------------------------
			note.append( "\nConvert and copy data....");
			u = convertArray(video,sizex,sizey,ntimes,nii,njj,original,unwrapped,taveraged);
			note.append("\nu"+"["+1000+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][1000]); 
			note.append("\nu"+"["+1100+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][1100]);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			// Copy min max of the original signal
			for (int i = 0; i < sizex; i++) {
				for (int j = 0; j < sizey; j++) {
					omax[i][j] = max[i][j];
					omin[i][j] = min[i][j];
				}
			}
			note.append("\noMax in "+"["+nii+"]"+"["+njj+"]: "+omax[nii][njj]); 
			note.append("\noMin in "+"["+nii+"]"+"["+njj+"]: "+omin[nii][njj]+"\n");
			// Call method to evaluate absolute Max and min --------------------
			note.append("\nAbsolute Max and min...");
			minAMaxA(sizex, sizey, ntimes, max, min, mask, maMa);
			note.append("\nMaxA: "+maMa[1]); 
			note.append("\nMinA: "+maMa[0]+"\n");
			// Shift to MAKE everything POSITIVE
			for (int i = 0; i < sizex; i++) {
				for (int j = 0; j < sizey; j++) {
					for (int k = 10; k < (ntimes-10); k++) {
						originalimg[i][j][k] = (short)(original[i][j][k] + Math.abs(maMa[0]));
					}
				}
			}
			// Call method to UNWRAP the signal --------------------------------
			note.append( "\nUnwrapping....");
			unwrapSignal(u, mask, sizex, sizey, ntimes, nii, njj, unwrapped, max, min);
			note.append("\nu"+"["+1000+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][1000]); 
			note.append("\nu"+"["+1100+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][1100]);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			// Call methods to normalize in [0:10000] + Max min
			note.append( "\nNormalization....");
			normaSignal(u, mask, sizex, sizey, ntimes, norma, max, min, unwrapped);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			unwmax = max[nii][njj];
			unwmin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			// Call method to performe the TIME AVERAGE ------------------------
			note.append( "\nTime averaging.... ");
			timeAverage(u, mask, sizex, sizey, ntimes, nii, njj, taveraged);
			// Call method to evaluate min & max
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			// Call methods to normalize in [0:10000] + Max min
			note.append( "\nNormalization....");
			normaSignal(u, mask, sizex, sizey, ntimes, norma, max, min, taveraged);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			tavnmax = max[nii][njj];
			tavnmin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+tavnmax); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+tavnmin);
			note.append("\nEnd.  ");
			// Structure and starting plots ------------------------------------
			signal1.setState(true);
			originc = true;
			frame11 = true;
			umovie = original;
			setshift.setBackground(Color.green);
			validate();	
		}
		else {
			if (ae.getSource() == start && start.getLabel().equals("Reset")) {
				beginAnalyze = false;
				start.setLabel("Start");
				repaint();
			}
		}		
		
		// Set B:-> shift
		if (ae.getSource() == setshift) {
		
			setshift.setBackground(Color.gray);	
			// Take shifting period
			double ffreq = Double.valueOf(tfreq.getText()).doubleValue();
			freq = (int)(ffreq);
			ifreq = freq/3;
			
			u = taveraged; 
			// Call method to performe shifting --------------------------------
			note.append( "\nShifting....\n");
			shiftSignal(u, mask, sizex, sizey, ntimes, ifreq, basev1, max, min, ntll, intervsh1, intervsh2, shifted);
			minMax(shifted, mask, sizex, sizey, ntimes, max, min);
			note.append( "\nNormalization....");
			normaSignalGN(shifted, mask, sizex, sizey, ntimes, norma, max, min);
			minMax(shifted, mask, sizex, sizey, ntimes, max, min);
			shifmax = max[nii][njj];
			shifmin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			
			u = shifted;
			// Flag to plot the shifted signal ---------------------------------
			signal1.setState(false);
			originc = false;
			signal3.setState(true);
			tavec = true;
			signal4.setState(true);
			shiftl = true;
			frame11 = true;
			validate();
			//--------------------------------
			shiftfirsttime	= true;
		}
		
		// Set B:-> erase
		if (ae.getSource() == seterase) {
			
			// Taking erasing intervals
			double nerasesx = Double.valueOf(erasesx.getText()).doubleValue();
			double nerasedx = Double.valueOf(erasedx.getText()).doubleValue();
			ertime1 = (int)(nerasesx);
			ertime2 = (int)(nerasedx);
			
			note.append( "\nErasing times....");
			eraseTime(u, mask, sizex, sizey, ntimes, ertime1, ertime2, erased);
			
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			note.append( "\nNormalization....");
			normaSignalGN(erased, mask, sizex, sizey, ntimes, norma, max, min);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			ermax = max[nii][njj];
			ermin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			// Flag to plot the erased signal --------------------------------
			signal1.setState(false);
			originc = false;
			signal2.setState(false);
			unwrapc = false;
			signal3.setState(false);
			tavec = false;
			signal4.setState(false);
			shiftl = false;
			signal5.setState(false);
			shiftc = false;
			signal6.setState(true);
			erasec = true;
			frame11 = true;
			validate();
			//--------------------------------
			u = erased;			
			erasefirsttime	= true;			
		}
	
		//Performe B:-> space average
		if (ae.getSource() == spaaverage) {
			note.append( "\nSpace average....");
			spaceAverage(u, mask, sizex, sizey, ntimes, nii, njj, oldText, note, spaceaveraged);
			note.append(oldText + "End.  ");
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");			
			note.append( "\nNormalization....");
			normaSignalGN(spaceaveraged, mask, sizex, sizey, ntimes, norma, max, min);
			minMax(u, mask, sizex, sizey, ntimes, max, min);			
			spamax = max[nii][njj];
			spamin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+spamax); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+spamin);
			note.append("\nEnd.  ");
			//--------------------------------
			u = spaceaveraged;
			spacefirsttime = true;			
			//Flag to plot the space averaged signal --------------------------
			signal1.setState(false);
			originc = false;
			signal2.setState(false);
			unwrapc = false;
			signal3.setState(false);
			tavec = false;
			signal4.setState(false);
			shiftl = false;
			signal5.setState(false);
			shiftc = false;
			signal6.setState(false);
			erasec = false;
			signal7.setState(true);
			spaveragec = true;
			validate();
		}
		else {
			spacefirsttime = false;
		}
		
		//Perform B:-> APD - DI thresholds evaluation -> Distribution
		if (ae.getSource() == apddi || ae.getSource() == thrset) {
		
			double urtt = Double.valueOf(thr.getText()).doubleValue();
			ur = (int)(urtt);
			
			note.append( "\nAPD - DI thresholds....");
			apdDI(u, mask, sizex, sizey, ntimes, ur, nii, njj, intervsh1, intervsh2, tpb, nb, ntr, apd, di);
			
			note.append("\nntr"+"["+nii+"]"+"["+njj+"]: "+ntr[nii][njj]);
			note.append("\nnb" + "[" + nii + "]" + "[" + njj + "]: " + nb[nii][njj]);
			note.append("\napd" + "[" + nii + "]" + "[" + njj + "]: " + apd[nii][njj][ntr[nii][njj]]);
			note.append("\ndi" + "[" + nii + "]" + "[" + njj + "]: " + di[nii][njj][ntr[nii][njj]]);
			note.append("\nEnd.");
			
			AmMApdDi(sizex,sizey,minDIn,maxDIn,minAPDn,maxAPDn,maMaApdDi,mask);
			note.append("\nAbsolute min di: "+maMaApdDi[0]);
			note.append("\nAbsolute max di: "+maMaApdDi[1]);
			note.append("\nAbsolute min apd: "+maMaApdDi[2]);
			note.append("\nAbsolute max apd: "+maMaApdDi[3]);
			note.append("\nEnd.");
			//--------------------------------
			//Flag to plot the thresholds
			apdDiMeanc = false;
			apdDiDist.setState(true);
			apdDistc = true;
			signal8.setState(true);
			thresholdc = true;
			validate();
		}

		// APD - DI mean value
		if (ae.getSource() == apdDiMean) {
			
			note.append( "\nAPD - DI mean value....");
			
			if (alternancec) {
				// Call method to evaluate mean APD - DI
				note.append( "\nPresence of alternance");
				meanApdDiA(sizex, sizey, ntr, di, apd, mdiAo, mapdAo, mdiAe, mapdAe, mask);
				
				note.append("\nMean odd DI in "+"["+nii+"]"+"["+njj+"]: "+mdiAo[nii][njj]);
				note.append("\nMean odd APD in "+"["+nii+"]"+"["+njj+"]: "+mapdAo[nii][njj]);
				note.append("\nMean even DI in "+"["+nii+"]"+"["+njj+"]: "+mdiAe[nii][njj]);
				note.append("\nMean even APD in "+"["+nii+"]"+"["+njj+"]: "+mapdAe[nii][njj]);
				
				minMaxApdDiA(sizex,sizey,ntimes,ntr,apd,di,minDIAe,maxDIAe,minAPDAe,maxAPDAe,minDIAo,maxDIAo,minAPDAo,maxAPDAo,mask);
				note.append("\nmin DIe in "+"["+nii+"]"+"["+njj+"]: "+minDIAe[nii][njj]+"\t"); 
				note.append("Max DIe in "+"["+nii+"]"+"["+njj+"]: "+maxDIAe[nii][njj]);
				note.append("\nmin APDe in "+"["+nii+"]"+"["+njj+"]: "+minAPDAe[nii][njj]+"\t");
				note.append("Max APDe in "+"["+nii+"]"+"["+njj+"]: "+maxAPDAe[nii][njj]);
				note.append("\nmin DIo in "+"["+nii+"]"+"["+njj+"]: "+minDIAo[nii][njj]+"\t"); 
				note.append("Max DIo in "+"["+nii+"]"+"["+njj+"]: "+maxDIAo[nii][njj]);
				note.append("\nmin APDo in "+"["+nii+"]"+"["+njj+"]: "+minAPDAo[nii][njj]+"\t");
				note.append("Max APDo in "+"["+nii+"]"+"["+njj+"]: "+maxAPDAo[nii][njj]);
				// Flag to plot the mean APD - DI ------------------------------
				apdDistc = false;
				apdDiMeanS.setState(true);
				apdDiMeanc = true;
				validate();
			}
			else {
				note.append( "\nWithout alternance");
				meanApdDi(sizex,sizey,ntr,di,apd,meandi,meanapd,mask);
				
				note.append("\nMean DI in "+"["+nii+"]"+"["+njj+"]: "+meandi[nii][njj]);
				note.append("\nMean APD in "+"["+nii+"]"+"["+njj+"]: "+meanapd[nii][njj]+"\n");
				
				note.append( "\nAPD - DI min - max....");
				minMaxApdDi(sizex,sizey,ntimes,ntr,apd,di,minDIn,maxDIn,minAPDn,maxAPDn,mask); 
				note.append("\nmin DI in "+"["+nii+"]"+"["+njj+"]: "+minDIn[nii][njj]+"\t"); 
				note.append("Max DI in "+"["+nii+"]"+"["+njj+"]: "+maxDIn[nii][njj]);
				note.append("\nmin APD in "+"["+nii+"]"+"["+njj+"]: "+minAPDn[nii][njj]+"\t");
				note.append("Max APD in "+"["+nii+"]"+"["+njj+"]: "+maxAPDn[nii][njj]);
				// Flag to plot the APD - DI mean
				apdDistc = false;
				apdDiMeanS.setState(true);
				apdDiMeanc = true;
				validate();
			}
		}
		else {
			apdDiMeanc = false;
		}
		
		// APD - DI space
		if (ae.getSource() == apdSpace) {
			apdDistc = false;
			apdDiMeanc = false;
			apdSpacec = true;
			validate();
		}
		
		// Set the number of APD/DI to show in space
		if (ae.getSource() == napddiA) {
			napddi++;
			nnapddi.setText(Integer.toString(napddi)); 
		}
		if (ae.getSource() == napddiB) {
			napddi--;
			nnapddi.setText(Integer.toString(napddi)); 
		}

		// Take number of frame to skip
		if (ae.getSource() == skipframe) {
			double deltamovietimeN = Double.valueOf(frametoskip.getText()).doubleValue();
			deltamovietime = (int)(deltamovietimeN);
		}
		if (ae.getSource() == pftsk) {
			deltamovietime += 1;
			frametoskip.setText(Integer.toString(deltamovietime));
		}
		if (ae.getSource() == mftsk) {
			deltamovietime -= 1;			
			frametoskip.setText(Integer.toString(deltamovietime));
		}
		
		// Movie
		if (ae.getSource() == movie && movie.getLabel().equals("Movie")) {
			startmovie = true;
			movie.setLabel("Stop");
			pause.setLabel("Pause");
			
			Thread runner = new Thread(this);
			runner.start();
		}
		else {
			if (ae.getSource() == movie && movie.getLabel().equals("Stop")) {
				time = 0;
				startmovie = false;
				frame11 = true;
				movie.setLabel("Movie");
				pause.setLabel("     ");
				nxframe.setLabel(" ");
				prframe.setLabel(" ");
				nx2frame.setLabel(" ");
				pr2frame.setLabel(" ");
			}
		}			
			
		// Pause movie
		if (ae.getSource() == pause && pause.getLabel().equals("Pause")){ 
			prFrameTime = time;
			deltamovietime=0;
			pause.setLabel("Contin");
			nxframe.setLabel("1+");
			prframe.setLabel("1-");
			nx2frame.setLabel("4+");
			pr2frame.setLabel("4-");
		}
		else{
			if (ae.getSource() == pause && pause.getLabel().equals("Contin")){
					//Take number of frame to skip
				double deltamovietimeN = Double.valueOf(frametoskip.getText()).doubleValue();
				deltamovietime = (int)(deltamovietimeN);
				pause.setLabel("Pause");
				nxframe.setLabel(" ");
				prframe.setLabel(" ");
				nx2frame.setLabel(" ");
				pr2frame.setLabel(" ");
			}
		}     
		
		// Increase frame during pause movie
		if (ae.getSource() == nxframe && pause.getLabel().equals("Contin")){
			time += 1;
		}
		if (ae.getSource() == nx2frame && pause.getLabel().equals("Contin")){
			time += 4;
		}
		if (ae.getSource() == prframe && pause.getLabel().equals("Contin")){
			time += -1;
		}
		if (ae.getSource() == pr2frame && pause.getLabel().equals("Contin")){
			time += -4;
		}
		
		//Take max min plot timing
		if (ae.getSource() == settpplot) {
			double tmintimeplot = Double.valueOf(tminpplot.getText()).doubleValue();
			double tmaxtimeplot = Double.valueOf(tmaxpplot.getText()).doubleValue();
			mintimeplot = (int)(tmintimeplot);
			maxtimeplot = (int)(tmaxtimeplot);
			repaint();
		} 
		else {
			if (ae.getSource() == resettpplot) {
				mintimeplot = 10;
				maxtimeplot = ntimes - 10;
				pmintimeplot = mintimeplot;
				tminpplot.setText(Integer.toString(mintimeplot));
				tmaxpplot.setText(Integer.toString(maxtimeplot));
				repaint();
			}
		}

		//Take point of interest
		if (ae.getSource() == setpoint) {
			double nnii = Double.valueOf(tnii.getText()).doubleValue();
			double nnjj = Double.valueOf(tnjj.getText()).doubleValue();
			nii = (int)(nnii);
			njj = (int)(nnjj);
			repaint();
		}
		
		// Set Restitution scale for ZOOM
		if (ae.getSource() == setRestScale) {
		
			clMaxc = clDuration.getSelectedIndex();
			
			if (clMaxc == 0 && mousezoom == 0) {
				mindi = mindi1;
				maxdi = maxdi1;
				minapd = minapd1;
				maxapd = maxapd1;
			}
			if (clMaxc == 1 && mousezoom == 0) {
				mindi = mindi2;
				maxdi = maxdi2;
				minapd = minapd2;
				maxapd = maxapd2;
			}
			if (clMaxc == 2 && mousezoom == 0) {
				mindi = mindi3;
				maxdi = maxdi3;
				minapd = minapd3;
				maxapd = maxapd3;
			}
			if (clMaxc == 3 && mousezoom == 0) {
				mindi = mindi4;
				maxdi = maxdi4;
				minapd = minapd4;
				maxapd = maxapd4;
			}
			if (clMaxc == 4 && mousezoom == 0) {
				mindi = mindi5;
				maxdi = maxdi5;
				minapd = minapd5;
				maxapd = maxapd5;
			}
			
			pmindi = 0;
			pmaxdi = 0;
			pminapd = 0;
			pmaxapd = 0;
		}
	
		//Exit
		{
			if (ae.getSource() == close) {
				dispose();
				System.exit(0);
			}			
		}	
				
		//Waiting window
		{
		if (beginAnalyze){
			if (firsttime || shiftfirsttime || erasefirsttime || spacefirsttime) {
					//waitwindow.setVisible(true);
			}
			else {
				waitwindow.setVisible(false);
				closewindow = true;
			}
		}
		else {
			waitwindow.setVisible(false);
			closewindow = true;
		}			
	}
				
		repaint();
	
	}//close actionPerformed
	//*************************************************************
	//*****************************************************
	//   Mouse section
	//*****************************************************
		public void mouseClicked(MouseEvent me){
		}
		public void mouseEntered(MouseEvent me){
		}
		public void mouseExited(MouseEvent me){                    
        }
		public void mouseMoved(MouseEvent me){
		}
		public void mousePressed(MouseEvent me){ //NEW updated imask section
			p=me.getPoint();
			pzoom=me.getPoint();
			nzoomx1=(int)((float)(p.x));
			nzoomy1=(int)((float)(p.y)); 
			int iipx=(int)((float)(p.x-dsx-dax)/(2.));
			int iipy=(int)((float)(p.y-dsy)/2.);
			if(iipx > 0 && iipx < sizex && iipy > 0 && iipy < sizey) {
				nii = (int)(iipx);
				njj = (int)(iipy);
				tnii.setText(String.valueOf(nii));
				tnjj.setText(String.valueOf(njj));
				repaint();                         
			}
			if (apdSpacec || diSpacec) {
				iipx=(int)((float)(p.x-dsx-dax-pw+2*ss)/(2.));
				iipy=(int)((float)(p.y-dsy)/2.);
				if(iipx > 0 && iipx < sizex && iipy > 0 && iipy < sizey ) {
					nii = (int)(iipx);
					njj = (int)(iipy);
					tnii.setText(String.valueOf(nii));
					tnjj.setText(String.valueOf(njj));
					repaint();                         
				}
			}
		}
		public void mouseDragged(MouseEvent me){     //NEW mouseDragged event
													 // Zoom on signal plot
			mousezoom=1;
			p=me.getPoint();
			pzoom=me.getPoint();
			nzoomx2=(int)((float)(pzoom.x));
			nzoomy2=(int)((float)(pzoom.y));     
			// Change point of interest on the image tissue structure       
			iipx=(int)((float)(p.x-dsx-dax)/(2.));
			iipy=(int)((float)(p.y-dsy)/2.);
			if(iipx > 0 && iipx < sizex && iipy > 0 && iipy < sizey ) {
				nii = (int)(iipx);
				njj = (int)(iipy);
				tnii.setText(String.valueOf(nii));
				tnjj.setText(String.valueOf(njj));
			}
			// Modify the mask with r=3 pixel circle
			if(imask == 1){
				if(iipx > 0 && iipx < sizex && iipy > 0 && iipy < sizey ) {
					nii = (int)(iipx);
					njj = (int)(iipy);
					for (int i = nii-3; i < nii+3; i++){
						for (int j = njj-3; j < njj+3; j++){
							double rr=Math.sqrt((double)((i-nii)*(i-nii))+(double)((j-njj)*(j-njj)));
							if(rr <= 3.) {
								mask[i+6][j+6]=0; 
							} 
						} 
					}
				}
			}
			
			repaint();
		}
		public void mouseReleased(MouseEvent me){
			// Update time scale for signal ZOOM
			if (nzoomx1>=dpx+2*dax && nzoomx1<=dpx+pw && nzoomy1>=dpy && nzoomy1<=dpy+ph && nzoomx2>=dpx+2*dax && nzoomx2<=dpx+pw && nzoomy2>=dpy && nzoomy2<=dpy+ph) {
				
				pmintimeplot = mintimeplot;
				if (nzoomx2>nzoomx1 && nzoomy2>nzoomy1) {
					mintimeplot=pmintimeplot+(nzoomx1-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
					maxtimeplot=pmintimeplot+(nzoomx2-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
				}
				if (nzoomx2<nzoomx1 && nzoomy2>nzoomy1) {
					mintimeplot=pmintimeplot+(nzoomx2-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
					maxtimeplot=pmintimeplot+(nzoomx1-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
				}
				if (nzoomx2<nzoomx1 && nzoomy2<nzoomy1) {
					mintimeplot=pmintimeplot+(nzoomx2-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
					maxtimeplot=pmintimeplot+(nzoomx1-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
				}
				if (nzoomx2>nzoomx1 && nzoomy2<nzoomy1) {
					mintimeplot=pmintimeplot+(nzoomx1-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
					maxtimeplot=pmintimeplot+(nzoomx2-dpx-2*dax)*(maxtimeplot-pmintimeplot)/(pw-2*dax);
				}	
			}
			// Update axes for APD - DI ZOOM
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10){
				pmindi = mindi;
				pmaxdi = maxdi;
				pminapd = minapd;
				pmaxapd = maxapd;
				if (nzoomx2>nzoomx1 && nzoomy2>nzoomy1) {
					mindi = pmindi+(nzoomx1-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					maxdi = pmindi+(nzoomx2-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					minapd = pminapd+((dsy+2*ss-10)-nzoomy2)*(pmaxapd-pminapd)/(2*ss-20);
					maxapd = pminapd+((dsy+2*ss-10)-nzoomy1)*(pmaxapd-pminapd)/(2*ss-20);
				}
				if (nzoomx2<nzoomx1 && nzoomy2>nzoomy1) {
					mindi = pmindi+(nzoomx2-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					maxdi = pmindi+(nzoomx1-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					minapd = pminapd+((dsy+2*ss-10)-nzoomy2)*(pmaxapd-pminapd)/(2*ss-20);
					maxapd = pminapd+((dsy+2*ss-10)-nzoomy1)*(pmaxapd-pminapd)/(2*ss-20);
				}
				if (nzoomx2<nzoomx1 && nzoomy2<nzoomy1) {
					mindi = pmindi+(nzoomx2-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					maxdi = pmindi+(nzoomx1-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					minapd = pminapd+((dsy+2*ss-10)-nzoomy1)*(pmaxapd-pminapd)/(2*ss-20);
					maxapd = pminapd+((dsy+2*ss-10)-nzoomy2)*(pmaxapd-pminapd)/(2*ss-20);
				}
				if (nzoomx2>nzoomx1 && nzoomy2<nzoomy1) {
					mindi = pmindi+(nzoomx1-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					maxdi = pmindi+(nzoomx2-(2*dsx+pw-2*ss+10))*(pmaxdi-pmindi)/(2*ss-20);
					minapd = pminapd+((dsy+2*ss-10)-nzoomy1)*(pmaxapd-pminapd)/(2*ss-20);
					maxapd = pminapd+((dsy+2*ss-10)-nzoomy2)*(pmaxapd-pminapd)/(2*ss-20);
				}
				//System.out.println("di\t"+di[nii][njj][5]+"\tIN\t"+mindi+" : "+maxdi);
				//System.out.println("apd\t"+apd[nii][njj][5]+"\tIN\t"+minapd+" : "+maxapd);
			}
			
			mousezoom = 0;            //NEW to clear zoom square
			validate();			
			repaint();
		}					
	//*************************************************************
	public void itemStateChanged(ItemEvent ie ){

                   //NNEW for saving files in txt form

                  if(ie.getItem().toString()== optionOriginal){
                                                              }
                  if(ie.getItem().toString()== optionUnwrapped){
                                                              }
                  if(ie.getItem().toString()== optionTaveraged){
                                                              }
                 if(ie.getItem().toString()== optionShiftLine){
                                                              }
                 if(ie.getItem().toString()== optionShifted){



 if(da == null) { 

    float data[] = new float[ntimes];
    float datat[] = new float[ntimes];

         int jjj=-1;
     for(int i=mintimeplot; i < maxtimeplot; i++) {
        jjj=jjj+1;

 //   nf.setMaximumFractionDigits(2);         // Not used, but this is for setting the number of diggits
 //   float  valuenff=(float)(v_of_t[i]*100); // Alessio: Maybe you can use it for the APD's DI's ascii window. 
 //   int    valueni=(int)(valuenff);
 //   float  valuenf=(float)(valueni)/100;
 //       data[jjj]=valuenf;
          datat[jjj]=(float)(i);
          data[jjj]=(float)(shifted[nii][njj][i]);
                                                }

        da = new DataWin(data,datat,jjj); 
        da.addWindowListener(new WindowAdapter() { 
          public void windowClosing(WindowEvent we) { 
           // dplot_vt.setState(false); 
            da = null; 
          } 
        }); 
      } 
//    else { 
//      if(da != null) { 
//        da.dispose(); 
//        da = null; 
//      } 
//    }  





                                                              }
                 if(ie.getItem().toString()== optionSpaceAver){
                                                              }
                 if(ie.getItem().toString()== optionThresholds){
                                                              }
                 if(ie.getItem().toString()== optionAPDDI){
                                                              }

			
				//Choice original
			if (signal1.getState() 	== true) 	{
				originc = true;
				validate();
			}
			else {
				//umovie = unwrapped;
				originc = false;
				validate();
			}
				//Choice unwrapped
			if (signal2.getState() == true) 	{
				unwrapc = true;
				validate();
			}
			else {
				unwrapc = false;
				validate();
			}
				//Choice time averaged
			if (signal3.getState() == true) 	{
				originc = false;
				tavec = true;
				shiftc = false;
				validate();
			}
			else {
				tavec = false;
				validate();
			}
				//Choice shifting line
			if (signal4.getState() == true) 	{
				shiftl = true;
				validate();
			}	
			else {
				shiftl = false;
				validate();
			}
				//Choice shifted
			if (signal5.getState() == true) 	{
				shiftl = false;
				tavec = false;
				shiftc = true;
				validate();
			}
			else {
				shiftc = false;
				validate();
			}
				//Choice erased
			if (signal6.getState() 	== true) 	{
				erasec = true;
				validate();
			}
			else {
				erasec = false;
				validate();
			}
				//Choice space averaged
			if (signal7.getState() 	== true) 	{
				spaveragec = true;
				validate();
			}
			else {
				spaveragec = false;
				validate();
			}
				//Choice thresholds
			if (signal8.getState() 	== true) 	{
				thresholdc = true;
				validate();
			}
			else {
				thresholdc = false;
				validate();
			}
			
				// APD - DI distribution
			if (apdDiDist.getState() == true) 	{
				apdDistc = true;
				validate();
			}
			else 								{
				apdDistc = false;	
				validate();
			}
			
			if (apdDiMeanS.getState() == true) 	{
				apdDiMeanc = true;
				validate();
			}
			else {
				apdDiMeanc = false;
				validate();
			}	
			if (apdSpace.getState() == true) 	{
				apdSpacec = true;
				validate();
			}
			else {
				apdSpacec = false;
				validate();
			}	
			if (diSpace.getState() == true) 	{
				diSpacec = true;
				validate();
			}
			else {
				diSpacec = false;
				validate();
			}	
			
		
			
			/*if (clMaxc != pclMaxc) {
				apdDistc = true;
				validate();
				repaint();
			}
			pclMaxc = clMaxc;
			*/
			// Choice for the color map
			nmap = colormap.getSelectedIndex();		
			// Choice for alternance
			alternancec = (alternance.getSelectedIndex() != 0);
			// Choice for error bars
			errorb = errorBars.getSelectedIndex();
			
			repaint();		
			
		}//Close itemStateChanged
	//*************************************************************
		
	//*************************************************************
	public class Close extends WindowAdapter {
				
		public void windowClosing(WindowEvent we){
			
			if (closewindow = true) waitwindow.dispose();
		}
				
	}//close Close class
	//*************************************************************
	
	//*************************************************************
	void createPalette1(byte r[],byte g[],byte b[]){
		
		byte ro = 0, go = 0, bo = 0;
		
		for (int i = 0; i < 228; i++) {
			b[i] = 0; 
			g[i] = 0; 
			b[i] = 0;
		}
		
			// ------------  map color ---------------
		{
			for( int i=0; i<15; i++){
				n = n + 1;
				r[i] = ro;
				g[i] = (byte)(go+(n*3.3));
				b[i] = (byte)(bo+(n*6.6));
			}
			
			n = -1;
			for( int i = 15; i < 53; i++){
				n = n + 1;
				r[i] = ro;
				g[i] = (byte)(50 + (2.7*n));
				b[i] = (byte)(100);
			}
			
			n = -1;
			for( int i = 53; i < 90; i++){
				n = n + 1;
				r[i] = (byte)(0);
				g[i] = (byte)(150);
				b[i] = (byte)(100 - (2.5*n));
			}
			
			n = -1;
			for( int i = 90; i < 109; i++){
				n = n + 1;
				r[i] = (byte)(0 + (n*6.58));
				g[i] = (byte)(140 - (n*2.23));
				b[i] = (byte)(0);
			}
			
			n = -1;
			for( int i = 109; i < 128; i++){
				n = n + 1;
				r[i] = (byte)(120 + (n*4.41));
				g[i] = (byte)(100 - (n*5.52));
				b[i] = (byte)(0);
			}
			
			n = -1;
			for( int i = 128; i < 147; i++){
				n = n + 1;
				r[i] = (byte)(200);
				g[i] = (byte)(0 + (n*2));
				b[i] = (byte)(0);
			}
			
			n = -1;
			for( int i = 147; i < 196; i++){
				n = n + 1;
				r[i] = (byte)(209 + (n*0.441));
				g[i] = (byte)(36 + (n*2));
				b[i] = (byte)(0);
			}
			
		}//end map colors
		
		//For background color
		r[200] = (byte)(244); 
		g[200] = (byte)(164);
		b[200] = (byte)(96);		
				
	}//close palette 1
	void createPalette2(byte r[],byte g[],byte b[]){
				
		
		byte ro = 0, go = 0, bo = 0;
		
		for (int i = 0; i < 228; i++) {
			b[i] = 0; 
			g[i] = 0; 
			b[i] = 0;
		}
		
		
			// ------------  map color ---------------
		{
			for( int i = 0; i < 98; i++){
				r[i] = (byte)(0);
				g[i] = (byte)(0 + (2.55*n));
				b[i] = (byte)(255 - (2.55*n));
				n = n + 1;
			}
			
			n = 1;
			for( int i = 98; i < 196; i++){
				r[i] = (byte)(0 + (2.55*n));
				g[i] = (byte)(ig[97] - (2.55*nn));
				b[i] = (byte)(0);
				n = n + 1;
				nn = nn + 1;
			}  
			
		}//end map colors
		
			//For background color
		r[200] = (byte)(244); 
		g[200] = (byte)(164);
		b[200] = (byte)(96);		
				
	}//close palette 2			
	void createPalette3(byte r[],byte g[],byte b[]){
		
		for(int i = 0; i < 196; i++){
			r[i] = (byte)(1.275*i);
			g[i] = (byte)(1.275*i);
			b[i] = (byte)(1.275*i);
		}
		
			// for background color
		r[200] = (byte)(244); 
		g[200] = (byte)(164);
		b[200] = (byte)(96);
				
	}//close palette 3
	//*************************************************************

						// *** Calculations *** //
	
	// This method reads the data and give video dimensions
	public short[][][] readArray(String namefilel) { 
		cascade_import data = new cascade_import();
		try{
			//cascade_import data = new cascade_import();
			short [][][] video = data.readVideoFile(namefilel);
			nframes = data.readNumbFrame();
			sizex = data.readXDim();
			sizey = data.readYDim();
			return video;
        }//end try
		catch (IOException e) { 
			System.out.println("IOException while reading file.");
			System.exit(0);
		}//close catch
		return video;	
	}//close readArray
	
	// This method convert the data and copy the original signal
	public short[][][] convertArray(short[][][] video, 
									int sizex, int sizey, int ntimes,
									int nii, int njj,
									short[][][] original, 
									short[][][] unwrapped,
									short[][][] taveraged){
		
		short[][][] u = new short[nf][nf][ntimes];
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				for (int k = 0; k < ntimes; k++) {						
					//Make constant the last times
					if (k < 10) video[k][i][j] = video[10][i][j];
					if (k > (ntimes - 10)) video[k][i][j] = video[ntimes - 10][i][j];
					//convert and copy data
					u[i][j][k] = (short)(-video[k][i][j]);
					original[i][j][k] = u[i][j][k];
					unwrapped[i][j][k] = u[i][j][k];
				}
			}
		}
		return u;
	}//close convertArray
	
	// This method evaluates the maximum and minimum values
	public static void minMax( short[][][] u, short[][] mask,
							   int sizex, int sizey, int ntimes, 
							   short[][] max, short[][] min){
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){			
				if (mask[i+6][j+6] == 1) {
					max[i][j] = -32768;
					min[i][j] = 32767;	
					for (int k = 10; k < (ntimes - 10); k++) {
						if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
						if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
					}
				}
			}
		}
	}//close minMax
	
	// This method evaluates the maximum and minimum values
	public static void minMaxINT( int[][][] uu, 
								  int sizex, int sizey, int ntimes, 
								  int[][] maxINT, int[][] minINT){
							   
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				maxINT[i][j] = -120000;
				minINT[i][j] = 120000;				
				for (int k = 10; k < (ntimes - 10); k++) {
					if (uu[i][j][k] < minINT[i][j]) minINT[i][j] = uu[i][j][k];
					if (uu[i][j][k] > maxINT[i][j]) maxINT[i][j] = uu[i][j][k];
				}
			}
		}
	}//close minMax
	
	// This method evaluates the ABSOLUTE maximum and minimum values
	public static void minAMaxA( int sizex, int sizey, int ntimes,
								 short[][] max, short[][] min, 
								 short[][] mask,
							   	 int[] maMa){
		int maxA = -32768;
		int minA = 32767;
		for (int i = 0; i < sizex-0; i++){
			for (int j = 0; j < sizey-0; j++){
				if (mask[i+6][j+6] == 1) {
					if (min[i][j] < minA) minA = min[i][j];
					if (max[i][j] > maxA) maxA = max[i][j];
				}
			}
		}
		maMa[0] = minA;
		maMa[1] = maxA;
	}//close minMax
	
	// This method evaluates the maximum and minimum values
	public static void minAMaxAINT( int sizex, int sizey, int ntimes,
									int[][] maxINT, int[][] minINT, 
									int[] maMa){
		int maxA = -120000;
		int minA = 120000;
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (minINT[i][j] < minA) minA = minINT[i][j];
				if (maxINT[i][j] > maxA) maxA = maxINT[i][j];
			}
		}
		maMa[0] = minA;
		maMa[1] = maxA;
		System.out.println("minA "+maMa[0]+" maxA "+maMa[1]);
		
	}//close minMax
	
	// This method unwraps the saturated signal
	public static void unwrapSignal( short[][][] u, short[][] mask,
									 int sizex, int sizey, int ntimes,
									 int nii, int njj, 
									 short[][][] unwrapped, 
									 short[][] max, short[][] min ){
									 
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					if ( (int)( Math.abs(max[i][j] - min[i][j]) ) > 30000 ){
						for (int k = 10; k < (ntimes - 10); k++) {
							u[i][j][k] = (short)(u[i][j][k] - 20000);
							unwrapped[i][j][k] = u[i][j][k];
						}
					}
				}
			}
		}
		
	}//close unwrapSignal
	
	// This method unwraps the saturated signal
	public static void unwrapSignalINT( short[][][] u,
										int sizex, int sizey, int ntimes,
									  	int[][][] uu ){
			
		int flag = 0;
		int delta = 0;
		
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				flag = 0;
				for (int k = 10; k < (ntimes - 10); k++) {
				
					delta = u[i][j][k]-u[i][j][k+1];
				
					if (Math.abs(delta) > 60000) flag=flag+delta/Math.abs(delta);
					
					if (flag == 0) uu[i][j][k] = u[i][j][k];
					if (flag == 1) uu[i][j][k] = u[i][j][k]+32767;
					if (flag == -1) uu[i][j][k] = u[i][j][k]-32768;
					
					//if (uu[i][j][k] > maxAInt[i][j]) maxAInt[i][j] = u[i][j][k];
					//if (uu[i][j][k] < minAInt[i][j]) minAInt[i][j] = u[i][j][k];
				}
				//uumovie[i][j][k] = (short)(190*uu[i][j][k]/(Math.abs(maxAInt[i][j] - minAInt[i][j])));
				//if (maxAInt[i][j] > imaxA) imaxA = maxAInt[i][j];
				//if (minAInt[i][j] > iminA) iminA = minAInt[i][j];
			}
		}
		
	}//close unwrapSignal
				
	// This method normalizes the signal in [0:10000]
	public static void normaSignal( short[][][] u, short[][] mask,
									int sizex, int sizey, int ntimes, 
									short norma, 
									short[][] max, short[][] min,
									short[][][] various){
													
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					if ((max[i][j] - min[i][j]) == 0) 
						for (int k = 0; k < (ntimes - 0); k++) {
							u[i][j][k] = 0;
						}
					else {
						for (int k = 0; k < (ntimes - 0); k++){
							u[i][j][k] = (short)(norma*(u[i][j][k] - min[i][j])/(max[i][j] - min[i][j]));
							various[i][j][k] = u[i][j][k];
						}
					}
				}
			}
		}
			
	}//close normaSignal
		
	// This method normalizes the signal in [0:10000] without rewrite the u matrix
	public static void normaSignalGN( short[][][] signal, short[][] mask,
									  int sizex, int sizey, int ntimes, 
									  short norma, 
									  short[][] max, short[][] min ){
			
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					if ((max[i][j] - min[i][j]) == 0) 
						for (int k = 0; k < (ntimes - 0); k++) {
							signal[i][j][k] = 0;
						}
					else {
						for (int k = 0; k < (ntimes - 0); k++){
							signal[i][j][k] = (short)(norma*(signal[i][j][k] - min[i][j])/(max[i][j] - min[i][j]));
						}
					}
				}
			}
		}
	}// close normaSignal
	
	// This method performes the time average
	public static void timeAverage( short[][][] u, short[][] mask,
									int sizex, int sizey, int ntimes,
									int nii, int njj, 
									short[][][] taveraged ){
		
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					int averp = 0;
					int averm = 0;
					int averr = 0;
					for (int nt = 10; nt < (ntimes - 10); nt++){
						if (nt == 10) {
							for	(int nn = 1; nn <= 3; nn++){
								
								averp = (averp + u[i][j][nt + nn]);
								averm = (averm + u[i][j][nt - nn]);
								
								u[i][j][nt] = (short)((u[i][j][nt] + averp + averm)/7);
							}
						}
						else {
							averp = (averp + u[i][j][nt + 3]);
							averm = (averm - u[i][j][nt - 4]);
							
							u[i][j][nt] = (short)((u[i][j][nt - 1] + averp + averm)/7);
						}						
					}
					for (int k = 0; k < ntimes; k++) {
						if (k <= 20) u[i][j][k] = u[i][j][20];
						if (k >= (ntimes - 20)) u[i][j][k] = u[i][j][ntimes - 20];	
						taveraged[i][j][k] = u[i][j][k];
					}
				}
			}
		}
	}//close timeAverage
	
	//This method shifts the signal without rewrite the u matrix
	public static void shiftSignal( short[][][] u, short[][] mask,
									int sizex, int sizey, int ntimes, int ifreq,
									short[][][] basev1,
									short[][] ntll, 
								    short[][] max, short[][] min,
									short[][] intervsh1, short[][] intervsh2,
								    short[][][] shifted ){
									
		short smin[][][]	= new short [sizex][sizey][1000];
		short smint[][][]	= new short [sizex][sizey][1000];
		
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					ntll[i][j] = 0;
					for (int k = 0; k < ntimes; k++){
						if (k < 1000) smin[i][j][k] = (short)(32767);
						basev1[i][j][k] = min[i][j];
					}
				}
			}
		}
		// Find minima and timing
		for (int nt = ifreq; nt < (ntimes - ifreq); nt = (nt + ifreq) ) {
			for (int i = 0; i < sizex; i++) {
				for (int j = 0; j < sizey; j++) {
					if (mask[i+6][j+6] == 1) {
						ntll[i][j]++;
						for (int nn = (nt - ifreq); nn < (nt + ifreq); nn++) {
							if( u[i][j][nn] < smin[i][j][ntll[i][j]] ) {   
								smin[i][j][ntll[i][j]] 	= u[i][j][nn];  //minimum in ifreq
								smint[i][j][ntll[i][j]] = (short)(nn);	//time of minimum
						//Take the last time of minima -> intervsh2 for shifting line
								intervsh2[i][j] = smint[i][j][ntll[i][j]];
							}
						}	
					}					
				}
			}
		}
			// Take the first time of minima -> intervsh1 for shifting line
		for (int i = 0; i < sizex; i++) {
			for (int j = 0; j < sizey; j++) {
				if (mask[i+6][j+6] == 1) {
					boolean vero = true;				
					for (int k = 0; k < ifreq; k++) {
						if (smint[i][j][k] > 0 && vero == true) {
							intervsh1[i][j] = smint[i][j][k];
							vero = false;
						}
					}
				}
			}
		}
			// Interpolation of the minima and shift
		double 	basev, fraq;
		short	idis = 0, idist = 1, idist_old = 1;
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					ntll[i][j] = 1;					
					for (int nt = intervsh1[i][j]; nt < intervsh2[i][j]; nt++){
						if (nt > (int)(smint[i][j][ntll[i][j]])) ntll[i][j]++;
						// Difference every two next minima starting from ntll = 1, ntll = 0
						idis = 	(short)(smin[i][j][ntll[i][j]] - smin[i][j][ntll[i][j]-1]);
						// Delta t for every two minima
						idist_old = idist;
						idist = (short)(smint[i][j][ntll[i][j]] - smint[i][j][ntll[i][j]-1]); 
						// Avoid 0 values
						if (idist == 0) idist = idist_old;
						// LINEAR INTERPOLATION between two minima
						fraq  = (double)(( (double)(nt) - (double)(smint[i][j][ ntll[i][j] - 1 ] ))/(double)(idist));
						basev = (double)((double)(smin[i][j][ntll[i][j] - 1])*(1.0 - fraq) + (double)(smin[i][j][ntll[i][j]])*fraq);
						// Shifting line and subtraction of the drift
						basev1[i][j][nt] 	= (short)(basev);					
						shifted[i][j][nt] 	= (short)(u[i][j][nt] - basev1[i][j][nt]);
						// Avoid negative values
						if (shifted[i][j][nt] < 0) shifted[i][j][nt] = 0;
					}
					
					// Set to 0 the first and last times
					for (int nt = 0; nt < intervsh1[i][j]; nt++){
						shifted[i][j][nt] = 0;
					}
					for (int nt = intervsh2[i][j]; nt < ntimes; nt++){
						shifted[i][j][nt] = 0;
					}
				}
			}	
		}
	}// close shiftSignal
	
	//This method performes the space average without rewrite the u matrix
	//NEW added the mask matrix
	public static void spaceAverage( short[][][] u,  short[][] mask, 
									 int sizex, int sizey, int ntimes, 
									 int nii, int njj,
									 String oldText, TextArea note,
									 short[][][] spaceaveraged ){
		double xlap = 0;
		double xcoef[][] = new double[13][13];
        double xcoeft = 0;
        int kt = 50;
        int ktt = 0;
		//Evaluate the Gaussian coefficients
		note.setText( "\nGaussian coefficients....");
		for (int i = 0; i <= 12; i++){
			for (int j = 0; j <= 12; j++){
				int ii = i - 6;
				int jj = j - 6;
				xcoef[i][j] = 1000*9*Math.exp(-( (ii*ii) + (jj*jj) )/8);
			}
		}
		note.append( "\nAveraging....");
		kt=0;
		for (int k = 0; k < ntimes; k++){
			kt = kt + 1;
			ktt = ktt + 1;
			if(kt == 250){
				note.append( "\ndoing now: " + ktt);
				kt = 0;
			}
			for (int j = 0; j < (sizey - 0); j++){
				for (int i = 0; i < (sizex - 0); i++){		
					xlap = 0;
					xcoeft = 0;
					
					for (int ii = 0; ii <= 12; ii++){
						for (int jj = 0; jj <= 12; jj++){
						
							int iii = (ii - 6)*mask[i+ii][j+jj];
							int jjj = (jj - 6)*mask[i+ii][j+jj];
							
							xcoeft = xcoeft + xcoef[ii][jj]*mask[i+ii][j+jj];
							xlap = xlap +(u[i+iii][j+jjj][k])*xcoef[ii][jj]*mask[i+ii][j+jj];
						
						}
					}
					spaceaveraged[i][j][k] = (short)(xlap/xcoeft);
				}
			}
		}
	}//close spaceAverage
	
	//This method erase the first and last times
	public static void eraseTime( short[][][] u, short[][] mask,
								  int sizex, int sizey, int ntimes, 
								  int ertime1, int ertime2,
								  short[][][] signaler ){
				
		for (int i = 0; i < sizex; i++) {
			for (int j = 0; j < sizey; j++) {
				if (mask[i+6][j+6] == 1) {
					for (int k = 0; k < ntimes; k++) {
						if (k <= ertime1 || k >= (ntimes - ertime2)) {
							signaler[i][j][k] = 0;
						}
						else {
							signaler[i][j][k] = u[i][j][k];
						}
					}
				}
			}
		}
	}//close eraseTime
		
	//This method evaluates APD and DI
	public static void apdDI( short[][][] u, short[][] mask,
							  int sizex, int sizey, int ntimes,
							  int ur, int nii, int njj,
							  short[][] istep, short[][] lstep,
							  short[][][] tpb, int[][] nb, int[][] ntr,
							  short[][][] apd, short[][][] di ){
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				for (int k = 0; k < ntimes; k++){
					tpb[i][j][k] 	= 0;
					di[i][j][k] 	= 0;
					apd[i][j][k] 	= 0;
					nb[i][j] 		= 0;
					ntr[i][j] 		= 0;					
				}
			}
		}
		int dt 		= 1;   //Choose dt!!!!!!
		int nsen	= 0;
		int nsen2 	= 0;
		int tzero = 0;
		for (int i = 0; i < (sizex - 0); i++){
			for (int j = 0; j < (sizey - 0); j++){
				if (mask[i+6][j+6] == 1) {
					nsen = 0;
					tzero = 0;
					for (int nt = (istep[i][j] + 10); nt <= (lstep[i][j] - 10); nt++) {
						if (nsen == 0 &&
							u[i][j][nt - 1] > u[i][j][nt] &&
							u[i][j][nt - 2] > u[i][j][nt + 1] &&
							(u[i][j][nt + 1] - ur)*(u[i][j][nt] - ur) <= 0) {						
							nsen++;
						}
						if (nsen == 1) {
							if ((u[i][j][nt + 1] - ur)*(u[i][j][nt] - ur) < 0 && tzero == 0) {
								nb[i][j]++;
								tpb[i][j][nb[i][j]] = (short)( nt*dt - dt*((u[i][j][nt] - ur)/
																		   (u[i][j][nt + 1] - u[i][j][nt])) );
								nsen2 = 1;
							}
								// If it's the first zero then evaluate the threshold
								// Otherwise update the counter
							if ( (u[i][j][nt + 1] - ur)*(u[i][j][nt] - ur) == 0 ){
								if (tzero == 0) {
									tzero = 1;
									nb[i][j]++;
									tpb[i][j][nb[i][j]] = (short)( nt*dt - dt*((u[i][j][nt] - ur)/
																			   (u[i][j][nt + 1] - u[i][j][nt])) );
									nsen2 = 1;
								}
								else {
									tzero = 0;
								}
							}
						}
						if (nb[i][j] >= 2) {
							if ((nb[i][j] % 2) == 1 && nsen2 == 1){
								ntr[i][j]++;
								di[i][j][ntr[i][j]] 	= (short)(tpb[i][j][nb[i][j] - 1] - tpb[i][j][nb[i][j] - 2]);
								apd[i][j][ntr[i][j]] 	= (short)(tpb[i][j][nb[i][j] - 0] - tpb[i][j][nb[i][j] - 1]); 
								nsen2 = 0;
							}
						}
					}
				}
			}
		}
		
	}//close adpDI
	
	//This method evaluates min - max APD - DI without alternance
	public static void minMaxApdDi( int sizex, int sizey, int ntimes,
									int[][] ntr,
									short[][][] apd, short[][][] di, 
									short[][] minDIn, short[][] maxDIn,
									short[][] minAPDn, short[][] maxAPDn,
									short[][] mask){
			
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					minDIn[i][j] = 30000;
					maxDIn[i][j] = -30000;
					minAPDn[i][j] = 30000;
					maxAPDn[i][j] = -30000;				
					for (int k = 1; k <= ntr[i][j]; k++){
						if (di[i][j][k] < minDIn[i][j]) minDIn[i][j] = di[i][j][k];
						if (di[i][j][k] > maxDIn[i][j]) maxDIn[i][j] = di[i][j][k];
						if (apd[i][j][k] < minAPDn[i][j]) minAPDn[i][j] = apd[i][j][k];
						if (apd[i][j][k] > maxAPDn[i][j]) maxAPDn[i][j] = apd[i][j][k];
					}
				}
			}
		}
	}
	
	// This method evaluates ABSOLUTE min-max APD - DI
	public static void AmMApdDi(int sizex, int sizey, 
								short[][] minDIn, short[][] maxDIn,
								short[][] minAPDn, short[][] maxAPDn,									
								int[] maMaApdDi,
								short[][] mask){
								
		int maxAdi = -32768;
		int minAdi = 32767;
		int maxAapd = -32768;
		int minAapd = 32767;
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					if (minDIn[i][j] < minAdi) minAdi = minDIn[i][j];
					if (maxDIn[i][j] > maxAdi) maxAdi = maxDIn[i][j];
					if (minAPDn[i][j] < minAapd) minAapd = minAPDn[i][j];
					if (maxAPDn[i][j] > maxAapd) maxAapd = maxAPDn[i][j];
				}
			}
		}
		maMaApdDi[0] = minAdi;
		maMaApdDi[1] = maxAdi;
		maMaApdDi[2] = minAapd;
		maMaApdDi[3] = maxAapd;
	}
	
	//This method evaluates min - max APD - DI in precense of alternance
	public static void minMaxApdDiA( int sizex, int sizey, int ntimes,
									 int[][] ntr,
									 short[][][] apd, short[][][] di, 
									 short[][] minDIAe, short[][] maxDIAe,
									 short[][] minAPDAe, short[][] maxAPDAe,
								   	 short[][] minDIAo, short[][] maxDIAo,
								   	 short[][] minAPDAo, short[][] maxAPDAo,
									 short[][] mask ){
			
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					minDIAe[i][j] = 30000;
					maxDIAe[i][j] = -30000;
					minAPDAe[i][j] = 30000;
					maxAPDAe[i][j] = -30000;
					minDIAo[i][j] = 30000;
					maxDIAo[i][j] = -30000;
					minAPDAo[i][j] = 30000;
					maxAPDAo[i][j] = -30000;					
					for (int k = 1; k <= ntr[i][j]; k++){
						if (k % 2 == 1){
							if (di[i][j][k] < minDIAe[i][j]) minDIAe[i][j] = di[i][j][k];
							if (di[i][j][k] > maxDIAe[i][j]) maxDIAe[i][j] = di[i][j][k];
							if (apd[i][j][k] < minAPDAe[i][j]) minAPDAe[i][j] = apd[i][j][k];
							if (apd[i][j][k] > maxAPDAe[i][j]) maxAPDAe[i][j] = apd[i][j][k];
						}
						else {
							if (di[i][j][k] < minDIAo[i][j]) minDIAo[i][j] = di[i][j][k];
							if (di[i][j][k] > maxDIAo[i][j]) maxDIAo[i][j] = di[i][j][k];
							if (apd[i][j][k] < minAPDAo[i][j]) minAPDAo[i][j] = apd[i][j][k];
							if (apd[i][j][k] > maxAPDAo[i][j]) maxAPDAo[i][j] = apd[i][j][k];
						}
					}
				}
			}
		}
	}
	
	//This method evaluates mean APD - mean DI without alternance
	public static void meanApdDi( int sizex, int sizey,
								  int[][] ntr, 
								  short[][][]di, short[][][] apd,
								  int[][] meandi, int[][] meanapd,
								  short[][] mask ){
		int sumdi = 0;
		int sumapd = 0;		
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					sumdi = 0;
					sumapd = 0;	
					if (ntr[i][j] > 2) {			
						for (int k = 1; k < ntr[i][j] - 1; k++){
							sumdi = sumdi + di[i][j][k];
							sumapd = sumapd + apd[i][j][k];				
						}
						meandi[i][j] = sumdi/(ntr[i][j] - 2);
						meanapd[i][j] = sumapd/(ntr[i][j] - 2);
					}
					else {
						meandi[i][j] = 0;
						meanapd[i][j] = 0;
					}
				}
			}
		}
	}//close meanApdDI
		
	//This method evaluates mean APD - mean DI with alternance
	public static void meanApdDiA( int sizex, int sizey, 
								   int[][] ntr, 
								   short[][][]di, short[][][] apd,
								   int[][] mdiAo, int[][] mapdAo, 
								   int[][] mdiAe, int[][] mapdAe,
								   short[][] mask ){
		int sumdio = 0;
		int sumapdo = 0;
		int sumdie = 0;
		int sumapde = 0;
		int numodd = 0;
		int numeven = 0;
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					sumdio=0;
					sumapdo=0;
					sumdie=0;
					sumapde=0;
					numodd=0;
					numeven=0;
					if (ntr[i][j] > 2) {				
						for (int k = 1; k < (ntr[i][j] - 1); k++) {
							if (k % 2 == 1) {
								numodd++;
								sumdio = sumdio + di[i][j][k];
								sumapdo = sumapdo + apd[i][j][k];
							}
							else {
								numeven++;
								sumdie = sumdie + di[i][j][k];
								sumapde = sumapde + apd[i][j][k];
							}
						}
						mdiAo[i][j] 	= sumdio/numodd;
						mapdAo[i][j] 	= sumapdo/numodd;
						mdiAe[i][j] 	= sumdie/numeven;
						mapdAe[i][j] 	= sumapde/numeven;
					}
				}
			}
		}
	}//close meanApdDiA
	
	
	
	
	
	
}//End class Java program

