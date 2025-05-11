package personnages;

import java.util.List;

import armurerie.Armurerie;
import armurerie.Equipement;
import personnages.race.Race;

public class Personnage {
    protected String m_nom;
    protected Race m_race;
    protected String m_classe;
    protected Caractéritiques m_caracteristiques;
    protected List<Armurerie> m_inventaire;
    protected Equipement m_equipements;
}
