# 🐉 DOOnjons et Dragons 
## A propos du projet
### Contexte
Ce jeu a fait l'objet d'un projet supervisé réalisé dans le cadre de ma formation (BUT Informatique). 
<br>
Nous étions un groupe de 2 développeurs ([Lucie](https://github.com/cookie2211) et moi-même), et avions 5 semaines pour le réaliser.
> *Vous pourrez trouver le sujet donné par nos enseignants [ici](https://github.com/pataatee/DOOnjons-et-Dragons/tree/rendu_final/sujet)*
### Statut du jeu
Ce projet est **fonctionnel**, malgré un affichage uniquement via un terminal.
*Un affichage graphique sera peut-être implémenté à l'avenir (très incertain, mais possible)*
## Description du jeu
Le jeu D&D est un **jeu de rôle** dans lequel vous incarnez un personnage, souvent fantastique (Mage, Elfe...). Durant la partie, vous **explorez un donjon** empli de monstres et trésors... à vous d'affronter les nombreux défis face auxquels vous pouvez tomber !
<br>
Un **Maître du Jeu dirige** et raconte l'histoire du début à la fin de la partie. Il **crée et positionne** tout un tas de monstres et butins dans le donjon afin de rendre l'expérience plus intéressante pour tous les personages !
<br>
<br>
Comme vous l'aurez sûrement maintenant compris, ce projet est une implémenttion d'un jeu basé sur le célèbre jeu de rôle **D&D** ! <br>

## Règles du jeu


## Fonctionnalités 
- Sélection du nombre de joueurs *(2 à 6 joueurs)*
- Création des personnages :
  - Choisir un nom
  - Choisir une race parmi 4 existantes *(Humain, Elfe, Halfelin, Nain)*
  - Choisir une classe parmi 4 existantes *(Clerc, Guerrier, Magicien, Roublard)*
  - Sélection d'une arme à équiper pour chaque personnage *(Fronde, Arbalète, Arc, Rapière, Epée longue, Epée à 2 mains, Bâton, Masse)*
  - Sélection d'une armure à équiper pour chaque personage *(Armure d'écailles, Demi-plates, Cotte de mailles, Harnois)*
- Création de la carte :
  - Choix des dimensions *(aléatoire/manuel)*
  - Génération des positions sur la carte *(aléatoire/manuel)*
  - Possibilité de changer de carte si celle créée ne convient pas
### Pour les joueurs
- Actions de la partie :
  - Attaquer
  - S'équiper *(armes/armures)*
  - Se déplacer
  - Terminer le tour
  - *Si Magicien, lancer un sort*
- Voir les détails de la dernière action réalisée
  - Affiche le tour courant
  - Affiche les statistiques des ennemis *(Nom, PVs, Coordonnées)*
  - Affiche les sttistiques personnelles *(PVs, Force, Dextérité, Initiative, Vitesse)*
  - Affiche l'inventaire *(Armes, Armures, Arme équipée)*
### Pour le MDJ
- Actions de la partie :
  - Ajouter des obstacles
  - Attaquer
  - Placer un trésor
  - Déplacer un personnage/un monstre
