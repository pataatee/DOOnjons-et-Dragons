package gameContent.personnages.race;

import gameContent.personnages.Caracteristiques;
import gameContent.personnages.Personnage;

public abstract class Race {
    private String m_nom; //TODO mettre du private et initialiser via super()
    private Caracteristiques m_caracteristiques = new Caracteristiques();

    Race(String nom, int pvs, int force, int dexterite, int vitesse, int initiative){
        m_nom = nom;
        m_caracteristiques.setM_pvs(pvs);
        m_caracteristiques.setM_force(force);
        m_caracteristiques.setM_dexterite(dexterite);
        m_caracteristiques.setM_vitesse(vitesse);
        m_caracteristiques.setM_initiative(initiative);
    }

    public Caracteristiques getM_caracteristiques() {
        return m_caracteristiques;
    }
    public String getNom() {
        return m_nom;
    }
}
