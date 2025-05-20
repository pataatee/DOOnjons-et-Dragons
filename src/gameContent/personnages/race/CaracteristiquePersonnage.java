package gameContent.personnages.race;

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
}
