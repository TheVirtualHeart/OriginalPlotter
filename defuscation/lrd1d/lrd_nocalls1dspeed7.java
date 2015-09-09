import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Scrollbar;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

public class lrd_nocalls1dspeed7
  extends Applet
  implements MouseListener, ActionListener, ItemListener, Runnable
{
  Point p;
  private Panel buttonPanel;
  private Panel checkPanel;
  private Panel stimPanel;
  private Label prompt1;
  private Label prompt2;
  private Label prompt3;
  private Label prompt4;
  private Label prompt5;
  private Label promptg;
  private Label promptg4;
  private Label promptg2;
  private Label promptg3;
  private Label promptg5;
  private Label promptgg;
  private Label slide1;
  private Label slide2;
  private Label slide3;
  private Label slide4;
  private TextField input1;
  private TextField input2;
  private TextField input3;
  private TextField input4;
  private TextField input5;
  private Label prompts1;
  private Label prompttime;
  private TextField inputs1;
  private TextField inputtime;
  private Label sstrength;
  private Label sduration;
  private Label slength;
  private Label sposition;
  private TextField sstrengtht;
  private TextField sdurationt;
  private TextField slengtht;
  private TextField spositiont;
  private double result;
  Scrollbar horiz;
  Scrollbar slider1;
  Button butt;
  Button butp;
  Button butf;
  Button buts;
  Button butr;
  Button butrs;
  Button butla;
  Button butsm;
  Button butup;
  Button butdown;
  Button bstimulus;
  Checkbox checkv;
  Checkbox checkm;
  Checkbox checkh;
  Checkbox checkj;
  Checkbox checkd;
  Checkbox checkf;
  Checkbox checkxs1;
  Checkbox checkca;
  Checkbox checkxs2;
  Checkbox checkxr;
  Checkbox checkb;
  Checkbox checkg;
  Checkbox checkna;
  Checkbox checkk;
  Color background;
  Image Buffer;
  Graphics gBuffer;
  boolean firsttime = true;
  boolean firsttime2 = true;
  boolean down;
  double s1 = 20.0D;
  double s2 = 320.0D;
  double time = 5.0D;
  int is1;
  int is2;
  int ntime;
  int iis1;
  int iis2;
  int iiss;
  int nt = 1;
  int xf;
  int izoom;
  double zoom;
  double valueee;
  int ncable = 1;
  double nbr = 1.0D;
  String optionc = "Cable";
  String optionr = "Ring";
  double pp;
  double upp;
  int ndtime;
  int nspeed = 1;
  int so = 205;
  int soo = 205;
  int xo = 20;
  int ngraph;
  int nx = 402;
  int mo = 185;
  int ho = 85;
  int jo = 85;
  int ddo = 185;
  int fo = 85;
  int x1o = 185;
  int sso = 185;
  int nstop;
  double u;
  double ca;
  double xca;
  double x1;
  int msen;
  int hsen;
  int jsen;
  int dsen;
  int fsen;
  int vsen = 10;
  int casen;
  int x1sen;
  int x2sen;
  int xrsen;
  int bsen;
  int gsen;
  int nasen;
  int ksen;
  double dtime;
  double Istim;
  double tstimul;
  double xstim;
  double lstim;
  int nstimdur;
  int nxs1;
  int nxs1i;
  int nxs1f;
  double dvt = 0.01D;
  double vmin = -115.0D;
  int ntab;
  double tab;
  int ntab2;
  double tab2;
  int xpos;
  double dx = 0.02D;
  double x;
  double s;
  int ndim = 805;
  int kk;
  int icount;
  int printdata;
  int printval;
  double l = 0.01D;
  double a = 0.0011D;
  double pi = 3.141592D;
  double vcell;
  double ageo;
  double acap;
  double vmyo;
  double vmito;
  double vsr;
  double vnsr;
  double vjsr;
  double vcleft;
  double[] v = new double[ndim];
  double[] vnew = new double[ndim];
  double[] dvdt = new double[ndim];
  double[] dvdtnew = new double[ndim];
  int[] flag = new int[ndim];
  int[] stimsen = new int[ndim];
  double[] dt = new double[ndim];
  double t;
  double udt;
  double lap;
  double[] xlap = new double[ndim];
  int[] utsc = new int[ndim];
  int[] nxstep = new int[ndim];
  int steps;
  int increment;
  int bcl = 150;
  int beats = 18;
  double[] vmax = new double[beats];
  double[] dvdtmax = new double[beats];
  double[] apd = new double[beats];
  double[] toneapd = new double[beats];
  double[] ttwoapd = new double[beats];
  double[] rmbp = new double[beats];
  double[] nair = new double[beats];
  double[] cair = new double[beats];
  double[] caimax = new double[beats];
  int i;
  double st;
  double[] tstim = new double[ndim];
  double[] stimtime = new double[ndim];
  double[] it = new double[ndim];
  double R = 8314.0D;
  double frdy = 96485.0D;
  double temp = 310.0D;
  double frdy_Rtemp = frdy / (R * temp);
  double ffrdy_Rtemp = frdy * frdy_Rtemp;
  double Rtemp_frdy = 1.0D / frdy_Rtemp;
  double zna = 1.0D;
  double zk = 1.0D;
  double zca = 2.0D;
  double[] nai = new double[ndim];
  double nao;
  double nabm;
  double dnao;
  double[] ki = new double[ndim];
  double ko;
  double kbm;
  double dko;
  double[] cai = new double[ndim];
  double cao;
  double cabm;
  double dcao;
  double cmdn;
  double trpn;
  double[] nsr = new double[ndim];
  double[] jsr = new double[ndim];
  double csqn;
  double taudiff = 1000.0D;
  double naiont;
  double dnai;
  double kiont;
  double dki;
  double dnsr;
  double iup;
  double ileak;
  double kleak;
  double kmup = 9.2E-4D;
  double iupbar = 0.00875D;
  double nsrbar = 15.0D;
  double djsr;
  double tauon = 0.5D;
  double tauoff = 0.5D;
  double[] tcicr = new double[ndim];
  double[] irelcicr = new double[ndim];
  double csqnth = 8.75D;
  double gmaxrel = 150.0D;
  double grelbarjsrol;
  double greljsrol;
  double[] tjsrol = new double[ndim];
  double ireljsrol;
  double csqnbar = 10.0D;
  double kmcsqn = 0.8D;
  double bjsr;
  double cjsr;
  double on;
  double off;
  double magrel;
  double[] dcaiont = new double[ndim];
  double[] dcaiontnew = new double[ndim];
  double[] caiontold = new double[ndim];
  double itr;
  double tautr = 180.0D;
  double[] caiont = new double[ndim];
  double dcai;
  double catotal;
  double bmyo;
  double cmyo;
  double dmyo;
  double gpig;
  double cmdnbar = 0.05D;
  double trpnbar = 0.07D;
  double kmcmdn = 0.00238D;
  double kmtrpn = 5.0E-4D;
  double ina;
  double gna = 16.0D;
  double ena;
  double am;
  double bm;
  double ah;
  double bh;
  double aj;
  double bj;
  double mtau;
  double htau;
  double jtau;
  double[] mss = new double['?'];
  double[] hss = new double['?'];
  double[] jss = new double['?'];
  double[] omtau = new double['?'];
  double[] ohtau = new double['?'];
  double[] ojtau = new double['?'];
  double[] m = new double[ndim];
  double[] h = new double[ndim];
  double[] j = new double[ndim];
  double ilca;
  double ilcana;
  double ilcak;
  double ilcatot;
  double ibarca;
  double ibarna;
  double ibark;
  double[] d = new double[ndim];
  double taud;
  double[] f = new double[ndim];
  double tauf;
  double[] dss = new double['?'];
  double[] otaud = new double['?'];
  double[] fss = new double['?'];
  double[] otauf = new double['?'];
  double fca;
  double kmca = 6.0E-4D;
  double pca = 5.4E-4D;
  double gacai = 1.0D;
  double gacao = 0.341D;
  double pna = 6.75E-7D;
  double ganai = 0.75D;
  double ganao = 0.75D;
  double pk = 1.93E-7D;
  double gaki = 0.75D;
  double gako = 0.75D;
  double icat;
  double gcat = 0.05D;
  double eca;
  double[] b = new double[ndim];
  double[] bss = new double['?'];
  double[] otaub = new double['?'];
  double[] gss = new double['?'];
  double[] otaug = new double['?'];
  double taub;
  double[] gg = new double[ndim];
  double taug;
  double ikr;
  double gkr;
  double ekr;
  double[] xr = new double[ndim];
  double tauxr;
  double[] xrss = new double['?'];
  double[] otauxr = new double['?'];
  double[] r = new double['?'];
  double iks;
  double gks;
  double eks;
  double[] xs1 = new double[ndim];
  double tauxs1;
  double[] xs2 = new double[ndim];
  double[] xs1ss = new double['?'];
  double[] otauxs1 = new double['?'];
  double tauxs2;
  double prnak = 0.01833D;
  double iki;
  double gki;
  double eki;
  double aki;
  double bki;
  double kin;
  double ikp;
  double gkp;
  double ekp;
  double[] kp = new double['?'];
  double ikna;
  double pona;
  double[] pov = new double['?'];
  double ekna;
  double gkna = 0.12848D;
  double nkna = 2.8D;
  double kdkna = 66.0D;
  double ikatp;
  double ekatp;
  double gkbaratp;
  double gkatp;
  double patp;
  double natp = 0.24D;
  double nicholsarea = 5.0E-5D;
  double atpi = 3.0D;
  double hatp = 2.0D;
  double katp = 0.25D;
  double ito;
  double gitodv;
  double ekdv;
  double rvdv;
  double zdv;
  double azdv;
  double bzdv;
  double tauzdv;
  double zssdv;
  double ydv;
  double aydv;
  double bydv;
  double tauydv;
  double yssdv;
  double inaca;
  double c1 = 2.5E-4D;
  double c2 = 1.0E-4D;
  double gammas = 0.15D;
  double inak;
  double[] fnak = new double['?'];
  double sigma;
  double ibarnak = 2.25D;
  double kmnai = 10.0D;
  double kmko = 1.5D;
  double insna;
  double insk;
  double ibarnsna;
  double ibarnsk;
  double pnsca = 1.75E-7D;
  double kmnsca = 0.0012D;
  double ipca;
  double ibarpca = 1.15D;
  double kmpca = 5.0E-4D;
  double icab;
  double gcab;
  double ecan;
  double inab;
  double gnab = 0.004D;
  double enan;
  double[] expzca = new double['?'];
  double[] expzna = new double['?'];
  double[] expzk = new double['?'];
  double[] expgammas = new double['?'];
  double[] expvfrdy = new double['?'];
  double[] expm = new double['?'];
  double[] exph = new double['?'];
  double[] expj = new double['?'];
  double[] expd = new double['?'];
  double[] expf = new double['?'];
  double[] expb = new double['?'];
  double[] expg = new double['?'];
  double[] expxr = new double['?'];
  double[] expxs1 = new double['?'];
  double[] expxs2 = new double['?'];
  int speed1 = 250;
  int speed2 = 400;
  double[] expaki1 = new double['?'];
  double[] expeki1 = new double['?'];
  double[] expeki2 = new double['?'];
  double[] expeki3 = new double['?'];
  double[] expeki4 = new double['?'];
  double[] expbki1 = new double['?'];
  double[] expbki2 = new double['?'];
  double[] expbki3 = new double['?'];
  double vv;
  
  public void init()
  {
    ntime = ((int)time);
    
    background = new Color(192, 192, 192);
    setSize(675, 330);
    setBackground(background);
    
    Thread localThread = new Thread(this);
    localThread.start();
    addMouseListener(this);
    
    Buffer = createImage(520, 290);
    gBuffer = Buffer.getGraphics();
    for (int k = 0; k < 20000; k++)
    {
      double d1 = vmin + k * dvt;
      if (Math.abs(d1 + 47.13D) < 1.0E-6D) {
        am = 3.2D;
      } else {
        am = (0.32D * (d1 + 47.13D) / (1.0D - Math.exp(-0.1D * (d1 + 47.13D))));
      }
      bm = (0.08D * Math.exp(-d1 / 11.0D));
      if (d1 < -40.0D)
      {
        ah = (0.135D * Math.exp((80.0D + d1) / -6.8D));
        bh = (3.56D * Math.exp(0.079D * d1) + 310000.0D * Math.exp(0.35D * d1));
        aj = ((-127140.0D * Math.exp(0.2444D * d1) - 3.474E-5D * Math.exp(-0.04391D * d1)) * ((d1 + 37.78D) / (1.0D + Math.exp(0.311D * (d1 + 79.23D)))));
        bj = (0.1212D * Math.exp(-0.01052D * d1) / (1.0D + Math.exp(-0.1378D * (d1 + 40.14D))));
      }
      else
      {
        ah = 0.0D;
        bh = (1.0D / (0.13D * (1.0D + Math.exp((d1 + 10.66D) / -11.1D))));
        aj = 0.0D;
        bj = (0.3D * Math.exp(-2.535E-7D * d1) / (1.0D + Math.exp(-0.1D * (d1 + 32.0D))));
      }
      mtau = (1.0D / (am + bm));
      htau = (1.0D / (ah + bh));
      jtau = (1.0D / (aj + bj));
      
      mss[k] = (am * mtau);
      hss[k] = (ah * htau);
      jss[k] = (aj * jtau);
      omtau[k] = (1.0D / mtau);
      ohtau[k] = (1.0D / htau);
      ojtau[k] = (1.0D / jtau);
      
      dss[k] = (1.0D / (1.0D + Math.exp(-(d1 + 10.0D) / 6.24D)));
      double d2 = 0.035D * (d1 + 10.0D);
      if (d2 == 0.0D)
      {
        otaud[k] = 0.0D;
      }
      else
      {
        taud = (dss[k] * (1.0D - Math.exp(-(d1 + 10.0D) / 6.24D)) / (0.035D * (d1 + 10.0D)));
        otaud[k] = (1.0D / taud);
      }
      fss[k] = (1.0D / (1.0D + Math.exp((d1 + 32.0D) / 8.0D)) + 0.6D / (1.0D + Math.exp((50.0D - d1) / 20.0D)));
      tauf = (1.0D / (0.0197D * Math.exp(-Math.pow(0.0337D * (d1 + 10.0D), 2.0D)) + 0.02D));
      otauf[k] = (1.0D / tauf);
      
      bss[k] = (1.0D / (1.0D + Math.exp(-(d1 + 14.0D) / 10.8D)));
      taub = (3.7D + 6.1D / (1.0D + Math.exp((d1 + 25.0D) / 4.5D)));
      otaub[k] = (1.0D / taub);
      
      gss[k] = (1.0D / (1.0D + Math.exp((d1 + 60.0D) / 5.6D)));
      if (d1 <= 0.0D) {
        taug = (-0.875D * d1 + 12.0D);
      } else {
        taug = 12.0D;
      }
      otaug[k] = (1.0D / taug);
      
      xrss[k] = (1.0D / (1.0D + Math.exp(-(d1 + 21.5D) / 7.5D)));
      tauxr = (1.0D / (0.00138D * (d1 + 14.2D) / (1.0D - Math.exp(-0.123D * (d1 + 14.2D))) + 6.1E-4D * (d1 + 38.9D) / (Math.exp(0.145D * (d1 + 38.9D)) - 1.0D)));
      otauxr[k] = (1.0D / tauxr);
      
      r[k] = (1.0D / (1.0D + Math.exp((d1 + 9.0D) / 22.4D)));
      if (d1 != -30.0D)
      {
        tauxs1 = (1.0D / (7.19E-5D * (d1 + 30.0D) / (1.0D - Math.exp(-0.148D * (d1 + 30.0D))) + 1.31E-4D * (d1 + 30.0D) / (Math.exp(0.0687D * (d1 + 30.0D)) - 1.0D)));
        otauxs1[k] = (1.0D / tauxs1);
      }
      else
      {
        otauxs1[k] = otauxs1[(k - 1)];
      }
      xs1ss[k] = (1.0D / (1.0D + Math.exp(-(d1 - 1.5D) / 16.7D)));
      
      kp[k] = (1.0D / (1.0D + Math.exp((7.488D - d1) / 5.98D)));
      
      nao = 140.0D;
      sigma = ((Math.exp(nao / 67.3D) - 1.0D) / 7.0D);
      
      fnak[k] = (1.0D / (1.0D + 0.1245D * Math.exp(-0.1D * d1 * frdy / (R * temp)) + 0.0365D * sigma * Math.exp(-d1 * frdy / (R * temp))));
      
      expzca[k] = Math.exp(zca * d1 * frdy_Rtemp);
      if (expzca[k] == 1.0D) {
        expzca[k] = expzca[(k - 1)];
      }
      expzna[k] = Math.exp(zna * d1 * frdy_Rtemp);
      expzk[k] = Math.exp(zk * d1 * frdy_Rtemp);
      if (expzna[k] == 1.0D) {
        expzna[k] = expzna[(k - 1)];
      }
      if (expzk[k] == 1.0D) {
        expzk[k] = expzk[(k - 1)];
      }
      expgammas[k] = Math.exp((gammas - 1.0D) * d1 * frdy_Rtemp);
      expvfrdy[k] = Math.exp(d1 * frdy_Rtemp);
      
      udt = 0.01D;
      
      expm[k] = Math.exp(-udt * omtau[k]);
      exph[k] = Math.exp(-udt * ohtau[k]);
      expj[k] = Math.exp(-udt * ojtau[k]);
      expd[k] = Math.exp(-udt * otaud[k]);
      expf[k] = Math.exp(-udt * otauf[k]);
      expb[k] = Math.exp(-udt / taub);
      expg[k] = Math.exp(-udt / taug);
      expxr[k] = Math.exp(-udt * otauxr[k]);
      expxs1[k] = Math.exp(-udt * otauxs1[k]);
      expxs2[k] = Math.exp(-udt * otauxs1[k] * 0.25D);
      
      vv = (-93.0D + k * 5.0E-4D);
      expaki1[k] = Math.exp(0.2385D * (d1 - 59.215D));
      expeki1[k] = Math.exp(-vv * 0.2385D);
      expeki2[k] = Math.exp(-vv * 0.08032D);
      expeki3[k] = Math.exp(-vv * 0.06175D);
      expeki4[k] = Math.exp(vv * 0.5143D);
      expbki1[k] = Math.exp(0.08032D * (d1 + 5.476D));
      expbki2[k] = Math.exp(0.06175D * (d1 - 594.31D));
      expbki3[k] = Math.exp(-0.5143D * (d1 + 4.753D));
    }
    buttonPanel = new Panel();
    butt = new Button("Start");
    butt.setBackground(Color.green);
    butt.addActionListener(this);
    add(butt);
    butt.reshape(0, 0, 65, 25);
    
    prompt2 = new Label("gna");
    add(prompt2);
    prompt2.reshape(85, 0, 25, 30);
    
    input2 = new TextField(5);
    input2.addActionListener(this);
    add(input2);
    input2.reshape(115, 0, 50, 30);
    
    prompt3 = new Label("gnab");
    add(prompt3);
    prompt3.reshape(185, 0, 40, 30);
    
    input3 = new TextField(5);
    input3.addActionListener(this);
    add(input3);
    input3.reshape(225, 0, 50, 30);
    
    prompt4 = new Label("gcat");
    add(prompt4);
    prompt4.reshape(300, 0, 25, 30);
    
    input4 = new TextField(3);
    input4.addActionListener(this);
    add(input4);
    input4.reshape(325, 0, 50, 30);
    
    slider1 = new Scrollbar(0, nx - 1, 60, 20, 860);
    add(slider1);
    slider1.reshape(405, 14, 165, 15);
    
    horiz = new Scrollbar(0, 1, 25, 1, 280);
    add(horiz);
    horiz.reshape(120, 302, 350, 15);
    
    slide1 = new Label("Simulation Speed");
    slide2 = new Label("Fast");
    slide3 = new Label("Slow");
    add(slide1);
    add(slide2);
    add(slide3);
    slide2.reshape(75, 299, 50, 21);
    slide3.reshape(480, 299, 50, 21);
    slide1.reshape(247, 312, 150, 20);
    
    prompts1 = new Label("Cable Length");
    Font localFont = new Font("Helvetica", 1, 9);
    prompts1.setFont(localFont);
    add(prompts1);
    prompts1.reshape(460, 0, 68, 13);
    
    slide1.setFont(localFont);
    
    input2.setText("16.0");
    input3.setText("0.004");
    input4.setText("0.05");
    
    inputs1 = new TextField(5);
    inputs1.addActionListener(this);
    inputs1.setText(String.valueOf(nx * dx));
    prompttime = new Label(" time ");
    inputtime = new TextField(5);
    inputtime.addActionListener(this);
    inputtime.setText("5");
    butup = new Button("<");
    butup.addActionListener(this);
    butdown = new Button(">");
    butdown.addActionListener(this);
    
    stimPanel = new Panel();
    bstimulus = new Button("Apply S1");
    bstimulus.addActionListener(this);
    add(bstimulus);
    bstimulus.reshape(0, 267, 65, 28);
    
    Choice localChoice = new Choice();
    localChoice.addItem("Cable");
    localChoice.addItem("Ring");
    localChoice.addItemListener(this);
    add(localChoice);
    localChoice.reshape(0, 302, 65, 28);
    
    sstrength = new Label("Strength");
    sstrengtht = new TextField(4);
    add(sstrength);
    sstrength.reshape(75, 270, 60, 25);
    sstrengtht.setText("-5.0");
    add(sstrengtht);
    sstrengtht.reshape(135, 267, 55, 30);
    
    sduration = new Label("Duration");
    add(sduration);
    sduration.reshape(195, 270, 60, 25);
    
    sdurationt = new TextField(4);
    sdurationt.setText("15");
    add(sdurationt);
    sdurationt.reshape(255, 267, 50, 30);
    
    slength = new Label("Length");
    add(slength);
    slength.reshape(310, 270, 48, 25);
    
    slengtht = new TextField(4);
    slengtht.setText("0.4");
    add(slengtht);
    slengtht.reshape(360, 267, 55, 30);
    
    sposition = new Label("Position");
    add(sposition);
    sposition.reshape(420, 270, 55, 25);
    spositiont = new TextField(4);
    spositiont.setText("0.75");
    add(spositiont);
    spositiont.reshape(477, 267, 50, 30);
    
    checkPanel = new Panel();
    
    butp = new Button("      ");
    butp.addActionListener(this);
    promptg = new Label("       ");
    promptgg = new Label("      ");
    promptg4 = new Label("      ");
    promptg2 = new Label("       ");
    promptg3 = new Label("      ");
    promptg5 = new Label("      ");
    
    checkv = new Checkbox("Voltage", true);
    checkv.addItemListener(this);
    checkm = new Checkbox("m gate", false);
    checkm.addItemListener(this);
    checkh = new Checkbox("h gate", false);
    checkh.addItemListener(this);
    checkj = new Checkbox("j gate", false);
    checkj.addItemListener(this);
    checkd = new Checkbox("d gate", false);
    checkd.addItemListener(this);
    checkf = new Checkbox("f gate", false);
    checkf.addItemListener(this);
    checkxs1 = new Checkbox("xs1 gate", false);
    checkxs1.addItemListener(this);
    checkxs2 = new Checkbox("xs2 gate", false);
    checkxs2.addItemListener(this);
    checkxr = new Checkbox("xr gate", false);
    checkxr.addItemListener(this);
    checkb = new Checkbox("b gate", false);
    checkb.addItemListener(this);
    checkg = new Checkbox("g gate", false);
    checkg.addItemListener(this);
    checkna = new Checkbox("[Na]", false);
    checkna.addItemListener(this);
    checkk = new Checkbox("[K]", false);
    checkk.addItemListener(this);
    checkca = new Checkbox("[Ca]", false);
    checkca.addItemListener(this);
    
    checkPanel.setLayout(new GridLayout(9, 2));
    
    checkPanel.add(promptgg);
    checkPanel.add(butp);
    checkPanel.add(checkv);
    checkPanel.add(checkm);
    checkPanel.add(checkh);
    checkPanel.add(checkj);
    checkPanel.add(checkd);
    checkPanel.add(checkf);
    checkPanel.add(checkxs1);
    checkPanel.add(checkxs2);
    checkPanel.add(checkxr);
    checkPanel.add(checkb);
    checkPanel.add(checkg);
    checkPanel.add(checkca);
    
    checkPanel.add(promptg2);
    checkPanel.add(promptg3);
    checkPanel.add(promptg4);
    checkPanel.add(promptg5);
    
    setLayout(new BorderLayout());
    
    add(checkPanel, "East");
    
    printdata = 60;
    
    vcell = (1000.0D * pi * a * a * l);
    ageo = (2.0D * pi * a * a + 2.0D * pi * a * l);
    acap = (ageo * 2.0D);
    vmyo = (vcell * 0.68D);
    vmito = (vcell * 0.26D);
    vsr = (vcell * 0.06D);
    vnsr = (vcell * 0.0552D);
    vjsr = (vcell * 0.0048D);
    vcleft = (vcell * 0.12D / 0.88D);
    
    t = 0.0D;
    udt = 0.01D;
    lap = (udt * 0.001D / 4.0E-4D);
    
    steps = 50;
    st = -80.0D;
    for (kk = 0; kk < 800; kk += 1)
    {
      tstim[kk] = 0.0D;
      stimtime[kk] = 10.0D;
      irelcicr[kk] = 0.0D;
      
      v[kk] = -88.654973D;
      dvdt[kk] = 0.0D;
      
      nai[kk] = 12.236437D;
      nao = 140.0D;
      nabm = 140.0D;
      ki[kk] = 136.89149D;
      ko = 4.5D;
      kbm = 4.5D;
      cai[kk] = 7.9E-5D;
      cao = 1.8D;
      cabm = 1.8D;
      
      m[kk] = 8.38E-4D;
      h[kk] = 0.993336D;
      j[kk] = 0.995484D;
      d[kk] = 3.0E-6D;
      f[kk] = 0.999745D;
      xs1[kk] = 0.004503D;
      xs2[kk] = 0.004503D;
      xr[kk] = 1.29E-4D;
      b[kk] = 9.94E-4D;
      gg[kk] = 0.994041D;
      zdv = 0.0120892D;
      ydv = 0.999978D;
      
      grelbarjsrol = 0.0D;
      tjsrol[kk] = 1000.0D;
      tcicr[kk] = 1000.0D;
      jsr[kk] = 1.179991D;
      nsr[kk] = 1.179991D;
      trpn = 0.0143923D;
      cmdn = 0.00257849D;
      csqn = 6.97978D;
      flag[kk] = 0;
      dt[kk] = udt;
      utsc[kk] = 50;
      dcaiont[kk] = 0.0D;
      caiont[kk] = 0.0D;
      i = -1;
      xlap[kk] = 0.0D;
      nxstep[kk] = 0;
      stimsen[kk] = 0;
    }
  }
  
  public void mouseClicked(MouseEvent paramMouseEvent) {}
  
  public void mouseEntered(MouseEvent paramMouseEvent) {}
  
  public void mouseExited(MouseEvent paramMouseEvent) {}
  
  public void mousePressed(MouseEvent paramMouseEvent)
  {
    p = paramMouseEvent.getPoint();
    pp = p.y;
    if ((pp > 30.0D) && (pp < 266.0D))
    {
      ndtime = 1;
      upp = ((85.0D - pp) / 1.7D);
      pp = (p.x - 20);
      if (upp < -90.0D) {
        upp = -90.0D;
      }
      if (upp > 80.0D) {
        upp = 79.9D;
      }
      double d1 = pp * izoom / 501.0D;
      int k = (int)d1;
      if (k > 0)
      {
        v[k] = upp;
        v[(k + 1)] = upp;
        v[(k + 2)] = upp;
        nxstep[k] = 0;
        nxstep[(k + 1)] = 0;
        nxstep[(k + 2)] = 0;
        if (k > 1)
        {
          v[(k - 1)] = upp;
          nxstep[(k - 1)] = 0;
        }
        if (k > 2)
        {
          v[(k - 2)] = upp;
          nxstep[(k - 1)] = 0;
        }
      }
    }
    down = true;
  }
  
  public void mouseReleased(MouseEvent paramMouseEvent)
  {
    down = false;
  }
  
  public void update(Graphics paramGraphics)
  {
    paint(paramGraphics);
  }
  
  public void paint(Graphics paramGraphics)
  {
    if (firsttime)
    {
      gBuffer.setColor(Color.black);
      gBuffer.fillRect(0, 30, 520, 230);
      gBuffer.setColor(Color.white);
      gBuffer.drawLine(20, 40, 20, 230);
      gBuffer.drawLine(20, 230, 520, 230);
      gBuffer.drawString("-85", 2, 240);
      gBuffer.drawString("mV", 1, 140);
      gBuffer.drawString("10", 2, 50);
      gBuffer.drawString("time=" + String.valueOf((float)dtime) + "ms", 400, 55);
      if (ncable == 1) {
        gBuffer.drawString("Length of cable: " + String.valueOf((float)(nx * dx)) + " cm ", 188, 249);
      } else {
        gBuffer.drawString("Length of ring: " + String.valueOf((float)(nx * dx)) + " cm ", 188, 249);
      }
      gBuffer.drawString("<---------------------| ", 20, 247);
      gBuffer.drawString("|---------------------> ", 356, 247);
      
      paramGraphics.drawImage(Buffer, 0, 0, this);
    }
    else
    {
      izoom = slider1.getValue();
      if (izoom > nx) {
        for (k = nx - 1; k < izoom; k++)
        {
          v[k] = v[(nx - 2)];
          m[k] = m[(nx - 2)];
          h[k] = h[(nx - 2)];
          j[k] = j[(nx - 2)];
          d[k] = d[(nx - 2)];
          f[k] = f[(nx - 2)];
          irelcicr[k] = irelcicr[(nx - 2)];
          dvdt[k] = dvdt[(nx - 2)];
          nai[k] = nai[(nx - 2)];
          ki[k] = ki[(nx - 2)];
          cai[k] = cai[(nx - 2)];
          xs1[k] = xs1[(nx - 2)];
          xs2[k] = xs2[(nx - 2)];
          xr[k] = xr[(nx - 2)];
          b[k] = b[(nx - 2)];
          gg[k] = gg[(nx - 2)];
          
          jsr[k] = jsr[(nx - 2)];
          nsr[k] = nsr[(nx - 2)];
          flag[k] = flag[(nx - 2)];
          dt[k] = dt[(nx - 2)];
          
          caiont[k] = caiont[(nx - 2)];
        }
      }
      nx = (izoom - 1);
      if (nx > 300)
      {
        speed1 = 250;
        speed2 = 400;
      }
      if ((nx <= 300) && (nx > 200))
      {
        speed1 = 80;
        speed2 = 100;
      }
      if ((nx <= 200) && (nx > 175))
      {
        speed1 = 20;
        speed2 = 20;
      }
      if (nx <= 175)
      {
        speed1 = 4;
        speed2 = 4;
      }
      int k = horiz.getValue();
      valueee = k;
      nspeed = k;
      
      gBuffer.setColor(Color.white);
      iis1 = ((int)(30.0D + s1 / (2 * nt)));
      gBuffer.drawLine(iis1 + 5, 215, iis1 + 5, 208);
      gBuffer.drawString("S1", iis1, 227);
      iis2 = ((int)(30.0D + s2 / (2 * nt)));
      gBuffer.drawLine(iis2 + 5, 215, iis2 + 5, 208);
      gBuffer.drawString("S2", iis2, 227);
      iiss = (nt * 650);
      gBuffer.drawString(" " + iiss + " ", 345, 227);
      
      xpos = 80;
      
      gBuffer.setColor(Color.red);
      if (nstop < 5) {
        for (increment = 0; increment < steps; increment += 1)
        {
          icount += 1;
          if (ndtime == 1) {
            dtime += udt;
          }
          if (ncable == 1)
          {
            v[0] = v[2];
            v[nx] = v[(nx - 1)];
          }
          else
          {
            v[1] = v[(nx - 1)];
            v[nx] = v[2];
            v[0] = v[(nx - 2)];
          }
          for (kk = 1; kk < nx; kk += 1)
          {
            if (v[kk] > 80.0D) {
              nxstep[kk] = 0;
            }
            nxstep[kk] = speed2;
            if ((v[kk] > 10.0D) || (j[kk] < 0.1D)) {
              nxstep[kk] = speed1;
            }
            if (Math.abs(dvdt[kk]) > 5.0D) {
              nxstep[kk] = 0;
            }
            if ((v[kk] > 0.0D) && (j[kk] > 0.3D)) {
              nxstep[kk] = 0;
            }
            if ((xlap[kk] > 0.15D) && (j[kk] > 0.2D)) {
              nxstep[kk] = 0;
            }
            if (j[kk] * m[kk] > 0.5D) {
              nxstep[kk] = 0;
            }
            if ((utsc[kk] >= nxstep[kk]) || (irelcicr[kk] > 0.01D) || (stimsen[kk] == 1))
            {
              tab = ((v[kk] - vmin) / dvt);
              ntab = ((int)tab);
              
              ena = (Rtemp_frdy * Math.log(nao / nai[kk]));
              
              ina = (gna * m[kk] * m[kk] * m[kk] * h[kk] * j[kk] * (v[kk] - ena));
              if (Math.abs(v[kk]) < 1.0E-6D)
              {
                ibarca = (0.00108D * (gacai * cai[kk] * expzca[ntab] - gacao * cao));
                ibarna = (6.75E-7D * (ganai * nai[kk] * expzna[ntab] - ganao * nao));
                ibark = (1.93E-7D * (gaki * ki[kk] * expzk[ntab] - gako * ko));
              }
              else
              {
                ibarca = (pca * zca * zca * (v[kk] * ffrdy_Rtemp) * ((gacai * cai[kk] * expzca[ntab] - gacao * cao) / (expzca[ntab] - 1.0D)));
                ibarna = (pna * zna * zna * (v[kk] * ffrdy_Rtemp) * ((ganai * nai[kk] * expzna[ntab] - ganao * nao) / (expzna[ntab] - 1.0D)));
                ibark = (pk * zk * zk * (v[kk] * ffrdy_Rtemp) * ((gaki * ki[kk] * expzk[ntab] - gako * ko) / (expzk[ntab] - 1.0D)));
              }
              fca = (1.0D / (1.0D + cai[kk] / kmca));
              
              ilca = (d[kk] * f[kk] * fca * ibarca);
              ilcana = (d[kk] * f[kk] * fca * ibarna);
              ilcak = (d[kk] * f[kk] * fca * ibark);
              
              ilcatot = (ilca + ilcana + ilcak);
              
              eca = (Rtemp_frdy * 0.5D * Math.log(cao / cai[kk]));
              
              icat = (gcat * b[kk] * b[kk] * gg[kk] * (v[kk] - eca));
              
              gkr = (0.02614D * Math.sqrt(ko / 5.4D));
              ekr = (Rtemp_frdy * Math.log(ko / ki[kk]));
              
              ikr = (gkr * xr[kk] * r[ntab] * (v[kk] - ekr));
              
              gks = (0.433D * (1.0D + 0.6D / (1.0D + Math.pow(3.8E-5D / cai[kk], 1.4D))));
              eks = (Rtemp_frdy * Math.log((ko + prnak * nao) / (ki[kk] + prnak * nai[kk])));
              
              iks = (gks * xs1[kk] * xs2[kk] * (v[kk] - eks));
              
              gki = (0.75D * Math.sqrt(ko / 5.4D));
              eki = (Rtemp_frdy * Math.log(ko / ki[kk]));
              
              tab2 = ((eki + 93.0D) / 5.0E-4D);
              ntab2 = ((int)tab2);
              
              bki = ((0.49124D * expbki1[ntab] * expeki2[ntab2] + expbki2[ntab] * expeki3[ntab2]) / (1.0D + expbki3[ntab] * expeki4[ntab2]));
              aki = (1.02D / (1.0D + expaki1[ntab] * expeki1[ntab2]));
              
              kin = (aki / (aki + bki));
              
              iki = (gki * kin * (v[kk] - eki));
              
              gkp = 0.00552D;
              ekp = eki;
              
              ikp = (gkp * kp[ntab] * (v[kk] - ekp));
              
              inaca = (c1 * expgammas[ntab] * ((expvfrdy[ntab] * nai[kk] * nai[kk] * nai[kk] * cao - nao * nao * nao * cai[kk]) / (1.0D + c2 * expgammas[ntab] * (expvfrdy[ntab] * nai[kk] * nai[kk] * nai[kk] * cao + nao * nao * nao * cai[kk]))));
              
              inak = (ibarnak * fnak[ntab] * (1.0D / (1.0D + Math.pow(kmnai / nai[kk], 2.0D))) * (ko / (ko + kmko)));
              
              ipca = (ibarpca * cai[kk] / (kmpca + cai[kk]));
              
              gcab = 0.003016D;
              ecan = (Rtemp_frdy * 0.5D * Math.log(cao / cai[kk]));
              
              icab = (gcab * (v[kk] - ecan));
              
              enan = ena;
              
              inab = (gnab * (v[kk] - enan));
              
              naiont = (ina + inab + ilcana + insna + 3.0D * inak + 3.0D * inaca);
              kiont = (ikr + iks + iki + ikp + ilcak + insk - 2.0D * inak + ito + ikna + ikatp);
              caiont[kk] = (ilca + icab + ipca - 2.0D * inaca + icat);
              if ((dvdtnew[kk] > 10.0D) && (tcicr[kk] > 10.0D) && (flag[kk] == 1)) {
                flag[kk] = 0;
              }
              if (stimsen[kk] == 1) {
                it[kk] = (Istim + naiont + kiont + caiont[kk]);
              } else {
                it[kk] = (naiont + kiont + caiont[kk]);
              }
              dnai = (-dt[kk] * (naiont * acap) / (vmyo * zna * frdy));
              nai[kk] = (dnai + nai[kk]);
              if (stimsen[kk] == 1) {
                dki = (-dt[kk] * ((kiont + Istim) * acap) / (vmyo * zk * frdy));
              } else {
                dki = (-dt[kk] * (kiont * acap) / (vmyo * zk * frdy));
              }
              ki[kk] = (dki + ki[kk]);
              
              itr = ((nsr[kk] - jsr[kk]) / tautr);
              
              kleak = (iupbar / nsrbar);
              ileak = (kleak * nsr[kk]);
              
              iup = (iupbar * cai[kk] / (cai[kk] + kmup));
              
              dcaiontnew[kk] = ((caiont[kk] - caiontold[kk]) / dt[kk]);
              if ((v[kk] > -35.0D) && (dcaiontnew[kk] > dcaiont[kk]) && (flag[kk] == 0))
              {
                flag[kk] = 1;
                tcicr[kk] = 0.0D;
              }
              on = (1.0D / (1.0D + Math.exp((-tcicr[kk] + 4.0D) / tauon)));
              off = (1.0D - 1.0D / (1.0D + Math.exp((-tcicr[kk] + 4.0D) / tauoff)));
              magrel = (1.0D / (1.0D + Math.exp((ilca + icab + ipca - 2.0D * inaca + icat + 5.0D) / 0.9D)));
              
              irelcicr[kk] = (gmaxrel * on * off * magrel * (jsr[kk] - cai[kk]));
              
              tcicr[kk] += dt[kk];
              
              greljsrol = (grelbarjsrol * (1.0D - Math.exp(-tjsrol[kk] / tauon)) * Math.exp(-tjsrol[kk] / tauoff));
              ireljsrol = (greljsrol * (jsr[kk] - cai[kk]));
              tjsrol[kk] += dt[kk];
              
              csqn = (csqnbar * (jsr[kk] / (jsr[kk] + kmcsqn)));
              djsr = (dt[kk] * (itr - irelcicr[kk] - ireljsrol));
              bjsr = (csqnbar - csqn - djsr - jsr[kk] + kmcsqn);
              cjsr = (kmcsqn * (csqn + djsr + jsr[kk]));
              
              jsr[kk] = ((Math.sqrt(bjsr * bjsr + 4.0D * cjsr) - bjsr) * 0.5D);
              
              dnsr = (dt[kk] * (iup - ileak - itr * vjsr / vnsr));
              nsr[kk] += dnsr;
              
              dcai = (-dt[kk] * (caiont[kk] * acap / (vmyo * zca * frdy) + (iup - ileak) * vnsr / vmyo - irelcicr[kk] * vjsr / vmyo - ireljsrol * vjsr / vmyo));
              trpn = (trpnbar * (cai[kk] / (cai[kk] + kmtrpn)));
              cmdn = (cmdnbar * (cai[kk] / (cai[kk] + kmcmdn)));
              
              catotal = (trpn + cmdn + dcai + cai[kk]);
              bmyo = (cmdnbar + trpnbar - catotal + kmtrpn + kmcmdn);
              cmyo = (kmcmdn * kmtrpn - catotal * (kmtrpn + kmcmdn) + trpnbar * kmcmdn + cmdnbar * kmtrpn);
              dmyo = (-kmtrpn * kmcmdn * catotal);
              
              gpig = Math.sqrt(bmyo * bmyo - 3.0D * cmyo);
              
              cai[kk] = (2.0D * gpig / 3.0D * Math.cos(Math.acos((9.0D * bmyo * cmyo - 2.0D * bmyo * bmyo * bmyo - 27.0D * dmyo) / (2.0D * Math.pow(bmyo * bmyo - 3.0D * cmyo, 1.5D))) / 3.0D) - bmyo / 3.0D);
              
              stimtime[kk] += dt[kk];
              
              utsc[kk] = 0;
              dt[kk] = 0.0D;
            }
            xlap[kk] = (v[(kk - 1)] + v[(kk + 1)] - 2.0D * v[kk]);
            vnew[kk] = (v[kk] - it[kk] * udt + lap * xlap[kk]);
            
            dvdtnew[kk] = ((vnew[kk] - v[kk]) / udt);
          }
          printval = 200;
          if (printdata >= printval)
          {
            gBuffer.setColor(Color.black);
            gBuffer.fillRect(0, 30, 520, 230);
            
            gBuffer.setColor(Color.red);
            gBuffer.setColor(Color.black);
            gBuffer.fillRect(0, 30, 520, 230);
            gBuffer.setColor(Color.white);
            
            gBuffer.drawLine(20, 40, 20, 230);
            gBuffer.drawLine(20, 230, 520, 230);
            gBuffer.drawString("-85", 2, 240);
            gBuffer.drawString("mV", 1, 140);
            gBuffer.drawString("10", 2, 50);
            gBuffer.drawString("time=" + String.valueOf((float)dtime) + "ms", 400, 55);
            if (ncable == 1) {
              gBuffer.drawString("Length of cable: " + String.valueOf((float)(nx * dx)) + " cm ", 188, 249);
            } else {
              gBuffer.drawString("Length of ring: " + String.valueOf((float)(nx * dx)) + " cm ", 188, 249);
            }
            gBuffer.drawString("<---------------------| ", 20, 247);
            gBuffer.drawString("|---------------------> ", 356, 247);
            int n;
            int i1;
            int i2;
            if (vsen == 10)
            {
              gBuffer.setColor(Color.red);
              gBuffer.drawString("V", xpos, 55);
              xpos += 20;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(120.0D - v[n] * 1.1D);
                i2 = (int)(120.0D - v[(n + 1)] * 1.1D);
                double d1 = n * 501.0D / nx;
                double d2 = (n + 1.0D) * 501.0D / nx;
                int i5 = (int)Math.ceil(d1);
                int i6 = (int)Math.ceil(d2);
                gBuffer.drawLine(i5 + 20, i1, i6 + 20, i2);
              }
            }
            int i3;
            int i4;
            if (msen == 10)
            {
              gBuffer.setColor(new Color(254, 222, 0));
              gBuffer.drawString("m", xpos, 55);
              xpos += 17;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - m[n] * 110.0D);
                i2 = (int)(200.0D - m[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (hsen == 10)
            {
              gBuffer.setColor(Color.blue);
              gBuffer.drawString("h", xpos, 55);
              xpos += 17;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - h[n] * 110.0D);
                i2 = (int)(200.0D - h[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (jsen == 10)
            {
              gBuffer.setColor(new Color(192, 192, 192));
              gBuffer.drawString("j", xpos, 55);
              xpos += 17;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - j[n] * 110.0D);
                i2 = (int)(200.0D - j[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (dsen == 10)
            {
              gBuffer.setColor(new Color(234, 161, 0));
              gBuffer.drawString("d", xpos, 55);
              xpos += 17;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - d[n] * 110.0D);
                i2 = (int)(200.0D - d[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (fsen == 10)
            {
              gBuffer.setColor(Color.magenta);
              gBuffer.drawString("f", xpos, 55);
              xpos += 15;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - f[n] * 110.0D);
                i2 = (int)(200.0D - f[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (x1sen == 10)
            {
              gBuffer.setColor(new Color(83, 192, 186));
              gBuffer.drawString("xs1", xpos, 55);
              xpos += 25;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - xs1[n] * 110.0D);
                i2 = (int)(200.0D - xs1[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (x2sen == 10)
            {
              gBuffer.setColor(new Color(118, 0, 254));
              gBuffer.drawString("xs2", xpos, 55);
              xpos += 25;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - xs2[n] * 110.0D);
                i2 = (int)(200.0D - xs2[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (xrsen == 10)
            {
              gBuffer.setColor(new Color(44, 154, 0));
              gBuffer.drawString("xr", xpos, 55);
              xpos += 20;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - xr[n] * 110.0D);
                i2 = (int)(200.0D - xr[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (bsen == 10)
            {
              gBuffer.setColor(new Color(0, 254, 0));
              gBuffer.drawString("b", xpos, 55);
              xpos += 17;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - b[n] * 110.0D);
                i2 = (int)(200.0D - b[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (gsen == 10)
            {
              gBuffer.setColor(new Color(204, 100, 0));
              gBuffer.drawString("g", xpos, 55);
              xpos += 17;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - gg[n] * 110.0D);
                i2 = (int)(200.0D - gg[(n + 1)] * 110.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            if (casen == 10)
            {
              gBuffer.setColor(Color.cyan);
              gBuffer.drawString("[Ca]", xpos, 55);
              xpos += 27;
              for (n = 1; n < nx - 1; n += 1)
              {
                i1 = (int)(200.0D - cai[n] * 125510.0D);
                i2 = (int)(200.0D - cai[(n + 1)] * 125510.0D);
                i3 = n * 501 / nx;
                i4 = (n + 1) * 501 / nx;
                gBuffer.drawLine(i3 + 20, i1, i4 + 20, i2);
              }
            }
            paramGraphics.drawImage(Buffer, 0, 0, this);
            
            printdata = 0;
          }
          printdata += 1;
          for (kk = 1; kk < nx; kk += 1)
          {
            v[kk] = vnew[kk];
            tab = ((v[kk] - vmin) / dvt);
            ntab = ((int)tab);
            dt[kk] += udt;
            utsc[kk] += 1;
            dvdt[kk] = dvdtnew[kk];
            caiontold[kk] = caiont[kk];
            dcaiont[kk] = dcaiontnew[kk];
            
            m[kk] = (mss[ntab] - (mss[ntab] - m[kk]) * expm[ntab]);
            h[kk] = (hss[ntab] - (hss[ntab] - h[kk]) * exph[ntab]);
            j[kk] = (jss[ntab] - (jss[ntab] - j[kk]) * expj[ntab]);
            d[kk] = (dss[ntab] - (dss[ntab] - d[kk]) * expd[ntab]);
            f[kk] = (fss[ntab] - (fss[ntab] - f[kk]) * expf[ntab]);
            b[kk] = (bss[ntab] - (bss[ntab] - b[kk]) * expb[ntab]);
            gg[kk] = (gss[ntab] - (gss[ntab] - gg[kk]) * expg[ntab]);
            xr[kk] = (xrss[ntab] - (xrss[ntab] - xr[kk]) * expxr[ntab]);
            xs1[kk] = (xs1ss[ntab] - (xs1ss[ntab] - xs1[kk]) * expxs1[ntab]);
            xs2[kk] = (xs1ss[ntab] - (xs1ss[ntab] - xs2[kk]) * expxs2[ntab]);
            if (t > tstim[kk]) {
              stimsen[kk] = 0;
            }
          }
          t += udt;
        }
      }
    }
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    if (checkm.getState() == true) {
      msen = 10;
    } else {
      msen = 0;
    }
    if (checkv.getState() == true) {
      vsen = 10;
    } else {
      vsen = 0;
    }
    if (checkh.getState() == true) {
      hsen = 10;
    } else {
      hsen = 0;
    }
    if (checkj.getState() == true) {
      jsen = 10;
    } else {
      jsen = 0;
    }
    if (checkf.getState() == true) {
      fsen = 10;
    } else {
      fsen = 0;
    }
    if (checkd.getState() == true) {
      dsen = 10;
    } else {
      dsen = 0;
    }
    if (checkxs1.getState() == true) {
      x1sen = 10;
    } else {
      x1sen = 0;
    }
    if (checkxs2.getState() == true) {
      x2sen = 10;
    } else {
      x2sen = 0;
    }
    if (checkxr.getState() == true) {
      xrsen = 10;
    } else {
      xrsen = 0;
    }
    if (checkb.getState() == true) {
      bsen = 10;
    } else {
      bsen = 0;
    }
    if (checkg.getState() == true) {
      gsen = 10;
    } else {
      gsen = 0;
    }
    if (checkna.getState() == true) {
      nasen = 10;
    } else {
      nasen = 0;
    }
    if (checkk.getState() == true) {
      ksen = 10;
    } else {
      ksen = 0;
    }
    if (checkca.getState() == true) {
      casen = 10;
    } else {
      casen = 0;
    }
    if (paramItemEvent.getItem().toString() == optionc) {
      ncable = 1;
    }
    if (paramItemEvent.getItem().toString() == optionr) {
      ncable = 0;
    }
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    gna = Double.valueOf(input2.getText()).doubleValue();
    gnab = Double.valueOf(input3.getText()).doubleValue();
    gcat = Double.valueOf(input4.getText()).doubleValue();
    
    nt = ((int)(time / 651.0D));
    nt += 1;
    Istim = Double.valueOf(sstrengtht.getText()).doubleValue();
    tstimul = Double.valueOf(sdurationt.getText()).doubleValue();
    lstim = Double.valueOf(slengtht.getText()).doubleValue();
    xstim = Double.valueOf(spositiont.getText()).doubleValue();
    
    input2.setText(input2.getText());
    input3.setText(input3.getText());
    input4.setText(input4.getText());
    
    inputs1.setText(inputs1.getText());
    inputtime.setText(inputtime.getText());
    if ((paramActionEvent.getSource() == butp) && 
      (!firsttime2)) {
      if (butp.getLabel().equals("Continue"))
      {
        butp.setLabel("Stop");
        nstop = 1;
      }
      else
      {
        butp.setLabel("Continue");
        nstop = 10;
      }
    }
    if (paramActionEvent.getSource() == bstimulus)
    {
      bstimulus.setBackground(Color.lightGray);
      
      ndtime = 1;
      t = 0.0D;
      double d1 = xstim / dx;
      nxs1 = ((int)d1);
      double d2 = lstim / dx / 2.0D;
      nxs1i = ((int)(nxs1 - d2));
      nxs1f = ((int)(nxs1 + d2));
      if (nxs1i < 0) {
        nxs1i = 0;
      }
      if (nxs1f > nx) {
        nxs1f = nx;
      }
      for (kk = nxs1i; kk < nxs1f; kk += 1)
      {
        tstim[kk] = tstimul;
        stimsen[kk] = 1;
      }
    }
    if (paramActionEvent.getSource() == butt) {
      if (butt.getLabel().equals("Start"))
      {
        butt.setBackground(Color.lightGray);
        bstimulus.setBackground(Color.green);
        
        butt.setLabel("Reset");
        firsttime = false;
        butp.setLabel("Stop");
        firsttime2 = false;
      }
      else
      {
        butt.setLabel("Reset");
        
        gna = 16.0D;
        gnab = 0.004D;
        gcat = 0.05D;
        
        t = 0.0D;
        time = 650.0D;
        dtime = 0.0D;
        ndtime = 0;
        s1 = 20.0D;
        nt = 1;
        for (kk = 0; kk < 800; kk += 1)
        {
          tstim[kk] = 0.0D;
          stimtime[kk] = 10.0D;
          irelcicr[kk] = 0.0D;
          
          v[kk] = -88.654973D;
          dvdt[kk] = 0.0D;
          
          nai[kk] = 12.236437D;
          nao = 140.0D;
          nabm = 140.0D;
          ki[kk] = 136.89149D;
          ko = 4.5D;
          kbm = 4.5D;
          cai[kk] = 7.9E-5D;
          cao = 1.8D;
          cabm = 1.8D;
          
          m[kk] = 8.38E-4D;
          h[kk] = 0.993336D;
          j[kk] = 0.995484D;
          d[kk] = 3.0E-6D;
          f[kk] = 0.999745D;
          xs1[kk] = 0.004503D;
          xs2[kk] = 0.004503D;
          xr[kk] = 1.29E-4D;
          b[kk] = 9.94E-4D;
          gg[kk] = 0.994041D;
          zdv = 0.0120892D;
          ydv = 0.999978D;
          
          grelbarjsrol = 0.0D;
          tjsrol[kk] = 1000.0D;
          tcicr[kk] = 1000.0D;
          jsr[kk] = 1.179991D;
          nsr[kk] = 1.179991D;
          trpn = 0.0143923D;
          cmdn = 0.00257849D;
          csqn = 6.97978D;
          flag[kk] = 0;
          dt[kk] = udt;
          utsc[kk] = 50;
          dcaiont[kk] = 0.0D;
          caiont[kk] = 0.0D;
          i = -1;
          xlap[kk] = 0.0D;
          nxstep[kk] = 0;
          stimsen[kk] = 0;
        }
        input2.setText("16.0");
        input3.setText("0.004");
        input4.setText("0.05");
      }
    }
  }
  
  public void run()
  {
    try
    {
      for (;;)
      {
        Thread.sleep(nspeed);
        if (!down) {
          repaint();
        }
      }
    }
    catch (Exception localException) {}
  }
}