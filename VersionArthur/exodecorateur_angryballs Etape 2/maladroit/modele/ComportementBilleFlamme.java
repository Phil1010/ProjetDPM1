package modele;

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
	public void gestionAcceleration(Vector<Bille> billes) {
		bille.gestionAcceleration(billes);
	}

	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		return bille.gestionCollisionBilleBille(billes);
	}

	public Bille gestionCollisionBilleBille2(Vector<Bille> billes) {
		return null;// bille.gestionCollisionBilleBille2(billes);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
								 double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}

	@Override
	public void visiteurDessine(VisiteurDessin v) {
		bille.visiteurDessine(v);
		t.visiteurDessine(v);
	}

	@Override
	public void update(Observable arg0, Object obj) {
		Bille bille = (Bille) obj;
		Vector<Bille> billes = this.billard.billes;
		for (int i = 0; i < billes.size(); i++) {
			if (billes.get(i).getClef() == bille.getClef() && !(bille instanceof ComportementBilleFlamme)) {
				billes.set(i, new ComportementBilleFlamme(billes.get(i), true, this.billard));
			}
		}
	}
}
