package armurerie;

public class Equipement {

    private Arme m_arme;
    private Armure m_armure;


    public void Equipement(Arme arme, Armure armure){
        this.m_arme = arme;
        this.m_armure = armure;
    }

    public Arme getM_arme() {
        return m_arme;
    }

    public Armure getM_armure() {
        return m_armure;
    }

    public void setM_arme(Arme m_arme) {
        this.m_arme = m_arme;
    }

    public void setM_armure(Armure m_armure) {
        this.m_armure = m_armure;
    }
}
