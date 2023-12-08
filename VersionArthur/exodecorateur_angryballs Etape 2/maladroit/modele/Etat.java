package modele;

import java.util.Vector;

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

    protected abstract void deplacer(double deltaT);

    protected abstract void gestionAcceleration(Vector<Bille> billes);

    protected abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    protected abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche,
	    double largeur, double hauteur);
}
