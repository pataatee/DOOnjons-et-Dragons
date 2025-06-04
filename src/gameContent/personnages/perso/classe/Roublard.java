package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;

public class Roublard implements Classe {
    private String m_nom;
    private Armurerie m_armurerie;
    private int m_pvs = 0;

    public Roublard() {
        this.m_nom = "Roublard";
        this.m_armurerie = new Armurerie();
        this.m_pvs = 16;
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeCourante.Rapiere);
        arm.addArmes(ArmeDistance.Arc_court);;
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
