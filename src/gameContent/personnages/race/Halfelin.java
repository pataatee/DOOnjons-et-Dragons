package gameContent.personnages.race;

import gameContent.personnages.Caracteristiques;
import gameContent.personnages.Personnage;

public class Halfelin extends Race{
    public Halfelin() {
        this.m_nom = "Halfelin";
        this.m_caracteristiques.setM_dexterite(4);
        this.m_caracteristiques.setM_vitesse(2);
    }

    public String getNom() {
        return m_nom;
    }
}
