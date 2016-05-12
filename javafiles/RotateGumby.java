import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;
import java.net.*;

/*      <applet code="RotateGumby" width=800 height=800>
         </applet>
*/

public class RotateGumby extends Applet {
  Image img = null;
  Image rot = null;

  int buffer[] = new int[32 * 32];
  int rotate[] = new int[32 * 32];

  public void init() {
   try {
      MediaTracker tracker = new MediaTracker (this);
      img = getImage(new URL(getDocumentBase(), "gumby.gif"));
      tracker.addImage (img, 0);
      tracker.waitForAll();
      PixelGrabber grabber =
        new PixelGrabber(img, 0, 0, 32, 32, buffer, 0, 32);
        try {
        grabber.grabPixels();
          }
      catch(InterruptedException e) {
         e.printStackTrace();
         }
      for(int y = 0; y < 32; y++) {
        for(int x = 0; x < 32; x++) {
          rotate[((32-x-1)*32)+y] = buffer[(y*32)+x];
          }
        }
      rot = createImage(new MemoryImageSource(32, 32, rotate, 0, 32));
      }
   catch (Exception e) {
      e.printStackTrace();
      }
   }

  public void update( Graphics g) {
   paint(g);
   }

  public void paint(Graphics g) {
     g.drawImage(img, 0, 0,this);
     g.drawImage(rot,0, 40, this);
    }
}
