package gameContent.personnages.perso;

import fonctionnement.de.De;
import gameContent.personnages.Attaque;

public class AttaquePersonnage implements Attaque {
    private int m_degats;

    public AttaquePersonnage() {
        De de = new De(1, 20);
        int resultatDe = de.lancer_de();
        int degats = resultatDe;
        m_degats = degats;
    }
    public int getDegats() {
        return m_degats;
    }

    public void setDegats(Personnage perso) {
        // y'a surement pas besoin de ça au final
    }
}
