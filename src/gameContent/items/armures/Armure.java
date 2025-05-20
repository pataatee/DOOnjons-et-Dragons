package gameContent.items.armures;

import gameContent.items.armes.Arme;

public class Armure {
    protected String m_nom;
    public Armure (String nom){
        this.m_nom = nom;
    }
    public String getNom() {
        return m_nom;
    }
}
