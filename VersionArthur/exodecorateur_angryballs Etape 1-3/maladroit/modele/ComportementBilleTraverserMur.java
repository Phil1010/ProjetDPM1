package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.mecanique.MecaniquePoint;

public class ComportementBilleTraverserMur extends DecorateurBille
{

	public ComportementBilleTraverserMur(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);
		
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
	}

	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		return bille.gestionCollisionBilleBille(billes);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
		Collisions.collisionBilleContourPasseMuraille( this.getPosition(), abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
	
	@Override
	public void dessine(Graphics g) {
		bille.dessine(g);
	}
}
