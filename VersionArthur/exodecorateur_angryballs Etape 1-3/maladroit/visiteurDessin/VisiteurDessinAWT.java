package visiteurDessin;

import java.awt.Color;
import java.awt.Graphics;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import modele.BilleConcrete;
import modele.ComportementBilleFlamme;
import modele.torche.Echine;
import modele.torche.Torche;

public class VisiteurDessinAWT extends VisiteurDessin
{
	private Graphics g;
	
	public VisiteurDessinAWT(Graphics g)
	{
		this.g = g;
	}
	
	@Override
	public void visiteurDessine(Bille b)
	{
		dessineDisque(g, b.getPosition(),b.getRayon(),b.getColor(),Color.CYAN);
		System.out.println(b.getPosition());
	}
	
	@Override
	public void visiteurDessine(Torche t)
	{
		
		/* on dessine la flamme par au dessus  à l'aide de l'échine vitesse */

		/* on remplit d'abord le tableau des positions des étincelles et le tableau des couleurs des étincelles */

		boolean ok = t.createurFlammes.creeFlamme(t.getBille().getPosition(), t.getBille().getRayon(),
		                                             t.echine, t.etincelles,t.couleursEtincelles);


		if (ok)
	    {
		    int i;
		    for (i = 0; i < t.etincelles.length; ++i) /* dessine les étincelles une par une */
	        {
		        int couleur = t.couleursEtincelles[i];
		        Color co = new Color(couleur);
		        dessineDisque(g, t.etincelles[i], t.rayonEtincelle, co.getRGB(), co);
	        } 
	    }
		else            /* échec du remplissage des tableaux de positions et de couleurs alors on dessine l'échine sans la flamme */
	    {
		    dessineSegment(g, t.getBille().getPosition(), t.echine.sommets[0], Echine.COULEUR_ECHINE);
		    int i;
			Vecteur p1,p2;
			for ( i = 1, p1 = t.echine.sommets[0]; i < t.echine.sommets.length; ++i, p1 = p2)
		    {
				p2 = t.echine.sommets[i];
		    	dessineSegment(g, p1, p2, Echine.COULEUR_ECHINE);
		    }
	    }
	}
	
	/**
	 * dessine le segment [p1 p2] avec la couleur indiqu�e
	 * 
	 *  � d�placer dans une classe mieux appropri�e */
	public static void dessineSegment( Graphics g, Vecteur p1, Vecteur p2, Color couleur)
	{
		int x1 = (int)Math.round(p1.x);
		int y1 = (int)Math.round(p1.y);
	
		int x2 = (int)Math.round(p2.x);
		int y2 = (int)Math.round(p2.y);
	
		Color ancienneCouleur = g.getColor();
		g.setColor(couleur);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(ancienneCouleur);
	}
	
	/** � d�placer dans une classe mieux appropri�e */
	public static void dessineDisque(Graphics g, final Vecteur position, final double rayon, final int couleurInterieur, final Color couleurBord)
	{
		int width, height;
		int xMin, yMin;
	
		xMin = (int)Math.round(position.x-rayon);
		yMin = (int)Math.round(position.y-rayon);
	
		width = height = 2*(int)Math.round(rayon); 
	
		g.setColor(new Color(couleurInterieur));
		g.fillOval( xMin, yMin, width, height);
		g.setColor(couleurBord);
		g.drawOval(xMin, yMin, width, height);
	}
}
