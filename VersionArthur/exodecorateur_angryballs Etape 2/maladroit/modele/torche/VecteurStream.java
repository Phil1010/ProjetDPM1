package modele.torche;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mesmaths.geometrie.base.Vecteur;

/**
 * Cette classe sert à écrire sur un flux ou bien lire sur un flux une information de type Vecteur en 2D. 
 * Un vecteur est défini par un couple de coordonnées (x,y) de type double
 * 
 * Les infomations sont écrites/lues au format binaire et non pas texte et cela dans un but d'optimiser le temps d'exécution.
 * 
 * Cette classe est en particulier utilisée pour l'envoi et la réception de messages sur des sockets dans une application de type client-serveur 
 * 
 * La classe gère des Vecteurs et des tableaux de Vecteurs.
 * Les méthodes de lecture de Vecteurs ou de tableaux de Vecteurs ne font pas de "new" toujours dans le but de gagner de l'efficacité en temps d'exécution. 
 * Les méthodes de lecture supposent donc que les Vecteurs à remplir et que les tableaux de Vecteurs à remplir sont déjà créés.
 * 
 * On suppose que les flux sont déjà ouverts avec succès
 * */
public class VecteurStream
{

/**
 * Ecrit au format binaire sur flux le vecteur v(x,y)
 *  @param flux : modifié en écriture par l'appel
 *  @param v : Vecteur à écrire sur flux
 * */
public static void writeVecteur(DataOutputStream flux, final Vecteur v) throws IOException
{
flux.writeDouble(v.x);
flux.writeDouble(v.y);
}


/**
 * Ecrit au format binaire sur flux le tableau de vecteurs t
 *  @param flux : modifié en écriture par l'appel
 *  @param t : tableau à écrire sur flux
 * */
public static void writeVecteurs(DataOutputStream flux, final Vecteur t[]) throws IOException
{
for (Vecteur v : t) writeVecteur(flux, v);
}

/**
 * Lit au format binaire sur flux le vecteur v (x,y)
 *  @param flux : modifié en lecture par l'appel
 *  @param v : Vecteur lu sur flux, v est le résultat
 *  On suppose que v existe ( c'est-à-dire que v != null)
 * */
public static void readVecteur(DataInputStream flux, Vecteur v) throws IOException
{
v.x = flux.readDouble();
v.y = flux.readDouble();
}

/**
 * 
 * Lit au format binaire sur flux le tableau de vecteurs t 
 *  @param flux : modifié en lecture par l'appel
 *  @param t : le tableau de vecteurs lu sur flux, t est le résultat
 *  On lit autant de Vecteurs que le tableau contient d'éléments.
 *  On suppose que les éléments de t ont déjà été créés ( le new a été fait pour chacun d'entre eux, ils sont tous != null)
 * */
public static void readVecteurs(DataInputStream flux, Vecteur t []) throws IOException
{
for (Vecteur v : t) readVecteur(flux,v);
}

}
