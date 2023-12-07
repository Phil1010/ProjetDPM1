package modele;

import java.awt.event.MouseEvent;
import java.util.Vector;

import vues.Billard;

/**
 * Etat quand on relache une bille
 */
public class Relache extends Etat
{
	public Relache(ControllerEtat e)
	{
		setControllerEtat(e);
	}
	
	public void request()
	{
		System.out.println("Relâché");
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// Rien a faire
		Bille bille = this.controller.getComportement().bille;
		System.out.println(bille.position);
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Rien a faire
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		Bille bille = this.controller.getComportement().bille;
		Billard billard = this.controller.getComportement().billard;
		
//		System.out.println("("+arg0.getX()+","+arg0.getY()+")");
		// On va vérifier que le point qu'on touche avec la souris est dans notre bille
		for(Bille b : billard.getBilles())
		{
			if(b.clef == bille.clef)
			{	
				// Une bille a un centre et un rayon
				// On peut dire qu'une bille c'est un disque, donc que une bille de centre (x,y) et de rayon r = 
				// {(a,b) / (x-a)^2 + (y-b)^2 <= r^2}
				
				if(Math.pow(arg0.getX() - bille.getPosition().x, 2) 
						+ Math.pow(arg0.getY() - bille.getPosition().y, 2) 
						<= Math.pow(bille.getRayon(), 2))
				{
					System.out.println("On touche la bille numero " + bille.getClef());
					controller.changerEtat();
					return;
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Rien a faire
		
	}

	@Override
	protected void deplacer(double deltaT) {
		Bille bille = this.controller.getComportement().bille;
		bille.deplacer(deltaT);
	}

	@Override
	protected void gestionAcceleration(Vector<Bille> billes) {
		Bille bille = this.controller.getComportement().bille;
		bille.gestionAcceleration(billes);
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
