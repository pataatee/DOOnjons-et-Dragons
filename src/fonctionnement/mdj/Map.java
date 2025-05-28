package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;

import java.util.Random;


public class Map {
    private int[][] m_carte;
    private int m_longueur;
    private int m_largeur;
    public Map(){
        Random random = new Random();
        this.m_longueur = random.nextInt(15, 25);
        this.m_largeur = random.nextInt(15,25);
        this.m_carte = new int[this.m_longueur][this.m_largeur];
        this.demanderMap();
    }
    public Map(int longueur, int largeur){
        if (longueur<15 || largeur<15 || longueur>25 || largeur>25){
            Affichage.afficherErreur("La carte doit faire entre 15 et 25 cases de long et de large");
            return;
        }
        this.m_longueur = longueur;
        this.m_largeur = largeur;
        this.m_carte = new int[this.m_longueur][this.m_largeur];
        demanderMap();
    }

    public void demanderMap(){
        Affichage.afficher("Voulez vous une map random (O/N) ?");
        String choix = Affichage.ScanString();
        if (choix.equals("O")||(choix.equals("o"))){
            createRandomMap();
            redemanderMap();
        }
        else if (choix.equals("N")||(choix.equals("n"))) {
            this.createMap();
        }
        else {
            Affichage.afficherErreur("Choix non valide");
            demanderMap();
        }
    }

    public void redemanderMap(){
        Affichage.afficher("Voulez vous utiliser cette map (O/N) ?");
        String choix = affichage.Affichage.ScanString();
        if (choix.equals("N")){
            demanderMap();
        }
        else if (!choix.equals("O")){
            Affichage.afficherErreur("Choix non valide");
            redemanderMap();
        }
    }

    public void createMap(){
        this.m_carte = new int[this.m_longueur][this.m_largeur];
        for (int i = 0; i<this.m_longueur;i++){
            for (int j = 0; j<this.m_largeur;j++){
                m_carte[i][j] = 0;
            }
        }
    }
    public int[][] getM_carte(){
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
        int obstacles = random.nextInt((this.m_longueur * this.m_largeur) / 15, (this.m_longueur * this.m_largeur) / 10);
        int monstres = random.nextInt(3, 5);
        int tresors = random.nextInt(2, 4);
        for (int i = 0; i < obstacles; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == 0) {
                m_carte[x][y] = -1; // -1 pour les obstacles
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        for (int i = 0; i < monstres; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == 0) {
                m_carte[x][y] = 1; // 1 pour les monstres
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
        for (int i = 0; i < tresors; i++) {
            int x = random.nextInt(0, this.m_longueur);
            int y = random.nextInt(0, this.m_largeur);
            if (m_carte[x][y] == 0) {
                m_carte[x][y] = 2; // 2 pour les trésors
            } else {
                i--; // Si la case est déjà occupée, on recommence
            }
        }
    }
}

