package gameContent.items.armes;

import fonctionnement.de.De;

public class ArmeGuerre implements Arme {
    private final String m_nom;
    private final De m_degats;
    private final int m_portee;

    public ArmeGuerre(String nom, De de, int portee){
        this.m_nom = nom;
        this.m_degats = de;
        this.m_portee = portee;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    @Override
    public int getDegats() {
        return m_degats.lancer_de();
    }
    @Override
    public int getPortee() {
        return m_portee;
    }
}
