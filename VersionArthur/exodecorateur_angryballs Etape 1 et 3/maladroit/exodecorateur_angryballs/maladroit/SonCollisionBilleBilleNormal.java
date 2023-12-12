package exodecorateur_angryballs.maladroit;

import musique.SonLong;
import musique.SonLongFantome;

import java.io.*;
import java.util.Vector;

public class SonCollisionBilleBilleNormal implements SonsCollisionsBilles{

    @Override
    public Vector<SonLong> chargeSons(File repertoireBruits, String nomFichierConfigAudio) {
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
}
