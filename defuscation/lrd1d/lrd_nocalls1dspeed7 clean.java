int izoom;
int isCable = true;
int nx = 402;
boolean paused = false;
double istim;
double dvt = 0.01D;
double vmin = -115.0D;
double dx = 0.02D;
double l = 0.01D;
double a = 0.0011D;
double pi = 3.141592D;
double acap = (2.0D * pi * a * a + 2.0D * pi * a * l) * 2.0D;
double vcell = (1000.0D * pi * a * a * l);
double vmyo = (vcell * 0.68D);
double vnsr = (vcell * 0.0552D);
double vjsr = (vcell * 0.0048D);
double t = 0.0D;
double udt = 0.01D;
double lap = udt * 0.001D / 4.0E-4D;
double R = 8314.0D;
double frdy = 96485.0D;
double temp = 310.0D;
double frdy_Rtemp = frdy / (R * temp);
double ffrdy_Rtemp = frdy * frdy_Rtemp;
double Rtemp_frdy = 1.0D / frdy_Rtemp;
double zna = 1.0D;
double zk = 1.0D;
double zca = 2.0D;
double nao = 140.0D;
double gna = 16.0D;
double gcat = 0.05D;
double gnab = 0.004D;
int ndim = 805;
double[] v = new double[ndim];
double[] vnew = new double[ndim];
double[] dvdt = new double[ndim];
double[] dvdtnew = new double[ndim];
boolean[] flag = new boolean[ndim];
boolean[] stimsen = new boolean[ndim];
double[] dt = new double[ndim];
double[] xlap = new double[ndim];
int[] utsc = new int[ndim];
double[] tstim = new double[ndim];
double[] it = new double[ndim];
double[] nai = new double[ndim];
double[] ki = new double[ndim];
double[] ca = new double[ndim];
double[] nsr = new double[ndim];
double[] jsr = new double[ndim];
double[] tcicr = new double[ndim];
double[] irelcicr = new double[ndim];
double[] dcaiont = new double[ndim];
double[] dcaiontnew = new double[ndim];
double[] caiontold = new double[ndim];
double[] caiont = new double[ndim];
double[] mss = new double['?'];
double[] hss = new double['?'];
double[] jss = new double['?'];
double[] m = new double[ndim];
double[] h = new double[ndim];
double[] j = new double[ndim];
double[] d = new double[ndim];
double[] f = new double[ndim];
double[] dss = new double['?'];
double[] fss = new double['?'];
double[] b = new double[ndim];
double[] bss = new double['?'];
double[] gss = new double['?'];
double[] g = new double[ndim];
double[] xr = new double[ndim];
double[] xrss = new double['?'];
double[] r = new double['?'];
double[] xs1 = new double[ndim];
double[] xs2 = new double[ndim];
double[] xs1ss = new double['?'];
double[] otauxs1 = new double['?'];
double[] kp = new double['?'];
double[] fnak = new double['?'];
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
double[] expaki1 = new double['?'];
double[] expeki1 = new double['?'];
double[] expeki2 = new double['?'];
double[] expeki3 = new double['?'];
double[] expeki4 = new double['?'];
double[] expbki1 = new double['?'];
double[] expbki2 = new double['?'];
double[] expbki3 = new double['?'];

//'?' = table ?

