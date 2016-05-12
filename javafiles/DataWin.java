import java.awt.event.*; 
import java.awt.*; 
 
// Display an array of numeric data. 
class DataWin extends Frame { 
  TextArea dataTA; 
 
  DataWin(float[] data, float[] datat, int jj, String title, String ondata, int nii, int njj) { 
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent we) { 
        setVisible(false); 
        dispose(); 
      } 
    }); 
 
    dataTA = new TextArea(40, 5000); 
    dataTA.setEditable(false); 
	
    dataTA.append(ondata);
	dataTA.append("\n\n");
    for(int i=0; i <= jj; i++) 
      dataTA.append(datat[i]+"  \t"+data[i]+"\t\t"+nii+"\t"+njj+"\n"); 
 
    setSize(new Dimension(210, 550)); 
    setLocation(1150, 15); 
 
    setTitle(title); 
    setResizable(true); 
 
    add(dataTA); 
     
    setVisible(true); 
  } 
}

