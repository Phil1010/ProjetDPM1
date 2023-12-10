package etat;

import modele.Bille;
import modele.ComportementBilleFlamme;
import visiteurDessin.VisiteurDessin;

public class ControllerEtatFlamme extends ControllerEtat
{
	ComportementBilleFlamme c;
	
	// Les états
	public BilleEnflammee be;
	public BilleNonEnflammee bne;
	
	public ControllerEtatFlamme(Bille b, ComportementBilleFlamme c, boolean enflammee)
	{
		super(b);
		this.c = c;
		
		be = new BilleEnflammee(this);
		bne = new BilleNonEnflammee(this);
		if(enflammee)
			this.setEtatCourant(be);
		else
			this.setEtatCourant(bne);
	}

	@Override
	public void changerEtat() 
	{
		if(this.getEtatCourant() == bne)
			this.setEtatCourant(be);
		
		System.out.println("On est dans l'état : " + getEtatCourant().getClass().getName());
	}
	
	public void visiteurDessine(VisiteurDessin v)
	{
		this.getEtatCourant().visiteurDessine(v);
	}

}
