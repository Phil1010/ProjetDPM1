package modele.torche;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import mesmaths.geometrie.base.Vecteur;

/**
 * Créateur de belles flammes.
 * 
 * ouvre un client TCP-IP vers le serveur de flammes C++.
 * Les messages circulent au format binaire pour optimiser le temps d'exécution
 * 
 * Les calculs sont faits du côté de C++
 * 
 * Les paramètres de la flamme : couleurs, vitesse d'écoulement des étincelles, forme du contour de la flamme, dispersion des étincelles 
 * sont définis côté C++
 * 
 * */
public class CreateurBellesFlammes implements CreateurFlammes
{

DataInputStream fluxEntrant;    /* flux de réception des données du socket */
DataOutputStream fluxSortant;   /* flux d'envoi des données sur le socket */

/**
 * @param mRangees : nombre de courbes offset de la flamme = 1 + mRangees  
 * @param mEtincelles : nombres d'étincelles par courbe offset = 1 + mEtincelles
 * 
 * on initialise un créateur de  nuages de (mRangees+1)*(mEtincelles+1) étincelles
 * ATTENTION : On doit avoir mRangees+1 <= L_LIGNES (défini côté serveur de flammes C++)
 * ATTENTION : On doit avoir mEtincelles+1 <= L_COLONNES (défini côté serveur de flammes C++)
 * 
 * mRangées définit la densité d'étincelles dans la direction de la largeur de la flamme
 * mEtincelles définit la densité d'étincelles dans la direction définie par l'échine
 * lance une IOException si la connexion échoue
 * */
public CreateurBellesFlammes(int mRangees, int mEtincelles) throws IOException
{
/*
 * créer un client vers le serveur de flammes
 * ouvrir les flux d'écriture et de lecture en mode binaire
 * */


//------------- paramètres du serveur de flammes : adresse et port d'écoute : à changer si besoin est ---------------
//------------- côté C++, on peut paramétrer adresse et port --------------------------------
//------------ si on veut définir ces paramètres au début de main(), il faut définir une abstract factory de CreateurFlammes --------------

InetAddress adresseServeur = InetAddress.getByName("127.0.0.1");
int portServeur = 3718;

//---------------- ouverture du socket vers le serveur, c-à-d connexion au serveur ----------------------


Socket socket = new Socket(adresseServeur, portServeur);

//------------- ouverture des flux au format binaire pour l'envoi et la réception de données avec le serveur -----------------

this.fluxEntrant = new DataInputStream(socket.getInputStream());
this.fluxSortant = new DataOutputStream(socket.getOutputStream());

//---------------------- on envoie au serveur les paramètres de la flamme --------------------

this.fluxSortant.writeInt(mRangees);
this.fluxSortant.writeInt(mEtincelles);
//throw new RuntimeException("CreateurBellesFlammes() : pas encore implémenté");
}

/**
 * cf. interface CreateurFlammes
 * 
 * envoie une requête au serveur de flamme puis réceptionne la répinse du serveur.
 * la requête envoyée est constituée de positionCentreBille, rayonBille et echine 
 * la réponse est le nuage d'étincelles : etincelles contient les positions des étincelles et couleursEtincelles les couleurs
 * 
 * */
@Override
public boolean creeFlamme( final Vecteur positionCentreBille, final double rayonBille, final Echine echine,
        Vecteur[] etincelles,
        int[] couleursEtincelles) 
{
try
    {
    // TODO Auto-generated method stub
    
    /*
     * envoyer dans l'ordre sur le flux d'écriture :
     * position
     * rayon
     * mEchine
     * echine.S0
     * echine.S1
     * ... 
     * echine.SmEchine
     * mRangees
     * mEtincelles
     * 
     * recevoir 
     * etincelles[0]
     * etincelles[1]
     * etincelles[2]
     * ...
     * etincelles[mNuage]
     * couleursEtincelles[0]
     * couleursEtincelles[1]
     * couleursEtincelles[2]
     * ...
     * couleursEtincelles[mNuage]
     * */
    VecteurStream.writeVecteur(fluxSortant, positionCentreBille);
    fluxSortant.writeDouble(rayonBille);
    int mEchine = echine.length()-1;
    fluxSortant.writeInt(mEchine);
    VecteurStream.writeVecteurs(fluxSortant, echine.sommets);
   
    
    //------------------ la requête a été envoyée -------------------------------
    
                                                                    //System.err.println("requête de flamme envoyée");
                                                                    
    //------------------- on réceptionne la réponse du serveur ------------------------------
    
   // int mLignes, mColonnes;
    //int mNuage = (mLignes+1)*(mColonnes+1) - 1;
    
    VecteurStream.readVecteurs(fluxEntrant, etincelles);
    
    int i;
    for ( i = 0; i < etincelles.length; ++i) couleursEtincelles[i] = fluxEntrant.readInt();
    
    return true;
    }
catch (IOException e)
    {
    return false;
    }

}

}
