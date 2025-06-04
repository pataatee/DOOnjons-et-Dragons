package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.*;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.perso.Personnage;

import static fonctionnement.affichage.Affichage.*;

public class Tour {
    private int m_tour;
    private int m_nbActions;
    private Personnage m_pers;
    private final String[] m_actions; // Actions possibles pour le personnage
    private boolean m_finTour; // Indique si le tour est terminé
    private Map m_map; // La carte du jeu, si nécessaire

    public Tour(){
        this.m_actions = new String[]{
                "Attaquer <Monstre>",
                "S'équiper <Objet>",
                "Se déplacer <Case>",
                "Obtenir des informations <Case>",
                "Finir le tour"
        };
    }
    public Tour(Personnage pers, int tour, Map map) {
        this.m_pers = pers;
        this.m_tour = tour+1;
        this.m_nbActions = 3; // Nombre d'actions par tour
        this.m_map = map; // Initialisation de la carte
        this.m_actions = new String[]{
            "Attaquer <Monstre>",
            "S'équiper <Objet>",
            "Se déplacer <Case>",
            "Obtenir des informations <Case>",
            "Finir le tour"
        };
        this.m_finTour = false;
        while (!m_finTour && m_nbActions > 0) {
            jouerTour();

        }
    }
    public void jouerTour(){
        Affichage.afficherTour(this.m_tour, this.m_pers, this.m_map);
        int numaction = Affichage.ScanInt();
        // Vérification que ce qu'a saisi l'utilisateur est un entier type int
        /*if (!(numaction type of int)) {
            Affichage.afficherErreur("Veuillez entrer un nombre valide pour l'action.");
            jouerTour();
            return;
        }*/
        if (numaction == 5) {
            m_finTour = true;
            Affichage.afficher("Fin du tour pour " + m_pers.getNom());
            return;
        }
        if (numaction < 1 || numaction > m_actions.length) {
            Affichage.afficherErreur("Action invalide. Veuillez choisir une action valide.");
            jouerTour();
        }
        else {
            String action = m_actions[numaction - 1];
            if (numaction == 2){
                afficherInventaire(this.m_pers);
                choisirEquipement();

            }
            if (numaction == 3){
                seDeplacer();
            }
            Affichage.afficher("Action choisie : " + action);
            m_nbActions--;
        }

    }
    public String [] getActions(){
        return this.m_actions;
    }

    public void choisirEquipement(){
        Affichage.afficher("de quel objet voulez-vous vous équiper ?");
        String objet = Affichage.ScanString();
        boolean objetTrouve = false;
        for (Item item : this.m_pers.getInventaire().getObjets()) {
            if (item.getNom().equals(objet)) {
                if (item instanceof Arme){
                    if (this.m_pers.getArme_equipee() != null) {
                        Affichage.afficher("Vous avez déjà une arme équipée. Voulez-vous la remplacer ? (O/N)");
                        String reponse = Affichage.ScanString();
                        if (!reponse.equals("0")) {
                            this.m_pers.getInventaire().addArmes(this.m_pers.getArme_equipee());
                            this.m_pers.setEquipement_Arme((Arme)item);
                            this.m_pers.getInventaire().deleteArme((Arme)item);
                        }
                    }
                    else{
                        this.m_pers.setEquipement_Arme((Arme)item);
                        this.m_pers.getInventaire().deleteArme((Arme)item);
                    }
                }
                else {
                    if (this.m_pers.getArmure_equipee() != null) {
                        Affichage.afficher("Vous avez déjà une armure équipée. Voulez-vous la remplacer ? (O/N)");
                        String reponse = Affichage.ScanString();
                        if (!reponse.equals("0")) {
                            this.m_pers.getInventaire().addArmures(this.m_pers.getArmure_equipee());
                            this.m_pers.setEquipement_Armure((Armure)item);
                            this.m_pers.getInventaire().deleteArmure((Armure)item);
                        }
                    }
                    else{
                        this.m_pers.setEquipement_Armure((Armure)item);
                        this.m_pers.getInventaire().deleteArmure((Armure)item);
                    }

                }
                Affichage.afficher("Vous vous êtes équipé de : " + item.getNom());
                objetTrouve = true;
                break;
            }
        }

        if (!objetTrouve) {
            Affichage.afficherErreur("Objet introuvable dans l'inventaire. Veuillez réessayer.");
            choisirEquipement();
        }


    }

    public void seDeplacer(){
        Affichage.afficher("Où voulez-vous vous déplacer ? (coordonée x)");
        int x = Affichage.ScanInt() - 1;
        char caractere = demandeCaractere();
        int y = caractere - 'A';

        int x_pers = this.m_pers.getX();
        int y_pers = this.m_pers.getY();
        CoordonneesPersonnage posActuelle = new CoordonneesPersonnage(x_pers, y_pers, this.m_pers);
        Coordonnees posVoulue = new CoordonneesCaseVide(x, y);

        if (!Deplacement(posActuelle, posVoulue)) {
            seDeplacer();
        }
        else {
            this.m_map.setCase(x, y, posActuelle); // On met à jour la position sur la carte
            this.m_map.setCase(x_pers, y_pers, new CoordonneesCaseVide(x_pers, y_pers)); // On vide l'ancienne position
            m_pers.setPosition(x, y); // on met à jour la position du personnage
            Affichage.afficherMap(m_map);
        }
    }

    public boolean Deplacement(Coordonnees posActuelle, Coordonnees posVoulue){
        if (posVoulue instanceof CoordonneesMonstre || posVoulue instanceof CoordonneesObstacle){
            Affichage.afficherErreur("vous ne pouvez pas vous déplacer sur une case occupée par un monstre ou un obstacle.");
            return false;
        }
        int vitesse = m_pers.getVitesse()/3;
        //distance = racine carre((x1 - x2)2 + (y1 - y2)2)
        int distance = (int) Math.sqrt(Math.pow(posActuelle.getX() - posVoulue.getX(), 2) + Math.pow(posActuelle.getY() - posVoulue.getY(), 2));
        if (distance > vitesse){
            Affichage.afficherErreur("Vous ne pouvez pas vous déplacer aussi loin, votre vitesse est de " + vitesse + ".");
            return false;
        }
        return true;
    }

    public char demandeCaractere(){
        char lettreMax = (char)('A' + this.m_map.getM_largeur() - 1);
        String lettre = Affichage.ScanString().toUpperCase();
        if (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
            Affichage.afficher("Veuillez entrer une lettre pour la coordonnée y (a/A, b/B, c/C, ...): ");
            return demandeCaractere(); // Redemande si l'entrée n'est pas valide
        }
        // Convertit la lettre en un entier correspondant à la coordonnée y
        char caractere = lettre.charAt(0);
        if (caractere < 'A' || caractere > lettreMax) {
            Affichage.afficherErreur("Veuillez entre une lettre comprise entre A et " + lettreMax + ".");
            return demandeCaractere(); // Redemande si la lettre n'est pas dans l'intervalle
        }
        return caractere; // Retourne le caractère valide
    }




}
