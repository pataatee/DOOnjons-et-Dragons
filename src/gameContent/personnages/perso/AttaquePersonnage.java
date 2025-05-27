package gameContent.personnages.perso;

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

    }
}
