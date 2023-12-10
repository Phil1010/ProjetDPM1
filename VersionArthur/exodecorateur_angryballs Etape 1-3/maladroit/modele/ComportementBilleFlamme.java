package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;
import modele.torche.CreateurBellesFlammes;
import modele.torche.CreateurFlammes;
import modele.torche.CreateurFlammesMock;
import modele.torche.Echine;
import modele.torche.Torche;
import visiteurDessin.VisiteurDessin;

public class ComportementBilleFlamme extends DecorateurBille
{
	public Torche t;
	private VisiteurDessin v;
	
	/**paramètres par défaut pour créer la bille torche */
	public ComportementBilleFlamme(Bille bille, Torche t)
	{
		super(bille);
		this.t = t;
	}
	
	public ComportementBilleFlamme(Bille bille)
	{
		this(bille, new Torche(bille));
	}
	
	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);
		
		Vecteur p = this.getPosition().copie();         // on sauvegarde l'ancienne position du centre de la bille

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

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
	
	@Override
	public void visiteurDessine(VisiteurDessin v)
	{
		super.visiteurDessine(v);
		v.visiteurDessine(t);
	}
}
