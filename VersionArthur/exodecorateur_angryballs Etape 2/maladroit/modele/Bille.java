package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

/**
 * Sert pour appliquer le Design Pattern Decorateur
 */
public abstract class Bille {
    /************************************/
    /** Attribus pour une bille normale */
    /************************************/

    public int test = 0;
    public Vecteur position; // centre de la bille
    public double rayon; // rayon > 0
    public Vecteur vitesse;
    public Vecteur acceleration;
    public int clef; // identifiant unique de cette bille

    public Color couleur; // reference awt : mauvais

    public static int prochaineClef = 0;

    public static double ro = 1; // masse volumique

    /** GETTERS */

    /**
     * @return the position
     */
    public Vecteur getPosition() {
	return this.position;
    }

    /**
     * @return the rayon
     */
    public double getRayon() {
	return this.rayon;
    }

    /**
     * @return the vitesse
     */
    public Vecteur getVitesse() {
	return this.vitesse;
    }

    /**
     * @return the acc�l�ration
     */
    public Vecteur getAcceleration() {
	return this.acceleration;
    }

    /**
     * @return the clef
     */
    public int getClef() {
	return this.clef;
    }

    /**
     * 
     * @return La masse de la bille
     */
    public double masse() {
	return ro * Geop.volumeSphère(rayon);
    }

    public void setCouleur(Color couleur) {
	this.couleur = couleur;
    }

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
    public static void mouvementUniformementAccelere(Vecteur position, Vecteur vitesse, Vecteur acceleration,
	    double deltaT) {
	position.ajoute(vitesse.produit(deltaT));
	position.ajoute(acceleration.produit(0.5 * deltaT * deltaT));
	vitesse.ajoute(acceleration.produit(deltaT));
    }

    /**
     * mise � jour de position et vitesse � t+deltaT � partir de position et vitesse
     * � l'instant t
     * 
     * modifie le vecteur position et le vecteur vitesse
     * 
     * laisse le vecteur acc�l�ration intact
     *
     * La bille subit par d�faut un mouvement uniform�ment acc�l�r�
     */
    public abstract void deplacer(double deltaT);

    /**
     * calcul (c-�-d mise � jour) �ventuel du vecteur acc�l�ration. billes est la
     * liste de toutes les billes en mouvement Cette m�thode peut avoir besoin de
     * "billes" si this subit l'attraction gravitationnelle des autres billes La
     * nature du calcul du vecteur acc�l�ration de la bille est d�finie dans les
     * classes d�riv�es A ce niveau le vecteur acc�l�ration est mis � z�ro (c'est �
     * dire pas d'acc�l�ration)
     */
    public abstract void gestionAcceleration(Vector<Bille> billes);

    /**
     * gestion de l'�ventuelle collision de cette bille avec les autres billes
     *
     * billes est la liste de toutes les billes en mouvement
     * 
     * Le comportement par d�faut est le choc parfaitement �lastique (c-�-d rebond
     * sans amortissement)
     * 
     * @return true si il y a collision et dans ce cas les positions et vecteurs
     *         vitesses des 2 billes impliqu�es dans le choc sont modifi�es si
     *         renvoie false, il n'y a pas de collision et les billes sont laiss�es
     *         intactes
     */
    public abstract boolean gestionCollisionBilleBille(Vector<Bille> billes);

    /**
     * gestion de l'�ventuelle collision de la bille (this) avec le contour
     * rectangulaire de l'�cran d�fini par (abscisseCoinHautGauche,
     * ordonn�eCoinHautGauche, largeur, hauteur)
     * 
     * d�tecte si il y a collision et le cas �ch�ant met � jour position et vitesse
     * 
     * La nature du comportement de la bille en r�ponse � cette collision est
     * d�finie dans les classes d�riv�es
     */
    public abstract void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
	    double hauteur);

    /** � d�placer dans une classe mieux appropri�e */
    public static void dessineDisque(Graphics g, final Vecteur position, final double rayon,
	    final Color couleurInterieur, final Color couleurBord) {
	int width, height;
	int xMin, yMin;

	xMin = (int) Math.round(position.x - rayon);
	yMin = (int) Math.round(position.y - rayon);

	width = height = 2 * (int) Math.round(rayon);

	g.setColor(couleurInterieur);
	g.fillOval(xMin, yMin, width, height);
	g.setColor(couleurBord);
	g.drawOval(xMin, yMin, width, height);
    }

    /*
     * cette m�thode engendre des clignotements : id�e : utiliser l'active rendering
     * et le double buffering pour �viter �a
     */

    public abstract void dessine(Graphics g); // r�f�rence awt : mauvais

    public String toString() {
	return "centre = " + position + " rayon = " + rayon + " vitesse = " + vitesse + " acceleration = "
		+ acceleration + " couleur = " + couleur + "clef = " + clef;
    }

    /**
     * dessine le segment [p1 p2] avec la couleur indiqu�e
     * 
     * � d�placer dans une classe mieux appropri�e
     */
    public static void dessineSegment(Graphics g, Vecteur p1, Vecteur p2, Color couleur) {
	int x1 = (int) Math.round(p1.x);
	int y1 = (int) Math.round(p1.y);

	int x2 = (int) Math.round(p2.x);
	int y2 = (int) Math.round(p2.y);

	Color ancienneCouleur = g.getColor();
	g.setColor(couleur);
	g.drawLine(x1, y1, x2, y2);
	g.setColor(ancienneCouleur);
    }

    public abstract Bille getBille();

}
