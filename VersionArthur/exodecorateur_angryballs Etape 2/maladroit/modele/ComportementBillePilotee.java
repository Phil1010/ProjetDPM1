package modele;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import vues.Billard;

/**
 * Ce comportement s'applique quand on peut tenir une bille avec la souris
 */
public class ComportementBillePilotee extends DecorateurBille implements MouseListener {
//    private ControllerEtat ct;

    private Etat etat;
    private Billard billard;

    public ComportementBillePilotee(Bille bille, Billard billard, double delta) {
	super(bille);

	this.billard = billard;
	billard.addMouseListener(this);

	this.etat = new Relache(this.billard, this.bille);
	System.out.print("La bille est dans l'état : ");
	etat.request();
    }

    @Override
    /**
     * On deplace la bille suivant la souris si on est dans l'état "Cliqué" On
     * deplace la bille suivant ses comportement si on est dans l'état "Relaché"
     */
    public void deplacer(double deltaT) {
	etat.deplacer(deltaT);
//	System.out.println("Position Bille==== : " + bille.position);
//	System.out.println("Vitesse Bille : " + bille.vitesse);
//	System.out.println("Acceleration Bille : " + bille.acceleration);
    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
	etat.gestionAcceleration(billes);
    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
	return etat.gestionCollisionBilleBille(billes);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur) {
	etat.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }

    @Override
    public void dessine(Graphics g) {
	bille.dessine(g);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	this.etat = new Relache(this.billard, this.bille);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	Point click = this.billard.getMousePosition();
	Vecteur positionBille = this.bille.getPosition();

	if (click.distance(positionBille.x, positionBille.y) <= this.bille.getRayon()) {
	    this.etat = new Clique(this.billard, this.bille);

	}

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
	this.etat = new Relache(this.billard, this.bille);
    }
}
