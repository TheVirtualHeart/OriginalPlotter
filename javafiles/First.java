// Applet number_1
import java.applet.Applet;
import java.awt.*;

/*      <applet code="First_Applet" width=300 height=250>
         </applet>
*/

public class First_Applet extends Applet {


  public void paint(Graphics g) {
  setBackground(Color.black);
  g.setColor(Color.white);
  
  g.setFont(new Font("TimesRoman", Font.PLAIN,15));
  g.drawString("Example isn't another way to teach,",15,30);
  g.drawString("it si the only way to teach.",17,50);
//  g.fillRoundRect(10,10,430,430,25,25);
  g.setColor(Color.lightGray);
  g.drawString(" Albert Einstein.",173,70);
  g.drawLine(163,65,173,65);

  g.setColor(Color.white);
  Polygon pp= new Polygon();
  g.drawLine(90,140,185,140);
  pp.addPoint(95,140);
  pp.addPoint(185,148);
  pp.addPoint(205,155);
  pp.addPoint(190,137);
  
  pp.addPoint(212,140);
  pp.addPoint(188,130);
//  pp.addPoint(188,105);
  pp.addPoint(195,105);
  pp.addPoint(180,126);
  pp.addPoint(174,100);
  pp.addPoint(183,105);
  pp.addPoint(189,110);
//  pp.addPoint(180,133);
  pp.addPoint(174,90);
  pp.addPoint(182,80);
  pp.addPoint(170,80);
  pp.addPoint(160,92);
  pp.addPoint(155,100);
  pp.addPoint(153,110);
  pp.addPoint(140,99);
  pp.addPoint(145,92);
  pp.addPoint(135,88);
  pp.addPoint(135,95);
  pp.addPoint(127,110);
  pp.addPoint(100,100);
  pp.addPoint(110,110);
  pp.addPoint(110,115);
  pp.addPoint(90,120);
  pp.addPoint(75,130);
  pp.addPoint(90,125);
  pp.addPoint(93,127);
  pp.addPoint(70,155);

  


    g.fillPolygon(pp);
  g.setColor(Color.black);
  g.drawPolygon(pp);

  
  g.setColor(Color.yellow);
  g.fillOval(100,115,80,80);
  g.setColor(Color.red);
  g.drawArc(115,135,50,50,180,180);
  g.drawArc(115,136,50,50,180,180);

  g.setColor(Color.black);
  g.drawOval(100,115,80,80);
  g.fillOval(110+14,136,10,20);
  g.fillOval(110+39,136,10,20);
  g.setColor(Color.white);
//  g.drawLine(110,165,170,165);
  Polygon p= new Polygon();
  p.addPoint(100,177);
  p.addPoint(110,165);
  p.addPoint(140,160);
  p.addPoint(170,165);
  p.addPoint(185,185);
  p.addPoint(180,190);
  p.addPoint(175,180);
  p.addPoint(170,188);
  p.addPoint(160,175);
  p.addPoint(150,182);
  p.addPoint(145,168);
  p.addPoint(133,181);
  p.addPoint(125,170);
  p.addPoint(110,179);
  p.addPoint(103,183);
  g.fillPolygon(p);
  g.setColor(Color.black);
  g.drawPolygon(p);
                                     }
   
                                     }
