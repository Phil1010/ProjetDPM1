package modele;

import java.util.Vector;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

/**
 * 
 * 
 * Op�rations utiles sur les billes
 * 
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 *  */

public class OutilsBille
{
/**
 * @param billes est la liste de TOUTES les billes en mouvement
 * @param cetteBille est l'une d'entre elles.
 * @return la liste des autres billes que cetteBille, c'est-�-dire la liste de toutes les billes sauf cetteBille 
 * 
 * */
public static Vector<Bille> autresBilles(Bille cetteBille, Vector<Bille> billes)
{
Vector<Bille> autresBilles = new Vector<Bille>();

Bille billeCourante;

int i;

for( i = 0; i < billes.size(); ++i)
   {
   billeCourante = billes.get(i);
   if ( billeCourante.getClef() != cetteBille.getClef())
     autresBilles.add(billeCourante);
   }

return autresBilles;
}


/**
 * @param cetteBille : une bille particuli�re
 * @param billes : une liste de billes, cette liste peut contenir cettebille
 *
 * gestion de l'�ventuelle  collision de cette bille avec les autres billes
 *
 * billes est la liste de toutes les billes en mouvement
 * 
 * Le comportement par d�faut est le choc parfaitement �lastique (c-�-d rebond sans amortissement)
 * 
 * @return true si il y a collision et dans ce cas les positions et vecteurs vitesses des 2 billes impliqu�es dans le choc sont modifi�es
 * si renvoie false, il n'y a pas de collision et les billes sont laiss�es intactes 
 * */
public static  boolean gestionCollisionBilleBille(Bille cetteBille, Vector<Bille> billes)
{
//--- on r�cup�re d'abord dans autresBilles toutes les billes sauf cetteBille ----

Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);

//--- on cherche � pr�sent la 1�re des autres billes avec laquelle cetteBille est en collision ---------------------
//-------------- on suppose qu'il ne peut y avoir de collision qui implique plus de deux billes � la fois ---------------

Bille billeCourante;

int i;

for ( i = 0 ; i < autresBilles.size(); ++i)
    {
    billeCourante = autresBilles.get(i);
    if (Collisions.CollisionBilleBille(    cetteBille.getPosition(),    cetteBille.getRayon(),    cetteBille.getVitesse(),    cetteBille.masse(), 
                                        billeCourante.getPosition(), billeCourante.getRayon(), billeCourante.getVitesse(), billeCourante.masse()))
       return true; 
    }
return false;
}

/**
 * Retourne une bille au lieu dde vrai ou faux
 * @param cetteBille
 * @param billes
 * @return
 */
public static  Bille gestionCollisionBilleBille2(Bille cetteBille, Vector<Bille> billes)
{
//--- on r�cup�re d'abord dans autresBilles toutes les billes sauf cetteBille ----

Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);

//--- on cherche � pr�sent la 1�re des autres billes avec laquelle cetteBille est en collision ---------------------
//-------------- on suppose qu'il ne peut y avoir de collision qui implique plus de deux billes � la fois ---------------

Bille billeCourante;

int i;

for ( i = 0 ; i < autresBilles.size(); ++i)
    {
    billeCourante = autresBilles.get(i);
    if (Collisions.CollisionBilleBille(    cetteBille.getPosition(),    cetteBille.getRayon(),    cetteBille.getVitesse(),    cetteBille.masse(), 
                                        billeCourante.getPosition(), billeCourante.getRayon(), billeCourante.getVitesse(), billeCourante.masse()))
    	System.out.println("C'EST VRAI");
    	return billeCourante; 
    }
return null;
}


/**
 * @param cetteBille : une bille particuli�re
 * @param billes : une liste de billes, cette liste peut contenir cettebille
 * 
 * On suppose que cetteBille subit l'attraction gravitationnelle due aux billes contenues dans "billes" autres que cetteBille.
 * 
 * t�che : calcule a, le vecteur acc�l�ration subi par cetteBille r�sultant de l'attraction par les autres billes de la liste.
 * 
 * @return a : le vecteur acc�l�ration r�sultant
 * 
 * */
public static Vecteur gestionAccelerationNewton(Bille cetteBille, Vector<Bille> billes)
{

//--- on r�cup�re d'abord dans autresBilles toutes les billes sauf celle-ci ----

Vector<Bille> autresBilles = OutilsBille.autresBilles(cetteBille, billes);

//-------------- � pr�sent on r�cup�re les masses et les positions des autres billes ------------------
int i;
Bille billeCourante;

int d = autresBilles.size();

double masses [] = new double[d];   // les masses des autres billes
Vecteur C [] = new Vecteur[d];      // les positions des autres billes

for ( i = 0; i < d; ++i)
    {
    billeCourante = autresBilles.get(i);
    masses[i] = billeCourante.masse();
    C[i] = billeCourante.getPosition();
    }

//------------------ � pr�sent on calcule le champ de gravit� exerc� par les autres billes sur cette bille ------------------

return  MecaniquePoint.champGravitéGlobal( cetteBille.getPosition(),  masses, C);
}
}
