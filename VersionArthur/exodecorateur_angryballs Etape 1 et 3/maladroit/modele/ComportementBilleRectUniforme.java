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
		BilleConcrete.mouvementUniformementAccelere( this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
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
		return bille.gestionCollisionBilleBille(billes);
	}
	
	@Override
	public Bille gestionCollisionBilleBille2(Vector<Bille> billes) {
		return bille.gestionCollisionBilleBille2(billes);
	}
	
	@Override
	// Collision avec les murs
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);		
	}
}
