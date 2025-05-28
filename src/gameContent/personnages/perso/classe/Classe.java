package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.personnages.Personnage;

import java.util.ArrayList;
import java.util.List;

public abstract class Classe {
    protected String m_nom;
    protected Armurerie m_armurerie = new Armurerie();
    protected int m_pvs = 0;

    public int getM_Pvs() {
        return this.m_pvs;
    }
    public Armurerie getM_armurerie() {
        return m_armurerie;
    }
}
