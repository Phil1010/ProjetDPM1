package etat;

import java.util.Vector;

import modele.*;
import visiteurDessin.VisiteurDessin;

public class BilleEnflammee extends Etat
{
	public BilleEnflammee(ControllerEtatFlamme e)
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
		// On va récupéré la bille frappée et lui faire changé de comportement
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
						if(tmp instanceof ComportementBilleFlamme)
						{
							ComportementBilleFlamme c = (ComportementBilleFlamme)tmp;
							c.ct.changerEtat();
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
	 * Dans le cas où la bille est en feu, on dessine la bille et la torche
	 * @param v
	 */
	protected void visiteurDessine(VisiteurDessin v)
	{
		v.visiteurDessine(this.controller.b);
		v.visiteurDessine(((ControllerEtatFlamme)this.controller).c.t);
	}

	@Override
	protected Bille gestionCollisionBilleBille2(Vector<Bille> billes) {
		return this.controller.b.gestionCollisionBilleBille2(billes);
	}
}
