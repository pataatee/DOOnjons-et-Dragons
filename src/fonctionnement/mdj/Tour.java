package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
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
        int numaction = Affichage.scanInt();
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
        String objet = Affichage.scanString();
        boolean objetTrouve = false;
        for (Item item : this.m_pers.getInventaire().getObjets()) {
            if (item.getNom().equals(objet)) {
                if (item instanceof Arme){
                    this.m_pers.setEquipement_Arme((Arme)item);;
                    this.m_pers.getInventaire().deleteArme((Arme)item);
                }
                else {
                    this.m_pers.setEquipement_Armure((Armure)item);
                    this.m_pers.getInventaire().deleteArmure((Armure)item);
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



}
