private TextField strength,duration,length,position;
Button startButt,pauseButt;
Button bstimulus;
Choice mode;
boolean hasStarted=false;
double dt=0.010;
boolean paused=false;
boolean stimulating=false;
int stimdur;
Point stimRange;
int stimCounter=0;
double m[]= new double[1101];
double h[]= new double[1101];
double n[]= new double[1101];
double u[]= new double[1101];
double ut[]= new double[1101];
int cableLength=801;
double time=0;
boolean running=false;
double amt[] = new double[24000];
double bmt[] = new double[24000];
double aht[] = new double[24000];
double bht[] = new double[24000];
double ant[] = new double[24000];
double bnt[] = new double[24000];
double ik1t[] = new double[24000];
double dvt=0.01;
double vmin=-150;
double dx=.025;
double restingPotential = -84.5737;

public void init()
{
	//   ******************TABLE****************
	for(int i=0; i< 24000; i++)
	{
		double xu=vmin+i*dvt;
		if(Math.exp((-xu-50.0)/10.0)-1.0==0)
			ant[i]=ant[i-1];
		else
			ant[i]=0.0001*(-xu-50)/(Math.exp((-xu-50.0)/10.0)-1.0);
		bnt[i]=0.002*Math.exp((-xu-90.0)/80.0);
		if(Math.exp((-xu-48.0)/15.0)-1.0==0)
			amt[i]=amt[i-1];
		else
			amt[i]=0.1*(-xu-48.0)/(Math.exp((-xu-48.0)/15.0)-1.0);
		if(Math.exp((xu+8.)/5.0)-1.0==0)
			bmt[i]=bmt[i-1];
		else
			bmt[i]=0.12*(xu+8.0)/(Math.exp((xu+8.)/5.0)-1.0);
		aht[i]=0.17*Math.exp((-xu-90)/20.0);
		bht[i]=1.0/(Math.exp((-xu-42.0)/10.0)+1.0);
		ik1t[i]=1.2*Math.exp((-xu-90.0)/50.0)+0.015*Math.exp((xu+90.0)/60.0);
	}
	//Initial conditions
	for(int i=0; i<1101; i++)
	{
		u[i]=restingPotential;
		int index=(int)((u[i]-vmin)/dvt);
		m[i]= amt[index]/(amt[index]+bmt[index]);
		h[i]= aht[index]/(aht[index]+bht[index]);
		n[i]= ant[index]/(ant[index]+bnt[index]);
	}
	cableLengthSlider = new Scrollbar(Scrollbar.HORIZONTAL, 800, 60, 20, 1160);
	bstimulus= new Button ("Apply S1");
	strength.setText("-35.0");
	duration= new TextField(4);
	duration.setText("30");
	length = new TextField(4);
	length.setText("0.5");
	position= new TextField(4);
	position.setText("0.25");
	mode= new Choice();
	mode.addItem("Cable");
	mode.addItem("Ring");
	pauseButt = new Button("      ");
	checkv = new Checkbox("Voltage", true);
	checkm= new Checkbox("m gate",false);
	checkh= new Checkbox("h gate",false);
	checkn= new Checkbox("n gate",false);
}

public void mousePressed(MouseEvent me) //simply apply stimulus with a girth of 2 at mouseX and strength mouseY
{
	Point p=me.getPoint();
	if(p.y > 30 && p.y < 266)
	{
		running=true;
		double up=(105.0-p.y)/1.4;
		if(up < -90)
			up=-90;
		if(up > 80)
			up=79.9;
		int px=(int)((p.x-20)*cableLength/501.);
		if(px > 0)
		{
			u[px]=up;
			u[px+1]=up;
			u[px+2]=up;
			if(px > 1)
				u[px-1]=up;
			if(px > 2)
				u[px-2]=up;
		}
	}
}

