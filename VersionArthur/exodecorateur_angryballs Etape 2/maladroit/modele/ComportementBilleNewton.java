package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.mecanique.MecaniquePoint;

public class ComportementBilleNewton extends DecorateurBille
{

	public ComportementBilleNewton(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);
		BilleConcrete.mouvementUniformementAccelere( this.getPosition(), this.getVitesse(), this.getAcceleration(), deltaT);
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
		this.getAcceleration().ajoute(OutilsBille.gestionAccelerationNewton(this, billes));     // contribution de l'acc�l�ration due � l'attraction des autres billes
		this.getAcceleration().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse()));      // contribution de l'acc�l�ration due au frottement dans l'air
	}

}
