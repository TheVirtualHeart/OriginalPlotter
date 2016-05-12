


 	import java.awt.*;
	import java.applet.Applet;
	import java.awt.event.*;
        import java.lang.*;

         /*
         <applet code="laubloomis" width=640 height=250>
         </applet>
          */

	public class laubloomis extends Applet 
         implements ActionListener, ItemListener {
      Panel buttonPanel, checkPanel;
	  Label prompt1, prompt2, prompt3, prompt4, prompt5;
      Label promptg,promptgg,promptg2,promptg3;
      Label lk1, lk2, lk3, lk4, lk5, lk6, lk7, lk8, lk9, lk10, lk11, lk12, lk13, lk14;
      TextField txtk1,txtk2,txtk3,txtk4,txtk5,txtk6,txtk7,txtk8,txtk9,txtk10,txtk11,txtk12;
      TextField txtk13, txtk14;
      TextField txty1,txty2,txty3,txty4,txty5,txty6,txty7;
      Label ltime;
      TextField txttime;
      
	 TextField input1, input2, input3, input4, input5;
	 double result;
	Button butt,butp,breplot;
        Checkbox checky1,checky2,checky3,checky4,checky5,checky6,checky7,checknorm;
        boolean firsttime= true;
        double k1=2.0,k2=0.9,k3=2.5,k4=1.5,k5=0.6,k6=0.8,k7=1.0,k8=1.3,k9=0.3,k10=0.8;
        double k11=0.7,k12=4.9,k13=18.0,k14=4.5;
        double y1=1,y2=1,y3=1,y4=1,y5=1,y6=.1,y7=1;
        double ttimemax=100, ttime=50;
        double dt=.005;
        double ppy;
        double y1sen=10,y2sen=0,y3sen=0,y4sen=0,y5sen=0,y6sen=0,y7sen=0;
        int nstep=(int)(ttime/dt);
        int xpos,mod,xi,xf,yi,yf,nnn;
        int time1=(int)(ttime/3);
        int time2=(int)(ttime*2/3);
        int time3=(int)(ttime);
        int nreset=0,nreplot=0,mnormal=1;
        int ii;
        int py1[]=new int[20000];
        int py2[]=new int[20000];
        int py3[]=new int[20000];
        int py4[]=new int[20000];
        int py5[]=new int[20000];
        int py6[]=new int[20000];
        int py7[]=new int[20000];
        int py1i,py1f,py2i,py2f,py3i,py3f,py4i,py4f,py5i,py5f,py6i,py6f,py7i,py7f;
        double yn1,yn2,yn3,yn4,yn5,yn6,yn7,t;
        double y1mi,y1ma,y2mi,y2ma,y3mi,y3ma,y4mi,y4ma,y5ma,y5mi;
        double y6mi,y6ma,y7mi,y7ma;
        
        
	public void paint(Graphics g) {
          g.setColor(Color.black);
          g.fillRect(0,25,350,215);
          g.setColor(Color.blue);
          g.drawLine(0,25,0,240); 
          g.drawLine(0,25,350,25);
          g.drawLine(0,240,350,240);
          g.drawLine(350,25,350,240);
          g.setColor(Color.white);
          g.drawLine(20,40,20,215);
          g.drawLine(20,215,345,215);
          g.drawString("Time",150,239);
          if(mnormal==1){g.drawString("0.0",4,220);
          g.drawString("1.0",4,40);
                        }
          else{ g.drawString("0.0",4,220);
          g.drawString("3.5",4,40);
                        }
        if(firsttime){
                      }
          else{
          g.setColor(Color.white);
          g.drawLine(340,215,340,210);
          g.drawString(""+time3,330,229);
          g.drawLine(127,215,127,210);
          g.drawString(""+time1,117,229);
          g.drawLine(234,215,234,210);
          g.drawString(""+time2,224,229);

             xpos=80;
           if(y1sen==10){
            g.setColor(Color.red);
            g.drawString("y1",xpos,38);
            xpos=xpos+20;
                        }
            if(y2sen==10){
            g.setColor(new Color(12,100,12));
            g.drawString("y2,",xpos,38);
            xpos=xpos+20;
                        }
           if(y3sen==10){
            g.setColor(Color.blue);
            g.drawString("y3,",xpos,38);
            xpos=xpos+20;
                        }
            if(y4sen==10){
            g.setColor(Color.gray);
            g.drawString("y4,",xpos,38);
            xpos=xpos+20;
                        }
           if(y5sen==10){
            g.setColor(new Color(234,161,0));
            g.drawString("y5,",xpos,38);
            xpos=xpos+20;
                        }
            if(y6sen==10){
            g.setColor(Color.magenta);
            g.drawString("y6,",xpos,38);
            xpos=xpos+20;
                        }
           if(y7sen==10){
            g.setColor(new Color(128,128,0));
            g.drawString("y7,",xpos,38);
            xpos=xpos+20;
                        }
            

             if(nreset==1){
                nreset=0; }
                else {

  	 g.setColor(Color.red);


          nstep=(int)(ttime/dt);
          nstep=10000;
          mod=(int)(nstep/320.);
          mod=mod+1;
          if(nreplot==0){
          y1mi=y1;
          y1ma=y1;
          y2mi=y2;
          y2ma=y2;
          y3mi=y3;
          y3ma=y3;
          y4mi=y4;
          y4ma=y4;
          y5mi=y5;
          y5ma=y5;
          y6mi=y6;
          y6ma=y6;
          y7mi=y7;
          y7ma=y7;
         
         for(int i=0; i< nstep; i++){
            yn1=y1+dt*(k1*y7-k2*y1);
            yn2=y2+dt*(k3*y5-k4*y2);
            yn3=y3+dt*(k5*y7-k6*y3*y2);
            yn4=y4+dt*(k7-k8*y3*y4);
            yn5=y5+dt*(k9*y1-k10*y5*y4);
            yn6=y6+dt*(k11*y1-k12*y6);
            yn7=y7+dt*(k13*y6-k14*y7*y2);
            y1=yn1;
            y2=yn2;
            y3=yn3;
            y4=yn4;
            y5=yn5;
            y6=yn6;
            y7=yn7;
            if(y1 > y1ma)y1ma=y1;
            if(y1 < y1mi)y1mi=y1;
            if(y2 > y2ma)y2ma=y2;
            if(y2 < y2mi)y2mi=y2;
            if(y3 > y3ma)y3ma=y3;
            if(y3 < y3mi)y3mi=y3;
            if(y4 > y4ma)y4ma=y4;
            if(y4 < y4mi)y4mi=y4;
            if(y5 > y5ma)y5ma=y5;
            if(y5 < y5mi)y5mi=y5;
            if(y6 > y6ma)y6ma=y6;
            if(y6 < y6mi)y6mi=y6;
            if(y7 > y7ma)y7ma=y7;
            if(y7 < y7mi)y7mi=y7;
            py1[i]=(int)(y1*170);
            py2[i]=(int)(y2*170);
            py3[i]=(int)(y3*170);
            py4[i]=(int)(y4*170);
            py5[i]=(int)(y5*170);
            py6[i]=(int)(y6*170);
            py7[i]=(int)(y7*170);
                                    }
  
                           }
            nnn=20;
            
           if(mnormal==1){
            py1i=(int)(215-170*(1-(170*y1ma-py1[0])/(170*(y1ma-y1mi))));
            py2i=(int)(215-170*(1-(170*y2ma-py2[0])/(170*(y2ma-y2mi))));
            py3i=(int)(215-170*(1-(170*y3ma-py3[0])/(170*(y3ma-y3mi))));
            py4i=(int)(215-170*(1-(170*y4ma-py4[0])/(170*(y4ma-y4mi))));
            py5i=(int)(215-170*(1-(170*y5ma-py5[0])/(170*(y5ma-y5mi))));
            py6i=(int)(215-170*(1-(170*y6ma-py6[0])/(170*(y6ma-y6mi))));
            py7i=(int)(215-170*(1-(170*y7ma-py7[0])/(170*(y7ma-y7mi))));
                          }
             else{
            py1i=(int)(215-(float)py1[0]/3.4);
            py2i=(int)(215-(float)py2[0]/3.4);
            py3i=(int)(215-(float)py3[0]/3.4);
            py4i=(int)(215-(float)py4[0]/3.4);
            py5i=(int)(215-(float)py5[0]/3.4);
            py6i=(int)(215-(float)py6[0]/3.4);
            py7i=(int)(215-(float)py7[0]/3.4);
                  }              
           for(int i=0; i< nstep-350; i=i+mod){
            if(mnormal==1){
            py1f=(int)(215-170*(1-(170*y1ma-py1[i+mod])/(170*(y1ma-y1mi))));
            py2f=(int)(215-170*(1-(170*y2ma-py2[i+mod])/(170*(y2ma-y2mi))));
            py3f=(int)(215-170*(1-(170*y3ma-py3[i+mod])/(170*(y3ma-y3mi))));
            py4f=(int)(215-170*(1-(170*y4ma-py4[i+mod])/(170*(y4ma-y4mi))));
            py5f=(int)(215-170*(1-(170*y5ma-py5[i+mod])/(170*(y5ma-y5mi))));
            py6f=(int)(215-170*(1-(170*y6ma-py6[i+mod])/(170*(y6ma-y6mi))));
            py7f=(int)(215-170*(1-(170*y7ma-py7[i+mod])/(170*(y7ma-y7mi))));
                          }
             else{
            py1f=(int)(215-(float)py1[i+mod]/3.4);
            py2f=(int)(215-(float)py2[i+mod]/3.4);
            py3f=(int)(215-(float)py3[i+mod]/3.4);
            py4f=(int)(215-(float)py4[i+mod]/3.4);
            py5f=(int)(215-(float)py5[i+mod]/3.4);
            py6f=(int)(215-(float)py6[i+mod]/3.4);
            py7f=(int)(215-(float)py7[i+mod]/3.4);
                   }              
            ii=i;
           if(y1sen==10){
          g.setColor(Color.red);
          g.drawLine(nnn,py1i,nnn+1,py1f);
                        }
            if(y2sen==10){
            g.setColor(new Color(12,100,12));
            g.drawLine(nnn,py2i,nnn+1,py2f);       
                        }
           if(y3sen==10){
            g.setColor(Color.blue);
            g.drawLine(nnn,py3i,nnn+1,py3f);
                        }
            if(y4sen==10){
            g.setColor(Color.darkGray);
            g.drawLine(nnn,py4i,nnn+1,py4f);
                        }
           if(y5sen==10){
            g.setColor(new Color(128,128,0));
            g.drawLine(nnn,py5i,nnn+1,py5f);
                        }
            if(y6sen==10){
            g.setColor(Color.magenta);
            g.drawLine(nnn,py6i,nnn+1,py6f);
                        }
           if(y7sen==10){
            g.setColor(Color.orange);
            g.drawLine(nnn,py7i,nnn+1,py7f);
                        }
            py1i=py1f;
            py2i=py2f;
            py3i=py3f;
            py4i=py4f;
            py5i=py5f;
            py6i=py6f;
            py7i=py7f;
       
            nnn=nnn+1;
                                            }
        

 //   g.drawString(""+ii+"   "+nnn+"  "+y1ma,124,150); 
       if(nreplot==0){
       txty1.setText(String.valueOf(y1));
       txty2.setText(String.valueOf(y2));
       txty3.setText(String.valueOf(y3));
       txty4.setText(String.valueOf(y4));
       txty5.setText(String.valueOf(y5));
       txty6.setText(String.valueOf(y6));
       txty7.setText(String.valueOf(y7));
                     }
        nreplot=0;
                                     //  }
                               //  }
                }
             }
	}

        public void init() {

        buttonPanel = new Panel();
	    butt = new Button ("Start");
        butt.addActionListener( this );
        breplot = new Button ("        ");
        breplot.addActionListener(this );
        butp = new Button("       ");
        butp.addActionListener(this );
        checknorm = new Checkbox("normalize", true);
        checknorm.addItemListener(this );
        ltime = new Label("    integration time=");
        txttime = new  TextField(3);
        txttime.addActionListener( this);
        txttime.setText(String.valueOf(ttime));
        
       checkPanel = new Panel();
       
       lk1 = new Label("   k1=");
       lk2 = new Label("   k2=");
       lk3 = new Label("   k3=");
       lk4 = new Label("   k4=");
       lk5 = new Label("   k5=");
       lk6 = new Label("   k6=");
       lk7 = new Label("   k7=");
       lk8 = new Label("    k8=");
       lk9 = new Label("    k9=");
       lk10 = new Label("   k10=");
       lk11 = new Label("   k11=");
       lk12 = new Label("   k12=");       
       lk13 = new Label("   k13=");
       lk14 = new Label("   k14=");       
       txtk1 = new  TextField(3);
       txtk2 = new  TextField(3);
       txtk3 = new  TextField(3);
       txtk4 = new  TextField(3);
       txtk5 = new  TextField(3);
       txtk6 = new  TextField(3);
       txtk7 = new  TextField(3);
       txtk8 = new  TextField(3);
       txtk9 = new  TextField(3);
       txtk10 = new  TextField(3);
       txtk11 = new  TextField(3);
       txtk12 = new  TextField(3);
       txtk13 = new  TextField(3);
       txtk14 = new  TextField(3);
       txtk1.addActionListener( this);
       txtk2.addActionListener( this);
       txtk3.addActionListener( this);
       txtk4.addActionListener( this);     
       txtk5.addActionListener( this);
       txtk6.addActionListener( this);
       txtk7.addActionListener( this);
       txtk8.addActionListener( this);  
       txtk9.addActionListener( this);
       txtk10.addActionListener( this);     
       txtk11.addActionListener( this);
       txtk12.addActionListener( this);
       txtk13.addActionListener( this);
       txtk14.addActionListener( this);
       txtk1.setText(String.valueOf(k1));
       txtk2.setText(String.valueOf(k2));
       txtk3.setText(String.valueOf(k3));
       txtk4.setText(String.valueOf(k4));
       txtk5.setText(String.valueOf(k5));
       txtk6.setText(String.valueOf(k6));
       txtk7.setText(String.valueOf(k7));
       txtk8.setText(String.valueOf(k8));
       txtk9.setText(String.valueOf(k9));
       txtk10.setText(String.valueOf(k10));
       txtk11.setText(String.valueOf(k11));
       txtk12.setText(String.valueOf(k12));
       txtk13.setText(String.valueOf(k13));
       txtk14.setText(String.valueOf(k14));
      
       txty1 = new  TextField(3);
       txty2 = new  TextField(3);
       txty3 = new  TextField(3);
       txty4 = new  TextField(3);
       txty5 = new  TextField(3);
       txty6 = new  TextField(3);
       txty7 = new  TextField(3);
       txty1.addActionListener( this);
       txty2.addActionListener( this);
       txty3.addActionListener( this);
       txty4.addActionListener( this);     
       txty5.addActionListener( this);
       txty6.addActionListener( this);
       txty7.addActionListener( this);
       txty1.setText(String.valueOf(y1));
       txty2.setText(String.valueOf(y2));
       txty3.setText(String.valueOf(y3));
       txty4.setText(String.valueOf(y4));
       txty5.setText(String.valueOf(y5));
       txty6.setText(String.valueOf(y6));
       txty7.setText(String.valueOf(y7));
       
       
       checky1 = new Checkbox("y1", true);
       checky1.addItemListener(this );
       checky2= new Checkbox("y2",false);
       checky2.addItemListener(this );
       checky3= new Checkbox("y3",false);
       checky3.addItemListener(this );
       checky4= new Checkbox("y4",false);
       checky4.addItemListener(this );
       checky5= new Checkbox("y5",false);
       checky5.addItemListener(this );
       checky6= new Checkbox("y6",false);
       checky6.addItemListener(this );
       checky7= new Checkbox("y7",false);
       checky7.addItemListener(this );
       


             repaint();


           buttonPanel.setLayout(new GridLayout(1,6));
           buttonPanel.add(butt);
           buttonPanel.add(breplot);
           buttonPanel.add(butp);
           buttonPanel.add(checknorm);
           buttonPanel.add(ltime);
           buttonPanel.add(txttime);




           checkPanel.setLayout(new GridLayout(7,6));
           checkPanel.add(checky1);
           checkPanel.add(txty1);
           checkPanel.add(lk1);
           checkPanel.add(txtk1);
           checkPanel.add(lk8);
           checkPanel.add(txtk8);
           checkPanel.add(checky2);
           checkPanel.add(txty2);
           checkPanel.add(lk2);
           checkPanel.add(txtk2);
           checkPanel.add(lk9);
           checkPanel.add(txtk9);           
           checkPanel.add(checky3);
           checkPanel.add(txty3);
           checkPanel.add(lk3);
           checkPanel.add(txtk3);
           checkPanel.add(lk10);
           checkPanel.add(txtk10);
           checkPanel.add(checky4);
           checkPanel.add(txty4);
           checkPanel.add(lk4);
           checkPanel.add(txtk4);
           checkPanel.add(lk11);
           checkPanel.add(txtk11);
           checkPanel.add(checky5);
           checkPanel.add(txty5);
           checkPanel.add(lk5);
           checkPanel.add(txtk5);
           checkPanel.add(lk12);
           checkPanel.add(txtk12);
           checkPanel.add(checky6);
           checkPanel.add(txty6);
           checkPanel.add(lk6);
           checkPanel.add(txtk6);
           checkPanel.add(lk13);
           checkPanel.add(txtk13);
           checkPanel.add(checky7);
           checkPanel.add(txty7);
           checkPanel.add(lk7);
           checkPanel.add(txtk7);
           checkPanel.add(lk14);
           checkPanel.add(txtk14);
           
           


           setLayout(new BorderLayout() );
            add(checkPanel, BorderLayout.EAST);
            add(buttonPanel, BorderLayout.NORTH);
 

        }


        public void itemStateChanged( ItemEvent ae){
         if(checky1.getState()==true){ y1sen=10;}
         else{ y1sen=0;}
         if(checky2.getState()==true){ y2sen=10;}
         else{ y2sen=0;}
         if(checky3.getState()==true){ y3sen=10;}
         else{ y3sen=0;}
         if(checky4.getState()==true){ y4sen=10;}
         else{ y4sen=0;}
         if(checky5.getState()==true){ y5sen=10;}
         else{ y5sen=0;}
         if(checky6.getState()==true){ y6sen=10;}
         else{ y6sen=0;}
         if(checky7.getState()==true){ y7sen=10;}
         else{ y7sen=0;}
         if(checknorm.getState()==true){ mnormal=1;}
         else{ mnormal=0;}
         


                                                     }

        public void  actionPerformed( ActionEvent ae)
	{

     k1 = Double.valueOf(txtk1.getText()).doubleValue();
     k2 = Double.valueOf(txtk2.getText()).doubleValue();
     k3 = Double.valueOf(txtk3.getText()).doubleValue();
     k4 = Double.valueOf(txtk4.getText()).doubleValue();
     k5 = Double.valueOf(txtk5.getText()).doubleValue();
     k6 = Double.valueOf(txtk6.getText()).doubleValue();
     k7 = Double.valueOf(txtk7.getText()).doubleValue();
     k8 = Double.valueOf(txtk8.getText()).doubleValue();
     k9 = Double.valueOf(txtk9.getText()).doubleValue();
     k10= Double.valueOf(txtk10.getText()).doubleValue();
     k11= Double.valueOf(txtk11.getText()).doubleValue();
     k12= Double.valueOf(txtk12.getText()).doubleValue();
     k13= Double.valueOf(txtk13.getText()).doubleValue();
     k14= Double.valueOf(txtk14.getText()).doubleValue();
     ttime = Double.valueOf(txttime.getText()).doubleValue();
     if(ttime > ttimemax){ttime=ttimemax;
     txttime.setText(String.valueOf(ttime));
                         }
     time1=(int)(ttime/3);
     time2=(int)(ttime*2/3);
     time3=(int)(ttime);
     y1 = Double.valueOf(txty1.getText()).doubleValue();
     y2 = Double.valueOf(txty2.getText()).doubleValue();
     y3 = Double.valueOf(txty3.getText()).doubleValue();
     y4 = Double.valueOf(txty4.getText()).doubleValue();
     y5 = Double.valueOf(txty5.getText()).doubleValue();
     y6 = Double.valueOf(txty6.getText()).doubleValue();
     y7 = Double.valueOf(txty7.getText()).doubleValue();
     
            if(ae.getSource()==breplot){
                breplot.setLabel("Replot");
                nreplot=1;
                repaint();
                                         }

           if(ae.getSource()==butp){
            butp.setLabel("Integrate");
            repaint();
                                   }
          
           if(ae.getSource()==butt){
            
          if(butt.getLabel().equals("Start")){
           butt.setLabel("Reset");
           butp.setLabel("Integrate");
           breplot.setLabel("Replot");
          firsttime=false;
	  repaint();
                                              }
          else{
           butt.setLabel("Reset");
        k1=2.0;k2=0.9;k3=2.5;k4=1.5;k5=0.6;k6=0.8;k7=1.0;k8=1.3;k9=0.3;k10=0.8;
        k11=0.7;k12=4.9;k13=18.0;k14=4.5; 
        y1=1;y2=1;y3=1;y4=1;y5=1;y6=.1;y7=1;
       txtk1.setText(String.valueOf(k1));
       txtk2.setText(String.valueOf(k2));
       txtk3.setText(String.valueOf(k3));
       txtk4.setText(String.valueOf(k4));
       txtk5.setText(String.valueOf(k5));
       txtk6.setText(String.valueOf(k6));
       txtk7.setText(String.valueOf(k7));
       txtk8.setText(String.valueOf(k8));
       txtk9.setText(String.valueOf(k9));
       txtk10.setText(String.valueOf(k10));
       txtk11.setText(String.valueOf(k11));
       txtk12.setText(String.valueOf(k12));
       txtk13.setText(String.valueOf(k13));
       txtk14.setText(String.valueOf(k14));
       txty1.setText(String.valueOf(y1));
       txty2.setText(String.valueOf(y2));
       txty3.setText(String.valueOf(y3));
       txty4.setText(String.valueOf(y4));
       txty5.setText(String.valueOf(y5));
       txty6.setText(String.valueOf(y6));
       txty7.setText(String.valueOf(y7));
       nreset=1;
       repaint ();
              }
                                    } 
        }

        }









