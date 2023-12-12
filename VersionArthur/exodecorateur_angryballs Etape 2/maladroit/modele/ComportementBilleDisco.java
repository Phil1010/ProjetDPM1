package modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import vues.Billard;

public class ComportementBilleDisco extends DecorateurBille {
	private Billard billard;

	public ComportementBilleDisco(Bille bille, Billard billard) {
		super(bille);
		this.billard = billard;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		List<Color> couleurs = new ArrayList(){  };
		couleurs.add(Color.BLACK);
		couleurs.add(Color.BLUE);
		couleurs.add(Color.CYAN);
		couleurs.add(Color.DARK_GRAY);
		
		
		
		
		if (OutilsBille.gestionCollisionBilleBille(bille, billes)) {
			this.getBille().couleur = couleurs.get(new Random().nextInt(couleurs.size())).getRGB();
			System.out.println("aaa");
		}
		
		
		return bille.gestionCollisionBilleBille(billes);
	}
}
