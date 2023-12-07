package exodecorateur_angryballs.maladroit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  ICI : IL N'Y A RIEN A CHANGER 
 *  
 *  */
public class EcouteurBoutonLancer implements ActionListener
{
AnimationBilles animationBilles;



/**
 * @param animationsBilles
 */
public EcouteurBoutonLancer(AnimationBilles animationBilles)
{
this.animationBilles = animationBilles;
}



@Override
public void actionPerformed(ActionEvent arg0)
{
this.animationBilles.lancerAnimation();
}

}
