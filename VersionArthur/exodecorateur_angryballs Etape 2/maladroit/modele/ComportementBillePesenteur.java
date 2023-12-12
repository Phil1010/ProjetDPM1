package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

public class ComportementBillePesenteur extends DecorateurBille
{
	public Vecteur pesanteur;

	public ComportementBillePesenteur(Bille bille, Vecteur pesanteur) {
		super(bille);
		this.pesanteur = pesanteur;
	}

	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);
		
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
		this.getAcceleration().ajoute(this.pesanteur);          // contribution du champ de pesanteur (par exemple)		
	}

}
