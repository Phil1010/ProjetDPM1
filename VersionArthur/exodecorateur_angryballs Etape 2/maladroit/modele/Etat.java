package modele;

import java.awt.event.MouseEvent;
import java.util.Vector;

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
	
	public abstract void request();
	
	public abstract void mouseDragged(MouseEvent arg0);
	public abstract void mouseMoved(MouseEvent arg0);
    public abstract void mousePressed(MouseEvent arg0);
    public abstract void mouseReleased(MouseEvent arg0);

	protected abstract void deplacer(double deltaT);

	protected abstract void gestionAcceleration(Vector<Bille> billes);

	protected abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

	protected abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche,
			double largeur, double hauteur);
}