public void update (Graphics g)
{
	if(!hasStarted)
	{
		gBuffer.drawString("time="+String.valueOf((float)(time))+"ms",400,55);
		gBuffer.drawString("Length of cable: "+String.valueOf((float)(cableLength*dx))+" cm ",188,249);
	}
	else
	{
		int izoom=cableLengthSlider.getValue();
		if(izoom > cableLength)
		{
			for(int i=cableLength; i<izoom; i++)
			{
				u[i]=u[cableLength-1];
				m[i]= m[cableLength-1];
				h[i]= h[cableLength-1];
				n[i]= n[cableLength-1];
			}
		}
		cableLength=izoom;
		if(paused)
			return;
		boolean isCable = mode.toString() == "Cable";
		for(int b=0; b<250; b++)
		{  
			if(stimulating)
			{
				stimCounter=stimCounter+1;
				if(stimCounter > stimdur)
					stimulating=false;
			}
			if(running)
			{
				time+=dt;
				for(int i=0; i<cableLength; i++)
				{
					int istim=0;
					if(i > stimRange.x && i < stimRange.y && stimulating)
						istim=Double.valueOf(strength.getText()).doubleValue();
					int index=(int)((u[i]-vmin)/dvt);
					m[i]= m[i]+dt*(amt[index]*(1.0-m[i])-bmt[index]*m[i]);
					h[i]= h[i]+dt*(aht[index]*(1.0-h[i])-bht[index]*h[i]);
					n[i]= n[i]+dt*(ant[index]*(1.0-n[i])-bnt[index]*n[i]);
					double gk2=1.2, gna1=400, gna2=0.12;
					double ik2=gk2*n[i]*n[i]*n[i]*n[i];
					double ina=(gna1*m[i]*m[i]*m[i]*h[i]+gna2)*(u[i]-40);
					double ik=(ik2+ik1t[index])*(u[i]+100.);
					double xlap = 1;
					if (i==0)
					{
						if(isCable)
							xlap=2.*u[1]-2.*u[i];
						else
							xlap=u[cableLength-1]+u[1]-2*u[i];
					}
					else if (i==cableLength-1)
					{
						if(isCable)
							xlap=2.*u[cableLength-2]-2.*u[i];
						else
							xlap=u[cableLength-2]+u[0]-2*u[i];
					}
					else
						xlap=u[i-1]+u[i+1]-2.*u[i];
					double diffusion=.001, capacitance = 12.;
					double ddtOdx2 = diffusion * dt / (dx * dx);
					ut[i]=u[i]-dt*(ina+ik+istim)/capacitance+ddtOdx2*xlap;
				}
				for(int i=0; i<cableLength; i++)
					u[i]=ut[i];
				if(!isCable)
				{
					m[cableLength-1]= m[0];
					h[cableLength-1]= h[0];
					n[cableLength-1]= n[0];
					u[cableLength-1]=u[0];
					ut[cableLength-1]=ut[0];
				}
			}
		}
		//draw graph
		gBuffer.drawString("time="+String.valueOf((float)(time))+"ms",400,55);
		gBuffer.drawString("Length of cable: "+String.valueOf((float)(cableLength*dx))+" cm ",188,249);
		int xpos=80;
		if(checkv.getState())
		{
			gBuffer.setColor(Color.red);
			gBuffer.drawString("V",xpos,55);
			xpos=xpos+20;
			for(int i=0; i<cableLength-1; i+=1)
			{
				int y1=(int)(105-u[i]*1.4);
				int y2=(int)(105-u[i+1]*1.4);
				double xx11=i*501.0/cableLength;
				double xx22=(i+1.)*501.0/cableLength;
				int x1=(int)Math.ceil(xx11);
				int x2=(int)Math.ceil(xx22);
				gBuffer.drawLine(x1+20,y1,x2+20,y2);
			}
		}
		if(checkm.getState())
		{
			gBuffer.setColor(new Color(12,100,12));
			gBuffer.drawString("m",xpos,55);
			xpos=xpos+20;
			for(int i=0; i<cableLength-2; i+=2)
			{
				int y1=(int)(230-m[i]*170);
				int y2=(int)(230-m[i+2]*170);
				int x1=(int)(i*500/cableLength);
				int x2=(int)((i+2)*500/cableLength);
				gBuffer.drawLine(x1+20,y1,x2+20,y2);
			}
		}
		if(checkh.getState())
		{
			gBuffer.setColor(Color.blue);
			gBuffer.drawString("h",xpos,55);
			xpos=xpos+20;
			for(int i=0; i<cableLength-2; i+=2)
			{
				int y1=(int)(230-h[i]*170);
				int y2=(int)(230-h[i+2]*170);
				int x1=(int)(i*500/cableLength);
				int x2=(int)((i+2)*500/cableLength);
				gBuffer.drawLine(x1+20,y1,x2+20,y2);
			}
		}
		if(checkn.getState())
		{
			gBuffer.setColor(Color.orange);
			gBuffer.drawString("n",xpos,55);
			for(int i=0; i<cableLength-2; i+=2)
			{
				int y1=(int)(230-n[i]*170);
				int y2=(int)(230-n[i+2]*170);
				int x1=(int)(i*500/cableLength);
				int x2=(int)((i+2)*500/cableLength);
				gBuffer.drawLine(x1+20,y1,x2+20,y2);
			}
		}
		g.drawImage(Buffer,0,0, this);
	}
}

public void  actionPerformed( ActionEvent ae)
{
	if(ae.getSource()==bstimulus)
	{
		running=true;
		stimulating=true;
		stimCounter=0;
		stimdur=(int)(Double.valueOf(duration.getText()).doubleValue()/dt);
		double stimMedian=Double.valueOf(position.getText()).doubleValue()/dx;
		double stimGirth=Double.valueOf(length.getText()).doubleValue()/dx/2.;
		stimRange.x=(int)(stimMedian-stimGirth);
		stimRange.y=(int)(stimMedian+stimGirth);
		if(stimRange.x < 0)
			stimRange.x = 0;
		if(stimRange.y > cableLength)
			stimRange.y=cableLength;
	}
	if(ae.getSource()==pauseButt)
	{
		if(hasStarted)
		{
			if(pauseButt.getLabel().equals("Resume"))
			{
				pauseButt.setLabel("Pause");
				paused=false;
			}
			else
			{
				pauseButt.setLabel("Resume");
				paused=true;
			} 
		}
	}
	if(ae.getSource()==startButt)
	{
		if(startButt.getLabel().equals("Start"))
		{
			startButt.setLabel("Reset");
			hasStarted=true;
			pauseButt.setLabel("Pause");
		}
		else
		{
			time=0;
			running=false;
			stimulating=false;
			cableLength=501;
			strength.setText("-35.0");
			duration.setText("30");
			length.setText("0.5");
			position.setText("0.25");
			for(int i=0; i<cableLength; i++)
			{
				u[i]=restingPotential;
				int index=(int)((u[i]-vmin)/dvt);
				m[i]= amt[index]/(amt[index]+bmt[index]);
				h[i]= aht[index]/(aht[index]+bht[index]);
				n[i]= ant[index]/(ant[index]+bnt[index]);
			}
		}
	}
}