package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.affichage.AffichageCarte;
import fonctionnement.affichage.AffichageCreateMonstre;
import fonctionnement.affichage.AfficherDsMonstre;
import fonctionnement.coordonnees.*;
import fonctionnement.de.De;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.personnages.monstre.AttaqueMonstre;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Espece;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;

import javax.swing.plaf.basic.BasicFormattedTextFieldUI;
import java.util.ArrayList;
import java.util.Random;


public class Map {
    private Coordonnees[][] m_carte;
    private int m_longueur;
    private int m_largeur;
    private int m_nbJoueurs;
    private int m_nbObstacles = 0;
    private int m_nbTresors = 0;
    private Personnage[] m_joueurs;
    private int m_nbMonstre = 0;
    private Monstre[] m_monstres;

    public Map(int nbjoueurs, Personnage[] joueurs){
        Random random = new Random();
        this.m_longueur = random.nextInt(15, 25);
        this.m_largeur = random.nextInt(15,25);
        this.m_carte = new Coordonnees[this.m_longueur][this.m_largeur];
        this.m_nbJoueurs = nbjoueurs;
        this.m_joueurs = joueurs;
        Affichage.afficher("la carte fait "+this.m_longueur+" cases de long et "+this.m_largeur+" cases de large");
        this.demanderMap();
    }
    public Map(int longueur, int largeur, int nbjoueurs, Personnage[] joueurs){
        if (longueur<15 || largeur<15 || longueur>25 || largeur>25){
            Affichage.afficherErreur("La carte doit faire entre 15 et 25 cases de long et de large");
            return;
        }
        this.m_longueur = longueur;
        this.m_largeur = largeur;
        this.m_carte = new Coordonnees[this.m_longueur][this.m_largeur];
        this.m_nbJoueurs = nbjoueurs;
        this.m_joueurs = joueurs;
        demanderMap();
    }

    public void demanderMap(){
        Affichage.afficher("Voulez vous une map random (O/N) ?");
        char choix = RecupInfos.scanOuiNon();
        if (choix == 'O'){
            createRandomMap();
            Affichage.afficherMap(this);
            redemanderMap();
        }
        else  {
            createMap();
            Affichage.afficherMap(this);
            redemanderMap();
        }
    }

    public void redemanderMap(){
        Affichage.afficher("Voulez vous utiliser cette map (O/N) ?");
        char choix = RecupInfos.scanOuiNon();
        if (choix == 'N'){
            demanderMap();
        }
    }

    public void initMap(){
        this.m_carte = new Coordonnees[this.m_longueur][this.m_largeur];
        for (int i = 0; i<this.m_longueur;i++){
            for (int j = 0; j<this.m_largeur;j++){
                m_carte[i][j] =null; // Initialisation de la carte avec des cases vides
            }
        }
    }
    public Coordonnees[][] getM_carte(){
        return m_carte;
    }
    public int getM_longueur(){
        return m_longueur;
    }
    public int getM_largeur(){
        return m_largeur;
    }
    public Coordonnees getCase(int i, int j) {
        if (i < 0 || i >= m_longueur || j < 0 || j >= m_largeur) {
            Affichage.afficherErreur("Coordonnées hors limites de la carte.");
            return null;
        }
        return m_carte[i][j];
    }

