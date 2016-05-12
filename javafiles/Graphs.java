import java.awt.*; 
import java.awt.event.*; 
import java.applet.*; 
import java.util.*; 
import java.text.*; 
 
// A general-purpose graph class. 
public class Graphs extends Frame { 
  // Constants for type of graph. 
  public final static int NA = 0; 
  public final static int K1 = 1; 
  public final static int TO = 2; 
  public final static int KUR = 3; 
  public final static int KR = 4; 
  public final static int KS = 5; 
  public final static int CAL = 6; 
  public final static int PCA = 7; 
  public final static int NAK = 8; 
  public final static int NACA = 9; 
  public final static int BNA = 10; 
  public final static int BCA = 11; 
  public final static int TOTAL = 12; 
  public final static int TOs = 13;
  public final static int TOsNa = 14;


 
  private int graphStyle; 
 
  /* These specify the amount of space to  
     leave between data and borders. */ 
  private final int leftGap = 4; 
  private final int topGap = 2; 
  private final int bottomGap = 8; 
  private int rightGap; // this value is computed  
 
  // These hold the min and max values of the data. 
  private float min, max; 
  private float vmin, vmax; 

 
  // Refers to the data. 
  private float[] data;  
  private float[] tdata;
  private int jj;
  NumberFormat nf = NumberFormat.getInstance(); 
 
  // Colors used by the graph. 
  Color gridColor = new Color(0, 150, 150); 
  Color dataColor = new Color(0, 0, 0); 
  Color dataColor2 = new Color(250,0,0);
  Color background = new Color(250,250,250);
  
  // Various values used to scale and display data. 
  private int hGap;   // space between data points 
  private float spread; // distance between min and max tdata 
  private float vspread; // distance between vmin and vmax data 

  private float scale; // scaling factor 
  private int baseline; // vertical coordinate of baseline 
  private int lengthx ; 
  private float scaley;
  private float scalex;
 
  // Location of data area within the window. 
  private int top, bottom, left, right; 
 
  public Graphs(float[] vals,float[] t, int style, int jjj) { 
//  int jj=jjj;
    // Handle window-closing events. 
    addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent we) { 
        setVisible(false); 
        dispose(); 
      } 
    }); 
 
    // Handle resize events. 
    addComponentListener(new ComponentAdapter() { 
      public void componentResized(ComponentEvent ce) { 
        repaint(); 
      } 
    }); 
 
    graphStyle = style; 
    jj=jjj;
 
    data = vals; 
    tdata= t;
 
    // Sort the data to find min and max values. 
//    double t[] = new double[vals.length]; 
//    System.arraycopy(vals, 0, t, 0, vals.length); 
//    Arrays.sort(t); 
    min = tdata[0]; 
    max = tdata[jj]; 
