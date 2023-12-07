package modele;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import mesmaths.cinematique.Collisions;
import vues.Billard;
import vues.CadreAngryBalls;

/**
 * Ce comportement s'applique quand on peut tenir une bille avec la souris
 */
public class ComportementBillePilotee extends DecorateurBille implements MouseListener, MouseMotionListener
{
	ControllerEtat ct;
	Billard billard;

	public ComportementBillePilotee(Bille bille, Billard billard, double delta) {
		super(bille);
		this.billard = billard;
		ct = new ControllerEtat(this, delta);
		billard.addMouseListener(this);
		billard.addMouseMotionListener(this);
		System.out.print("La bille est dans l'état : ");
		ct.getEtatCourant().request();
	}
	
	@Override
	/**
	 * On deplace la bille suivant la souris si on est dans l'état "Cliqué"
	 * On deplace la bille suivant ses comportement si on est dans l'état "Relaché"
	 */
	public void deplacer(double deltaT) {
		ct.getEtatCourant().deplacer(deltaT);
		System.out.println("Position Bille==== : " + bille.position);
		System.out.println("Vitesse Bille : " + bille.vitesse);
		System.out.println("Acceleration Bille : " + bille.acceleration);
	}

	@Override
	public void gestionAcceleration(Vector<Bille> billes) {
		ct.getEtatCourant().gestionAcceleration(billes);
	}

	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		return ct.getEtatCourant().gestionCollisionBilleBille(billes);
	}

	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		ct.getEtatCourant().collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}

	@Override
	public void dessine(Graphics g)
	{
		bille.dessine(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) { ct.getEtatCourant().mouseDragged(e); }

	@Override
	public void mouseMoved(MouseEvent e) { ct.getEtatCourant().mouseMoved(e); }
	
	@Override
	public void mouseReleased(MouseEvent e) { ct.getEtatCourant().mouseReleased(e); }

	@Override
	public void mousePressed(MouseEvent e) { ct.getEtatCourant().mousePressed(e); }

	// Rien à faire
	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) {  }

}
