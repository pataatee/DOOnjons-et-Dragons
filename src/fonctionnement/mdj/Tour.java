package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.Coordonnees;
import fonctionnement.coordonnees.CoordonneesCaseVide;
import fonctionnement.coordonnees.CoordonneesMonstre;
import fonctionnement.coordonnees.CoordonneesObstacle;
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
                            this.m_pers.getInventaire().addM_armes(this.m_pers.getArme_equipee());
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
                            this.m_pers.getInventaire().addM_armures(this.m_pers.getArmure_equipee());
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

    public boolean seDeplacerPersonnage(Coordonnees posActuelle, Coordonnees posVoulue){
        if (posVoulue instanceof CoordonneesMonstre || posVoulue instanceof CoordonneesObstacle){
            Affichage.afficherErreur("vous ne pouvez pas vous déplacer sur une case occupée par un monstre ou un obstacle.");
            return false;
        }
        int vitesse = m_pers.getVitesse/3;
        //distance = racine carre((x1 - x2)2 + (y1 - y2)2)
        int distance = (int) Math.sqrt(Math.pow(posActuelle.getX() - posVoulue.getX(), 2) + Math.pow(posActuelle.getY() - posVoulue.getY(), 2));
        if (distance > vitesse){
            Affichage.afficherErreur("Vous ne pouvez pas vous déplacer aussi loin, votre vitesse est de " + vitesse + ".");
            return false;
        }
        Coordonnees temp = posActuelle;
        posActuelle = new CoordonneesCaseVide(temp.getX(), temp.getY()); // on crée une nouvelle case vide à la position actuelle
        posVoulue = temp ;
        return true;
    }




}
