package modele.torche;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

//import modele.Dessinateur;
import modele.Bille;
import mesmaths.geometrie.base.Vecteur;

/**
 * représente la colonne vertébrale (ou l'échine) de la queue de la bille-torche
 * 
 * Le centre de la bille n'est pas représenté.
 * 
 * sommets[0] est le sommet de la queue le plus proche du centre de la bille, sommets[length -1] le plus éloigné
 * 
 * Les distances entres sommets ne varient pas au cours du temps.
 * Alors, pour économiser du temps de calcul (calcul de racine carrée surtout), on stocke toutes les distances dans
 * le tableau longueursVertebres.
 * Notons sommets = {S0, S1, ..., Sm}
 * Notons longueursVertebres = {V0, V1, ..., Vm} alors Vi = |Si - Si-1| pour i = 0 ... m avec V0 = distance centre bille à S0
 * */
public class Echine
{
/**
 * L'échine est dessinée en noir
 * */
public static final Color COULEUR_ECHINE = Color.BLACK;  
public Vecteur sommets[];
double longueursVertebres[];

public int length() { return sommets.length; }

public Vecteur[] getSommets()
{
return this.sommets;
}

/**
 *
 * l'échine est créée rectiligne 
 * 
 * @param positionCentreBille : position du centre de la bille
 * @param d0 : d0 est la distance entre positionCentreBille et S0
 * @param u : u est le vectur unitaire définissant la direction et le sens de la translation d'un sommet au suivant.
 * @param coeffRaison : les longueurs des vertèbres sont en progression géométrique. coeffRaison est la raison de cette suite.
 * @param : nombreSommets : nombre de sommets de l'échine (centre de la bille exclu)
 * */
public Echine( final Vecteur positionCentreBille, final double d0, final Vecteur u,  final double coeffRaison, final int nombreSommets)
{
this.longueursVertebres = new double[nombreSommets];
this.sommets = new Vecteur[nombreSommets];

Vecteur position;
double d;
int i;

for ( i = 0, d = d0, position = positionCentreBille.somme(u.produit(d0)); i < sommets.length; ++i, position.ajoute(u.produit(d*=coeffRaison)))
    {
    this.longueursVertebres[i] = d;
    this.sommets[i] = position.copie();    
    }

}


/**
 * constructeur de copie, à partir d'un tableau de sommets et d'un tableau de longueurs
 * Il y a redondance d'information parce qu'on veut éviter de recalculer des distances (le calcul d'une distance est coûteux en temps d'exécution à cause
 * des calculs de racine carrée)
 * */
public Echine(final Vecteur[] sommets, final double[] longueursVertebres)
{
this.sommets = new Vecteur[sommets.length];
this.longueursVertebres = new double[sommets.length];

int i;
for ( i = 0; i < sommets.length; ++i)
    {
    this.sommets[i] = sommets[i].copie();
    this.longueursVertebres[i] = longueursVertebres[i];    
    }
}

//--------------------------------------------------------------------------------------------

/**
 * Contexte : dans la situation où l'on procède à une animation, la bille change de position au cours du temps.
 * Par conséquent l'échine de la queue de la bille doit se déplacer aussi. Cette méthode gère le déplacement et la déformation  de l'échine 
 * losque la bille a changé de position. Cette méthode doit être appelée immédiatement après le déplacement de la bille
 * 
 * @param : positionCentreBilleAvant : position précédente du centre de la bille
 * @param : positionCentreBilleAprès : position actuelle du centre de la bille
 * 
 *  met à jour les positions des sommets de l'échine
 * */

public void miseAJour(final Vecteur positionCentreBilleAvant, final Vecteur positionCentreBilleAprès)
{
int i;
Vecteur A0, A1;
for ( i = 0, A0 = positionCentreBilleAvant, A1 =  positionCentreBilleAprès; i< sommets.length ; ++i)
    {
    Vecteur B0 = sommets[i].copie();
    Vecteur B1 = sommets[i] = miseAJour(B0, A0, A1,this.longueursVertebres[i]);
    A0 = B0;
    A1 = B1;
    }
}

/**
 * Tâche : met à jour  le sommet n° i en fct du déplacement du sommet n°i-1
 * 
 * @param B0 : ancienne position du sommet n° i
 * @param A0 : ancienne position du sommet n° i-1
 * @param A1 : nouvelle position du sommet n° i-1
 * @param d : distance |B0 - A0|
 * Notons B1 la nouvelle position du sommet n° i
 * 
 * Le déplacement est réalisé de telle sorte que |B0 - A0| = |B1-A1| (l'échine se déforme mais elle ne s'allonge pas ni se raccourcit)
 * */
public static  Vecteur miseAJour(final Vecteur B0, final Vecteur A0, final Vecteur A1, final double d)
{
Vecteur A1A0 = A0.difference(A1);
Vecteur A1B0 = B0.difference(A1);
Vecteur s = A1A0.somme(A1B0);

double n = s.norme();
//double d = précédent0.difference(p).norme();
s.multiplie(d/n);
return  A1.somme(s);
}

/**
 * dessine l'échine this sous forme de ligne brisée
 * */
public void dessine(Graphics g) 
{ 
int i;
Vecteur p1,p2;
for ( i = 1, p1 = this.sommets[0]; i <this.sommets.length; ++i, p1 = p2)
    {
    p2 = this.sommets[i];
    Bille.dessineSegment(g, p1, p2, COULEUR_ECHINE);
    }
}

@Override
public String toString()
{
return "Echine [sommets=" + Arrays.toString(this.sommets) + "]";
}

public Echine copie()
{
// TODO Auto-generated method stub
return new Echine(this.sommets,this.longueursVertebres);
}

/**/
/*Vecteur [] creePolygone()
{
Vecteur [] contour = new Vecteur2D[];

}*/

}
