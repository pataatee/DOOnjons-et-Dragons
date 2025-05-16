package gameContent.personnages;

import java.util.ArrayList;
import java.util.List;

import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.personnages.classe.Classe;
import gameContent.personnages.race.Race;

public class Personnage {
    protected String m_nom;
    protected Race m_race;
    protected Classe m_classe;
    protected Caracteristiques m_caracteristiques;
    protected Armurerie m_inventaire;
    protected Equipement m_equipements;

    public Personnage(String nom, Race race, Classe classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
        race.SetM_pers(this);
        race.getM_pers().setM_caracteristiques(this.m_caracteristiques);
        classe.SetM_personnage(this);
        //TODO s'occuper de Classe, y'a un pb avec les pvs et les caractéritiques
    }
    public String getM_nom(){
        return m_nom;
    }

    public void setM_inventaire(List<Armurerie> inventaire) {
        this.m_inventaire = inventaire;
    }

    public List<Armurerie> getM_inventaire(){
        return this.m_inventaire;
    }

    public void setM_caracteristiques(Caracteristiques caracteristiques){
        this.m_caracteristiques = caracteristiques;

    }
    public Caracteristiques getM_caracteristiques(){
        return this.m_caracteristiques;
    }
}
