package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;

public class ComportementBilleRebondir extends DecorateurBille
{

	public ComportementBilleRebondir(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
		Collisions.collisionBilleContourAvecRebond(this.getPosition(), this.getRayon(), this.getVitesse(), abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
	

}
