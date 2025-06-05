package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.affichage.AffichageCarte;
import fonctionnement.affichage.AffichageCreateMonstre;
import fonctionnement.coordonnees.*;
import fonctionnement.de.De;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.personnages.monstre.AttaqueMonstre;
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
        String choix = RecupInfos.scanString();
        if (choix.equals("O")||(choix.equals("o"))){
            createRandomMap();
            Affichage.afficherMap(this);
            redemanderMap();
        }
        else if (choix.equals("N")||(choix.equals("n"))) {
            //TODO demander dcp les maps keski va où etc
            createMap();
            Affichage.afficherMap(this);
            redemanderMap();
        }
        else {
            Affichage.afficherErreur("Choix non valide");
            demanderMap();
        }
    }

    public void redemanderMap(){
        Affichage.afficher("Voulez vous utiliser cette map (O/N) ?");
        String choix = RecupInfos.scanString();
        if (choix.equals("N")){
            demanderMap();
        }
        else if (!choix.equals("O")){
            Affichage.afficherErreur("Choix non valide");
            redemanderMap();
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
        for (int i = 0; i < monstres; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                m_carte[x][y] = new CoordonneesMonstre(x,y, m_monstres[i]); // 1 pour les monstres
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        for (int i = 0; i < tresors; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                m_carte[x][y] = new CoordonneesItem(x,y); // 2 pour les trésors
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        for (int i = 0; i < this.m_nbJoueurs; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == null) {
                this.m_joueurs[i].setPosition(x,y);
                m_carte[x][y] = new CoordonneesPersonnage(x,y, this.m_joueurs[i]); // 2 pour les trésors
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        for (int i = 0; i < this.m_longueur; i++) {
            for (int j = 0; j < this.m_largeur; j++) {
                if (m_carte[i][j] == null) {
                    m_carte[i][j] = new CoordonneesCaseVide(i,j); // 0 pour les cases vides
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

        int xObstacle;
        String yStringObstacle;
        int yObstacle;

        for (int i = 0; i < nbObstacles; i++) {

            do {
                AffichageCarte.demanderCoordonneeX();
                xObstacle = RecupInfos.scanInt()-1;
                if (xObstacle < 0 || xObstacle > m_longueur) {
                    AffichageCarte.xInvalide();
                }
            } while (xObstacle < 0 || xObstacle > m_longueur);

            do {
                AffichageCarte.demanderCoordonneeY();
                yStringObstacle = RecupInfos.scanString();
                if (yStringObstacle.length() != 1 || yStringObstacle.charAt(0) < 'A' || yStringObstacle.charAt(0) > 'A' + m_largeur) { // y.charat0 verif si c > A, et la derniere condition verif si c < a+largeur de carte
                    AffichageCarte.yInvalide();
                }
            } while (yStringObstacle.length() != 1 || yStringObstacle.charAt(0) < 'A' || yStringObstacle.charAt(0) > 'A' + m_largeur);
            yObstacle = yStringObstacle.charAt(0) - 'A' ; // Convertit la lettre en un entier correspondant à la coordonnée Y

            if (m_carte[xObstacle][yObstacle] != null) {
                AffichageCarte.caseOccupee();
                i--; // pr recommencer
            }
            else {
                m_carte[xObstacle][yObstacle] = new CoordonneesObstacle(xObstacle-1, yObstacle); // On place un obstacle
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

        int xTresor;
        String yStringTresor;
        int yTresor;
        for (int i = 0; i < nbTresors; i++) {
            do {
                AffichageCarte.demanderCoordonneeX();
                xTresor = RecupInfos.scanInt()-1;
                if (xTresor < 0 || xTresor > m_longueur) {
                    AffichageCarte.xInvalide();
                }
            } while (xTresor < 0 || xTresor > m_longueur);

            do {
                AffichageCarte.demanderCoordonneeY();
                yStringTresor = RecupInfos.scanString();
                if (yStringTresor.length() != 1 || yStringTresor.charAt(0) < 'A' || yStringTresor.charAt(0) > 'A' + m_largeur) {
                    AffichageCarte.yInvalide();
                }
            } while (yStringTresor.length() != 1 || yStringTresor.charAt(0) < 'A' || yStringTresor.charAt(0) > 'A' + m_largeur);
            yTresor = yStringTresor.charAt(0) - 'A';

            if (m_carte[xTresor][yTresor] != null) {
                AffichageCarte.caseOccupee();
                i--; // cancel ce tour de boucle
            }
            else {
                m_carte[xTresor][yTresor] = new CoordonneesItem(xTresor-1, yTresor); // on place un trésor
            }
        }
    }
    public void placerMonstre() {
        //int nbMonstres;
        do {
            AffichageCarte.demanderMonstres();
            this.m_nbMonstre = RecupInfos.scanInt();
            if (m_nbMonstre < 0 || m_nbMonstre > (m_longueur * m_largeur)) {
                Affichage.afficherErreur("Erreur : Trop de monstres.\n");
            }
        } while (m_nbMonstre < 0 || m_nbMonstre > (m_longueur * m_largeur));

        int xMonstre;
        String yStringMonstre;
        int yMonstre;
        for (int i = 0; i < m_nbMonstre; i++) {
            do {
                AffichageCarte.demanderCoordonneeX();
                xMonstre = RecupInfos.scanInt() - 1;
                if (xMonstre < 0 || xMonstre > m_longueur) {
                    AffichageCarte.xInvalide();
                }
            } while (xMonstre < 0 || xMonstre > m_longueur);

            do {
                AffichageCarte.demanderCoordonneeY();
                yStringMonstre = RecupInfos.scanString();
                if (yStringMonstre.length() != 1 || yStringMonstre.charAt(0) < 'A' || yStringMonstre.charAt(0) > 'A' + m_largeur) {
                    AffichageCarte.yInvalide();
                }
            } while (yStringMonstre.length() != 1 || yStringMonstre.charAt(0) < 'A' || yStringMonstre.charAt(0) > 'A' + m_largeur);
            yMonstre = yStringMonstre.charAt(0) - 'A';

            if (m_carte[xMonstre][yMonstre] != null) {
                AffichageCarte.caseOccupee();
                i--; // cancel ce tour de boucleeeee
            }
            else {
                m_carte[xMonstre][yMonstre] = new CoordonneesMonstre(xMonstre-1, yMonstre, m_monstres[i]);
            }
        }
    }

    public void placerPerso() {
        int xPerso;
        String yStringPerso;
        int yPerso;
        for (int i = 0; i < m_nbJoueurs; i++) {
            do {
                AffichageCarte.demanderCoordonneeX();
                xPerso = RecupInfos.scanInt()-1;
                if (xPerso < 0 || xPerso > m_longueur) {
                    AffichageCarte.xInvalide();
                }
            } while (xPerso < 0 || xPerso > m_longueur);

            do {
                AffichageCarte.demanderCoordonneeY();
                yStringPerso = RecupInfos.scanString();
                if (yStringPerso.length() != 1 || yStringPerso.charAt(0) < 'A' || yStringPerso.charAt(0) > 'A' + m_largeur) {
                    AffichageCarte.yInvalide();
                }
            } while (yStringPerso.length() != 1 || yStringPerso.charAt(0) < 'A' || yStringPerso.charAt(0) > 'A' + m_largeur);
            yPerso = yStringPerso.charAt(0) - 'A';

            if (m_carte[xPerso][yPerso] != null) {
                AffichageCarte.caseOccupee();
                i--; // cancel tour de boucle
            }
            else {
                m_carte[xPerso][yPerso] = new CoordonneesPersonnage(xPerso, yPerso, m_joueurs[i]);
                this.m_joueurs[i].setPosition(xPerso, yPerso); // on place le perso
            }
        }
    }

    public int getNbMonstre() {
        return this.m_nbMonstre;
    }

    public void createMonstre() {

        for (int i = 0; i < m_nbMonstre; i++) {

            // pouf on crée l'espèce du monstre
            String especeMonstre;

            AffichageCreateMonstre.demanderEspece();
            especeMonstre = RecupInfos.scanString();

            Espece espece = new Espece(especeMonstre, i); // i parce qu'on veut que le num du monstre soit jms le mm
            // TODO vérif si espece egales, si ya des especes egales oui on utilise constructeur w numero sinon no

            // pouf on crée euh l'attaque du monstre
            String nomAttaque;
            AffichageCreateMonstre.demanderAttaque();
            nomAttaque = RecupInfos.scanString();

            int porteeAttaque;
            AffichageCreateMonstre.demanderPorteeAttaque();
            porteeAttaque = RecupInfos.scanInt();

            // TODO do while si on veut limiter la portée max

            int degatsAttaque;
            AffichageCreateMonstre.demanderDegatsAttaque();
            degatsAttaque = RecupInfos.scanInt();

            // TODO idem si on veut limiter degats max de l'attaque bcs oui eviter que ce soit trop cheaté

            AttaqueMonstre attaque = new AttaqueMonstre(nomAttaque, porteeAttaque, degatsAttaque);

            // caracteristiques du monstre
            int pvMonstre;
            AffichageCreateMonstre.demanderPvMonstre();
            pvMonstre = RecupInfos.scanInt();

            // TODO midif si on veut des pv min ou max pr encore une x que ce soit pas trop cheaté

            int forceMonstre; // 0 si attaque a distance
            AffichageCreateMonstre.demanderForce();
            forceMonstre = RecupInfos.scanInt();

            int dexteriteMonstre; // 0 si attaque au cac
            AffichageCreateMonstre.demanderDexterite();
            dexteriteMonstre = RecupInfos.scanInt();

            int initiativeMonstre;
            De de = new De(4, 4);
            initiativeMonstre = de.lancer_de();
            // caracteristiques monstre determinees par un dé ?
            // estce que ya le +3 aussi ?

            int classeArmureMonstre;


            Monstre monstre = new Monstre();
        }
        return;
    }
}

// TODO des monstres par defaut

