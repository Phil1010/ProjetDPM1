package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import etat.ControllerEtatFlamme;
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
	public ControllerEtatFlamme ct;
	
	/**paramètres par défaut pour créer la bille torche */
	public ComportementBilleFlamme(Bille bille, Torche t, boolean enflammee)
	{
		super(bille);
		this.t = t;
		ct = new ControllerEtatFlamme(bille, this, enflammee);
		System.out.println("Etat : " + ct.getEtatCourant().getClass().getName());
	}
	
	public ComportementBilleFlamme(Bille bille, boolean enflammee)
	{
		this(bille, new Torche(bille), enflammee);
	}
	
	@Override
	public void deplacer(double deltaT) {
		bille.deplacer(deltaT);
		
		Vecteur p = this.getPosition().copie();         // on sauvegarde l'ancienne position du centre de la bille

		t.miseAJour2Echines(p);
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		ct.gestionAcceleration(billes);
	}

	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		return ct.gestionCollisionBilleBille(billes);
	}
	
	@Override
	public Bille gestionCollisionBilleBille2(Vector<Bille> billes) {
		return ct.gestionCollisionBilleBille2(billes);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		ct.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
	
	@Override
	public void visiteurDessine(VisiteurDessin v)
	{
		ct.visiteurDessine(v);
	}
}
