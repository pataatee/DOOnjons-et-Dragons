package mdj;

import affichage.Affichage;

import java.util.Random;


public class Map {
    private int[][] m_carte;
    private int m_longueur;
    private int m_largeur;
    public Map(){
        this.m_longueur = 15;
        this.m_largeur = 25;
        this.createMap();
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
        String choix = affichage.Affichage.ScanString();
        if (choix.equals("O")||(choix.equals("o"))){
            createRandomMap();
        }
        else if (choix.equals("N")||(choix.equals("n"))) {
            this.createMap();
        }
        else {
            Affichage.afficherErreur("Choix non valide");
            demanderMap();
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
        int obstacles = random.nextInt((this.m_longueur * this.m_largeur) / 8, (this.m_longueur * this.m_largeur) / 12);
        int monstres = random.nextInt(3, 5);
        int tresors = random.nextInt(2, 4);

    }
}

