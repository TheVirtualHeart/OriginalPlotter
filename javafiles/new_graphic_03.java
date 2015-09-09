/*
 *  new_graphic_03
 *  
 *
 *  Created by Alessio Gizzi on 08/04/10.
 *  Copyright 2010 Università Campus Bio-Medico. All rights reserved.
 *
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.lang.*;
import java.text.*;
import java.io.*;



public 	class 		new_graphic_03
		extends		Frame 
		implements 	ActionListener, ItemListener, Runnable {

	
	//Interface declarations
	private Button		start, stop, reset, save, movie;
	private Button		apdDiMean, apdDiRest, apdClRest, cvRest;
	private Button		close;
	private Button		setpoint, setshift, seterase, spaaverage, apddi, thrset;
	
	private Checkbox	norma1, norma2;
	private Checkbox 	signal1, signal2, signal3, signal4;
	private Checkbox	signal5, signal6, signal7, signal8;
	private Checkbox	apdDiDist, apdDiMeanS;
	
	private Label 		file, comment, point, intervall, normat, apddieval;
	
	private TextField 	filename, tnii, tnjj, tfreq, erasesx, erasedx, thr;
	private TextField 	answer;
	
	private TextArea 	note;
	
	private Choice 		cnorm, colormap, clDuration, alternance, errorBars;
	
	private Point		p;
	
	//Commands for analysis
	boolean beginAnalyze	= false;		//start analysis
		
	boolean firsttime		= true;			//read and run standard analysis
	boolean shiftfirsttime	= false;		//run the shifting code 
	boolean erasefirsttime	= false;		//run the erasing code
			
	boolean spacefirsttime	= false;		//run the space time code
			
	boolean threshc			= false;		//run the APD - DI code
			
			
	//Commands for plot
	boolean originc 		= false;	//plot the original signal
	boolean unwrapc			= false;	//plot the unwrapped signal
	boolean tavec			= false;	//plot the time average signal
	boolean shiftl			= false;	//plot the shifting line
	boolean shiftc 			= false;	//plot the shifted signal
	boolean	erasec			= false;	//plot the erased signal
			
	boolean spaveragec		= false;	//plot the space averaged signal
			
	boolean thresholdc		= false;	//plot the threshold points
	boolean apddishow		= false;	//plot the restiturion
			
	boolean startmovie		= false;	//start movie plot
	
	boolean apdDistc		= false;	//APD - DI distribution
	boolean alternancec		= false;	//APD - DI alternance
	boolean apdDiMeanc		= false;	//APD - DI mean
	
	
	
	
	//Loading file declarations
	TextField 	displayFilename = new TextField(45);
	FileDialog	f 				= new FileDialog(this, "Load a File", FileDialog.LOAD);
	String 		namefilel 		= " ";
	String 		oldText 		= " ";
	Dialog		IOError			= new Dialog(this, "Error reading file", true);
	Button		openButton		= new Button("Load file");
	Button		errorButton		= new Button("Try again");
	Color		background		= new Color(192,192,192);
			
			
	//Waiting window
	private TextField 	text1		= new TextField(25);	
	private Button		wait 		= new Button(" Wait for calculations progress. ");
	private Button		goplot		= new Button(" Continue ");
	private Dialog		waitwindow	= new Dialog(this, "Progress", true);
	boolean closewindow 			= false;
	
	//Parameters
	int 	nf = 128, ntimes = 3000;
	int 	freq, ifreq;
	int		nii, njj;
	int		ertime1, ertime2;
	int 	ur = 2500;
	short	norma = 10000;
	
	//To normalize the plot
	int origmin	= -10, origmax = 10, unwmin	= -10,  unwmax = 10;
	int nor1min = -10, nor1max = 10, tavnmin = -10, tavnmax = 10; 
	int shifmin = -10, shifmax = 10, ermin = -10, ermax = 10;
	int spamin = -10,  spamax = 10;
	
	//To normalize the APD - DI distribution
	int mindi1 = 10, maxdi1 = 250, minapd1 = 20, maxapd1 = 200;  //APD - DI restitution un to 250 ms CL
	int mindi2 = 10, maxdi2 = 500, minapd2 = 20, maxapd2 = 300;  //APD - DI restitution un to 500 ms CL
	int mindi3 = 10, maxdi3 = 750, minapd3 = 20, maxapd3 = 400;  //APD - DI restitution un to 750 ms CL
	int mindi4 = 10, maxdi4 = 1000, minapd4 = 20, maxapd4 = 500; //APD - DI restitution un to 1000 ms CL
	int clMaxc = 0;
	
	
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
	short lsx 	= 1150;						//screen width
	short lsy 	= 700;						//screen height
	//Plot position 
	int pw		= 700;						//plot width
	int ph		= 250;						//plot height
	int phm		= 200;						//plot height with movie
	int phG		= 0;						//plot height parameter
	int dpx		= 130;						//Distance from x window boundary
	int dpy		= 90;						//Distance from y window boundary
	int dax		= 60;						//Distance from x plot boundary
	int day 	= 35;						//Distance from y plot boundary
			
	int ss		= nf;						//square movie side
	int dsx		= dpx;						//Distance from x window boundary
	int dsy		= dpy + phm + 30;			//Distance from graph boundary	
			
	int axp1	= dpx + dax;				//x axes point 1
	int ayp1	= dpy + day;				//y axes point 1
	int axp2	= dpx + dax;				//x axes point 2
	int ayp2	= dpy + ph - day;			//y axes point 2
	int axp3	= dpx + pw - dax;			//x axes point 3
	int ayp3	= dpy + ph - day;			//y axes point 3
	
	int pcx1	= 0;						//x coord threshold circles
	int pcy1	= 0;						//y coord threshold circles
	int pcx2	= 0;						//x coord threshold circles
	int pcy2	= 0;						//y coord threshold circles
	
	int pehbx1	= 0;						//x1 coord horizontal error bars
	int pehbx2	= 0;						//x2 coord horizontal error bars
	int pehby1	= 0;						//y1 coord vertical error bars
	int pehby2	= 0;						//y2 coord vertical error bars
	
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
	
	
	int errorb = 3;
	
	
   	
	
	short 	video[][][] 		= new short[ntimes][nf][nf];
	short 	u[][][]				= new short[nf][nf][ntimes];
	short 	original[][][]	 	= new short[nf][nf][ntimes];
	short 	unwrapped[][][] 	= new short[nf][nf][ntimes];
	short 	taveraged[][][] 	= new short[nf][nf][ntimes];
	short 	shifted[][][] 		= new short[nf][nf][ntimes];
	short 	basev1[][][] 		= new short[nf][nf][ntimes];
	short 	spaceaveraged[][][] = new short[nf][nf][ntimes];
	short 	erased[][][] 		= new short[nf][nf][ntimes];
	short 	max[][] 			= new short[nf][nf];
	short	min[][] 			= new short[nf][nf];
	short 	ntll[][] 			= new short[nf][nf];
	short	intervsh1[][]		= new short[nf][nf];
	short	intervsh2[][]		= new short[nf][nf];
	
	short	apd[][][]			= new short[nf][nf][ntimes];
	short	di[][][]			= new short[nf][nf][ntimes];
	short 	tpb[][][] 			= new short[nf][nf][ntimes];
	
	short	minDIn[][]			= new short[nf][nf];
	short	maxDIn[][]			= new short[nf][nf];
	short	minAPDn[][]			= new short[nf][nf];
	short	maxAPDn[][]			= new short[nf][nf];
	
	//int	ddi[][]				= new int[nf][nf];
	//int	dapd[][]			= new int[nf][nf];
	int 	nb[][] 				= new int [nf][nf];	//number of thresholds
	int 	ntr[][] 			= new int [nf][nf]; //number of apd - di
	
	
	
	//Movie declarations
	Thread thread;
	IndexColorModel myColorModel1 = null;
	IndexColorModel myColorModel2 = null;
	IndexColorModel myColorModel3 = null;
	Image image1,image2;
			
	byte pix[] = new byte[ss*ss];
	int pixels[];
	
	int time 	= 0;
	int index	= 0;
	
	MemoryImageSource source, sourcel;
	int plx1, ply1, plx2, ply2;
	
	
	
	
	
	
	
	public static void main(String args[]) {
		
		//Create an instance of new_graphic_03_up 
		new_graphic_03 a = new new_graphic_03();
		
		
		//Definitions
		/*
		int 	nfM = 128, ntimesM = 3000, ifreqM = 120;
		int 	niiM = 50, njjM = 50;
		
		short 	normaM = 10000;
		
        short 	uM[][][] 			= new short [nfM][nfM][ntimesM];
		short 	originalM[][][] 	= new short [nfM][nfM][ntimesM];
		short 	unwrappedM[][][]	= new short [nfM][nfM][ntimesM];
		short 	taveragedM[][][]	= new short [nfM][nfM][ntimesM];
		short 	shiftedM[][][]		= new short [nfM][nfM][ntimesM];
		short 	basev1M[][][]		= new short [nfM][nfM][ntimesM];
		short 	maxM[][] 			= new short [nfM][nfM];
		short	minM[][]   			= new short [nfM][nfM];
		short 	ntllM[][] 			= new short [nfM][nfM];
		
		
		String	namefilel = " ";
		//boolean beginAnalyze	= false;		//start analysis
		
		System.out.print("---------------------------------------------------");
		
		
		a.display();	// *** Call method to display the information
		
		
		System.out.println("\nReading....");
		readArray(uM, nfM, ntimesM, originalM, unwrappedM, namefilel);	// *** Call method to read video
		a.original = originalM;
		
		
		System.out.print("\nMax & min:\n");
		minMax(uM, nfM, ntimesM, maxM, minM);	// *** Call method to evaluate min & max
		a.origmax = maxM[niiM][njjM];
		a.origmin = minM[niiM][njjM];
		
		
		System.out.println("\nUnwrapping....");
		unwrapSignal(uM, nfM, ntimesM, unwrappedM, maxM, minM);	// *** Call method to unwrap the signal
		a.unwrapped = unwrappedM;
		
		
		System.out.print("\nMax & min:\n");
		minMax(uM, nfM, ntimesM, maxM, minM);	// *** Call method to evaluate min & max
		a.unwmax = maxM[niiM][njjM];
		a.unwmin = minM[niiM][njjM];
		
		System.out.print("\nNormalization....\n");
		normaSignal(uM, nfM, ntimesM, normaM, ifreqM, maxM, minM); // *** Call method to normalize [0:10000]
		
		System.out.print("\nMax & min:\n");
		minMax(uM, nfM, ntimesM, maxM, minM);	// *** Call method to evaluate min & max
		//a.unwmax = maxM[niiM][njjM];
		//a.unwmin = minM[niiM][njjM];
		
				
		
		//System.out.print("\nTime averaging....\n");
		//timeAverage(u, nf, ntimes, taveraged);	// *** Call method to performe the time average
		
		
		//System.out.print("\nShifting....\n");
		// *** Call method to performe shifting
		//shiftSignal(u, nf, ntimes, ifreq, shifted, basev1, max, min, ntll);	
		
		
		
		//System.out.println("\nSpace average:");
		//spaceAverage(u, nf, ntimes);	// *** Call method to performe the space average
		//System.out.println("End of space average");
		
		
		
		
		System.out.println("\nEnd of the analysis.");
		System.out.print("---------------------------------------------------");
		
			
		*/	
		
	}//close main
	
	
	//*************************************************************	
	//This method builts the graphical interface		
	public new_graphic_03() {
				
		super("Optical Mapping Analysis");
				
		setSize(lsx,lsy);
		setFont(new Font("Serif", Font.PLAIN, 16));
		setBackground(Color.gray);
		setLayout(new BorderLayout(30,30));
		
		
		//INTERFACE
		{
			//Load - save file - comment
			displayFilename.setFont(new Font("Serif", Font.PLAIN, 14));
			file 		= new Label("Save as: ");			//file to load
			comment	 	= new Label("Progress: ");
			filename	= new TextField("prova.txt", 15);	//file to save
			note		= new TextArea(4,30);
			
			//Buttons
			start 		= new Button("Start");
			stop 		= new Button("Stop ");
			reset 		= new Button("Reset");
			save  		= new Button("Save ");
			movie 		= new Button("Movie");
			apdDiMean	= new Button("Eval. Mean Rest.");
			apdDiRest	= new Button("APD-DI Restitution");
			apdClRest	= new Button("APD-CL");
			cvRest		= new Button("CV rest");
			close 		= new Button("Close");
			
			setpoint	= new Button("Set");
			setshift	= new Button("Performe");
			seterase	= new Button("Set");
			spaaverage	= new Button("Space Average");
			apddi		= new Button("Threshold");
			thrset		= new Button("Set");
			
			
			start.addActionListener 		( this );	
			stop.addActionListener 			( this );
			reset.addActionListener 		( this );
			save.addActionListener 			( this );
			movie.addActionListener 		( this );
			apdDiMean.addActionListener 	( this );
			apdDiRest.addActionListener 	( this );
			apdClRest.addActionListener 	( this );
			cvRest.addActionListener 		( this );
			close.addActionListener 		( this );
			
			setpoint.addActionListener 		( this );
			setshift.addActionListener 		( this );
			seterase.addActionListener 		( this );
			spaaverage.addActionListener 	( this );
			apddi.addActionListener 		( this );
			thrset.addActionListener 		( this );
			
			
			//if (beginAnalyze) add(new Label("Wait for processing:"));
			
			point 		= new Label("Test point (x;y):\t", Label.LEFT);
			tnii 		= new TextField("60", 3);
			tnjj 		= new TextField("60", 3);
			
			tfreq 		= new TextField("180", 3);
			
			normat 		= new Label("Normalization:\t", Label.LEFT);
			cnorm		= new Choice();
			cnorm.addItem("[0:10000]");
			cnorm.addItem("[0:1000]");
			
			intervall	= new Label("Erase intervalls:\t", Label.LEFT);
			erasesx		= new TextField("100", 3);
			erasedx 	= new TextField("100", 3);
			
			thr		 	= new TextField("2500", 3);
			
			//Plot conditions
			signal1 	= new Checkbox("Original", 			false);		
			signal2 	= new Checkbox("Unwrapped", 		false);
			signal3 	= new Checkbox("Taveraged", 		false);
			signal4 	= new Checkbox("Shift Line", 		false);
			signal5 	= new Checkbox("Shifted", 			false);
			signal6 	= new Checkbox("Erased", 			false);
			signal7 	= new Checkbox("Space aver",	 	false);
			signal8 	= new Checkbox("Thresholds", 		false);
			
			apdDiDist	= new Checkbox("APD-DI Dist.", false);
			apdDiMeanS	= new Checkbox("APD-DI Mean.", false);

			
			signal1.addItemListener( this );
			signal2.addItemListener( this );
			signal3.addItemListener( this );
			signal4.addItemListener( this );	
			signal5.addItemListener( this );
			signal6.addItemListener( this );
			signal7.addItemListener( this );
			signal8.addItemListener( this );
			
			apdDiDist.addItemListener( this );
			apdDiMeanS.addItemListener( this );
			
			openButton.addActionListener( this );
			
			
			
			//Color Choice
			colormap =  new Choice();
			colormap.addItem("Full scale");
			colormap.addItem("Rev. scale");
			colormap.addItem("Gray scale");
			colormap.addItemListener( this );
					
			//CL Choice
			clDuration	= new Choice();
			clDuration.addItem("250");
			clDuration.addItem("500");
			clDuration.addItem("750");
			clDuration.addItem("1000");
			
			//Alternance Choice
			alternance = new Choice();
			alternance.addItem("No Altern.");
			alternance.addItem("Alternance");
			alternance.addItemListener( this );
			
			//Error Bars Choice
			errorBars =  new Choice();
			errorBars.addItem("No Bars");
			errorBars.addItem("Horizontal");
			errorBars.addItem("Vertical");
			errorBars.addItem("Both");
			errorBars.addItemListener( this );
			
			
			
			//Color maps
			{
				byte r[]= new byte[228];
				byte g[]= new byte[228];
				byte b[]= new byte[228];
				
					//Color map 1: full
				{
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
					
				}
				
					//Color map 2: reversed
				{
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
				}
				
					//Color map 3: gray scale
				{
					createPalette3(r, g, b);
					myColorModel3 = new IndexColorModel(1, 228, r, g, b);
					
					for(int i = 0; i < maplength; i++){
						irrr[i] = (int)(1.275*i);
						iggg[i] = (int)(1.275*i);
						ibbb[i] = (int)(1.275*i);
					}
				}
				
				
					// --- Image for buffering and avoid flickering ---
				image1 = createImage(200,200);
				image2 = createImage(350,300);			
			}//end color map
					
			
			
			
			//Elements Position***
			{
				//North
				Panel rowN = new Panel();
				rowN.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowN.add	( openButton );
				rowN.add	( displayFilename );
				rowN.add	( file );
				rowN.add	( filename );
				rowN.add	( save );
				add 		( rowN, BorderLayout.NORTH );

				//South
				Panel rowS = new Panel();
				//rowS.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS.setLayout(new GridLayout(1,10));
				
				Panel rowS1 = new Panel();
				rowS1.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS1.add	( comment );
				rowS1.add	( note );
				rowS1.add 	( close );
				rowS.add	( rowS1 );
				
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
				
				Panel rowS23 = new Panel();
				rowS23.setLayout(new GridLayout(2,1));
				rowS23.add	( rowS2 );
				rowS23.add	( rowS3 );
				rowS.add	( rowS23 );
				
				add 		( rowS, BorderLayout.SOUTH );

				//West
				Panel rowW = new Panel();
				rowW.setLayout(new GridLayout(10,1));
				rowW.add	( start );
				rowW.add	( stop );
				rowW.add	( reset );
				rowW.add	( movie );
				add 		( rowW, BorderLayout.WEST );

				//East
				Panel rowE = new Panel();	//General west layout
				rowE.setLayout(new GridLayout(15,1));
				
				Panel rowE3 = new Panel();	//Normalization
				rowE3.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE3.add	( normat );
				rowE3.add	( cnorm );
				rowE.add	( rowE3 );
				
				Panel rowE82 = new Panel();		//Color maps and Max CL
				rowE82.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE82.add	( new Label("Movie Color:\t"));
				rowE82.add	( colormap );
				rowE.add	( rowE82 );
				
				Panel rowE85 = new Panel();		//Max CL
				rowE85.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE85.add	( new Label("Max scaling CL:\t"));
				rowE85.add	( clDuration );
				rowE.add	( rowE85 );
				
				Panel rowE4 = new Panel();	//Test point
				rowE4.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE4.add 	( new Label("Error Bars:\t\t") );
				rowE4.add	( errorBars );
				rowE.add	( rowE4 );
				
				Panel rowE40 = new Panel();	//Erasing intervall
				rowE40.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE40.add	( intervall );
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
				
				/*
				Panel rowE401 = new Panel();	//Threshold
				rowE401.setLayout(new FlowLayout(FlowLayout.LEFT));
				
				rowE.add	( rowE401 );
				*/
				Panel rowE99 = new Panel();	//Performe space average 
				rowE99.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE99.add	( new Label("Performe average:\t") );
				rowE99.add	( spaaverage );
				rowE.add	( rowE99 );
				
				Panel rowE991 = new Panel();	//Evaluate thresholds and APD - DI
				rowE991.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE991.add	( new Label("Eval. Thresholds:\t\t") );
				rowE991.add	( apddi );
				rowE.add	( rowE991 );
				
				Panel rowE83 = new Panel();		//Color maps and Max CL
				rowE83.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE83.add	( alternance );
				rowE83.add	( apdDiMean );
				rowE.add	( rowE83 );
				
				Panel rowE5 = new Panel();	//Original and unwrapped
				rowE5.setLayout(new GridLayout(1,2));
				rowE5.add	( signal1 );
				rowE5.add	( signal2 );
				rowE.add	( rowE5 );
				
				Panel rowE6 = new Panel();	//Time aver and shifting line
				rowE6.setLayout(new GridLayout(1,2));
				rowE6.add	( signal3 );
				rowE6.add	( signal4 );
				rowE.add	( rowE6 );
				
				Panel rowE7 = new Panel();	//Shifted and erased
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
				
				
				add		 	( rowE, BorderLayout.EAST );

			}
			
			
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
	//public void update(Graphics g){
	//	repaint();
	//}
	//*************************************************************
	
	
	//*************************************************************
	//This method plots the signal
	public void paint(Graphics g){
		
		//Plot parameters
		int pp1 = 0;
		int pp2 = 0;
		int kk1 = 0;
		int kk2 = 0;
		
		int mindi = 0;
		int maxdi = 0;
		int minapd = 0;
		int maxapd = 0;
		
		
		
		//Graph plot
		if (startmovie == false) {
		
			//Graph dimensions without movie and restitutions
			if (apdDistc == false || apdDiMeanc == false) {
				
				phG = ph;
				
				ayp2 = dpy + phG - day;		
				ayp3 = dpy + phG - day;		
				
				g.setColor(Color.black);
				g.fillRect(dpx, dpy, pw, ph);
				g.setColor(Color.white);
				g.drawLine(axp1,ayp1,axp2,ayp2);
				g.drawLine(axp2,ayp2,axp3,ayp3);
				
				g.drawString("Time (ms)", dpx + pw/2, phG + dpy - day/3);
				g.drawString("V (mV)", dpx + dax/4, dpy + day/2 + 2);
				
			}
						
			
				//Original -> signal1
			if (originc) {
				
					//oldText = note.getText();
				
				g.setColor(Color.red);
				g.drawString("OrSi", pw/3, dpy + day/2);
				g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(origmin), dpx + dax/5, dpy + phG - day - 1);
				g.drawString(Integer.toString(origmax), dpx + dax/5, dpy + day + 10);
				
				for (int t = 10; t < (ntimes - 10); t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 	= dpy + phG - 1*day - (phG - 2*day)*(original[nii][njj][t] 	  - origmin)/(origmax - origmin);
					kk2 	= dpy + phG - 1*day - (phG - 2*day)*(original[nii][njj][t + 1] - origmin)/(origmax - origmin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}
								
			}//end original	
			
			
				//Unwrapped -> signal2
			if (unwrapc) {
				
				g.setColor(Color.yellow);
				g.drawString("UnwS", pw/3 + dax + 2, dpy + day/2);
				g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(unwmin), dpx + dax/5, dpy + phG - day - 1);
				g.drawString(Integer.toString(unwmax), dpx + dax/5, dpy + day + 10);
				
				for (int t = 10; t < (ntimes - 10); t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 = dpy + phG - 1*day - (phG - 2*day)*(unwrapped[nii][njj][t]  	- unwmin)/(unwmax - unwmin);
					kk2 = dpy + phG - 1*day - (phG - 2*day)*(unwrapped[nii][njj][t + 1] - unwmin)/(unwmax - unwmin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}	
				
			}//end unwrapped 
			
			
				//Time averaged -> signal3
			if (tavec) {
				
				g.setColor(Color.green);
				g.drawString("TASi", pw/3 + 2*dax + 4, dpy + day/2);
				g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(tavnmin), dpx + dax/5, dpy + phG - day - 1);
				g.drawString(Integer.toString(tavnmax), dpx + dax/5, dpy + day + 10);
				
				for (int t = 10; t < (ntimes - 10); t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 = dpy + phG - 1*day - (phG - 2*day)*(taveraged[nii][njj][t]  	- tavnmin)/(tavnmax - tavnmin);
					kk2 = dpy + phG - 1*day - (phG - 2*day)*(taveraged[nii][njj][t + 1] - tavnmin)/(tavnmax - tavnmin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}	
				
			}//end time averaged 
			
			
				//Shifted line -> signal4 
			if (shiftl) {
				
				g.setColor(Color.yellow);
				g.drawString("ShLn", pw/3 + 3*dax + 6, dpy + day/2);
								
				for (int t = 10; t < ntimes - 10; t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 = dpy + phG - 1*day - (phG - 2*day)*(basev1[nii][njj][t]  	 - tavnmin)/(tavnmax - tavnmin);
					kk2 = dpy + phG - 1*day - (phG - 2*day)*(basev1[nii][njj][t + 1] - tavnmin)/(tavnmax - tavnmin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}
				
			}//end shifted line
			
			
				//Shifted -> signal5
			if (shiftc) {
				
				g.setColor(Color.blue);
				g.drawString("ShSi", pw/3 + 4*dax + 8, dpy + day/2);
				g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(shifmin), dpx + dax/5, dpy + phG - day - 1);
				g.drawString(Integer.toString(shifmax), dpx + dax/5, dpy + day + 10);
				
				for (int t = 10; t < (ntimes - 10); t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 = dpy + phG - 1*day - (phG - 2*day)*(shifted[nii][njj][t]  	  - shifmin)/(shifmax - shifmin);
					kk2 = dpy + phG - 1*day - (phG - 2*day)*(shifted[nii][njj][t + 1] - shifmin)/(shifmax - shifmin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}
								
			}//end shift signal
			
			
				//Erased -> signal6
			if (erasec) {
				
					//Plot the erased signal
				g.setColor(Color.magenta);
				g.drawString("SeSi", pw/3 + 5*dax + 4, dpy + day/2);
				g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(ermin), dpx + dax/5, dpy + phG - day - 1);
				g.drawString(Integer.toString(ermax), dpx + dax/5, dpy + day + 10);
				
				for (int t = 10; t < ntimes - 10; t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 = dpy + phG - 1*day - (phG - 2*day)*(erased[nii][njj][t]  	  - ermin)/(ermax - ermin);
					kk2 = dpy + phG - 1*day - (phG - 2*day)*(erased[nii][njj][t + 1] - ermin)/(ermax - ermin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}
				
			}//close erase
			
			
				//Space averaged -> signal7
			if (spaveragec) {
				
				g.setColor(Color.red);
				g.drawString("SpAvSi", pw/3 + 6*dax + 4, dpy + day/2);
				g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(spamin), dpx + dax/5, dpy + phG - day - 1);
				g.drawString(Integer.toString(spamax), dpx + dax/5, dpy + day + 10);
				
				for (int t = 10; t < ntimes - 10; t++) {
					
					pp1 = dpx + dax + t*(pw - 2*dax)/(ntimes - 20);
					pp2 = dpx + dax + (t + 1)*(pw - 2*dax)/(ntimes - 20);
					
					kk1 = dpy + phG - 1*day - (phG - 2*day)*(spaceaveraged[nii][njj][t]  	  - spamin)/(spamax - spamin);
					kk2 = dpy + phG - 1*day - (phG - 2*day)*(spaceaveraged[nii][njj][t + 1] - spamin)/(spamax - spamin);
					
					g.drawLine(pp1, kk1, pp2, kk2);
				}
								
			}//close space average
			
			
				//APD - DI thresholds-> signal8
			if (thresholdc) {
				
				g.setColor(Color.yellow);
				g.drawString("APD - DI thresholds\n", pw/4 + dax, dpy + day/2);
				
				
				
				for (int t = 1; t < nb[nii][njj]; t++) {
					
						//System.out.println("tpb" + "[" + nii + "]" + "[" + njj + "]: " + tpb[nii][njj][t]);
					
					pcx1 	= dpx + dax + tpb[nii][njj][t]*(pw - 2*dax)/(ntimes - 20);					
					pcy1 	= dpy + phG - 1*day - (phG - 2*day)*(ur - min[nii][njj])/(max[nii][njj] - min[nii][njj]);
					
					g.fillOval(pcx1 - 2, pcy1 - 2, 2*2, 2*2);
				}
				
				g.drawString(Integer.toString(ur), dpx + dax/5, pcy1);
								
			}//close APD - DI thresholds
			
			
			// ----------------- Restitution -------------------
			
				//APD - DI distribution 
			if (apdDistc) {
				
				phG = ph;
				
				ayp2 = dpy + phG - day;		
				ayp3 = dpy + phG - day;		
				
				g.setColor(Color.black);
				g.fillRect(dpx, dpy, pw, ph);
				g.setColor(Color.white);
				g.drawLine(axp1,ayp1,axp2,ayp2);
				g.drawLine(axp2,ayp2,axp3,ayp3);
				
				g.drawString("DI (ms)", dpx + pw/2, phG + dpy - day/3);
				g.drawString("APD (ms)", dpx + dax/4, dpy + day/2 + 2);
				
				g.setColor(Color.yellow);
				g.drawString("APD - DI distribution points", dpx + dax + pw/4, dpy + 20);
				
				//Normalization choice
				if (clMaxc == 0) {
					mindi = mindi1;
					maxdi = maxdi1;
					minapd = minapd1;
					maxapd = maxapd1;
				}
				else if (clMaxc == 1) {
					mindi = mindi2;
					maxdi = maxdi2;
					minapd = minapd2;
					maxapd = maxapd2;
				}
				else if (clMaxc == 2) {
					mindi = mindi3;
					maxdi = maxdi3;
					minapd = minapd3;
					maxapd = maxapd3;
				}
				else {
					mindi = mindi4;
					maxdi = maxdi4;
					minapd = minapd4;
					maxapd = maxapd4;
				}
				
				g.drawString(Integer.toString(mindi), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(maxdi), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(minapd), dpx + dax/3, dpy + phG - day - 1);
				g.drawString(Integer.toString(maxapd), dpx + dax/3, dpy + day + 10);
				
				for (int n = 1; n < ntr[nii][njj]; n++) {
					
					pcx1 = dpx + dax + di[nii][njj][n]*(pw - 2*dax)/(maxdi - mindi);
					pcy1 = dpy + phG - day - (phG - 2*day)*(apd[nii][njj][n] - minapd)/(maxapd - minapd);
					
					g.fillOval(pcx1 - 2, pcy1 - 2, 2, 2);
				}
				
				
				
			}//close APD - DI distribution
			
			
				//APD - DI mean value
			if (apdDiMeanc) {
				
				phG = ph;
				
				ayp2 = dpy + ph - day;		//y axes point 2
				ayp3 = dpy + ph - day;		//y axes point 3
				
				g.setColor(Color.black);
				g.fillRect(dpx, dpy, pw, ph);
				g.setColor(Color.white);
				g.drawLine(axp1,ayp1,axp2,ayp2);
				g.drawLine(axp2,ayp2,axp3,ayp3);
				
				g.drawString("DI (ms)", dpx + pw/2, phG + dpy - day/3);
				g.drawString("APD (ms)", dpx + dax/4, dpy + day/2 + 2);
				
				g.setColor(Color.green);
				g.drawString("APD - DI restitution curve", dpx + dax + pw/4, dpy + 20);
				
				//Normalization choice
				if (clMaxc == 0) {
					mindi = mindi1;
					maxdi = maxdi1;
					minapd = minapd1;
					maxapd = maxapd1;
				}
				else if (clMaxc == 1) {
					mindi = mindi2;
					maxdi = maxdi2;
					minapd = minapd2;
					maxapd = maxapd2;
				}
				else if (clMaxc == 2) {
					mindi = mindi3;
					maxdi = maxdi3;
					minapd = minapd3;
					maxapd = maxapd3;
				}
				else {
					mindi = mindi4;
					maxdi = maxdi4;
					minapd = minapd4;
					maxapd = maxapd4;
				}
				
				g.drawString(Integer.toString(mindi), dpx + dax, dpy + phG - day/2);
				g.drawString(Integer.toString(maxdi), dpx + pw - dax - dax/2, dpy + phG - day/2);
				g.drawString(Integer.toString(minapd), dpx + dax/3, dpy + phG - day - 1);
				g.drawString(Integer.toString(maxapd), dpx + dax/3, dpy + day + 10);
				
				if (alternancec) {
					
					g.setColor(Color.yellow);
					pcx1 = dpx + dax + mdiAo[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
					pcy1 = dpy + phG - day - (phG - 2*day)*(mapdAo[nii][njj] - minapd)/(maxapd - minapd);
					
					g.fillOval(pcx1 - 4, pcy1 - 4, 4, 4);
					
					g.setColor(Color.blue);
					pcx2 = dpx + dax + mdiAe[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
					pcy2 = dpy + phG - day - (phG - 2*day)*(mapdAe[nii][njj] - minapd)/(maxapd - minapd);
					
					g.fillOval(pcx2 - 4, pcy2 - 4, 4, 4);
				}
				else {
					
					int rr = 2;
					
					g.setColor(Color.green);
					pcx1 = dpx + dax + meandi[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
					pcy1 = dpy + phG - day - (phG - 2*day)*(meanapd[nii][njj] - minapd)/(maxapd - minapd);
					
					g.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
					
					//Error bars
					g.setColor(Color.red);
					if (errorb == 0){
					
						//No error bars
					
					}
					if (errorb == 1) {
						
						//Horizontal only
						pehbx1 = dpx + dax + minDIn[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
						pehbx2 = dpx + dax + maxDIn[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
						g.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						
					}
					else if (errorb == 2){
						
						//Vertical only
						pehby1 = dpy + phG - day - (phG - 2*day)*(minAPDn[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dpy + phG - day - (phG - 2*day)*(maxAPDn[nii][njj] - minapd)/(maxapd - minapd);
						g.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					}
					else if (errorb == 3){
					
						//horizontal & vertical
						pehbx1 = dpx + dax + minDIn[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
						pehbx2 = dpx + dax + maxDIn[nii][njj]*(pw - 2*dax)/(maxdi - mindi);
						pehby1 = dpy + phG - day - (phG - 2*day)*(minAPDn[nii][njj] - minapd)/(maxapd - minapd);
						pehby2 = dpy + phG - day - (phG - 2*day)*(maxAPDn[nii][njj] - minapd)/(maxapd - minapd);
						
						g.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						g.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
					
					}

				}
				
				
				
			}//close APD - DI mean value
			
			// ----------------- ----------- -------------------
				
			
		}//close if normal plots
		else {
			
			//Change graph dimensions
			phG = phm;
			
			ayp2	= dpy + phG - day;	
			ayp3	= dpy + phG - day;	
			
			//Resized graph plot
			g.setColor(Color.black);
			g.fillRect(dpx, dpy, pw, phm);
			g.setColor(Color.white);
			g.drawLine(axp1,ayp1,axp2,ayp2);
			g.drawLine(axp2,ayp2,axp3,ayp3);
			
			g.drawString("Time (ms)", dpx + pw/2, phG + dpy - day/3);
			g.drawString("V (mV)", dpx + dax/4, dpy + day/2 + 2);
			
			g.setColor(Color.red);
			g.drawString("Movie signal", dpx + pw/2, dpy + day/2);
			g.drawString(Integer.toString(10), dpx + dax, dpy + phG - day/2);
			g.drawString(Integer.toString(ntimes-10), dpx + pw - dax - dax/2, dpy + phG - day/2);
			g.drawString(Integer.toString(min[nii][njj]), dpx + dax/5, dpy + phG - day - 1);
			g.drawString(Integer.toString(max[nii][njj]), dpx + dax/5, dpy + day + 10);
			
			//Color table
			{
				if (nmap == 0) {
					for (int ii = 0; ii < maplength; ii += 2){
						int iii = ii;
						if(iii < 30) iii = 30;
						g.setColor(new Color(ir[iii], ig[iii], ib[iii]));
						g.fillRect(dsx + ss + 10, dsy + ss - ii/2 + 1, 10, 1);
					}
				}
				
				if (nmap == 1) {
					for (int ii = 0; ii < maplength; ii += 2){
						g.setColor(new Color(irr[ii], igg[ii], ibb[ii]));
						g.fillRect(dsx + ss + 10, dsy + ss - ii/2 + 1, 10, 1);
					}
				}
				
				if (nmap == 2) {
					for (int ii = 0; ii < maplength; ii += 2){
						g.setColor(new Color(irrr[ii], iggg[ii], ibbb[ii]));
						g.fillRect(dsx + ss + 10, dsy + ss - ii/2 + 1, 10, 1);
					}
				}
				
				g.setColor(Color.black);
				g.drawString(" V max", dsx + ss + 22, dsy + ss - maplength/2 + 10);
				g.drawString(" V min", dsx + ss + 22, dsy + ss - 2);
			}
			
			firsttime = false;
			
			//Square movie dimensions
			g.setColor(Color.black);
			g.fillRect(dsx, dsy, ss + 4, ss + 4);
			
			//Test point graph
			/*
			{
				g.setColor(Color.white);
				g.fillRect(nii + dsx + norig, njj + dsy + norig, 3, 3);
			}
			*/
		
			//Movie image
			int pix[] 	= new int[ss * ss];
			index = 0;
			for (int i = 0; i < ss; i++) {
				for (int j = 0; j < ss; j++) {
					
					int kk = (int)(255*u[i][j][time]/norma);
					pix[index++] = (byte)(kk);
				}
			}
			
			if (nmap == 0)
				source = new MemoryImageSource(ss, ss, pix, 0, ss);
			if (nmap == 1)
				source = new MemoryImageSource(ss, ss, myColorModel2, pix, 0, ss);
			if (nmap == 2)
				source = new MemoryImageSource(ss, ss, myColorModel3, pix, 0, ss);
			
			//Create image
			source.setAnimated(true);
			image1 = createImage(source);
			g.drawImage(image1, dsx + 2, dsy + 2, ss, ss, this);
			
			//Movie line
			g.setColor(Color.red);
			plx1 = dpx + dax + time*(pw - 2*dax)/(ntimes - 20);
			ply1 = dpy + phG - day - (phG - 2*day)*(u[nii][njj][time] - min[nii][njj])/(max[nii][njj] - min[nii][njj]);			
			
			plx2 = dpx + dax + (time + 1)*(pw - 2*dax)/(ntimes - 20);
			ply2 = dpy + phG - day - (phG - 2*day)*(u[nii][njj][time + 1] - min[nii][njj])/(max[nii][njj] - min[nii][njj]);			
			
			g.drawLine(plx1, ply1, plx2, ply2);
			
			
			
			
			time = time + 10;
			
			
			if (time >= ntimes) {
				
				time = 0;					//Reset timing
				startmovie = false;			//Esc movie config.
				movie.setLabel("Movie");	//Reset the movie button
				
				image1 = null;
				source = null;
				pix = null;
				System.gc();
				
				repaint();
				
			
			}
			
		
			
		}//close movie


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
				Thread.sleep(2);
				
			}
			catch (InterruptedException e) {
				return;
			}
			
			repaint(dsx + 2, dsy + 2, ss, ss);
		}
	}//close run
	//*************************************************************
	
	
	
	
	//*************************************************************
	//public void update (Graphics g){ paint(g); }
	//*************************************************************
	
	//*************************************************************
	public void  actionPerformed( ActionEvent ae ){
		
		
		
		//Load B:-> Loading file
		if (ae.getSource() == openButton) {
			
			note.setText("Loading file. \n");
			note.append("Loading file1. \n");
			note.append("Loading file2. \n");
			note.append("Loading file3. \n");
			note.append("Loading file4. \n");
			note.append("Loading file5. \n");
			note.append("Loading file6. \n");
			note.append("Loading file7. \n");
			note.append("Loading file8. \n");
			note.append("Loading file9. \n");
			note.append("Loading file10. \n");
			note.append("Loading file11. \n");
			note.append("Loading fileee. \n");


		//	oldText = note.getText();
			
			f.setDirectory("2010_feb16_temp_endo_010.dat");
			f.setVisible(true);
			String s = f.getDirectory();
			s += f.getFile();
			displayFilename.setText(s);
			namefilel = s;
		
		}
		else if (ae.getSource() == errorButton) IOError.dispose();
	
				
		//Start B:-> read -> convert -> unwrap -> t average -> normalization
		if (ae.getSource() == start && start.getLabel().equals("Start")) {
		
			//Take parameters
			double nnii = Double.valueOf(tnii.getText()).doubleValue();
			double nnjj = Double.valueOf(tnjj.getText()).doubleValue();
			nii = (int)(nnii);
			njj = (int)(nnjj);
			
			beginAnalyze = true;
			start.setLabel("Reset");
			
			note.append(oldText + "\nReading....");
		//	oldText = note.getText();
			video = readArray(namefilel);//Call method to read video
			note.append(oldText + "\nvideo" + "[" + 100 + "]" + "[" + nii + "]" + "[" + njj + "]: " + video[100][nii][njj] + 
						 "\nvideo" + "[" + 110 + "]" + "[" + nii + "]" + "[" + njj + "]: " + video[110][nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			note.append(oldText + "\nConvert and copy data....");
		//	oldText = note.getText();
			u = convertArray(video, nf, ntimes, nii, njj, original, unwrapped, taveraged);//Call method to convert data
			note.append(oldText + "\nu" + "[" + nii + "]" + "[" + njj + "]" + "[" + 100 + "]: " + u[nii][njj][100] +
						 "\nu" + "[" + nii + "]" + "[" + njj + "]" + "[" + 110 + "]: " + u[nii][njj][110]);
		//	oldText = note.getText();
			
			minMax(u, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			origmax = max[nii][njj];
			origmin = min[nii][njj];
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
			//oldText = note.getText();
			
			
			//Flag to plot the original signal
			//signal1.setState(true);
			//originc = true;
			
			//--------------------------------
			
			note.append(oldText + "\nUnwrapping....");
		//	oldText = note.getText();
			unwrapSignal(u, nf, ntimes, nii, njj, unwrapped, max, min);//Call method to unwrap the signal
			note.append(oldText + "\nu" + "[" + nii + "]" + "[" + njj + "]" + "[" + 100 + "]: " + u[nii][njj][100] +
						 "\nu" + "[" + nii + "]" + "[" + njj + "]" + "[" + 110 + "]: " + u[nii][njj][110]);
	       //		oldText = note.getText();
			note.append(oldText + "\nEnd.  ");
			
			minMax(u, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			note.append(oldText + "\nNormalization....");
			// oldText = note.getText();
			normaSignal(u, nf, ntimes, nii, njj, norma, max, min, unwrapped);//Call method to normalize [0:10000]
			
			minMax(u, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			unwmax = max[nii][njj];
			unwmin = min[nii][njj];
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
			//oldText = note.getText();
			
			//Flag to plot the unwrapped signal
			//signal1.setState(false);
			//signal2.setState(true);
			//repaint();
			//--------------------------------
			
			note.append(oldText + "\nTime averaging.... ");
		//	oldText = note.getText();
			timeAverage(u, nf, ntimes, nii, njj, taveraged);//Call method to performe the time average
			
			minMax(u, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			note.append(oldText + "\nNormalization....");
			//oldText = note.getText();
			normaSignal(u, nf, ntimes, nii, njj, norma, max, min, taveraged);//Call method to normalize [0:10000]
			
			minMax(u, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			tavnmax = max[nii][njj];
			tavnmin = min[nii][njj];
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
			//oldText = note.getText();
			
			//Flag to plot the time averaged signal
			//signal2.setState(false);
			signal3.setState(true);
			tavec = true;
			validate();
			//--------------------------------
			
		}
		else {
			if (ae.getSource() == start && start.getLabel().equals("Reset")) {
				beginAnalyze = false;
				start.setLabel("Start");
				dispose();
				repaint();
			}
		}		
		
		
		//Set B:-> shift
		if (ae.getSource() == setshift) {
	
			//Shifting frequency
			double ffreq = Double.valueOf(tfreq.getText()).doubleValue();
			freq = (int)(ffreq);
			ifreq = freq/3;
			
			u = taveraged;
			
			note.append(oldText + "\nShifting....\n");
	//		oldText = note.getText();
			
			shiftSignal(u, nf, ntimes, ifreq, nii, njj, basev1, max, min, ntll, intervsh1, intervsh2, shifted);//Call method to performe shifting
			
			minMax(shifted, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			
			note.append(oldText + "\nNormalization....");
			//oldText = note.getText();
			
			normaSignalGN(shifted, nf, ntimes, nii, njj, norma, max, min);//Call method to normalize [0:10000]
			
			minMax(shifted, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			
			shifmax = max[nii][njj];
			shifmin = min[nii][njj];
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
			// oldText = note.getText();
			
			
			//Flag to plot the shifting line
			//signal1.setState(false);
			//signal2.setState(false);
			//signal3.setState(false);
			
			
			//--------------------------------
				
			//Flag to plot the shifted signal
			signal3.setState(false);
			tavec = false;
			signal5.setState(true);
			shiftc = true;
			validate();
			
			//--------------------------------
			
			u = shifted;
			
			shiftfirsttime	= true;
		}
		
		
		//Set B:-> erase
		if (ae.getSource() == seterase) {
			
				//Taking erasing intervalls
			double nerasesx = Double.valueOf(erasesx.getText()).doubleValue();
			double nerasedx = Double.valueOf(erasedx.getText()).doubleValue();
			ertime1 = (int)(nerasesx);
			ertime2 = (int)(nerasedx);
			
			note.append(oldText + "\nErasing times....");
		//	oldText = note.getText();
			eraseTime(u, nf, ntimes, ertime1, ertime2, erased);
			
			minMax(erased, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			note.append(oldText + "\nNormalization....");
		//	oldText = note.getText();
			normaSignalGN(erased, nf, ntimes, nii, njj, norma, max, min);//Call method to normalize [0:10000]
			
			minMax(erased, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			ermax = max[nii][njj];
			ermin = min[nii][njj];
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			
			
			//--------------------------------
			
			//Flag to plot the erased signal
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
			validate();
			
			//--------------------------------
			
			u = erased;
			
			erasefirsttime	= true;
			
		}
	
	
		//Performe B:-> space average
		if (ae.getSource() == spaaverage) {
		
		
			note.append(oldText + "\nSpace average....");
			// oldText = note.getText();
			spaceAverage(u, nf, ntimes, nii, njj, oldText, note, spaceaveraged);//Call method to performe the space average
			note.append(oldText + "End.  ");
	//		oldText = note.getText();
			
			minMax(spaceaveraged, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			note.append(oldText + "\nNormalization....");
			//oldText = note.getText();
			normaSignalGN(spaceaveraged, nf, ntimes, nii, njj, norma, max, min);//Call method to normalize [0:10000]
			
			minMax(spaceaveraged, nf, ntimes, nii, njj, max, min);//Call method to evaluate min & max
			spamax = max[nii][njj];
			spamin = min[nii][njj];
			note.append(oldText + "\nMax in " + "[" + nii + "]" + "[" + njj + "]: " + max[nii][njj] +
						 "\nmin in " + "[" + nii + "]" + "[" + njj + "]: " + min[nii][njj] + 
						 "\nEnd.  ");
		//	oldText = note.getText();
			
			u = spaceaveraged;
			
			spacefirsttime = true;
			
			//--------------------------------
			//Flag to plot the space averaged signal
			
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
			
			//--------------------------------
			
		}
		else {
			spacefirsttime = false;
		}			
	
	
		//Perform B:-> APD - DI thresholds evaluation -> Distribution
		if (ae.getSource() == apddi) {
		
			note.append(oldText + "\nAPD - DI thresholds....");
			// oldText = note.getText();
			apdDI(u, nf, ntimes, ur, nii, njj, intervsh1, intervsh2, tpb, nb, ntr, apd, di);
			
			note.append(oldText + 
						"\nntr" + "[" + nii + "]" + "[" + njj + "]: " + ntr[nii][njj] + 
						"\nnb" + "[" + nii + "]" + "[" + njj + "]: " + nb[nii][njj] + 
						"\napd" + "[" + nii + "]" + "[" + njj + "]: " + apd[nii][njj][ntr[nii][njj]] + 
						"\ndi" + "[" + nii + "]" + "[" + njj + "]: " + di[nii][njj][ntr[nii][njj]] + 
						"\nEnd.");
		// 	oldText = note.getText();
			
			
			//--------------------------------
			
			//Flag to plot the thresholds
			apdDiMeanS.setState(false);
			apdDiMeanc = false;
			apdDiDist.setState(false);
			apdDistc = false;
			signal8.setState(true);
			thresholdc = true;
			validate();
			
			//--------------------------------
			
		}

		
		//APD - DI mean value
		if (ae.getSource() == apdDiMean) {
			
			note.append(oldText + "\nAPD - DI mean value....");
			//oldText = note.getText();
			
			if (alternancec) {
			
				note.append(oldText + "\nPresence of alternance");
				// oldText = note.getText();
				
				meanApdDiA(nf, ntr, di, apd, mdiAo, mapdAo, mdiAe, mapdAe);//Call method to evaluate mean APD - DI
				
				
				//--------------------------------
				
				//Flag to plot the APD - DI mean
				apdDiDist.setState(false);
				apdDistc = false;
				apdDiMeanS.setState(true);
				apdDiMeanc = true;
				validate();
				
				//--------------------------------
				
			}
			else {
			
				note.append(oldText + "\nWithout alternance");
			//	oldText = note.getText();
				meanApdDi(nf, ntr, di, apd, meandi, meanapd);
				
				note.append(oldText + 
							 "\nMean DI in " + "[" + nii + "]" + "[" + njj + "]: " + meandi[nii][njj] + 
							 "\nMean APD in " + "[" + nii + "]" + "[" + njj + "]: " + meanapd[nii][njj] +
							 "\nEnd.  ");
		//		oldText = note.getText();
				
				note.append(oldText + "\nAPD - DI min - max....");
		//		oldText = note.getText();
				
				minMaxApdDi(nf, ntimes, ntr, apd, di, minDIn, maxDIn, minAPDn, maxAPDn); 
				//Call method to evaluate min - Max APD - DI
				
				note.append(oldText + 
							"\nmin DI in " + "[" + nii + "]" + "[" + njj + "]: " + minDIn[nii][njj] + "\t" +
							"Max DI in " + "[" + nii + "]" + "[" + njj + "]: " + maxDIn[nii][njj] + 
							"\nmin APD in " + "[" + nii + "]" + "[" + njj + "]: " + minAPDn[nii][njj] + "\t" +
							"Max APD in " + "[" + nii + "]" + "[" + njj + "]: " + maxAPDn[nii][njj] + 
							"\nEnd.  ");
		//		oldText = note.getText();
				
				
				//--------------------------------
				
				//Flag to plot the APD - DI mean
				apdDiDist.setState(false);
				apdDistc = false;
				apdDiMeanS.setState(true);
				apdDiMeanc = true;
				validate();
				
				//--------------------------------
			}
			
		}
		else {
			apdDiMeanc = false;
		}

		
		//Movie
		{
		if (ae.getSource() == movie && movie.getLabel().equals("Movie")) {
			startmovie = true;
			movie.setLabel("Stop");
			Thread runner = new Thread(this);
			runner.start();
		}
		else {
			if (ae.getSource() == movie && movie.getLabel().equals("Stop")) {
			
				time = 0;
				startmovie = false;
				movie.setLabel("Movie");
								
			}
		}			
		}
	
	
		//Set parameters
		if (ae.getSource() == setpoint) {
			repaint();
			//beginAnalyze 	= true;
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
		
	//*************************************************************
	public void itemStateChanged(ItemEvent ie ){
				
		//Choice original
		if (signal1.getState() 	== true) 	originc = true;
		else 								originc = false;
		
		//Choice unwrapped
		if (signal2.getState() 	== true) 	unwrapc = true;
		else 								unwrapc = false;
		
		//Choice time averaged
		if (signal3.getState() 	== true) 	tavec = true;
		else 								tavec = false;
		
		//Choice shifting line
		if (signal4.getState()	== true) 	shiftl = true;	
		else 								shiftl = false;
		
		//Choice shifted
		if (signal5.getState() 	== true) 	shiftc = true;
		else 								shiftc = false;
		
		//Choice erased
		if (signal6.getState() 	== true) 	erasec = true;
		else 								erasec = false;
		
		//Choice space averaged
		if (signal7.getState() 	== true) 	spaveragec = true;
		else 								spaveragec = false;
		
		//Choice thresholds
		if (signal8.getState() 	== true) 	thresholdc = true;
		else 								thresholdc = false;
		
		
		
		
		//APD - DI distribution
		if (apdDiDist.getState() == true) 	{
			
			apdDiMeanS.setState(false);
			apdDiMeanc = false;
			//apdDiDist.setState(true);
			apdDistc = true;
			validate();
			
		}
		else 								{
			
			apdDiDist.setState(false);
			apdDistc = false;	
			validate();
		
		}
		
		if (apdDiMeanS.getState() == true) 	{
			
			apdDiDist.setState(false);
			apdDistc = false;
			//apdDiMeanS.setState(true);
			apdDiMeanc = true;
			validate();
			
		}
		else 								{
			
			apdDiMeanS.setState(false);
			apdDiMeanc = false;
			validate();
			
		}	
				
		//Choice for the color map
		nmap = colormap.getSelectedIndex();
		
		//Choice for the maximum CL
		clMaxc = clDuration.getSelectedIndex();
		
		//Choice for alternance
		alternancec = (alternance.getSelectedIndex() != 0);
		
		//Choice for error bars
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
			
			n=-1;
			for( int i = 128; i < 147; i++){
				n = n + 1;
				r[i] = (byte)(200);
				g[i] = (byte)(0 + (n*2));
				b[i] = (byte)(0);
			}
			
			n = -1;
			for( int i = 147; i < 195; i++){
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

	
	//This method reads the data    
	public short[][][] readArray( String namefilel ) { 
		
		try{
			cascade_import data = new cascade_import();
			short [][][] video = data.readVideoFile(namefilel);
			//int [][][] video = data.readVideoFile(namefilel);
			//short [][][] video = data.readVideoFile("2010_feb16_temp_endo_010.dat");
			
			
			return video;
			
        }//end try
		
		catch (IOException e) { 
			
			System.out.println("IOException while reading file.");
			System.exit(0);
		}//close catch
		
		return video;
		
	}//close readArray
	
	
	
	//This method convert the data and copy the original signal
	public short[][][] convertArray(short[][][] video, int nf, int ntimes, 
									int nii, int njj,
									short[][][] original, 
									short[][][] unwrapped,
									short[][][] taveraged){
		
		
		short[][][] u = new short[nf][nf][ntimes];
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
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
	
	
	//This method evaluates the maximum and minimum values
	public static void minMax( short[][][] u, int nf, int ntimes, 
							   int nii, int njj,
							   short[][] max, short[][] min){
		
		//short[][][] u = new short[nf][nf][ntimes];
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
				
				max[i][j] = -32768;
				min[i][j] = 32767;
				
				for (int k = 10; k < (ntimes - 10); k++) {
					
					if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
					if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
				}
			}
		}
		
		
	}//close minMax
	
	
	//This method unwraps the saturated signal
	public static void unwrapSignal( short[][][] u, int nf, int ntimes,
									 int nii, int njj, 
									 short[][][] unwrapped, 
									 short[][] max, short[][] min ){
									 
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
			
				if ( (int)( Math.abs(max[i][j] - min[i][j]) ) > 30000 ){
				
					for (int k = 10; k < (ntimes - 10); k++) {
					
						u[i][j][k] = (short)(u[i][j][k] - 20000);
						unwrapped[i][j][k] = u[i][j][k];
					}
				}
			}
		}
		
	}//close unwrapSignal
	
	
			
	//This method normalizes the signal in [0:10000]
	public static void normaSignal( short[][][] u, int nf, int ntimes, 
									int nii, int njj,
									short norma, 
									short[][] max, short[][] min,
									short[][][] various){
													
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
			
				if ((max[i][j] - min[i][j]) == 0) 
					for (int k = 0; k < (ntimes - 0); k++) {
						u[i][j][k] = 0;
					}
				else
					for (int k = 0; k < (ntimes - 0); k++){
						u[i][j][k] = (short)(norma*(u[i][j][k] - min[i][j])/(max[i][j] - min[i][j]));
						various[i][j][k] = u[i][j][k];
					}
			}
		}
			
	}//close normaSignal
	
	
	//This method performes the time average
	public static void timeAverage( short[][][] u, int nf, int ntimes,
									int nii, int njj, 
									short[][][] taveraged ){
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
			
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
		
	}//close timeAverage
	
	
	//This method shifts the signal without rewrite the u matrix
	public static void shiftSignal( short[][][] u, int nf, int ntimes, int ifreq, 
									int nii, int njj, 
									short[][][] basev1,
									short[][] ntll, 
								    short[][] max, short[][] min,
									short[][] intervsh1, short[][] intervsh2,
								    short[][][] shifted ){
									
				
		short smin[][][]	= new short [nf][nf][1000];
		short smint[][][]	= new short [nf][nf][1000];
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
				
				ntll[i][j] = 0;
				
				for (int k = 0; k < ntimes; k++){
					
					if (k < 1000) smin[i][j][k] = (short)(32767);
					
					basev1[i][j][k] = min[i][j];
					
				}
			}
		}
		
			//Find minima and timing
		for (int nt = ifreq; nt < (ntimes - ifreq); nt = (nt + ifreq) ) {
			
			for (int i = 0; i < nf; i++) {
				for (int j = 0; j < nf; j++) {
					
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
		
			//Take the first time of minima -> intervsh1 for shifting line
		for (int i = 0; i < nf; i++) {
			for (int j = 0; j < nf; j++) {
				
				boolean vero = true;
				
				for (int k = 0; k < ifreq; k++) {
					
					if (smint[i][j][k] > 0 && vero == true) {
						intervsh1[i][j] = smint[i][j][k];
						vero = false;
					}
				}
				
			}
		}
		
			//Interpolation of the minima and shift
		double 	basev, fraq;
		short	idis = 0, idist = 1, idist_old = 1;
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
				
				ntll[i][j] = 1;	
				
				for (int nt = intervsh1[i][j]; nt < intervsh2[i][j]; nt++){
					
					if ( nt > (int)(smint[i][j][ntll[i][j]]) ) ntll[i][j]++;
					
					//Difference every two next minima starting from ntll = 1, ntll = 0
					idis = 	(short)(smin[i][j][ntll[i][j]] - smin[i][j][ntll[i][j] - 1]);
				
					//Delta t for every two minima
					idist_old = idist;
					idist = (short)(smint[i][j][ntll[i][j]] - smint[i][j][ntll[i][j] - 1]); 
					
					//Avoid 0 values
					if (idist == 0) idist = idist_old;
					
					//LINEAR INTERPOLATION between two minima
					
					fraq  = (double)(( (double)(nt) - (double)(smint[i][j][ ntll[i][j] - 1 ] ))/(double)(idist));
					basev = (double)((double)(smin[i][j][ntll[i][j] - 1])*(1.0 - fraq) + (double)(smin[i][j][ntll[i][j]])*fraq);
						
					//Shifting line and subtraction of the drift
					basev1[i][j][nt] 	= (short)(basev);					
					shifted[i][j][nt] 	= (short)(u[i][j][nt] - basev1[i][j][nt]);
					
					//Avoid negative values
					if (shifted[i][j][nt] < 0) shifted[i][j][nt] = 0;
					
					
				}
				
				//Set to 0 the first and last times
				for (int nt = 0; nt < intervsh1[i][j]; nt++){
					shifted[i][j][nt] = 0;
				}
				for (int nt = intervsh2[i][j]; nt < ntimes; nt++){
					shifted[i][j][nt] = 0;
				}
				
			}	
		}
		
	}//close shiftSignal
	
		
		
	//This method performes the space average without rewrite the u matrix
	public static void spaceAverage( short[][][] u, int nf, int ntimes, 
									 int nii, int njj,
									 String oldText, TextArea note,
									 short[][][] spaceaveraged ){
			
		double xlap 		= 0;
		double xcoef[][]	= new double [13][13];
        double xcoeft		= 0;
		
		//short spaceaveraged[][][] = new short [nf][nf][ntimes];
		
        int kt = 50;
        int ktt = 0;
		
		
		//Evaluate the Gaussian coefficients
		note.setText(oldText + "\nGaussian coefficients....");
		oldText = note.getText();

		for (int i = 0; i <= 12; i++){
			for (int j = 0; j <= 12; j++){
				int ii = i - 6;
				int jj = j - 6;
					//Maintain all the parenthesis
				xcoef[i][j] = 1000*9*Math.exp(-( (ii*ii) + (jj*jj) )/8);
				xcoeft 		= xcoeft + xcoef[i][j];
			}
		}
		xcoeft = 1/xcoeft;
		
		note.setText(oldText + "\nAveraging....");
		oldText = note.getText();
		kt=0;
		
		for (int k = 0; k < ntimes; k++){
			
			kt = kt + 1;
			ktt = ktt + 1;
			
			if(kt == 500){
				note.append(oldText + "\ndoing now: " + ktt);
				//oldText = note.getText();
				kt = 0;
			}
			
			
			for (int j = 3; j < (nf - 15); j++){
				for (int i = 3; i < (nf - 15); i++){
					
					xlap = 0;
					
					for (int ii = 0; ii <= 12; ii++){
						for (int jj = 0; jj <= 12; jj++){
							
							xlap = xlap +(u[i+ii][j+jj][k])*xcoef[ii][jj];
						}
					}
					
					spaceaveraged[i][j][k] = (short)(xlap*xcoeft);
					
				}
			}
		}
		
	}//close spaceAverage
	
		
	//This method erase the first and last times
	public static void eraseTime( short[][][] u, int nf, int ntimes, 
								  int ertime1, int ertime2,
								  short[][][] signaler ){
				
		for (int i = 0; i < nf; i++) {
			for (int j = 0; j < nf; j++) {
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
		
		
	}//close eraseTime
	
	
	//This method normalizes the signal in [0:10000] without rewrite the u matrix
	public static void normaSignalGN( short[][][] signal, int nf, int ntimes, 
									  int nii, int njj,
									  short norma, 
									  short[][] max, short[][] min ){
				
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
				
				if ((max[i][j] - min[i][j]) == 0) 
					for (int k = 0; k < (ntimes - 0); k++) {
						signal[i][j][k] = 0;
					}
				else
					for (int k = 0; k < (ntimes - 0); k++){
						signal[i][j][k] = (short)(norma*(signal[i][j][k] - min[i][j])/(max[i][j] - min[i][j]));
					}
			}
		}
		
		
				
	}//close normaSignal
	
	
	
	//This method evaluates APD and DI
	public static void apdDI( short[][][] u, int nf, int ntimes,
							  int ur, int nii, int njj,
							  short[][] istep, short[][] lstep,
							  short[][][] tpb, int[][] nb, int[][] ntr,
							  short[][][] apd, short[][][] di ){
							  
		
		//System.out.println("u" + "[" + nii + "]" + "[" + njj + "]" + "[" + 100 + "]: " + u[nii][njj][100]);
		//System.out.println("u" + "[" + nii + "]" + "[" + njj + "]" + "[" + 110 + "]: " + u[nii][njj][110]);
		//System.out.println("istep" + "[" + nii + "]" + "[" + njj + "]: " + istep[nii][njj]);
		//System.out.println("lstep" + "[" + nii + "]" + "[" + njj + "]: " + lstep[nii][njj]);
		
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
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
		int tzero 	= 0;
		int tzero1 	= 0;
		
		for (int i = 8; i < (nf - 9); i++){
			for (int j = 8; j < (nf - 9); j++){
			
				nsen = 0;
				tzero = 0;
				tzero1 = 0;
			
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
						else tzero = 0;
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
		
		
	}//close adpDI
	
	
	//This method evaluates min - max APD - DI without alternance
	public static void minMaxApdDi( int nf, int ntimes,
									int[][] ntr,
									short[][][] apd, short[][][] di, 
									short[][] minDIn, short[][] maxDIn,
									short[][] minAPDn, short[][] maxAPDn ){
			
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
			
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
				
				//ddi[i][j] = maxdi[i][j] - mindi[i][j];
				//dapd[i][j] = maxapd[i][j] - minapd[i][j];
			}
		}
	
			//System.out.println(minDIn[50][50] + " " + maxDIn[50][50]);
			//System.out.println(minAPDn[50][50] + " " + maxAPDn[50][50]);
	}
	
	
	//This method evaluates mean APD - mean DI without alternance
	public static void meanApdDi( int nf, 
								  int[][] ntr, 
								  short[][][]di, short[][][] apd,
								  int[][] meandi, int[][] meanapd ){
		
		int sumdi = 0;
		int sumapd = 0;
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
			
				sumdi = 0;
				sumapd = 0;
				
				for (int k = 1; k < ntr[i][j] - 1; k++){
					
					sumdi = sumdi + di[i][j][k];
					sumapd = sumapd + apd[i][j][k];
				
				}
				
				meandi[i][j] = sumdi/(ntr[i][j] - 2);
				meanapd[i][j] = sumapd/(ntr[i][j] - 2);
			}
		}
		
		
	}//close meanApdDI
	
	
	
	//This method evaluates mean APD - mean DI with alternance
	public static void meanApdDiA( int nf, 
								   int[][] ntr, 
								   short[][][]di, short[][][] apd,
								   int[][] mdiAo, int[][] mapdAo, 
								   int[][] mdiAe, int[][] mapdAe ){
	
		int sumdio = 0;
		int sumapdo = 0;
		int sumdie = 0;
		int sumapde = 0;
		int numodd = 0;
		int numeven = 0;
		
		
		for (int i = 0; i < nf; i++){
			for (int j = 0; j < nf; j++){
			
				if (ntr[i][j] > 2) {
				
					for (int k = 1; k < ntr[i][j] - 1; k++) {
						if (k % 2 == 1) {
												
							numodd++;
							sumdio = sumdio + 2*di[i][j][k];
							sumapdo = sumapdo + 2*apd[i][j][k];
						}
						else {
							numeven++;
							sumdie = sumdie + 2*di[i][j][k];
							sumapde = sumapde + 2*apd[i][j][k];
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

