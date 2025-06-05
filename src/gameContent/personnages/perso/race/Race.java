package gameContent.personnages.perso.race;

import fonctionnement.de.De;
import gameContent.personnages.perso.CaracteristiquePersonnage;

public abstract class Race {
    private String m_nom; //TODO mettre du private et initialiser via super()
    private CaracteristiquePersonnage m_caracteristiques;

    public Race(String nom, int pvs, int force, int dexterite, int initiative, int vitesse){
        m_nom = nom;
        De de = new De(4,4);
        m_caracteristiques = new CaracteristiquePersonnage(pvs+3, force+3+de.lancer_de(), dexterite+3+de.lancer_de(), initiative+3+de.lancer_de(), vitesse+3+de.lancer_de());
    }

    public CaracteristiquePersonnage getM_caracteristiques() {
        return m_caracteristiques;
    }
    public String getNom() {
        return m_nom;
    }
}
