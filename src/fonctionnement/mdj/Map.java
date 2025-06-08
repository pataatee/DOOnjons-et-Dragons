package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.affichage.AffichageCarte;
import fonctionnement.affichage.AffichageCreateMonstre;
import fonctionnement.coordonnees.*;
import fonctionnement.de.De;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.personnages.monstre.AttaqueMonstre;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Espece;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;

import java.util.Random;


public class Map {
    private Coordonnees[][] m_carte;
    private int m_longueur;
    private int m_largeur;
    private int m_nbJoueurs;
    private Personnage[] m_joueurs;
    private int m_nbMonstre;
    private Monstre[] m_monstres;

    public Map(int nbjoueurs, Personnage[] joueurs){
        Random random = new Random();
        this.m_longueur = random.nextInt(15, 25);
        this.m_largeur = random.nextInt(15,25);
        this.m_carte = new Coordonnees[this.m_longueur][this.m_largeur];
        this.m_nbJoueurs = nbjoueurs;
        this.m_joueurs = joueurs;
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
        // placer obstacles
        int obstacles = random.nextInt((this.m_longueur * this.m_largeur) / 15, (this.m_longueur * this.m_largeur) / 10);
        int monstres = random.nextInt(3, 5);
        int tresors = random.nextInt(2, 4);
        for (int i = 0; i < obstacles; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                m_carte[x][y] = new CoordonneesObstacle(x,y); // -1 pour les obstacles
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        // placer monstres
        for (int i = 0; i < monstres; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                Monstre monstre = new Monstre(new CaracteristiqueMonstre(1, 1, 1, 1, 1, 1));
                monstre.setPosition(x,y);
                m_carte[x][y] = new CoordonneesMonstre(x,y, monstre);
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }

        // placer trésors
        for (int i = 0; i < tresors; i++) {
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
        int nbObstacles;
        do {
            AffichageCarte.demanderObstacles();
            nbObstacles = RecupInfos.scanInt();
            if (nbObstacles < 0 || nbObstacles > (m_longueur * m_largeur)) { // il ne peut pas y avoir + d'obstacles que de cases...
                Affichage.afficherErreur("Nombre d'obstacles invalide.");
            }
        } while (nbObstacles < 0 || nbObstacles > (m_longueur * m_largeur)); // on veut pas + d'obstaces que de cases

        int [] CoordObstacle;

        for (int i = 0; i < nbObstacles; i++) {

            do {
                CoordObstacle = RecupInfos.scanCoord(this);
                if (CoordObstacle[0] < 0 || CoordObstacle[0] > m_longueur || CoordObstacle[1] < 0 || CoordObstacle[1] > m_largeur) {
                    AffichageCarte.CoordInvalide();
                }
            } while (CoordObstacle[0] < 0 || CoordObstacle[0] > m_longueur || CoordObstacle[1] < 0 || CoordObstacle[1] > m_largeur);

             if (m_carte[CoordObstacle[0]][CoordObstacle[1]] != null) {
                AffichageCarte.caseOccupee();
                i--; // pr recommencer
            }
            else {
                m_carte[CoordObstacle[0]][CoordObstacle[1]] = new CoordonneesObstacle(CoordObstacle[0]-1,CoordObstacle[1]); // On place un obstacle
            }
        }
    }
    public void placerTresor() {
        int nbTresors;
        do {
            AffichageCarte.demanderTresors();
            nbTresors = RecupInfos.scanInt();
            if (nbTresors < 0 || nbTresors > (m_largeur * m_longueur)) {
                Affichage.afficherErreur("Trop de trésors");
            }
        } while (nbTresors < 0 || nbTresors > (m_largeur * m_longueur));

        int CoordTresor[];
        for (int i = 0; i < nbTresors; i++) {
            do {
                CoordTresor = RecupInfos.scanCoord(this);
                if (CoordTresor[0] < 0 || CoordTresor[0] > m_longueur || CoordTresor[1] < 0 || CoordTresor[1] > m_largeur) {
                    AffichageCarte.CoordInvalide();
                }
            } while (CoordTresor[0] < 0 || CoordTresor[0] > m_longueur || CoordTresor[1] < 0 || CoordTresor[1] > m_largeur);

           if (m_carte[CoordTresor[0]][CoordTresor[1]] != null) {
                AffichageCarte.caseOccupee();
                i--; // cancel ce tour de boucle
            }
            else {
                m_carte[CoordTresor[0]][CoordTresor[1]] = new CoordonneesItem(CoordTresor[0]-1,CoordTresor[1]); // on place un trésor
            }
        }
    }
    public void placerMonstre() {
        int nbMonstres;
        do {
            AffichageCarte.demanderMonstres();
            m_nbMonstre = RecupInfos.scanInt();
            if (m_nbMonstre < 0 || m_nbMonstre > (m_longueur * m_largeur)) {
                Affichage.afficherErreur("Erreur : Trop de monstres.");
            }
        } while (m_nbMonstre < 0 || m_nbMonstre > (m_longueur * m_largeur));

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


    public void createMonstre() {

        this.m_monstres = new Monstre[this.m_nbMonstre];

        for (int i = 0; i < m_nbMonstre; i++) {


            // pouf on crée l'espèce du monstre
            String especeMonstre;
            AffichageCreateMonstre.demanderEspece();
            especeMonstre = RecupInfos.scanString();
            Espece espece = null;
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

            // pouf on crée euh l'attaque du monstre
            AttaqueMonstre atk = CreerEntites.createAttaqueMonstre();

            // caracteristiques du monstre
            CaracteristiqueMonstre carac = CreerEntites.createCaracMonstre();

            Monstre monstre = new Monstre(espece, carac, atk);

            this.m_monstres[i] = monstre;


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
}

// TODO des monstres par defaut

