package gameContent.personnages;

public abstract class Caracteristique {
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_initiative;

    public Caracteristique(int pv, int force, int dexterite, int initiative) {
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_initiative = initiative;
    }

    public int getPv() {
        return m_pv;
    }

    public int getForce() {
        return m_force;
    }

    public int getDexterite() {
        return m_dexterite;
    }

    public int getInitiative() {
        return m_initiative;
    }
}
