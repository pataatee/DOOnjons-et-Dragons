package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;

public class Magicien implements Classe {

    private String m_nom;
    private Armurerie m_armurerie;
    private int m_pvs = 0;

    public Magicien() {
        this.m_nom = "Magicien";
        this.m_armurerie = new Armurerie();
        this.m_pvs = 12;
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeCourante.Baton);
        arm.addArmes(ArmeCourante.Baton);;
        return arm;
    }

    @Override
    public int getPvs() {
        return this.m_pvs;
    }

    @Override
    public String getNom() {
        return this.m_nom;
    }

    @Override
    public Armurerie getArmurerie() {
        return this.m_armurerie;
    }
}
