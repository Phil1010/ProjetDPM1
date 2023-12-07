package modele;

import java.awt.event.MouseEvent;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;

/**
 * Etat quand on clique sur une bille
 */
public class Clique extends Etat
{	
	public double delta;
	public Clique(ControllerEtat e, double delta)
	{
		setControllerEtat(e);
		this.delta = delta;
	}
	
	public void request()
	{
		System.out.println("Cliqué");
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("DRAGGED");
		
		Bille bille = this.controller.getComportement().bille;
		System.out.println("DRAGGED ("+arg0.getX()+","+arg0.getY()+")");
		
		this.controller.relx = arg0.getX()-bille.getPosition().x;
		this.controller.rely = arg0.getY()-bille.getPosition().y;
		
//		System.out.println(bille.position);
		
//		bille.acceleration = new Vecteur();	// 1.
		bille.position = new Vecteur(arg0.getX(), arg0.getY());	// 2.
//		bille.deplacer(delta);
//		System.out.println(bille.position);
//		
//////		double x = bille.position.x;
//////		double y = bille.position.y;
//////		bille.mouvementUniformementAccelere( bille.getPosition(), bille.getVitesse(), bille.getAcceleration(), deltaT);
//////		bille.position = new Vecteur(x + arg0.getX(), y + arg0.getY());
//		bille.position.ajoute((new Vecteur(arg0.getX(), arg0.getY())));   
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("MOUVED");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("PRESSED");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		controller.changerEtat();
	}

	@Override
	protected void deplacer(double deltaT) {
		// TODO Auto-generated method stub
		Bille bille = this.controller.getComportement().bille;
		
		// Deplacer que quand on drag la souris
//		bille.deplacer(deltaT);
		
//		double x = bille.position.x;
//		double y = bille.position.y;
//		bille.mouvementUniformementAccelere( bille.getPosition(), bille.getVitesse(), bille.getAcceleration(), deltaT);
//		bille.position = new Vecteur(x + arg0.getX(), y + arg0.getY());
//		bille.position.ajoute(bille.getPosition().produit(deltaT));   
//		System.out.println("DEPLACER");
	}

	@Override
	protected void gestionAcceleration(Vector<Bille> billes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		// TODO Auto-generated method stub
		Bille bille = this.controller.getComportement().bille;
		return bille.gestionCollisionBilleBille(billes);
	}

	@Override
	protected void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
			double hauteur) {
		Bille bille = this.controller.getComportement().bille;
		bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
	}
}
