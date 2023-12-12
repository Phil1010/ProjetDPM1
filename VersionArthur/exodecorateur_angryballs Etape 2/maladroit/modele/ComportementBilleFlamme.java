package modele;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.torche.Torche;
import visiteurDessin.VisiteurDessin;

public class ComportementBilleFlamme extends DecorateurBille {
	public Torche t;

	/** paramètres par défaut pour créer la bille torche */
	public ComportementBilleFlamme(Bille bille, Torche t, boolean enflammee) {
		super(bille);
		this.t = t;
	}

	public ComportementBilleFlamme(Bille bille, boolean enflammee) {
		this(bille, new Torche(bille), enflammee);
	}

	@Override
	public void deplacer(double deltaT) {
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
}
