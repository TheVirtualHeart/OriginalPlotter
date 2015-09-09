//this code will NOT compile, it is for reference and simplification
int cableLength=195;
double u[] = new double[cableLength];
double ut[]= new double[cableLength];
double v[] = new double[cableLength];
double w[] = new double[cableLength];
double s[] = new double[cableLength];
double lapd1[] = new double[cableLength];
double lapd2[] = new double[cableLength];
double lapd11[] = new double[cableLength];
double lapd22[] = new double[cableLength];
double cv1[] = new double[cableLength];
double cv2[] = new double[cableLength];
int ncv1[]= new int[cableLength];
int nncv1[]= new int[cableLength];
int ncv2[]= new int[cableLength];
int nncv2[]= new int[cableLength];
double tp[][] = new double[cableLength][50];
int ntp[]= new int[cableLength];
int step=0;
int nntime=0;
int ncounter=0;
int imm=1;
int npp=1980;
int nppc=0;
double dt=0.15;
double dx=.0479;
double vlo=-.2;
double dvt;
double table1[]= new double[17050];
double table2[]= new double[17050];
double table3[]= new double[17050];

public void init()
{
	double vhi = 2, vv, tso1 = 200, tso2 = 0.54494910, us = 0.86854118, uso = 0.34233130, xks = 3.2239589, xkso = 2.7969265, nvt = 17000;
	dvt=(vhi-vlo)/((double)(nvt));
	for(int i=0; i < nvt-1; i++)
	{
		vv=vlo+dvt*(double)(i);
		table1[i] = 97. + (170. - 97.) * (1 - ((Math.exp((vv - .325) * 20) - Math.exp(-(vv - .325) * 20)) / (Math.exp((vv - .325) * 20) + Math.exp(-(vv - .325) * 20)))) / 2;
		table2[i] = tso1 + (tso2 - tso1) * (1. + ((Math.exp((vv - uso) * xkso) - Math.exp(-(vv - uso) * xkso)) / (Math.exp((vv - uso) * xkso) + Math.exp(-(vv - uso) * xkso)))) / 2.;
		table3[i] = (1. + ((Math.exp((vv - us) * xks) - Math.exp(-(vv - us) * xks)) / (Math.exp((vv - us) * xks) + Math.exp(-(vv - us) * xks)))) / 2.;
	}
	for(int i=0; i < cableLength+1; i++)
	{
		u[i] = i < 5 ? 1:0;
		v[i]=1;
		w[i]=1;
		s[i]=0;
		ntp[i]=0;
		nncv1[i]=0; 
		nncv2[i]=0;
	}
	
	Buffer=createImage(1140,720);
	gBuffer=Buffer.getGraphics();
}

public void itemStateChanged(ItemEvent ie)
{
	if(ie.getItem().toString()== "period 297")
	{
		imm=1;
		npp=1980;
		nppc=0;
	}
	if(ie.getItem().toString()== "period 225")
	{
		imm=2;
		npp=1370;
		if(nppc>=npp)
			nppc=0;
	}
	if(ie.getItem().toString()== "period 165")
	{
		imm=3;
		npp=1100;
		if(nppc>=npp)
			nppc=0;
	}
	if(ie.getItem().toString()== "period 150")
	{
		imm=4;
		npp=1000;
		if(nppc>=npp)
			nppc=0;
	}
	if(ie.getItem().toString()== "period 135")
	{
		imm=5;
		npp=1200;
		if(nppc>=npp)
			nppc=0;
	}
}

public void actionPerformed( ActionEvent ae)
{
	if(ae.getSource()==butres)
	{
		for(int i=0; i < cableLength+1; i++)
		{
			u[i]=0;
			v[i]=1;
			w[i]=1;
			s[i]=0;
			ut[i]=0;
			lapd1[i]=0;
			lapd2[i]=0;
			lapd11[i]=297;
			lapd22[i]=297;
			cv1[i]=0;
			cv2[i]=0;
			ntp[i]=0;
			nncv1[i]=0; 
			nncv2[i]=0;
		}

		imm=1;
		npp=1980;
		nppc=0;
	}
}

