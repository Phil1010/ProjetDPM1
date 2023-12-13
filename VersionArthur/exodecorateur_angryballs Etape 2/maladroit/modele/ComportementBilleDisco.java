package modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import vues.Billard;

class Disco extends TimerTask {
	private ComportementBilleDisco disco;
	public Disco(ComportementBilleDisco disco) {
		this.disco = disco;
	}

	@Override
	public void run() {
		this.disco.getBille().couleur = new Random().nextInt(16777216);
		
	}
	
}

public class ComportementBilleDisco extends DecorateurBille {
	private Billard billard;

	public ComportementBilleDisco(Bille bille, Billard billard) {
		super(bille);
		this.billard = billard;
		new Timer().schedule(new Disco(this), 0, 1000);

		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
	
		
		
		
		if (OutilsBille.gestionCollisionBilleBille(bille, billes)) {
			// this.getBille().couleur = new Random().nextInt(16777216);
			
			System.out.println("aaa");
		}
		
		
		return bille.gestionCollisionBilleBille(billes);
	}
}
