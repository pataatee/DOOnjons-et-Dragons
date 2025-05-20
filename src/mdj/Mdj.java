package mdj;

import affichage.Affichage;
import com.sun.nio.sctp.AbstractNotificationHandler;
import gameContent.personnages.Personnage;
import gameContent.personnages.classe.*;
import gameContent.personnages.race.*;

import java.util.Objects;
import java.util.Scanner;

public class Mdj {
    private Map m_map;
    private int m_nbJoueurs;
    private int m_nbMonstres;
    private int m_nbTresors;
    //private Personnage[m_nbJoueurs] m_joueurs;
    private static Scanner scan= new Scanner(System.in);

    public Mdj() {
        this.m_map = new Map();

        Affichage.afficher("Selectionnez le nombre de joueurs (2-6) : ");
        String text= scan.nextLine();
        this.m_nbJoueurs = Integer.parseInt(text);
        for (int i=0; i<this.m_nbJoueurs; i++){
            Affichage.afficher("joueur "+(i+1));
            Affichage.afficher("Selectionnez le nom du personnage : ");
            String nom= scan.nextLine();
            Race race = chooseRace();
            Classe classe = chooseClasse();
            Personnage personnage = new Personnage(nom,race, classe);

        }

        this.m_nbMonstres = 0;
        this.m_nbTresors = 0;
    }
    public Race chooseRace(){
        Affichage.afficher("Selectionnez la race du personnage : ");
        Race race= null;
        String resultat = scan.nextLine();
        if (Objects.equals(resultat, "Halfelin")){
            race = new Halfelin();
        }
        else if (Objects.equals(resultat, "Nain")){
            race = new Nain();
        }
        else if (Objects.equals(resultat, "Elfe")){
            race = new Elfe();
        }
        else if (Objects.equals(resultat, "Humain")){
            race = new Humain();
        }
        else{
            Affichage.afficherErreur("Choississez une race existante");
            chooseRace();
        }
        return race;
    }
    public Classe chooseClasse(){
        Affichage.afficher("Selectionnez la classe du personnage : ");
        Classe classe= null;
        String resultat = scan.nextLine();
        if (Objects.equals(resultat, "Clerc")){
            classe = new Clerc();
        }
        else if (Objects.equals(resultat, "Magicien")){
            classe = new Magicien();
        }
        else if (Objects.equals(resultat, "Roublard")){
            classe = new Roublard();
        }
        else if (Objects.equals(resultat, "Guerrier")){
            classe = new Guerrier();
        }
        else{
            Affichage.afficherErreur("Choisissez une classe existante");
            chooseClasse();
        }
        return classe;
    }
}