public void update(Graphics g)
{
	int y1, y2;
	
	for(int i=0; i< cableLength-1; i++) //rendering the graphs, excluding beat chart (that is done mid-analysis, which may need to be changed)
	{		
		if(ntp[i]==1) //odd beat in top graph
		{
			gBuffer.setColor(Color.orange);
			y1=(int)(225-.25*120);
			gBuffer.drawLine(i+50, y1, i+51, y1);
		}
		else if(ntp[i]==3) //even beat in top graph
		{
			gBuffer.setColor(Color.green);
			y1=(int)(225-.25*120);
			gBuffer.drawLine(i+50, y1, i+51, y1);
		}
	
		gBuffer.setColor(Color.black); //draws the first graph
		y1=(int)(225-u[i]*120.0);
		y2=(int)(225-u[i+1]*120.0);
		gBuffer.drawLine(i+50, y1, i+51, y2);
	
		if(i>5 && lapd1[i+1] > 10) //orange line in remaining 3 graphs
		{
			gBuffer.setColor(Color.orange);
			
			y1=(int)((480-lapd1[i]*1.25));
			y2=(int)((480-lapd1[i+1]*1.25));
			gBuffer.drawLine(i+50,y1,i+51,y2);
			
			if(i > 11 && i< cableLength-6 && nncv1[i+1] > 530)
				gBuffer.drawLine(i+50, nncv1[i], i+51, nncv1[i+1]); //figure out what this does
			
			switch(imm)
			{
				case 1:
					y1=(int)((2856-lapd11[i]*8.));
					y2=(int)((2856-lapd11[i+1]*8.));
					break;
				case 2:
					y1=(int)((2280-lapd11[i]*8.));
					y2=(int)((2280-lapd11[i+1]*8.));
					break;
				case 3:
					y1=(int)((1800-lapd11[i]*8.));
					y2=(int)((1800-lapd11[i+1]*8.));
					break;
				case 4:
					y1=(int)((1680-lapd11[i]*8.));
					y2=(int)((1680-lapd11[i+1]*8.));
					break;
				case 5:
					y1=(int)((1560-lapd11[i]*8.));
					y2=(int)((1560-lapd11[i+1]*8.));
					break;
				default:
					break;
			}
			
			if(y2 > 440 && y1 > 440 && y2 < 520 && y1 < 520 )
				gBuffer.drawLine(i+50, y1, i+51, y2);
		}
		
		if(i>5 && lapd2[i+1] > 10) //green line in remaining 3 graphs
		{
			gBuffer.setColor(Color.green);
			
			y1=(int)((480-lapd2[i]*1.25));
			y2=(int)((480-lapd2[i+1]*1.25));
			gBuffer.drawLine(i+50,y1,i+51,y2);
			
			if(i > 11 && i< cableLength-6 && nncv2[i+1] > 530)
				gBuffer.drawLine(i+50, nncv2[i], i+51, nncv2[i+1]);
			
			switch(imm)
			{
				case 1:
					y1=(int)((2856-lapd22[i]*8.));
					y2=(int)((2856-lapd22[i+1]*8.));
					break;
				case 2:
					y1=(int)((2280-lapd22[i]*8.));
					y2=(int)((2280-lapd22[i+1]*8.));
					break;
				case 3:
					y1=(int)((1800-lapd22[i]*8.));
					y2=(int)((1800-lapd22[i+1]*8.));
					break;
				case 4:
					y1=(int)((1680-lapd22[i]*8.));
					y2=(int)((1680-lapd22[i+1]*8.));
					break;
				case 5:
					y1=(int)((1560-lapd22[i]*8.));
					y2=(int)((1560-lapd22[i+1]*8.));
					break;
				default:
					break;
			}
			
			if(y2 > 440 && y1 > 440 && y2 < 520 && y1 < 520 )
				gBuffer.drawLine(i+50,y1,i+51,y2);
		}
	}
	
	if(active) //active being used to start and stop the graphs
	{
		step++;
		
		//renderBeats being a boolean that determines if the even odd beat graph is rendered
		if(renderBeats)
		{
			nntime++;
			if(nntime > 17500)
			{
				gBuffer.setColor(Color.white);
				gBuffer.fillRect(380,68,502,350);
				nntime = 0;
			}
		}
		
		nppc++;
		if(nppc==npp) //induces a beat if a period has been reached
		{
			u[1]=1;
			nppc=0;
		}
		
		int izoom=slider1.getValue();
		if(izoom > cableLength)
		{
			for(int i=0; i < cableLength+1; i++)
			{
				u[i]=0;
				lapd1[i]=0;
				lapd2[i]=0;
				lapd11[i]=297;
				lapd22[i]=297;
				cv1[i]=0;
				cv2[i]=0;
				ntp[i]=0;
			}
			
			npp=1980;
			nppc=0;
		}
		cableLength=izoom;
		
		for(int j=0; j < cableLength; j++) //solving differential equation and populating ut, w, v, s (u is populated after analysis from ut, so as to have a new and old value pairing)
		{
			int windex=Math.floor((w[j]-vlo)/dvt); //table indexes for w and v
			int vindex=Math.floor((u[j]-vlo)/dvt);
			double um = 0.3, up = 0.13, uq = 0.2, ur = 0.006;
			double xm = u[j]>um?1:0;
			double xp = u[j]>up?1:0;
			double xq = u[j]>uq?1:0;
			double xr = u[j]>ur?1:0;
			double tvm1 = 20, tvm2 = 20, twm = 50;
			double tvm = (1-xq)*tvm1+xq*tvm2;
			
			if(w[j] > .45)
				twm=180;
			else if(w[j] > .40)
				twm=92;
			else if(w[j] > .14)
				twm=table1[windex];
			
			double ts1 = 1., ts2 = 1.7721446, to1 = 600, to2 = 20;
			double ts  = (1.-xp)*ts1 + xp*ts2;
			double to  = (1.-xr)*to1 + xr*to2;
			double vinf = 1.-xq;
			double twinf = 9.397222, winfast = 2.8725006;
			double winf = Math.max((1.-xr)*(1.-u[j]/twinf)+xr*winfast, 1.);
			double tvp = 1.4506, twp = 67.880750;
			double dv  = (1.-xm)*(vinf-v[j])/tvm-xm*v[j]/tvp;
			double dw  = (1.-xp)*(winf-w[j])/twm-xp*w[j]/twp;
			double ds  = (table3[vindex]-s[j])/ts;
			v[j] = v[j] + dt*dv;
			w[j] = w[j] + dt*dw;
			s[j] = s[j] + dt*ds;
			double uu = 1.56, tfi = 0.104, uo = 0.0, tsi = 1.3459624;
			double tso = table2[vindex];
			double xfi = -v[j]*xm*(u[j]-um)*(uu-u[j])/tfi;
			double xso = (u[j]-uo)*(1-xp)/to + xp/tso;
			double xsi = -xp*w[j]*s[j]/tsi;
			ut[j]=u[j]+(u[j+1]+u[j-1]-2*u[j])*dt*.001/(dx*dx)-dt*(xfi+xso+xsi);
		}
		
		for(int j=0; j < cableLength; j++) //analyzing the vwus
		{
			if((u[j]-.25)*(ut[j]-.25) < 0.)
			{
				ntp[j]++;
				tp[j][ntp[j]]=step*dt-((-.25+u[j])/(ut[j]-u[j]))*dt;
				
				switch (ntp[j])
				{
					case: 2
						lapd1[j] = tp[j][2] - tp[j][1];
						break;
					case: 3
						lapd11[j]=tp[j][3]-tp[j][1];
						cv2[j]=(dx)/(tp[j][ntp[j]]-tp[j-1][ntp[j]]);
						break;
					case: 4
						lapd2[j] = tp[j][4] - tp[j][3];
						break;
					case: 5
						lapd22[j]=tp[j][5]-tp[j][3];
						cv1[j]=(dx)/(tp[j][ntp[j]]-tp[j-1][ntp[j]]);
						ntp[j]=1;
						tp[j][1]=tp[j][5];
						break;
					default:
						break;
				}
				/*if (j > 6) //j >= width of stimulation
				{
					if(ntp[j]==1)
					{
						ncv1[j]=1100-(int)((cv1[j]*1000.*12));
						if(j>10 && j<cableLength-5)
						{
							nncv1[j]=(ncv1[j-1]+ncv1[j-2]+ncv1[j-3]+ncv1[j-4]+ncv1[j-5]+ncv1[j+1]+ncv1[j+2]+ncv1[j+3]+ncv1[j+4]+ncv1[j+5]+ncv1[j])/11;
						}
					}
					else if(ntp[j]==3)
					{
						ncv2[j]=1100-(int)((cv2[j]*1000.*12));
						if(j>10 && j<cableLength-5)
						{
							nncv2[j]=(ncv2[j-1]+ncv2[j-2]+ncv2[j-3]+ncv2[j-4]+ncv2[j-5]+ncv2[j+1]+ncv2[j+2]+ncv2[j+3]+ncv2[j+4]+ncv2[j+5]+ncv2[j])/11;
						}
					}
				}*/
			}
			
			if(renderBeats)
			{
				ncounter++;
				
				if(ncounter==3)
				{
					if(ntp[j] <=2)
						gBuffer.setColor(Color.orange);
					else
						gBuffer.setColor(Color.green);
					
					int vy1=142+j*1-(int)(u[j]*30);
					int vy2=142+j*1-(int)(ut[j]*30);
					int xntime1=380+(int)(nntime/35);
					int xntime2=381+(int)((nntime+1)/35);
					gBuffer.drawLine(xntime1,vy1,xntime2,vy2);  
					ncounter=0;
				}
			}
		}
		
		u = ut.slice();
	}
	
	g.drawImage(Buffer,0,0, this);
}