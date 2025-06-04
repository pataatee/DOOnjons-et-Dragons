package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.*;
import gameContent.personnages.perso.Personnage;

import java.util.Random;


public class Map {
    private Coordonnees[][] m_carte;
    private int m_longueur;
    private int m_largeur;
    private int m_nbJoueurs;
    private Personnage[] m_joueurs;

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
        String choix = Affichage.ScanString();
        if (choix.equals("O")||(choix.equals("o"))){
            createRandomMap();
            Affichage.afficherMap(this);
            redemanderMap();
        }
        else if (choix.equals("N")||(choix.equals("n"))) {

        }
        else {
            Affichage.afficherErreur("Choix non valide");
            demanderMap();
        }
    }

    public void redemanderMap(){
        Affichage.afficher("Voulez vous utiliser cette map (O/N) ?");
        String choix = Affichage.ScanString();
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
                m_carte[x][y] = new CoordonneesMonstre(x,y); // 1 pour les monstres
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
}

