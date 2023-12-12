package modele;

import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import visiteurDessin.VisiteurDessin;

/**
 * Sert pour appliquer le Design Pattern Decorateur
 */
public abstract class Bille {
    /**********************/
    /* CONSTANTES STATICS */
    /**********************/

    public static int prochaineClef = 0;
    public static double ro = 1; // masse volumique

    /** GETTERS */

    /**
     * @return the position
     */
    public abstract Vecteur getPosition();

    /**
     * @return the rayon
     */
    public abstract double getRayon();

    /**
     * @return the vitesse
     */
    public abstract Vecteur getVitesse();

    /**
     * @return the acc�l�ration
     */
    public abstract Vecteur getAcceleration();

    /**
     * @return the clef
     */
    public abstract int getClef();

    /**
     *
     * @return La masse de la bille
     */
    public abstract double masse();

    /**
     *
     * @return La couleur de la bille
     */
    public abstract int getColor();

    /********************/
    /** AUTRES METHODES */
    /********************/

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
     * Pareil que gestionCollisionBilleBille mais retourne une bille
     *
     * @param billes
     * @return
     */
    public abstract Bille gestionCollisionBilleBille2(Vector<Bille> billes);

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

    public abstract void visiteurDessine(VisiteurDessin v);
}
