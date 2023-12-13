package etat;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import vues.Billard;

/**
 * Etat quand on clique sur une bille
 */
public class EtatPiloteeCliquee extends EtatPilotee {

	public EtatPiloteeCliquee(Billard billard, Bille bille) {
		super(billard, bille);
	}

	public void request() {
		System.out.println("Cliqué");
	}

	@Override
	public void deplacer(double deltaT) {
		Point point = this.getBillard().getMousePosition();

		Vecteur souris = new Vecteur(point.getX(), point.getY());
		

		
		Vecteur accelerationSouris = souris.difference(this.getBille().getPosition());

		// acceleration.multiplie(1 / 2);
		// vitesse.multiplie((this.getBille().masse() / (48 * this.getBille().masse())));
		// this.getBille().getVitesse().set(vitesse);
		// this.getBille().getPosition().set(souris);
		// this.getBille().deplacer(deltaT);
		// this.getBille().getAcceleration().multiplie(deltaT);
		
		accelerationSouris.multiplie(1.0 / (this.getBille().masse() * 100.0));

		this.getBille().getAcceleration().ajoute(accelerationSouris);
		System.out.println(this.getBille().getAcceleration().toString());


		// System.out.println(this.getBille().getAcceleration().toString());
		// this.getBille().getAcceleration().add(vitesse);
		this.getBille().deplacer(deltaT);
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		// TODO Auto-generated method stub
		return this.getBille().gestionCollisionBilleBille(billes);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		this.getBille().collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
}
