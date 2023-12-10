package modele.torche;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import visiteurDessin.VisiteurDessin;

public class Torche
{
	/**
	 * conditionne le retour à la position verticale de la flamme
	 * doit être tel que 0 < K.
	 * 
	 * Plus K est proche de zéro, plus la queue est verticale
	 * Plus K s'éloigne de zéro, plus la queue suit fidèlement la trajectoire de la bille
	 * 
	 * */
	public final static double K = 5.0e2;
	
	/**
	 * echine : echine actuelle de la flamme de la bille (= échine-vitesse)
	 * echineVerticale : position de l'échine dans sa position verticale, 
	 * plus la bille ralentit, plus son échine tend à se confondre avec echineVerticale
	 * */
	public Echine echine, echineVerticale;

	/** positions du nuage d'étincelles */
	public Vecteur etincelles[];

	/** couleurs du nuage d'étincelles au format RGB 24 bits */
	public int couleursEtincelles[];

	/**
	 * tel que mNuage +1 = (mEtincelles+1)*(mRangees+1)
	 * tel que mNuage +1 = etincelles.length = couleursEtincelles.length
	 * */
	public int mEtincelles, mRangees, mNuage;

	/**
	 * rayon d'une étincelle en coordonnées écran
	 * 
	 * */
	public double rayonEtincelle;

	/**
	 * 
	 * l'objet qui sait créer une flamme, c-à-d le nuage d'étincelles (positions et couleurs) à partir de l'échine 
	 * */
	public CreateurFlammes createurFlammes;
	
	private Bille b;
	
	public Bille getBille() {return b;}
	
	public Torche(Bille b)
	{
		this(b, 20, 0.18, 1.12, 25, 20, 1.5);
	}
	
	public Torche(Bille b,
	final int nombreSommets,  
    final double coeffR0, 
    final double coeffRaison, 
    final int mEtincelles, 
    final int mRangees, 
    final double rayonEtincelle)
	{
		this.b = b;
		
		Vecteur u = new Vecteur(0,-1);  // vecteur unitaire vertical orienté vers le haut, en coordonnées écran

		/* l'échine est créée verticale, orientée du bas vers le haut */
		this.echine = new Echine(b.getPosition().somme(u.produit(b.getRayon())), coeffR0*b.getRayon(), u, coeffRaison, nombreSommets);
		//this.echineVerticale = new Echine(getPosition().somme(u.produit(getRayon())), 0.65*coeffR0*this.getRayon(), u, coeffRaison, nombreSommets);
		this.echineVerticale = this.echine.copie();

		/* on tente de lancer le créateur de belles flammes (client vers le serveur de Flammes C++) */
		try
	    {
		    this.mEtincelles = mEtincelles;
		    this.mRangees = mRangees;
		    this.createurFlammes = new CreateurBellesFlammes(this.mRangees,this.mEtincelles);
		    
		    this.rayonEtincelle = rayonEtincelle;
		    System.out.println("CréateurBellesFlammes créé");
	    }
		catch (Exception e)         /* échec de connexion vers le serveur de flammes, on crée un créateur de flammes minimaliste */
	    {
		    this.createurFlammes = new CreateurFlammesMock();
		    this.mEtincelles = this.echine.length()-1;
		    this.mRangees = 0;
		    this.rayonEtincelle = 4;
	    }

		/* pour optimiser les temps d'exécution, le nuage d'étincelles est déjà créé ( c-à-d tableau des positions et tableau des couleurs) */

		this.mNuage = (this.mEtincelles+1)*(this.mRangees+1) - 1;
		this.etincelles = new Vecteur[this.mNuage+1];
		int i; for ( i = 0 ; i < this.etincelles.length; ++i) this.etincelles[i] = new Vecteur();
		this.couleursEtincelles = new int[this.etincelles.length];
	}
	
	/**
	 * Notons i-1 l'instant précédent et i l'instant actuel
	 * 
	 * Notons échine(i-1) = position et forme de l'échine-vitesse à l'instant i-1
	 * Notons échineVerticale(i-1) = position et forme de l'échine verticale à l'instant i-1
	 * Notons échine(i) = position et forme de l'échine-vitesse à l'instant i
	 * Notons échineVerticale(i) = position et forme de l'échine verticale à l'instant i
	 * 
	 * alors echineVerticale(i) = fct(echineVerticale(i-1))
	 * alors echine(i) = fct(echine(i-1),echineVerticale(i-1))
	 * 
	 * mise à jour de l'échine vitesse et de l'échine verticale : attributs this.echine et this.echineVerticale
	 * @param p : position du centre de la bille à l'instant i-1
	 */
	public void miseAJour2Echines(Vecteur p)
	{
		echine.miseAJour(p, b.getPosition());    // on met à jour la position de l'échine de la torche (échine-vitesse)
		/*ok = this.createurFlammes.creeFlamme(this.getPosition(), this.getRayon(),
		        this.echine, this.mRangees, this.mEtincelles, this.etincelles,this.couleursEtincelles);*/
		//System.err.println(this.echine);
	
		Vecteur trans = b.getPosition().difference(p);           /* translation à appliquer à l'échine verticale */
	
		for (Vecteur v : echineVerticale.sommets) v.ajoute(trans);          // on met à jour l'échine verticale
	
		double alfa0 = Math.exp(-Torche.K*b.getVitesse().normeCarrée());        // définit l'influence de l'échine verticale, l'influence dépend de la vitesse de la bille
		                                                                    // plus la bille est rapide, plus alfa0. se rapproche de zéro
		                                                                    // on a toujours 0 < alfa0 <= 1
	
	
		//-------- la nouvelle position de l'échine est un mélange entre l'échine-vitesse et l'échine verticale ----
		//--------- on fait une combinaison linéaire convexe de telle sorte que plus on va vers la pointe de la flamme, plus l'échine verticale a de l'influence
	
		double d = echine.sommets.length;
	
		alfa0/=d;
		int i,j;
		for ( i = 0, j = 1; i < echine.sommets.length; i = j++) 
	    {
		    double alfa = alfa0*j;          /* astuce pour que les sommets situés près de la pointe de la flamme soient plus attirés par l'échine verticale
		                                         que les sommets situés près de la bille */
		    double alfabar = 1-alfa;
		    echine.sommets[i].combinaisonLineaire(alfabar, alfa, echineVerticale.sommets[i]);
	    }
	}
	
	public void visiteurDessine(VisiteurDessin v)
	{
		v.visiteurDessine(this);
	}
}
