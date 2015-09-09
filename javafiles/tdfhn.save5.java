//    Java program for FHN cell model in 2D
//
// Copyright (c) 1998 Flavio H Fenton.
//
//
//   
//
//This program is free software; you can redistribute it and/or
//modify it under the terms of the GNU General Public License
//as published by the Free Software Foundation; either version 2
//of the License, or (at your option) any later version.
//
//This program is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//GNU General Public License for more details.
//
//for  copy of the GNU General Public License
//write to the Free Software
//Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
//
//*********************************************************************************
// Your are welcome to use, change and redistribute this progrma, we only request
// to please ratain the above information for future references.
// Also, send an e-mail to Flavio Fenton at matfhf@hofstra.edu whenever changes or
// re-distributions are made.
// If the code is used in any way for research leading to an article, we kindly request
// citation to "Real-time Computer Simulations of Excitable Media: Java as a Scientific
// Language and as a Wrapper for C and Fortran Programs"
// F.H. Fenton, E.M. Cherry, H.M. Hastings and S.J. Evans, BioSystems, Vol 64, 73-96 (2002)
//*******************************************************************************




        import java.awt.*;
        import java.applet.Applet;
        import java.awt.event.*;
        import java.lang.*;
        import java.awt.image.MemoryImageSource;
      //import java.awt.image.ColorModel;
        import java.awt.image.IndexColorModel;

         /*
         <applet code="tdfhn" width=510 height=510>
          </applet>
          */


     public class tdfhn extends Applet
      implements MouseListener, ActionListener, ItemListener, Runnable {
      Thread  thread;
      Button bstartstop, bric,bwic,brestart, breseth, btip, bsignal, bstim;
      Choice chcolormap, chboundary;
      Checkbox chuplot,chvplot,chusig,chvsig;
      Label ldt,ldx,ldy,leps,lgamma,lbeta,lgsize,laa,ldelta,ls1size;
      TextField txtdt,txtdx,txtdy,txteps,txtgamma,txtbeta,txtgsize,txtaa,txtdelta,txts1size;
      Panel  Panela;
      Color  background;
      Image img,img1,lvent,iimg1,lventt;
      MediaTracker  tr;
      Graphics g1;
      Point p;
      boolean down;
      double  dt=.5,dx=1.,dy=1.,eps=.01,gamma=1,bbeta=.5,ggsize,ss1size,ggsizeold;
      double  aa=0.1,delta=0,xlap;
      double u[][]=new double[300][300],v[][]=new double[300][300];
      double ut[][]=new double[300][300],tmp[][]=new double[300][300];
      double tmpo[][]=new double[300][300];
      double alfa[]=new double[300],beta[]=new double[300];
      double usig[]=new double[400],vsig[]=new double[400];
      int gsize=200,s1size=5,gsizeold=200;
      int nmap=0,mapon=1,nmapold=0;
      int nboundary=0;
      int nchuplot=0;
      int nchvplot=0;
      int nchusig=0;
      int nchvsig=0;
      int mplot=1; 
      int n=-1;
      int nn=0;
      int nfirst=0,mmtime=3;
      int ir[]= new int[260];
      int ig[]= new int[260];
      int ib[]= new int[260];
      int irr[]= new int[260];
      int igg[]= new int[260];
      int ibb[]= new int[260];
      int irrr[]= new int[260];
      int iggg[]= new int[260];
      int ibbb[]= new int[260];
      int k=0,ktime,nstop=1,nbsignal=0,nbstim=0,nok=0,nok1=0,nstart=0,msenal=0;
      int norig=0,nx=200,nxi,nxf,nyi,nyf,ipx=100,ipy=100,iipx=100,iipy=100,jpx,jpy;
      int ny1,ny2,nx1,nx2,nxsignal=0,mi,mf,ni,nf;
      double rcx,rcy,bbx,bby,aaccy,aaccx,func,den;
      float uipx,vipx;
      byte pix[] = new byte[205*205];
  
  IndexColorModel myColorModel= null;
  IndexColorModel myColorModel2= null;
  IndexColorModel myColorModel3= null;



public void init()
{
//  set background color to SandyBrown (rgb=244, 164, 96)
//  for wheat use (rgb=245 222 179)

//    background = new Color (244, 164, 96);
        background = new Color (0,0,0);

     setBackground(background);

         tr=  new MediaTracker(this);
        lvent= getImage(getCodeBase(), "heart1.gif");
        lventt= getImage(getCodeBase(), "heart2.gif");

         addMouseListener(this);
//--------- Layout of buttons,Choices,chechboxes,labels and textfields.----
//              Create Buttons
      bstartstop = new Button ("Start");
      bric       = new Button ("R. I.C.");
      bwic       = new Button ("W. I.C.");
      brestart   = new Button ("Restart");
      breseth    = new Button ("Reset h");
      btip       = new Button ("tip off");
      bsignal    = new Button ("trace off");
      bstim      = new Button ("S1.off");
      bstartstop.addActionListener( this );
      bric.addActionListener( this );
      bwic.addActionListener( this );
      brestart.addActionListener( this );
      breseth.addActionListener( this );
      btip.addActionListener( this );
      bsignal.addActionListener( this );
      bstim.addActionListener( this );

//              Create Choices
//     chcolormap =  new Choice();
//     chcolormap.addItem("color  1 ");
//     chcolormap.addItem("color  2 ");
//    chcolormap.addItem("color  3 ");
//     chboundary =  new Choice();
//     chboundary.addItem("reflexive");
//     chboundary.addItem("periodic");
//     chcolormap.addItemListener( this );
//     chboundary.addItemListener( this );

//           Create Checkboxes
//      chuplot = new Checkbox("Voltage", true);
//      chvplot = new Checkbox("v-gate", false);
//      chusig  = new Checkbox("Voltage",false);
//      chvsig  = new Checkbox("v-gate",false);
//      chuplot.addItemListener( this );
//      chvplot.addItemListener( this );
//      chusig.addItemListener( this );
//      chvsig.addItemListener( this );

setLayout (null); // disable the default layout manager
// add buttons to the applet
add (bstartstop);
add (bric);
add (bwic);
add (brestart);
add (breseth);
//add (btip);
//add (bsignal);
add (bstim);
// add Choices to the applet
//add (chcolormap);
//add (chboundary);
// add Checkboxes to the applet
//add (chuplot);
//add (chvplot);
//add (chusig);
//add (chvsig);
// place buttons at desired locations.
bstartstop.reshape (2, 2, 50, 25); 
bric.reshape (53,2,55,25);
bwic.reshape (111,2,55,25);
brestart.reshape (167,2,55,25);
breseth.reshape (225,2,55,25);
//btip.reshape (283,2, 55, 25); 
//bsignal.reshape (380, 300, 60, 25);
bstim.reshape (392, 150, 50, 25);
// place   Choices at desired locations.
//chcolormap.reshape (341, 3, 70, 25);
//chboundary.reshape (341, 28, 70, 25);
// place Checkboxes at desired locations.
//chuplot.reshape (260,90 , 55, 25);
//chvplot.reshape (260,120, 55, 25);
//chusig.reshape (380, 325, 55, 25);
//chvsig.reshape (380, 350, 55, 25);
// place Labels and TextFields.
ls1size = new Label("S1 size");
txts1size= new  TextField(3);
txts1size.addActionListener( this);
txts1size.setText(String.valueOf(s1size));
add (ls1size);
add (txts1size);
txts1size.reshape (400,175,35,25);
ls1size.reshape (495,202,65,25);


//  Panel  for Labels and TextFields.
//        Panela = new Panel();
      ldt    = new Label("dt");
      ldx    = new Label("dx");
      ldy    = new Label("dx");
      leps   = new Label("eps");
      lgamma  = new Label("gama");
      lbeta  = new Label("beta");
      laa    = new Label("a");
      ldelta = new Label("delta");
      lgsize = new Label("size");
      txtdt    = new  TextField(3);
      txtdx    = new  TextField(3);
      txtdy    = new  TextField(3);
      txteps   = new  TextField(3);
      txtgamma  = new  TextField(3);
      txtbeta  = new  TextField(3);
      txtaa  = new  TextField(3);
      txtdelta  = new  TextField(3);    
      txtgsize = new  TextField(3);
      txtdt.addActionListener( this);
      txtdx.addActionListener( this);
      txtdy.addActionListener( this);
      txteps.addActionListener( this);
      txtgamma.addActionListener( this);
      txtbeta.addActionListener( this);
      txtaa.addActionListener( this);
      txtdelta.addActionListener( this);
      txtgsize.addActionListener( this);
      txtdt.setText(String.valueOf(dt));
      txtdx.setText(String.valueOf(dx));
/*example of inverse
String s= "125";
Integer obj = Integer.valueOf(s);
int i= obj.intValue();
*/
      txtdy.setText(String.valueOf(dx));
      txteps.setText(String.valueOf(eps));
      txtgamma .setText(String.valueOf(gamma));
      txtbeta.setText(String.valueOf(bbeta));
      txtaa.setText(String.valueOf(aa));
      txtdelta.setText(String.valueOf(delta));
      txtgsize.setText(String.valueOf(gsize));

//------------Initial Conditions --------------   
		for(int i=0;i<=nx+1;i++){
		for(int j=0; j<=nx+1; j++)
		{
    	        v[i][j] = 0;
		u[i][j] =0;
		ut[i][j] =0;
                tmp[i][j] =0;
                tmpo[i][j] =0;
		}}  
/*
		for(int i=0; i<=(int)(nx/5.);i++){
		for(int j=0; j<= nx+1; j++)
		{
		u[i][j] =1;
		}}  
  */
      rcx=dt/(dx*dx);
      rcy=dt/(dy*dy);
      bbx=1.+2.*rcx;
      bby=1.+2.*rcy;
      aaccy=-2.*rcy;
      aaccx=-2.*rcx;


// ------------  map colors -----------------
     byte r[]= new byte[228];
     byte g[]= new byte[228];
     byte b[]= new byte[228];
    
     createPalette(r,g,b);

     myColorModel= new IndexColorModel(1,228,r,g,b);
     
     createPalette2(r,g,b);
     
     myColorModel2= new IndexColorModel(1,228,r,g,b);
    
     createPalette3(r,g,b);
     
     myColorModel3= new IndexColorModel(1,228,r,g,b);


          for(int i=0; i<15; i++){
           n=n+1;
          ir[i]=0;
          ig[i]=0+(int)(n*3.3);
          ib[i]=0+(int)(n*6.6);
          }
         n=-1;
         for(int i=15; i<53; i++){
          n=n+1;
          ir[i]=0;
          ig[i]=50+(int)(2.7*n);
          ib[i]=100;
         }
          n=-1;
         for(int i=53; i<90; i++){
          n=n+1;
          ir[i]=0;
          ig[i]=150;
          ib[i]=100-(int)(2.5*n);
          }
          n=-1;
         for(int i=90; i<109; i++){
          n=n+1;
          ir[i]=0+(int)(n*6.58);
          ig[i]=140-(int)(n*2.23);
          ib[i]=0;
          }
          n=-1;
         for(int i=109; i<128; i++){
          n=n+1;
          ir[i]=120+(int)(n*4.41);
          ig[i]=100-(int)(n*5.52);
          ib[i]=0;
          }
          n=-1;
         for(int i=128; i<147; i++){
          n=n+1;
          ir[i]=200;
          ig[i]=0+(int)(n*2);
          ib[i]=0;
          }
          n=-1;
         for(int i=147; i<195; i++){
          n=n+1;
          ir[i]=209+(int)(n*0.441);
          ig[i]=36+(int)(n*2);
          ib[i]=0;
          }
          n=0;
          nn=0;
//--------other color code -------
         for(int i=0; i<98; i++){
         irr[i]=0;
         igg[i]=0+(int)(2.55*n);
         ibb[i]=255-(int)(2.55*n);
         n=n+1;
         }
         n=1;
        for(int i=98; i<196; i++){
         irr[i]=0+(int)(2.55*n);
         igg[i]=igg[97]-(int)(2.55*nn);
         ibb[i]=0;
         n=n+1;
         nn=nn+1;
         }
//--------other color code -------
         for(int i=0; i<196; i++){
         irrr[i]=(int)(1.275*i);
         iggg[i]=(int)(1.275*i);
         ibbb[i]=(int)(1.275*i);
         }
// --- image for buffering and avoid flickering--
        img = createImage(250,250);
        img1= createImage(308,419);
        g1 = img1.getGraphics();
// ----- thread ----

          thread = new Thread(this);
          thread.start();

}

void createPalette(byte r[],byte g[],byte b[]) {
     int i;
     byte ro=0,go=0,bo=0;

   for (i=0;i<228;i++) {
         b[i]=0; g[i]=0; b[i]=0;
       }
  
  
    // ------------  map colors -----------------
         for( i=0; i<15; i++){
           n=n+1;
          r[i]=ro;
          g[i]=(byte)(go+(n*3.3));
          b[i]=(byte)(bo+(n*6.6));
          }
         n=-1;
        for( i=15; i<53; i++){
          n=n+1;
          r[i]=ro;
          g[i]=(byte)(50+(2.7*n));
          b[i]=(byte)(100);
         }
          n=-1;
         for( i=53; i<90; i++){
          n=n+1;
          r[i]=(byte)0;
          g[i]=(byte)150;
          b[i]=(byte)(100-(2.5*n));
          }
          n=-1;
         for( i=90; i<109; i++){
          n=n+1;
          r[i]=(byte)(0+(n*6.58));
          g[i]=(byte)(140-(n*2.23));
          b[i]=(byte)(0);
          }
          n=-1;
         for( i=109; i<128; i++){
          n=n+1;
          r[i]=(byte)(120+(n*4.41));
          g[i]=(byte)(100-(n*5.52));
          b[i]=(byte)(0);
          }
          n=-1;
          
         for( i=128; i<147; i++){
          n=n+1;
          r[i]=(byte)(200);
          g[i]=(byte)(0+(n*2));
          b[i]=(byte)(0);
          }
          n=-1;
         for( i=147; i<195; i++){
          n=n+1;
          r[i]=(byte)(209+(n*0.441));
          g[i]=(byte)(36+(n*2));
          b[i]=(byte)(0);
          }
          r[200]=(byte)(244); // for background color
          g[200]=(byte)(164);
          b[200]=(byte)(96);
          }

   void createPalette2(byte r[],byte g[],byte b[]) {
     int i;
     byte ro=0,go=0,bo=0;
     int n=0;
     int nn=0;
   for (i=0;i<228;i++) {
         b[i]=0; g[i]=0; b[i]=0;
       }
            for( i=0; i<98; i++){
         r[i]=(byte)(0);
         g[i]=(byte)(0+(2.55*n));
         b[i]=(byte)(255-(2.55*n));
         n=n+1;
         }
         n=1;
        for(i=98; i<196; i++){
         r[i]=(byte)(0+(2.55*n));
         g[i]=(byte)(ig[97]-(2.55*nn));
         b[i]=(byte)(0);
         n=n+1;
         nn=nn+1;
         }   
          r[200]=(byte)(244); // for background color
          g[200]=(byte)(164);
          b[200]=(byte)(96);       
   }
   void createPalette3(byte r[],byte g[],byte b[]) {
         for(int i=0; i<196; i++){
         r[i]=(byte)(1.275*i);
         g[i]=(byte)(1.275*i);
         b[i]=(byte)(1.275*i);
                                 }
          r[200]=(byte)(244); // for background color
          g[200]=(byte)(164);
          b[200]=(byte)(96);       
                                 
   }


        public void itemStateChanged( ItemEvent ae){
// choices
           mapon=1;
     //   nmapold=nmap;
        nmap=chcolormap.getSelectedIndex();
      //  if(nmap==nmapold){
     //   }
     //   else{
     //       mapon=1;
      //  }
        nboundary=chboundary.getSelectedIndex();
// checkboxes
         if(chuplot.getState()==true&chvplot.getState()==true){
           mplot=-1*mplot;}
         if(mplot==1){chvplot.setState(false);
           chuplot.setState(true);
                      }
         if(mplot==-1){chvplot.setState(true);
           chuplot.setState(false);
                                  }
         if(chusig.getState()==true){ nchusig=1;
            }
         else{ nchusig=0;}
         if(chvsig.getState()==true){ nchvsig=1;
           }
         else{ nchvsig=0;}
                                                  }

        public void mouseClicked(MouseEvent me){
        }
        public void mouseEntered(MouseEvent me){
        }
        public void mouseExited(MouseEvent me){
        }
        public void mousePressed(MouseEvent me){

         p=me.getPoint();
          if(nbstim==0 && nbsignal==1){

         iipx=(int)(p.x-10-norig);
         iipy=(int)(p.y-60-norig);
             nok1=0;
          if(200-iipx > 0 && 200-iipy > 0 && iipx >= 0 && iipy >=0){
             ipx=(int)(p.x-10-norig);
             ipy=(int)(p.y-60-norig);
             nok1=1;
             nxsignal=0;
             for(int i=0; i<90; i++){
                usig[i]=0;
                vsig[i]=0;          }
                                                           }
                                       }
          if(nbstim==1){
         iipx=(int)(p.x-10-norig);
         iipy=(int)(p.y-60-norig);
             nok=0;
          if(gsize-iipx>0 && gsize-iipy>0){
            nok=1;
                                          }
                       }
           

/*          pp=p.y;
          u[p.x-20]=(95.0-pp)/2.0;
          u[p.x-19]=(95.0-pp)/2.0;
          u[p.x-18]=(95.0-pp)/2.0;
          u[p.x-21]=(95.0-pp)/2.0;
          u[p.x-22]=(95.0-pp)/2.0;
*/
         down=true;
        }
        public void mouseReleased(MouseEvent me){
          down=false;
        }


        public void  actionPerformed( ActionEvent ae) {
       ggsizeold=ggsize;
      dt    =Double.valueOf(txtdt.getText()).doubleValue();
      dx    =Double.valueOf(txtdx.getText()).doubleValue(); 
      dy    =Double.valueOf(txtdx.getText()).doubleValue();
      eps   =Double.valueOf(txteps.getText()).doubleValue();
      gamma =Double.valueOf(txtgamma.getText()).doubleValue();
      bbeta =Double.valueOf(txtbeta.getText()).doubleValue();
      aa    =Double.valueOf(txtaa.getText()).doubleValue();
      delta =Double.valueOf(txtdelta.getText()).doubleValue();
     ggsize =Double.valueOf(txtgsize.getText()).doubleValue();
     ss1size=Double.valueOf(txts1size.getText()).doubleValue();
      gsize = (int)(ggsize);
      if(gsize >= 201)gsize=200;
      if(gsize <=49) gsize=50;
      gsize = (int)((gsize/2));
      gsize = gsize*2;
      txtgsize.setText(String.valueOf(gsize));
     s1size = (int)(ss1size);
      norig= (int)((200-gsize)/2);
       if(gsize<gsizeold){
       for(int i=0; i<norig; i++){
        for (int j=0; j<201; j++){
            u[i][j]=0;
            v[i][j]=0;
           tmpo[i][j]=0;
           tmp[i][j]=0;
            u[200-i][j]=0;
            v[200-i][j]=0;
           tmpo[200-i][j]=0;
           tmp[200-i][j]=0;
            u[j][i]=0;
            v[j][i]=0;
           tmpo[j][i]=0;
           tmp[j][i]=0;
            u[j][200-i]=0;
            v[j][200-i]=0;
           tmpo[j][200-i]=0;
           tmp[j][200-i]=0;
                                  }
                                  }
                           }
          gsizeold=gsize;
          if(ggsizeold==ggsize){
                               }
          else{
               bsignal.setLabel("trace off");
               nbsignal=0;
               nxsignal=0;
               for(int i=0; i<90; i++){
               usig[i]=0;
               vsig[i]=0;
                                       }                
      for (int i=0; i<100; i++){
       
       for (int j=0; j<100; j++){
              
      int  kk=(int)(200);
         pix[j*100+i]= (byte)(kk);  
                                             }}
               }

/* dont know if necesary yet
           input2.setText(input2.getText());
           input3.setText(input3.getText());
           input4.setText(input4.getText());
           input5.setText(input5.getText());
*/
           if(ae.getSource()==bstartstop){
             if(bstartstop.getLabel().equals("Start")){
               bstartstop.setLabel("Stop");
               if(nfirst==0){nstop=0;}
               else{
                nstop=0;
               }
// ---- DD stop the simulation
                                                      }
             else{
               bstartstop.setLabel("Start");
                 nstop=1; }
                                         }
           if(ae.getSource()==bric){
// ---- DD read ic.
                                    } 

           if(ae.getSource()==bwic){
// ---- DD write ic.
                                    }  
           if(ae.getSource()==brestart){
// ---- DD restart values
           nstart=0;
            for(int i=0; i<nx+1;i++){
			for(int j=0; j<nx+1; j++)
			{
			u[i][j] =0;
			v[i][j] =0;
			tmpo[i][j]=0;
            tmp[i][j]=0;
			}} 
		for(int i=0;i<=(int)((10+norig));i++){
		for(int j=0; j<nx+1; j++)
		{
		u[i][j] =1;
		}}  
//		repaint();
//		nstop=1;
                                       }
           if(ae.getSource()==breseth){
// ---- DD reset half plane 
//     make it a choice later.
            
            for(int i=0; i<nx+1;i++){
			for(int j=0; j<=nx/2; j++)
			{
			u[i][j] =0;
            tmpo[i][j]=0;
             tmp[i][j]=0;
//			v[i][j] =0;
			}} 

                                    }

           if(ae.getSource()==btip){
             if(btip.getLabel().equals("tip off")){
               btip.setLabel("tip on");
// ---- DD calcualte tip
                                                   }
             else{
               btip.setLabel("tip off");
                 }
                                    }

           if(ae.getSource()==bsignal){
             if(bsignal.getLabel().equals("trace off")){
               bsignal.setLabel("trace on");
               nbsignal=1;
               ipx=45;
               ipy=45;
// ---- DD calcualte tip
                                                        }
             else{
               bsignal.setLabel("trace off");
               nbsignal=0;
               nxsignal=0;
               for(int i=0; i<390; i++){
               usig[i]=0;
               vsig[i]=0;
                               }

                 }
                                      }

           if(ae.getSource()==bstim){
             if(bstim.getLabel().equals("S1.off")){
               bstim.setLabel("S1.on");
               nbstim=1;
// ---- DD calcualte tip
                                                   }
             else{
               bstim.setLabel("S1.off");
               nbstim=0;
                 }


                                    }

        }

        public void update (Graphics g){ paint(g); }                                            

        public void paint(Graphics g) {

          if(mapon==1){   
            
        g.setColor(background);
        g.fillRect(240,345,70,20);
        g.setColor(Color.white);


       if(mplot==1){
            
           g.drawString("+20 mV",368,275);
           g.drawString("-80 mV",368,455);
                        } 
           else{
           g.drawString("v-gate_max",245,75);
           g.drawString("v-gate_min",245,255); 
               }
            
            
          if(nmap==0){
          for(int ii=0; ii< 190; ii+=2){
            int iii=ii;
            if(iii<30)iii=30;
           g.setColor(new Color(ir[iii],ig[iii],ib[iii]));
           g.fillRect(355,455-ii,10,2);                            
                                       }
                      }
             if(nmap==1){
          for(int ii=0; ii< 190; ii+=2){
           g.setColor(new Color(irr[ii],igg[ii],ibb[ii]));
           g.fillRect(355,455-ii,10,2);                            
                                       }
                      }  
            if(nmap==2){
          for(int ii=0; ii< 190; ii+=2){
           g.setColor(new Color(irrr[ii],iggg[ii],ibbb[ii]));
           g.fillRect(355,455-ii,10,2);                            
                                       }
                      } 
                         }
       

     
 if(mmtime==3){
       

 if(mplot==1){  

     g1.drawImage(lvent,0,0, this);

 for (int i=1+norig; i<200-norig; i+=2){
    int ii=(int)(i/2.);
         for (int j=1+norig; j<200-norig; j+=2){
    int jj=(int)(j/2);        
  
      int  kk=(int)(130.*(u[i][j]+.4));
        if(kk > 70){
       
           g1.setColor(new Color(ir[kk],ig[kk],ib[kk]));
           g1.fillRect((int)(i*1.5)+15,(int)(j*1.5)+140,3,3);                            
       
                }  
      }}
             }
             else{    
  
             } 

        g1.drawImage(lventt,0,0, this);
       g.drawImage(img1,0,40,350,450, this); 
          mmtime=0;
              }
    
mmtime=mmtime+1;


       }

    public void run(){
       try{
       while(true){
        if(nstop==0){
            
            if(nfirst==0){   // initiate with a plane pulse
        for(int i=0;i<=(int)((10+norig));i++){
		for(int j=0; j<=nx; j++)
		{
		u[i][j] =1;
		}}  
		    nfirst=1;
		                 }
//----------- calculation ------------
          nxf=nx-norig;
          nxi=1+norig;
          nyf=nxf;
          nyi=nxi;
      rcx=dt/(dx*dx);
      rcy=dt/(dy*dy);
      bbx=1.+2.*rcx;
      bby=1.+2.*rcy;
      aaccy=-2.*rcy;
      aaccx=-2.*rcx;

//--------------conditions--------------
            if(nbstim==1 && nok==1){
            nbstim=0;
            nok=0;
             ni=iipx-s1size+norig;
             nf=iipx+s1size+norig;
             mi=iipy-s1size+norig;
             mf=iipy+s1size+norig;
            if(ni<0)ni=0;
            if(mi<0)mi=0;
            if(mf>200)mf=200;
            if(nf>200)nf=200;
            for(int i=ni; i<nf; i++){
            for(int j=mi; j<mf; j++){
              u[i][j]=1;
//              v[i][j]=0;
             tmpo[i][j]=0;
             tmp[i][j]=0;
                                   } }
             bstim.setLabel("S1.off");
                         }

            nstart=nstart+1;
/*            if(nstart==50){
            for(int i=0; i<=nxf+1;i++){
			for(int j=0; j<=nyf/2; j++)
			{
			u[i][j] =0;
			tmpo[i][j]=0;
            tmp[i][j]=0;
//			v[i][j] =0;
			}} 
 
                         }
*/

//----------------time integration----------------             
              
          for(int ktime=1; ktime<3; ktime++){


//----------- integration implicit on y ------------  
            for(int i=nxi; i<nxf+1; i++){  
            for(int j=nyi; j<nyf+1; j++){
              func=u[i][j]*(aa-u[i][j])*(u[i][j]-1.0)-v[i][j];
            tmp[i][j]=dt*1.5*func-0.5*tmpo[i][j]+u[i][j]+rcx*(u[i-1][j]+u[i+1][j]-2.*u[i][j]);
              tmpo[i][j]=dt*func;
                                   }
                                   }
            for(int i=nxi; i<nxf+1; i++){ 
            alfa[nyf-1]=2.*rcy/bby;
            beta[nyf-1]=tmp[i][nyf]/bby;
              for(int j=nyf-1; j>nyi; j--){
               den=bby-rcy*alfa[j];
               alfa[j-1]=rcy/den;
               beta[j-1]=(tmp[i][j]+rcy*beta[j])/den;
                                           }
              u[i][nyi]=(tmp[i][nyi]+2*rcy*beta[nyi])/(bby-2.*rcy*alfa[nyi]);
              v[i][nyi]=v[i][nyi]+dt*eps*(bbeta*u[i][nyi]-gamma*v[i][nyi]-delta);
              for(int j=nyi; j<nyf; j++){
               u[i][j+1]=u[i][j]*alfa[j]+beta[j];
               v[i][j+1]=v[i][j+1]+dt*eps*(bbeta*u[i][j+1]-gamma*v[i][j+1]-delta);
                                        }
                u[i][nyi-1]=u[i][nyi+1];
                u[i][nyf+1]=u[i][nyf-1];
     
                                        }  
     //new                                   
                       if(nbsignal==1){

           usig[nxsignal]=u[ipx+norig][ipy+norig];
           vsig[nxsignal]=v[ipx+norig][ipy+norig];
         if(nstop==0)nxsignal=nxsignal+1;
 
             if(nxsignal==350){
              nxsignal=0;
              for(int i=0; i<=390; i++){
               usig[i]=0;
               vsig[i]=0;
                                       }
                              }
                         }                            
                                       
//-------- integration implicit on x ---------
 
             for(int i=nxi; i<nxf+1; i++){  
            for(int j=nyi; j<nyf+1; j++){
              func=u[i][j]*(aa-u[i][j])*(u[i][j]-1.0)-v[i][j];
              tmp[i][j]=dt*1.5*func-0.5*tmpo[i][j]+u[i][j]+rcy*(u[i][j-1]+u[i][j+1]-2.*u[i][j]);
              tmpo[i][j]=dt*func;
                                   }
                                   }   
              for(int j=nxi; j<nxf+1; j++){ 
            alfa[nyf-1]=2.*rcx/bbx;
            beta[nyf-1]=tmp[nyf][j]/bbx;
              for(int i=nyf-1; i>nyi; i--){
               den=bbx-rcx*alfa[i];
               alfa[i-1]=rcx/den;
               beta[i-1]=(tmp[i][j]+rcx*beta[i])/den;
                                           }
              u[nyi][j]=(tmp[nyi][j]+2*rcx*beta[nyi])/(bby-2.*rcx*alfa[nyi]);
              v[nyi][j]=v[nyi][j]+dt*eps*(bbeta*u[nyi][j]-gamma*v[nyi][j]-delta);
              for(int i=nyi; i<nyf; i++){
               u[i+1][j]=u[i][j]*alfa[i]+beta[i];
               v[i+1][j]=v[i+1][j]+dt*eps*(bbeta*u[i+1][j]-gamma*v[i+1][j]-delta);
                                        }
                u[nyi-1][j]=u[nyi+1][j];
                u[nyf+1][j]=u[nyf-1][j];
                                         }                                            

/*     //new                                   
                       if(nbsignal==1){

           usig[nxsignal]=u[ipx][ipy];
           vsig[nxsignal]=v[ipx][ipy];
         if(nstop==0)nxsignal=nxsignal+1;
 
             if(nxsignal==350){
              nxsignal=0;
              for(int i=0; i<=390; i++){
               usig[i]=0;
               vsig[i]=0;
                                       }
                              }
                         }                 
*/

                                                }
     if(nstop==0){   repaint();}
//        msenal=msenal+1;
//        if(msenal==10)msenal=0;
                              }
        Thread.sleep(2);
                   }
           }
          catch(Exception e){
                            }
                       }
      
     





                         } 
                       
