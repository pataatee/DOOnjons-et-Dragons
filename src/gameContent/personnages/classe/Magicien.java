package gameContent.personnages.classe;

import gameContent.items.Armurerie;

public class Magicien extends Classe {
    public Magicien() {
        this.m_nom = "Magicien";
        this.m_pvs = 1;
        this.m_armurerie = new Armurerie("Baton", "Fronde");
    }
}
