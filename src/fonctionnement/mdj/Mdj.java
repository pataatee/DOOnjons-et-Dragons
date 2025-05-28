package fonctionnement.mdj;

<<<<<<< HEAD:src/fonctionnement/mdj/Mdj.java
import fonctionnement.affichage.Affichage;
=======
import affichage.Affichage;
import gameContent.items.Armurerie;
import gameContent.personnages.monstre.Monstre;
>>>>>>> fd98c5ffaede09f722149d020060d234531625c9:src/mdj/Mdj.java
import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.classe.*;
import gameContent.personnages.perso.race.*;

import java.util.Objects;

public class Mdj {
    private final Map m_map;
    private final int m_nbJoueurs; //nombre de joueurs
    private int m_nbMonstres;
    private int m_nbTresors;
    private Personnage[] m_joueurs; // Tableau contenant tous les joueurs
    private Monstre[] m_monstres; // Tableau contenant tous les monstres (à implémenter plus tard)
    private Armurerie[] m_tresors; // Tableau contenant tous les trésors (à implémenter plus tard)

    public Mdj() {
        Affichage.afficher("Selectionnez le nombre de joueurs (2-6) : ");
        this.m_nbJoueurs = Affichage.ScanInt();
        this.m_joueurs = new Personnage[this.m_nbJoueurs];
        for (int i=0; i<this.m_nbJoueurs; i++){
            Affichage.afficher("joueur "+(i+1));
            Affichage.afficher("Selectionnez le nom du personnage : ");
            String nom= Affichage.ScanString();
            Race race = chooseRace();
            Classe classe = chooseClasse();
            Personnage personnage = new Personnage(nom,race, classe);
            this.m_joueurs[i] = personnage;
        }
        this.m_map = mdj_map_dimensions();
        Affichage.afficherMap(this.m_map);

        this.m_nbMonstres = 0;
        this.m_nbTresors = 0;
        tours();
    }
    public Race chooseRace(){
        Affichage.afficher("Selectionnez la race du personnage : ");
        Race race= null;
        String resultat = Affichage.ScanString();
        if (Objects.equals(resultat, "Halfelin")){
            race = new Halfelin();
            return race;
        }
        else if (Objects.equals(resultat, "Nain")){
            race = new Nain();
            return race;
        }
        else if (Objects.equals(resultat, "Elfe")){
            race = new Elfe();
            return race;
        }
        else if (Objects.equals(resultat, "Humain")){
            race = new Humain();
            return race;
        }
        else{
            Affichage.afficherErreur("Choississez une race existante");
            return chooseRace();
        }
    }
    public Classe chooseClasse(){
        Affichage.afficher("Selectionnez la classe du personnage : ");
        Classe classe= null;
        String resultat = Affichage.ScanString();
        if (Objects.equals(resultat, "Clerc")){
            classe = new Clerc();
            return classe;
        }
        else if (Objects.equals(resultat, "Magicien")){
            classe = new Magicien();
            return classe;
        }
        else if (Objects.equals(resultat, "Roublard")){
            classe = new Roublard();
            return classe;
        }
        else if (Objects.equals(resultat, "Guerrier")){
            classe = new Guerrier();
            return classe;
        }
        else{
            Affichage.afficherErreur("Choisissez une classe existante");
            return chooseClasse();
        }

    }
    public Map mdj_map_dimensions(){
        Map map;
        Affichage.afficher("Voulez vous des dimensions random pour votre map ?");
        String ouinon = Affichage.ScanString();
        if (Objects.equals(ouinon, "O")){
            map = new Map();
        }
        else if (Objects.equals(ouinon, "N")){
            Affichage.afficher("Selectionnez la longueur de la carte (15-25) : ");
            int longueur = Affichage.ScanInt();
            Affichage.afficher("Selectionnez la largeur de la carte (15-25) : ");
            int largeur = Affichage.ScanInt();
            map = new Map(longueur, largeur);
            if (map.getM_carte() == null){             //si la carte est vide, on re appelle la fonction
                Affichage.afficherErreur("Erreur lors de la creation de la carte");
                return mdj_map_dimensions();
            }
        }
        else {
            Affichage.afficherErreur("Choix non valide");
            return mdj_map_dimensions();
        }
        return map;
    }

    public void tours(){
        boolean morts = verify_morts();
        int i = 0;
        while ((!morts) && (i < 10)){ //et que le donjon est pas fini, mais pas encore testé TODO a enlever le i, c temporaire pour éviter une boucle infinie
            for (int pers = 0; pers<m_nbJoueurs; pers++){
                Affichage.afficherTour(i, this.m_joueurs[pers], this.m_map);
                morts = verify_morts();
                i++;
            }
        }
        if (morts){
            Affichage.afficher("y'a eu un mort, so fin du jeu heheeee"); // ! A MODIF C UNE BLAGUE D'ACCORD, CA FAIT 4H QUE J'SUIS DESSUS ALED
        }
    }

    public boolean verify_morts(){ //renvoie true si y'a des morts, false sinon
        boolean val_retour = false;
        for (int pers = 0; pers<m_nbJoueurs; pers++){
            if (this.m_joueurs[pers].getPvs() <= 0){
                val_retour = true;
            }
        }
        return val_retour;
    }
}
