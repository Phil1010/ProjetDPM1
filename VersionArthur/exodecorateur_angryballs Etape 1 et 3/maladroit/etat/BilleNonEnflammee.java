package etat;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import modele.BilleConcrete;
import modele.ComportementBilleFlamme;
import modele.DecorateurBille;
import modele.OutilsBille;
import visiteurDessin.VisiteurDessin;

/**
 * Représente l'état où une bille n'est pas en feu
 */
public class BilleNonEnflammee extends Etat
{
	
	public BilleNonEnflammee(ControllerEtatFlamme e)
	{
		this.controller = e;
	}

	@Override
	protected void deplacer(double deltaT) {
		// On fait rien
		this.controller.b.deplacer(deltaT);
	}

	@Override
	protected void gestionAcceleration(Vector<Bille> billes) {
		this.controller.b.gestionAcceleration(billes);
	}

	@Override
	protected boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		// On va récupéré la bille frappée et voir si c'est une bille en feu
		Bille b = OutilsBille.gestionCollisionBilleBille2(this.controller.b, billes);
		
		if(b != null)
		{
			// On va parcourir toute la chaine
			if(b instanceof DecorateurBille)
			{
				try
				{
					DecorateurBille tmp = (DecorateurBille)b;
					while(true)
					{
						System.out.println("Type de la bille : " + b.getClass().getName());
						// La bille qu'on a frappé est une bille en feu : 
						// On va vérifié qu'elle est dans l'état enflammée
						if(tmp instanceof ComportementBilleFlamme)
						{
							
							ComportementBilleFlamme c = (ComportementBilleFlamme)tmp;
							if(c.ct.getEtatCourant() instanceof BilleEnflammee)
								this.controller.changerEtat();
							
							return true;
						}				
						tmp = (DecorateurBille) tmp.getBille();
					}
				}
				catch(Exception e)
				{
					// On est arrivé au bout de la chaîne, et la bille ne comporte pas le comportement
					// de la bille en feu : on ne fait rien
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		// On fait rien
		this.controller.b.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
	
	/**
	 * Dans le cas où la bille n'est pas en feu, on dessine juste la bille
	 * @param v
	 */
	protected void visiteurDessine(VisiteurDessin v)
	{
		v.visiteurDessine(this.controller.b);
	}

	@Override
	protected Bille gestionCollisionBilleBille2(Vector<Bille> billes) {
		return this.controller.b.gestionCollisionBilleBille2(billes);
	}

}
