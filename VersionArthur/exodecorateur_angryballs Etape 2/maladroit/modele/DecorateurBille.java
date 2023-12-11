package modele;

import java.util.Vector;

public abstract class DecorateurBille extends Bille {
    protected Bille bille; // La bille qu'ont veut decorer

    public DecorateurBille(Bille bille) {
	this.bille = bille;
	if (bille != null) {
	    this.position = bille.getPosition();
	    this.rayon = bille.getRayon();
	    this.vitesse = bille.getVitesse();
	    this.acceleration = bille.getAcceleration();
	    this.couleur = bille.couleur;
	    this.clef = bille.clef;

	}

    }

    @Override
    public abstract void deplacer(double deltaT);

    @Override
    public abstract void gestionAcceleration(Vector<Bille> billes);

    @Override
    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    @Override
    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur);

    @Override
    public Bille getBille() {
	return this.bille.getBille();
    }
}
