package etat;

import java.util.Vector;

import modele.Bille;
import vues.Billard;

public abstract class Etat {
    private Billard billard;
    private Bille bille;

    public Etat(Billard billard, Bille bille) {
	this.billard = billard;
	this.bille = bille;
    }

    public Billard getBillard() {
	return billard;
    }

    public Bille getBille() {
	return bille;
    }

    public abstract void request();

    public abstract void deplacer(double deltaT);

    public abstract void gestionAcceleration(Vector<Bille> billes);

    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur);
}
