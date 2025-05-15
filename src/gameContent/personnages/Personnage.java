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
    protected List<Armurerie> m_inventaire = new ArrayList<>();
    protected Equipement m_equipements;

    public Personnage(String nom, Race race, Classe classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
    }
    public String getM_nom(){
        return m_nom;
    }

    public void setM_inventaire(List<Armurerie> m_inventaire) {
        this.m_inventaire = m_inventaire;
    }

    public List<Armurerie> getM_inventaire(){
        return this.m_inventaire;
    }
}
