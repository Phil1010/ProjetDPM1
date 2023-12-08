package modele;

import java.util.Vector;

import vues.Billard;

/**
 * Etat quand on relache une bille
 */
public class Relache extends Etat {
    public Relache(Billard billard, Bille bille) {
	super(billard, bille);
    }

    public void request() {
	System.out.println("Relâché");
    }

    @Override
    protected void deplacer(double deltaT) {
//	Bille bille = this.controller.getComportement().bille;
	this.getBille().deplacer(deltaT);
    }

    @Override
    protected void gestionAcceleration(Vector<Bille> billes) {
//	Bille bille = this.controller.getComportement().bille;
	this.getBille().gestionAcceleration(billes);
    }

    @Override
    protected boolean gestionCollisionBilleBille(Vector<Bille> billes) {
	// TODO Auto-generated method stub
//	Bille bille = this.controller.getComportement().bille;
	return this.getBille().gestionCollisionBilleBille(billes);
    }

    @Override
    protected void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur) {
//	Bille bille = this.controller.getComportement().bille;
	this.getBille().collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }
}
