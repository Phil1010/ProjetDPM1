package exodecorateur_angryballs.maladroit;

import java.awt.Color;
import java.io.File;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.*;
import modele.torche.Torche;
import musique.SonLong;
import vues.Billard;
import vues.BillardAR;
import vues.CadreAngryBalls;

/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement
 * différent
 * 
 * Idéal pour mettre en place le DP decorator
 */
public class TestAngryBalls {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
//---------------------- gestion des bruitages : paramétrage du chemin du dossier contenant les fichiers audio --------------------------

		File file = new File(""); // là où la JVM est lancée : racine du projet

		File répertoireSon = new File(file.getAbsoluteFile(), 
				File.separatorChar + "maladroit" + File.separatorChar + "bruits");
		System.out.println(répertoireSon);

//-------------------- chargement des sons pour les hurlements --------------------------------------

		Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(répertoireSon,
				"config_audio_bille_hurlante.txt");
		Vector<SonLong> sonsCollision = OutilsConfigurationBilleHurlante.chargeSons(répertoireSon,
				"config_collision.txt");

		SonLong hurlements[] = SonLong.toTableau(sonsLongs); // on obtient un tableau de SonLong
		SonLong collision[] = SonLong.toTableau(sonsCollision);

//------------------- création de la liste (pour l'instant vide) des billes -----------------------

		Vector<Bille> billes = new Vector<Bille>();

//---------------- création de la vue responsable du dessin des billes -------------------------

//Billard billard = new Billard(billes);
		Billard billard = new BillardAR(billes);

		Thread.sleep(500);

//---------------- création de la vue responsable du dessin des billes -------------------------

		int choixHurlementInitial = 0;
		CadreAngryBalls cadre = new CadreAngryBalls("Angry balls",
				"Animation de billes ayant des comportements différents. Situation idéale pour mettre en place le DP Decorator",
				billard, billes, hurlements, choixHurlementInitial);

		cadre.montrer(); // on rend visible la vue

//------------- remplissage de la liste avec 6 billes -------------------------------

		double xMax, yMax;
		double vMax = 0.1;
		xMax = cadre.largeurBillard(); // abscisse maximal
		yMax = cadre.hauteurBillard(); // ordonnée maximale

		double rayonRouge = 0.05 * Math.min(xMax, yMax); // rayon des billes : ici toutes les billes ont le même rayon,
		// mais
		// ce n'est pas obligatoire

		double rayonJaune = 0.06 * Math.min(xMax, yMax);
		double rayonVert = 0.07 * Math.min(xMax, yMax);
		double rayonCyan = 0.08 * Math.min(xMax, yMax);
		double rayonNoire = 0.09 * Math.min(xMax, yMax);
		double rayonBleuAzur = 0.1 * Math.min(xMax, yMax);

		Vecteur p0, p1, p2, p3, p4, p5, v0, v1, v2, v3, v4, v5; // les positions des centres des billes et les vecteurs
		// vitesse au démarrage.
		// Elles vont être choisies aléatoirement

//------------------- création des vecteurs position des billes ---------------------------------

		p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p5 = Vecteur.créationAléatoire(0, 0, xMax, yMax);

//------------------- création des vecteurs vitesse des billes ---------------------------------

		v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, 0);
		v2 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v3 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v4 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v5 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

//--------------- ici commence la partie à changer ---------------------------------

		Vecteur p = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		Vecteur v = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

// Bille rouge MRU avec rebond
		Bille red = new BilleConcrete(p1, rayonRouge, v1, Color.red.getRGB());
		red = new ComportementBilleRebondir(red);
		red = new ComportementBilleRectUniforme(red);
		red = new ComportementBillePilotee(red, billard, rayonRouge);
		red = new ComportementBilleCollision(red, collision[2], cadre);
		red = new ComportementBilleDisco(red, billard);
		billes.add(red);

