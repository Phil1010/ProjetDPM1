package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

/**
 * Comportement Mouvement rectiligne uniforme
 */
public class ComportementBilleRectUniforme extends DecorateurBille
{

	public ComportementBilleRectUniforme(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);

		BilleConcrete.mouvementUniformementAccelere( this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
	}

	@Override
	// Aucune acceleration
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
		this.getAcceleration().set(Vecteur.VECTEURNUL);
	}


}
