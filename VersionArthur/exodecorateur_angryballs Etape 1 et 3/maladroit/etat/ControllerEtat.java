package etat;

import java.util.Vector;

import modele.Bille;
import visiteurDessin.VisiteurDessin;
import vues.Billard;

/**
 * Le controller d'états pour gérer les états
 * Il est abstract car il peut servir pour bouger les billes avec la souris, passer d'une bille normale à une bille en feu, ...
 */
public abstract class ControllerEtat
{
	private Etat etatCourant = null;
	protected Bille b;
	
	public ControllerEtat(Bille b)
	{
		this.b = b;
	}
	
	public Etat getEtatCourant()
	{
		return this.etatCourant;
	}
	
	public void setEtatCourant(Etat e)
	{
		this.etatCourant = e;
	}
	
	/**
	 * Sert pour passer d'un état à un autre
	 */
	public abstract void changerEtat();
	
	public void deplacer(double deltaT) { etatCourant.deplacer(deltaT);} 

	public void gestionAcceleration(Vector<Bille> billes) { etatCourant.gestionAcceleration(billes); }

	public boolean gestionCollisionBilleBille(Vector<Bille> billes) { return etatCourant.gestionCollisionBilleBille(billes); }

	public Bille gestionCollisionBilleBille2(Vector<Bille> billes) { return etatCourant.gestionCollisionBilleBille2(billes); };
	
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche,
			double largeur, double hauteur) { etatCourant.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur); }
	
	public void visiteurDessine(VisiteurDessin v) { etatCourant.visiteurDessine(v); }
}
