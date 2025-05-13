package gameContent.personnages;

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
    protected List<Armurerie> m_inventaire;
    protected Equipement m_equipements;
}
