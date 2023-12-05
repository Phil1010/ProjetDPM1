package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

/**
 * Comportement Mouvement rectiligne uniforme
 */
public class ComportementBilleRectUniforme extends DecorateurBille
{

	public ComportementBilleRectUniforme(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);
		mouvementUniformementAccelere( this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
	}

	@Override
	// Aucune acceleration
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
		this.getAcceleration().set(Vecteur.VECTEURNUL);
	}

	@Override
	// On veut une gestion des billes
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
//		bille.gestionCollisionBilleBille(billes);
		return OutilsBille.gestionCollisionBilleBille((Bille)bille, billes);
	}

	@Override
	// Collision avec les murs
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);		
	}

	@Override
	public void dessine(Graphics g) {
		bille.dessine(g);
	}
}
