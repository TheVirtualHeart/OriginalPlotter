import java.awt.*;
import java.awt.event.*;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.lang.*;
import java.text.*;
import java.io.*;


public class test3{
  //write the variables for Class
	String		namefilel;

  String Name;
  int AccNumber;
  float Bal;
   short [][][] video;
//   short [][][] u;
 
  //This method display the information on the screen.
  void display(){
    System.out.println("Name: " + Name);
    System.out.println("Account Number: " + AccNumber);
    System.out.println("Balance: " + Bal);
  }
  public static void main(String args[]) {
      
        short u[][][] 	= new short [128][128][3000];
    //Create an instance of test3 
    test3 a = new test3();
    //Assigning values to the variables in class test3 
    a.Name = "Vinod";
    a.AccNumber = 467256282;
    a.Bal =635;
    //Draw the top border
    for (int i = 0; i < 20; i++)
      System.out.print("--");
    //Title
    System.out.println(" PARTICULARS");
    //Call method to display the information
    a.display();
    //Draw bottom border
    for (int i = 0; i < 20; i++)
      System.out.print("--");
    //Ending remark
    System.out.println(" ");
    System.out.println("reading....");
     readArray(u);  //video);
    System.out.println("return from reading");
    System.out.println("value:-24872: " + u[50][50][100]);
    System.out.println("value:-24938: " + u[50][50][110]);
    printArray(u);
    System.out.println("back from subroutine");

                                          }

    public static void printArray(short[][][] u){
    System.out.println("now on a subroutine");
    System.out.println("value:-24872: " + u[50][50][100]);
    System.out.println("value:-24938: " + u[50][50][110]);

                                     }


    public static void readArray(short[][][] u  ){  //short [][][] video){

				short xlap = 0;
                                int nf= 128;
	                        int ntimes = 3000;
	short max[][] = new short [nf][nf];
	short min[][] = new short [nf][nf];
//      	short u[][][]      			= new short [nf][nf][ntimes];
	short spaceaveraged[][][] 	= new short [nf][nf][ntimes];
        int kt=50;
        int ktt=0;



	short xcoef[][]	= new short [13][13];
        short xcoeft=0;


    try{
	cascade_import data = new cascade_import();
	//int [][][] video = data.readVideoFile(namefilel);
	short [][][] video = data.readVideoFile("2010_feb16_temp_endo_010.dat");

    System.out.println("End of reading");

					for (int i = 0; i < 128; i++){
					for (int j = 0; j < 128; j++){
					
						max[i][j] = -32768;
						min[i][j] = 32767;
												
						for (int k = 0; k < ntimes; k++) {
													
							//Make constant the last times
							if (k < 10) video[k][i][j] = video[10][i][j];
							if (k > (ntimes - 10)) video[k][i][j] = video[ntimes - 10][i][j];
							
							//copy the data
							u[i][j][k] 			=(short)( -video[k][i][j]);
                                                                                  }
                                                                    }
                                                                    }

    System.out.println("End of copying");
    System.out.println("value:-24872: " + u[50][50][100]);
    System.out.println("value:-24938: " + u[50][50][110]);


        }//end try

catch (IOException e) { 
				
		System.out.println("IOException while reading file.");
				System.exit(0);
				}//close catch


     //End reading data

			for (int i = 0; i <= 12; i++){
			for (int j = 0; j <= 12; j++){
					
				int ii = i - 6;
				int jj = j - 6;
					
				//Maintain all the parenthesis
				xcoef[i][j] = (short)(1000*9*Math.exp(-( (ii*ii) + (jj*jj) )/8));
				
				xcoeft =(short)( xcoeft + xcoef[i][j]);
			}
			}
			
		      xcoeft = (short)(1/xcoeft);

                         kt=0;
    System.out.println("End of coeff");


					for (int k = 0; k < ntimes; k++){
                                                kt=kt+1;
                                                ktt=ktt+1;
                                               
                                                if(kt==500){
                                          System.out.println("doing now"+ktt);
                                                    kt=0;
                                                          }


				

				for (int j = 3; j < (nf - 15); j++){
				for (int i = 3; i < (nf - 15); i++){
					
					max[i][j] = -32768;
					min[i][j] = 32767;
						

								
						xlap = 0;
								
						for (int ii = 0; ii <= 12; ii++){
						for (int jj = 0; jj <= 12; jj++){
								
							xlap = (short)(xlap +(u[i+ii][j+jj][k])*xcoef[ii][jj]);
						}
						}
								
				//		u[i][j][k] = (short) (xlap*xcoeft);
				//		spaceaveraged[i][j][k] = u[i][j][k];
							
				//		if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
				//		if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
							
					}
						

}
}






  }
} 
