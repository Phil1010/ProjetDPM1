package modele;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import vues.VueBillard;

public class ComportementBilleCollision extends DecorateurBille implements Observer
{
    public static final int DELAI_MIN = 10;    /* délai minimum de rafraichissement du son. en millisecondes */
    public static final int DELAI_MAX = 150;    /* délai maximum de rafraichissement du son. en millisecondes */
    public static final double COEFF_VOLUME = 6;      // plus la valeur est grande, plus le son augmente rapidement en fct de la vitesse de la boule

    public SonLong sonLong;                            /* bande son à diffuser */
    int i;              /* n° de l'élément de sonLong à jouer. on doit avoir i >= 0.
	                    sonLong se charge de faire le modulo pour obtenir un indice correct
	                    et pour boucler ainsi sur le tableau inscrit dans sonLong */
    long dernierInstant;        /* dernier instant où le son a été diffusé */
    VueBillard vueBillard;


    public ComportementBilleCollision(Bille bille, SonLong sonLong, VueBillard vueBillard) {
        super(bille);
        this.sonLong = sonLong;
        i = 0;
        dernierInstant = System.currentTimeMillis();
        this.vueBillard = vueBillard;
    }

    @Override
    public void deplacer(double deltaT) {
        bille.deplacer(deltaT);

    }

    @Override
    public void gestionAcceleration(Vector<Bille> billes) {
        bille.gestionAcceleration(billes);

    }

    @Override
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        // TODO Auto-generated method stub
        boolean tmp = bille.gestionCollisionBilleBille(billes);

        // On va jouer le bruit
        if(tmp)
        {
            Vecteur p = this.getPosition();
            Vecteur v = this.getVitesse();
            double xMax;

            xMax = vueBillard.largeurBillard();

            double n = v.norme();
            //double n2 = v.normeCarrée();
            double y = Math.exp(-COEFF_VOLUME*n);                // y = e^(-COEFF*n). on obtient donc 0 < y <= 1
            double volume = 1-y;                                 // on obtient 0 <= volume < 1 avec volume == 0 si n == 0  et volume proche de 1 si n est grand
            double x1 = p.x/xMax;                   /* on obtient 0 <= x1 <= 1 */ ////System.err.println("dans BilleHurlante.déplacer() : x1 =  "+ x1);
            double balance = 2*x1 - 1;              // on obtient -1 <= balance <= 1
            ////System.err.println("volume = " + volume);
            //double v2 = volume*volume;
            int délai = (int)(DELAI_MIN*volume + DELAI_MAX*y);              /* le délai entre 2 diffusions diminue lorsque la vitesse augmente */
            long instant = System.currentTimeMillis();
            if (instant - this.dernierInstant >=délai)                      /* la fréquence de diffusion augmente donc avec la vitesse de la bille */
            {
                double coeffPitch = 1;
                this.sonLong.joue(i++, volume, balance, coeffPitch);            /* le son est diffusé dans un thread séparé */
                this.dernierInstant= instant;
            }
        }

        return tmp;
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
                                 double hauteur) {
        bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);

    }

	@Override
	public void update(Observable arg0, Object arg1) {
		Bille bille = (Bille) arg1;
		
		
	}


}