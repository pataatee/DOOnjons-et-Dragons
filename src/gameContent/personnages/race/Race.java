package gameContent.personnages.race;

import gameContent.personnages.CaracteristiquePersonnage;

public abstract class Race {
    private String m_nom; //TODO mettre du private et initialiser via super()
    private CaracteristiquePersonnage m_caracteristiques;

    Race(String nom, int pvs, int force, int dexterite, int vitesse, int initiative){
        m_nom = nom;
        m_caracteristiques = new CaracteristiquePersonnage(pvs, force, dexterite, vitesse, initiative);
    }

    public CaracteristiquePersonnage getM_caracteristiques() {
        return m_caracteristiques;
    }
    public String getNom() {
        return m_nom;
    }
}
