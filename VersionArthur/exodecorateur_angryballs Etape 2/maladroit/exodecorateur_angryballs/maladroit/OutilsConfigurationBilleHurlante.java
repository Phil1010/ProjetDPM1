package exodecorateur_angryballs.maladroit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import musique.SonLong;
import musique.SonLongFantome;
import musique.javax.SonLongJavax;

/**
 * sert � construire tous les sons qui seront propos�s pour animer la bille hurlante
 * 
 *  ICI : IL N'Y A RIEN A CHANGER
 *  
 * */
public class OutilsConfigurationBilleHurlante
{

/**
 * La seule t�che de cette m�thode est d'ouvrir le fichier de configuration de SonLong de la bile hurlante
 * cf. m�thode chargeSons1 juste apr�s
 * */
public static Vector<SonLong>  chargeSons(File repertoireBruits, String nomFichierConfigAudio) //throws IOException
{
Vector<SonLong> resultat;
try
    {
    File f = new File(repertoireBruits,nomFichierConfigAudio);
    FileInputStream f1 = new FileInputStream(f);
    BufferedReader fichierConfigBilleHurlante = new BufferedReader(new InputStreamReader(f1));
    
    resultat = OutilsConfigurationBilleHurlante.chargeSons1(repertoireBruits, fichierConfigBilleHurlante);
    fichierConfigBilleHurlante.close();
    }

catch (IOException e)
    {
    resultat = new Vector<SonLong>();
    resultat.add(new SonLongFantome());
    System.err.println("sons indisponibles pour les hurlements");
    }
return resultat;
}

/**
 * T�che : construit une liste de SonLong.
 *  
 * @param r�pertoireBruits : construit une liste de SonLong � partir de fichiers audio plac�s sur le dossier d�fini par le chemin r�pertoireBruits
 * @param fichierConfigBilleHurlante : indique comment construire les SonLong.
 * 
 * fichierConfigBilleHurlante respecte le format suivant :
 * sur les 8 premi�res lignes est d�taill� le format du fichier lui-m�me
 * puis chaque ligne suivante permet de construire un SonLong. 
 * 
 * Exemple de fichierConfigBilleHurlante : 
 * 
        configuration des fichiers audios � utiliser pour la bille hurlante. Un fichier audio au format wav par ligne. 
        4 informations sur une ligne : 
        nom du fichier (sans l'extention .wav) d�but de l'extrait (en centi�mes de secondes) fin de l'extrait (en centi�mes de secondes) effectif (nombre de morceaux composant l'extrait)
        s�parateur : espace. exemple :  sabrelaser 0 150 15  
        Important ===> On doit toujours avoir : effectif^2 >= (finExtrait - d�butExtrait) / BilleHurlante.DELAI_MIN
        Important ===> On doit toujours avoir : (finExtrait - d�butExtrait) / effectif >= SonJavax.TAILLE_BUFFER_LIGNE
        Les fichiers audio doivent �tre dans le m�me r�pertoire que ce fichier
        Les 8 premi�res lignes du fichier sont ignor�es
        huey2 1200 1300 10
        spitfire 1100 1700 30
        sabrelaser 0 150 15
        loups 0 600 40
        crapaud 20 120 10
 * 
 * A partir de la 9�me ligne, toute ligne contenant une erreur est ignor�e
 * 
 * Si � partir du fichier, on est incapable de construire au moins un SonLong valide, la m�thode renvoie un Vector contenant un unique SonLongFantome
 * pour que l'application puisse quand m�me tourner. 
 * */
public static Vector<SonLong>  chargeSons1(File repertoireBruits, BufferedReader fichierConfigBilleHurlante)
{
Vector<SonLong> sons = new Vector<SonLong>();

int i;
String ligne = null;

try
    {
    for (i = 0; i < 8; ++i) ligne = fichierConfigBilleHurlante.readLine(); /* on ignore les 8 premi�res lignes du fichier qui contiennent le r�sum� */
    }
catch (IOException e1)  /* l'ent�te du fichier contient un probl�me */
    {
    sons.add(new SonLongFantome());         /* on cr�e un son bidon pour que l'application puisse quand m�me tourner sans la diffusion du son */
    return sons;
    }

/* � pr�sent on est s�r que la lecture des 8 lignes d'ent�te s'est bien pass�e. le pointeur de ligne de fichierConfigBilleHurlante pointe sur la 9�me ligne */

for ( /* rien � faire ici*/; ligne != null; ++i)
    {
    try
      {
      ligne = fichierConfigBilleHurlante.readLine();        /*ligne est suppos�e respecter le format suivant : "spitfire 1100 1700 30" */
      
      if (ligne != null) sons.add(SonLongJavax.crée(repertoireBruits, ligne) );
      }
    catch (Exception e)
      {
      /* on ignore la ligne g�n�rant une erreur et on passe au prochain son sur la ligne suivante */
      System.err.println("Dans OutilsConfigurationBilleHurlante.chargeSons1() : ligne n� " + i + " ignor�e car contenant une erreur : " + e);            
      }
    }       // for

if (sons.isEmpty()) sons.add(new SonLongFantome());
return sons;
}
//SonLongJavax( File r�pertoire, String nomFichier, int d�butExtrait, int finExtrait, int effectif)
}
