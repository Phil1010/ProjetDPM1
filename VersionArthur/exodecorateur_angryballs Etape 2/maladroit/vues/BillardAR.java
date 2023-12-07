package vues;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Vector;

import modele.Bille;
import modele.Bille;


/**
 * repr�sente un billard o� l'affichage est fait par active rendering (c�-d sans passer par paint)
 * 
 * 
 * */
public class BillardAR extends Billard
{
BufferStrategy strategie;

public BillardAR(Vector<Bille> billes)
{
super(billes);
}

public void init()
{
this.setIgnoreRepaint(true);
this.createBufferStrategy(2);

this.strategie = this.getBufferStrategy();
}



@Override
public void miseAJour()
{


    Graphics g = this.strategie.getDrawGraphics();
    g.clearRect(0, 0, this.getWidth(), this.getHeight());
    this.dessine(g);
    this.strategie.show();
    

}

public void nettoie()
{
Graphics g = this.strategie.getDrawGraphics();
g.clearRect(0, 0, this.getWidth(), this.getHeight());
this.strategie.show();
}

public void repaint()
{
this.miseAJour();
}
}
