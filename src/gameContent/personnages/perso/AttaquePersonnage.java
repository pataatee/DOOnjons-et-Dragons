package gameContent.personnages.perso;

import de.De;
import gameContent.personnages.Attaque;

public class AttaquePersonnage implements Attaque {
    private int m_degats;

    public AttaquePersonnage(int degats) {
        m_degats = degats;
    }
    public int getDegats() {
        return m_degats;
    }

    public void setDegats() {
        De de = new De(20, 1);
        
    }
}
