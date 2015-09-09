
//  <applet code="appletImage" width="400" height="400"></applet>
import java.applet.*; 
import java.awt.*; 
             
public class appletImage extends Applet{
  Image img;
  MediaTracker tr;
  public void paint(Graphics g) {
    tr = new MediaTracker(this);
    img = getImage(getCodeBase(), "java1.gif");
    tr.addImage(img,0);
    g.drawImage(img, 0, 0, this);
  } 
} 