    public void createRandomMap(){
        Random random = new Random();
        initMap();
        m_nbObstacles = random.nextInt((this.m_longueur * this.m_largeur) / 15, (this.m_longueur * this.m_largeur) / 10);
        m_nbMonstre = random.nextInt(3, 5);
        m_nbTresors = random.nextInt(2, 4);
        for (int i = 0; i < m_nbObstacles; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                m_carte[x][y] = new CoordonneesObstacle(x,y); // -1 pour les obstacles
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        m_monstres = new Monstre[m_nbMonstre];
        for (int i = 0; i < m_nbMonstre; i++) {
            Monstre monstre = createRandomMonstres();
            int x, y;
            do {
                x = random.nextInt(0, this.m_longueur);
                y = random.nextInt(0, this.m_largeur);
            } while (m_carte[x][y] != null);

            monstre.setPosition(x, y);
            m_carte[x][y] = new CoordonneesMonstre(x, y, monstre);
            m_monstres[i] = monstre;
        }

        for (int i = 0; i < m_nbTresors; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                m_carte[x][y] = new CoordonneesItem(x,y);
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }

        //placer joueurs
        for (int i = 0; i < this.m_nbJoueurs; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                this.m_joueurs[i].setPosition(x,y);
                m_carte[x][y] = new CoordonneesPersonnage(x,y, this.m_joueurs[i]);
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }

        
        for (int i = 0; i < this.m_longueur; i++) {
            for (int j = 0; j < this.m_largeur; j++) {
                if (m_carte[i][j] == null) {
                    m_carte[i][j] = new CoordonneesCaseVide(i,j);
                }
            }
        }


    }

    public void setCase(int i, int j, Coordonnees coordonnees) {
        if (i < 0 || i >= m_longueur || j < 0 || j >= m_largeur) {
            Affichage.afficherErreur("Coordonnées hors limites de la carte.");
            return;
        }
        m_carte[i][j] = coordonnees;
    }

    public void createMap() {
        // demander cb d'obstacles
        // dder cb de monstres
        // dder cb de trésors
        // dder où sont positionnés chaque perso & chaque monstre

        initMap();
        placerObstacle();
        placerTresor();
        placerMonstre();
        placerPerso();

        for (int i = 0; i < this.m_longueur; i++) {
            for (int j = 0; j < this.m_largeur; j++) {
                if (m_carte[i][j] == null) {
                    m_carte[i][j] = new CoordonneesCaseVide(i,j);
                }
            }
        }


    }
    public void placerObstacle() {
        do {
            AffichageCarte.demanderObstacles();
            m_nbObstacles = RecupInfos.scanInt();
            if (m_nbObstacles < 0 || m_nbObstacles > ((m_longueur * m_largeur)-m_nbMonstre-m_nbJoueurs-m_nbTresors)) { // il ne peut pas y avoir + d'obstacles que de cases...
                Affichage.afficherErreur("Nombre d'obstacles invalide.");
            }
        } while (m_nbObstacles < 0 || m_nbObstacles > (m_longueur * m_largeur)-m_nbMonstre-m_nbJoueurs-m_nbTresors); // on veut pas + d'obstacles que de cases
        int [] CoordObstacle;

        for (int i = 0; i < m_nbObstacles; i++) {

            do {
                CoordObstacle = RecupInfos.scanCoord(this);
                if (CoordObstacle[0] < 0 || CoordObstacle[0] > m_longueur || CoordObstacle[1] < 0 || CoordObstacle[1] > m_largeur) {
                    AffichageCarte.CoordInvalide();
                }
            } while (CoordObstacle[0] < 0 || CoordObstacle[0] > m_longueur || CoordObstacle[1] < 0 || CoordObstacle[1] > m_largeur);

             if (m_carte[CoordObstacle[0]][CoordObstacle[1]] != null && m_carte[CoordObstacle[0]][CoordObstacle[1]].getCaseVide() ==null) {
                AffichageCarte.caseOccupee();
                i--; // pr recommencer
            }
            else {
                m_carte[CoordObstacle[0]][CoordObstacle[1]] = new CoordonneesObstacle(CoordObstacle[0]-1,CoordObstacle[1]); // On place un obstacle
            }
        }
    }
    public void placerTresor() {
        do {
            AffichageCarte.demanderTresors();
            m_nbTresors = RecupInfos.scanInt();
            if (m_nbTresors < 0 ||m_nbTresors > (m_largeur * m_longueur)-m_nbObstacles-m_nbMonstre-m_nbJoueurs) {
                Affichage.afficherErreur("Trop de trésors");
            }
        } while (m_nbTresors < 0 || m_nbTresors > (m_largeur * m_longueur)-m_nbObstacles-m_nbMonstre-m_nbJoueurs);
        int CoordTresor[];
        String nomObj = "";
        boolean trouve;
        int indexfinal = 0;
        String[][] listItem = {
                {"Arme","Bâton", "1d6", "1"},
                {"Arme","Rapière", "1d4", "1"},
                {"Arme","Épée longue", "1d4", "2"},
                {"Arme","Masse d'armes", "1d6", "1"},
                {"Arme","Fronde", "1d4", "6"},
                {"Arme","Arbalète légère", "1d8", "16"},
                {"Arme","Arc court", "1d6", "16"},
                {"Arme","Épée à deux mains", "2d6", "1"},
                {"Armure","Armure d'écailles", "9", "-"},
                {"Armure","Demi-plates", "10", "-"},
                {"Armure","Cotte de maille", "11", "-"},
                {"Armure","Harnois", "12", "-"}
        };
        for (int i = 0; i < m_nbTresors; i++) {
            Affichage.afficherLstItems(listItem);
            Affichage.afficher("");
            do {
                Affichage.afficher("Saisissez le nom de l'item a mettre a cette case");
                trouve = false;
                nomObj = RecupInfos.scanString().toUpperCase();
                for (int index = 0; index<listItem.length; index++){
                    if (listItem[index][1].toUpperCase().equals(nomObj)){
                        trouve = true;
                        indexfinal = index;
                    }
                }
                if (!trouve){
                    Affichage.afficherErreur("l'item n'est pas dans la liste");
                }
            } while (!trouve);
            
            do {
                CoordTresor = RecupInfos.scanCoord(this);
                if (CoordTresor[0] < 0 || CoordTresor[0] > m_longueur || CoordTresor[1] < 0 || CoordTresor[1] > m_largeur) {
                    AffichageCarte.CoordInvalide();
                }
            } while (CoordTresor[0] < 0 || CoordTresor[0] > m_longueur || CoordTresor[1] < 0 || CoordTresor[1] > m_largeur);

           if (m_carte[CoordTresor[0]][CoordTresor[1]] != null&&m_carte[CoordTresor[0]][CoordTresor[1]].getCaseVide() ==null) {
                AffichageCarte.caseOccupee();
                i--; // cancel ce tour de boucle
            }
            else {
                m_carte[CoordTresor[0]][CoordTresor[1]] = new CoordonneesItem(CoordTresor[0]-1,CoordTresor[1],indexfinal); // on place un trésor
            }
        }
    }
    public void placerMonstre() {
        do {
            AffichageCarte.demanderMonstres();
            m_nbMonstre = RecupInfos.scanInt();
            if (m_nbMonstre < 0 || m_nbMonstre > (m_longueur * m_largeur)-m_nbObstacles-m_nbJoueurs-m_nbTresors) {
                Affichage.afficherErreur("Erreur : Trop de monstres.");
            }
        } while (m_nbMonstre < 0 || m_nbMonstre > (m_longueur * m_largeur)-m_nbObstacles-m_nbJoueurs-m_nbTresors);

        createMonstre();

        int [] CoordMonstre;
        for (int i = 0; i < m_nbMonstre; i++) {
            do {
                CoordMonstre = RecupInfos.scanCoord(this);
                if (CoordMonstre[0] < 0 || CoordMonstre[0] >= m_longueur || CoordMonstre[1] < 0 || CoordMonstre[1] >= m_largeur) {
                    AffichageCarte.CoordInvalide();
                }
            } while (CoordMonstre[0] < 0 || CoordMonstre[0] > m_longueur || CoordMonstre[1] < 0 || CoordMonstre[1] > m_largeur);

            if (m_carte[CoordMonstre[0]][CoordMonstre[1]] != null) {
                AffichageCarte.caseOccupee();
                i--; // cancel ce tour de boucleeeee
            }
            else {

                m_monstres[i].setPosition(CoordMonstre[0], CoordMonstre[1]);
                m_carte[CoordMonstre[0]][CoordMonstre[1]] = new CoordonneesMonstre(CoordMonstre[0],CoordMonstre[1], m_monstres[i]);
            }
        }
    }

    public void placerPerso() {
        int [] CoordPerso;
        for (int i = 0; i < m_nbJoueurs; i++) {
            do {
                CoordPerso = RecupInfos.scanCoord(this);
                if (CoordPerso[0] < 0 || CoordPerso[0] > m_longueur || CoordPerso[1] < 0 || CoordPerso[1] > m_largeur) {
                    AffichageCarte.CoordInvalide();
                }
            } while (CoordPerso[0] < 0 || CoordPerso[0] > m_longueur || CoordPerso[1] < 0 || CoordPerso[1] > m_largeur);

            if (m_carte[CoordPerso[0]][CoordPerso[1]] != null) {
                AffichageCarte.caseOccupee();
                i--; // cancel tour de boucle
            }
            else {
                m_carte[CoordPerso[0]][CoordPerso[1]] = new CoordonneesPersonnage(CoordPerso[0],CoordPerso[1], m_joueurs[i]);
                this.m_joueurs[i].setPosition(CoordPerso[0],CoordPerso[1]); // on place le perso
            }
        }
    }

    public int getNbMonstre() {
        return this.m_nbMonstre;
    }

    public Monstre createRandomMonstres() {
        // creer des monstres pour la map random : renvoie un monstre random parmi les 5 profils existants
        Monstre[] profilsMonstre = {Monstre.dragon, Monstre.goblin, Monstre.rat, Monstre.troll, Monstre.loupGarou};
        Random random = new Random();
        int index = random.nextInt(profilsMonstre.length);
        return profilsMonstre[index];
    }



    // je m'excuse pour cette fonction je l'ai modifiée alors que y'avait pas assez de temps alors c'est tout moche ;-;
    // pas le temps de remodifier pour que ce soit clean malheureusement :')

    public void createMonstre() {

        this.m_monstres = new Monstre[this.m_nbMonstre];

        for (int i = 0; i < m_nbMonstre; i++) {

            Espece espece = null;
            AttaqueMonstre atk = null;
            CaracteristiqueMonstre carac = null;


            // pouf on crée l'espèce du monstre
            String especeMonstre;

            while (true) {
                // laisser possibilité de faire monstre random
                AffichageCarte.creerMonstreExistantOuPas();
                String temp = RecupInfos.scanString();


                if (temp.equalsIgnoreCase("+")) {
                    AfficherDsMonstre.possibilitesMonstre();
                    continue;
                }
                else if (temp.equalsIgnoreCase("oui")) {
                    AffichageCarte.afficherMonstresPossibles();
                    String monstre = RecupInfos.scanString();
                    Monstre m = Monstre.getMonstreByNom(monstre);
                    if (m != null) {
                        this.m_monstres[i] = new Monstre(m);
                        break; // pr skip le reste et retourner au debut du for, faire la prochaine bcl
                    }
                    // verif que le nom du monstre existe bien ds la liste
                }
                else if (temp.equalsIgnoreCase("non")) {

                    // on doit creer monstre custom

                    // ESPECE
                    while (true) {
                        AffichageCreateMonstre.demanderEspecePredefinie();
                        String t = RecupInfos.scanString();
                        if (t.equalsIgnoreCase("+")) {
                            AfficherDsMonstre.possibilitesEspece();
                            continue;

                        } else if (t.equalsIgnoreCase("oui")) {
                            AffichageCarte.afficherEspecesPossibles();
                            // string if == un truc qui existe ds la liste boum espece = ça
                            String nomEspece = RecupInfos.scanString();
                            espece = Monstre.getEspeceByNom(nomEspece);
                            if (espece == null) {
                                Affichage.afficherErreur("Erreur : espèce inconnue");
                                continue;
                            }
                        } else if (t.equalsIgnoreCase("Non")) {
                            AffichageCreateMonstre.demanderEspece();
                            especeMonstre = RecupInfos.scanString();
                            boolean existeEspece = false;
                            for (int j = 0; j < i; j++) {
                                if (m_monstres[j] != null && especeMonstre.trim().equals(m_monstres[j].getEspece().getNomEspece().trim())) { // c barbare, rajouter des getters pr que ca le soit moins i guess
                                    espece = CreerEntites.createEspece(especeMonstre, j);
                                    existeEspece = true;
                                    break;
                                }
                            }
                            if (!existeEspece) {
                                espece = CreerEntites.createEspece(especeMonstre);
                            }
                        }
                        else {
                            Affichage.afficherErreur("Erreur : réponse invalide.");
                            continue;
                        }
                        break;
                    }

                    // ATTAQUE
                    while (true) {
                        AffichageCreateMonstre.demanderAttaquePredefinie();
                        String t = RecupInfos.scanString();
                        if (t.equalsIgnoreCase("+")) {
                            AfficherDsMonstre.possibilitesAttaque();
                            continue;
                        } else if (t.equalsIgnoreCase("oui")) {
                            AffichageCarte.afficherAttaquesPossibles();
                            String nomAtk = RecupInfos.scanString();
                            atk = Monstre.getAttaqueByNom(nomAtk);
                            if (atk == null) {
                                Affichage.afficherErreur("Erreur : attaque inexistante");
                                continue;
                            }
                            // if == un truc qui existe attaque = ça
                        } else if (t.equalsIgnoreCase("non")) {
                            // pouf on crée euh l'attaque du monstre
                            atk = CreerEntites.createAttaqueMonstre();
                        }
                        else {
                            Affichage.afficherErreur("Erreur : réponse invalide");
                            continue;
                        }
                        break;
                    }

                    while (true) {


                        AffichageCreateMonstre.demanderCaracPredefinies();
                        String t = RecupInfos.scanString();
                        if (t.equalsIgnoreCase("+")) {
                            AfficherDsMonstre.possibilitesCarac();
                            continue;
                        } else if (t.equalsIgnoreCase("oui")) {
                            AffichageCarte.afficherCaracPossibles();
                            String nomCarac = RecupInfos.scanString();
                            carac = Monstre.getCaracByNom(nomCarac);
                            if (carac == null) {
                                Affichage.afficherErreur("Erreur : caractéristiques invalides");
                                continue;
                            }
                            // if == un truc qui existe attaque = ça
                        } else if (t.equalsIgnoreCase("non")) {
                            // caracteristiques du monstre
                            carac = CreerEntites.createCaracMonstre();
                        }
                        else {
                            Affichage.afficherErreur("Erreur : réponse invalide");
                            continue;
                        }
                        break;
                    }

                    if (espece != null && atk != null && carac != null) {
                        Monstre monstre = new Monstre(espece, carac, atk);
                        this.m_monstres[i] = monstre;
                        Affichage.afficher("Monstre créé avec succès !");
                        break;  // Fin de la création pour ce monstre
                    } else {
                        Affichage.afficherErreur("Impossible de créer le monstre, données manquantes.");
                        continue;
                    }

                }
                else {
                    Affichage.afficherErreur("Erreur : réponse invalide");
                    continue;
                }



            }

        }
    }

    public Personnage[] getJoueurs() {
        return m_joueurs;
    }


    public Monstre getMontreByNom(String nomMonstre) {
        for (int i = 0; i < m_longueur; i++) {
            for (int j = 0; j < m_largeur; j++) {
                Monstre m = m_carte[i][j].getMonstre();
                if (m != null && m.getEspece().getNomEspece().equalsIgnoreCase(nomMonstre)) {
                    return m;
                }
            }
        }
        return null;
    }

    public Monstre[] getMonstres(){
        return m_monstres;
    }
}

// TODO des monstres par defaut

