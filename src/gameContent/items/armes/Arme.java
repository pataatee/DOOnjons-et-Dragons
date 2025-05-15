package gameContent.items.armes;

import gameContent.items.Armurerie;

public abstract class Arme implements Armurerie {
    private final String m_nom;
    private final int m_degats;
    private final int m_portée;
    public Arme (String nom, int attaque, int portee){
        this.m_nom = nom;
        this.m_degats = attaque;
        this.m_portée = portee;
    }
    public String getNom() {
        return m_nom;
    }

}