public void init()
{
	double am, bm, ah, bh, aj, bj, taug;
	for (int i = 0; i < 20000; i++)
	{
		double d1 = vmin + i * dvt;
		if (Math.abs(d1 + 47.13D) < 1.0E-6D)
			am = 3.2D;
		else
			am = 0.32D * (d1 + 47.13D) / (1.0D - Math.exp(-0.1D * (d1 + 47.13D)));
		
		bm = 0.08D * Math.exp(-d1 / 11.0D);
		
		if (d1 < -40.0D)
		{
			ah = 0.135D * Math.exp((80.0D + d1) / -6.8D);
			bh = 3.56D * Math.exp(0.079D * d1) + 310000.0D * Math.exp(0.35D * d1);
			aj = (-127140.0D * Math.exp(0.2444D * d1) - 3.474E-5D * Math.exp(-0.04391D * d1)) * ((d1 + 37.78D) / (1.0D + Math.exp(0.311D * (d1 + 79.23D))));
			bj = 0.1212D * Math.exp(-0.01052D * d1) / (1.0D + Math.exp(-0.1378D * (d1 + 40.14D)));
		}
		else
		{
			ah = 0.0D;
			bh = 1.0D / (0.13D * (1.0D + Math.exp((d1 + 10.66D) / -11.1D)));
			aj = 0.0D;
			bj = 0.3D * Math.exp(-2.535E-7D * d1) / (1.0D + Math.exp(-0.1D * (d1 + 32.0D)));
		}
		double mtau = 1.0D / (am + bm);
		double htau = 1.0D / (ah + bh);
		double jtau = 1.0D / (aj + bj);
		
		mss[i] = am * mtau;
		hss[i] = ah * htau;
		jss[i] = aj * jtau;
		double omtau = 1.0D / mtau;
		double ohtau = 1.0D / htau;
		double ojtau = 1.0D / jtau;
		
		dss[i] = 1.0D / (1.0D + Math.exp(-(d1 + 10.0D) / 6.24D));
		double d2 = 0.035D * (d1 + 10.0D);
		double otaud;
		if (d2 != 0.0D)
			otaud = 1.0D / (dss[i] * (1.0D - Math.exp(-(d1 + 10.0D) / 6.24D)) / (0.035D * (d1 + 10.0D)));
		fss[i] = 1.0D / (1.0D + Math.exp((d1 + 32.0D) / 8.0D)) + 0.6D / (1.0D + Math.exp((50.0D - d1) / 20.0D));
		double otauf = 1.0D / (1.0D / (0.0197D * Math.exp(-Math.pow(0.0337D * (d1 + 10.0D), 2.0D)) + 0.02D));
		
		bss[i] = 1.0D / (1.0D + Math.exp(-(d1 + 14.0D) / 10.8D));
		double taub = 3.7D + 6.1D / (1.0D + Math.exp((d1 + 25.0D) / 4.5D));
		
		gss[i] = 1.0D / (1.0D + Math.exp((d1 + 60.0D) / 5.6D));
		if (d1 <= 0.0D)
			taug = -0.875D * d1 + 12.0D;
		else
			taug = 12.0D;
		
		xrss[i] = 1.0D / (1.0D + Math.exp(-(d1 + 21.5D) / 7.5D));
		double otauxr = 1.0D / (1.0D / (0.00138D * (d1 + 14.2D) / (1.0D - Math.exp(-0.123D * (d1 + 14.2D))) + 6.1E-4D * (d1 + 38.9D) / (Math.exp(0.145D * (d1 + 38.9D)) - 1.0D)));
		
		r[i] = 1.0D / (1.0D + Math.exp((d1 + 9.0D) / 22.4D));
		if (d1 != -30.0D)
			otauxs1[i] = 1.0D / (1.0D / (7.19E-5D * (d1 + 30.0D) / (1.0D - Math.exp(-0.148D * (d1 + 30.0D))) + 1.31E-4D * (d1 + 30.0D) / (Math.exp(0.0687D * (d1 + 30.0D)) - 1.0D)));
		else
			otauxs1[i] = otauxs1[i-1];
		xs1ss[i] = 1.0D / (1.0D + Math.exp(-(d1 - 1.5D) / 16.7D));
		
		kp[i] = 1.0D / (1.0D + Math.exp((7.488D - d1) / 5.98D));
		
		double sigma = (Math.exp(nao / 67.3D) - 1.0D) / 7.0D;
		
		fnak[i] = 1.0D / (1.0D + 0.1245D * Math.exp(-0.1D * d1 * frdy_Rtemp) + 0.0365D * sigma * Math.exp(-d1 * frdy_Rtemp));
		
		expzca[i] = Math.exp(zca * d1 * frdy_Rtemp);
		expzna[i] = Math.exp(zna * d1 * frdy_Rtemp);
		expzk[i] = Math.exp(zk * d1 * frdy_Rtemp);
		
		if (expzca[i] == 1.0D)
			expzca[i] = expzca[i-1];
		if (expzna[i] == 1.0D)
			expzna[i] = expzna[i-1];
		if (expzk[i] == 1.0D)
			expzk[i] = expzk[i-1];
		
		double gammas = 0.15D;
		expgammas[i] = Math.exp((gammas - 1.0D) * d1 * frdy_Rtemp);
		expvfrdy[i] = Math.exp(d1 * frdy_Rtemp);
		expm[i] = Math.exp(-udt * omtau);
		exph[i] = Math.exp(-udt * ohtau);
		expj[i] = Math.exp(-udt * ojtau);
		expd[i] = Math.exp(-udt * otaud);
		expf[i] = Math.exp(-udt * otauf);
		expb[i] = Math.exp(-udt / taub);
		expg[i] = Math.exp(-udt / taug);
		expxr[i] = Math.exp(-udt * otauxr);
		expxs1[i] = Math.exp(-udt * otauxs1[i]);
		expxs2[i] = Math.exp(-udt * otauxs1[i] * 0.25D);
		
		double vv = -93.0D + i * 5.0E-4D;
		expaki1[i] = Math.exp(0.2385D * (d1 - 59.215D));
		expeki1[i] = Math.exp(-vv * 0.2385D);
		expeki2[i] = Math.exp(-vv * 0.08032D);
		expeki3[i] = Math.exp(-vv * 0.06175D);
		expeki4[i] = Math.exp(vv * 0.5143D);
		expbki1[i] = Math.exp(0.08032D * (d1 + 5.476D));
		expbki2[i] = Math.exp(0.06175D * (d1 - 594.31D));
		expbki3[i] = Math.exp(-0.5143D * (d1 + 4.753D));
	}
	
	reset();
}

