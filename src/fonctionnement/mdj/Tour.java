package mdj;

import affichage.Affichage;
import gameContent.personnages.perso.Personnage;

import static affichage.Affichage.*;

public class Tour {
    private int m_tour;
    private int m_nbActions;
    private Personnage m_pers;
    private String[] m_actions = new String[3]; // Actions possibles pour le personnage
    private boolean m_finTour; // Indique si le tour est terminé

    public Tour(Personnage pers, int tour){
        this.m_pers = pers;
        this.m_tour = tour;
        this.m_nbActions = 3; // Nombre d'actions par tour
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
        int numaction = Affichage.ScanInt();
        if (numaction == 5) {
            m_finTour = true;
            Affichage.afficher("Fin du tour pour " + m_pers.getNom());
            return;
        }
        if (numaction < 1 || numaction > m_actions.length) {
            Affichage.afficherErreur("Action invalide. Veuillez choisir une action valide.");
            return;
        }
        executerAction(action);
    }



}
