package modele;

import vues.Billard;

/**
 * Le controller d'états pour gérer les états
 */
public class ControllerEtat
{
	private Etat etatCourant = null;
	private ComportementBillePilotee comportement;
	
	public double relx, rely;
	
	// Les differents états
	private Clique clique;
	private Relache relache;
	
	public ControllerEtat(ComportementBillePilotee comportement, double delta)
	{
		this.comportement = comportement;
		this.clique = new Clique(this, delta);
		this.relache = new Relache(this);
		this.etatCourant = relache;
	}
	
	public Etat getEtatCourant()
	{
		return this.etatCourant;
	}
	
	public void setEtatCourant(Etat e)
	{
		this.etatCourant = e;
	}
	
	public ComportementBillePilotee getComportement()
	{
		return this.comportement;
	}
	
	public void changerEtat()
	{
		if(etatCourant == clique)
		{
			etatCourant = relache;
		}
		else if(etatCourant == relache)
		{
			etatCourant = clique;
		}
		System.out.print("Etat : ");
		etatCourant.request();
	}
}
