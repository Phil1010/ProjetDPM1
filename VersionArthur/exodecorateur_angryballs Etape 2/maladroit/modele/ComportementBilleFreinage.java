package modele;

import java.awt.Graphics;
import java.util.Vector;

import mesmaths.mecanique.MecaniquePoint;

public class ComportementBilleFreinage extends DecorateurBille
{

	public ComportementBilleFreinage(Bille bille) {
		super(bille);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
		this.getAcceleration().ajoute(MecaniquePoint.freinageFrottement(this.masse(), this.getVitesse())); // contribution de l'acc�l�ration due au frottement dans l'air
	}
}
