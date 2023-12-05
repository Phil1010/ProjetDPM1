package modele.torche;

import mesmaths.geometrie.base.Vecteur;

/**
 * crée une flamme à partir d'une échine.
 * 
 * 
 * */
public interface CreateurFlammes
{

/**
 * Crée une flamme à partir de l'échine.
 * La flamme est définie par un nuage d'étincelles. Les positions des étincelles sont inscrites dans le tableau étincelles et les couleurs 
 * des étincelles dans le tableau couleursEtincelles. Les coouleurs sont au format RGB sur 24 bits.
 * 
 * Pour optimiser le temps d'exécution, on ne fait pas de new inutile.
 * On suppose donc que le tableau etincelles existe déjà et que tous ses élements ont déjà été créés.
 * De même, on suppose que le tableau couleursEtincelles est déjà créé avec tous ses éléments.
 * 
 * Le tableau etincelles doit avoir le même nombre d'éléments que le tableau couleursEtincelles.
 * de telle sorte que l'étincelle n° i a sa position en etincelles[i] et sa couleur en couleursEtincelles[i]
 * 
 * @param : positionCentreBille : position du centre de la bille
 * @param : rayonBille : rayon de la bille
 * @param echine : colonne vertébrale de la queue (et donc de la flamme)
 * @param etincelles : résultat : tableau des positions des étincelles
 * @param couleursEtincelles : résultat  : tableau des couleurs des étincelles
 * 
 * @return true en cas de succès et false en cas d'échec
 * 
 * */
public boolean creeFlamme(final Vecteur positionCentreBille, final double rayonBille, final Echine echine, Vecteur[] etincelles, int[] couleursEtincelles);

}
