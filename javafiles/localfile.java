import java.applet.*;
import java.awt.*;
import java.util.*;
import java.lang.*;
import java.text.*;
import java.awt.event.*; 
import java.io.*;

public class localfile extends Applet {
	public localfile() {
		Panel p = new Panel();
		Font f;
		String osname = System.getProperty("os.name","");
		if (!osname.startsWith("Windows")) {
			f = new Font("Arial",Font.BOLD,10);
		} else {
			f = new Font("Verdana",Font.BOLD,12);
		}
		p.setFont(f);
		p.add(new Button("Open"));
		
		p.setBackground(new Color(255, 255, 255));
		
		add("North",p);
		
	}
	public boolean action(Event evt, Object arg) {
		if (arg.equals("Open")) {
			System.out.println("OPEN CLICKED");
			
			int arrlen = 10000;
			short infile[] = new short[49152000];
			Frame parent = new Frame();
			FileDialog fd = new FileDialog(parent, "Please choose a file:",
			    FileDialog.LOAD);
			fd.show();
			String selectedItem = fd.getFile();
			if (selectedItem == null) {
				// no file selected
			} else {
				File ffile = new File( fd.getDirectory() + File.separator +
				                     fd.getFile());
				// read the file
				System.out.println("reading file " + fd.getDirectory() +
				                        File.separator + fd.getFile() );
				try {
					FileInputStream fis = new FileInputStream(ffile); 
					BufferedInputStream bis = new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);
					try {
						int filelength = dis.read(infile);
						String filestring = new String(infile, 0,
						                           filelength);
						System.out.println("FILE CONTENT=" + filestring);
					} catch(IOException iox) {
						System.out.println("File read error...");
						iox.printStackTrace();
					}
				} catch (FileNotFoundException fnf) {
					System.out.println("File not found...");
					fnf.printStackTrace();
				}
			}		
			
		} else return false;
		return true;
	}

}