public void mousePressed(MouseEvent paramMouseEvent)
{
	p = paramMouseEvent.getPoint();
	if ((p.y > 30.0D) && (p.y < 266.0D))
	{
		double upp = (85.0D - p.y) / 1.7D;
		if (upp < -90.0D)
			upp = -90.0D;
		if (upp > 80.0D)
			upp = 79.9D;
		double d1 = (p.x - 20) * izoom / 501.0D;
		int i = (int)d1;
		if (i > 0)
		{
			v[i] = upp;
			v[i+1] = upp;
			v[i+2] = upp;
			if (i > 1)
				v[i-1] = upp;
			if (i > 2)
				v[i-2] = upp;
		}
	}
}

public void update(Graphics paramGraphics)
{
	if (paused)
		return;
	
	izoom = slider1.getValue();
	if (izoom > nx)
	{
		for (int i = nx - 1; i < izoom; i++)
		{
			v[i] = v[nx-2];
			m[i] = m[nx-2];
			h[i] = h[nx-2];
			j[i] = j[nx-2];
			d[i] = d[nx-2];
			f[i] = f[nx-2];
			irelcicr[i] = irelcicr[nx-2];
			dvdt[i] = dvdt[nx-2];
			nai[i] = nai[nx-2];
			ki[i] = ki[nx-2];
			ca[i] = ca[nx-2];
			xs1[i] = xs1[nx-2];
			xs2[i] = xs2[nx-2];
			xr[i] = xr[nx-2];
			b[i] = b[nx-2];
			g[i] = g[nx-2];
			
			jsr[i] = jsr[nx-2];
			nsr[i] = nsr[nx-2];
			flag[i] = flag[nx-2];
			dt[i] = dt[nx-2];
			
			caiont[i] = caiont[nx-2];
		}
	}
	nx = izoom - 1;
	
	int speed1, speed2;
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
	
	for (int increment = 0; increment < 50; increment += 1)
	{
		if (isCable)
		{
			v[0] = v[2];
			v[nx] = v[nx-1];
		}
		else
		{
			v[1] = v[nx-1];
			v[nx] = v[2];
			v[0] = v[nx-2];
		}
		
		for (int i = 1; i < nx; i++)
		{
			int nxstep = speed2;
			if ((v[i] > 10.0D) || (j[i] < 0.1D))
				nxstep = speed1;
			if (Math.abs(dvdt[i]) > 5.0D)
				nxstep = 0;
			else if ((v[i] > 0.0D) && (j[i] > 0.3D))
				nxstep = 0;
			else if ((xlap[i] > 0.15D) && (j[i] > 0.2D))
				nxstep = 0;
			else if (j[i] * m[i] > 0.5D)
				nxstep = 0;
			
			if (utsc[i] >= nxstep || irelcicr[i] > 0.01D || stimsen[i])
			{
				int index = (int)((v[i] - vmin) / dvt);
				
				double ena = Rtemp_frdy * Math.log(nao / nai[i]);
				double ina = gna * m[i] * m[i] * m[i] * h[i] * j[i] * (v[i] - ena);
				double ibarca, ibarna, ibark;
				double kmca = 6.0E-4D, pca = 5.4E-4D, gacai = 1.0D, gacao = 0.341D, pna = 6.75E-7D, ganai = 0.75D, ganao = 0.75D, pk = 1.93E-7D, gaki = 0.75D, gako = 0.75D, cao = 1.8D, ko = 4.5D, kmup = 9.2E-4D, iupbar = 0.00875D, nsrbar = 15.0D, tauon = 0.5D, tauoff = 0.5D, gmaxrel = 150.0D, csqnbar = 10.0D, kmcsqn = 0.8D, tautr = 180.0D, cmdnbar = 0.05D, trpnbar = 0.07D, kmcmdn = 0.00238D, kmtrpn = 5.0E-4D, c1 = 2.5E-4D, c2 = 1.0E-4D, kmpca = 5.0E-4D, ibarpca = 1.15D, kmko = 1.5D, kmnai = 10.0D, ibarnak = 2.25D, prnak = 0.01833D;
				if (Math.abs(v[i]) < 1.0E-6D)
				{
					ibarca = 0.00108D * (gacai * ca[i] * expzca[index] - gacao * cao);
					ibarna = 6.75E-7D * (ganai * nai[i] * expzna[index] - ganao * nao);
					ibark = 1.93E-7D * (gaki * ki[i] * expzk[index] - gako * ko);
				}
				else
				{
					ibarca = pca * zca * zca * (v[i] * ffrdy_Rtemp) * ((gacai * ca[i] * expzca[index] - gacao * cao) / (expzca[index] - 1.0D));
					ibarna = pna * zna * zna * (v[i] * ffrdy_Rtemp) * ((ganai * nai[i] * expzna[index] - ganao * nao) / (expzna[index] - 1.0D));
					ibark = pk * zk * zk * (v[i] * ffrdy_Rtemp) * ((gaki * ki[i] * expzk[index] - gako * ko) / (expzk[index] - 1.0D));
				}
				double fca = 1.0D / (1.0D + ca[i] / kmca);
				double ilca = d[i] * f[i] * fca * ibarca;
				double ilcana = d[i] * f[i] * fca * ibarna;
				double ilcak = d[i] * f[i] * fca * ibark;
				double eca = Rtemp_frdy * 0.5D * Math.log(cao / ca[i]);
				double icat = gcat * b[i] * b[i] * g[i] * (v[i] - eca);
				double gkr = 0.02614D * Math.sqrt(ko / 5.4D);
				double ekr = Rtemp_frdy * Math.log(ko / ki[i]);
				double ikr = gkr * xr[i] * r[index] * (v[i] - ekr);
				double gks = 0.433D * (1.0D + 0.6D / (1.0D + Math.pow(3.8E-5D / ca[i], 1.4D)));
				double eks = Rtemp_frdy * Math.log((ko + prnak * nao) / (ki[i] + prnak * nai[i]));
				double iks = gks * xs1[i] * xs2[i] * (v[i] - eks);
				double gki = 0.75D * Math.sqrt(ko / 5.4D);
				double eki = Rtemp_frdy * Math.log(ko / ki[i]);
				index = (int)((eki + 93.0D) / 5.0E-4D);
				double bki = (0.49124D * expbki1[index] * expeki2[index] + expbki2[index] * expeki3[index]) / (1.0D + expbki3[index] * expeki4[index]);
				double aki = 1.02D / (1.0D + expaki1[index] * expeki1[index]);
				double kin = aki / (aki + bki);
				double iki = gki * kin * (v[i] - eki);
				double gkp = 0.00552D;
				double ikp = gkp * kp[index] * (v[i] - eki);
				double inaca = c1 * expgammas[index] * ((expvfrdy[index] * nai[i] * nai[i] * nai[i] * cao - nao * nao * nao * ca[i]) / (1.0D + c2 * expgammas[index] * (expvfrdy[index] * nai[i] * nai[i] * nai[i] * cao + nao * nao * nao * ca[i])));
				double inak = ibarnak * fnak[index] * (1.0D / (1.0D + Math.pow(kmnai / nai[i], 2.0D))) * (ko / (ko + kmko));
				double ipca = ibarpca * ca[i] / (kmpca + ca[i]);
				double gcab = 0.003016D;
				double ecan = Rtemp_frdy * 0.5D * Math.log(cao / ca[i]);
				double icab = gcab * (v[i] - ecan);
				double inab = gnab * (v[i] - ena);
				
				double naiont = ina + inab + ilcana + 3.0D * inak + 3.0D * inaca;
				double kiont = ikr + iks + iki + ikp + ilcak - 2.0D * inak;
				caiont[i] = ilca + icab + ipca - 2.0D * inaca + icat;
				
				if (dvdtnew[i] > 10.0D && tcicr[i] > 10.0D && flag[i])
					flag[i] = false;
				
				if (stimsen[i])
					it[i] = istim + naiont + kiont + caiont[i];
				else
					it[i] = naiont + kiont + caiont[i];
				
				double dnai = -dt[i] * (naiont * acap) / (vmyo * zna * frdy);
				nai[i] = dnai + nai[i];
				
				double dki;
				if (stimsen[i])
					dki = -dt[i] * ((kiont + istim) * acap) / (vmyo * zk * frdy);
				else
					dki = -dt[i] * (kiont * acap) / (vmyo * zk * frdy);
				
				ki[i] = dki + ki[i];
				double itr = (nsr[i] - jsr[i]) / tautr;
				double ileak = (iupbar / nsrbar) * nsr[i];
				double iup = iupbar * ca[i] / (ca[i] + kmup);
				dcaiontnew[i] = (caiont[i] - caiontold[i]) / dt[i];
				
				if (v[i] > -35.0D && dcaiontnew[i] > dcaiont[i] && !flag[i])
				{
					flag[i] = true;
					tcicr[i] = 0.0D;
				}
				
				double on = 1.0D / (1.0D + Math.exp((-tcicr[i] + 4.0D) / tauon));
				double off = 1.0D - 1.0D / (1.0D + Math.exp((-tcicr[i] + 4.0D) / tauoff));
				double magrel = 1.0D / (1.0D + Math.exp((ilca + icab + ipca - 2.0D * inaca + icat + 5.0D) / 0.9D));
				irelcicr[i] = gmaxrel * on * off * magrel * (jsr[i] - ca[i]);
				tcicr[i] += dt[i];
				double csqn = csqnbar * (jsr[i] / (jsr[i] + kmcsqn));
				double djsr = dt[i] * (itr - irelcicr[i]);
				double bjsr = csqnbar - csqn - djsr - jsr[i] + kmcsqn;
				double cjsr = kmcsqn * (csqn + djsr + jsr[i]);
				jsr[i] = (Math.sqrt(bjsr * bjsr + 4.0D * cjsr) - bjsr) * 0.5D;
				nsr[i] += dt[i] * (iup - ileak - itr * vjsr / vnsr);
				double dcai = -dt[i] * (caiont[i] * acap / (vmyo * zca * frdy) + (iup - ileak) * vnsr / vmyo - irelcicr[i] * vjsr / vmyo);
				double trpn = trpnbar * (ca[i] / (ca[i] + kmtrpn));
				double cmdn = cmdnbar * (ca[i] / (ca[i] + kmcmdn));
				double catotal = trpn + cmdn + dcai + ca[i];
				double bmyo = cmdnbar + trpnbar - catotal + kmtrpn + kmcmdn;
				double cmyo = kmcmdn * kmtrpn - catotal * (kmtrpn + kmcmdn) + trpnbar * kmcmdn + cmdnbar * kmtrpn;
				double dmyo = -kmtrpn * kmcmdn * catotal;
				double gpig = Math.sqrt(bmyo * bmyo - 3.0D * cmyo);
				ca[i] = 2.0D * gpig / 3.0D * Math.cos(Math.acos((9.0D * bmyo * cmyo - 2.0D * bmyo * bmyo * bmyo - 27.0D * dmyo) / (2.0D * Math.pow(bmyo * bmyo - 3.0D * cmyo, 1.5D))) / 3.0D) - bmyo / 3.0D;
				utsc[i] = 0;
				dt[i] = 0.0D;
			}
			xlap[i] = v[i-1] + v[i+1] - 2.0D * v[i];
			vnew[i] = v[i] - it[i] * udt + lap * xlap[i];
			dvdtnew[i] = (vnew[i] - v[i]) / udt;
		}
		
		for (int i = 1; i < nx; i++)
		{
			v[i] = vnew[i];
			int index = (int)((v[i] - vmin) / dvt);
			dt[i] += udt;
			utsc[i] += 1;
			dvdt[i] = dvdtnew[i];
			caiontold[i] = caiont[i];
			dcaiont[i] = dcaiontnew[i];
			
			m[i] = mss[index] - (mss[index] - m[i]) * expm[index];
			h[i] = hss[index] - (hss[index] - h[i]) * exph[index];
			j[i] = jss[index] - (jss[index] - j[i]) * expj[index];
			d[i] = dss[index] - (dss[index] - d[i]) * expd[index];
			f[i] = fss[index] - (fss[index] - f[i]) * expf[index];
			b[i] = bss[index] - (bss[index] - b[i]) * expb[index];
			g[i] = gss[index] - (gss[index] - g[i]) * expg[index];
			xr[i] = xrss[index] - (xrss[index] - xr[i]) * expxr[index];
			xs1[i] = xs1ss[index] - (xs1ss[index] - xs1[i]) * expxs1[index];
			xs2[i] = xs1ss[index] - (xs1ss[index] - xs2[i]) * expxs2[index];
			
			if (t > tstim[i])
				stimsen[i] = false;
		}
		t += udt;
	}
}

