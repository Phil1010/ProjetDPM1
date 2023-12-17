package etat;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import vues.Billard;
import vues.VueBillard;

/**
 * Etat quand on clique sur une bille
 */
public class EtatPiloteeCliquee extends EtatPilotee {

	public EtatPiloteeCliquee(VueBillard billard, Bille bille) {
		super(billard, bille);
	}

	public void request() {
		System.out.println("Cliqué");
	}

	@Override
	public void deplacer(double deltaT)
	{
		int x = this.getBillard().getPositionMouseX();
		int y = this.getBillard().getPositionMouseX();

		Vecteur souris = new Vecteur(x, y);
		
		Vecteur accelerationSouris = souris.difference(this.getBille().getPosition());
		
		accelerationSouris.multiplie(1.0 / (this.getBille().masse() * 100.0));

		this.getBille().getAcceleration().ajoute(accelerationSouris);
		System.out.println(this.getBille().getAcceleration().toString());

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
