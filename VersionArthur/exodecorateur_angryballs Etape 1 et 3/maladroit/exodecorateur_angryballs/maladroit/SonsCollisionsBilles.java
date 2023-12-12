package exodecorateur_angryballs.maladroit;

import musique.SonLong;

import java.io.File;
import java.util.Vector;

public interface SonsCollisionsBilles {
    public Vector<SonLong> chargeSons(File repertoireBruits, String nomFichierConfigAudio); //throws IOException
}
