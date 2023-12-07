package vues;

import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;


import musique.SonLong;

/**
 * Repr�sente la ligne des cases � cocher pour le choix du son pour la bille hurlante.
 * 
 * Ligne du bas du cadre contenant les billes
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 * */
public class PanneauChoixHurlement extends Panel
{
BoutonChoixHurlement boutons[];
CheckboxGroup checkboxGroup;

/**
 * @param hurlements : tous les sons disponibles pour la bille hurlante
 * @param choixHurlementInitial : hurlement choisi par d�faut � l'initialisation de l'application. 
 *                                  choixHurlementInitial doit repr�senter un indice valide du tableau hurlements
 * */
public PanneauChoixHurlement(SonLong [] hurlements, int choixHurlementInitial)
{
this.boutons = new BoutonChoixHurlement [hurlements.length];
this.checkboxGroup = new CheckboxGroup();
this.setLayout(new GridLayout(1, this.boutons.length));
int i;
for ( i = 0; i < this.boutons.length; ++i)
    {
    this.boutons[i] = new BoutonChoixHurlement(checkboxGroup, false, hurlements[i]);
    this.add(this.boutons[i]);
    }

this.boutons[choixHurlementInitial].setState(true);
}

}