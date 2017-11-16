package edu.ucsc.cross.hse.model.hybridinverter;

import edu.ucsc.cross.hse.core.object.ObjectSet;

public class InverterState extends ObjectSet
{

	public double p;
	public double q;
	public double iL;
	public double vC;
	public double vIn;

	public InverterState(double p, double q, double iL, double vC, double vIn)
	{
		this.p = p;
		this.q = q;
		this.iL = iL;
		this.vC = vC;
		this.vIn = vIn;
	}
}
