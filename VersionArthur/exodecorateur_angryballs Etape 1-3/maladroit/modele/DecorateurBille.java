package modele;

import java.awt.Color;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

public abstract class DecorateurBille extends Bille
{
	protected Bille bille;	// La bille qu'ont veut decorer
	
	public DecorateurBille(Bille bille)
	{
		this.bille = bille;
	}
	
	public Bille getBille()
	{
		return bille;
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
	
	/** GETTERS */
	
	@Override
	public Vecteur getPosition() { return bille.getPosition(); }
	
	@Override
	public double getRayon() { return bille.getRayon(); }

	@Override
	public Vecteur getVitesse() { return bille.getVitesse(); }

	@Override
	public Vecteur getAcceleration() { return bille.getAcceleration(); }

	@Override
	public int getClef() { return bille.getClef(); }

	@Override
	public double masse() { return bille.masse(); }
}
