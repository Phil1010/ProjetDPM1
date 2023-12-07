package vues;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import exodecorateur_angryballs.maladroit.OutilsConfigurationBilleHurlante;
import modele.Bille;
import musique.SonBref;
import musique.SonBrefFantome;
import musique.SonLong;
import musique.javax.SonBrefJavax;


public class TestCadreAngryBallsSeul
{

/**
 * @param args
 */
public static void main(String[] args)
{
//---------------------- gestion des bruitages : param�trage du chemin du dossier contenant les fichiers audio --------------------------

File file = new File(""); // l� o� la JVM est lanc�e : racine du projet

File repertoireSon = new File(file.getAbsoluteFile(),
      "exodecorateur_angryballs"+File.separatorChar+
      "maladroit"+File.separatorChar+"bruits");

//-------------------- chargement des sons pour les hurlements --------------------------------------

Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(repertoireSon, "config_audio_bille_hurlante.txt");
   
SonLong hurlements[] = SonLong.toTableau(sonsLongs); // on obtient un tableau de SonLong
  
Vector<Bille> billes = new Vector<Bille>();
int choixHurlementInitial = 0;
CadreAngryBalls cadre =new CadreAngryBalls("angry balls", "animation de billes marrantes", null, billes, hurlements, choixHurlementInitial);
cadre.montrer();
}

}
