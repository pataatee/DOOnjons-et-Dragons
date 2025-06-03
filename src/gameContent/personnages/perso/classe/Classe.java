package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;

public abstract class Classe {
    private String m_nom;
    private Armurerie m_armurerie = new Armurerie();
    private int m_pvs = 0;

    public int getM_Pvs() {
        return this.m_pvs;
    }
    public Armurerie getArmurerie() {
        return m_armurerie;
    }
    public String getNom() {
        return m_nom;
    }

    public Classe(String nom, Armurerie armurerie, int pv) {
        this.m_nom = nom;
        this.m_armurerie = armurerie;
        this.m_pvs = pv;
    }

    public abstract Armurerie modifyArmurerie();
}
