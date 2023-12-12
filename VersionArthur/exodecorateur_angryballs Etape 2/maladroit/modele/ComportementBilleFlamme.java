package modele;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.torche.Torche;
import visiteurDessin.VisiteurDessin;
import vues.Billard;

public class ComportementBilleFlamme extends DecorateurBille implements Observer {
	public Torche t;
	private Billard billard;

	/** paramètres par défaut pour créer la bille torche */
	public ComportementBilleFlamme(Bille bille, Torche t, boolean enflammee, Billard billard) {
		super(bille);
		this.t = t;
		OutilsBille.getInstance().addObserver(this);
		this.billard = billard;
	}

	public ComportementBilleFlamme(Bille bille, boolean enflammee, Billard billard) {
		this(bille, new Torche(bille), enflammee, billard);
	}

	@Override
	public void deplacer(double deltaT) {
		// System.out.println(OutilsBille.getInstance().countObservers());

		bille.deplacer(deltaT);

		Vecteur p = bille.getPosition().copie(); // on sauvegarde l'ancienne position du centre de la bille

		t.miseAJour2Echines(p);
	}

	@Override
	public void visiteurDessine(VisiteurDessin v) {
		bille.visiteurDessine(v);
		t.visiteurDessine(v);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		List<Bille> l = (List<Bille>) arg1;

		Vector<Bille> billes = billard.billes;

		// 1 = la bille sans flamme
		// 0 = bille avec flamme

		if (l.get(0).getClef() == this.getClef() && !(l.get(1) instanceof ComportementBilleFlamme)) {
			for (int i = 0; i < billard.billes.size(); i++) {
				if (l.get(1).getClef() == billard.billes.get(i).getClef()) {
					billard.billes.set(i, new ComportementBilleFlamme(l.get(1), true, billard));
				}
			}
		}

		// else if (l.get(1).getClef() == this.getClef()) {
		// if (!(l.get(0) instanceof ComportementBilleFlamme)){
		// for (int i = 0; i < billard.billes.size(); i++) {
		// if (l.get(0).getClef() == billard.billes.get(i).getClef()) {
		// billard.billes.set(i, new ComportementBilleFlamme(l.get(0), true, billard));
//
//					}
//
//				}
//			}
//		}
	}
}
