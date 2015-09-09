import java.applet.*;
import java.awt.*;
import java.math.*;

// Java Bouncing Ball
// Terrence Ma
// Modified from Java Examples in a Nutshell

/*      <applet code="brine" width=400 height=400>
         </applet>
*/
public class brine extends Applet implements Runnable {
  int x = 150, y = 150, r=70;      // Position and radius of the circle
  int dx = 8, dy = 6;              // Trajectory of circle
  int xscale=100, yscale=100;
  Dimension size;                  // The size of the applet
  Image buffer;                    // The off-screen image for double-buffering
  Image ball;
  Graphics2D big;
  Graphics bufferGraphics;         // A Graphics object for the buffer
  Thread animator;                 // Thread that performs the animation
  Color background;
  boolean please_stop;             // A flag asking animation thread to stop

  /** Set up an off-screen Image for double-buffering */
  public void init() {
  
    size = this.size();
    buffer = this.createImage(size.width, size.height);
    ball = getImage(getCodeBase(), "babybs1.gif" );
    bufferGraphics = buffer.getGraphics();
    background = new Color(43,128,94);
    setBackground(background);
  }

  /** Draw the circle at its current position, using double-buffering */
  public void paint(Graphics g) {
  Graphics2D g2 = (Graphics2D) g;
    // Draw into the off-screen buffer.
    // Note, we could do even better clipping by setting the clip rectangle
    // of bufferGraphics to be the same as that of g.
    // In Java 1.1:  bufferGraphics.setClip(g.getClip());
    bufferGraphics.setColor(background);
    bufferGraphics.fillRect(0, 0, 2*size.width, 2*size.height); // clear the buffer
//    bufferGraphics.fillOval(x-r, y-r, r*2, r*2);            // draw the circle
      bufferGraphics.drawImage(ball,x-(int)(xscale/2),y-(int)(yscale/2),this);
//      bufferGraphics.fillOval(x-r/2, y-r/2, r, r);            // draw the circle

//    bufferGraphics.setColor(Color.blue);
//    bufferGraphics.drawRect(10,10, size.width-20, size.height-20);

    // Then copy the off-screen buffer onto the screen
//      bufferGraphics.setColor(Color.black);
//       bufferGraphics.drawString("T= "+String.valueOf(Math.atan2((double)(dy),(double)(dx))),x-30,x-32);

       g2.rotate(Math.atan2((double)(dy),(double)(dx))+.9,x,y);

//    g2.rotate(1.7475,x,y);
     g2.drawImage(buffer, 0, 0, this);
    
//    g.drawImage(ball,x-r,y-r,this);
  }

  /** Don't clear the screen; just call paint() immediately
  *   It is important to override this method like this for double-buffering */
  public void update(Graphics g) { paint(g); }

  /** The body of the animation thread */
  public void run() {
    while(!please_stop) {
      // Bounce the circle if we've hit an edge.
      if ((x - 10 -r/2 + dx < 0) || (x + 10 +r/2 + dx > size.width)) dx = -dx;
      if ((y - 10 -r/2 + dy < 0) || (y +10 +r/2 + dy > size.height)) dy = -dy;


      if(y > 65 && y < size.height-65) {

      if(x < 65 ) { xscale = 100 - (65-x) ;
                         yscale = 100 + (65-x) ; } 
      if(x > 65 && x < size.width-65 ) { xscale=100;			 
                   yscale=100; }
      if(x > size.width-65) {  xscale = 100 + (size.width-65 - x);
                               yscale = 100 - (size.width-65 - x); }
		                       }
				       
      if(y < 65 ) { yscale = 100 - (65-y);
                    xscale = 100 + (65-y); }
      if(y > size.height-65) { yscale = 100 + (size.height-65 -y);
                               xscale = 100 - (size.height-65 -y);
			                                          }

      // Move the circle.
      x += dx;  y += dy;

      // Ask the browser to call our paint() method to redraw the circle
      // at its new position.  Tell repaint what portion of the applet needs
      // be redrawn: the rectangle containing the old circle and the
      // the rectangle containing the new circle.  These two redraw requests
      // will be merged into a single call to paint()
//      repaint(x-r-dx, y-r-dy, 2*r, 2*r);   // repaint old position of circle
//      repaint(x-r, y-r, 2*r, 2*r);         // repaint new position of circle
        repaint();

      // Now pause 50 milliseconds before drawing the circle again.
      try { Thread.sleep(50); } catch (InterruptedException e) { ; }
    }
    animator = null;
  }

  /** Start the animation thread */
  public void start() {
    if (animator == null) {
      please_stop = false;
      animator = new Thread(this);
      animator.start();
    }
  }

  /** Stop the animation thread */
  public void stop() { please_stop = true; }

  /** Allow the user to start and stop the animation by clicking */
  public boolean mouseDown(Event e, int x, int y) {
    if (animator != null) please_stop = true;  // if running request a stop
    else start();                              // otherwise start it.
    return true;
  }
}

