package fonctionnement.coordonnees;

import gameContent.personnages.monstre.Monstre;

public class CoordonneesMonstre extends Coordonnees{
    private Monstre m_monstre;

    public CoordonneesMonstre(int x ,int y, Monstre monstre) {
        super(x, y, new char[] {'X', ')', ' '});
        this.m_monstre = monstre;
    }

    public Monstre getMonstre() {
        return m_monstre;
    }
}
