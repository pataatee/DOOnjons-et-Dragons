package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.classe.*;
import gameContent.personnages.perso.race.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class Mdj {
    private Map m_map;
    private final int m_nbJoueurs; //nombre de joueurs
    private int m_nbMonstres;
    private int m_nbTresors;
    private final Personnage[] m_joueurs; // Tableau contenant tous les joueurs
    private Monstre[] m_monstres; // Tableau contenant tous les monstres (à implémenter plus tard)
    private Armurerie[] m_tresors; // Tableau contenant tous les trésors (à implémenter plus tard)
    private final String[] m_MdjActions = new String[]{"Ajouter des obstacles", "Attaquer", "placer un trésor"};

    public Mdj() {
        Affichage.afficher("Selectionnez le nombre de joueurs (2-6) : ");
        this.m_nbJoueurs = RecupInfos.scanInt();
        this.m_joueurs = new Personnage[this.m_nbJoueurs];
        for (int i=0; i<this.m_nbJoueurs; i++){
            Affichage.afficher("joueur "+(i+1));
            Affichage.afficher("Selectionnez le nom du personnage : ");
            String nom = "";
            do { nom= RecupInfos.scanString();}
            while (nom.length()<3);
            Race race = chooseRace();
            Classe classe = chooseClasse();
            Personnage personnage = new Personnage(nom,race, classe);
            this.m_joueurs[i] = personnage;
        }
        for (Personnage pers : this.m_joueurs){
            // chaque joueur s'équipe
            pers.equiperArme();
            pers.equiperArmure();
        }
    }
    public Race chooseRace(){
        Affichage.afficher("Selectionnez la race du personnage : ");
        Race race= null;
        String resultat = RecupInfos.scanString().toUpperCase();
        if (Objects.equals(resultat, "HALFELIN")){
            race = new Halfelin();
            return race;
        }
        else if (Objects.equals(resultat, "NAIN")){
            race = new Nain();
            return race;
        }
        else if (Objects.equals(resultat, "ELFE")){
            race = new Elfe();
            return race;
        }
        else if (Objects.equals(resultat, "HUMAIN")){
            race = new Humain();
            return race;
        }
        else{
            Affichage.afficherErreur("Choississez une race parmi celles proposées :\n- Halfelin\n- Elfe\n- Nain\n- Humain");
            return chooseRace();
        }
    }
    public Classe chooseClasse(){
        Affichage.afficher("Selectionnez la classe du personnage : ");
        Classe classe= null;
        String resultat = RecupInfos.scanString().toUpperCase();
        if (Objects.equals(resultat, "CLERC")){
            classe = new Clerc();
            return classe;
        }
        else if (Objects.equals(resultat, "MAGICIEN")){
            classe = new Magicien();
            return classe;
        }
        else if (Objects.equals(resultat, "ROUBLARD")){
            classe = new Roublard();
            return classe;
        }
        else if (Objects.equals(resultat, "GUERRIER")){
            classe = new Guerrier();
            return classe;
        }
        else{
            Affichage.afficherErreur("Choisissez une classe parmi celles proposées :\n- Clerc\n- Magicien\n- Roublard\n- Guerrier");
            return chooseClasse();
        }

    }
        public Map mdj_map_dimensions(){
        Map map;
        Affichage.afficher("Voulez vous des dimensions random pour votre map ?(O/N)");
        char ouinon = RecupInfos.scanOuiNon();
        if (ouinon == 'O'){
            map = new Map(this.m_nbJoueurs, this.m_joueurs); //on crée la map avec des dimensions random
        }
        else {
            Affichage.afficher("Selectionnez la longueur de la carte (15-25) : ");
            int longueur = RecupInfos.scanInt();
            Affichage.afficher("Selectionnez la largeur de la carte (15-25) : ");
            int largeur = RecupInfos.scanInt();
            map = new Map(longueur, largeur, this.m_nbJoueurs, this.m_joueurs); //on crée la map avec les dimensions choisies
            if (map.getM_carte() == null) {             //si la carte est vide, on re appelle la fonction
                Affichage.afficherErreur("Erreur lors de la creation de la carte");
                return mdj_map_dimensions();
            }
        }
        return map;
    }

    public void donjon(){
        this.m_map = mdj_map_dimensions();
        Affichage.afficherMap(this.m_map);
        if (this.m_map.getNbMonstre() == 0){
            return;
        }

        this.m_nbMonstres = 0;
        this.m_nbTresors = 0;

        Affichage.afficher("La partie peut commencer !");
        int tour = 0;
        while (!verify_morts()) {
            for (int pers = 0; pers < m_nbJoueurs; pers++) {
                Tour t = new Tour(this.m_joueurs[pers], tour, this.m_map); //on lance le tour pour chaque joueur
                if (t.getStatut() != 0) {
                    return;
                }
                if (!this.m_joueurs[pers].estPasMort()) {
                    Affichage.afficher("y'a eu un mort, so fin du jeu heheeee"); // ! A MODIF C UNE BLAGUE D'ACCORD, CA FAIT 16H QUE J'SUIS DESSUS ALED
                    return;
                }
            }
            tourMdj(tour);
            tour++;
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

    public void tourMdj(int tour){
        Affichage.afficherTour(tour,this.m_map, m_MdjActions);
        int numaction = RecupInfos.scanInt();
        if (numaction> 3 || numaction <= 0){
            Affichage.afficherErreur("Vous devez saisir un nombre entre 1 et 3");
            tourMdj(tour);
        }
        else {
            if (numaction == 1){
                m_map.placerObstacle();
            }
            else if (numaction == 2){
                Affichage.afficher("Saisissez les coordonnées de l'entité a attaquer");
                int [] coords = RecupInfos.scanCoord(m_map);

                //attaquer un monstre / un personnage
            }
            else {
                m_map.placerTresor();
            }
        }
    }



}
