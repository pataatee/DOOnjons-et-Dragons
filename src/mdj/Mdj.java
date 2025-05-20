package mdj;

import gameContent.personnages.Personnage;

public class Mdj {
    private Map m_map;
    private int m_nbJoueurs;
    private int m_nbMonstres;
    private int m_nbTresors;
    //private Personnage[m_nbJoueurs] m_joueurs;

    public Mdj() {
        this.m_map = new Map();
        this.m_nbJoueurs = 0;
        this.m_nbMonstres = 0;
        this.m_nbTresors = 0;
    }
}
