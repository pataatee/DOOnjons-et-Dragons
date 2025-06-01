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

    // methodes get

    public int getPvs() {
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


    // methodes modify

    public void modifyPvs(int pv) {
        this.m_pv = pv;
    }

    public void modifyForce(int force) {
        this.m_force = force;
    }

    public void modifyDexterite(int dex) {
        this.m_dexterite = dex;
    }

    public void modifyInitiative(int ini) {
        this.m_initiative = ini;
    }


    // methodes bonus

    public void bonusPvs(int bonus) {
        this.m_pv += bonus;
    }

    public void bonusForce(int bonus) {
        this.m_force += bonus;
    }

    public void bonusDexterite(int bonus) {
        this.m_dexterite += bonus;
    }

    public void bonusInitiative(int bonus) {
        this.m_initiative += bonus;
    }

}