//   vmin = data[0]; 
//   vmax = data[tdata.length-2]; 
     vmin=900;
     vmax=-900;
    setBackground(background);


 
    setSize(new Dimension(400, 350)); 
 
    switch(graphStyle) { 
      case NA: 
        setTitle("   I_Na vs time"); 
        setLocation(150,10); 
        break; 
      case K1: 
        setTitle("   I_K1 vs time"); 
        setLocation(165, 10+10); 
        break; 
      case TO: 
        setTitle("   I_to vs time"); 
        setLocation(180,20); 
        break; 
      case TOs:
        setTitle("   I_stim vs time");
        setLocation(195,20);
        break;
      case TOsNa:
        setTitle("   I_leak vs time");
        setLocation(210,30);
        break;
      case KUR: 
        setTitle("   I_tr vs time"); 
        setLocation(195,40); 
        break; 
      case KR: 
        setTitle("   I_Kr vs time"); 
        setLocation(195+10,50); 
        break; 
      case KS: 
        setTitle("   I_Ks vs time"); 
        setLocation(195+10*2,60); 
        break; 
      case CAL: 
        setTitle("   I_Cal vs time"); 
        setLocation(195+10*3,70); 
        break; 
      case PCA: 
        setTitle("   I_up  time"); 
        setLocation(195+10*4,80); 
        break; 
      case NAK: 
        setTitle("   I_NaK vs time"); 
        setLocation(195+10*5,90); 
        break; 
      case NACA: 
        setTitle("   I_NaCa vs time"); 
        setLocation(195+10*6,100); 
        break; 
      case BNA: 
        setTitle("   I_bNa vs time"); 
        setLocation(195+10*7,110); 
        break; 
      case BCA: 
        setTitle("   I_bCa vs time"); 
        setLocation(195+10*8,120); 
        break; 
      case TOTAL: 
        setTitle("   I_total vs time"); 
        setLocation(195+10*9,130); 
        break; 
    } 
 
    setVisible(true); 
  } 
 
  public void paint(Graphics g) { 
 
    Dimension winSize = getSize(); // size of window 
    Insets ins = getInsets(); // size of borders 
 
    // Get the size of the curently selected font. 
    FontMetrics fm = g.getFontMetrics(); 
 
    // Compute right gap. 
    rightGap = fm.stringWidth("" + data.length); 
 
    // Compute the total insets for the data region. 
    left = ins.left + leftGap + fm.charWidth('0'); 
    top = ins.top + topGap + fm.getAscent(); 
    bottom = ins.bottom + bottomGap + fm.getAscent(); 
    right = ins.right + rightGap; 
 
    /* If minimum value positive, then use 0 
       as the starting point for the graph. 
       if maximum value is negative, use 0. */ 
//    if(min > 0) min = 0; 
//    if(max < 0) max = 0; 
 
    /* Compute the distance between the minimum  
       and maximum values. */ 
    spread =  (max - min); 
    for(int i=0; i < jj; i++) { 

       if(data[i]>vmax)vmax=data[i];
       if(data[i]<vmin)vmin=data[i];
                             }
    vspread = (vmax-vmin); 

 
    // Compute the scaling factor. 
    scale = (float) (winSize.height - bottom - top) / vspread; 
 
 
    // Find where the baseline goes. 
//BASELINE AND STUF
    baseline = (int) (winSize.height-40);  
    scaley = (float) ((winSize.height-105)/vspread); 
    lengthx = (int) (winSize.width - 60);
    scalex = (float) ((winSize.width - 60)/spread);

 
    // Compute the spacing between data. 
//    hGap = (winSize.width - left - right) / (spread); 
 
    // Set the grid color. 
    g.setColor(gridColor); 
 
    // Draw the baseline. 
//    g.drawLine(left, baseline, 
//               left + spread * hGap, baseline); 
 
    // Draw the Y axis. 
//    if(graphStyle != BAR) 
//      g.drawLine(left, winSize.height-bottom, left, top); 
 
    // Display the min, max, and 0 values. 
//    g.drawString("0", ins.left, baseline+fm.getAscent()/2); 
 
//    if(max != 0) 
//      g.drawString("" + vmax, ins.left, baseline - 
    //               (int) (hGap*tdata[30]) - 4); 

//      g.drawString("" + vmax, ins.left, top); 
//                   (int) (hGap*tdata[25])); 


//    if(min != 0) 
//      g.drawString("" + min, ins.left, baseline - 
//                   (int) (min*scale)+fm.getAscent()); 
 
    // Display number of values.  x 
//    g.drawString("" + max, 
//                 (int)(max) * (hGap) - left, 
//                 baseline + fm.getAscent()); 

//    g.drawString("" + max/2, 
//                 (int)(max/2.) * (hGap)-left, 
//                 baseline + fm.getAscent()); 

//    g.setColor(dataColor2); 
//    x axis
    g.drawLine(40,baseline,40+lengthx,baseline);
//   y axis
    g.drawLine(40,baseline,40,60);
 
    // Set the data color. 
    g.setColor(dataColor); 
 
    // Display the data. 
    switch(graphStyle) { 
      case NA: 
        scatter(g); 
        break; 
      case K1: 
        scatter(g); 
        break; 
      case TO: 
        scatter(g); 
        break; 
      case TOs:
        scatter(g);
        break;
      case TOsNa:
        scatter(g);
        break;
      case KUR: 
        scatter(g); 
        break;
      case KR: 
        scatter(g); 
        break;
     case KS: 
        scatter(g); 
        break;
     case CAL: 
        scatter(g); 
        break;

     case PCA: 
        scatter(g); 
        break;

     case NAK: 
        scatter(g); 
        break;

     case NACA: 
        scatter(g); 
        break;

     case BNA: 
        scatter(g); 
        break;

     case BCA: 
        scatter(g); 
        break;
     case TOTAL: 
        scatter(g); 
        break;





    } 
  } 
 
 
  // Display a scatter graph. 
  private void scatter(Graphics g) { 
    int v; 
    int tt;
    g.setColor(dataColor); 
 
    for(int i=0; i < jj; i++) { 

       if(data[i]>vmax)vmax=data[i];
       if(data[i]<vmin)vmin=data[i];

//       v=(int) (baseline-(baseline-(data[i]+85)*scaley));
         v=(int)((baseline-(data[i]-vmin)*scaley));


      tt=(int)((tdata[i]-min)*scalex+40); 
      g.drawRect(tt,  v, 2, 2); 
    } 
    g.setColor(dataColor2); 
    for(int i=0; i < jj; i++) { 
    int yy1=(int) ((baseline-(data[i]-vmin)*scaley));
    int yy2=(int) ((baseline-(data[i+1]-vmin)*scaley));
    int xx1=(int)((tdata[i]-min)*scalex+40); 
    int xx2=(int)((tdata[i+1]-min)*scalex+40); 
    g.drawLine(xx1,yy1,xx2,yy2);
                                           }
    g.setColor(gridColor); 
    g.drawString("" + (int)(max),(int)((max-min)*scalex+20),baseline+13); 
    g.drawString("" + (int)(max/2),(int)((max-min)*scalex/2)+20,baseline+13); 
    g.drawString("" + (int)(min),(int)((min-min)*scalex+40),baseline+13); 

    nf.setMaximumFractionDigits(3); 
    String ss=nf.format((float)(vmax)); 
    String sr=nf.format((float)(vmin)); 

    g.setColor(Color.black); 

    g.drawString("" + ss,11,(int)(baseline-(vmax-vmin)*scaley-3)); 
    g.drawString("" + sr,10,(int)(baseline-(vmin-vmin)*scaley-3)); 

//    g.drawString("vmax " + (vmax),(int)((max-min)*scalex)-33,baseline+13); 
//    g.drawString("vmin " + (vmin),(int)((max-min)*scalex/2),baseline+13); 
//    g.drawString("vspread "+ vspread ,20,baseline+13); 



  } 
 
  // Display a scatter graph with regression line. 
//  private void regplot(Graphics g) { 
//    int v; 
 
//    RegData rd = Stats.regress(data); 
 
//    for(int i=0; i < data.length; i++) { 
//      v = (int) (data[i] * scale); 
//      g.drawRect(i*hGap+left, baseline - v, 1, 1); 
//    } 
 
    // Draw the regression line. 
//    g.drawLine(left, baseline - (int) ((rd.a)*scale), 
//               hGap*(data.length-1)+left+1, 
 //              baseline - (int) ((rd.a+(rd.b*(data.length-1)))*scale));     
//  } 
}

