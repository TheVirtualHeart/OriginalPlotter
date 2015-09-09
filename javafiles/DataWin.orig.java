import java.awt.event.*; 
import java.awt.*; 
 
// Display an array of numeric data. 
class DataWin extends Frame { 
  TextArea dataTA; 
 
  DataWin(float[] data, float[] datat, int jj) { 
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent we) { 
        setVisible(false); 
        dispose(); 
      } 
    }); 
 
    dataTA = new TextArea(25, 5000); 
    dataTA.setEditable(false); 
 
    for(int i=0; i <= jj; i++) 
      dataTA.append(datat[i]+"   "+data[i]+"\n"); 
 
    setSize(new Dimension(210, 550)); 
    setLocation(250, 15); 
 
    setTitle("Data"); 
    setResizable(true); 
 
    add(dataTA); 
     
    setVisible(true); 
  } 
}

