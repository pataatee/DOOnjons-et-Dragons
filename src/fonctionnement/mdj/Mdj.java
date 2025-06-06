package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.classe.*;
import gameContent.personnages.perso.race.*;

import java.util.List;
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
        this.m_nbJoueurs = Affichage.scanInt();
        this.m_joueurs = new Personnage[this.m_nbJoueurs];
        for (int i=0; i<this.m_nbJoueurs; i++){
            Affichage.afficher("joueur "+(i+1));
            Affichage.afficher("Selectionnez le nom du personnage : ");
            String nom= Affichage.scanString();
            Race race = chooseRace();
            Classe classe = chooseClasse();
            Personnage personnage = new Personnage(nom,race, classe);
            this.m_joueurs[i] = personnage;
        }
        this.m_map = mdj_map_dimensions();
        Affichage.afficherMap(this.m_map);

        this.m_nbMonstres = 0;
        this.m_nbTresors = 0;

        Affichage.afficher("La partie peut commencer !");

        for (Personnage pers : this.m_joueurs){
            // chaque joueur s'équipe
            pers.equiperArme();
            pers.equiperArmure();
        }
        tours();
    }
    public Race chooseRace(){
        Affichage.afficher("Selectionnez la race du personnage : ");
        Race race= null;
        String resultat = Affichage.scanString();
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
        String resultat = Affichage.scanString();
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
        char ouinon = Affichage.scanOuiNon();
        if (ouinon == 'O'){
            map = new Map(this.m_nbJoueurs, this.m_joueurs); //on crée la map avec des dimensions random
        }
        else {
            Affichage.afficher("Selectionnez la longueur de la carte (15-25) : ");
            int longueur = Affichage.scanInt();
            Affichage.afficher("Selectionnez la largeur de la carte (15-25) : ");
            int largeur = Affichage.scanInt();
            map = new Map(longueur, largeur, this.m_nbJoueurs, this.m_joueurs); //on crée la map avec les dimensions choisies
            if (map.getM_carte() == null) {             //si la carte est vide, on re appelle la fonction
                Affichage.afficherErreur("Erreur lors de la creation de la carte");
                return mdj_map_dimensions();
            }
        }
        return map;
    }

    public void tours(){
        boolean morts = verify_morts();
        int i = 0;
        while ((!morts) && (i < 10)){ //et que le donjon est pas fini, mais pas encore testé TODO a enlever le i, c temporaire pour éviter une boucle infinie
            for (int pers = 0; pers<m_nbJoueurs; pers++){
                new Tour(this.m_joueurs[pers], i, this.m_map); //on lance le tour pour chaque joueur
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
