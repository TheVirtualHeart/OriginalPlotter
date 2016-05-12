import java.awt.event.*; 
import java.awt.*; 

	// Display an array of numeric data. 
class restDataAn extends Frame { 
  
  	TextArea dataTA; 
	restDataAn(String title, boolean alternansc, int nii, int njj, 
			   int maMaApdDi[], int meandi[][], int meanapd[][],
			   int mdiAo[][], int mapdAo[][], int mdiAe[][], int mapdAe[][]) { 
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent we) { 
				setVisible(false); 
				dispose(); 
			} 
		});
		
		dataTA = new TextArea(400, 300); 
		dataTA.setEditable(true); 
		
		if (alternansc == false) {
			dataTA.append("Point \t \n");
			dataTA.append(nii+"  "+njj+"\n\n");
			dataTA.append("DI \t APD \n\n");
			dataTA.append(meandi[nii][njj]+" \t "+meanapd[nii][njj]+"\n\n");
		}
		else {
			dataTA.append("Point \t \n");
			dataTA.append(nii+"  "+njj+"\n\n");
			if (mdiAo[nii][njj] > mapdAo[nii][njj]) {
				dataTA.append("DI-L \t DI-S \n");
				dataTA.append(mdiAo[nii][njj]+" \t "+mdiAe[nii][njj]+"\n\n");
				dataTA.append("APD-L \t APD-S \n");
				dataTA.append(mapdAo[nii][njj]+" \t "+mapdAe[nii][njj]+"\n\n");
			}
			else {
				dataTA.append("DI-S \t DI-L \n");
				dataTA.append(mdiAe[nii][njj]+" \t "+mdiAo[nii][njj]+"\n\n");
				dataTA.append("APD-S \t APD-L \n");
				dataTA.append(mapdAe[nii][njj]+" \t "+mapdAo[nii][njj]+"\n\n");
			}
		}
		dataTA.append("DI-m \t DI-M \n");
		dataTA.append(maMaApdDi[0]+"  \t  "+maMaApdDi[1]+"\n");
		dataTA.append("APD-m \t APD-M \n");
		dataTA.append(maMaApdDi[2]+"  \t  "+maMaApdDi[3]);
	 
    setSize(new Dimension(500, 550)); 
    setLocation(1000, 15); 
 
    setTitle(title); 
    setResizable(true); 
 
    add(dataTA); 
     
    setVisible(true); 
  } 
}
