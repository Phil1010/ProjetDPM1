package modele;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import etat.EtatPilotee;
import etat.EtatPiloteeCliquee;
import etat.EtatPiloteeRelachee;
import mesmaths.geometrie.base.Vecteur;
import vues.Billard;
import vues.VueBillard;
import visiteurPilotee.*;

/**
 * Ce comportement s'applique quand on peut tenir une bille avec la souris
 * ComportementBillePilotee utilise un VueBillard et non un Billard qui dépend d'AWT
 */
public class ComportementBillePilotee extends DecorateurBille implements MouseListener {
    private EtatPilotee etat;
    private VueBillard billard;

    public ComportementBillePilotee(Bille bille, VueBillard billard, double delta, VisiteurPilotee v)
    {
		super(bille);
	
		this.billard = billard;
		
		v.addMouseListener(this);		// Ne dépend plus de AWT
	
		this.etat = new EtatPiloteeRelachee(this.billard, this.bille);
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
    public void mouseReleased(MouseEvent e) {
    	this.etat = new EtatPiloteeRelachee(this.billard, this.bille);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
		int x = this.billard.getPositionMouseX();
		int y = this.billard.getPositionMouseY();
		Vecteur positionBille = this.bille.getPosition();
		
		double dist = Math.sqrt((positionBille.x - x)*(positionBille.x - x) + (positionBille.y - y)*(positionBille.y - y));
	
		if (dist <= this.bille.getRayon()) {
		    this.etat = new EtatPiloteeCliquee(this.billard, this.bille);
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
    	this.etat = new EtatPiloteeRelachee(this.billard, this.bille);
    }
}
