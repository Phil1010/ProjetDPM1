package modele.torche;

import java.util.Arrays;

import mesmaths.geometrie.base.Vecteur;

/**
 * Créateur de flammes par défaut, utilisé si le lancement du créateur de belles flammes a échoué
 * 
 * 
 * */
public class CreateurFlammesMock implements CreateurFlammes
{

/**
 * On suppose que echine.length <= etincelles.length
 * 
 * Par défaut le nuage d'étincelles est l'ensemble des sommets de l'échine.
 * La couleur par défaut est la couleur définie dans Echine
 * 
 * */
@Override
public boolean creeFlamme( final Vecteur position, final double rayon, final Echine echine,
        Vecteur[] etincelles,
        int[] couleursEtincelles)
{

int i;
for ( i = 0 ; i < echine.length(); ++i) { etincelles[i] = echine.getSommets()[i]; couleursEtincelles[i] = Echine.COULEUR_ECHINE.getRGB(); }

return true;
}

}
