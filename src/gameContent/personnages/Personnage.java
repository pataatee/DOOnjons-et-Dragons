package gameContent.personnages;

import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.personnages.classe.Classe;
import gameContent.personnages.race.Race;

public class Personnage {
    protected String m_nom;
    protected Race m_race;
    protected Classe m_classe;
    protected Caracteristiques m_caracteristiques;
    protected Armurerie m_inventaire = new Armurerie();
    protected Equipement m_equipements;

    public Personnage(String nom, Race race, Classe classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
        this.setM_caracteristiques(this.m_race.getM_caracteristiques());
        this.setM_inventaire(this.m_classe.getM_armurerie());
        this.m_caracteristiques.setM_pvs(this.m_classe.getM_Pvs());
    }
    public String getM_nom(){
        return m_nom;
    }

    public void setM_inventaire(Armurerie inventaire) {
        this.m_inventaire = inventaire;
    }

    public Armurerie getM_inventaire(){
        return this.m_inventaire;
    }

    public void setM_caracteristiques(Caracteristiques caracteristiques){
        this.m_caracteristiques = caracteristiques;

    }
    public Caracteristiques getM_caracteristiques(){
        return this.m_caracteristiques;
    }
}