public void actionPerformed(ActionEvent paramActionEvent)
{
	gna = Double.valueOf(input2.getText()).doubleValue();
	gnab = Double.valueOf(input3.getText()).doubleValue();
	gcat = Double.valueOf(input4.getText()).doubleValue();
	
	istim = Double.valueOf(sstrengtht.getText()).doubleValue();
	
	if (paramActionEvent.getSource() == bstimulus)
	{
		double lstim = Double.valueOf(slengtht.getText()).doubleValue();
		double xstim = Double.valueOf(spositiont.getText()).doubleValue();
		t = 0.0D;
		int d1 = (int)(xstim / dx);
		double d2 = lstim / dx / 2.0D;
		int nxs1i = (int)(d1 - d2);
		int nxs1f = (int)(d1 + d2);
		if (nxs1i < 0)
			nxs1i = 0;
		if (nxs1f > nx)
			nxs1f = nx;
		for (int i = nxs1i; i < nxs1f; i++)
		{
			tstim[i] = Double.valueOf(sdurationt.getText()).doubleValue();
			stimsen[i] = true;
		}
	}
	
	if (paramActionEvent.getSource().getLabel() == "Reset")
	{
		gna = 16.0D;
		gnab = 0.004D;
		gcat = 0.05D;
		t = 0.0D;
		reset();
	}
}

public void reset()
{
	for (int i = 0; i < 800; i++)
	{
		tstim[i] = 0.0D;
		irelcicr[i] = 0.0D;
		
		v[i] = -88.654973D;
		dvdt[i] = 0.0D;
		
		nai[i] = 12.236437D;
		ki[i] = 136.89149D;
		ca[i] = 7.9E-5D;
		
		m[i] = 8.38E-4D;
		h[i] = 0.993336D;
		j[i] = 0.995484D;
		d[i] = 3.0E-6D;
		f[i] = 0.999745D;
		xs1[i] = 0.004503D;
		xs2[i] = 0.004503D;
		xr[i] = 1.29E-4D;
		b[i] = 9.94E-4D;
		g[i] = 0.994041D;
		
		tcicr[i] = 1000.0D;
		jsr[i] = 1.179991D;
		nsr[i] = 1.179991D;
		flag[i] = false;
		dt[i] = udt;
		utsc[i] = 50;
		dcaiont[i] = 0.0D;
		caiont[i] = 0.0D;
		xlap[i] = 0.0D;
		stimsen[i] = false;
	}
}