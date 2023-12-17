package modele;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

/**
 * C'est une bille qui grossit à chaque collision
 */
public class ComportementBilleGrossit extends DecorateurBille implements Observer
{
	double pourcentage = 0.01;
	
	public ComportementBilleGrossit(Bille bille, double pourcentage)
	{
		super(bille);
		this.pourcentage = pourcentage;
		OutilsBille.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		List<Bille> l = (List<Bille>) arg;
		
		for(Bille b : l)
		{
			if(this.getClef() == b.getClef())
			{
				System.out.println("ind : " + b.getClef());
				bille.setRayon((1 + pourcentage) * bille.getRayon());
			}
		}
		System.out.println("\n");
		
	}
}
