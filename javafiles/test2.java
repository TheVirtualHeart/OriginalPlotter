import java.awt.*;
import java.awt.event.*;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.lang.*;
import java.text.*;
import java.io.*;


public class test2{
  //write the variables for Class
	String		namefilel;




  String Name;
  int AccNumber;
  float Bal;
  //This method display the information on the screen.
  void display(){
    System.out.println("Name: " + Name);
    System.out.println("Account Number: " + AccNumber);
    System.out.println("Balance: " + Bal);
  }
  public static void main(String args[]) {
      
    //Create an instance of test2 
    test2 a = new test2();
    //Assigning values to the variables in class test1 
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
    System.out.println("End of display");



				double xlap = 0;
                                int nf= 128;
	                        int ntimes = 3000;
	double  max[][] = new double [nf][nf];
	double min[][] = new  double [nf][nf];
	double u[][][]      			= new double [nf][nf][ntimes];
	double spaceaveraged[][][] 	= new double [nf][nf][ntimes];
        int kt=50;



	double xcoef[][]	= new double [13][13];
        double xcoeft=0;


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
							u[i][j][k] 			=(double)( -video[k][i][j]);
                                                                                  }
                                                                    }
                                                                    }

    System.out.println("End of copying");

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
				xcoef[i][j] = (1000*9*Math.exp(-( (ii*ii) + (jj*jj) )/8));
				
				xcoeft =( xcoeft + xcoef[i][j]);
			}
			}
			
		      xcoeft = (1/xcoeft);

                         kt=0;
    System.out.println("End of coeff");

			for (int k = 10; k < ntimes; k++){
                                                kt=kt+1;
                                                if(kt==100){
                                     //     System.out.println("End of coeff");
                                                    kt=0;
                                                          }

				

				for (int j = 3; j < (nf - 15); j++){
				for (int i = 3; i < (nf - 15); i++){
					
					max[i][j] = -32768;
					min[i][j] = 32767;
		
		
				

								
						xlap = 0;
								
						for (int jj = 0; jj <= 12; jj++){
						for (int ii = 0; ii <= 12; ii++){
								
							xlap = (xlap +(u[i+ii][j+jj][k])*xcoef[ii][jj]);
						}
						}
								
						u[i][j][k] =  (xlap*xcoeft);
						spaceaveraged[i][j][k] = u[i][j][k];
							
						if (u[i][j][k] < min[i][j]) min[i][j] = u[i][j][k];
						if (u[i][j][k] > max[i][j]) max[i][j] = u[i][j][k];
							
					}
						

}
}






  }
} 

