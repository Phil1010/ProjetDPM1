package etat;

import java.awt.event.MouseEvent;
import java.util.Vector;

import modele.Bille;
import visiteurDessin.VisiteurDessin;

public abstract class Etat
{
	protected ControllerEtat controller;
	
	public void setControllerEtat(ControllerEtat e)
	{
		this.controller = e;
	}
	
	public void changerControllerEtat()
	{
		controller.changerEtat();
	}

	protected abstract void deplacer(double deltaT);

	protected abstract void gestionAcceleration(Vector<Bille> billes);

	protected abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);
	
	protected abstract Bille gestionCollisionBilleBille2(Vector<Bille> billes);

	protected abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche,
			double largeur, double hauteur);
	
	protected abstract void visiteurDessine(VisiteurDessin v);
}
