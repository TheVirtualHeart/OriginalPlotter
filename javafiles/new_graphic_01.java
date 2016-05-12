/*
 *  new_graphic_01
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



public 	class 		new_graphic_01
		extends		Frame {
	
	//write the variables for Class
	String namefilel, Name;
	
	//Interface declarations
	private Button		start, stop, reset, save, movie, close, setpoint, setshift, seterase, spaaverage, apddi, thrset;
	private Checkbox 	alternance, saverage, norma1, norma2;
	private Checkbox 	signal1, signal2, signal3, signal4, signal5, signal6, signal7, signal8;
	private Label 		file, comment, point, intervall, normat, apddieval;
	private TextField 	filename, tnii, tnjj, tfreq, erasesx, erasedx, thr;
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
			
	boolean thresheval		= false;		//run the APD - DI code
			
			
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
			
	//To normalize the plot
	int origmin	= -10, origmax = 10, unwmin	= -10,  unwmax = 10;
	int nor1min = -10, nor1max = 10, tavnmin = -10, tavnmax = 10; 
	int shifmin = -10, shifmax = 10, nor2min = -10, nor2max = 10;
	int avermin = -10, avermax = 10, spamin = -10,  spamax = 10;
			
			
	
	
   	short [][][] video;
	
	
	
  	
	public static void main(String args[]) {
		
		//Create an instance of new_graphic_01 
		new_graphic_01 a = new new_graphic_01();
		
		//Assigning values to the variables in class new_graphic_01 
		/*
		//a.Name 		= "Vinod";
		//a.AccNumber = 467256282;
		//a.Bal 		= 635;
		*/
		
		
		//Definitions
		
		int 	nf = 128, ntimes = 3000, ifreq = 120;
		
        short 	u[][][] 		= new short [nf][nf][ntimes];
		short 	original[][][] 	= new short [nf][nf][ntimes];
		short 	unwrapped[][][]	= new short [nf][nf][ntimes];
		short 	taveraged[][][]	= new short [nf][nf][ntimes];
		short 	shifted[][][]	= new short [nf][nf][ntimes];
		short 	basev1[][][]	= new short [nf][nf][ntimes];
		short 	max[][] 		= new short [nf][nf];
		short	min[][]   		= new short [nf][nf];
		short 	ntll[][] 		= new short [nf][nf];
		
		System.out.print("---------------------------------------------------");
		
		
		a.display();	// *** Call method to display the information
		
		System.out.println("\nReading....");
		readArray(u, nf, ntimes, original);	// *** Call method to read video
		
		System.out.print("\nMax & min:\n");
		minMax(u, nf, ntimes, max, min);	// *** Call method to evaluate min & max
		
		System.out.println("\nUnwrapping....");
		unwrapSignal(u, nf, ntimes, unwrapped, max, min);	// *** Call method to unwrap the signal
		
		System.out.print("\nTime averaging....\n");
		timeAverage(u, nf, ntimes, taveraged);	// *** Call method to performe the time average
		
		System.out.print("\nShifting....\n");
		// *** Call method to performe shifting
		shiftSignal(u, nf, ntimes, ifreq, shifted, basev1, max, min, ntll);	
		
		
		System.out.println("\nSpace average:");
		spaceAverage(u, nf, ntimes);	// *** Call method to performe the space average
		System.out.println("End of space average");
		
		
		//printArray(u);	// *** Call method to print the values
		
		
		System.out.println("\nEnd of the analysis.");
		System.out.print("---------------------------------------------------");
	}
	
		//This method displays the information on the screen.
  	void display(){
		//System.out.println("Name: " + Name);
		//System.out.println("Account Number: " + AccNumber);
		//System.out.println("Balance: " + Bal);
	}
	//close display
	
		//This method prints the array values on the screen.
    public static void printArray( short[][][] u ){
	
		//System.out.println("\nNow on printArray subroutine");
		System.out.println("value:-24872: " + u[50][50][100]);
		System.out.println("value:-24938: " + u[50][50][110]);
		
	}
	//close printArray
	
		//This method reads the data
    public static void readArray( short[][][] u, int nf, int ntimes, 
								  short[][][] original ) {  		
		
		try{
			cascade_import data = new cascade_import();
			//int [][][] video = data.readVideoFile(namefilel);
			short [][][] video = data.readVideoFile("2010_feb16_temp_endo_010.dat");
			
			System.out.println("End of reading");
			System.out.println("\nCopying matrix....");
			
			for (int i = 0; i < nf; i++){
				for (int j = 0; j < nf; j++){
					for (int k = 0; k < ntimes; k++) {
							//Make constant the last times
						if (k < 10) video[k][i][j] = video[10][i][j];
						if (k > (ntimes - 10)) video[k][i][j] = video[ntimes - 10][i][j];
							//copy the data
						u[i][j][k] = video[k][i][j];
						original[i][j][k] = u[i][j][k];
					}
				}
			}
			
			System.out.println("u[50][50][100] = " + u[50][50][100]);
			System.out.println("u[50][50][110] = " + u[50][50][110]);
			System.out.println("End of copying");
			
        }//end try
		
		catch (IOException e) { 
			
			System.out.println("IOException while reading file.");
			System.exit(0);
		}//close catch
		//End reading data
		
	}
	//close readArray
	
		//This method evaluates the maximum and minimum values
	public static void minMax( short[][][] u, int nf, int ntimes, 
							   short[][] max, short [][] min){
		
		for (int i = 0; i < 128; i++){
			for (int j = 0; j < 128; j++){
				
				max[i][j] = -32768;
				min[i][j] = 32767;
				
				for (int k = 0; k < ntimes; k++) {
					
					if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
					if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
				}
			}
		}
		
		System.out.println("Max in [50][50]: " + max[50][50]);
		System.out.println("min in [50][50]: " + min[50][50]);
	}
	//close minMax
	
		//This method unwraps the saturated signal
	public static void unwrapSignal( short[][][] u, int nf, int ntimes, 
									 short[][][] unwrapped, 
									 short[][] max, short [][] min ){
									 
		for (int i = 0; i < 128; i++){
			for (int j = 0; j < 128; j++){
				for (int k = 10; k < ntimes - 10; k++) {
					
					if ( (int)( Math.abs(max[i][j] - min[i][j]) ) > 30000 ){
					
						u[i][j][k] = (short)( u[i][j][k] - 20000 );
						unwrapped[i][j][k] = u[i][j][k];
					}
				}
			}
		}
		
		System.out.println("u[50][50][100] = " + u[50][50][100]);
		System.out.println("u[50][50][110] = " + u[50][50][110]);
		System.out.print("End unwrapping\n");
	}
	//close unwrapSignal
	
		//This method performes the time average
	public static void timeAverage( short[][][] u, int nf, int ntimes, 
									short[][][] taveraged ){
		
		for (int i = 0; i < 128; i++){
			for (int j = 0; j < 128; j++){
			
				int averp = 0;
				int averm = 0;
				int averr = 0;
				
				for (int nt = 10; nt < ntimes - 10; nt++){
					
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
					
					taveraged[i][j][nt] = u[i][j][nt];
				}
			}
		}
		System.out.println("u[50][50][100] = " + u[50][50][100]);
		System.out.println("u[50][50][110] = " + u[50][50][110]);
		System.out.println("End of time averaging");
	}
	//close timeAverage
					
					
		//This method shifts the signal
	public static void shiftSignal( short[][][] u, int nf, int ntimes, int ifreq, 
									short[][][] shifted, short[][][] basev1,
									short[][] max, short[][] min, short[][] ntll ){
									
				
		short smin[][][]	= new short [nf][nf][1000];
		short smint[][][]	= new short [nf][nf][1000];
		short intervsh1[][]	= new short [nf][nf];
		short intervsh2[][]	= new short [nf][nf];
		
		for (int i = 0; i < 128; i++){
			for (int j = 0; j < 128; j++){
				
				ntll[i][j] = 0;
				
				for (int k = 0; k < ntimes; k++){
					
					if (k < 1000) smin[i][j][k] = (short)(32767);
					
					basev1[i][j][k] = min[i][j];
					
				}
			}
		}
		
			//Find minima and timing
		for (int nt = ifreq; nt < (ntimes - ifreq); nt = (nt + ifreq) ) {
			
			for (int i = 0; i < 128; i++) {
				for (int j = 0; j < 128; j++) {
					
					ntll[i][j]++;
					
					for (int nn = (nt - ifreq); nn < (nt + ifreq); nn++) {
						
						if( u[i][j][nn] < smin[i][j][ntll[i][j]] ) {   
							
							smin[i][j][ntll[i][j]] 	= u[i][j][nn];  //minimum in ifreq
							smint[i][j][ntll[i][j]] = (short)(nn);			 //time of minimum
							
								//Take the last time of minima -> intervsh2 for shifting line
							intervsh2[i][j] = smint[i][j][ntll[i][j]];
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
					/*
					fraq = (double)(( (double)(nt) - (double)(smint[i][j][ ntll[i][j] - 1 ] ))/(double)(idist));
					basev = (double)((double)(smin[i][j][ntll[i][j] - 1])*(1.0 - fraq) + (double)(smin[i][j][ntll[i][j]])*fraq);
					*/
					fraq = (double)(( nt - smint[i][j][ ntll[i][j] - 1 ] )/idist);
					basev = (double)( smin[i][j][ntll[i][j] - 1] *(1.0 - fraq) 
									+ smin[i][j][ntll[i][j]]*fraq );
						
						//Shifting line and subtraction of the drift
					basev1[i][j][nt] 	= (short)(basev);					
					u[i][j][nt] 		= (short)(u[i][j][nt] - basev);
					shifted[i][j][nt] 	= u[i][j][nt];
					
				}
			}	
		}
		
		System.out.println("u[50][50][100] = " + u[50][50][100]);
		System.out.println("u[50][50][110] = " + u[50][50][110]);
		System.out.println("End of shifting");
		
	}
	//close shiftSignal
		
		//This method performes the space average
	public static void spaceAverage( short[][][] u, int nf, int ntimes ){
			
		double xlap 		= 0;
		double xcoef[][]	= new double [13][13];
        double xcoeft		= 0;
		
		short spaceaveraged[][][] = new short [nf][nf][ntimes];
		
        int kt = 50;
        int ktt = 0;
		
		short max[][] = new short [nf][nf];
		short min[][] = new short [nf][nf];
		
		//Evaluate the Gaussian coefficients
		System.out.println("\nGaussian coefficients....");
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
		
		System.out.println("End of coeff");
		
		System.out.println("\nAveraging....");
		kt=0;
		
		for (int k = 0; k < ntimes; k++){
			
			kt = kt + 1;
			ktt = ktt + 1;
			
			if(kt == 500){
				System.out.println("doing now: " + ktt);
				kt = 0;
			}
			
			
			for (int j = 3; j < (nf - 15); j++){
				for (int i = 3; i < (nf - 15); i++){
					max[i][j] = -32768;
					min[i][j] = 32767;
					
					xlap = 0;
					
					for (int ii = 0; ii <= 12; ii++){
						for (int jj = 0; jj <= 12; jj++){
							
							xlap = xlap +(u[i+ii][j+jj][k])*xcoef[ii][jj];
						}
					}
					
					u[i][j][k] = (short)(xlap*xcoeft);
					spaceaveraged[i][j][k] = u[i][j][k];
					
					//if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
					//if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
					
				}
			}
		}
		System.out.println("\nu[50][50][100] = " + u[50][50][100]);
		System.out.println("u[50][50][110] = " + u[50][50][110]);
	}
	//close spaceAverage
		
		
		
		
		
	
}//End class Java program

