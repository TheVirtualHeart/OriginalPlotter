import java.awt.*;
import java.awt.event.*;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.lang.*;
import java.text.*;
import java.io.*;


public 	class		graphic 
		extends 	Frame
		implements 	ActionListener, ItemListener, MouseListener {
		//*************************************************************	
	
	
	
	//Interface declarations
	private Button		start, stop, reset, save, movie, close, setpoint, setshift, seterase, spaaverage;
	private Checkbox 	alternance, saverage, norma1, norma2;
	private Checkbox 	signal1, signal2, signal3, signal4, signal5, signal6, signal7, signal8;
	private Label 		file, comment, aconditions, point, intervall, pconditions, normat;
	private TextField 	filename, tnii, tnjj, tfreq, erasesx, erasedx;
	private TextField 	answer;
	private TextArea 	note;
	private Choice 		cnorm, colormap;
	private Point		p;
	
	
	
	//Commands for analysis
	boolean beginAnalyze	= false;		//start analysis
	
	boolean firsttime		= true;			//read and run standard analysis
	boolean shiftfirsttime	= false;		//run the shifting code 
	boolean erasefirsttime	= false;		//run the erasing code
	
	boolean spacefirsttime	= false;		//run the space time code
	
	
	//Commands for plot
	boolean originc 		= false;	//plot the original signal
	boolean unwrapc			= false;	//plot the unwrapped signal
	boolean tavec			= false;	//plot the time average signal
	boolean shiftl			= false;	//plot the shifting line
	boolean shiftc 			= false;	//plot the shifted signal
	boolean	erasec			= false;	//plot the erased signal
	
	boolean spaveragec		= false;	//plot the space averaged signal
	boolean startmovie		= false;	//start movie plot
	
	//To normalize the plot
	int origmin	= -10, origmax = 10, unwmin	= -10,  unwmax = 10;
	int nor1min = -10, nor1max = 10, tavnmin = -10, tavnmax = 10; 
	int shifmin = -10, shifmax = 10, nor2min = -10, nor2max = 10;
	int avermin = -10, avermax = 10, spamin = -10,  spamax = 10;
	
	
	
	//Loading file declarations
	TextField 	displayFilename = new TextField(45);
	FileDialog	f 				= new FileDialog(this, "Load a File", FileDialog.LOAD);
	String		namefilel;
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

	
	//Analysis matrices and parameters
	int nf        				= 128;							
	int ntimes    				= 3000;
	int nii;			
	int njj;
	
	int u[][][]      			= new int [nf][nf][ntimes];
	int max[][]      			= new int [nf][nf];
	int min[][]      			= new int [nf][nf];
	short s1min[][]				= new short [nf][nf];
	short s1max[][]				= new short [nf][nf];
	short s2min[][]				= new short [nf][nf];
	short s2max[][]				= new short [nf][nf];
	short s3min[][]				= new short [nf][nf];
	short s3max[][]				= new short [nf][nf];
	short s4min[][]				= new short [nf][nf];
	short s4max[][]				= new short [nf][nf];
	short s5min[][]				= new short [nf][nf];
	short s5max[][]				= new short [nf][nf];
	
	short original[][][]  		= new short [nf][nf][ntimes];
	short unwrapped[][][]		= new short [nf][nf][ntimes];
	short taver[][][]			= new short [nf][nf][ntimes];
	short shifted[][][]			= new short [nf][nf][ntimes];
	short erased[][][]			= new short [nf][nf][ntimes];
	short spaceaveraged[][][] 	= new short [nf][nf][ntimes];
	
	short basev1[][][]			= new short [nf][nf][ntimes];
	int ntll[][] 				= new int [nf][nf]; //-> change to short
	
	short smin[][][]			= new short [nf][nf][1000];
	short smint[][][]			= new short [nf][nf][1000];
	
	int intervsh1[][]			= new int [nf][nf];
	int intervsh2[][]			= new int [nf][nf];
	
	int freq, ifreq;						//Shift frequency
	int norma 					= 1000;		//Normalization
	int	ertime1;							//Erasing starting intervall
	int ertime2;							//Erasing ending intervall
	
	float xcoef[][] 			= new float [13][13];
	float xcoeft 				= 0;		//Space average parameter

	
	
	
	//Screen dimension and plot position 
	short lsx 	= 1050;						//screen width
	short lsy 	= 700;						//screen height
	short pw	= 640;						//plot width
	short ph	= 350;						//plot height
	short phm	= 200;						//plot height with movie
	short dpx	= 90;						//Distance from x window boundary
	short dpy	= 90;						//Distance from y window boundary
	short da 	= 35;						//Distance from plot boundary
	
	short ss;								//square side
	short dsx;								//Distance from x window boundary
	short dsy;								//Distance from graph boundary	
	
	short axp1	= (short) (dpx + da);		//x axes point 1
	short ayp1	= (short) (dpy + da);		//y axes point 1
	short axp2	= (short) (dpx + da);		//x axes point 2
	short ayp2	= (short) (dpy + ph - da);	//y axes point 2
	short axp3	= (short) (dpx + pw - da);	//x axes point 3
	short ayp3	= (short) (dpy + ph - da);	//y axes point 3
		
	
	
	//Movie declarations
	IndexColorModel myColorModel1 = null;
	IndexColorModel myColorModel2 = null;
	IndexColorModel myColorModel3 = null;
	Image image1,image2;
	
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
	
	boolean down;

	int pix[] 		= new int [nf*nf];	
	
	//Thread
	Thread  thread;
	
	//*************************************************************
	public static void main (String[] arguments){		
		
		graphic graph = new graphic();
		
	}//close main
	//*************************************************************
	public void  actionPerformed( ActionEvent ae ){
	
		//Loading file
		{
			
			if (ae.getSource() == openButton) {
				f.setDirectory("2010_feb16_temp_endo_010.dat");
				f.setVisible(true);
				String s = f.getDirectory();
				s += f.getFile();
				displayFilename.setText(s);
				namefilel = s;
			}
			else if (ae.getSource() == errorButton) IOError.dispose();
			
		}

		//Start
		{
			
			if (ae.getSource() == start && start.getLabel().equals("Start")) {
				beginAnalyze = true;
				start.setLabel("Reset");
				repaint();
			}
			else {
				if (ae.getSource() == start && start.getLabel().equals("Reset")) {
					beginAnalyze = false;
					start.setLabel("Start");
					dispose();
					repaint();
				}
			}
			
		}
		
		//Set parameters
		{
			
			if (ae.getSource() == setpoint) {
				repaint();
				beginAnalyze 	= true;
			}
			if (ae.getSource() == setshift) {
				repaint();
				//beginAnalyze 	= true;
				shiftfirsttime	= true;
			}
			if (ae.getSource() == seterase) {
				repaint();
				//beginAnalyze 	= true;
				erasefirsttime	= true;
			}
			
		}
		
		//Space average
		{
			if (ae.getSource() == spaaverage) {
				spacefirsttime = true;
			}
			else {
				spacefirsttime = false;
			}

		}
		
		//Movie
		{
			if (ae.getSource() == movie && movie.getLabel().equals("Movie")) {
				startmovie = true;
				movie.setLabel("Stop");
				repaint();
			}
			else {
				if (ae.getSource() == movie && movie.getLabel().equals("Stop")) {
					startmovie = false;
					movie.setLabel("Movie");
					repaint();				
				}
			}

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
		

	}//close actionPerformed
	
	//*************************************************************
	public void itemStateChanged(ItemEvent ie ){
	
	
		//Choice of the signal to plot
		if (signal1.getState() 	== true) 	originc = true;
		else 								originc = false;
		
		if (signal2.getState() 	== true) 	unwrapc = true;
		else 								unwrapc = false;
		
		if (signal3.getState() 	== true) 	tavec = true;
		else 								tavec = false;
		
		if (signal4.getState()	== true) 	shiftl = true;
		else 								shiftl = false;
		
		if (signal5.getState() 	== true) 	{
											//setBackground(background);
											signal3.setState(false);
											tavec = false;
											signal4.setState(false);
											shiftl = false;		
											repaint();									
											shiftc = true;
		}
		else 								shiftc = false;
		
		if (signal6.getState() 	== true) 	erasec = true;
		else 								erasec = false;
		
		if (signal7.getState() 	== true) 	{
											repaint();
											spaveragec = true;
		}
		else 								spaveragec = false;
		
		
		
		//Choice for the color map
		nmap = colormap.getSelectedIndex();
		
		
		repaint();
		
	}//Close itemStateChanged
	//*************************************************************
	
	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void mousePressed(MouseEvent me){
		
		p = me.getPoint();
		nii = (int)(p.x - norig);
		njj = (int)(p.y - norig);
		/*
		if(nbstim==0 && nbsignal==1){
			
			iipx = (int)(p.x - 10 - norig);
			iipy = (int)(p.y - 60 - norig);
			nok1=0;
			
			if(200 - iipx > 0 && 200 - iipy > 0 && iipx >= 0 && iipy >=0){
				ipx = (int)(p.x - 10 - norig);
				ipy = (int)(p.y - 60 - norig);
				nok1=1;
				nxsignal = 0;
				for(int i = 0; i < 90; i++){
					usig[i]=0;
                	vsig[i]=0;          
				}
			}
		}
		if(nbstim == 1){
			iipx = (int)(p.x - 10 - norig);
			iipy = (int)(p.y - 60 - norig);
			nok=0;
          	if(gsize - iipx > 0 && gsize - iipy > 0){
            	nok=1;
			}
		}
		*/
		
		down=true;
		
	}
	public void mouseReleased(MouseEvent me){
		down = false;
	}
	
	//*************************************************************
	public class Close extends WindowAdapter {
		
		public void windowClosing(WindowEvent we){
			
			if (closewindow = true) waitwindow.dispose();
		}
		
	}//close Close class
	//*************************************************************
	

	//*************************************************************			
	public graphic() {
		
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
		note		= new TextArea(4,65);
		
		//Buttons
		start 		= new Button("Start");
		stop 		= new Button("Stop ");
		reset 		= new Button("Reset");
		save  		= new Button("Save ");
		movie 		= new Button("Movie");
		close 		= new Button("Close");
		setpoint	= new Button("Set");
		setshift	= new Button("Set");
		seterase	= new Button("Set");
		spaaverage	= new Button("Performe");
		
		start.addActionListener ( this );	
		stop.addActionListener ( this );
		reset.addActionListener ( this );
		save.addActionListener ( this );
		movie.addActionListener ( this );
		close.addActionListener ( this );
		setpoint.addActionListener ( this );
		setshift.addActionListener ( this );
		seterase.addActionListener ( this );
		spaaverage.addActionListener ( this );
		
		
		//if (beginAnalyze) add(new Label("Wait for processing:"));
		
		//Analysis conditions
		alternance 	= new Checkbox("Alternance", false);
		alternance.addItemListener	(this);
		saverage   	= new Checkbox("Space average", false);
		saverage.addItemListener	(this);
		
		aconditions = new Label("ANALYSIS CONDITIONS");
		
		point 		= new Label("Test point (x;y):", Label.LEFT);
		tnii 		= new TextField("60", 3);
		tnjj 		= new TextField("60", 3);
		
		tfreq 		= new TextField("180", 3);
		
		normat 		= new Label("Normalization:   ", Label.LEFT);
		cnorm		= new Choice();
		cnorm.addItem("[0:1000]");
		cnorm.addItem("[0:10000]");
		
		intervall	= new Label("Erase intervalls: ", Label.LEFT);
		erasesx		= new TextField("100", 3);
		erasedx 	= new TextField("100", 3);
		
		//Plot conditions
		pconditions = new Label("Signal plot conditions: ");
		signal1 	= new Checkbox("Original ", 		false);		
		signal2 	= new Checkbox("Unwrapped", 		false);
		signal3 	= new Checkbox("Taveraged", 		false);
		signal4 	= new Checkbox("Shift Line  ", 		false);
		signal5 	= new Checkbox("Shifted ", 			false);
		signal6 	= new Checkbox("Erased ", 			false);
		signal7 	= new Checkbox("Space averaged ", 	false);
		signal1.addItemListener( this );
		signal2.addItemListener( this );
		signal3.addItemListener( this );
		signal4.addItemListener( this );	
		signal5.addItemListener( this );
		signal6.addItemListener( this );
		signal7.addItemListener( this );
			
		openButton.addActionListener( this );
		
		//Color Choice
		colormap =  new Choice();
		colormap.addItem("Full scale    ");
		colormap.addItem("Reversed scale");
		colormap.addItem("Gray scale    ");
		colormap.addItemListener( this );
		
		
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
				myColorModel1 = new IndexColorModel(1, 228, r, g, b);
				
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
				myColorModel1 = new IndexColorModel(1, 228, r, g, b);
				
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
		
			
		// ----- thread ----
		{		
			
				
		}
		
		
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
			rowS.setLayout(new FlowLayout(FlowLayout.LEFT));
			rowS.add	( comment );
			rowS.add	( note );
			rowS.add 	( close );
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
			Panel rowE = new Panel();				//General west layout
			rowE.setLayout(new GridLayout(15,1));
			
			Panel rowE11 = new Panel();				//Break
			rowE11.add	( new Label(" * *********************** *"));
			rowE.add	( rowE11 );
			
			Panel rowE1 = new Panel();				//Title
			rowE1.add	( aconditions );
			rowE.add	( rowE1 );
			
			Panel rowE4 = new Panel();				//Test point
			rowE4.setLayout(new FlowLayout(FlowLayout.LEFT));
				//rowE4.setLayout(new GridLayout(1,4, 20, 20));
			rowE4.add	( point );
			rowE4.add	( tnii );
			rowE4.add	( tnjj );
			rowE4.add	( setpoint );
			rowE.add	( rowE4 );
			
			Panel rowE3 = new Panel();				//Normalization
			rowE3.setLayout(new FlowLayout(FlowLayout.LEFT));
				//rowE3.setLayout(new GridLayout(1,2, 20, 20));
			rowE3.add	( normat );
			rowE3.add	( cnorm );
			rowE.add	( rowE3 );
			
			Panel rowE2 = new Panel();				//Shift freq
			rowE2.setLayout(new FlowLayout(FlowLayout.LEFT));
				//rowE2.setLayout(new GridLayout(1,3, 20, 20));
			rowE2.add	( new Label("Shift frequency:            ") );
			rowE2.add	( tfreq );
			rowE2.add	( setshift );
			rowE.add	( rowE2 );
			
			Panel rowE40 = new Panel();				//Erasing intervall
			rowE40.setLayout(new FlowLayout(FlowLayout.LEFT));
				//rowE40.setLayout(new GridLayout(1,4, 20, 20));
			rowE40.add	( intervall );
			rowE40.add	( erasesx );
			rowE40.add	( erasedx );
			rowE40.add	( seterase );
			rowE.add	( rowE40 );
			
			Panel rowE41 = new Panel();				//Break
			rowE41.add	( new Label(" * --------------------------- *"));
			rowE.add	( rowE41 );
			
			Panel rowE42 = new Panel();				//Title for plot conditions
			rowE42.setLayout(new FlowLayout(FlowLayout.CENTER));
			rowE42.add	( pconditions );
			rowE.add	( rowE42 );
			
			Panel rowE5 = new Panel();				//Original and unwrapped
			rowE5.setLayout(new GridLayout(1,2));
			rowE5.add	( signal1 );
			rowE5.add	( signal2 );
			rowE.add	( rowE5 );
			
			Panel rowE6 = new Panel();				//Time aver and shifting line
			rowE6.setLayout(new GridLayout(1,2));
			rowE6.add	( signal3 );
			rowE6.add	( signal4 );
			rowE.add	( rowE6 );
			
			Panel rowE7 = new Panel();				//Shifted and erased
			rowE7.setLayout(new GridLayout(1,2));
			rowE7.add	( signal5 );
			rowE7.add	( signal6 );
			rowE.add	( rowE7 );
			
			Panel rowE80 = new Panel();				//Break
			rowE80.add	( new Label(" * --------------------------- *"));
			rowE.add	( rowE80 );
			
			Panel rowE81 = new Panel();				//Space averaged
			//rowE81.setLayout(new GridLayout(1,2));
			rowE81.setLayout(new FlowLayout(FlowLayout.LEFT));
			rowE81.add	( signal7 );
			rowE81.add	( spaaverage );
			rowE.add	( rowE81 );
			
			Panel rowE82 = new Panel();				//Color map
			//rowE82.setLayout(new GridLayout(1,2));
			rowE82.setLayout(new FlowLayout(FlowLayout.LEFT));
			rowE82.add	( new Label("Color map"));
			rowE82.add	( colormap );
			rowE.add	( rowE82 );
			
			Panel rowEf = new Panel();				//Break
			rowEf.add	( new Label(" * *********************** *"));
			rowE.add	( rowEf );
			
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
	
	//public void update (Graphics g){ paint(g); }
	
	//*************************************************************
	int pp1 = 0;
	int pp2 = 0;
	int kk1 = 0;
	int kk2 = 0;
	
	int pixels[];
	MemoryImageSource source;
	
	public void paint(Graphics g) {
			
		//Graph dimensions without movie
		{
			if (startmovie == false) {
				
				pw	= 640;						//plot width
				ph	= 350;						//plot height
				dpx	= 90;						//Distance from x window boundary
				dpy	= 90;						//Distance from y window boundary
				da 	= 35;						//Distance from plot boundary
				
				axp1	= (short) (dpx + da);		//x axes point 1
				ayp1	= (short) (dpy + da);		//y axes point 1
				axp2	= (short) (dpx + da);		//x axes point 2
				ayp2	= (short) (dpy + ph - da);	//y axes point 2
				axp3	= (short) (dpx + pw - da);	//x axes point 3
				ayp3	= (short) (dpy + ph - da);	//y axes point 3
				
					//Graph plot
				g.setColor(Color.black);
				g.fillRect(dpx, dpy, pw, ph);
				g.setColor(Color.white);
				g.drawLine(axp1,ayp1,axp2,ayp2);
				g.drawLine(axp2,ayp2,axp3,ayp3);
				
				g.drawString("Time (ms)", pw + 12, ph + dpy - da/2 + 6);
				g.drawString("V (mV)", dpx + da/2 - 5, dpy + da/2 + 2);
			}
			
		}
				
		//Take analysis parameters: 
		{
			//Test point
			if (startmovie == false) {
				double nnii = Double.valueOf(tnii.getText()).doubleValue();
				double nnjj = Double.valueOf(tnjj.getText()).doubleValue();
				nii = (short)(nnii);
				njj = (short)(nnjj);
			}
				
			//Shifting frequency
			double ffreq = Double.valueOf(tfreq.getText()).doubleValue();
			freq = (int)(ffreq);
			ifreq = freq/3;
			
			//Erasing intervalls
			double nerasesx = Double.valueOf(erasesx.getText()).doubleValue();
			double nerasedx = Double.valueOf(erasedx.getText()).doubleValue();
			ertime1 = (int)(nerasesx);
			ertime2 = (int)(nerasedx);
			
		}//end take parameters
		
		//Spave average coefficients
		{
			for (int i = 0; i <= 12; i++){
			for (int j = 0; j <= 12; j++){
					
				int ii = i - 6;
				int jj = j - 6;
					
				//Maintain all the parenthesis
				xcoef[i][j] = (float)(9*Math.exp(-( (ii*ii) + (jj*jj) )/8));
				
				xcoeft = xcoeft + xcoef[i][j];
			}
			}
			
			xcoeft = 1/xcoeft;
		}
		
		//Read data - Analysis codes - Plot pointwise signals
		if (beginAnalyze) {
		
			//**************************************************************
			//--> if possible make the looop as "i" - "j" - "k"
			//**************************************************************
			
			//**************************************************************
			//Read data and standard analysis
			if (firsttime) {
				
				try {
					note.setText("Loading file. ");
					oldText = note.getText();
					cascade_import data = new cascade_import();
					int [][][] video = data.readVideoFile(namefilel);
					//End reading data
					
					//**********************************************************
					//Standard analysis calculations
					{
					
					note.setText(oldText + " " + "Starting analysis.  Unwrapping. ");
					oldText = note.getText();
					
					for (int i = 0; i < 128; i++){
					for (int j = 0; j < 128; j++){
					
						max[i][j] = -32768;
						min[i][j] = 32767;
												
						for (int k = 0; k < ntimes; k++) {
													
							//Make constant the last times
							if (k < 10) video[k][i][j] = video[10][i][j];
							if (k > (ntimes - 10)) video[k][i][j] = video[ntimes - 10][i][j];
							
							//copy the data
							u[i][j][k] 			= -video[k][i][j];
							original[i][j][k] 	= (short)(u[i][j][k]);
							unwrapped[i][j][k] 	= original[i][j][k];
							taver[i][j][k]		= original[i][j][k];
							
							//max - min original signal
							
							if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
							if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
						}
						
						s1min[i][j] = (short)(min[i][j]);
						s1max[i][j] = (short)(max[i][j]);
						
						//Unwrapping
						for (int k = 10; k < ntimes - 10; k++) {
						
							if ( Math.abs(max[i][j] - min[i][j]) > 25000 ){
								u[i][j][k] = u[i][j][k] - 20000;
								//save the unwrapped signal
								unwrapped[i][j][k] = (short)(u[i][j][k]);
								//Unwrapped max - min
								if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
								if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
							}
						}

						s2min[i][j] = (short)(min[i][j]);
						s2max[i][j] = (short)(max[i][j]);
						
					}
					}
					note.setText(oldText + "\n" + "Original.         Max - min: [" + s1max[nii][njj] + ", " + s1min[nii][njj] + "]. ");
					oldText = note.getText();
					note.setText(oldText + " " + " New Max - min: [" + s2max[nii][njj] + ", " + s2min[nii][njj] + "]. ");
					oldText = note.getText();
					}//end starting analysis
					

					/*
					//**********************************************************
					
					//NORMALIZING [0:1000]
									//choose normalization: norma
					
					note.setText(oldText + "\n" + "Norm: " + "[0:1000].");
					oldText = note.getText();
					//int norma 	= 1000;
					for (int i = 0; i < 128; i++){
					for (int j = 0; j < 128; j++){
						for (int k = 0; k < ntimes; k++) {
							if ((max[i][j] - min[i][j]) == 0) u[i][j][k] = 0;
							else{
								u[i][j][k] = norma*(u[i][j][k] - min[i][j])/(max[i][j] - min[i][j]);
							}
						}
					}
					}
					
					
					//Max and min of the normalized original signal
					note.setText(oldText + " " + "Max & min normalized: ");
					oldText = note.getText();
					for (int i = 0; i < 128; i++){
						for (int j = 0; j < 128; j++){
							max[i][j] = -9999;
							min[i][j] = 9999;
							
							for (int k = 20; k < ntimes - 10; k++){
								if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
								if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
							}
						}
					}
					//To plot the normalized signal
					nor1min = min[nii][njj];
					nor1max = max[nii][njj];
					note.setText(oldText + " " + nor1max + ", " + nor1min);
					oldText = note.getText();
					//End normalization 
					*/
					
					//**********************************************************
				
					
					//TIME AVERAGE 7 points
					note.setText(oldText + "\n" + "Time average. Max - min: ");
					oldText = note.getText();
					

					
					for (int i = 0; i < 128; i++){
					for (int j = 0; j < 128; j++){
					
						max[i][j] = -32768;
						min[i][j] = 32767;
						
						int averp = 0;
						int averm = 0;
						int averr = 0;
						
						//Time average
						for (int nt = 10; nt < ntimes - 10; nt++){
						
							if (nt == 10) {
								for	(int nn = 1; nn <= 3; nn++){
									
									averp = averp + u[i][j][nt + nn];
									averm = averm + u[i][j][nt - nn];
									
									u[i][j][nt] = (u[i][j][nt] + averp + averm)/7;
								}
							}
							else {
								averp = averp + u[i][j][nt + 3];
								averm = averm - u[i][j][nt - 4];
							
								u[i][j][nt] = (u[i][j][nt - 1] + averp + averm)/7;
							}
						
							//taver[i][j][nt] = (short)(u[i][j][nt]);
						
							if (u[i][j][nt] < min[i][j]) min[i][j] = u[i][j][nt];
							if (u[i][j][nt] > max[i][j]) max[i][j] = u[i][j][nt];
						}
						
						s3min[i][j] = (short)(min[i][j]);
						s3max[i][j] = (short)(max[i][j]);
					}
					}
					
					note.setText(oldText + " [" + s3max[nii][njj] + ", " + s3min[nii][njj] + "]. ");
					oldText = note.getText();
					
					
					//Make the signal positive
					note.setText(oldText + "  " + "Make positive.   Max - min: ");
					oldText = note.getText();
					
					for (int i = 0; i < 128; i++){
					for (int j = 0; j < 128; j++){
					
						max[i][j] = -32768;
						min[i][j] = 32767;
					
						for (int k = 10; k < ntimes - 10; k++) {
						
					 		u[i][j][k] = u[i][j][k] - (int)(s3min[i][j]);
					 		
							taver[i][j][k] = (short)(u[i][j][k]);
							
					 		//New max - min
					 		if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
					 		if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
							
					 	}
						
						s3min[i][j] = (short)(min[i][j]);
						s3max[i][j] = (short)(max[i][j]);
					}
					}
					 
					note.setText(oldText + " [" + s3max[nii][njj] + ", " + s3min[nii][njj] + "]. ");
					oldText = note.getText();
					
					 
					//**********************************************************
										
					
				}//close try
				
				catch (IOException e) { 
				
					/*
				 	IOError.setSize(200,100);
				 	IOError.add(errorButton);
				 	errorButton.addActionListener( this );
				 	IOError.setVisible(true);
				 	*/
				
					System.out.println("IOException while reading file.");
					System.exit(0);
				}//close catch
				
				oldText = note.getText();
				
				firsttime	= false;
				
			}//end read and standard analysis
			//**************************************************************
	
			//**************************************************************
			//Shifting code
			if (shiftfirsttime) {
				
				//SUBTRACT DRIFT by minima
				note.setText(oldText + "\n" + "Shifting.            ");
				oldText = note.getText();
				
				for (int i = 0; i < 128; i++){
				for (int j = 0; j < 128; j++){
					ntll[i][j] = 0;
					for (int k = 0; k < ntimes; k++){
					
						if (k < 1000) smin[i][j][k] = (short)(32767);
						
						basev1[i][j][k] = s3min[i][j];
						shifted[i][j][k] = (short)(s3min[i][j]);
						
					}
				}
				}
				
				//Find minima and timing
				for (int nt = ifreq; nt < ntimes - ifreq; nt = nt + ifreq) {
										
					for (int i = 0; i < 128; i++) {
					for (int j = 0; j < 128; j++) {

						ntll[i][j]++;

						
						for (int nn = nt - ifreq; nn < nt + ifreq; nn++) {

							if(u[i][j][nn] < (int)(smin[i][j][ntll[i][j]])) {   
							
								smin[i][j][ntll[i][j]] 	= (short)(u[i][j][nn]);  //minimum in ifreq
								smint[i][j][ntll[i][j]] = (short)(nn);			 //time of minimum
								
								//Take the last time of minima -> intervsh2 for shifting line
								intervsh2[i][j] = (int)(smint[i][j][ntll[i][j]]);
							}
						}						
					}
					}
				}
				
				//Take the first time of minima -> intervsh1 for shifting line
				for (int i = 0; i < 128; i++) {
				for (int j = 0; j < 128; j++) {
				
					boolean vero = true;
					
					for (int k = 0; k < 20; k++) {
					
						if (smint[i][j][k] > 0 && vero == true) {
							intervsh1[i][j] = (int)(smint[i][j][k]);
							vero = false;
						}
					}
					
				}
				}
				
				//Interpolation of the minima and shift
				double basev, fraq;
				int idis = 0, idist = 1, idist_old = 1;
				
				/*
				System.out.println("smint " + smint[nii][njj][0] + "   2*ifreq " + 2*ifreq);
				System.out.println("smint " + smint[nii][njj][1] + "   2*ifreq " + 2*ifreq);
				System.out.println("smint " + smint[nii][njj][2] + "   2*ifreq " + 2*ifreq);
				System.out.println("intervsh1 " + intervsh1[nii][njj]);				
				
				System.out.println("smint " + smint[nii][njj][ntll[nii][njj]] + "  ntimes - 2*ifreq " + (ntimes - 2*ifreq));
				System.out.println("smint " + smint[nii][njj][ntll[nii][njj] - 1] + "  ntimes - 2*ifreq " + (ntimes - 2*ifreq));
				System.out.println("intervsh2 " + intervsh2[nii][njj]);
				*/
				
				for (int i = 0; i < nf; i++){
				for (int j = 0; j < nf; j++){
				
					ntll[i][j] = 1;	
				
					for (int nt = intervsh1[i][j]; nt < intervsh2[i][j]; nt++){
							
						if ( nt > (int)(smint[i][j][ntll[i][j]]) ) ntll[i][j]++;
							
						//Difference every two next minima starting from ntll = 1, ntll = 0
						idis = 	(int)(smin[i][j][ntll[i][j]] - smin[i][j][ntll[i][j] - 1]);
							
						//Delta t for every two minima
						idist_old = idist;
						idist = (int)(smint[i][j][ntll[i][j]] - smint[i][j][ntll[i][j] - 1]); 
							
						//Avoid 0 values
						if (idist == 0) idist = idist_old;
							
						//LINEAR INTERPOLATION between two minima
						fraq = (double)(( (double)(nt) - (double)(smint[i][j][ ntll[i][j] - 1 ] ))/(double)(idist));
						basev = (double)((double)(smin[i][j][ntll[i][j] - 1])*(1.0 - fraq) + (double)(smin[i][j][ntll[i][j]])*fraq);
							
						//Shifting line for the chosen point
						basev1[i][j][nt] = (short)(basev);
						
							
						//Subtraction of the drift
						u[i][j][nt] = u[i][j][nt] - (int)(basev);
						shifted[i][j][nt] = (short)(u[i][j][nt]);
						spaceaveraged[i][j][nt] = (short)(u[i][j][nt]);
							
					}
				}	
				}		
				
				//Max and min of the shifted signal
				note.setText(oldText + " Max - min: ");
				oldText = note.getText();
			
				for (int i = 0; i < 128; i++){
				for (int j = 0; j < 128; j++){
					for (int k = intervsh1[i][j] + 1; k < intervsh2[i][j] - 1; k++){
					
						if (k == intervsh1[i][j] + 10) {
							max[i][j] = -32768;
							min[i][j] = 32767;
						}
					
						//Avoid negative values
						if (u[i][j][k] < 0) u[i][j][k] = 0;
					
						if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
						if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
					}
					
					s4min[i][j] = (short)(min[i][j]);
					s4max[i][j] = (short)(max[i][j]);
					
					s5min[i][j] = (short)(min[i][j]);
					s5max[i][j] = (short)(max[i][j]);
				}
				}
				
				note.setText(oldText + " [" + s4max[nii][njj] + ", " + s4min[nii][njj] + "]. ");
				oldText = note.getText();
				
				
				/*
				//Normalizing the shifted signal
				note.setText(oldText + " " + "Norm. ");
				oldText = note.getText();
					//int norma 	= 1000;
				for (int k = ifreq; k < ntimes - ifreq - 10; k++) {
					for (int j = 0; j < 128; j++){
						for (int i = 0; i < 128; i++){
							if ((max[i][j] - min[i][j]) == 0) {
								u[i][j][k] = 0;
							}
							else{
								u[i][j][k] = norma*(u[i][j][k] - min[i][j])/(max[i][j] - min[i][j]);
							}
						}
					}
				}//end of normalization
				
				
				//Signal to plot
				for (int i = 0; i < 128; i++) {
					for (int j = 0; j < 128; j++) {
						for (int nt = 0; nt < ntimes; nt++) {
							shifted[i][j][nt] = (short)(u[i][j][nt]);
						}
					}
				}
				
				//Max and min of the normalized shifted signal
				note.setText(oldText + " " + "Max & min normalized shifted: ");
				oldText = note.getText();
				for (int j = 0; j < 128; j++){
					for (int i = 0; i < 128; i++){
						max[i][j] = -9999;
						min[i][j] = 9999;
					}
				}
				for (int j = 0; j < 128; j++){
					for (int i = 0; i < 128; i++){
						for (int k = ifreq; k < ntimes - ifreq - 10; k++){
							if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
							if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
						}
					}
				}
				*/
				
				shiftfirsttime = false;
			}
			//close Shifting code
			//**************************************************************
			
			//**************************************************************
			//Erasing code
			if (erasefirsttime) {
				
				for (int i = 0; i < 128; i++){
					for (int j = 0; j < 128; j++){
						for (int t = 0; t < ertime1; t++) {
							u[i][j][t] = 1;
						}
						for (int t = ntimes - ertime2; t < ntimes; t++) {
							u[i][j][t] = 1;
						}
						for (int t = 0; t < ntimes; t++) {
							erased[i][j][t] = (short)u[i][j][t];
						}
					}
				}
				erasefirsttime = false;
			}
			//end erasing
			//**************************************************************
			
			//**************************************************************
			//Space average
			
			if (spacefirsttime) {
			
				note.setText(oldText + "\n" + "Space average. ");
				oldText = note.getText();
				
				float xlap = 0;
				
				for (int i = 3; i < (nf - 15); i++){
				for (int j = 3; j < (nf - 15); j++){
					
					max[i][j] = -32768;
					min[i][j] = 32767;
						
					for (int k = intervsh1[i][j]; k < intervsh2[i][j]; k++){
								
						xlap = 0;
								
						for (int ii = 0; ii <= 12; ii++){
						for (int jj = 0; jj <= 12; jj++){
								
							xlap = xlap + (float)(u[i+ii][j+jj][k])*xcoef[ii][jj];
						}
						}
								
						u[i][j][k] = (int)(xlap*xcoeft);
						spaceaveraged[i][j][k] = (short)(u[i][j][k]);
							
						if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
						if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
							
					}
						
					s5min[i][j] = (short)(min[i][j]);
					s5max[i][j] = (short)(max[i][j]);
						
				}
				}
			
				/*
				//Normalizing the space averaged signal
				note.setText(oldText + " " + "Norm. ");
				oldText = note.getText();
				
				
				for (int i = 0; i < nf; i++){
				for (int j = 0; j < nf; j++){
					for (int k = ifreq; k < ntimes - ifreq - 10; k++) {
						if ((max[i][j] - min[i][j]) == 0) {
							u[i][j][k] = 0;
						}
						else{
							u[i][j][k] = norma*(u[i][j][k] - min[i][j])/(max[i][j] - min[i][j]);
						}
					}
					}
				}//end of normalization
				
				
				//Signal to plot
				for (int i = 0; i < 128; i++) {
				for (int j = 0; j < 128; j++) {
					for (int nt = 0; nt < ntimes; nt++) {
						spaceaveraged[i][j][nt] = (short)(u[i][j][nt]);
					}
				}
				}
				
				
				//Max and min of the space averaged normalized signal
				note.setText(oldText + " " + "Max & min space averaged: ");
				oldText = note.getText();
				
				for (int i = 0; i < nf; i++){
					for (int j = 0; j < nf; j++){
						max[i][j] = -32768;
						min[i][j] = 32767;
					}
				}
				for (int j = 0; j < 128; j++){
					for (int i = 0; i < 128; i++){
						for (int k = ifreq; k < ntimes - ifreq - 10; k++){
							if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
							if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
						}
					}
				}
				*/
				
				note.setText(oldText + " [" + s5max[nii][njj] + ", " + s5min[nii][njj] + "]. ");
				oldText = note.getText();
				
				System.out.println("End of space average.");

				spacefirsttime = false;
			
			}//end space average
			//**************************************************************
			
			
			//Original -> signal1
			if (originc) {
			
				origmin = (int)(s1min[nii][njj]);
				origmax = (int)(s1max[nii][njj]);
				
				g.setColor(Color.red);
				
				g.drawString("OrSi", pw/3, dpy + 20);
				oldText = note.getText();
				
				for (int t = 10; t < ntimes - 10; t++) {
					
					pp1 = dpx + da + t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
					
					if (startmovie == false) {
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 	= (ph - 2*da)*(original[nii][njj][t] 	 - origmin)/(origmax - origmin);
						kk2 	= (ph - 2*da)*(original[nii][njj][t + 1] - origmin)/(origmax - origmin);
					}
					else {
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1 	= (phm - 2*da)*(original[nii][njj][t] 	  - origmin)/(origmax - origmin);
						kk2 	= (phm - 2*da)*(original[nii][njj][t + 1] - origmin)/(origmax - origmin);
					}

					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
				}
				originc = false;
			}//end original	
			
			//Unwrapped -> signal2
			if (unwrapc) {
				
				unwmin = (int)(s2min[nii][njj]);
				unwmax = (int)(s2max[nii][njj]);
				
				g.setColor(Color.yellow);
				g.drawString("UnSi", pw/3 + da + 2, dpy + 20);
				
				for (int t = 10; t < ntimes - 10; t++) {
				
					if (startmovie == false) {
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 	= (ph - 2*da)*(unwrapped[nii][njj][t] 	  - unwmin)/(unwmax - unwmin);
						kk2 	= (ph - 2*da)*(unwrapped[nii][njj][t + 1] - unwmin)/(unwmax - unwmin);
					}
					else {
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1	 	= (phm - 2*da)*(unwrapped[nii][njj][t] 	  - unwmin)/(unwmax - unwmin);
						kk2 	= (phm - 2*da)*(unwrapped[nii][njj][t + 1] - unwmin)/(unwmax - unwmin);
					}
					
					pp1 = dpx + da + t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
					
					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
				}
				unwrapc = false;
			}//end unwrapped 

			//Time averaged -> signal3
			if (tavec) {
				
				tavnmin = (int)(s3min[nii][njj]);
				tavnmax = (int)(s3max[nii][njj]);
				
				//System.out.println( s3min[nii][njj] + " " + s3max[nii][njj] + " " + tavnmin + " " + tavnmax);
				
				g.setColor(Color.green);
				
				g.drawString("TASi", pw/3 + 2*da + 3, dpy + 20);
				oldText = note.getText();
				
				for (int t = 10; t < ntimes - 10; t++) {
				
					if (startmovie == false) {
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 	= (ph - 2*da)*(taver[nii][njj][t] 	  - tavnmin)/(tavnmax - tavnmin);
						kk2 	= (ph - 2*da)*(taver[nii][njj][t + 1] - tavnmin)/(tavnmax - tavnmin);
					}
					else {
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1 	= (phm - 2*da)*(taver[nii][njj][t] 	   - tavnmin)/(tavnmax - tavnmin);
						kk2 	= (phm - 2*da)*(taver[nii][njj][t + 1] - tavnmin)/(tavnmax - tavnmin);
					}
					
					pp1 = dpx + da + 	   t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
					
					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
				}
				tavec = false;
			}//end time averaged 
			
			//Shifted line -> signal4 
			if (shiftl) {
				
				tavnmin = (int)(s3min[nii][njj]);
				tavnmax = (int)(s3max[nii][njj]);
				
				g.setColor(Color.yellow);
				g.drawString("ShLn", pw/3 + 3*da + 4, dpy + 20);

				for (int t = 10; t < ntimes - 10; t++) {
					
					if (startmovie == false) {
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 = (ph - 2*da)*(basev1[nii][njj][t] 	   - tavnmin)/(tavnmax - tavnmin);
						kk2 = (ph - 2*da)*(basev1[nii][njj][t + 1] - tavnmin)/(tavnmax - tavnmin);
					}
					else {
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1 = (phm - 2*da)*(basev1[nii][njj][t]	    - tavnmin)/(tavnmax - tavnmin);
						kk2 = (phm - 2*da)*(basev1[nii][njj][t + 1] - tavnmin)/(tavnmax - tavnmin);
					}	
					
					pp1 = dpx + da + 	   t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
						
					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
					
				}
				shiftl = false;
			}//end shifted line
			
			//Shifted -> signal5
			if (shiftc) {
				
				shifmin = (int)(s4min[nii][njj]);
				shifmax = (int)(s4max[nii][njj]);
				
				g.setColor(Color.blue);
				g.drawString("ShSi", pw/3 + 4*da + 3, dpy + 20);
				
				for (int t = 10; t < ntimes - 10; t++) {
					
					if (startmovie == false) {
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 = (ph - 2*da)*(shifted[nii][njj][t] 	- shifmin)/(shifmax - shifmin);
						kk2 = (ph - 2*da)*(shifted[nii][njj][t + 1] - shifmin)/(shifmax - shifmin);
					}
					else {
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1 = (phm - 2*da)*(shifted[nii][njj][t] 	 - shifmin)/(shifmax - shifmin);
						kk2 = (phm - 2*da)*(shifted[nii][njj][t + 1] - shifmin)/(shifmax - shifmin);
					}
					
					pp1 = dpx + da + t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
					
					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
				}
				shiftc = false;
			}//end shift signal
					
			//Erased -> signal6
			if (erasec) {
				
				//Plot the erased signal
				g.setColor(Color.magenta);
				g.drawString("SeSi", pw/3 + 5*da + 4, dpy + 20);
				
				for (int t = 10; t < ntimes - 10; t++) {
					
					if (startmovie == false) {
					
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 = (ph - 2*da)*(erased[nii][njj][t]	   - shifmin)/(shifmax - shifmin);
						kk2 = (ph - 2*da)*(erased[nii][njj][t + 1] - shifmin)/(shifmax - shifmin);
					}
					else {
					
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1 = (phm - 2*da)*(erased[nii][njj][t]	    - shifmin)/(shifmax - shifmin);
						kk2 = (phm - 2*da)*(erased[nii][njj][t + 1] - shifmin)/(shifmax - shifmin);
					}
					
					pp1 = dpx + da + t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
					
					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
				}
					
			}//close erase
			
			//Space averaged -> signal7
			if (spaveragec) {
				
				spamin = (int)(s5min[nii][njj]);
				spamax = (int)(s5max[nii][njj]);
				
				g.setColor(Color.red);
				g.drawString("SpAvSi", pw/3 + 6*da + 4, dpy + 20);
				
				for (int t = 10; t < ntimes - 10; t++) {
					
					if (startmovie == false) {
						
						ayp2	= (short) (dpy + ph - da);	//y axes point 2
						ayp3	= (short) (dpy + ph - da);	//y axes point 3
						kk1 = (ph - 2*da)*(spaceaveraged[nii][njj][t]	  - spamin)/(spamax - spamin);
						kk2 = (ph - 2*da)*(spaceaveraged[nii][njj][t + 1] - spamin)/(spamax - spamin);
					}
					else {
						ayp2	= (short) (dpy + phm - da);	//y axes point 2
						ayp3	= (short) (dpy + phm - da);	//y axes point 3
						kk1 = (phm - 2*da)*(spaceaveraged[nii][njj][t]	   - spamin)/(spamax - spamin);
						kk2 = (phm - 2*da)*(spaceaveraged[nii][njj][t + 1] - spamin)/(spamax - spamin);
					}
					
					pp1 = dpx + da + t*(pw - 2*da)/(ntimes - 20);
					pp2 = dpy + da + (t + 1)*(pw - 2*da)/(ntimes - 20);
					
					g.drawLine(pp1, ayp2 - kk1, pp2, ayp2 - kk2);
				}
				
			}//close space average
			
		}//close begin analyze
		
		//Movie
		if (startmovie) {
			
			//Graph dimensions with movie
			{
				
				ayp2	= (short) (dpy + phm - da);	//y axes point 2
				ayp3	= (short) (dpy + phm - da);	//y axes point 3
				
					//Resized graph plot
				g.setColor(Color.black);
				g.fillRect(dpx, dpy, pw, phm);
				g.setColor(Color.white);
				g.drawLine(axp1,ayp1,axp2,ayp2);
				g.drawLine(axp2,ayp2,axp3,ayp3);
				
				g.drawString("Time (ms)", pw + 12, phm + dpy - da/2 + 6);
				g.drawString("V (mV)", dpx + da/2 - 5, dpy + da/2 + 2);
			}
			
			//Square movie dimensions
			{
				ss	= (short)(nf);						//square side
				dsx	= dpx;						//Distance from x window boundary
				dsy	= (short)(dpy + phm + 30);	//Distance from y window boundary
				
				g.setColor(Color.black);
				g.fillRect(dsx, dsy, ss + 2, ss + 2);
			}
			
			//Color table
			{
				if (nmap == 0) {
					for (int ii = 0; ii < maplength; ii += 2){
						int iii = ii;
						if(iii < 30) iii = 30;
						g.setColor(new Color(ir[iii], ig[iii], ib[iii]));
						g.fillRect(dsx + ss + 10, dsy + ss - ii/2 - 2, 10, 1);
					}
				}
				
				if (nmap == 1) {
					for (int ii = 0; ii < maplength; ii += 2){
						g.setColor(new Color(irr[ii], igg[ii], ibb[ii]));
						g.fillRect(dsx + ss + 10, dsy + ss - ii/2 - 2, 10, 1);
					}
				}
				
				if (nmap == 2) {
					for (int ii = 0; ii < maplength; ii += 2){
						g.setColor(new Color(irrr[ii], iggg[ii], ibbb[ii]));
						g.fillRect(dsx + ss + 10, dsy + ss - ii/2 - 2, 10, 1);
					}
				}
				
				g.setColor(Color.black);
				g.drawString(" V max", dsx + ss + 22, dsy + ss - maplength/2 + 10);
				g.drawString(" V min", dsx + ss + 22, dsy + ss - 2);
			}
			
			
			//Test point graph
			{
				g.setColor(Color.white);
				g.fillRect(nii + dsx + norig, njj + dsy + norig, 3, 3);
			}
			
			//Create images and movie
			{
				int width	= nf;
				int height	= nf;
				int size	= width * height;
				pixels		= new int [size];
				
				int value = getBackground().getRGB();
				for (int i = 0; i < size; i++) {
					pixels[i] = (byte)(u[nii][njj][60]);
				}
				
				if (nmap == 0)
					source = new MemoryImageSource(width, height, myColorModel1, pixels, 0, width);
				if (nmap == 1)
					source = new MemoryImageSource(width, height, myColorModel2, pixels, 0, width);
				if (nmap == 2)
					source = new MemoryImageSource(width, height, myColorModel3, pixels, 0, width);
				
				source.setAnimated(true);
				image1 = createImage(source);
				
				g.drawImage(image1,dsx + 1,dsy + 1,ss,ss, this); 
				
			}
		}
		
	}//close paint
	//*************************************************************
	
	/*
	public void run() {
		Thread me = Thread.currentThread( );
		me.setPriority(Thread.MIN_PRIORITY);
				
		while (true) {
			try {
				thread.sleep(10);
			} catch( InterruptedException e ) {
				return;
			}
					
			// Modify the values in the pixels array at (x, y, w, h)
				
			// Send the new data to the interested ImageConsumers
			//source.newPixels(x, y, w, h);
		}
	}
	*/
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
			
			
			
			
}//close class 













