package visiteurDessin;
import modele.Bille;
import modele.torche.Torche;
/**
 * Classe Visiteur qui permet de faire abstraction de la librairie graphique choisie
 */
public abstract class VisiteurDessin
{
    /**
     * @brief Permet de dessiner la bille
     * @param b La bille
     */
    public abstract void visiteurDessine(Bille b);

    public abstract void visiteurDessine(Torche t);
}
