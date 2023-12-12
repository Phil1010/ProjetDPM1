package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;

public class ComportementBilleTraverserMur extends DecorateurBille {

    public ComportementBilleTraverserMur(Bille bille) {
	super(bille);
	// TODO Auto-generated constructor stub
    }

   

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur) {

	bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	Collisions.collisionBilleContourPasseMuraille(this.getPosition(), abscisseCoinHautGauche,
		ordonneeCoinHautGauche, largeur, hauteur);
    }

}
