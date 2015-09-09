import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class kmodel extends Applet
  implements ActionListener
{
  private Panel buttonPanel;
  private Label prompt1;
  private Label prompt2;
  private Label prompt3;
  private Label prompt4;
  private Label prompt5;
  private Label promptx;
  private Label prompts1;
  private Label prompts2;
  private Label prompttime;
  private TextField input1;
  private TextField input2;
  private TextField input3;
  private TextField input4;
  private TextField input5;
  private TextField inputs1;
  private TextField inputs2;
  private TextField inputtime;
  private double result;
  private Label promptn1;
  private Label promptn2;
  Button butt;
  boolean firsttime = true;
  double a = 0.1D;                        // unused
  double gama = 1.0D;                     // unused
  double b = 0.5D;                        // unused
  double ueps = 250.0D;
  double eps = 1.0D / this.ueps;
  double delta = 0.0D;
  double xm = 10.0D;
  double gam = 1.92D;
  double vstar = 0.64D;
  double s1 = 0.0D;
  double s2 = 300.0D;
  double time = 500.0D;
  double alf;
  double alft;
  double top;
  double bot;
  double bett;
  double bet;
  double vv;
  int is1;
  int is2;
  int ntime;
  int iis1;
  int iis2;
  int iiss;
  int nt = 1;

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    this.vstar = Double.valueOf(this.input1.getText()).doubleValue();
    this.xm = Double.valueOf(this.input2.getText()).doubleValue();

    this.ueps = Double.valueOf(this.input4.getText()).doubleValue();

    this.s1 = Double.valueOf(this.inputs1.getText()).doubleValue();
    this.s2 = Double.valueOf(this.inputs2.getText()).doubleValue();
    this.time = Double.valueOf(this.inputtime.getText()).doubleValue();
    this.eps = (1.0D / this.ueps);
    this.nt = ((int)(this.time / 501.0D));
    this.nt += 1;

    this.input1.setText(this.input1.getText());
    this.input2.setText(this.input2.getText());

    this.input4.setText(this.input4.getText());

    this.inputs1.setText(this.inputs1.getText());
    this.inputs2.setText(this.inputs2.getText());
    this.inputtime.setText(this.inputtime.getText());

    if (paramActionEvent.getSource() == this.butt)
    {
      if (this.butt.getLabel().equals("Start")) {
        this.butt.setLabel("Reset");
        this.firsttime = false;
      }
      else
      {
        this.butt.setLabel("Start");
        this.firsttime = true;
        this.a = 0.1D;
        this.gama = 1.0D;
        this.b = 0.5D;
        this.vstar = 0.64D;
        this.xm = 10.0D;
        this.ueps = 250.0D;
        this.eps = (1.0D / this.ueps);
        this.delta = 0.0D;
        this.s1 = 0.0D;
        this.s2 = 300.0D;
        this.time = 500.0D;
        this.input1.setText("0.64");
        this.input2.setText("10");

        this.input4.setText("250");

        this.inputs1.setText("0");
        this.inputs2.setText("300");
        this.inputtime.setText("500");
      }
    }

    repaint();
  }

  public void init()
  {

    // initialize buttons and the like
    this.buttonPanel = new Panel();
    this.butt = new Button("Start");
    this.butt.addActionListener(this);
    this.prompt1 = new Label(" vstar");
    this.input1 = new TextField(3);
    this.input1.addActionListener(this);
    this.prompt2 = new Label("   xm");
    this.input2 = new TextField(3);
    this.input2.addActionListener(this);
    this.promptx = new Label("       ");

    this.prompt4 = new Label(" 1/eps");
    this.input4 = new TextField(3);
    this.input4.addActionListener(this);

    this.input1.setText("0.64");
    this.input2.setText("10");

    this.input4.setText("250");

    this.prompts1 = new Label("   S1");
    this.inputs1 = new TextField(3);
    this.inputs1.addActionListener(this);
    this.inputs1.setText("0");
    this.prompts2 = new Label("   S2");
    this.inputs2 = new TextField(3);
    this.inputs2.addActionListener(this);
    this.inputs2.setText("300");
    this.prompttime = new Label("  time");
    this.inputtime = new TextField(3);
    this.inputtime.addActionListener(this);
    this.inputtime.setText("500");
    this.promptn1 = new Label("    ");
    repaint();

    this.buttonPanel.setLayout(new GridLayout(2, 7));
    this.buttonPanel.add(this.butt);

    this.buttonPanel.add(this.prompt1);
    this.buttonPanel.add(this.input1);
    this.buttonPanel.add(this.prompt2);
    this.buttonPanel.add(this.input2);

    this.buttonPanel.add(this.prompt4);
    this.buttonPanel.add(this.input4);

    this.buttonPanel.add(this.promptn1);

    this.buttonPanel.add(this.prompts1);
    this.buttonPanel.add(this.inputs1);
    this.buttonPanel.add(this.prompts2);
    this.buttonPanel.add(this.inputs2);
    this.buttonPanel.add(this.prompttime);
    this.buttonPanel.add(this.inputtime);

    setLayout(new BorderLayout());
    add(this.buttonPanel, "North");
  }

  public void paint(Graphics paramGraphics)
  {
    // this sets up the graph
    paramGraphics.setColor(Color.black);
    paramGraphics.fillRect(4, 50, 340, 200);
    paramGraphics.setColor(Color.blue);
    paramGraphics.drawRect(4, 50, 340, 200);
    paramGraphics.setColor(Color.white);
    paramGraphics.drawLine(30, 75, 30, 215);
    paramGraphics.drawLine(30, 215, 330, 215);
    paramGraphics.drawString("Time (ms)", 140, 245);
    paramGraphics.drawString("V", 13, 145);
    paramGraphics.drawString("(mV)", 5, 155);
    if (!this.firsttime)
    {

      // draw the labels for "S1", "S2", "500", "U", and "V"
      paramGraphics.setColor(Color.white);
      this.iis1 = ((int)(30.0D + this.s1 / (this.nt * 2)));
      paramGraphics.drawLine(this.iis1 + 5, 218, this.iis1 + 5, 210);
      paramGraphics.drawString("S1", this.iis1, 230);
      this.iis2 = ((int)(30.0D + this.s2 / (this.nt * 2)));
      paramGraphics.drawLine(this.iis2 + 5, 218, this.iis2 + 5, 210);
      paramGraphics.drawString("S2", this.iis2, 230);
      this.iiss = (this.nt * 500);
      paramGraphics.drawString(" " + this.iiss + " ", 275, 230);
      paramGraphics.setColor(Color.red);
      paramGraphics.drawString("U,", 290, 75);
      paramGraphics.setColor(Color.green);
      paramGraphics.drawString("v", 300, 75);

      paramGraphics.setColor(Color.red);

      double d1 = 0.25D;
      double d2 = this.eps * d1 * 0.5D;
      double d3 = 0.0D;
      double d4 = 0.0D;
      double d5 = 0.0D;
      double d6 = 0.0D;
      int i = 175;
      int j = 175;
      int k = 35;
      this.is2 = ((int)this.s2);
      this.is1 = ((int)this.s1);
      this.ntime = ((int)this.time);

      // for each increment of time
      for (int m = 0; m < this.ntime; m++) {

        /// if the time is either s1 or s2, change the value of Vm to 1.5
        if (m == this.is1) d5 = 1.5D;
        if (m == this.is2) d5 = 1.5D;
        for (int n = 0; n < 4; n++)
        {

          // accumulate voltage
          if (d5 >= 1.0D)
            this.alft = 1.0D;
          else
            this.alft = 0.0D;


          this.vv = (d6 / this.vstar);

          // Partial derivative of Vm (equation 1)                                                        h(Vm)
          d5 += d1 * 5.0D * (-d5 + (this.gam - Math.pow(this.vv, this.xm)) * (d5 * d5 - 0.23D * d5 * d5 * d5)) / 7.5D;
          
          this.bett = (1.0D - this.alft);

          // Heaviside
          if (d5 >= 1.0D)
            this.alf = 1.0D;
          else {
            this.alf = 0.0D;
          }

          this.bet = (1.0D - this.alf);
          this.top = (d6 + d2 * (this.alf + this.alft * (1.0D - d6) - this.bett * d6));
          this.bot = (1.0D + d2 * (this.alf + this.bet));
          d6 = this.top / this.bot;

          d3 += d1;
        }
        if (m % this.nt + 1 == 1) {
          int i1 = (int)(35.0D + d3 / (2 * this.nt));
          int i2 = (int)(185.0D - d5 * 25.0D);
          int i3 = (int)(185.0D - d6 * 50.0D);
          paramGraphics.setColor(Color.red);
          paramGraphics.drawLine(k, i, i1, i2);
          paramGraphics.setColor(Color.green);
          paramGraphics.drawLine(k, j, i1, i3);
          j = i3;
          i = i2;
          k = i1;
        }
      }
    }
  }
}
