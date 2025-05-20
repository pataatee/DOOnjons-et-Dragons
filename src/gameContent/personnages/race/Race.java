package gameContent.personnages.race;

import gameContent.personnages.Caracteristiques;
import gameContent.personnages.Personnage;

public abstract class Race {
    protected String m_nom;
    protected Caracteristiques m_caracteristiques = new Caracteristiques();

    public Caracteristiques getM_caracteristiques() {
        return m_caracteristiques;
    }
    public String getNom() {
        return m_nom;
    }
}
