package mdj;

import gameContent.personnages.perso.Personnage;

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
    }



}
