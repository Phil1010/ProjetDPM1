package etat;

import java.util.Vector;

import modele.Bille;
import vues.Billard;
import vues.VueBillard;

/**
 * Etat quand on relache une bille
 */
public class EtatPiloteeRelachee extends EtatPilotee {
    public EtatPiloteeRelachee(VueBillard billard, Bille bille) {
    	super(billard, bille);
    }

    public void request() {
    	System.out.println("Relâché");
    }

    @Override
    public void deplacer(double deltaT) {
    	this.getBille().deplacer(deltaT);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
    	this.getBille().gestionAcceleration(billes);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
    	return this.getBille().gestionCollisionBilleBille(billes);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur) {
    	this.getBille().collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }
}
