package modele;

import java.awt.*;
import java.util.Vector;

import mesmaths.cinematique.Cinematique;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;


/**
 * Cas g�n�ral d'une bille de billard
 * 
 * Aucune des classes du package maladroit.modele ne doit faire de r�f�rence � des classes de java.awt ni � aucune autre librairie graphique JAVA car 
 * le mod�le NE DOIT PAS d�pendre de la vue !!! Vous devez faire les modifications en cons�quence !! Exploitez les Design Patterns. 
 * 
 * On rappelle que ces r�f�rences � une librairie graphique sont n�fastes car si on change de librairie graphique, voire on fait migrer le projet sur android, 
 * il faut modifier les classes du mod�le. La maintenance devient catastrophique 
 * 
 *  A MODIFIER
 *  
 * 
 * */
public class BilleConcrete extends Bille
{
	/************************************/
	/** Attribus pour une bille normale */
	/************************************/
	
	public  Vecteur position;   // centre de la bille
	public  double rayon;            // rayon > 0
	public  Vecteur vitesse;
	public  Vecteur acceleration;
	public int clef;                // identifiant unique de cette bille

	public Color couleur;          // reference awt : mauvais

	/** GETTERS */
	
	@Override
	public Vecteur getPosition() { return this.position; }
	
	@Override
	public double getRayon() { return this.rayon; }

	@Override
	public Vecteur getVitesse() { return this.vitesse; }

	@Override
	public Vecteur getAcceleration() { return this.acceleration; }

	@Override
	public int getClef() { return this.clef; }

	@Override
	public double masse() { return ro*Geop.volumeSphère(rayon); }
	
	/********************/
	/** AUTRES METHODES */
	/********************/
	
	/**
	 * @brief Meme methode que dans mesmaths.jar mais sans les accents
	 * @param position
	 * @param vitesse
	 * @param acceleration
	 * @param deltaT
	 */
	public static void mouvementUniformementAccelere(Vecteur position, Vecteur vitesse, Vecteur acceleration, double deltaT)
	{
		position.ajoute(vitesse.produit(deltaT));   
		position.ajoute(acceleration.produit(0.5*deltaT*deltaT));
		vitesse.ajoute(acceleration.produit(deltaT));
	}
	
	/**
	 * @brief Constructeur de la bille
	 * @param centre
	 * @param rayon
	 * @param vitesse
	 * @param acceleration
	 * @param couleur
	 */
	protected BilleConcrete(Vecteur centre, double rayon, Vecteur vitesse,
	        Vecteur acceleration, Color couleur)
	{
		this.position = centre;
		this.rayon = rayon;
		this.vitesse = vitesse;
		this.acceleration = acceleration;
		this.couleur = couleur;
		this.clef = Bille.prochaineClef ++;
	}
	
	/**
	 * @brief Constructeur avec acceleration nulle
	 * @param position
	 * @param rayon
	 * @param vitesse
	 * @param couleur
	 */
	public BilleConcrete(Vecteur position, double rayon, Vecteur vitesse, Color couleur)
	{
		this(position,rayon,vitesse,new Vecteur(),couleur);
	}
	
	@Override
	public  void  deplacer( double deltaT)
	{
		// Par defaut, pas de deplacement
		mouvementUniformementAccelere( this.getPosition(), new Vecteur(), new Vecteur(), deltaT);
	}
	
	@Override
	public  void gestionAcceleration(Vector<Bille> billes)
	{
		// Par defaut, pas d'accelerationn
		this.getAcceleration().set(Vecteur.VECTEURNUL);
	}
	
	@Override
	public boolean gestionCollisionBilleBille(Vector<Bille> billes)
	{
		// Par defaut, la bille reste immobile
		return false;
	}
	
	@Override
	public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur, double hauteur)
	{
		// Par defaut, aucunes colisions avec les murs
	}

	@Override
	public void dessine(Graphics g) {
		dessineDisque(g,this.getPosition(),this.getRayon(),this.couleur,Color.CYAN);
	}	
	
	/** � d�placer dans une classe mieux appropri�e */
	public static void dessineDisque(Graphics g, final Vecteur position, final double rayon, final Color couleurInterieur, final Color couleurBord)
	{
		int width, height;
		int xMin, yMin;
	
		xMin = (int)Math.round(position.x-rayon);
		yMin = (int)Math.round(position.y-rayon);
	
		width = height = 2*(int)Math.round(rayon); 
	
		g.setColor(couleurInterieur);
		g.fillOval( xMin, yMin, width, height);
		g.setColor(couleurBord);
		g.drawOval(xMin, yMin, width, height);
	}

	/* cette m�thode engendre des clignotements : id�e : utiliser l'active rendering et le double buffering pour �viter �a */


	public String toString() 
    {
		return "centre = " + position + " rayon = "+rayon +  " vitesse = " + vitesse + " acceleration = " + acceleration + " couleur = " + couleur + "clef = " + clef;
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
}

