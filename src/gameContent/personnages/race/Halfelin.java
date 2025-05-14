package gameContent.personnages.race;

public class Halfelin extends Race{
    public Halfelin() {
        this.m_nom = "Halfelin";
        this.m_Force = 0;
        this.m_Dexterite = 4;
        this.m_Vitesse = 2;
        this.m_Initiative = 0;
        this.m_Pvs = 0;
    }

    public String getNom() {
        return m_nom;
    }

    public int getForce() {
        return m_Force;
    }

    public int getDexterite() {
        return m_Dexterite;
    }

    public int getVitesse() {
        return m_Vitesse;
    }

    public int getInitiative() {
        return m_Initiative;
    }

    public int getPvs() {
        return m_Pvs;
    }
}
