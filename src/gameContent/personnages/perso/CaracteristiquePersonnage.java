package gameContent.personnages.perso;

import gameContent.personnages.Caracteristique;

public class CaracteristiquePersonnage extends Caracteristique {
    private int m_vitesse;

    public CaracteristiquePersonnage(int pv, int force, int dexterite, int initiative, int vitesse) {
        super(pv, force, dexterite, initiative);
        m_vitesse = vitesse;
    }

    public int getVitesse() {
        return m_vitesse;
    }
    public void modifyVitesse(int vitesse) {
        this.m_vitesse = vitesse;
    }
    public void bonusVitesse(int bonus) {
        this.m_vitesse += bonus;
    }
}
