package gameContent.personnages;

import gameContent.personnages.classe.Classe;
import gameContent.personnages.race.Race;

public class Personnage {
    protected String m_nom;
    protected Race m_race;
    protected Classe m_classe;
    protected CaracteristiquePersonnage m_caracteristiques;

    public CaracteristiquePersonnage getCaracteriques() {
        return m_caracteristiques;
    }

    public int getPv() {
        return m_caracteristiques.getPv();
    }

    public int getForce() {
        return m_caracteristiques.getForce();
    }

    public int getDexterite() {
        return m_caracteristiques.getDexterite();
    }

    public int getInitiative() {
        return m_caracteristiques.getInitiative();
    }
}
