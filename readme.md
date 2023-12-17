# Angryballs

## Points faits

- Partie 1 : Réorganisation du comportement des billes grâce au design pattern **décorateur**
- Partie 2 : Ajout de la bille pilotée grâce aux design pattern **décorateur** et **state**
- Partie 3 : Ajout de bruits d'impact lors de la collision des billes
- Partie 4 : Modification du comportement de la bille enflamée : dès qu'elle rentre en contact avec une autre bille, celle-ci s'enflamme

## Améliorations effectuées :

- La méthode *dessine* qui dépendait d'AWT a été remplacé par une autre méthode *visiteurDessine* qui utilise le design pattern **Visiteur** pour faire abstraction de la librairie graphique.
- Dans *BilleConcrete*, on représente une couleur avec un *int* et non une *Color*.
- Utilisation du design pattern **Observeur / Observable** pour prévenir les autres billes quand ds billes se touchent.
- Création d'une classe *Torche* qui factorise du code pour la bille enflammée.
- Création de la bille disco qui change de couleur aléatoirement toutes les secondes.
- Création de la bille qui grossit. À chaque fois qu'une bille la frappe, elle grossit d'un certain pourcentage.

## Remarques et améliorations possibles : 

- Partie 1 : Rien à dire.
- Partie 2 : Le comportement *ComportementBillePilotee* dépend encore d'AWT, car on doit ajouter un mouseListener à ce comportement. Même en utilisant **visiteur** pour faire abstraction de la librairie graphique, pour que la bille soit bien pilotée avec AWT, on devrait créer une classe dérivée *ComportementBillePiloteeAWT* de *ComportementBillePilotee* et qui implémente *MouseListener* pour l'utiliser dans le main.
- Partie 3 : Le bruit d'impact se lance uniquement bille à bille. Quand une bille touche un mur, elle ne fait aucuns bruits.
- Partie 4 : Pour bien détecter qu'une bille est enflammée, on regarde uniquement le premier maillon (on ne parcours pas toute la chaîne).