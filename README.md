# 🐉 DOOnjons et Dragons 
## 🛈 A propos du projet
### Contexte
Ce jeu a fait l'objet d'un projet supervisé réalisé dans le cadre de ma formation (BUT Informatique). 
<br>
Nous étions un groupe de 2 développeurs ([Lucie](https://github.com/cookie2211) et moi-même), et avions 5 semaines pour le réaliser. <br>
*Vous pourrez trouver le sujet donné par nos enseignants [ici](https://github.com/pataatee/DOOnjons-et-Dragons/tree/rendu_final/sujet)*
### Statut du jeu
Ce projet est **fonctionnel**, malgré un affichage uniquement via un terminal. <br>
*Un affichage graphique sera peut-être implémenté à l'avenir (très incertain, mais possible)*
## 🐲 Description du jeu
Le jeu D&D est un **jeu de rôle** dans lequel vous incarnez un personnage, souvent fantastique (Mage, Elfe...). Durant la partie, vous **explorez un donjon** empli de monstres et trésors... à vous d'affronter les nombreux défis face auxquels vous pouvez tomber !
<br>
Un **Maître du Jeu dirige** et raconte l'histoire du début à la fin de la partie. Il **crée et positionne** tout un tas de monstres et butins dans le donjon afin de rendre l'expérience plus intéressante pour tous les personages !
<br>
<br>
Comme vous l'aurez sûrement maintenant compris, ce projet est une implémenttion d'un jeu basé sur le célèbre jeu de rôle **D&D** ! <br>

## 📜 Règles du jeu
En entrant dans l'univers de **Donjons et Dragons**, l'objectif est simple : **éliminer tous les monstres** ! <br>
Ainsi, vous incarnez un **personnage fantastique** afin de mener cette bataille. <br>
Vous vous battez dans un donjon plus ou moins grand, plus ou moins garni de trésors...ou de pièges. <br>

*Note: Les lancers de dés seront présentés de la manière suivante : **<nb_de_dés>d<nb_de_faces>***

### Les personnages 🧍
Le personnage que vous incarnez possède une certaine **race** ainsi qu'une certaine **classe**. Ces dites classes et races possèdent des spécificités et offrent ainsi des bonus sur les **caractéristiques** de ce personnage. <br>
Les **caractéristiques** des personnages sont les suivantes : 
- PVs *(points de vie)* - le nombre de dégâts que peut subir le personnage avant de mourir
- Force - offre un bonus de dégâts lors de l'utilisation d'une arme au corps-à-corps
- Dextérité - offre un bonus de dégâts lors de l'utilisation d'une arme à distance
- Vitesse - indique le nombre de cases que le personnage peut parcourir en se déplaçant
- Initiative - offre un bonus pour déterminer l'ordre d'attaque lors d'un combat

Les caractéristiques de base des personnages (hors PVs) sont déterminées par un lancer de **4d4 +3**. <br>
A ces résultats sont ajoutés les **bonus** de chacune des races. <br>
Les PVs, quant à eux, sont déterminés par la **classe** du personnage. <br>
#### Caractéristiques des classes : 
- Clerc : 16 PVs
- Guerrier : 20 PVs
- Magicien : 12 PVs
- Roublard : 16 PVs

#### Bonus des races : 
- Humain : +2 pour chaque caractéristique
- Elfe : +6 dextérité 
- Halfelin : +4 dextérité, +2 vitesse
- Nain : +6 force

### Les armes et armures ⚔️
Les personnages ont accès à des **armes** et **armures** pour se battre et se protéger contre les monstres. Chaque arme possède ses **spécificités** *(dégâts, portée)*, ainsi que chaque armure *(classe d'armure = PVs supplémentaires)*. <br> En voici la liste.<br>
#### Armes : 
- Armes à distance :
  - arbalète légère - dégâts: 1d8, portée: 16 cases
  - fronde - dégâts: 1d4, portée: 6 cases
  - arc court - dégâts: 1d6, portée: 16 cases
- Armes courantes au corps-à-corps :
  - bâton - dégâts: 1d6, portée: 1 case
  - masse d'armes - dégâts: 1d6, portée: 1 case
- Armes de guerre au corps à corps :
  - épée longue - dégâts: 1d8, portée: 1 case
  - rapière - dégâts: 1d8, portée: 1 case
  - épée à deux mains - dégâts: 2d6, portée: 1 case

#### Armures : 
- Armures légères :
  - armure d'écailles - classe d'armure: 9
    demi-plate - classe d'armure: 10
- Armures lourdes :
  - cotte de mailles - classe d'armure: 11
  - harnois - classe d'armure: 12

Les personnages ne peuvent avoir qu'**une seule arme et armure équipée** à la fois, mais leur inventaire ne possède aucue limite. <br>

Les personnages possèdent un **inventaire** de base différent selon leur **classe**. Ainsi, voici ce que chaque personnage possède dès sa création selon sa classe : 
- Clerc :
  - masse d'armes
  - armure d'écailles
  - arbalète légère
- Guerrier :
  - cotte de mailles
  - épée longue
  - arbalète légère
- Magicien :
  - bâton
  - fronde
- Roublard :
  - rapière
  - arc court

**/!\ A noter** : <br> Lorsque votre personnage équipe une **arme de guerre**, sa **vitesse** est **diminuée de 2 points**, mais sa **force** est **augmentée de 4**. <br> Lorsque votre personnage équipe une **armure lourde**, sa **vitesse** est **diminuée de 4 points**.

### Les sorts 🪄
Les **magiciens** possèdent une spécifité hors du commun : ils peuvent **lancer des sorts** ! <br>
Les **clercs** peuvent également lancer un unique sort : **guérison**. <br>
Voici donc la liste de leurs pouvoirs...

#### Sorts
- Guérison - le magicien ou le clerc guérit un allié *(ou lui-même)* d'un nombre de PVs déterminé par un lancer de **1d10**. *A noter que le personnage se faisant guérir ne peut pas avoir plus de PVs que ce qu'il n'en avait à l'origine.*
- Boogie Woogie - le magicien échange les positions de deux entités *(personnage ou monstre)*.
- Arme magique - le magicien offre un bonus de 1 lors des jets d'attaque et de 1 lors des jets de dégâts sur **une arme**. Les bonus sont cumulables.

### Les monstres 👹
Dans le donjon dans lequel vous jouez, des **montres** rôdent, avec comme objectif de vous tuer... <br>
Les monstres possèdent certaines caractéristiques, qui sont déterminées par le **maître du jeu**. Ces caractéristiques sont un **nom**, une **espèce**, une **attaque**, un nombre de **PVs**, une **force**, une **vitesse**, une **déxtérité**, une **classe d'armure** ainsi qu'une **initiative**. <br>
Votre objectif en tant que joueur : **tuer ces monstres avant qu'ils ne vous tuent** !

### Fin du jeu 🏁
La partie se finit sur une **victoire** si les personnages que vous incarnez ont réussi à éliminer tous les monstres du donjon. Elle se finira cependant sur une **défaite** si ce sont les monstres qui vous tuent...

## ✨ Fonctionnalités 
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

## ⚙️ Installation
### Prérequis
- **Java** doit être installé sur votre machine.
- **IDE** supportant le Java.
- **Git** installé sur votre machine.

### Comment installer ?
**Clonez** ce dépôt git sur votre machine : <br> <br>
*Avec SSH:*
```bash
git clone git@github.com:pataatee/DOOnjons-et-Dragons.git
```
*Avec HTTPS:*
```bash
https://github.com/pataatee/DOOnjons-et-Dragons.git
```

**Ouvrez** le projet depuis votre machine sur votre IDE *(IntelliJ, VSCode...)* <br>
**Naviguez** vers `/src/Main.java` <br>
**Clic droit** sur `Main.java`, puis cliquez sur **Run**. Votre IDE ouvrira un terminal intégré dans lequel vous pourrez ensuite jouer ! <br>
<br>
### 🎉 Vous avez installé le jeu avec succès !
Maintenant, amusez-vous bien ! 😉
