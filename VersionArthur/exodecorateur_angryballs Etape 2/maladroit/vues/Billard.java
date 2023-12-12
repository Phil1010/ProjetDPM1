package vues;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Vector;

import modele.Bille;
import visiteurDessin.VisiteurDessin;
import visiteurDessin.VisiteurDessinAWT;
import modele.Bille;



/*import exodecorateur_angryballs.mieux.modele.Dessinateur;
import exodecorateur_angryballs.mieux.modele.awt.DessinateurBilleAWT;*/




/**
 * responsable du dessin des billes
 *
 *
 * */
public class Billard extends Canvas implements VueBillard
{
    public Vector<Bille> billes;
    public Billard(Vector<Bille> billes)
    {
        this.billes = billes;
    }

    public void init() {}

    /* (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graphics) { this.dessine(graphics); }

    @Override
    public double largeurBillard() { return this.getWidth(); }

    @Override
    public double hauteurBillard() { return this.getHeight(); }

    @Override
    public void miseAJour() { this.repaint(); }

    @Override
    public void montrer() { this.setVisible(true); }

    /**
     * Comme on fait appelle Ã  AWT, on utilise VisiteurDessinAWT
     * @param graphics
     */
    public  void dessine(Graphics graphics)
    {
        int i;

        for ( i = 0; i < this.billes.size(); ++i)
            this.billes.get(i).visiteurDessine(new VisiteurDessinAWT(graphics));
    }

    public void setImage() {}

}