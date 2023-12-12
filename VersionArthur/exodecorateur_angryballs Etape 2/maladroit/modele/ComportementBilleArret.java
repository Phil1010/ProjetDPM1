package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;

public class ComportementBilleArret extends DecorateurBille
{

	public ComportementBilleArret(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
		Collisions.collisionBilleContourAvecArretHorizontal(this.getPosition(), this.getRayon(), this.getVitesse(), abscisseCoinHautGauche, largeur);
		Collisions.collisionBilleContourAvecArretVertical(this.getPosition(), this.getRayon(), this.getVitesse(), ordonneeCoinHautGauche, hauteur);
	}

}
