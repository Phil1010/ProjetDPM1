package etat;

import java.util.Vector;

import modele.Bille;
import vues.Billard;

/**
 * Etat quand on relache une bille
 */
public class EtatPiloteeRelachee extends EtatPilotee {
    public EtatPiloteeRelachee(Billard billard, Bille bille) {
	super(billard, bille);
    }

    public void request() {
	System.out.println("Relâché");
    }

    @Override
    public void deplacer(double deltaT) {
//	Bille bille = this.controller.getComportement().bille;
	this.getBille().deplacer(deltaT);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
//	Bille bille = this.controller.getComportement().bille;
	this.getBille().gestionAcceleration(billes);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
	// TODO Auto-generated method stub
//	Bille bille = this.controller.getComportement().bille;
	return this.getBille().gestionCollisionBilleBille(billes);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur) {
//	Bille bille = this.controller.getComportement().bille;
	this.getBille().collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }
}
