package fonctionnement.coordonnees;

import gameContent.personnages.monstre.Monstre;

public class CoordonneesMonstre extends Coordonnees{
    private Monstre m_monstre;

    public CoordonneesMonstre(int x ,int y) {
        super(x, y, new char[] {'X', ')', ' '});
    }
}
