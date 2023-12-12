package modele;

import java.awt.Color;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import visiteurDessin.VisiteurDessin;

public abstract class DecorateurBille extends Bille
{
    protected Bille bille;	// La bille qu'ont veut decorer

    public DecorateurBille(Bille bille)
    {
        this.bille = bille;
    }

    public Bille getBille()
    {
        return bille.getBille();
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
    	return bille.gestionCollisionBilleBille(billes);
    }


    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonneeCoinHautGauche, double largeur,
                                          double hauteur) {
    	bille.collisionContour(abscisseCoinHautGauche, ordonneeCoinHautGauche, largeur, hauteur);
    }

    @Override
    public void visiteurDessine(VisiteurDessin v) { bille.visiteurDessine(v); }

    /** GETTERS */

    @Override
    public Vecteur getPosition() { return bille.getPosition(); }

    @Override
    public double getRayon() { return bille.getRayon(); }

    @Override
    public Vecteur getVitesse() { return bille.getVitesse(); }

    @Override
    public Vecteur getAcceleration() { return bille.getAcceleration(); }

    @Override
    public int getClef() { return bille.getClef(); }

    @Override
    public double masse() { return bille.masse(); }

    @Override
    public int getColor() { return bille.getColor(); }
}
