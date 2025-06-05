package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;
import gameContent.items.armures.ArmureLourde;

public class Guerrier implements Classe {

    private String m_nom;
    private Armurerie m_armurerie;
    private int m_pvs = 0;


    public Guerrier() {
        this.m_nom = "Guerrier";
        this.m_armurerie = new Armurerie();
        this.m_pvs = 20;
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeGuerre.Epee_longue);
        arm.addArmes(ArmeDistance.Arbalete_legere);
        arm.addArmures(ArmureLourde.Cotte_de_maille);
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

