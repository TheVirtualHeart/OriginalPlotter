import java.applet.*;
import java.awt.*;
import java.math.*;
        import java.lang.*;
        import java.util.Random;

// Java Bouncing Ball
// Terrence Ma
// Modified from Java Examples in a Nutshell

/*      <applet code="brine" width=800 height=800>
         </applet>
*/
public class brine extends Applet implements Runnable {
  int x = 150, y = 150, r=70;      // Position and radius of the circle
  int dx = 8, dy = 6, dyo, dxo;              // Trajectory of circle
  int n=1;
  int m=1;
  int xscale=100, yscale=100;
  Dimension size;                  // The size of the applet
  Image buffer;                    // The off-screen image for double-buffering
  Image brine1,brine2;
  Graphics2D big;
  Graphics bufferGraphics;         // A Graphics object for the buffer
  Thread animator;                 // Thread that performs the animation
  Color background;
  boolean please_stop;             // A flag asking animation thread to stop
        Random m_r=new Random();

  /** Set up an off-screen Image for double-buffering */
  public void init() {
     n=3; 
    size = this.size();
    buffer = this.createImage(size.width, size.height);
    brine1 = getImage(getCodeBase(), "babybs1.gif" );
    brine2 = getImage(getCodeBase(), "babybs2.gif" );
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
    bufferGraphics.fillRect(0, 0, 3*size.width, 3*size.height); // clear the buffer
//    bufferGraphics.fillOval(x-r, y-r, r*2, r*2);            // draw the circle
      if(m==1)bufferGraphics.drawImage(brine1,x,y,this);
      if(m==2)bufferGraphics.drawImage(brine2,x,y,this);

//    bufferGraphics.setColor(Color.blue);
//      bufferGraphics.fillOval(x+25, y+30, 10,10);            // draw the circle

//    bufferGraphics.drawRect(10,10, size.width-20, size.height-20);

    // Then copy the off-screen buffer onto the screen
//      bufferGraphics.setColor(Color.black);
//       bufferGraphics.drawString("T= "+String.valueOf(n),x+30,y+32);
         
       g2.rotate(Math.atan2((double)(dy*10),(double)(dx*10))+.9,x+25,y+30);

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

     n=n+1; 
     m=m+1;

       if(n==4){

        dxo=dx;
	dyo=dy;
        dx=(int)(8*m_r.nextGaussian());
	dy=(int)(8*m_r.nextGaussian());

	if(dxo*dx<0){ if(dyo < 1) ;
	              if(dyo >=1)dx=-dx;
		     }
	if(dyo*dy<0){ if(dxo < 1) ;
	              if(dxo >=1)dy=-dy;
		     }
		     
	n=0;
               }
       if(m==3)m=1;
               
      
      // Move the circle.
      x += dx;  y += dy;
      if ((x    < 50) || (x  > size.width-50)) x = x-dx;
      if ((y    < 50) || (y  > size.height-50)) y = y-dy;


	
      
      // Ask the browser to call our paint() method to redraw the circle
      // at its new position.  Tell repaint what portion of the applet needs
      // be redrawn: the rectangle containing the old circle and the
      // the rectangle containing the new circle.  These two redraw requests
      // will be merged into a single call to paint()
//      repaint(x-r-dx, y-r-dy, 2*r, 2*r);   // repaint old position of circle
//      repaint(x-r, y-r, 2*r, 2*r);         // repaint new position of circle
        repaint();

      // Now pause 50 milliseconds before drawing the circle again.
      try {
      if(m==1)Thread.sleep(250);
      if(m==2)Thread.sleep(50);
      } catch (InterruptedException e) { ; }
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