// Bille jaune Newton avec pesanteur, frottements et rebond
		Bille yellow = new BilleConcrete(p2, rayonJaune, v2, Color.yellow.getRGB());
		yellow = new ComportementBilleRebondir(yellow);
		yellow = new ComportementBilleNewton(yellow);
		yellow = new ComportementBillePesenteur(yellow, new Vecteur(0, 0.001));
		yellow = new ComportementBilleFreinage(yellow);
		yellow = new ComportementBillePilotee(yellow, billard, rayonRouge);
		yellow = new ComportementBilleCollision(yellow, collision[2], cadre);

		billes.add((Bille) yellow);

// Bille verte Newton avec frottements et rebond
		Bille green = new BilleConcrete(p3, rayonVert, v3, Color.green.getRGB());
		green = new ComportementBilleRebondir(green);
		green = new ComportementBilleNewton(green);
		green = new ComportementBilleFreinage(green);
		green = new ComportementBillePilotee(green, billard, rayonRouge);
		green = new ComportementBilleCollision(green, collision[2], cadre);

		// billes.add((Bille) green);

// Bille cyan MRU qui traverse les murs
		Bille cyan = new BilleConcrete(p4, rayonCyan, v4, Color.cyan.getRGB());
		cyan = new ComportementBilleRectUniforme(cyan);
		cyan = new ComportementBilleTraverserMur(cyan);
		cyan = new ComportementBillePilotee(cyan, billard, rayonRouge);
		cyan = new ComportementBilleCollision(cyan, collision[2], cadre);

		// billes.add((Bille) cyan);
////
// Bille noir hurlante Newton
		Bille noire = new BilleConcrete(p5, rayonNoire, v5, Color.black.getRGB());
		noire = new ComportementBilleHurlante(noire,
		hurlements[choixHurlementInitial], cadre);
		ComportementBilleHurlante ref = (ComportementBilleHurlante) noire;
		noire = new ComportementBilleNewton(noire);
		noire = new ComportementBilleArret(noire);
		noire = new ComportementBillePilotee(noire, billard, rayonRouge);
		noire = new ComportementBilleCollision(noire, collision[2], cadre);

		// billes.add((Bille) noire);
		cadre.addChoixHurlementListener(ref);
//
// Bille azur MRU torche avec arret contre le mur et freignage
		int couleurBleuAzur = 0x003399;
		Bille azur = new BilleConcrete(p0, rayonBleuAzur, v0, couleurBleuAzur);

		azur = new ComportementBilleRebondir(azur);
		azur = new ComportementBilleRectUniforme(azur);
		azur = new ComportementBilleFreinage(azur);
		azur = new ComportementBillePilotee(azur, billard, rayonRouge);
		azur = new ComportementBilleCollision(azur, collision[2], cadre);
		azur = new ComportementBilleFlamme(azur, new Torche(azur), true, billard);

		billes.add((Bille) azur);

// Bille rose MRU avec arret contre le mur
//Bille rose = new BilleConcrete(p, rayon, v, Color.pink);
//rose = new ComportementBilleTraverserMur(rose);
//rose = new ComportementBilleRectUniforme(rose);
//rose = new ComportementBilleFreinage(rose);
////rose = new ComportementBillePilotee(rose, billard, 10);
//billes.add((Bille)rose);

//---------------------- ici finit la partie à changer -------------------------------------------------------------

		System.out.println("billes = " + billes);

//-------------------- création de l'objet responsable de l'animation (c'est un thread séparé) -----------------------

		AnimationBilles animationBilles = new AnimationBilles(billes, cadre);

//----------------------- mise en place des écouteurs de boutons qui permettent de contrôler (un peu...) l'application -----------------

		EcouteurBoutonLancer écouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
		EcouteurBoutonArreter écouteurBoutonArrêter = new EcouteurBoutonArreter(animationBilles);

//------------------------- activation des écouteurs des boutons et ça tourne tout seul ------------------------------

		cadre.lancerBilles.addActionListener(écouteurBoutonLancer); // pourrait être remplacé par Observable - Observer
		cadre.arreterBilles.addActionListener(écouteurBoutonArrêter); // pourrait être remplacé par Observable -
		// Observer

	}

}
