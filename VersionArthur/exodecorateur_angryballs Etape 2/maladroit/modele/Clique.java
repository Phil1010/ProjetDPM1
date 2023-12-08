package modele;

import java.awt.Point;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import vues.Billard;

/**
 * Etat quand on clique sur une bille
 */
public class Clique extends Etat {

    public Clique(Billard billard, Bille bille) {
	super(billard, bille);
    }

    public void request() {
	System.out.println("Cliqué");
    }

    @Override
    protected void deplacer(double deltaT) {
	Point point = this.getBillard().getMousePosition();
	Vecteur souris = new Vecteur(point.getX(), point.getY());
	Vecteur vitesse = souris.difference(this.getBille().position);
	vitesse.multiplie(1.0 / 10.0);
	this.getBille().vitesse.set(vitesse);
	this.getBille().position.set(souris);
	System.out.println(this.getBille().toString());
    }

    @Override
    protected void gestionAcceleration(Vector<Bille> billes) {
	// TODO Auto-generated method stub
    }

    @Override
    protected boolean gestionCollisionBilleBille(Vector<Bille> billes) {
	// TODO Auto-generated method stub
	return this.getBille().gestionCollisionBilleBille(billes);
    }

    @Override
    protected void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur) {
	this.getBille().collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }
}
