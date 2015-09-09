/*
 *  new_graphic_22
 *
 *	Flavio - Alessio Java game 2010!
 *
 */
 
 /*
 To compile it needs:
 - DIRECTORIES: "/movie", "/mask", "/analyzed", "/apddiSpace"
 - LIBRARY: "/org/apache/commons"
 - JAVA FILES: "DataWin", "restDataAn", "cascade"
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.BufferedImage;		
import java.lang.*;
import java.text.*;
import java.io.*;
import javax.imageio.*;  
import java.awt.image.*;
import java.awt.geom.*;

public 	class 	new_graphic_22
	extends	Frame                 
	implements MouseListener, MouseMotionListener, ActionListener, ItemListener, KeyListener, Runnable {
	//Interface declarations ---------------------------------------------------
	private Button		start, startOld, maskb, resetmask, pause, movie; 
	private Button		nxframe, prframe, nx2frame, pr2frame, pftsk, mftsk;
	private Button		save, savemask, loadmask, savemoviepng, generateS;
	private Button		apdDiMean, apdDiRest, apdClRest, cvRest, chooseSignal;
	private Button		close, skipframe, settpplot, resettpplot, setRestScale;
	private Button		setpoint, setshift, seterase, spaaverage, thrset;
	private Button		napddiA, napddiB, napdset, ndiset;
	private Button		saveTrest, saveMrest, saveapdspace, savedispace;
	private Checkbox 	signal1, signal2, signal3, signal4;
	private Checkbox	signal5, signal6, signal7, signal8;
	private Checkbox	apdDiDist, apdDiMeanS, apdSpace, diSpace;
	private Checkbox	apdTBD, diTBD;
	private Label 		comment, point, interval, apddieval;
	private TextField 	tnii, tnjj, tfreq, erasesx, erasedx, thr, gaussTWh;
	private TextField 	answer, frametoskip, tminpplot, tmaxpplot;
	private TextField	nnapddi, nMxapdspace, nmiapdspace, nMxdispace, nmidispace;
	private TextArea 	note;
	private Choice 		colormap, clDuration, alternans, errorBars;
	private Choice		options, optionsLoad, optionsSave, optionsAnalyze;
	private Point p;
	private Point pzoom; 
	int imask=-1,imaskf=-1;   
	int mousezoom=0;         
	int iipx;              
	int iipy;
	//Commands for analysis ----------------------------------------------------
	boolean beginAnalyze = false;		//start analysis for new file
	boolean firsttime = true;			//read and run standard analysis
	boolean spacefirsttime = false;		//run the space time code
	boolean threshc	= false;		//run the APD - DI code
	// Comands for text files --- For window with ascii files ------------------
	int generate=1, selectedSigLoad=0, selectedSigSave=1, selectedSigAnalyze=0;
	int chosen = 0;
    //String optionFiles = "V(t) to save:";
    String optionOriginal 	= "Original";
    String optionUnwrapped 	= "Unwrapped";
    String optionTaveraged 	= "T-averaged";
    String optionShiftLine 	= "Shift Line";
    String optionShifted 	= "Shifted";
	String optionErased 	= "Erased";
    String optionSpaceAver 	= "Space Aver";
    String optionThresholds = "Thresholds";
    String optionAPDDI 		= "APDs-DIs";
	// -------------------------------------------------------------------------
    DataWin da;    			// For the ascii window.
	restDataAn datarest;	// For APD-DI mean restitution
    int savemovieframes=0; 	// Flag for saving movie frames
    String filenamepng;   	// Filename
    File filepng;        	// File   
	//File fileApdDiTxt;		
	//FileWriter fw;
	//Commands for plot --------------------------------------------------------
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
	boolean alternansc	= false;	//APD - DI alternans
	boolean apdDiMeanc	= false;	//APD - DI mean
	boolean apdSpacec	= false;	//APD space
	boolean diSpacec	= false;	//DI space
	boolean apdTBDc		= false;	//APD TBD space
	boolean diTBDc		= false;	//DI TBD space
	boolean frame11		= false;	//Plot the structure
	boolean frame1p		= false;	//Plot the next - previous frame
	// Commands for saving - loading analyzed data and mask --------------------
	String analyzedfile = " ", anfiletosave = " ", stringName = " ";
	String createdmask = "mask", maskname = " ";
	int savemaskpng=0,savedipng=0,saveapdpng=0,saveapddimeanvalue=0,saveapddiTvalues=0;
	String maskfilenamepng, difilenamepng, apdfilenamepng,apddifilenam,apddiMfilenam;
	File filemaskpng, filedipng, fileapdpng;
	int maskcontrol=0;
	//Loading file declarations ------------------------------------------------
	TextField displayFilename = new TextField(26);
	TextField filename = new TextField(26);
	FileDialog	f = new FileDialog(this, "Load a File", FileDialog.LOAD);
	String 	namefilel = " ";
	String  sigsig = " ";
	String 	oldText = " ";
	Dialog	IOError	= new Dialog(this, "Error reading file", true);
	Button	openButton = new Button("Load");
	Button	errorButton = new Button("Try again");
	//Waiting window -----------------------------------------------------------
	private Button wait = new Button(" Wait for calculations progress. ");
	private Button goplot = new Button(" Continue ");
	private Dialog waitwindow = new Dialog(this, "Progress", true);
	boolean closewindow = false;
	// Dimension Parameters
	public int nframes, sizex, sizey;
	int nf = 128, ntimes = 3000;
	int freq, ifreq;
	int	nii = 6, njj = 6;   
	int gaussWh = 8;
	int nzoomx1, nzoomx2, nzoomy1, nzoomy2;  
	int	ertime1, ertime2;
	int ur = 2500;
	short norma = 10000, normaO;
	// To normalize the plot
	int origmin = -10, origmax = 10, unwmin	= -10,  unwmax = 10;
	int nor1min = -10, nor1max = 10, tavnmin = -10, tavnmax = 10; 
	int shifmin = -10, shifmax = 10, ermin = -10, ermax = 10;
	int spamin  = -10,  spamax = 10;
	// To normalize APD - DI distribution
	int mindi1=0, maxdi1=100, minapd1=0, maxapd1=100; 
	int mindi2=0, maxdi2=200, minapd2=0, maxapd2=200;
	int mindi3=0, maxdi3=300, minapd3=0, maxapd3=300;
	int mindi4=0, maxdi4=400, minapd4=0, maxapd4=400;
	int mindi5=0, maxdi5=500, minapd5=0, maxapd5=500;
	int	mindi6=0, maxdi6=600, minapd6=0, maxapd6=600;
	int	mindi7=0, maxdi7=800, minapd7=0, maxapd7=800;
	int mindi=mindi2, maxdi=maxdi2, minapd=minapd2, maxapd=maxapd2;
	int pmindi, pmaxdi, pminapd, pmaxapd;
	int clMaxc;
	// Screen dimension -------------------------------------------------------- 
	short lsx 	= 1240;		//screen width
	short lsy 	= 768;		//screen height
	int pw		= 700;		//plot width
	int ph		= 200;		//plot height
	int phm		= 200;		//plot height with movie
	int phG		= ph;		//plot height parameter
	int dpx		= 80;		//Distance from x window boundary
	int dpy		= 100;		//Distance from y window boundary
	int dax		= 60;		//Distance from x plot boundary
	int day 	= 35;		//Distance from y plot boundary
	int ss		= nf;					//square movie side
	int dsx		= dpx;		//Distance from x window boundary
	int dsy		= dpy + phm + 20;	//Distance from graph boundary	
	int axp1	= dpx + dax;	//x axes point 1
	int ayp1	= dpy + day;	//y axes point 1
	int axp2	= dpx + dax;	//x axes point 2
	int ayp2	= dpy + ph - day;	//y axes point 2
	int axp3	= dpx + pw - dax;	//x axes point 3
	int ayp3	= dpy + ph - day;	//y axes point 3
	int axp11, ayp11, axp21, ayp21, axp31, ayp31;
	// Coord threshold circles -------------------------------------------------
	int pcx1=0, pcy1=0, pcx2=0, pcy2=0;
	int pehbx1=0, pehbx2=0, pehby1=0, pehby2=0;	
	// Color maps --------------------------------------------------------------
	int ir[]	= new int [260]; int ig[]	= new int [260];
	int ib[]	= new int [260]; int irr[]	= new int [260];
	int igg[]	= new int [260]; int ibb[]	= new int [260];
	int irrr[]	= new int [260]; int iggg[]	= new int [260];
	int ibbb[]	= new int [260];
	// -------------------------------------------------------------------------
	int n=-1,nn=0,nmap=0,maplength=196;
	int norig=0,errorb=3,uflag=1,napddi=1; // frame of apd/di to plot in space
	// -------------------------------------------------------------------------
	short 	mask[][],video[][][],u[][][],chosenSignal[][][];  
	short	umovie[][][];
	short 	original[][][],originalimg[][][],unwrapped[][][],taveraged[][][];
	short 	shifted[][][],basevL[][][],spaceaveraged[][][],erased[][][];
	short 	max[][],min[][],ormax[][],ormin[][];
	int  	maxINT[][],	minINT[][];
	int		maMa[];//		= new int[2];
	int 	maMaApdDi[];// = new int[4];
	int  	maxAInt[][], minAInt[][];
	short 	ntll[][],intervsh1f[][],intervsh2p[][];
	short	apd[][][],di[][][],tpb[][][];
	int 	nb[][],ntr[][]; //number of thresholds; number of apd - di 
	short	minDIn[][],maxDIn[][],minAPDn[][],maxAPDn[][];
	//int 	apdSpaceTBD[][], diSpaceTBD[][];
	// APD - DI mean values ----------------------------------------------------
	int 	meandi[][], meanapd[][];// No alternans
	short 	minDIAe[][],maxDIAe[][],minAPDAe[][],maxAPDAe[][];// Presence of alternans
	short 	minDIAo[][],maxDIAo[][],minAPDAo[][],maxAPDAo[][];// Presence of alternans
	int 	mdiAo[][],mapdAo[][],mdiAe[][],mapdAe[][];// Presence of alternans
	//Movie declarations -------------------------------------------------------
	Thread thread;
	IndexColorModel myColorModel1 ;
	IndexColorModel myColorModel2 = null;
	IndexColorModel myColorModel3 = null;
	Image Buffer,bi;
	BufferedImage bimask, biapd, bidi; // To save movie frames and mask
	Graphics gBuffer, big;
	Graphics g2, g3, g4, g5; // To save movie frames and mask
	Label prompthoriz;
	Toolkit toolkit;
	MediaTracker tracker;
	int width,height;
	//  NumberFormat nf = NumberFormat.getInstance(); 
	byte pix[];// = new byte[ss*ss];
	int pixels[];
	// -------------------------------------------------------------------------
	int time = 0, prFrameTime;
	int dt = 2;
	int deltamovietime = 1; //number of file to skipr while playing the movie
	int mintimeplot, maxtimeplot;
	int pmintimeplot = 0;
	int movieon = 0;
	int index = 0;
	// -------------------------------------------------------------------------
	MemoryImageSource source, sourcel;
	int plx1, ply1, plx2, ply2;
	Color background = new Color(65,130,183);
	Color background2 = new Color(234,240,255);
	//**************************************************************************
	public static void main(String args[]) {
		
		new_graphic_22 a = new new_graphic_22();
		
	}//close main
	//**************************************************************************	
	//This method builts the graphical interface		
	public new_graphic_22() {
				
		super("Optical Mapping Analysis");
		super.addKeyListener(this);
		
		setSize(lsx,lsy);
		setFont(new Font("Serif", Font.PLAIN, 16));
		setBackground(background);
		setLayout(new BorderLayout(30,30));
		//INTERFACE ------------------------------------------------------------
		{
			//Load - save file - comment
			displayFilename.setFont(new Font("Serif", Font.PLAIN, 14));
			comment = new Label("Progress: ");
			filename.setFont(new Font("Serif", Font.PLAIN, 14));	//file to save
			note = new TextArea(6,18);
			// --- Buttons -----------------------------------------------------
			start 		= new Button("Start New");
			startOld	= new Button("Start Old");
			maskb 		= new Button("Set Mask"); 
			resetmask	= new Button("Reset Mask");
			pause 		= new Button("        ");
			nxframe		= new Button(" ");
			prframe		= new Button(" ");
			nx2frame	= new Button(" ");
			pr2frame	= new Button(" ");
			pftsk		= new Button(">");
			mftsk		= new Button("<");
			napddiA		= new Button(">");
			napddiB		= new Button("<");
			napdset		= new Button("Set");
			ndiset		= new Button("Set");
			// ----
			save  		= new Button("Save");
			savemask  	= new Button("Save Mask");
			loadmask  	= new Button("Load Mask");
			movie 		= new Button("Movie");
			savemoviepng= new Button("Save Movie"); 
			generateS	= new Button("Stamp");
			apdDiMean	= new Button("Mean");
			apdDiRest	= new Button("APD-DI Restitution");
			apdClRest	= new Button("APD-CL");
			chooseSignal= new Button("Set");
			cvRest		= new Button("CV rest");
			close 		= new Button("Close");
			setpoint	= new Button("Set");
			setshift	= new Button("Shift Freq");
			seterase	= new Button("Erase Intervals");
			spaaverage	= new Button("SpaceAv. GaussW");
			saveTrest	= new Button("Save Time Restit.");
			saveMrest	= new Button("Save Mean Restit.");
			saveapdspace= new Button("Save");	// Save APD in space
			savedispace = new Button("Save");	// Save DI in sapce
			// ----
			//apddi		= new Button("Thr");
			thrset		= new Button("APD-DI Thr.");
			skipframe	= new Button("Set");
			settpplot	= new Button("Set");
			resettpplot = new Button("Def.");
			setRestScale= new Button("Restitution Scale");
			// ----
			start.addActionListener(this);	
			startOld.addActionListener(this);	
			maskb.addActionListener(this);	
			resetmask.addActionListener(this);
			pause.addActionListener(this);
			nxframe.addActionListener(this);
			prframe.addActionListener(this);
			nx2frame.addActionListener(this);
			pr2frame.addActionListener(this);
			pftsk.addActionListener(this);
			mftsk.addActionListener(this);
			napddiA.addActionListener(this);
			napddiB.addActionListener(this);
			napdset.addActionListener(this);
			ndiset.addActionListener(this);
			// ---- Choice to print a signal-point -----------------------------
			options= new Choice();       
			options.addItem("V(t) to print:");
			options.addItem("Original");
			options.addItem("Unwrapped");
			options.addItem("T-averaged");
			options.addItem("Shift Line");
			options.addItem("Shifted");
			options.addItem("Erased");
			options.addItem("Space Aver");
			options.addItem("Thresholds");
			options.addItem("APDs-DIs");
			options.addItem("APD-DI mean");
			options.addItemListener(this);
			// ---- Choice to save a signal-matrix -----------------------------
			optionsSave= new Choice();       
			optionsSave.addItem("Signal to save:");
			optionsSave.addItem("Original");
			optionsSave.addItem("Unwrapped");
			optionsSave.addItem("T-averaged");
			optionsSave.addItem("Shift Line");
			optionsSave.addItem("Shifted");
			optionsSave.addItem("Erased");
			optionsSave.addItem("Space Aver");
			optionsSave.addItemListener(this);
			// ---- Choice to load a signal-matrix -----------------------------
			optionsLoad= new Choice();       
			optionsLoad.addItem("Signal to load:");
			optionsLoad.addItem("Original");
			optionsLoad.addItem("Unwrapped");
			optionsLoad.addItem("T-averaged");
			optionsLoad.addItem("Shift Line");
			optionsLoad.addItem("Shifted");
			optionsLoad.addItem("Erased");
			optionsLoad.addItem("Space Aver");
			optionsLoad.addItemListener(this);
			// ---- Choice to analyze a signal-matrix --------------------------
			optionsAnalyze= new Choice();       
			optionsAnalyze.addItem("Signal to Analyze:");
			optionsAnalyze.addItem("Original");
			optionsAnalyze.addItem("Unwrapped");
			optionsAnalyze.addItem("T-averaged");
			optionsAnalyze.addItem("Shift Line");
			optionsAnalyze.addItem("Shifted");
			optionsAnalyze.addItem("Erased");
			optionsAnalyze.addItem("Space Avr.");
			optionsAnalyze.addItemListener(this);
			// ----
			save.addActionListener(this);
			savemask.addActionListener(this);
			loadmask.addActionListener(this);
			movie.addActionListener(this);
			savemoviepng.addActionListener(this);
			saveTrest.addActionListener(this);
			saveMrest.addActionListener(this);
			saveapdspace.addActionListener(this);
			savedispace.addActionListener(this);
			// ----
			generateS.addActionListener(this);
			apdDiMean.addActionListener(this);
			apdDiRest.addActionListener(this);
			apdClRest.addActionListener(this);
			chooseSignal.addActionListener(this);
			cvRest.addActionListener(this);
			close.addActionListener(this);
			setpoint.addActionListener(this);
			setshift.addActionListener(this);
			seterase.addActionListener(this);
			spaaverage.addActionListener(this);
			//apddi.addActionListener(this);
			thrset.addActionListener(this);
			skipframe.addActionListener(this);
			settpplot.addActionListener(this);
			resettpplot.addActionListener(this);
			setRestScale.addActionListener(this);
			// -----------------------------------------------------------------
			addMouseListener(this);
			addMouseMotionListener(this);   
			// --- Labels and Texts -------------------------------------------- 
			tnii 		= new TextField(" ", 2);
			tnjj 		= new TextField(" ", 2);
			tfreq 		= new TextField("180", 3);
			tminpplot 	= new TextField(" ", 4);
			tmaxpplot 	= new TextField(" ", 4);
			nnapddi		= new TextField("1",2);
			frametoskip = new TextField("2",2);
			nMxapdspace = new TextField(" ", 3);
			nmiapdspace = new TextField(" ", 3);
			nMxdispace 	= new TextField(" ", 3);
			nmidispace 	= new TextField(" ", 3);
			interval	= new Label("Erase intervals:\t", Label.LEFT);
			erasesx		= new TextField("100", 3);
			erasedx 	= new TextField("100", 3);
			thr			= new TextField("2500", 3);
			gaussTWh 	= new TextField("8", 2);
			// --- Plot conditions ---------------------------------------------
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
			apdTBD		= new Checkbox("APD TBD",false);
			diTBD		= new Checkbox("DI TBD",false);
			// ----
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
			apdTBD.addItemListener(this);
			diTBD.addItemListener(this);
			openButton.addActionListener(this);
			openButton.setBackground(Color.green);
			// Color Choice
			colormap = new Choice();
			colormap.addItem("Red-Black");
			colormap.addItem("Red-Blue");
			colormap.addItem("Gray");
			colormap.addItemListener( this );
			// CL Choice
			clDuration = new Choice();
			clDuration.addItem("100");
			clDuration.addItem("200");
			clDuration.addItem("300");
			clDuration.addItem("400");
			clDuration.addItem("500");
			clDuration.addItem("600");
			clDuration.addItem("800");
			// alternans Choice
			alternans = new Choice();
			alternans.addItem("No Altern.");
			alternans.addItem("Alternans");
			alternans.addItemListener(this);
			// Error Bars Choice
			errorBars = new Choice();
			errorBars.addItem("Both");
			errorBars.addItem("Horizontal");
			errorBars.addItem("Vertical");
			errorBars.addItem("No Bars");
			errorBars.addItemListener(this);
			// --- Color maps --------------------------------------------------
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
				// Color map 2: reversed
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
				// Color map 3: gray scale
				createPalette3(r, g, b);
				myColorModel3 = new IndexColorModel(1, 228, r, g, b);
				//for(int i = 0; i < maplength; i++){
				for(int i = 0; i < 255; i++){
					irrr[i] = (int)(1.*i);
					iggg[i] = (int)(1.*i);
					ibbb[i] = (int)(1.*i);
				}
			}//end color map		
			
			// Elements Position -----------------------------------------------
			{
				// --- North ---------------------------------------------------
				Panel rowN = new Panel();
				rowN.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowN.add    ( optionsLoad ); 
				rowN.add	( openButton );
				rowN.add	( displayFilename );
				rowN.add    ( optionsSave );
				rowN.add	( save );
				rowN.add	( filename );
				rowN.add    ( generateS );
				rowN.add    ( options ); 
				//rowN.add	( close );
				add 		( rowN, BorderLayout.NORTH );
				// --- South ---------------------------------------------------
				Panel rowS = new Panel();
				rowS.setLayout(new GridLayout(1,4,15,16));
				// --- First column ---				
				Panel rowS2 = new Panel();
				rowS2.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS2.add	( new Label("Point:\t", Label.LEFT));
				rowS2.add	( tnii );
				rowS2.add	( tnjj );
				rowS2.add	( setpoint );
				
				Panel rowS4 = new Panel();
				rowS4.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS4.add	( new Label("Frames:\t", Label.LEFT));
				rowS4.add	( frametoskip );
				rowS4.add	( mftsk );
				rowS4.add	( pftsk );
				rowS4.add	( skipframe );
				
				Panel rowS41 = new Panel();
				rowS41.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS41.add	( new Label("Zoom:\t", Label.LEFT));
				rowS41.add	( tminpplot );
				rowS41.add	( tmaxpplot );
				rowS41.add	( settpplot );
				rowS41.add	( resettpplot );
				
				Panel rowE82 = new Panel();		//Color maps and Max CL
				rowE82.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE82.add	( new Label("Movie:\t"));
				rowE82.add	( colormap );
				
				Panel rowS234 = new Panel();
				rowS234.setLayout(new GridLayout(4,1));
				rowS234.add	( rowS4 );
				rowS234.add	( rowE82 );
				rowS234.add	( rowS2 );
				rowS234.add	( rowS41 );
				rowS.add	( rowS234 );
				
				// --- Second column ---
				Panel rowS42 = new Panel();
				rowS42.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS42.add	( new Label("APD-DI Fr:\t") );
				rowS42.add	( nnapddi );
				rowS42.add	( napddiB );
				rowS42.add	( napddiA );
				
				Panel rowS43 = new Panel();
				rowS43.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS43.add	( new Label("APDs:\t") );
				rowS43.add	( nmiapdspace );
				rowS43.add	( nMxapdspace );
				rowS43.add	( napdset );
				rowS43.add	( saveapdspace );
				
				Panel rowS44 = new Panel();
				rowS44.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowS44.add	( new Label("DIs :\t") );
				rowS44.add	( nmidispace );
				rowS44.add	( nMxdispace );
				rowS44.add	( ndiset );
				rowS44.add	( savedispace );
				
				Panel rowS1 = new Panel();
				rowS1.setLayout(new FlowLayout(FlowLayout.LEFT));
				
				Panel rowSc1r = new Panel();
				rowSc1r.setLayout(new GridLayout(4,1) );
				rowSc1r.add ( rowS42 );
				rowSc1r.add ( rowS43 );
				rowSc1r.add ( rowS44 );	
				rowSc1r.add ( rowS1 );			
				rowS.add	( rowSc1r );
				
				// --- Third column --- 
				Panel rowSo1 = new Panel();
				rowSo1.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowSo1.add	( note );
				rowS.add	( rowSo1 );
				
				// --- Fourth column ---
				Panel rowStc = new Panel();
				rowStc.setLayout(new GridLayout(3,1));
				rowStc.add	( close );
				rowStc.add	( saveTrest );
				rowStc.add	( saveMrest );
				rowS.add	( rowStc );
				
				add 		( rowS, BorderLayout.SOUTH );
				
				// --- West ----------------------------------------------------
				Panel rowW = new Panel();
				rowW.setLayout(new GridLayout(11,1,2,2));
				rowW.add	( start );
				//rowW.add	( startOld );
				rowW.add	( maskb );
				rowW.add	( loadmask ); 
				rowW.add	( resetmask );
				rowW.add	( savemask );
				rowW.add	( movie );
				rowW.add	( pause );
				
				Panel rowWfpm = new Panel();
				rowWfpm.setLayout(new GridLayout(2,2,2,2));
				rowWfpm.add	( nxframe );
				rowWfpm.add	( prframe );
				rowWfpm.add	( nx2frame );
				rowWfpm.add	( pr2frame );
				rowW.add	( rowWfpm );
				rowW.add    ( savemoviepng );
				
				add 		( rowW, BorderLayout.WEST );
				
				// --- East ----------------------------------------------------
				Panel rowE = new Panel();	//General west layout
				rowE.setLayout(new GridLayout(15,1));
				
				Panel rowE821 = new Panel();		//Color maps and Max CL
				rowE821.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE821.add	( optionsAnalyze );
				rowE821.add	( chooseSignal );
				rowE.add	( rowE821 );
				
				Panel rowE40 = new Panel();	//Erasing interval
				rowE40.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE40.add	( seterase );
				rowE40.add	( erasesx );
				rowE40.add	( erasedx );
				rowE.add	( rowE40 );
				
				Panel rowE2 = new Panel();	//Shift freq
				rowE2.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE2.add	( setshift );
				rowE2.add	( tfreq );
				rowE.add	( rowE2 );
				
				Panel rowE99 = new Panel();	//perform space average 
				rowE99.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE99.add	( spaaverage );
				rowE99.add	( gaussTWh );
				rowE.add	( rowE99 );
				
				Panel rowE23 = new Panel();
				rowE23.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE23.add	( thrset );
				rowE23.add 	( thr );
				rowE.add	( rowE23 );
				
				Panel rowE83 = new Panel();	//Color maps and Max CL
				rowE83.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE83.add	( alternans );
				//rowE83.add	( apddi );
				rowE83.add	( apdDiMean );
				rowE.add	( rowE83 );
				
				Panel rowE85 = new Panel();		//Max CL
				rowE85.setLayout(new FlowLayout(FlowLayout.LEFT));
				rowE85.add	( setRestScale );
				rowE85.add	( clDuration );
				rowE.add	( rowE85 );
				
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
				
				Panel rowE87 = new Panel();	
				rowE87.setLayout(new GridLayout(1,2));
				rowE87.add	( apdTBD );
				rowE87.add	( diTBD );
				rowE.add	( rowE87 );
				
				add		 	( rowE, BorderLayout.EAST );
			} // End element position
		} // End interface
		
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
	//**************************************************************************
	// Plotting and Saving
	//**************************************************************************
	public void paint(Graphics g) {

		// Image to save movie frames
		BufferedImage bi = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
		// Image to save mask
		BufferedImage bimask = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g3 = bimask.createGraphics();
		// Image to save space DI 
		BufferedImage bidi = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g4 = bidi.createGraphics();
		// Image to save space APD 
		BufferedImage biapd = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g5 = biapd.createGraphics();
		
		Buffer=createImage(dpx+pw+30, dsy+ph+2*ss);
        gBuffer=Buffer.getGraphics();
		gBuffer.setColor(background2);
		gBuffer.fillRect(dpx-15, dpy-20, pw+dpx+30, dsy+ph+2*ss); 
		
		// Plot parameters
		int pp1 = 0, pp2 = 0, kk1 = 0, kk2 = 0;
		// Graph plot
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
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
			gBuffer.drawString("SELECTED POINT SIGNAL TIME EVOLUTION", dpx+2, dpy-3);
			gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
			// Structure square
			gBuffer.setColor(Color.black);
			gBuffer.fillRect(dsx, dsy, 2*ss, 2*ss);
			// Restitution square
			gBuffer.setColor(Color.black);
			gBuffer.fillRect(dsx+pw-2*ss, dsy, 2*ss, 2*ss);
			
			// Save MASK as png and bin
			if (savemaskpng==1) {
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii=(int)(maplength*mask[i+6][j+6]);
						if (iii > maplength) iii = maplength;
						if (iii < 30) iii=0;
						g3.setColor(new Color(irr[iii],igg[iii],ibb[iii]));
						g3.fillRect(i*2, j*2, 2, 2);
					}
				}
				String maskfilenamepng = String.valueOf("mask/"+"mask_"+analyzedfile+".png");
				filemaskpng = new File(maskfilenamepng);
				try {
					ImageIO.write(bimask, "png", filemaskpng);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				savemaskpng = 0;
				note.append("Mask Saved");
			}
			
			// Original -> signal1
			if (originc) {
				gBuffer.setColor(Color.red);
				gBuffer.drawString("OrSi", pw/3, dpy+day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				if (selectedSigLoad == 0 || selectedSigLoad == 1) {
					origmin = ormin[nii][njj];
					origmax	= ormax[nii][njj];
				}
				else {
					origmin = -10;
					origmax = 10;
				}
				gBuffer.drawString(Integer.toString(origmin), dpx+dax/5, dpy+phG-day-1);
				gBuffer.drawString(Integer.toString(origmax), dpx+dax/5, dpy+day+10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = origmax - origmin;
				for (int t = mintimeplot; t < maxtimeplot; t = t+dt) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(original[nii][njj][tT1]-origmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(original[nii][njj][tT2]-origmin)/(deltaUn);
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
				for (int t = mintimeplot; t < maxtimeplot; t = t+dt) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t+1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(unwrapped[nii][njj][tT1]-unwmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(unwrapped[nii][njj][tT2]-unwmin)/(deltaUn);
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
				for (int t = mintimeplot; t < maxtimeplot; t = t+dt) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(taveraged[nii][njj][tT1]-tavnmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(taveraged[nii][njj][tT2]-tavnmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}
			}// end time averaged 
			
			// Shifting line -> signal4 
			if (shiftl) {
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("ShLn", pw/3 + 3*dax + 6, dpy + day/2);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = tavnmax - tavnmin;
				for (int t = mintimeplot; t < maxtimeplot; t = t+dt) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(basevL[nii][njj][tT1]-tavnmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(basevL[nii][njj][tT2]-tavnmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}
			}// end shifted line
			
			// Shifted -> signal5
			if (shiftc) {
				gBuffer.setColor(Color.orange);
				gBuffer.drawString("ShSi", pw/3 + 4*dax + 8, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(shifmin), dpx + dax/5, dpy + phG - day - 1);
				gBuffer.drawString(Integer.toString(shifmax), dpx + dax/5, dpy + day + 10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = shifmax - shifmin;
				for (int t = mintimeplot; t < maxtimeplot; t = t+dt) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(shifted[nii][njj][tT1]-shifmin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(shifted[nii][njj][tT2]-shifmin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}	
			}//end shift signal
			
			//Erased -> signal6
			if (erasec) {
				gBuffer.setColor(Color.orange);
				gBuffer.drawString("SeSi", pw/3 + 5*dax + 4, dpy + day/2);
				gBuffer.drawString(Integer.toString(mintimeplot),dpx+dax,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(maxtimeplot),dpx+pw-dax-dax/2,dpy+phG-day/2);
				gBuffer.drawString(Integer.toString(ermin), dpx + dax/5, dpy + phG - day - 1);
				gBuffer.drawString(Integer.toString(ermax), dpx + dax/5, dpy + day + 10);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = ermax - ermin;
				for (int t = mintimeplot; t < maxtimeplot; t = t+dt) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(erased[nii][njj][tT1]-ermin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(erased[nii][njj][tT2]-ermin)/(deltaUn);
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
				for (int t = mintimeplot; t < maxtimeplot; t = t+2) {
					pp1=dpx+dax+(t - mintimeplot)*(pw - 2*dax)/(deltaTn);
					pp2=dpx+dax+(t + 1 - mintimeplot)*(pw - 2*dax)/(deltaTn);
					int tT1 = t/dt;
					int tT2 = (t+dt)/dt;
					kk1=dpy+phG-1*day-(phG-2*day)*(spaceaveraged[nii][njj][tT1]-spamin)/(deltaUn);
					kk2=dpy+phG-1*day-(phG-2*day)*(spaceaveraged[nii][njj][tT2]-spamin)/(deltaUn);
					gBuffer.drawLine(pp1, kk1, pp2, kk2);
				}			
			}// close space average
			
			// APD - DI thresholds-> signal8
			if (thresholdc) {
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("APD - DI thresholds\n", pw/4 + dax, dpy + day/2);
				int deltaTn = maxtimeplot - mintimeplot;
				int deltaUn = max[nii][njj]-min[nii][njj];
				for (int n = 1; n < nb[nii][njj]-1; n++) {
					// tpb is already multiplied by dt
					if (tpb[nii][njj][n] >= mintimeplot && tpb[nii][njj][n] <= maxtimeplot) {
						pcx1=dpx+dax+(tpb[nii][njj][n]-mintimeplot)*(pw-2*dax)/(deltaTn);	
						pcy1=dpy+phG-1*day-(phG-2*day)*(ur-min[nii][njj])/(deltaUn);
						gBuffer.fillOval(pcx1-2, pcy1-2, 2*2, 2*2);
						// Plot the current DI on the plot
						if (diSpacec || diTBDc && n==napddi) {
							pcx1=dpx+dax+(tpb[nii][njj][n+n-1]-mintimeplot)*(pw-2*dax)/(deltaTn);	
							pcy1=dpy+phG-1*day-(phG-2*day)*(ur-min[nii][njj])/(deltaUn);
							gBuffer.drawLine(pcx1, pcy1, pcx1, dpy+phG-day);
							pcx1=dpx+dax+(tpb[nii][njj][n+n]-mintimeplot)*(pw-2*dax)/(deltaTn);	
							pcy1=dpy+phG-1*day-(phG-2*day)*(ur-min[nii][njj])/(deltaUn);
							gBuffer.drawLine(pcx1, pcy1, pcx1, dpy+phG-day);
						}
						// Plot the current APD on the plot
						if (apdSpacec || apdTBDc && n==napddi) {
							pcx1=dpx+dax+(tpb[nii][njj][n+n]-mintimeplot)*(pw-2*dax)/(deltaTn);	
							pcy1=dpy+phG-1*day-(phG-2*day)*(ur-min[nii][njj])/(deltaUn);
							gBuffer.drawLine(pcx1, pcy1, pcx1, dpy+phG-day);
							pcx1=dpx+dax+(tpb[nii][njj][n+n+1]-mintimeplot)*(pw-2*dax)/(deltaTn);	
							pcy1=dpy+phG-1*day-(phG-2*day)*(ur-min[nii][njj])/(deltaUn);
							gBuffer.drawLine(pcx1, pcy1, pcx1, dpy+phG-day);
						}
					}
				}
				gBuffer.drawString(Integer.toString(ur), dpx + dax/4, pcy1);
			}//close APD - DI thresholds
			
			// ----------------- Structure -------------------------------------

			// Static frame for STRUCTURE and MASK.
			if (frame11) {
				
				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString("STRUCTURE & MASK",dsx+2,dsy-3);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
				int ftime = 350; // Default frame
				for (int ii = 0; ii < maplength; ii += 1){
					gBuffer.setColor(new Color(irrr[ii],iggg[ii],ibbb[ii]));
					gBuffer.fillRect(dsx+2*ss+10,dsy+2*ss-16-ii,10,1);
				}
				int iii = 0;
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						if(mask[i+6][j+6] == 1){
							if (selectedSigLoad == 0) {
								if(original[i][j][ftime] < 0){
									iii=(int)(255*(-original[i][j][ftime]))/(32767);
								}
								else {
									iii=(int)(255*(original[i][j][ftime]))/(32767);
								}
							}
							if (selectedSigLoad == 1) {
								if(original[i][j][ftime] < 0){
									iii=(int)(255*(-original[i][j][ftime]))/(32767);
								}
								else {
									iii=(int)(255*(original[i][j][ftime]))/(32767);
								}
							}
							if (selectedSigLoad == 2) {
								iii=(int)(255*(unwrapped[i][j][ftime]))/(norma);
							}
							if (selectedSigLoad == 3) {
								iii=(int)(255*(taveraged[i][j][ftime]))/(norma);
							}
							if (selectedSigLoad == 5) {
								iii=(int)(255*(shifted[i][j][ftime]))/(norma);
							}
							if (selectedSigLoad == 6) {
								iii=(int)(255*(erased[i][j][ftime]))/(norma);
							}
							if (selectedSigLoad == 7) {
								iii=(int)(255*(spaceaveraged[i][j][ftime]))/(norma);
							}
						}
						else {
							iii = 0;
						}
						if (iii > 255) iii = 0;
						if (iii < 0) iii = 0;
						gBuffer.setColor(new Color(irrr[iii],iggg[iii],ibbb[iii]));
						gBuffer.fillRect(dsx+i*2 , dsy+j*2, 2, 2);
					}
				}
				// Point of interest
				gBuffer.setColor(Color.red);
				gBuffer.fillRect(dsx+nii*2-2,dsy+njj*2-2, 4,4);
				// Map scale min - max
				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString(" Vmax",dsx+2*ss,dsy+2*ss-maplength-19);
				gBuffer.drawString(" Vmin",dsx+2*ss,dsy+2*ss);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
			}//end static frame
			
			// ----------------- Restitution -----------------------------------
			
			// APD - DI distribution 
			if (apdDistc) {
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
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("DI (ms)", dsx+pw-ss-4, dsy+2*ss+13);
				gBuffer.drawString("APD", dsx+pw-2*ss-35, dsy+ss);
				gBuffer.drawString("(ms)", dsx+pw-2*ss-35, dsy+ss+13);
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("APD - DI distribution points", dsx+pw-ss-3*ss/4, dsy+14);
				gBuffer.setColor(Color.black);
				gBuffer.drawString(Integer.toString(mindi), dsx+pw-2*ss+4, dsy+2*ss+13);
				gBuffer.drawString(Integer.toString(maxdi), dsx+pw-20, dsy+2*ss+13);
				gBuffer.drawString(Integer.toString(minapd), dsx+pw-2*ss-15, dsy+2*ss-4);
				gBuffer.drawString(Integer.toString(maxapd), dsx+pw-2*ss-25, dsy+20);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
				gBuffer.setColor(Color.yellow);
				for (int n = 1; n < ntr[nii][njj]; n++) {
					if (maxdi>mindi && maxapd>minapd && apd[nii][njj][n]<=maxapd && di[nii][njj][n]<=maxdi) {
						pcx1=dsx+pw-2*ss+10 + (di[nii][njj][n]-mindi)*(2*ss-20)/(maxdi-mindi);
						pcy1=dsy+2*ss-10 - (apd[nii][njj][n]-minapd)*(2*ss-20)/(maxapd-minapd);
						gBuffer.fillOval(pcx1-1, pcy1-1, 1*2, 1*2);
					}
				}
			}// close APD - DI distribution
			
			// APD - DI mean value
			if (apdDiMeanc) {
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
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("DI (ms)", dsx+pw-ss-4, dsy+2*ss+13);
				gBuffer.drawString("APD", dsx+pw-2*ss-35, dsy+ss);
				gBuffer.drawString("(ms)", dsx+pw-2*ss-35, dsy+ss+13);
				gBuffer.setColor(Color.yellow);
				gBuffer.drawString("APD - DI restitution curve", dsx+pw-ss-3*ss/4, dsy+14);
				gBuffer.setColor(Color.black);
				gBuffer.drawString(Integer.toString(mindi), dsx+pw-2*ss+4, dsy+2*ss+13);
				gBuffer.drawString(Integer.toString(maxdi), dsx+pw-20, dsy+2*ss+13);
				gBuffer.drawString(Integer.toString(minapd), dsx+pw-2*ss-15, dsy+2*ss-4);
				gBuffer.drawString(Integer.toString(maxapd), dsx+pw-2*ss-25, dsy+20);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
				
				if (mapdAo[nii][njj]<=maxapd && mdiAo[nii][njj]<=maxdi) {
					if (alternansc) {
						int rr = 2;
						// Odd -------------------------------------------------
						gBuffer.setColor(Color.red);
						pcx1 = dsx+pw-2*ss+10 + (mdiAo[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
						pcy1 = dsy+2*ss-10 - (2*ss - 20)*(mapdAo[nii][njj] - minapd)/(maxapd - minapd);
						gBuffer.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
						// Error bars
						if (errorb == 3){
							// No error bars
						}
						if (errorb == 1) {
							// Horizontal only
							pehbx1 = dsx+pw-2*ss+10 + (minDIAe[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehbx2 = dsx+pw-2*ss+10 + (maxDIAe[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						}
						else if (errorb == 2){
							// Vertical only
							pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAe[nii][njj] - minapd)/(maxapd - minapd);
							pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAe[nii][njj] - minapd)/(maxapd - minapd);
							gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
						}
						else if (errorb == 0){
							// Horizontal & vertical
							pehbx1 = dsx+pw-2*ss+10 + (minDIAe[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehbx2 = dsx+pw-2*ss+10 + (maxDIAe[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAe[nii][njj] - minapd)/(maxapd - minapd);
							pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAe[nii][njj] - minapd)/(maxapd - minapd);
							
							gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
							gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
						}
						// Even ------------------------------------------------
						gBuffer.setColor(Color.orange);
						pcx1 = dsx+pw-2*ss+10 + (mdiAe[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
						pcy1 = dsy+2*ss-10 - (2*ss - 20)*(mapdAe[nii][njj] - minapd)/(maxapd - minapd);
						gBuffer.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
						// Error bars
						if (errorb == 3){
						// No error bars
						}
						if (errorb == 1) {
							// Horizontal only
							pehbx1 = dsx+pw-2*ss+10 + (minDIAo[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehbx2 = dsx+pw-2*ss+10 + (maxDIAo[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
						}
						else if (errorb == 2){
							// Vertical only
							pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAo[nii][njj] - minapd)/(maxapd - minapd);
							pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAo[nii][njj] - minapd)/(maxapd - minapd);
							gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
						}
						else if (errorb == 0){
							// Horizontal & vertical
							pehbx1 = dsx+pw-2*ss+10 + (minDIAo[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehbx2 = dsx+pw-2*ss+10 + (maxDIAo[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDAo[nii][njj] - minapd)/(maxapd - minapd);
							pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDAo[nii][njj] - minapd)/(maxapd - minapd);
							gBuffer.drawLine(pehbx1, pcy1 - rr*0, pehbx2, pcy1 - rr*0);
							gBuffer.drawLine(pcx1 - rr*0, pehby1, pcx1 - rr*0, pehby2);
						}
					}
					else {
						int rr = 2;
						gBuffer.setColor(Color.green);
						pcx1 = dsx+pw-2*ss+10 + (meandi[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
						pcy1 = dsy+2*ss-10 - (2*ss - 20)*(meanapd[nii][njj]-minapd)/(maxapd - minapd);
						gBuffer.fillOval(pcx1 - rr, pcy1 - rr, 2*rr, 2*rr);
						
						// Error bars
						gBuffer.setColor(Color.red);
						if (errorb == 3){
							// No error bars
						}
						if (errorb == 1) {
							// Horizontal only
							pehbx1 = dsx+pw-2*ss+10 + (minDIn[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehbx2 = dsx+pw-2*ss+10 + (maxDIn[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							gBuffer.drawLine(pehbx1, pcy1, pehbx2, pcy1);
						}
						else if (errorb == 2){
							// Vertical only
							pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDn[nii][njj] - minapd)/(maxapd - minapd);
							pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDn[nii][njj] - minapd)/(maxapd - minapd);
							gBuffer.drawLine(pcx1, pehby1, pcx1, pehby2);
						}
						else if (errorb == 0){
							// Horizontal & vertical
							pehbx1 = dsx+pw-2*ss+10 + (minDIn[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehbx2 = dsx+pw-2*ss+10 + (maxDIn[nii][njj]-mindi)*(2*ss - 20)/(maxdi - mindi);
							pehby1 = dsy+2*ss-10 - (2*ss - 20)*(minAPDn[nii][njj] - minapd)/(maxapd - minapd);
							pehby2 = dsy+2*ss-10 - (2*ss - 20)*(maxAPDn[nii][njj] - minapd)/(maxapd - minapd);
							gBuffer.drawLine(pehbx1, pcy1, pehbx2, pcy1);
							gBuffer.drawLine(pcx1, pehby1, pcx1, pehby2);
						}
						// Save APD - DI mean value without alternans
						if (saveapddimeanvalue==1) {
							String s = "Mean APD - DI without alternans\n";
							s = s+"\n"+String.valueOf(meandi[nii][njj]).toString()+"\t";
							s = s+"\t"+tnii.getText()+"\t"+tnjj.getText();
							
							String apddiMfilenam = String.valueOf("analyzed/"+"M_APDDI_no_alt_"+analyzedfile+"_p_"+nii+"_"+njj);
							File fileApdDiTxt = new File(apddiMfilenam);
							try {
								FileWriter fw = new FileWriter(fileApdDiTxt);
								fw.write(s);
							} catch (IOException ex) {
								ex.printStackTrace();
							}
							saveapddimeanvalue = 0;
						}
					}	
				}
				else {
					gBuffer.setColor(Color.green);
					gBuffer.drawString("Change ZOOM", dsx+pw-ss-3*ss/4, dsy+ss/2);
				}	
				
			}// close APD - DI mean value
			
			// APD space
			if (apdSpacec) {
				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("APD SPACE DISTRIBUTION", dsx+pw-2*ss+2, dpy-3);
				gBuffer.drawString("APDmax", dsx+pw-2*ss-70, dsy+2*ss-maplength-19);
				gBuffer.drawString("APDmin", dsx+pw-2*ss-70, dsy+2*ss);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString("APD: ", dsx+pw-ss-35, dsy-2);
				gBuffer.drawString(String.valueOf(apd[nii][njj][napddi])+" ms", dsx+pw-ss+5, dsy-2);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
				for (int ii = 0; ii < (maplength - 1); ii += 1){
					int iii = ii;
					if (iii < 30) iii = 30;
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+pw-2*ss-50, dsy+2*ss-16-ii, 10, 1);
				}
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
						if (mask[i+6][j+6] == 1) {
							iii=(int)(maplength*(apd[i][j][napddi]-maMaApdDi[2])/(maMaApdDi[3]-maMaApdDi[2]));
						}	
						if (iii > maplength) iii=maplength;
						if (iii < 30) iii=30;
						gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
						gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
							// To save png
						g5.setColor(new Color(ir[iii],ig[iii],ib[iii]));
						g5.fillRect(i*2 , j*2 , 2, 2);
					}
				}
				// Save APD space on png file
				if (saveapdpng==1) {
					String apdfilenamepng = String.valueOf("apddiSpace/"+"APD_"+analyzedfile+"_nfr_"+napddi+".png");
					fileapdpng = new File(apdfilenamepng);
					try {
						ImageIO.write(biapd, "png", fileapdpng);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					saveapdpng = 0;
				}
			}// close APD space
			
			// DI space
			if (diSpacec) {
				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("DI SPACE DISTRIBUTION", dsx+pw-2*ss+2, dpy-3);
				gBuffer.drawString("DImax", dsx+pw-2*ss-70, dsy+2*ss-maplength-19);
				gBuffer.drawString("DImin", dsx+pw-2*ss-70, dsy+2*ss);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString("DI: ", dsx+pw-ss-35, dsy-2);
				gBuffer.drawString(String.valueOf(di[nii][njj][napddi])+" ms", dsx+pw-ss, dsy-2);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
				// Plot DI in space
				for (int ii = 0; ii < (maplength - 1); ii += 1){
					int iii = ii;
					if (iii < 30) iii = 30;
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+pw-2*ss-50, dsy+2*ss-16-ii, 10, 1);
				}
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
						if (mask[i+6][j+6] == 1) {
							iii=(int)(maplength*(di[i][j][napddi]-maMaApdDi[0])/(maMaApdDi[1]-maMaApdDi[0]));
						}	
						/*if (i == nii && j == njj) {
							System.out.println("iii "+iii+" di "+di[i][j][napddi]);
							System.out.println("mindi "+maMaApdDi[0] + "Mxdi "+maMaApdDi[1]);
						}*/
						//if (iii > maplength) iii = maplength-1;
						if (iii > maplength) iii = maplength;
						if (iii < 30) iii = maplength;
						gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
						gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
						// To save png
						g4.setColor(new Color(ir[iii],ig[iii],ib[iii]));
						g4.fillRect(i*2 , j*2 , 2, 2);
					}
				}
				// Save DI space on png file
				if (savedipng==1) {
					String difilenamepng = String.valueOf("apddiSpace/"+"DI_"+analyzedfile+"_nfr_"+napddi+".png");
					filedipng = new File(difilenamepng);
					try {
						ImageIO.write(bidi, "png", filedipng);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					savedipng = 0;
				}
			}// close DI space
			
			// APD TBD space
			if (apdTBDc) {
				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("APD SPACE TOPOLOGY", dsx+pw-2*ss+2, dpy-3);
				gBuffer.drawString("APDmax", dsx+pw-2*ss-70, dsy-2);
				gBuffer.drawString("APDmin", dsx+pw-2*ss-70, dsy+2*ss+14);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString("APD: ", dsx+pw-ss-35, dsy-2);
				gBuffer.drawString(String.valueOf(apd[nii][njj][napddi])+" ms", dsx+pw-ss+5, dsy-2);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
									
				for (int ii = 70; ii < (maplength - 11); ii += 1){
					int iii = ii;
					if (iii > 100) iii = 100; //reference value
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+pw-2*ss-50, dsy+2*ss+68-ii, 10, 1);
				}
				gBuffer.setColor(Color.white);
				gBuffer.fillRect(dsx+pw-2*ss-50, dsy+2*ss-142, 10, 26);
				for (int ii = 80; ii < (maplength - 1); ii += 1){
					int iii = ii;
					if (iii < 120) iii = 120; // reference value
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+pw-2*ss-50, dsy+ss+66-ii, 10, 1);
				}
				
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
						int diffAPD = 0;
						diffAPD = apd[i][j][napddi] - apd[i][j][napddi+1];
						if (mask[i+6][j+6] == 1 && ntr[i][j] > 2) {
							if ((int)( Math.abs(diffAPD) ) <= dt) {
								gBuffer.setColor(Color.white);
								gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
								// To save png
								g5.fillRect(i*2 , j*2 , 2, 2);
							}
							else if (diffAPD > dt) {
								iii=(int)(120+(75)*(apd[i][j][napddi]-maMaApdDi[2])/(maMaApdDi[3]-maMaApdDi[2]));
								if (iii > maplength) iii=maplength;
								if (iii < 120) iii=maplength;
								gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
								gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
							 	// To save png
							 	g5.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							 	g5.fillRect(i*2 , j*2 , 2, 2);
							}
							else if (diffAPD < -dt) {
								iii=(int)(70+(30)*(apd[i][j][napddi]-maMaApdDi[2])/(maMaApdDi[3]-maMaApdDi[2]));
								if (iii > maplength) iii=maplength;
								if (iii < 30) iii=maplength;
								gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							 	gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
							 	// To save png
							 	g5.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							 	g5.fillRect(i*2 , j*2 , 2, 2);
							}
						}
					}
				}
				// Save APD space on png file
				if (saveapdpng==1) {
					String apdfilenamepng = String.valueOf("apddiSpace/"+
					"APD_TBD"+analyzedfile+"_nfr_"+napddi+".png");
					fileapdpng = new File(apdfilenamepng);
					try {
						ImageIO.write(biapd, "png", fileapdpng);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					saveapdpng = 0;
				}
			}// close APD TBD space
			
			// DI TBD space
			if (diTBDc) {
				gBuffer.setColor(Color.black);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 16));
				gBuffer.drawString("DI SPACE DISTRIBUTION", dsx+pw-2*ss+2, dpy-3);
				gBuffer.drawString("DImax", dsx+pw-2*ss-70, dsy-2);
				gBuffer.drawString("DImin", dsx+pw-2*ss-70, dsy+2*ss);
				gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
				gBuffer.drawString("DI: ", dsx+pw-ss-35, dsy-2);
				gBuffer.drawString(String.valueOf(di[nii][njj][napddi])+" ms", dsx+pw-ss, dsy-2);
				gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
				// Plot DI TBD in space
				for (int ii = 70; ii < (maplength - 11); ii += 1){
					int iii = ii;
					if (iii > 100) iii = 100; //reference value
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+pw-2*ss-50, dsy+2*ss+68-ii, 10, 1);
				}
				gBuffer.setColor(Color.white);
				gBuffer.fillRect(dsx+pw-2*ss-50, dsy+2*ss-142, 10, 26);
				for (int ii = 80; ii < (maplength - 1); ii += 1){
					int iii = ii;
					if (iii < 120) iii = 120; // reference value
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+pw-2*ss-50, dsy+ss+66-ii, 10, 1);
				}
				
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
						int diffDI = 0;
						diffDI = di[i][j][napddi] - di[i][j][napddi+1];
						if (mask[i+6][j+6] == 1 && ntr[i][j] > 2) {
							if ((int)( Math.abs(diffDI) ) <= dt) {
								gBuffer.setColor(Color.white);
								gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
								// To save png
								g5.fillRect(i*2 , j*2 , 2, 2);
							}
							else if (diffDI > dt) {
								iii=(int)(120+(75)*(di[i][j][napddi]-maMaApdDi[0])/(maMaApdDi[1]-maMaApdDi[0]));
								if (iii > maplength) iii=maplength;
								if (iii < 120) iii=maplength;
								gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
								gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
								// To save png
							 	g5.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							 	g5.fillRect(i*2 , j*2 , 2, 2);
							}
							else if (diffDI < -dt) {
								iii=(int)(70+(30)*(di[i][j][napddi]-maMaApdDi[0])/(maMaApdDi[1]-maMaApdDi[0]));
								if (iii > maplength) iii=maplength;
								if (iii < 30) iii=maplength;
								gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							 	gBuffer.fillRect(dsx+pw-2*ss+i*2 , dsy+j*2 , 2, 2);
								// To save png
							 	g5.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							 	g5.fillRect(i*2 , j*2 , 2, 2);
							}
						}
					}
				}
				// Save DI TBD space on png file
				if (savedipng==1) {
					String difilenamepng = String.valueOf("apddiSpace/"+"DI_TBD"+
					analyzedfile+"_nfr_"+napddi+".png");
					filedipng = new File(difilenamepng);
					try {
						ImageIO.write(bidi, "png", filedipng);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					savedipng = 0;
				}
			}// close DI TBD space
			
			// -----------------------------------------------------------------
		}// close if normal plots
		// ------------------- *** Plot movie *** ------------------------------
		else {
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
			// Change graph dimensions
			phG = phm;
			ayp2 = dpy + phG - day;	
			ayp3 = dpy + phG - day;	
			// Resized graph plot
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
				umovie = unwrapped;
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
				gBuffer.setColor(Color.orange);
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
			if (nmap == 0) {// Full scale
				for (int ii = 0; ii < (maplength - 1); ii += 1){
					int iii = ii;
					if(iii < 30) iii = 30;
					gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
					gBuffer.fillRect(dsx+2*ss+10, dsy+2*ss-16-ii, 10, 1);
				}
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
							// To show the structure under the activation
						if(original[i][j][350] < 0){
							iii=(int)(255*(-original[i][j][350]))/(32767);
						}
						else {
							iii=(int)(255*(original[i][j][350]))/(32767);
						}
						if (iii > 255) iii = 0;
						if (iii < 0) iii = 0;
						gBuffer.setColor(new Color(irrr[iii],iggg[iii],ibbb[iii]));
						gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
						g2.setColor(new Color(irrr[iii],iggg[iii],ibbb[iii]));
						g2.fillRect(i*2 , j*2 , 2, 2);
						
						iii = 0;
						if (mask[i+6][j+6] == 1) {
							iii=(int)(maplength*umovie[i][j][time]/norma);
						}	
						if( iii >= 40 ){
			        		if (iii > maplength-1) iii=maplength;
							gBuffer.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
							g2.setColor(new Color(ir[iii],ig[iii],ib[iii]));
							g2.fillRect(i*2 , j*2 , 2, 2);
						}
					}
				}
			}
			if (nmap == 1) {// Reversed scale
				for (int ii = 0; ii < (maplength - 1); ii += 1){
					gBuffer.setColor(new Color(irr[ii],igg[ii],ibb[ii]));
					gBuffer.fillRect(dsx+2*ss+10, dsy+2*ss-16-ii, 10, 1);
				}
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
						if (mask[i+6][j+6] == 1) {
							iii=(int)(maplength*umovie[i][j][time]/norma);
						}	
						if (iii > maplength-1) iii = maplength;
						if (iii < 30) iii = 30;
						gBuffer.setColor(new Color(irr[iii],igg[iii],ibb[iii]));
						gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
						// To save png
						g2.setColor(new Color(irr[iii],igg[iii],ibb[iii]));
						g2.fillRect(i*2 , j*2 , 2, 2);
					}
				}
			}
			if (nmap == 2) {// Gray scale
				for (int ii = 0; ii < maplength; ii += 1){
					gBuffer.setColor(new Color(irrr[ii],iggg[ii],ibbb[ii]));
					gBuffer.fillRect(dsx+2*ss+10, dsy+2*ss-16-ii, 10, 1);
				}
				for (int i = 0; i < sizex; i++) {
					for (int j = 0; j < sizey; j++) {
						int iii = 0;
						if (mask[i+6][j+6] == 1) {
							iii=(int)(255*umovie[i][j][time]/norma);
						}	
						if (iii > 255) iii=255;
						if (iii < 1) iii=0;
						gBuffer.setColor(new Color(irrr[iii], iggg[iii], ibbb[iii]));
						gBuffer.fillRect(dsx+i*2 , dsy+j*2 , 2, 2);
						// To save png
						g2.setColor(new Color(irrr[iii],iggg[iii],ibbb[iii]));
						g2.fillRect(i*2 , j*2 , 2, 2);
					}
				}
			}
			gBuffer.setColor(Color.black);
			gBuffer.drawString(" Vmax", dsx+2*ss, dsy+2*ss-maplength-19);
			gBuffer.drawString(" Vmin", dsx+2*ss, dsy+2*ss);
			
			// Save png files on the movie directory
			if(savemovieframes==1){
				if(time < 10){
					String filenamepng = String.valueOf("movie/000"+time+".png");
					filepng = new File(filenamepng);
				}
				if(time >=10 && time < 100){
					String filenamepng = String.valueOf("movie/00"+time+".png");
					filepng = new File(filenamepng);
				}
				if(time >= 100 && time < 1000){
					String filenamepng = String.valueOf("movie/0"+time+".png");
					filepng = new File(filenamepng);
				}
				if(time >= 1000) {
					String filenamepng = String.valueOf("movie/"+time+".png");
					filepng = new File(filenamepng);
				}
				try {
					ImageIO.write(bi, "png", filepng);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			// Plots point on the screen from where time signal is displayed
			gBuffer.setColor(Color.white);
			if(nmap ==2) gBuffer.setColor(Color.red);
			gBuffer.fillRect(dsx+nii*2-2,dsy+njj*2-2, 4,4);	
			// ***************
			firsttime = false;
			// ***************
			// Movie image
			index = 0;
			// Create image
			gBuffer.setColor(Color.black);
			gBuffer.setFont(new Font("Serif", Font.BOLD, 15));
			gBuffer.drawString("Time = ", dsx+ss-50, dsy-4);
			gBuffer.drawString(String.valueOf(time*2)+" ms", dsx+ss+2, dsy-4);
			gBuffer.setFont(new Font("Serif", Font.PLAIN, 16));
			// Update time 
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
				gBuffer.drawRect(nzoomx1-dpx+20, nzoomy1, nzoomx2-nzoomx1, nzoomy2-nzoomy1);
			}
			if (nzoomx1>=dpx+2*dax && nzoomx1<=dpx+pw && nzoomy1>=dpy && nzoomy1<=dpy+ph && nzoomx2>=dpx+2*dax && nzoomx2<=dpx+pw && nzoomy2>=dpy && nzoomy2<=dpy+ph && nzoomx2>nzoomx1 && nzoomy1>nzoomy2){
				gBuffer.drawRect(nzoomx1-dpx+20, nzoomy2, nzoomx2-nzoomx1, nzoomy1-nzoomy2);
			}
			if (nzoomx1>=dpx+2*dax && nzoomx1<=dpx+pw && nzoomy1>=dpy && nzoomy1<=dpy+ph && nzoomx2>=dpx+2*dax && nzoomx2<=dpx+pw && nzoomy2>=dpy && nzoomy2<=dpy+ph && nzoomx2<nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx2-dpx+20, nzoomy2, nzoomx1-nzoomx2, nzoomy1-nzoomy2);
			}
			if (nzoomx1>=dpx+2*dax && nzoomx1<=dpx+pw && nzoomy1>=dpy && nzoomy1<=dpy+ph && nzoomx2>=dpx+2*dax && nzoomx2<=dpx+pw && nzoomy2>=dpy && nzoomy2<=dpy+ph && nzoomx2<nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx2-dpx+20, nzoomy1, nzoomx2-nzoomx1, nzoomy2-nzoomy1);
			}
			// Zoom on the APD - DI distribution plot
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2>nzoomx1 && nzoomy2>nzoomy1){
				gBuffer.drawRect(nzoomx1-dsx+20, nzoomy1, nzoomx2-nzoomx1, nzoomy2-nzoomy1);
			}
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2<nzoomx1 && nzoomy2>nzoomy1){
				gBuffer.drawRect(nzoomx2-dsx+20, nzoomy1, nzoomx1-nzoomx2, nzoomy2-nzoomy1);
			}
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2<nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx2-dsx+20, nzoomy2, nzoomx1-nzoomx2, nzoomy1-nzoomy2);
			}
			if (nzoomx1>=2*dsx+pw-2*ss+10 && nzoomx1<=2*dsx+pw-10 && nzoomy1>=dsy+10 && nzoomy1<=dsy+2*ss-10 && nzoomx2>=2*dsx+pw-2*ss+10 && nzoomx2<=2*dsx+pw-10 && nzoomy2>=dsy+10 && nzoomy2<=dsy+2*ss-10 && nzoomx2>nzoomx1 && nzoomy2<nzoomy1){
				gBuffer.drawRect(nzoomx1-dsx+20, nzoomy2, nzoomx2-nzoomx1, nzoomy1-nzoomy2);
			}
		}
		g.drawImage(Buffer,dax,0,this);
	}//close paint
	//**************************************************************************
	//**************************************************************************
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
	} //close run
	//**************************************************************************
	//**************************************************************************
	public void  actionPerformed( ActionEvent ae ){
	//**************************************************************************
		
		// Load B:-> Loading file ----------------------------------------------
		if (ae.getSource() == openButton) {
			if (start.getLabel().equals("Start New")) {
				note.append("Loading file:\n");
				f.setDirectory("2010_feb16_temp_endo_010.dat");
				f.setVisible(true);
				String s = f.getDirectory();
				s += f.getFile();
				namefilel = s;
				// Take file name
				int filNamLen = namefilel.length();
				int indexToTake = 5;
				int found = 0;
				String findSl = "/";
				for (int i = 0; i < filNamLen-1; i++) {
					String strTock = namefilel.substring(filNamLen-(i+1), filNamLen-i);
					if (strTock.equals(findSl) && found == 0) {
						indexToTake = i;
						found = 1;
					}
				}
				analyzedfile = namefilel.substring(filNamLen-indexToTake, filNamLen-4);
				// Set string for the analyzed data
				displayFilename.setText(analyzedfile);
				// Set string for the postprocessing data
				filename.setText("analyzed/"+"AN_"+analyzedfile);
				note.setForeground(Color.red);
				note.append( namefilel + "\n");
				openButton.setBackground(Color.gray);
				start.setLabel("Start New");
				start.setBackground(Color.red); 
			}
		}
		else if (ae.getSource() == errorButton) IOError.dispose();
		
		// Loadmask B:-> load a previous mask ----------------------------------
		if (ae.getSource() == loadmask) {
			maskb.setLabel("Mask off"); 
			note.append("\nLoading mask.");
			f.setDirectory("2010_feb16_temp_endo_010.dat");
			f.setVisible(true);
			String s = f.getDirectory();
			s += f.getFile();
			maskname = s;
			
			try {
				InputStream in = new FileInputStream(maskname);
				bimask = ImageIO.read(in);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(0);
			}
			
			height = bimask.getHeight();
			width = bimask.getWidth();
			//System.out.println("height"+height+"  width "+width);
			int i = 0;
			int j = 0;
			for (int x = 0; x < width; x=x+2) {
				for (int y = 0; y < height; y=y+2){
					i = x/2;
					j = y/2;
					mask[i+6][j+6]=(short)(bimask.getRGB(x,y));
					if (mask[i+6][j+6] == 0) {
						mask[i+6][j+6] = 1;
					}
					else {
						mask[i+6][j+6] = 0;
					}
				}
			}
			note.append("\nMask loaded");
		}
		else if (ae.getSource() == errorButton) IOError.dispose();
		
		// Start New B:-> read-> convert-> unwrap-> t average-> normalization --
		if (ae.getSource() == start && start.getLabel().equals("Start New")) {
			
			beginAnalyze = true;
			start.setLabel("Reset");
			start.setBackground(Color.gray);
			note.setForeground(Color.black);
			// Call method to READ video ---------------------------------------
			if (selectedSigLoad == 0) { // Reading a new data matrix
				
				note.append("\nReading New Data:\n");
				video = readArray(namefilel);
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append("\nNumber of frames: " + nframes);
				note.append("\nXdim: " + sizex + " - Ydim: " + sizey);
				// Initialize zeros matrices matrices --------------------------
				ntimes = nframes;
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						unoP2D[i][j] = 1;
						unoM2D[i][j] = -1;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa = zero1D;
				maMaApdDi = zero1D;
				/*
				 min=zero2D;max=zero2D;ormin=zero2D;ormax=zero2D;
				 ntll=zero2D;intervsh1f=zero2D;intervsh2p=zero2D;
				 minDIn=zero2D;maxDIn=zero2D;minAPDn=zero2D;maxAPDn=zero2D;
				 minDIAe=zero2D;maxDIAe=zero2D;minAPDAe=zero2D;maxAPDAe=zero2D;
				 minDIAo=zero2D;maxDIAo=zero2D;minAPDAo=zero2D;maxAPDAo=zero2D;
				 */
				min = copyMatrix2D(unoM2D,sizex,sizey);
				max = copyMatrix2D(unoP2D,sizex,sizey);
				ormin = copyMatrix2D(unoM2D,sizex,sizey);
				ormax = copyMatrix2D(unoP2D,sizex,sizey);
				
				ntll = copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f = copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p = copyMatrix2D(zero2D,sizex,sizey);
				minDIn = copyMatrix2D(zero2D,sizex,sizey);
				maxDIn = copyMatrix2D(zero2D,sizex,sizey);
				minAPDn = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn = copyMatrix2D(zero2D,sizex,sizey);
				minDIAe = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				minDIAo = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				/*
				 nb=zeroI2D;ntr=zeroI2D;meandi=zeroI2D;meanapd=zeroI2D;
				 mdiAo=zeroI2D;mapdAo=zeroI2D;mdiAe=zeroI2D;mapdAe=zeroI2D;
				 */
				nb = copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr = copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi = copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				/*
				 original=zero3D;unwrapped=zero3D;taveraged=zero3D;basevL=zero3D;
				 shifted=zero3D;erased=zero3D;spaceaveraged=zero3D;apd=zero3D;
				 di=zero3D;tpb=zero3D;
				 */
				original = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				nii = sizex/2;
				njj = sizey/2;
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[ntimes/dt][nii][njj]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[ntimes/dt][nii][njj]+"\n");
				// Do MASK -----------------------------------------------------
				if (maskcontrol == 1) {}
				else {
					short[][] formask = new short[sizex+12][sizey+12];
					for (int i = 0; i < (sizex + 12); i++){
						for (int j = 0; j < (sizey + 12); j++){
							if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
								formask[i][j] = 0;
							}
							else {
								formask[i][j] = 1;
							}
						}
					}
					mask = formask;
				}
				// Call method to CONVERT data ---------------------------------
				note.append( "\nConvert and copy data....");
				u = convertArray(video,sizex,sizey,ntimes);
				original = copyMatrix3D(u,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(u,sizex,sizey,ntimes);
				note.append("\nu"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/dt]); 
				short[][] maxtest = new short[sizex][sizey];
				short[][] mintest = new short[sizex][sizey];
				minMax(u, mask, sizex, sizey, ntimes, maxtest, mintest);
				ormax = copyMatrix2D(maxtest,sizex,sizey);
				ormin = copyMatrix2D(mintest,sizex,sizey);
				max = copyMatrix2D(maxtest,sizex,sizey);
				min = copyMatrix2D(mintest,sizex,sizey);
				note.append("\norMax in "+"["+nii+"]"+"["+njj+"]: "+ormax[nii][njj]); 
				note.append("\norMin in "+"["+nii+"]"+"["+njj+"]: "+ormin[nii][njj]+"\n");
				// Call method to evaluate absolute Max and min ----------------
				note.append("\nAbsolute Max and min...");
				minAMaxA(sizex, sizey, ntimes, max, min, mask, maMa);
				note.append("\nMaxA: "+maMa[1]); 
				note.append("\nMinA: "+maMa[0]+"\n");
				// Call method to UNWRAP the signal ----------------------------
				note.append( "\nUnwrapping....");
				unwrapSignal(u, mask, sizex, sizey, ntimes, nii, njj, unwrapped, max, min);
				note.append("\nu"+"["+ntimes/2+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/2]); 
				//note.append("\nu"+"["+ntimes/2+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/2]);
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
				// Call method to perform the TIME AVERAGE ---------------------
				note.append( "\nTime averaging.... ");
				taveraged = timeAverage(u,mask,sizex,sizey,ntimes);
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
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
				// Structure and starting plots --------------------------------
				signal1.setState(true);
				originc = true;
				frame11 = true;
				umovie = original;
				setshift.setBackground(Color.green);
				validate();	
			} // End Reading a new data matrix
			if (selectedSigLoad == 1) { // Reading an Original data matrix
				
				note.append( "\nReading Original data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2;
				njj = sizey/2;
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa = zero1D;
				maMaApdDi = zero1D;
				/*
				 min=zero2D;max=zero2D;ormin=zero2D;ormax=zero2D;
				 ntll=zero2D;intervsh1f=zero2D;intervsh2p=zero2D;
				 minDIn=zero2D;maxDIn=zero2D;minAPDn=zero2D;maxAPDn=zero2D;
				 minDIAe=zero2D;maxDIAe=zero2D;minAPDAe=zero2D;maxAPDAe=zero2D;
				 minDIAo=zero2D;maxDIAo=zero2D;minAPDAo=zero2D;maxAPDAo=zero2D;
				 */
				min = copyMatrix2D(unoM2D,sizex,sizey);
				max = copyMatrix2D(unoP2D,sizex,sizey);
				ormin = copyMatrix2D(unoM2D,sizex,sizey);
				ormax = copyMatrix2D(unoP2D,sizex,sizey);
				
				ntll = copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f = copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p = copyMatrix2D(zero2D,sizex,sizey);
				minDIn = copyMatrix2D(zero2D,sizex,sizey);
				maxDIn = copyMatrix2D(zero2D,sizex,sizey);
				minAPDn = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn = copyMatrix2D(zero2D,sizex,sizey);
				minDIAe = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				minDIAo = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				/*
				 nb=zeroI2D;ntr=zeroI2D;meandi=zeroI2D;meanapd=zeroI2D;
				 mdiAo=zeroI2D;mapdAo=zeroI2D;mdiAe=zeroI2D;mapdAe=zeroI2D;
				 */
				nb = copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr = copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi = copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				/*
				 original=zero3D;unwrapped=zero3D;taveraged=zero3D;basevL=zero3D;
				 shifted=zero3D;erased=zero3D;spaceaveraged=zero3D;apd=zero3D;
				 di=zero3D;tpb=zero3D;
				 */
				original = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				original = copyMatrix3D(u,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(u,sizex,sizey,ntimes);
				note.append("\nu"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/dt]); 
				short[][] maxtest = new short[sizex][sizey];
				short[][] mintest = new short[sizex][sizey];
				minMax(u, mask, sizex, sizey, ntimes, maxtest, mintest);
				ormax = copyMatrix2D(maxtest,sizex,sizey);
				ormin = copyMatrix2D(mintest,sizex,sizey);
				max = copyMatrix2D(maxtest,sizex,sizey);
				min = copyMatrix2D(mintest,sizex,sizey);
				note.append("\norMax in "+"["+nii+"]"+"["+njj+"]: "+ormax[nii][njj]); 
				note.append("\norMin in "+"["+nii+"]"+"["+njj+"]: "+ormin[nii][njj]+"\n");
				// Call method to evaluate absolute Max and min ----------------
				note.append("\nAbsolute Max and min...");
				minAMaxA(sizex, sizey, ntimes, max, min, mask, maMa);
				note.append("\nMaxA: "+maMa[1]); 
				note.append("\nMinA: "+maMa[0]+"\n");
				// Call method to UNWRAP the signal ----------------------------
				note.append( "\nUnwrapping....");
				unwrapSignal(u, mask, sizex, sizey, ntimes, nii, njj, unwrapped, max, min);
				note.append("\nu"+"["+ntimes/2+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/2]); 
				//note.append("\nu"+"["+ntimes/2+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/2]);
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
				// Call method to perform the TIME AVERAGE ---------------------
				note.append( "\nTime averaging.... ");
				taveraged = timeAverage(u,mask,sizex,sizey,ntimes);
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
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
				// Structure and starting plots --------------------------------
				signal1.setState(true);
				originc = true;
				frame11 = true;
				umovie = original;
				setshift.setBackground(Color.green);
				validate();	
			} // End Reading an Original data matrix
			if (selectedSigLoad == 2) { // Reading an Unwrapped data matrix
				
				note.append( "\nReading Unwrapped data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2; njj = sizey/2;
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa = zero1D;
				maMaApdDi = zero1D;
				/*
				 min=zero2D;max=zero2D;ormin=zero2D;ormax=zero2D;
				 ntll=zero2D;intervsh1f=zero2D;intervsh2p=zero2D;
				 minDIn=zero2D;maxDIn=zero2D;minAPDn=zero2D;maxAPDn=zero2D;
				 minDIAe=zero2D;maxDIAe=zero2D;minAPDAe=zero2D;maxAPDAe=zero2D;
				 minDIAo=zero2D;maxDIAo=zero2D;minAPDAo=zero2D;maxAPDAo=zero2D;
				 */
				min = copyMatrix2D(unoM2D,sizex,sizey);
				max = copyMatrix2D(unoP2D,sizex,sizey);
				ormin = copyMatrix2D(unoM2D,sizex,sizey);
				ormax = copyMatrix2D(unoP2D,sizex,sizey);
				
				ntll = copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f = copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p = copyMatrix2D(zero2D,sizex,sizey);
				minDIn = copyMatrix2D(zero2D,sizex,sizey);
				maxDIn = copyMatrix2D(zero2D,sizex,sizey);
				minAPDn = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn = copyMatrix2D(zero2D,sizex,sizey);
				minDIAe = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				minDIAo = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				/*
				 nb=zeroI2D;ntr=zeroI2D;meandi=zeroI2D;meanapd=zeroI2D;
				 mdiAo=zeroI2D;mapdAo=zeroI2D;mdiAe=zeroI2D;mapdAe=zeroI2D;
				 */
				nb = copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr = copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi = copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				/*
				 original=zero3D;unwrapped=zero3D;taveraged=zero3D;basevL=zero3D;
				 shifted=zero3D;erased=zero3D;spaceaveraged=zero3D;apd=zero3D;
				 di=zero3D;tpb=zero3D;
				 */
				original = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				original = copyMatrix3D(u,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(u,sizex,sizey,ntimes);
				note.append("\nu"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/dt]); 				
				short[][] maxtest = new short[sizex][sizey];
				short[][] mintest = new short[sizex][sizey];
				minMax(u, mask, sizex, sizey, ntimes, maxtest, mintest);				
				max = copyMatrix2D(maxtest,sizex,sizey);
				min = copyMatrix2D(mintest,sizex,sizey);
				unwmax = max[nii][njj];
				unwmin = min[nii][njj];				
				// Call method to perform the TIME AVERAGE ---------------------
				note.append( "\nTime averaging.... ");
				taveraged = timeAverage(u,mask,sizex,sizey,ntimes);
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
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
				// Structure and starting plots --------------------------------
				signal2.setState(true);
				unwrapc = true;
				frame11 = true;
				umovie = unwrapped;
				setshift.setBackground(Color.green);
				validate();	
			} // End Reading an Unwrapped data matrix
			if (selectedSigLoad == 3) { // Reading a time averaged matrix
				
				note.append( "\nReading Time Averaged data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2;
				njj = sizey/2;
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa = zero1D;
				maMaApdDi = zero1D;
				min = copyMatrix2D(unoM2D,sizex,sizey);
				max = copyMatrix2D(unoP2D,sizex,sizey);
				ormin = copyMatrix2D(unoM2D,sizex,sizey);
				ormax = copyMatrix2D(unoP2D,sizex,sizey);
				
				ntll = copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f = copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p = copyMatrix2D(zero2D,sizex,sizey);
				minDIn = copyMatrix2D(zero2D,sizex,sizey);
				maxDIn = copyMatrix2D(zero2D,sizex,sizey);
				minAPDn = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn = copyMatrix2D(zero2D,sizex,sizey);
				minDIAe = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				minDIAo = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				nb = copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr = copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi = copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				original = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				taveraged = copyMatrix3D(u,sizex,sizey,ntimes);
				note.append("\nu"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+u[nii][njj][ntimes/dt]); 
				short[][] maxtest = new short[sizex][sizey];
				short[][] mintest = new short[sizex][sizey];
				minMax(u, mask, sizex, sizey, ntimes, max, min);
				tavnmax = max[nii][njj];
				tavnmin = min[nii][njj];
				note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+tavnmax); 
				note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+tavnmin);
				note.append("\nEnd.  ");
				// Structure and starting plots --------------------------------
				signal3.setState(true);
				tavec = true;
				frame11 = true;
				umovie = taveraged;
				setshift.setBackground(Color.green);
				validate();	
			} // End Reading a time averaged matrix
			if (selectedSigLoad == 4) { // Reading a Shifting line matrix
				
				note.append( "\nReading Shifting Line data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2;
				njj = sizey/2;
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				// Read intervals data
				intervsh1f = readDataInfo1(namefilel);
				intervsh2p = readDataInfo2(namefilel);
				ntll = readDataInfo3(namefilel);
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa = zero1D;
				maMaApdDi = zero1D;
				min = copyMatrix2D(unoM2D,sizex,sizey);
				max = copyMatrix2D(unoP2D,sizex,sizey);
				ormin = copyMatrix2D(unoM2D,sizex,sizey);
				ormax = copyMatrix2D(unoP2D,sizex,sizey);
				
				ntll = copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f = copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p = copyMatrix2D(zero2D,sizex,sizey);
				minDIn = copyMatrix2D(zero2D,sizex,sizey);
				maxDIn = copyMatrix2D(zero2D,sizex,sizey);
				minAPDn = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn = copyMatrix2D(zero2D,sizex,sizey);
				minDIAe = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				minDIAo = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				nb = copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr = copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi = copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				original = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				basevL = copyMatrix3D(u,sizex,sizey,ntimes);
				minMax(u, mask, sizex, sizey, ntimes, max, min);
				tavnmax = max[nii][njj];
				tavnmin = min[nii][njj];
				note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+tavnmax); 
				note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+tavnmin);
				note.append("\nEnd.  ");
				// Structure and starting plots --------------------------------
				signal4.setState(true);
				shiftl = true;
				frame11 = true;
				umovie = basevL;
				validate();	
			} // End Reading a Shifting line matrix
			if (selectedSigLoad == 5) { // Reading a Shifted matrix
				
				note.append( "\nReading Shifted data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2;
				njj = sizey/2;
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				// Read intervals data
				intervsh1f = readDataInfo1(namefilel);
				intervsh2p = readDataInfo2(namefilel);
				ntll = readDataInfo3(namefilel);
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa = zero1D;
				maMaApdDi = zero1D;
				min = copyMatrix2D(unoM2D,sizex,sizey);
				max = copyMatrix2D(unoP2D,sizex,sizey);
				ormin = copyMatrix2D(unoM2D,sizex,sizey);
				ormax = copyMatrix2D(unoP2D,sizex,sizey);
				
				//ntll = copyMatrix2D(zero2D,sizex,sizey);
				//intervsh1f = copyMatrix2D(zero2D,sizex,sizey);
				//intervsh2p = copyMatrix2D(zero2D,sizex,sizey);
				minDIn = copyMatrix2D(zero2D,sizex,sizey);
				maxDIn = copyMatrix2D(zero2D,sizex,sizey);
				minAPDn = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn = copyMatrix2D(zero2D,sizex,sizey);
				minDIAe = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe = copyMatrix2D(zero2D,sizex,sizey);
				minDIAo = copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo = copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo = copyMatrix2D(zero2D,sizex,sizey);
				nb = copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr = copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi = copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo = copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe = copyMatrixI2D(zeroI2D,sizex,sizey);
				original = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				shifted = copyMatrix3D(u,sizex,sizey,ntimes);
				minMax(u, mask, sizex, sizey, ntimes, max, min);
				shifmax = max[nii][njj];
				shifmin = min[nii][njj];
				note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+shifmax); 
				note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+shifmin);
				note.append("\nEnd.  ");
				// Structure and starting plots --------------------------------
				signal5.setState(true);
				shiftc = true;
				frame11 = true;
				umovie = shifted;
				validate();	
			} // End Reading a Shifted matrix
			if (selectedSigLoad == 6) { // Reading an Erased matrix
				
				note.append( "\nReading Erased data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2;
				njj = sizey/2;
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				// Read intervals data -----------------------------------------
				intervsh1f = readDataInfo1(namefilel);
				intervsh2p = readDataInfo2(namefilel);
				ntll = readDataInfo3(namefilel);
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa 		= zero1D;
				maMaApdDi 	= zero1D;
				min 		= copyMatrix2D(unoM2D,sizex,sizey);
				max 		= copyMatrix2D(unoP2D,sizex,sizey);
				ormin 		= copyMatrix2D(unoM2D,sizex,sizey);
				ormax 		= copyMatrix2D(unoP2D,sizex,sizey);
				ntll 		= copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f 	= copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p 	= copyMatrix2D(zero2D,sizex,sizey);
				minDIn 		= copyMatrix2D(zero2D,sizex,sizey);
				maxDIn 		= copyMatrix2D(zero2D,sizex,sizey);
				minAPDn 	= copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn 	= copyMatrix2D(zero2D,sizex,sizey);
				minDIAe 	= copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe 	= copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe 	= copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe 	= copyMatrix2D(zero2D,sizex,sizey);
				minDIAo 	= copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo 	= copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo 	= copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo 	= copyMatrix2D(zero2D,sizex,sizey);
				nb 			= copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd 	= copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				//apdSpaceTBD = copyMatrixI2D(zeroI2D,sizex,sizey);
				//diSpaceTBD 	= copyMatrixI2D(zeroI2D,sizex,sizey);
				original	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di 			= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				erased = copyMatrix3D(u,sizex,sizey,ntimes);
				minMax(u, mask, sizex, sizey, ntimes, max, min);
				ermax = max[nii][njj];
				ermin = min[nii][njj];
				note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+ermax); 
				note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+ermin);
				note.append("\nEnd.  ");
				// Structure and starting plots --------------------------------
				signal6.setState(true);
				erasec = true;
				frame11 = true;
				umovie = erased;
				validate();	
			} // End Reading an Erased matrix
			if (selectedSigLoad == 7) { // Reading a Space Averaged matrix
				
				note.append( "\nReading Sp. Averaged data\n");
				video = readData(namefilel);
				u = video;
				sizex = (int)(video.length);
				sizey = (int)(video[0].length);
				nframes = (int)(video[0][0].length);
				ntimes = nframes;
				nii = sizex/2;
				njj = sizey/2;
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]); 
				note.append("\nVideo"+"["+ntimes+"]"+"["+nii+"]"+"["+njj+"]: "+video[nii][njj][ntimes/dt]+"\n");
				tminpplot.setText(Integer.toString(10));
				tmaxpplot.setText(Integer.toString(dt*nframes-10));
				note.append( "\nNumber of frames: " + nframes);
				note.append( "\nXdim: " + sizex + " - Ydim: " + sizey);
				// Read intervals data -----------------------------------------
				intervsh1f = readDataInfo1(namefilel);
				intervsh2p = readDataInfo2(namefilel);
				ntll = readDataInfo3(namefilel);
				// Initialize zeros matrices -----------------------------------
				mintimeplot = 10;
				maxtimeplot = 2*ntimes-10;
				int[] zero1D = new int[sizex];
				int[][] zeroI2D = new int[sizex][sizey];
				short[][] zero2D = new short[sizex][sizey];
				short[][] unoP2D = new short[sizex][sizey];
				short[][] unoM2D = new short[sizex][sizey];
				short[][][] zero3D = new short[sizex][sizey][ntimes];
				for (int i = 0; i < sizex; i++){
					zero1D[i] = 0;
					for (int j = 0; j < sizey; j++){
						zero2D[i][j] = 0;
						zeroI2D[i][j] = 0;
						for (int k = 0; k < ntimes; k++){
							zero3D[i][j][k] = 0;
						}
					}
				}
				maMa 		= zero1D;
				maMaApdDi 	= zero1D;
				min 		= copyMatrix2D(unoM2D,sizex,sizey);
				max 		= copyMatrix2D(unoP2D,sizex,sizey);
				ormin 		= copyMatrix2D(unoM2D,sizex,sizey);
				ormax 		= copyMatrix2D(unoP2D,sizex,sizey);
				ntll 		= copyMatrix2D(zero2D,sizex,sizey);
				intervsh1f 	= copyMatrix2D(zero2D,sizex,sizey);
				intervsh2p 	= copyMatrix2D(zero2D,sizex,sizey);
				minDIn 		= copyMatrix2D(zero2D,sizex,sizey);
				maxDIn 		= copyMatrix2D(zero2D,sizex,sizey);
				minAPDn 	= copyMatrix2D(zero2D,sizex,sizey);
				maxAPDn 	= copyMatrix2D(zero2D,sizex,sizey);
				minDIAe 	= copyMatrix2D(zero2D,sizex,sizey);
				maxDIAe 	= copyMatrix2D(zero2D,sizex,sizey);
				minAPDAe 	= copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAe 	= copyMatrix2D(zero2D,sizex,sizey);
				minDIAo 	= copyMatrix2D(zero2D,sizex,sizey);
				maxDIAo 	= copyMatrix2D(zero2D,sizex,sizey);
				minAPDAo 	= copyMatrix2D(zero2D,sizex,sizey);
				maxAPDAo 	= copyMatrix2D(zero2D,sizex,sizey);
				nb 			= copyMatrixI2D(zeroI2D,sizex,sizey);
				ntr 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				meandi 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				meanapd 	= copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAo 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAo 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				mdiAe 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				mapdAe 		= copyMatrixI2D(zeroI2D,sizex,sizey);
				//apdSpaceTBD = copyMatrixI2D(zeroI2D,sizex,sizey);
				//diSpaceTBD 	= copyMatrixI2D(zeroI2D,sizex,sizey);
				original 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				unwrapped 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				taveraged 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				basevL 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				shifted 	= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				erased 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				spaceaveraged = copyMatrix3D(zero3D,sizex,sizey,ntimes);
				apd 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				di 			= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				tpb 		= copyMatrix3D(zero3D,sizex,sizey,ntimes);
				
				// Do MASK -----------------------------------------------------
				short[][] formask = new short[sizex+12][sizey+12];
				for (int i = 0; i < (sizex + 12); i++){
					for (int j = 0; j < (sizey + 12); j++){
						if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
							formask[i][j] = 0;
						}
						else {
							formask[i][j] = 1;
						}
					}
				}
				mask = formask;
				
				// Start analyzing ---------------------------------------------
				spaceaveraged = copyMatrix3D(u,sizex,sizey,ntimes);
				minMax(u, mask, sizex, sizey, ntimes, max, min);
				spamax = max[nii][njj];
				spamin = min[nii][njj];
				note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+spamax); 
				note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+spamin);
				note.append("\nEnd.  ");
				// Structure and starting plots --------------------------------
				signal7.setState(true);
				spaveragec = true;
				frame11 = true;
				umovie = spaceaveraged;
				validate();	
			} // End Reading a Space Averaged matrix
		}
		else {
			if (ae.getSource() == start && start.getLabel().equals("Reset")) {
				// Reset matrices
				u=null;//mask=null;
				video=null;
				umovie=null;original=null;chosenSignal=null;
				originalimg=null;unwrapped=null;taveraged=null;
				shifted=null;basevL=null;spaceaveraged=null;erased=null;
				max=null;min=null;maxINT=null;minINT=null;maMa=null;
				maMaApdDi=null;maxAInt=null;minAInt=null;ormax=null;ormin=null;
				ntll=null;intervsh1f=null;intervsh2p=null;apd=null;
			    di=null;tpb=null;meandi=null;meanapd=null;
				mdiAo=null;mapdAo=null;mdiAe=null;mapdAe=null;
				minDIn=null;maxDIn=null;minAPDn=null;maxAPDn=null;
				minDIAe=null;maxDIAe=null;minAPDAe=null;maxAPDAe=null;
				minDIAo=null;maxDIAo=null;minAPDAo=null;maxAPDAo=null;
				nb=null;ntr=null;//apdSpaceTBD=null;diSpaceTBD=null;
				// Reset all the checkbox to default
				originc=false; unwrapc=false;	
				tavec=false; shiftl=false;	
				shiftc=false; erasec=false;	
				spaveragec=false; thresholdc=false;	
				apddishow=false; startmovie=false;	
				apdDistc=false; alternansc=false;	
				apdDiMeanc=false; apdSpacec=false;	
				diSpacec=false; 
				apdTBDc=false; diTBDc=false;
				frame11=false;frame1p=false;
				// Mask control
				maskcontrol = 1;
				// Reset to plot
				mintimeplot = 10;
				maxtimeplot = dt*ntimes - 10;
				pmintimeplot = mintimeplot;
				tminpplot.setText(Integer.toString(mintimeplot));
				tmaxpplot.setText(Integer.toString(maxtimeplot));
				// 
				analyzedfile=" "; anfiletosave=" "; stringName=" "; 
				createdmask="mask"; maskname=" ";
				savemaskpng=0;savedipng=0;saveapdpng=0;saveapddimeanvalue=0;
				namefilel=" "; sigsig=" "; oldText=" ";
				// Dimension Parameters
				nf=128; ntimes=3000; nii=60; njj=60; ur=2500; norma=10000;				
				// To normalize the plot
				origmin = -10; origmax = 10; unwmin	= -10;  unwmax = 10;
				nor1min = -10; nor1max = 10; tavnmin = -10; tavnmax = 10; 
				shifmin = -10; shifmax = 10; ermin = -10; ermax = 10;
				spamin  = -10; spamax = 10;
				// Reset To normalize APD - DI distribution
				mindi1=0; maxdi1=100; minapd1=0; maxapd1=100; 
				mindi2=0; maxdi2=200; minapd2=0; maxapd2=200;
				mindi3=0; maxdi3=300; minapd3=0; maxapd3=300;
				mindi4=0; maxdi4=400; minapd4=0; maxapd4=400;
				mindi5=0; maxdi5=500; minapd5=0; maxapd5=500;
				mindi6=0; maxdi6=600; minapd6=0; maxapd6=600;
				mindi7=0; maxdi7=800; minapd7=0; maxapd7=800;
				mindi=mindi2; maxdi=maxdi2; minapd=minapd2; maxapd=maxapd2;
				// Other
				n=-1; nn=0; nmap=0; maplength=196; norig=0; errorb=3;
				uflag=1; napddi=1;
				// Reset Text Area
				displayFilename.setText(" ");
				filename.setText(" ");
				note.setText(" ");
				// Reset Checkboxes
				signal1.setState(false);signal2.setState(false);
				signal3.setState(false);signal4.setState(false);
				signal5.setState(false);signal6.setState(false);
				signal7.setState(false);signal8.setState(false);
				// Clear garbage
				System.gc();
				beginAnalyze = false;
				start.setLabel("Start New");
				repaint();
			}
		} // End read - convert - ...
		
		// Mask B:-> modify mask
		if(ae.getSource() == maskb ){
			if(imask == -1){
				maskb.setLabel("Set Mask");
			}
			if(imask == 1){
				maskb.setLabel("Mask off"); 
			}
			imask=imask*imaskf;
		} // End modify mask
		
		// Resetmask B:-> reset mask
		if(ae.getSource() == resetmask ){
			maskb.setLabel("Mask off");
			short[][] formask = new short[sizex+12][sizey+12];
			for (int i = 0; i < (sizex + 12); i++){
				for (int j = 0; j < (sizey + 12); j++){
					if (i < 6 || i >= (sizex + 6) || j < 6 || j >= (sizey + 6)) {
						formask[i][j] = 0;
					}
					else {
						formask[i][j] = 1;
					}
				}
			}
			mask = formask;
		} // End resetmask
		
		// Set B:-> select signal to analyze
		if (ae.getSource() == chooseSignal) {
			chosen = 1;
		}
		
		// Set B:-> shift ------------------------------------------------------
		if (ae.getSource() == setshift) {
			
			setshift.setBackground(Color.gray);	
			// Take shifting period
			double ffreq = Double.valueOf(tfreq.getText()).doubleValue();
			freq = (int)(ffreq);
			ifreq = freq/3;
			
			// Choose signal and perform shifting 
			if (selectedSigAnalyze == 0 && chosen == 0) {
				note.append( "\nShifting Time Averaged\n");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 1 && chosen == 1) {
				note.append( "\nShifting Original\n");
				u = copyMatrix3D(original,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 2 && chosen == 1) {
				note.append( "\nShifting Unwrapped\n");
				u = copyMatrix3D(unwrapped,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 3 && chosen == 1) {
				note.append( "\nShifting Time Averaged\n");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 4 && chosen == 1) {
				note.append( "\nShifting Time Averaged\n");
				u = copyMatrix3D(shifted,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 5 && chosen == 1) {
				note.append( "\nShifting Shifted\n");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 6 && chosen == 1) {
				note.append( "\nShifting Erased\n");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 7 && chosen == 1) {
				note.append( "\nShifting Space Averaged\n");
				u = copyMatrix3D(spaceaveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			short[][][] tempbasevL = new short[sizex][sizey][nframes];
			short[][][] tempshifted = new short[sizex][sizey][nframes];
			short[][] intervsh1first = new short[sizex][sizey]; 
			short[][] intervsh2final = new short[sizex][sizey];
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			shiftSignal(u,mask,sizex,sizey,ntimes,ifreq,max,min,ntll,tempbasevL,tempshifted,intervsh1first,intervsh2final);
			
			shifted = copyMatrix3D(tempshifted,sizex,sizey,ntimes); 
			tempshifted = null;
			basevL = copyMatrix3D(tempbasevL,sizex,sizey,ntimes); 
			tempbasevL = null;;
			intervsh1f = copyMatrix2D(intervsh1first,sizex,sizey); 
			intervsh1first = null;
			intervsh2p = copyMatrix2D(intervsh2final,sizex,sizey); 
			intervsh2final = null;
			
			// Max min
			minMax(shifted, mask, sizex, sizey, ntimes, max, min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nNormalization....");
			shifted = normaSignalGN(shifted,mask,sizex,sizey,ntimes,norma,max,min);
			minMax(shifted,mask,sizex,sizey,ntimes,max,min);
			// Max min
			shifmax = max[nii][njj];
			shifmin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			
			u = copyMatrix3D(shifted,sizex,sizey,ntimes);  
			// Flag to plot the shifted signal ---------------------------------
			if (selectedSigAnalyze == 1) {
				signal1.setState(true);
				originc = true;
				signal4.setState(true);
				chosen = 1;
			}
			if (selectedSigAnalyze == 2) {
				signal1.setState(false);
				originc = false;
				signal2.setState(true);
				unwrapc = true;
				signal4.setState(true);
				chosen = 1;
			}
			if (selectedSigAnalyze == 3) {
				signal1.setState(false);
				originc = false;
				signal3.setState(true);
				tavec = true;
				signal4.setState(true);
				chosen = 1;
			}
			if (selectedSigAnalyze == 4) {
				signal1.setState(false);
				originc = false;
				signal3.setState(true);
				tavec = true;
				signal4.setState(true);
				chosen = 1;
			}
			if (selectedSigAnalyze == 5) {
				signal1.setState(false);
				originc = false;
				signal5.setState(true);
				erasec = true;
				signal4.setState(true);
				chosen = 1;
			}
			if (selectedSigAnalyze == 6) {
				signal1.setState(false);
				originc = false;
				signal6.setState(true);
				shiftc = true;
				signal4.setState(true);
				chosen = 1;
			}
			if (selectedSigAnalyze == 7) {
				signal1.setState(false);
				originc = false;
				signal7.setState(true);
				spaveragec = true;
				signal4.setState(true);
				chosen = 1;
			}
			
			shiftl = true;
			frame11 = true;
			
			chosen = 0;
			// optionsAnalyze.select(chosen);
			
			validate();
		} // End shift
		
		// Set B:-> erase ------------------------------------------------------
		if (ae.getSource() == seterase) {
			
			// Taking erasing intervals
			double nerasesx = Double.valueOf(erasesx.getText()).doubleValue();
			double nerasedx = Double.valueOf(erasedx.getText()).doubleValue();
			ertime1 = (int)(nerasesx);
			ertime2 = (int)(nerasedx);
			
			if (selectedSigAnalyze == 1 && chosen == 1) {
				note.append( "\nErasing Original");
				u = copyMatrix3D(original,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 2 && chosen == 1) {
				note.append( "\nErasing Unwrapped");
				u = copyMatrix3D(unwrapped,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 3 && chosen == 1) {
				note.append( "\nErasing Time Averaged");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 4 && chosen == 1) {
				note.append( "\nErasing Shifted");
				u = copyMatrix3D(shifted,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 5 && chosen == 1) {
				note.append( "\nErasing Shifted");
				u = copyMatrix3D(shifted,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 6 && chosen == 1) {
				note.append( "\nErasing Erased");
				u = copyMatrix3D(erased,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 7 && chosen == 1) {
				note.append( "\nErasing Space Averaged");
				u = copyMatrix3D(spaceaveraged,sizex,sizey,ntimes);
				chosen = 0;
			}

			eraseTime(u, mask, sizex, sizey, ntimes, ertime1, ertime2, erased);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");
			note.append( "\nNormalization....");
			//normaSignalGN(erased, mask, sizex, sizey, ntimes, norma, max, min);
			erased = normaSignalGN(erased, mask, sizex, sizey, ntimes, norma, max, min);
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
			signal7.setState(false);
			spaveragec = false;
			signal6.setState(true);
			erasec = true;
			frame11 = true;
			
			// !!! We can change which signal analize after this action
			chosen = 0;
			optionsAnalyze.select(chosen);
			
			validate();
			//--------------------------------
			u = copyMatrix3D(erased,sizex,sizey,ntimes);			
		} // End erase
	
		// Perform B:-> space average ------------------------------------------
		if (ae.getSource() == spaaverage) {
		
			
			if (selectedSigAnalyze == 1 && chosen == 1) {
				note.append( "\nSpace average Original");
				u = copyMatrix3D(original,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 2 && chosen == 1) {
				note.append( "\nSpace average Unwrapped");
				u = copyMatrix3D(unwrapped,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 3 && chosen == 1) {
				note.append( "\nSpace average Time Averaged");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 4 && chosen == 1) {
				note.append( "\nSpace average Time Averaged");
				u = copyMatrix3D(taveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 5 && chosen == 1) {
				note.append( "\nSpace average Shifted");
				u = copyMatrix3D(shifted,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 6 && chosen == 1) {
				note.append( "\nSpace average Erased");
				u = copyMatrix3D(erased,sizex,sizey,ntimes);
				chosen = 0;
			}
			if (selectedSigAnalyze == 7 && chosen == 1) {
				note.append( "\nSpace average Space Averaged");
				u = copyMatrix3D(spaceaveraged,sizex,sizey,ntimes);
				chosen = 0;
			}
			
			spaceAverage(u,mask,sizex,sizey,ntimes,nii,njj,gaussWh,oldText,note,spaceaveraged);
			note.append(oldText + "End.  ");
			minMax(u,mask,sizex,sizey,ntimes,max,min);
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+max[nii][njj]); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+min[nii][njj]);
			note.append("\nEnd.  ");			
			note.append( "\nNormalization....");
			//normaSignalGN(spaceaveraged, mask, sizex, sizey, ntimes, norma, max, min);
			spaceaveraged = normaSignalGN(spaceaveraged,mask,sizex,sizey,ntimes,norma,max,min);
			minMax(u, mask, sizex, sizey, ntimes, max, min);
			spamax = max[nii][njj];
			spamin = min[nii][njj];
			note.append("\nMax in "+"["+nii+"]"+"["+njj+"]: "+spamax); 
			note.append("\nMin in "+"["+nii+"]"+"["+njj+"]: "+spamin);
			note.append("\nEnd.  ");
			//--------------------------------
			u = copyMatrix3D(spaceaveraged,sizex,sizey,ntimes); // u = spaceaveraged;
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
			
			chosen = 0;
			optionsAnalyze.select(chosen);
			
			validate();
		} 
		else {
			spacefirsttime = false;
		} // End space average
		
		// Perform B:-> APD - DI thresholds evaluation -> Distribution ---------
		if (ae.getSource() == thrset) {
		
			if (selectedSigLoad != 0) {
				for (int i = 0; i < sizex; i++){
					for (int j = 0; j < sizey; j++){
						intervsh1f[i][j] = 100;
						intervsh2p[i][j] = (short)(ntimes - 100);
					}
				}
			}
			double urtt = Double.valueOf(thr.getText()).doubleValue();
			ur = (int)(urtt);
			
			note.append( "\nAPD - DI thresholds....");
			apdDI(u,mask,sizex,sizey,ntimes,dt,ur,nii,njj,intervsh1f,intervsh2p,tpb,nb,ntr,apd,di);
			
			note.append("\nntr"+"["+nii+"]"+"["+njj+"]: "+ntr[nii][njj]);
			note.append("\nnb"+"["+nii+"]"+"["+njj+"]: "+nb[nii][njj]);
			note.append("\napd"+"["+nii+"]"+"["+njj+"]: "+apd[nii][njj][ntr[nii][njj]]);
			note.append("\ndi"+"["+nii+"]"+"["+njj+"]: "+di[nii][njj][ntr[nii][njj]]);
			note.append("\nEnd.");
			
			note.append( "\nAPD - DI min - max....");
			minMaxApdDi(sizex,sizey,ntimes,ntr,apd,di,minDIn,maxDIn,minAPDn,maxAPDn,mask); 
			note.append("\nmin DI in "+"["+nii+"]"+"["+njj+"]: "+minDIn[nii][njj]); 
			note.append("\nMax DI in "+"["+nii+"]"+"["+njj+"]: "+maxDIn[nii][njj]);
			note.append("\nmin APD in "+"["+nii+"]"+"["+njj+"]: "+minAPDn[nii][njj]);
			note.append("\nMax APD in "+"["+nii+"]"+"["+njj+"]: "+maxAPDn[nii][njj]);
			
			AmMApdDi(sizex,sizey,minDIn,maxDIn,minAPDn,maxAPDn,maMaApdDi,mask);
			note.append("\nAbsolute min di: "+maMaApdDi[0]);
			note.append("\nAbsolute max di: "+maMaApdDi[1]);
			note.append("\nAbsolute min apd: "+maMaApdDi[2]);
			note.append("\nAbsolute max apd: "+maMaApdDi[3]);
			note.append("\nEnd.");
			
			int mmmdi = (int)(maMaApdDi[0]);
			int mMxdi = (int)(maMaApdDi[1]);
			nmidispace.setText(Integer.toString(mmmdi));
			nMxdispace.setText(Integer.toString(mMxdi));
			int mmmapd = (int)(maMaApdDi[2]);
			int mMxapd = (int)(maMaApdDi[3]);
			nmiapdspace.setText(Integer.toString(mmmapd));
			nMxapdspace.setText(Integer.toString(mMxapd));
			
			//hetSpaceAPDDI(mask,ntr,sizex,sizey,ntimes,dt,apd,di,apdSpaceTBD,diSpaceTBD);
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
			
			if (alternansc) {
				// Call method to evaluate mean APD - DI
				note.append( "\nPresence of alternans");
				meanApdDiA(sizex, sizey, ntr, di, apd, mdiAo, mapdAo, mdiAe, mapdAe, mask);
				
				note.append("\nMean odd DI in "+"["+nii+"]"+"["+njj+"]: "+mdiAo[nii][njj]);
				note.append("\nMean odd APD in "+"["+nii+"]"+"["+njj+"]: "+mapdAo[nii][njj]);
				note.append("\nMean even DI in "+"["+nii+"]"+"["+njj+"]: "+mdiAe[nii][njj]);
				note.append("\nMean even APD in "+"["+nii+"]"+"["+njj+"]: "+mapdAe[nii][njj]);
				
				minMaxApdDiA(sizex,sizey,ntimes,ntr,apd,di,minDIAe,maxDIAe,minAPDAe,maxAPDAe,minDIAo,maxDIAo,minAPDAo,maxAPDAo,mask);
				note.append("\nmin DIe in "+"["+nii+"]"+"["+njj+"]: "+minDIAe[nii][njj]); 
				note.append("\nMax DIe in "+"["+nii+"]"+"["+njj+"]: "+maxDIAe[nii][njj]);
				note.append("\nmin APDe in "+"["+nii+"]"+"["+njj+"]: "+minAPDAe[nii][njj]);
				note.append("\nMax APDe in "+"["+nii+"]"+"["+njj+"]: "+maxAPDAe[nii][njj]);
				note.append("\nmin DIo in "+"["+nii+"]"+"["+njj+"]: "+minDIAo[nii][njj]); 
				note.append("\nMax DIo in "+"["+nii+"]"+"["+njj+"]: "+maxDIAo[nii][njj]);
				note.append("\nmin APDo in "+"["+nii+"]"+"["+njj+"]: "+minAPDAo[nii][njj]);
				note.append("\nMax APDo in "+"["+nii+"]"+"["+njj+"]: "+maxAPDAo[nii][njj]);
				
				// Flag to plot the mean APD - DI ------------------------------
				apdDistc = false;
				apdDiMeanS.setState(true);
				apdDiMeanc = true;
				validate();
			}
			else {
				note.append( "\nWithout alternans");
				meanApdDi(sizex,sizey,ntr,di,apd,meandi,meanapd,mask);
				
				note.append("\nMean DI in "+"["+nii+"]"+"["+njj+"]: "+meandi[nii][njj]);
				note.append("\nMean APD in "+"["+nii+"]"+"["+njj+"]: "+meanapd[nii][njj]+"\n");
				// Flag to plot the APD - DI mean ------------------------------
				apdDistc = false;
				apdDiMeanS.setState(true);
				apdDiMeanc = true;
				validate();
			}
		}
		else {
			apdDiMeanc = false;
		}
		
		// Set the number of APD/DI to show in space ---------------------------
		if (ae.getSource() == napddiA && napddi <= ntr[nii][njj]-2) {
			napddi++;
			nnapddi.setText(Integer.toString(napddi)); 
		}
		if (ae.getSource() == napddiB && napddi >= 2) {
			napddi--;
			nnapddi.setText(Integer.toString(napddi)); 
		}

		// Take the number of frame to skip during the movie -------------------
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
		
		// Movie ---------------------------------------------------------------
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
		
		// Stamp Sig. B:-> generate aschii file --------------------------------
		if (ae.getSource() == generateS) {
			
			if (generate == 1) { // Original
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(original[nii][njj][tT1]);
				}
				String title = "Original Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 2) { // Unwrapped
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(unwrapped[nii][njj][tT1]);
				}
				String title = "Unwrapped Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj);  
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 3) { // Time averaged
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(taveraged[nii][njj][tT1]);
				}
				String title = "T-averaged Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 4) { // Shifting line
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(taveraged[nii][njj][tT1]);
				}
				String title = "Shifting Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 5) { // Shifted
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(taveraged[nii][njj][tT1]);
				}
				String title = "Shifted Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 6) { // Erased
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(erased[nii][njj][tT1]);
				}
				String title = "Erased Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 7) { // Space averaged
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for(int t = mintimeplot; t < maxtimeplot; t = t + dt) {
					jjj=jjj+1;
					int tT1 = t/dt;
					datat[jjj]=(float)(tT1);
					data[jjj]=(float)(taveraged[nii][njj][tT1]);
				}
				String title = "S-averaged Sig.";
				String ondata = "Sig.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 8) { // Thresholds
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for (int n = 1; n < nb[nii][njj]; n++){
					jjj=jjj+1;
					datat[jjj]=(float)(tpb[nii][njj][n]);
					data[jjj]=(float)(ur);
				}
				String title = "Thresholds";
				String ondata = "Thr.   \t"+"Time \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 9) { // APD - DI
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				for (int n = 1; n < ntr[nii][njj]; n++){
					jjj=jjj+1;
					datat[jjj]=(float)(di[nii][njj][n]);
					data[jjj]=(float)(apd[nii][njj][n]);
				}
				String title = "DIs-APDs";
				String ondata = "DI     \t"+"APD     \t"+"x \t"+"y \t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
			if (generate == 10) { // APD - DI mean value
				float data[] = new float[ntimes];
				float datat[] = new float[ntimes];				
				int jjj=-1;
				if (alternansc) {
					jjj=jjj+1;
					datat[jjj]=(float)(mdiAo[nii][njj]);
					data[jjj]=(float)(mapdAo[nii][njj]);
					jjj=jjj+1;
					datat[jjj]=(float)(mdiAe[nii][njj]);
					data[jjj]=(float)(mapdAe[nii][njj]);
				}
				else {
					jjj=jjj+1;
					datat[jjj]=(float)(meandi[nii][njj]);
					data[jjj]=(float)(meanapd[nii][njj]);
				}

				String title = "DI-APD mean value";
				String ondata = "DI     \t"+"APD     \t"+"x \t"+" y\t";
				da = new DataWin(data,datat,jjj,title,ondata,nii,njj); 
				da.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {da = null;}}); 
			}
		}
		
		// Save APD-DI. B:->  aschii file --------------------------------------
		if (ae.getSource() == saveMrest) {
			String title = "Mean Restitution on Point";
			datarest = new restDataAn(title,alternansc,nii,njj,maMaApdDi,meandi,meanapd,mdiAo,mapdAo,mdiAe,mapdAe); 
			datarest.addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we) {datarest = null;}}); 
		}
		
			// Save B:-> save analyzed data
		if (ae.getSource() == save) {
			note.append("\nSaving analyzed data:\n");
			note.append("DIM: "+u.length+", "+u[0].length+", "+u[0][0].length);
			if (selectedSigSave == 1) {
				note.append("\nOriginal");
				anfiletosave = String.valueOf("analyzed/"+"Original_"+analyzedfile);
				try {
					String dimOut = new String(anfiletosave+"_DIM");
					DataOutputStream outDim = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dimOut)));
					outDim.writeShort(spaceaveraged.length); // sizex
					outDim.writeShort(spaceaveraged[0].length); // sizey
					outDim.writeShort(spaceaveraged[0][0].length); // nframes
					outDim.close();
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(anfiletosave)));
					for (int x = 0; x < original.length; x++) {
						for (int y = 0; y < original[0].length; y++) {
							for (int z = 0; z < original[0][0].length; z++) {
								out.writeShort(original[x][y][z]);
							}
						}
					}
					out.close();
					note.append("Saving complete.\n");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (selectedSigSave == 2) {
				anfiletosave = String.valueOf("analyzed/"+"Unwrapped_"+analyzedfile);
				try {
					note.append("\nUnwrapped");
					String dimOut = new String(anfiletosave+"_DIM");
					DataOutputStream outDim = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dimOut)));
					outDim.writeShort(spaceaveraged.length); // sizex
					outDim.writeShort(spaceaveraged[0].length); // sizey
					outDim.writeShort(spaceaveraged[0][0].length); // nframes
					outDim.close();
					
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(anfiletosave)));
					for (int x = 0; x < unwrapped.length; x++) {
						for (int y = 0; y < unwrapped[0].length; y++) {
							for (int z = 0; z < unwrapped[0][0].length; z++) {
								out.writeShort(unwrapped[x][y][z]);
							}
						}
					}
					out.close();
					note.append("Saving complete.\n");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (selectedSigSave == 3) {
				anfiletosave = String.valueOf("analyzed/"+"T-Averaged_"+analyzedfile);
				try {
					note.append("\nTime Averaged.\n");
					String dimOut = new String(anfiletosave+"_DIM");
					DataOutputStream outDim = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dimOut)));
					outDim.writeShort(spaceaveraged.length); // sizex
					outDim.writeShort(spaceaveraged[0].length); // sizey
					outDim.writeShort(spaceaveraged[0][0].length); // nframes
					outDim.close();
					
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(anfiletosave)));
					for (int x = 0; x < taveraged.length; x++) {
						for (int y = 0; y < taveraged[0].length; y++) {
							for (int z = 0; z < taveraged[0][0].length; z++) {
								out.writeShort(taveraged[x][y][z]);
							}
						}
					}
					out.close();
					note.append("Saving complete.\n");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (selectedSigSave == 4) {
				anfiletosave = String.valueOf("analyzed/"+"ShiftL_"+analyzedfile);
				try {
					note.append("\nShifting line.\n");
					String dimOut = new String(anfiletosave+"_DIM");
					DataOutputStream outDim = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dimOut)));
					outDim.writeShort(spaceaveraged.length); // sizex
					outDim.writeShort(spaceaveraged[0].length); // sizey
					outDim.writeShort(spaceaveraged[0][0].length); // nframes
					outDim.close();
					
					String intervOut1 = new String(anfiletosave+"_INTERV1");
					DataOutputStream outInterv1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut1)));
					for (int x = 0; x < intervsh1f.length; x++) {
						for (int y = 0; y < intervsh1f[0].length; y++) {
					 		outInterv1.writeShort(intervsh1f[x][y]);
					 	}
					}
					outInterv1.close();
					String intervOut2 = new String(anfiletosave+"_INTERV2");
					DataOutputStream outInterv2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut2)));
					for (int x = 0; x < intervsh2p.length; x++) {
						for (int y = 0; y < intervsh2p[0].length; y++) {
					 		outInterv2.writeShort(intervsh2p[x][y]);
					 	}
					}
					outInterv2.close();
					String intervOut3 = new String(anfiletosave+"_INTERV3");
					DataOutputStream outInterv3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut3)));
					for (int x = 0; x < ntll.length; x++) {
						for (int y = 0; y < ntll[0].length; y++) {
					 		outInterv3.writeShort(ntll[x][y]);
					 	}
					}
					outInterv3.close();
					
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(anfiletosave)));
					for (int x = 0; x < basevL.length; x++) {
						for (int y = 0; y < basevL[0].length; y++) {
							for (int z = 0; z < basevL[0][0].length; z++) {
								out.writeShort(basevL[x][y][z]);
							}
						}
					}
					out.close();
					note.append("Saving complete.\n");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (selectedSigSave == 5) {
				anfiletosave = String.valueOf("analyzed/"+"Shifted_"+analyzedfile);
				try {
					note.append("\nShifted.\n");
					String dimOut = new String(anfiletosave+"_DIM");
					DataOutputStream outDim = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dimOut)));
					outDim.writeShort(spaceaveraged.length); // sizex
					outDim.writeShort(spaceaveraged[0].length); // sizey
					outDim.writeShort(spaceaveraged[0][0].length); // nframes
					outDim.close();
					
					String intervOut1 = new String(anfiletosave+"_INTERV1");
					DataOutputStream outInterv1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut1)));
					for (int x = 0; x < intervsh1f.length; x++) {
						for (int y = 0; y < intervsh1f[0].length; y++) {
					 		outInterv1.writeShort(intervsh1f[x][y]);
					 	}
					}
					outInterv1.close();
					String intervOut2 = new String(anfiletosave+"_INTERV2");
					DataOutputStream outInterv2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut2)));
					for (int x = 0; x < intervsh2p.length; x++) {
						for (int y = 0; y < intervsh2p[0].length; y++) {
					 		outInterv2.writeShort(intervsh2p[x][y]);
					 	}
					}
					outInterv2.close();
					String intervOut3 = new String(anfiletosave+"_INTERV3");
					DataOutputStream outInterv3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut3)));
					for (int x = 0; x < ntll.length; x++) {
						for (int y = 0; y < ntll[0].length; y++) {
					 		outInterv3.writeShort(ntll[x][y]);
					 	}
					}
					outInterv3.close();
					
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(anfiletosave)));
					for (int x = 0; x < shifted.length; x++) {
						for (int y = 0; y < shifted[0].length; y++) {
							for (int z = 0; z < shifted[0][0].length; z++) {
								out.writeShort(shifted[x][y][z]);
							}
						}
					}
					out.close();
					note.append("Saving complete.\n");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			if (selectedSigSave == 7) {
				anfiletosave = String.valueOf("analyzed/"+"S-Averaged_"+analyzedfile);
				try {
					note.append("\nSpace Averaged.\n");
					String dimOut = new String(anfiletosave+"_DIM");
					DataOutputStream outDim = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dimOut)));
					outDim.writeShort(spaceaveraged.length); // sizex
					outDim.writeShort(spaceaveraged[0].length); // sizey
					outDim.writeShort(spaceaveraged[0][0].length); // nframes
					outDim.close();
					
					String intervOut1 = new String(anfiletosave+"_INTERV1");
					DataOutputStream outInterv1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut1)));
					for (int x = 0; x < intervsh1f.length; x++) {
						for (int y = 0; y < intervsh1f[0].length; y++) {
					 		outInterv1.writeShort(intervsh1f[x][y]);
					 	}
					}
					outInterv1.close();
					String intervOut2 = new String(anfiletosave+"_INTERV2");
					DataOutputStream outInterv2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut2)));
					for (int x = 0; x < intervsh2p.length; x++) {
						for (int y = 0; y < intervsh2p[0].length; y++) {
					 		outInterv2.writeShort(intervsh2p[x][y]);
					 	}
					}
					outInterv2.close();
					String intervOut3 = new String(anfiletosave+"_INTERV3");
					DataOutputStream outInterv3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(intervOut3)));
					for (int x = 0; x < ntll.length; x++) {
						for (int y = 0; y < ntll[0].length; y++) {
					 		outInterv3.writeShort(ntll[x][y]);
					 	}
					}
					outInterv3.close();
					
					DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(anfiletosave)));
					for (int x = 0; x < spaceaveraged.length; x++) {
						for (int y = 0; y < spaceaveraged[0].length; y++) {
							for (int z = 0; z < spaceaveraged[0][0].length; z++) {
								out.writeShort(spaceaveraged[x][y][z]);
							}
						}
					}
					out.close();
					note.append("Saving complete.\n");
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			// Print text analyzed data file			
			String title = namefilel;
		}
		
		// Savemask B:-> save created mask
		if (ae.getSource() == savemask) {
			savemaskpng = 1;
			note.append("\nSaving mask.\n");
			savemask.setLabel("Save Mask");
		}
		// Save APD - DI values in the selected point
		if (ae.getSource() == saveTrest){
			saveapddiTvalues = 1;
		}
		// Save APD - DI mean value in the selected point
		if (ae.getSource() == saveMrest){
			saveapddimeanvalue = 1;
		}
		// Save space DI
		if (ae.getSource() == savedispace){
			savedipng = 1;
		}
		// Save space APD
		if (ae.getSource() == saveapdspace){
			saveapdpng = 1;
		}	
		// Save movie frames
		if (ae.getSource() == savemoviepng && savemoviepng.getLabel().equals("Save Movie")){
			savemovieframes=1;
			savemoviepng.setLabel("Saving frames");
		}
		else{
			if (ae.getSource() == savemoviepng && savemoviepng.getLabel().equals("Saving frames")){
				savemovieframes=0;
				savemoviepng.setLabel("Save Movie");
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
		
		// Increase/Decrease frame during pause movie
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
		
		//Take max min plot timing ---------------------------------------------
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
				maxtimeplot = dt*ntimes - 10;
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
			if (clMaxc == 5 && mousezoom == 0) {
				mindi = mindi6;
				maxdi = maxdi6;
				minapd = minapd6;
				maxapd = maxapd6;
			}
			if (clMaxc == 6 && mousezoom == 0) {
				mindi = mindi7;
				maxdi = maxdi7;
				minapd = minapd7;
				maxapd = maxapd7;
			}
			pmindi = 0;
			pmaxdi = 0;
			pminapd = 0;
			pmaxapd = 0;
		}
		
		// Set DI space normalization
		if (ae.getSource() == ndiset) {
			double nnmidispace = Double.valueOf(nmidispace.getText()).doubleValue();
			double nnMxdispace = Double.valueOf(nMxdispace.getText()).doubleValue();
			maMaApdDi[0] = (int)(nnmidispace);
			maMaApdDi[1] = (int)(nnMxdispace);
			//System.out.println("mindi "+maMaApdDi[0] + "Mxdi "+maMaApdDi[1]);
		}
		// Set APD space normalization
		if (ae.getSource() == napdset) {
			double nnmiapdspace = Double.valueOf(nmiapdspace.getText()).doubleValue();
			double nnMxapdspace = Double.valueOf(nMxapdspace.getText()).doubleValue();
			maMaApdDi[2] = (int)(nnmiapdspace);
			maMaApdDi[3] = (int)(nnMxapdspace);
			//System.out.println("minapd "+maMaApdDi[2] + "Mxapd "+maMaApdDi[3]);
		}
	
		//Exit -----------------------------------------------------------------
		if (ae.getSource() == close) {
			dispose();
			System.exit(0);
		}			
				
		repaint();
	
	}//close actionPerformed
	//*************************************************************
	//*************************************************************
	//   Mouse section
	//*************************************************************
		public void mouseClicked(MouseEvent me){
		}
		public void mouseEntered(MouseEvent me){
		}
		public void mouseExited(MouseEvent me){                    
        }
		public void mouseMoved(MouseEvent me){
		}
		public void mousePressed(MouseEvent me){ 
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
				// Modify the mask in the selected point
				if(imask == 1){
					mask[nii+6][njj+6]=0;
				}		
				repaint();                         
			}
			if (apdSpacec || diSpacec || apdTBDc || diTBDc) {
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
		public void mouseDragged(MouseEvent me){     
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
			// Modify the mask with r=4 pixel circle
			if(imask == 1){
				int rrmask = 4;
				if(iipx > 0 && iipx < sizex && iipy > 0 && iipy < sizey ) {
					nii = (int)(iipx);
					njj = (int)(iipy);
					mask[nii+6][njj+6]=0;
					for (int i = nii-rrmask; i < nii+rrmask; i++){
						for (int j = njj-rrmask; j < njj+rrmask; j++){
							double rr=Math.sqrt((double)((i-nii)*(i-nii))+(double)((j-njj)*(j-njj)));
							if(rr <= rrmask) {
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
			}
			mousezoom = 0;            // to clear zoom square
			validate();			
			repaint();
		}					
	//*************************************************************
	//*************************************************************
	//	Keyboard section
	//*************************************************************
		public void keyTyped(KeyEvent ke) {
		}			
		public void keyPressed(KeyEvent ke) {
			System.out.println("OK");
			switch (ke.getKeyCode()) {
				case KeyEvent.VK_LEFT:  // move x coordinate left
					System.out.println("OK");
					nii = nii - 1;
					tnii.setText(String.valueOf(nii));
					tnjj.setText(String.valueOf(njj));
					break;
				case KeyEvent.VK_RIGHT: // move x coordinate right
					nii = nii + 1;
					tnii.setText(String.valueOf(nii));
					tnjj.setText(String.valueOf(njj));
					break;
				case KeyEvent.VK_UP: // move x coordinate up
					njj = njj + 1;
					tnii.setText(String.valueOf(nii));
					tnjj.setText(String.valueOf(njj));
					break;
				case KeyEvent.VK_DOWN: // move x coordinate down
					njj = njj - 1;
					tnii.setText(String.valueOf(nii));
					tnjj.setText(String.valueOf(njj));
			}
			repaint();
		}
		public void keyReleased(KeyEvent ke) {
		}
	//*************************************************************
	//*************************************************************
	public void itemStateChanged(ItemEvent ie ){
			
		// Saving files in txt form --------------------------------------------
		if(options.getSelectedIndex() == 1){
			generate = 1;
		}
		if(options.getSelectedIndex() == 2){
			generate = 2;
		}
		if(options.getSelectedIndex() == 3){
			generate = 3;
		}
		if(options.getSelectedIndex() == 4){
			generate = 4;
		}
		if(options.getSelectedIndex() == 5){
			generate = 5;
		}
		if(options.getSelectedIndex() == 6){
			generate = 6;
		}
		if(options.getSelectedIndex() == 7){
			generate = 7;
		}
		if(options.getSelectedIndex() == 8){
			generate = 8;
		}
		if(options.getSelectedIndex() == 9){
			generate = 9;
		}
		if(options.getSelectedIndex() == 10){
			generate = 10;
		}
		// Choosing files to Load ----------------------------------------------
		if(optionsLoad.getSelectedIndex() == 1){
			selectedSigLoad = 1;
			selectedSigAnalyze = 1;
			chosen = 1;
		}
		if(optionsLoad.getSelectedIndex() == 2){
			selectedSigLoad = 2;
			selectedSigAnalyze = 2;
			chosen = 1;
		}
		if(optionsLoad.getSelectedIndex() == 3){
			selectedSigLoad = 3;
			selectedSigAnalyze = 3;
			chosen = 1;
		}
		if(optionsLoad.getSelectedIndex() == 4){
			selectedSigLoad = 4;
			selectedSigAnalyze = 4;
			chosen = 1;
		}
		if(optionsLoad.getSelectedIndex() == 5){
			selectedSigLoad = 5;
			selectedSigAnalyze = 5;
			chosen = 1;
		}
		if(optionsLoad.getSelectedIndex() == 6){
			selectedSigLoad = 6;
			selectedSigAnalyze = 6;
			chosen = 1;
		}
		if(optionsLoad.getSelectedIndex() == 7){
			selectedSigLoad = 7;
			selectedSigAnalyze = 7;
			chosen = 1;
		}
		// Choosing files to Save ----------------------------------------------
		if(optionsSave.getSelectedIndex() == 1){
			selectedSigSave = 1;
		}
		if(optionsSave.getSelectedIndex() == 2){
			selectedSigSave = 2;
		}
		if(optionsSave.getSelectedIndex() == 3){
			selectedSigSave = 3;
		}
		if(optionsSave.getSelectedIndex() == 4){
			selectedSigSave = 4;
		}
		if(optionsSave.getSelectedIndex() == 5){
			selectedSigSave = 5;
		}
		if(optionsSave.getSelectedIndex() == 6){
			selectedSigSave = 6;
		}
		if(optionsSave.getSelectedIndex() == 7){
			selectedSigSave = 7;
		}
		// Choosing file to Analyze --------------------------------------------
		if(optionsAnalyze.getSelectedIndex() == 1){
			selectedSigAnalyze = 1;
		}
		if(optionsAnalyze.getSelectedIndex() == 2){
			selectedSigAnalyze = 2;
		}
		if(optionsAnalyze.getSelectedIndex() == 3){
			selectedSigAnalyze = 3;
		}
		if(optionsAnalyze.getSelectedIndex() == 4){
			selectedSigAnalyze = 4;
		}
		if(optionsAnalyze.getSelectedIndex() == 5){
			selectedSigAnalyze = 5;
		}
		if(optionsAnalyze.getSelectedIndex() == 6){
			selectedSigAnalyze = 6;
		}
		if(optionsAnalyze.getSelectedIndex() == 7){
			selectedSigAnalyze = 7;
		}		
		// Choice signal to plot -----------------------------------------------
		if (signal1.getState() 	== true) 	{ // original
			originc = true;
			validate();
		}
		else {
			originc = false;
			validate();
		}
		if (signal2.getState() == true) 	{ // unwrapped
			unwrapc = true;
			validate();
		}
		else {
			unwrapc = false;
			validate();
		}
		if (signal3.getState() == true) 	{ // time averaged
			originc = false;
			tavec = true;
			validate();
		}
		else {
			tavec = false;
			validate();
		}
		if (signal4.getState() == true) 	{ // shifting line
			shiftl = true;
			validate();
		}	
		else {
			shiftl = false;
			validate();
		}
		if (signal5.getState() == true) 	{ // shifted
			shiftl = false;
			tavec = false;
			shiftc = true;
			validate();
		}
		else {
			shiftc = false;
			validate();
		}
		if (signal6.getState() 	== true) 	{ // erased
			erasec = true;
			validate();
		}
		else {
			erasec = false;
			validate();
		}
		if (signal7.getState() 	== true) 	{ // space averaged
			spaveragec = true;
			validate();
		}
		else {
			spaveragec = false;
			validate();
		}
		if (signal8.getState() 	== true) 	{ // thersholds
			thresholdc = true;
			validate();
		}
		else {
			thresholdc = false;
			validate();
		}
		
		// APD - DI ------------------------------------------------------------
		// distribution
		if (apdDiDist.getState() == true) 	{ 
			apdDistc = true;
			apdDiMeanc = false;
			validate();
		}
		else 								{
			apdDistc = false;	
			validate();
		}
		// mean value
		if (apdDiMeanS.getState() == true) 	{ 
			apdDiMeanc = true;
			apdDistc = false;
			validate();
		}
		else {
			apdDiMeanc = false;
			validate();
		}	
		// APD space
		if (apdSpace.getState() == true) 	{ 
			apdSpacec = true;
			diSpacec = false;
			apdDiMeanc = false;
			apdDistc = false;
			validate();
		}
		else {
			apdSpacec = false;
			validate();
		}	
		// DI space
		if (diSpace.getState() == true) 	{ 
			diSpacec = true;
			apdSpacec = false;
			apdDiMeanc = false;
			apdDistc = false;
			validate();
		}
		else {
			diSpacec = false;
			validate();
		}	
		// APD TBD space
		if (apdTBD.getState() == true) 	{ 
			apdTBDc = true;
			apdSpacec = false;
			diSpacec = false;
			apdDiMeanc = false;
			apdDistc = false;
			validate();
		}
		else {
			apdTBDc = false;
			validate();
		}	
		// DI TBD space
		if (diTBD.getState() == true) 	{ 
			diTBDc = true;
			diSpacec = false;
			apdSpacec = false;
			apdDiMeanc = false;
			apdDistc = false;
			validate();
		}
		else {
			diTBDc = false;
			validate();
		}	
		
		// Choice for the color map
		nmap = colormap.getSelectedIndex();		
		// Choice for alternans
		alternansc = (alternans.getSelectedIndex() != 0);
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
		// Map color -----------------------------------------------------------
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
		// end map colors ------------------------------------------------------
		// For background color
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
		// Map color -----------------------------------------------------------
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
		// end map colors ------------------------------------------------------
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
	//**************************************************************************

						// *** Calculations *** //
	
	// This method reads NEW data and give video dimensions --------------------
	public short[][][] readArray(String namefilel) { 
		cascade_import data = new cascade_import();
		try{
			short [][][] video = data.readVideoFile(namefilel);
			nframes = data.readNumbFrame();
			sizex = data.readXDim();
			sizey = data.readYDim();
			return video;
        }
		catch (IOException e) { 
			System.out.println("IOException while reading file.");
			System.exit(0);
		}
		return video;	
	} // close readArray 	
	
	// This method reads Analyzed data -----------------------------------------
	public short[][][] readData(String namefilel) { 
		try{
			short nono = 3;
			short[] dataDim = new short[nono];
			String dim = new String(namefilel+"_DIM");
			DataInputStream dimdim = new DataInputStream(new BufferedInputStream(new FileInputStream(dim)));
			for (int i = 0; i < nono; i++) {
				dataDim[i] = dimdim.readShort();
			}
			dimdim.close();
			sizex = dataDim[0];
			sizey = dataDim[1];
			nframes = dataDim[2];
			ntimes = nframes;
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(namefilel)));			
			short[][][] video = new short[sizex][sizey][nframes]; 
			for (int x = 0; x < sizex; x++) {
				for (int y = 0; y < sizey; y++) {
					for (int z = 0; z < nframes; z++) {
						video[x][y][z] = dis.readShort();
					}
				}
			}
			dis.close();
			return video;
		}
		catch (IOException e) { 
			System.out.println("IOException while reading file.");
			System.exit(0);
		}
		return video;
	} // close readArray	
	
	// This method reads related info when reading Analyzed data ---------------
	public short[][] readDataInfo1(String namefilel) { 
		try{
			String interv10 = new String(namefilel+"_INTERV1");
			DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(interv10)));			
			short[][] intervsh1f = new short[sizex][sizey];
			for (int x = 0; x < sizex; x++) {
				for (int y = 0; y < sizey; y++) {
					intervsh1f[x][y] = dis.readShort();
				}
			}
			dis.close();
			return intervsh1f;
		}
		catch (IOException e) { 
			System.out.println("IOException while reading file.");
			System.exit(0);
		}
		return intervsh1f;
	} // close readDataInfo		
	
	// This method reads related info when reading Analyzed data ---------------
		public short[][] readDataInfo2(String namefilel) { 
			try{
				String interv10 = new String(namefilel+"_INTERV2");
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(interv10)));			
				short[][] intervsh2p = new short[sizex][sizey];
				for (int x = 0; x < sizex; x++) {
					for (int y = 0; y < sizey; y++) {
						intervsh2p[x][y] = dis.readShort();
					}
				}
				dis.close();
				return intervsh2p;
			}
			catch (IOException e) { 
				System.out.println("IOException while reading file.");
				System.exit(0);
			}
			return intervsh2p;
		} // close readDataInfo		
	
	// This method reads related info when reading Analyzed data ---------------
		public short[][] readDataInfo3(String namefilel) { 
			try{
				String interv10 = new String(namefilel+"_INTERV3");
				DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(interv10)));			
				short[][] ntll = new short[sizex][sizey];
				for (int x = 0; x < sizex; x++) {
					for (int y = 0; y < sizey; y++) {
						ntll[x][y] = dis.readShort();
					}
				}
				dis.close();
				return ntll;
			}
			catch (IOException e) { 
				System.out.println("IOException while reading file.");
				System.exit(0);
			}
			return ntll;
		} // close readDataInfo		
		
		
	
	// This method convert the data and copy the original signal ---------------
	public short[][][] convertArray(short[][][] video,int sizex,int sizey,
	int ntimes){
			
		short[][][] u = new short[sizex][sizey][ntimes];
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				for (int k = 0; k < ntimes; k++) {						
						//Make constant the last times
					if (k < 10) video[k][i][j] = video[10][i][j];
					if (k > (ntimes - 10)) video[k][i][j] = video[ntimes - 10][i][j];
					u[i][j][k] = (short)(-video[k][i][j]); //convert and copy data
				}
			}
		}
		return u;
	} // close convertArray	
	
	// This method copies 2D short matrix --------------------------------------
	public short[][] copyMatrix2D(short[][] matrix,int sizex,int sizey){
			
		short[][] u = new short[sizex][sizey];
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				u[i][j] = matrix[i][j];
			}
		}
		return u;
	} // close copyMatrix2D	
	
	// This method copies 2D int matrix ----------------------------------------
	public int[][] copyMatrixI2D(int[][] matrix,int sizex,int sizey){
			
		int[][] u = new int[sizex][sizey];
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				u[i][j] = matrix[i][j];
			}
		}
		return u;
	} // close copyMatrixI2D		
	
	// This method copies 3D short matrix --------------------------------------
	public short[][][] copyMatrix3D(short[][][] matrix,int sizex,int sizey,
	int ntimes){
		
		short[][][] u = new short[sizex][sizey][ntimes];
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				for (int k = 0; k < ntimes; k++) {	
					u[i][j][k] = matrix[i][j][k];
				}
			}
		}
		return u;
	} // close copyMatrix3D 
	
	// This method copies 3D int matrix ----------------------------------------
	public int[][][] copyMatrixI3D(int[][][] matrix,int sizex,int sizey,
	int ntimes){
			
		int[][][] u = new int[sizex][sizey][ntimes];
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				for (int k = 0; k < ntimes; k++) {	
					u[i][j][k] = matrix[i][j][k];
				}
			}
		}
		return u;
	} // close copyMatrixI3D
	
	// This method evaluates the maximum and minimum values of "signal" --------
	public static void minMax(short[][][] signal,short[][] mask,int sizex,
	int sizey,int ntimes,short[][] max,short[][] min){
							   			   
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){			
				if (mask[i+6][j+6] == 1) {
					max[i][j] = -32768;
					min[i][j] = 32767;	
					for (int k = 10; k < (ntimes - 10); k++) {
						if (signal[i][j][k] < min[i][j]) min[i][j] = signal[i][j][k];
						if (signal[i][j][k] > max[i][j]) max[i][j] = signal[i][j][k];
					}
				}
			}
		}
	} // close minMax
	
	// This method evaluates the ABSOLUTE maximum and minimum values -----------
	public static void minAMaxA(int sizex,int sizey,int ntimes,short[][] max,
	short[][] min,short[][] mask,int[] maMa){
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
	} // close minMax 	
	
	// This method evaluates the maximum and minimum values --------------------
	public static void minAMaxAINT(int sizex,int sizey,int ntimes,
	int[][] maxINT,int[][] minINT,int[] maMa){
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
		
	} //close minMax 	
	
	// This method unwraps the saturated signal --------------------------------
	public static void unwrapSignal( short[][][] u,short[][] mask,int sizex,
	int sizey,int ntimes,int nii,int njj,short[][][] unwrapped,
	short[][] max,short[][] min ){
									 
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
	} // close unwrapSignal 	
	
	// This method normalizes the signal in [0:10000] --------------------------
	public static void normaSignal(short[][][] u,short[][] mask,int sizex,
	int sizey,int ntimes,short norma,short[][] max,short[][] min,
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
	} // close normaSignal 	
	
	// This method normalizes "signal" in [0:10000] ----------------------------
	public short[][][] normaSignalGN(short[][][] signal,short[][] mask,
	int sizex,int sizey,int ntimes,short norma,short[][] max,short[][] min){
		
		
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
		return signal;
	} // close normaSignal 	
	
	// This method performs the time average -----------------------------------
	public short [][][] timeAverage(short[][][] u,short[][] mask,int sizex,
	int sizey,int ntimes){
		short[][][] taveraged = new short[sizex][sizey][ntimes];
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
		return taveraged;
	} // close timeAverage 	
	
	//This method shifts the signal without rewrite the u matrix ---------------
	public static void shiftSignal( short[][][] u,short[][] mask,int sizex,
	int sizey,int ntimes,int ifreq,short[][] max,short[][] min,short[][] ntll,
	short[][][] nnso,short[][][] shifted,short[][] intervsh1first,
	short[][] intervsh2final ){
	
		short smin[][][] = new short [sizex][sizey][ntimes];
		short smint[][][] = new short [sizex][sizey][ntimes];
		
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					ntll[i][j] = 0;
					for (int k = 0; k < ntimes; k++){
						// if (k < 1000) smin[i][j][k] = (short)(32767);
						smin[i][j][k] = (short)(32767);
						//basevL[i][j][k] = min[i][j]; //uncomment if min != 0
						nnso[i][j][k] = (short)(0);
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
							if(u[i][j][nn] < smin[i][j][ntll[i][j]]) {   
								smin[i][j][ntll[i][j]] = u[i][j][nn];  //minimum in ifreq
								smint[i][j][ntll[i][j]] = (short)(nn);	//time of minimum
								intervsh2final[i][j] = (short)(nn);// Take the last time of minima 
							}
						}
					}					
				}
			}
		}
		// Take the first time of minima
		for (int i = 0; i < sizex; i++) {
			for (int j = 0; j < sizey; j++) {
				if (mask[i+6][j+6] == 1) {
					boolean vero = true;				
					for (int k = 0; k < ifreq; k++) {
						if (smint[i][j][k] > 0 && vero == true) {
							intervsh1first[i][j] = smint[i][j][k];
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
					for (int nt = intervsh1first[i][j]; nt < intervsh2final[i][j]; nt++){
						if (nt > (int)(smint[i][j][ntll[i][j]])) ntll[i][j]++;
						// Difference every two next minima starting from ntll = 1, ntll = 0
						// Delta t for every two minima
						idis = 	(short)(smin[i][j][ntll[i][j]] - smin[i][j][ntll[i][j]-1]);
						idist_old = idist; 
						idist = (short)(smint[i][j][ntll[i][j]] - smint[i][j][ntll[i][j]-1]); 
						if (idist == 0) idist = idist_old; // Avoid 0 values
						// LINEAR INTERPOLATION between two minima
						fraq  = (double)(( (double)(nt) - (double)(smint[i][j][ ntll[i][j] - 1 ] ))/(double)(idist));	
						nnso[i][j][nt] = (short)((double)(smin[i][j][ntll[i][j] - 1])*(1.0 - fraq) + (double)(smin[i][j][ntll[i][j]])*fraq);		
						shifted[i][j][nt] = (short)(u[i][j][nt] - nnso[i][j][nt]);
						// Avoid negative values
						if (shifted[i][j][nt] < 0) shifted[i][j][nt] = 0;
					}
					
					// Set to 0 the first and last times
					for (int nt = 0; nt < intervsh1first[i][j]; nt++){
						shifted[i][j][nt] = 0;
					}
					for (int nt = intervsh2final[i][j]; nt < ntimes; nt++){
						shifted[i][j][nt] = 0;
					}
				}
			}	
		}
	} // close shiftSignal 
	
	//This method performs the space average without rewrite the u matrix
	public static void spaceAverage( short[][][] u,short[][] mask,int sizex,
	int sizey,int ntimes,int nii,int njj,int gaussWh,String oldText,
	TextArea note,short[][][] spaceaveraged ){
		int gwh = gaussWh;
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
				xcoef[i][j] = 1000*9*Math.exp(-( (ii*ii) + (jj*jj) )/gwh);
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
			for (int j = 0; j < sizey; j++){
				for (int i = 0; i < sizex; i++){	
					if (mask[i+6][j+6] == 1) {
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
		}
	} // close spaceAverage 
	
	// This method erase the first and last times ------------------------------
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
	}// close eraseTime 
			
	//This method evaluates APD and DI
	public static void apdDI( short[][][] u, short[][] mask,
							  int sizex, int sizey, int ntimes, int dt,
							  int ur, int nii, int njj,
							  short[][] istep, short[][] lstep,
							  short[][][] tpb, int[][] nb, int[][] ntr,
							  short[][][] apd, short[][][] di ){
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				nb[i][j] = 0; // Number of thresholds
				ntr[i][j] = 0; // Number of APD - DI
				for (int k = 0; k < ntimes; k++){
					tpb[i][j][k] = 0;
					di[i][j][k]	= 0;
					apd[i][j][k] = 0;				
				}
			}
		}
		int nsen = 0;
		int nsen2 = 0;
		int tzero = 0;
		for (int i = 0; i < (sizex - 0); i++){
			for (int j = 0; j < (sizey - 0); j++){
				if (mask[i+6][j+6] == 1) {
					nsen = 0;
					tzero = 0;
					for (int nt = (istep[i][j]+10); nt <= (lstep[i][j]-10); nt++) {
						if (nsen == 0 &&
							u[i][j][nt - 1] > u[i][j][nt] &&
							u[i][j][nt - 2] > u[i][j][nt + 1] &&
							(u[i][j][nt + 1] - ur)*(u[i][j][nt] - ur) <= 0) {nsen++;}
						if (nsen == 1) {
							if ((u[i][j][nt+1]-ur)*(u[i][j][nt]-ur) < 0 && tzero == 0) {
								nb[i][j]++;
								tpb[i][j][nb[i][j]]=(short)(nt*dt-dt*((u[i][j][nt]-ur)/(u[i][j][nt+1]-u[i][j][nt])));
								nsen2 = 1;
							}
							// If it's the first zero then evaluate the threshold
							// Otherwise update the counter
							if ((u[i][j][nt+1]-ur)*(u[i][j][nt]-ur) == 0 &&
								(u[i][j][nt+1]-u[i][j][nt]) != 0){
								if (tzero == 0) {
									tzero = 1;
									nb[i][j]++;
									tpb[i][j][nb[i][j]]=(short)(nt*dt-dt*((u[i][j][nt]-ur)/(u[i][j][nt+1]-u[i][j][nt])));
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
								di[i][j][ntr[i][j]]=(short)(tpb[i][j][nb[i][j]-1]-tpb[i][j][nb[i][j]-2]);
								apd[i][j][ntr[i][j]]=(short)(tpb[i][j][nb[i][j]-0]-tpb[i][j][nb[i][j]-1]); 
								nsen2 = 0;
							}
						}
					}
				}
			}
		}
	}// close adpDI ------------------------------------------------------------
	
	// This method evaluates min - max APD - DI without alternans
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
	}// close minMaxApdDi ------------------------------------------------------
	
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
	}// close absolute AmMApdDi ------------------------------------------------
	
	//This method evaluates min - max APD - DI in precense of alternans
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
	}// close minMaxApdDiA -----------------------------------------------------
	
	//This method evaluates mean APD - mean DI without alternans
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
	}// close meanApdDI --------------------------------------------------------
		
	//This method evaluates mean APD - mean DI with alternans
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
	}// close meanApdDiA -------------------------------------------------------
	
	/*
	//This method evaluates mean APD - mean DI with alternans
	public static void hetSpaceAPDDI(	short[][] mask,
										int[][] ntr, 
										int sizex, int sizey, 
										int ntimes, int dt,
										short[][][] apd, short[][][] di, 
										int[][] apdSpaceTBD, 
										int[][] diSpaceTBD){
		
		int diffAPD = 0;
		int diffDI = 0;
		for (int i = 0; i < sizex; i++){
			for (int j = 0; j < sizey; j++){
				if (mask[i+6][j+6] == 1) {
					if (ntr[i][j] > 2) {
						diffAPD = 0;
						diffDI = 0;
						for (int k = 1; k < (ntr[i][j] - 2); k++) {
							diffAPD = apd[i][j][k] - apd[i][j][k+1];
							diffDI = di[i][j][k] - di[i][j][k+1];
							if ((int)( Math.abs(diffAPD) ) <= dt) {
								apdSpaceTBD[i][j] = 0;
							}
							if (diffAPD > dt) {
								apdSpaceTBD[i][j] = 1;
							}
							if (diffAPD < -dt) {
								apdSpaceTBD[i][j] = -1;
							}
							if ((int)( Math.abs(diffDI) ) <= dt) {
								diSpaceTBD[i][j] = 0;
							}
							if (diffDI > dt) {
								diSpaceTBD[i][j] = 1;
							}
							if (diffDI < -dt) {
								diSpaceTBD[i][j] = -1;
							}
						}
					}
				}
			}
		}
		
	}// close hetSpaceAPDDI -------------------------------------------------------
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//End class Java program

