


 	import java.awt.*;
	import java.applet.Applet;
	import java.awt.event.*;
        import java.lang.*;

         /*
         <applet code="nob" width=300 height=350>
         </applet>
          */

	public class nob extends Applet 
         implements ActionListener, ItemListener {
         private Panel buttonPanel, checkPanel;
	 private Label prompt1, prompt2, prompt3, prompt4, prompt5;
         private Label promptg,promptgg,promptg2,promptg3;
	 private TextField input1, input2, input3, input4, input5,input6;
	 private double result;
	Button butt,butp;
        Checkbox checkv,checkm,checkh,checkn,checkd,checkf,checkx,checkca;
        boolean firsttime= true;
        double a=0.1;
        double gama=1;
        double b=0.5;
        double eps=0.01;
        double delta=0;

        double x1,m,h,j,d,f,u,ca,xca,n;
        double ax1,am,ah,aj,ad,af,an;
        double bx1,bm,bh,bj,bd,bf,bn;
        double ik1,ix1,ina,is,es,gix1;
        double ik,ik2;
        double xik1,xik2;
        double gk2=1.2;
        double gna1=400;
        double gna2=0.14;
        double gna=4.0;
        double gnac=0.003;
        double ena=50;
        double gs=0.09; 
        double msen=0;
        double hsen=0;
        double nsen=0;
        double dsen=0;
        double vsen=10;
        double casen=0;
        double xsen=0;
        double fsen=0;
        double s2=3000;
        double s1=3000;
        int is2;
        int is1;
        int xpos;

	public void paint(Graphics g) {
          g.setColor(Color.black);
          g.fillRect(0,30,313,210);
          g.setColor(Color.blue);
          g.drawLine(0,30,0,240);
          g.drawLine(0,30,313,30);
          g.drawLine(0,240,313,240);
          g.drawLine(313,30,313,240);
          g.setColor(Color.white);
          g.drawLine(20,40,20,215);
          g.drawLine(20,215,310,215);
          g.drawString("Time (ms)",140,239);
        if(firsttime){
                      }
          else{
          g.setColor(Color.white);
          is1=(int)(30+s1/6);
//          g.drawLine(45,215,45,208);
          g.drawLine(is1+5,215,is1+5,208);
          g.drawString("S1",is1,227);
          is2=(int)(30+s2/6);
          g.drawLine(is2+5,215,is2+5,208);
          g.drawString("S2",is2,227);
 
            xpos=80;
           if(vsen==10){
            g.setColor(Color.red);
            g.drawString("V",xpos,55);
            xpos=xpos+20;
                        }
            if(msen==10){
            g.setColor(new Color(12,100,12));
            g.drawString("m,",xpos,55);
            xpos=xpos+20;
                        }
           if(hsen==10){
            g.setColor(Color.cyan);
            g.drawString("h,",xpos,55);
            xpos=xpos+20;
                        }
            if(nsen==10){
            g.setColor(new Color(234,161,0));
            g.drawString("n.",xpos,55);
            xpos=xpos+20;
                        }



  	 g.setColor(Color.red);
//         firsttime=false;
         double dx=0.01;
         double x=0.0;
         double s=0.0;
//         double u=0.5;
//         double v=0.0;
//            IC
        u=-85;
      
         an=0.0001*(-u-50)/(Math.exp((-u-50.0)/10.0)-1.0);
         bn=0.002*Math.exp((-u-90.0)/80.0);

         am=0.1*(-u-48.0)/(Math.exp((-u-48.0)/15.0)-1.0);
         bm=0.12*(u+8.0)/(Math.exp((u+8.)/5.0)-1.0);

         ah=0.17*Math.exp((-u-90)/20.0);
         bh=1.0/(Math.exp((-u-42.0)/10.0)+1.0);


        m= am/(am+bm);
        h= ah/(ah+bh);
        n= an/(an+bn);
//        u=-85;


         int so=185;
         int mo=185;
         int ho=85;
         int no=185;
         int xo=20;
//         u=-55;
         is1=(int)(s1);
         is2=(int)(s2);
         for(int i=0; i< 1550; i++){
          if(i==is1)u=-55;
          if(i==is2)u=-55;
           for(int k=0; k<100; k++){  

         an=0.0001*(-u-50)/(Math.exp((-u-50.0)/10.0)-1.0);
         bn=0.002*Math.exp((-u-90.0)/80.0);

         am=0.1*(-u-48.0)/(Math.exp((-u-48.0)/15.0)-1.0);
         bm=0.12*(u+8.0)/(Math.exp((u+8.)/5.0)-1.0);

         ah=0.17*Math.exp((-u-90)/20.0);
         bh=1.0/(Math.exp((-u-42.0)/10.0)+1.0);


      
        m= m+dx*(am*(1.0-m)-bm*m);
        h= h+dx*(ah*(1.0-h)-bh*h);
        n= n+dx*(an*(1.0-n)-bn*n);

         ik2=gk2*n*n*n*n;

         ik1=1.2*Math.exp((-u-90.0)/50.0)+0.015*Math.exp((u+90.0)/60.0);

        ina=(gna1*m*m*m*h+gna2)*(u-40);
        ik=(ik2+ik1)*(u+100);
         u=u-dx*(ina+ik)/12.0;

           
          x+=dx;
              }
           if(i%6 ==1){
           int xf=(int)(35+x/6);
           int sf=(int)(100-u);
           int mf=(int)(185-m*100);
           int hf=(int)(185-h*100);
           int nf=(int)(185-n*100);
//           int ssf=(int)(100-v*2.0); 
//           g.setColor(Color.red);
//           g.drawLine(xo,so,xf,sf);
//           g.setColor(Color.green);
//           g.drawLine(xo,sso,xf,ssf);
//           sso=ssf;
           if(vsen==10){
            g.setColor(Color.red);
            g.drawLine(xo,so,xf,sf);
                        }
            if(msen==10){
            g.setColor(new Color(12,100,12));
            g.drawLine(xo,mo,xf,mf);
                        }
           if(hsen==10){
            g.setColor(Color.cyan);
            g.drawLine(xo,ho,xf,hf);
                        }
            if(nsen==10){
            g.setColor(new Color(234,161,0));
            g.drawLine(xo,no,xf,nf);
                        }


           mo=mf;
           so=sf;
           ho=hf;
           no=nf;
           xo=xf;
                                       }
                                 }
             }
	}

        public void init() {

        buttonPanel = new Panel();
	butt = new Button ("Start");
        butt.addActionListener( this );
//	   prompt1= new Label("      a");
//	   input1= new TextField(3);
//           input1.addActionListener( this);
	   prompt2= new Label("  gk2");
           input2= new TextField(3);
	   input2.addActionListener( this);
           prompt3=new Label (" gna1");
           input3= new TextField(3);
           input3.addActionListener( this); 
           prompt4=new Label ("  gna2");
           input4= new TextField(3);
           input4.addActionListener( this); 

           prompt5=new Label (" S1 at:");
           input5= new TextField(3);
           input5.addActionListener( this); 
//	   input1.setText("4.0");
           input2.setText("1.2");
	   input3.setText("400");
           input4.setText("0.14");
           input5.setText("3000");

       checkPanel = new Panel();
       
       butp = new Button("Replot");
       butp.addActionListener(this );
       promptg = new Label("       ");
       promptgg = new Label("      ");
       promptg2 = new Label("   S2 at:");
       promptg3 = new Label("      ");
       input6= new TextField(3);
       input6.addActionListener( this);
       input6.setText("3000");


       checkv = new Checkbox("Voltage", true);
       checkv.addItemListener(this );
       checkm= new Checkbox("m gate",false);
       checkm.addItemListener(this );
       checkh= new Checkbox("h gate",false);
       checkh.addItemListener(this );
       checkn= new Checkbox("n gate",false);
       checkn.addItemListener(this );

//        check1.addActionListener(this );
//        check2.addActionListener(this );
//        check3.addActionListener(this );
             repaint();


           buttonPanel.setLayout(new GridLayout(1,9));
           buttonPanel.add(butt);
//           buttonPanel.add(prompt1);
//           buttonPanel.add(input1);
           buttonPanel.add(prompt2);
           buttonPanel.add(input2);
           buttonPanel.add(prompt3);
           buttonPanel.add(input3);

           buttonPanel.add(prompt4);
           buttonPanel.add(input4);

           buttonPanel.add(prompt5);
           buttonPanel.add(input5);
//           buttonPanel.add(promptg);
//           buttonPanel.add(promptgg);

//           buttonPanel.add(promptg2);
//           buttonPanel.add(promptg3);




           checkPanel.setLayout(new GridLayout(7,1));
           checkPanel.add(promptg2);
           checkPanel.add(input6);
           checkPanel.add(butp);
           checkPanel.add(checkv);
           checkPanel.add(checkm);
           checkPanel.add(checkh);
           checkPanel.add(checkn);


           setLayout(new BorderLayout() );
            add(checkPanel, BorderLayout.EAST);
            add(buttonPanel, BorderLayout.NORTH);
 

        }


        public void itemStateChanged( ItemEvent ae){
         if(checkm.getState()==true){ msen=10;}
         else{ msen=0;}
         if(checkv.getState()==true){ vsen=10;}
         else{vsen=0;}
         if(checkh.getState()==true){ hsen=10;}
         else{ hsen=0;}
         if(checkn.getState()==true){ nsen=10;}
         else{nsen=0;}


                                                     }

        public void  actionPerformed( ActionEvent ae)
	{

//            a= Double.valueOf(input1.getText()).doubleValue();
            gk2= Double.valueOf(input2.getText()).doubleValue();
            gna1= Double.valueOf(input3.getText()).doubleValue();
            gna2= Double.valueOf(input4.getText()).doubleValue();
            s1= Double.valueOf(input5.getText()).doubleValue();
            s2=Double.valueOf(input6.getText()).doubleValue();
          

//	   input1.setText(input1.getText());
           input2.setText(input2.getText());
	   input3.setText(input3.getText());
           input4.setText(input4.getText());
	   input5.setText(input5.getText());
           input6.setText(input6.getText());

           if(ae.getSource()==butp){
            repaint();
                                   }
          
           if(ae.getSource()==butt){
            
          if(butt.getLabel().equals("Start")){
           butt.setLabel("Reset");
          firsttime=false;
//	  repaint();
                                              }
          else{
           butt.setLabel("Reset");
//           firsttime=true;
         a=0.1;
         gk2=1.2;
         gna1=400;
         gna2=0.14;
         s2=3000;
         s1=3000;
         gama=1;
         b=0.5;
         eps=0.01;
         delta=0;
//	   input1.setText("0.1");
           input2.setText("1.2");
	   input3.setText("400");
           input4.setText("0.14");
           input5.setText("3000");
           input6.setText("3000");

//           repaint();
              }
                                    } 
         repaint();
        }

        }









