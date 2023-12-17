package etat;

import java.util.Observable;
import java.util.Vector;

import modele.Bille;
import vues.Billard;
import vues.VueBillard;

public abstract class Etat {
	private VueBillard billard;
	private Bille bille;

	public Etat(VueBillard billard2, Bille bille) {
		this.billard = billard2;
		this.bille = bille;
	}

	public VueBillard getBillard() {
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
