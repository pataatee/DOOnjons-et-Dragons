package gameContent.personnages;

public class Caracteristiques {
    private Personnage m_pers;
    private int m_pvs = 0;
    private int m_force = 0;
    private int m_dexterite = 0;
    private int m_vitesse= 0;
    private int m_initiative=0;
    public Caracteristiques(Personnage pers){
        this.m_pers = pers;
        M_pers_set_Caracteristiques();
    }
    public Caracteristiques(){}

    public int getM_pvs() {
        return m_pvs;
    }

    public int getM_force() {
        return m_force;
    }

    public int getM_dexterite() {
        return m_dexterite;
    }

    public int getM_vitesse() {
        return m_vitesse;
    }

    public int getM_initiative() {
        return m_initiative;
    }

    public void setM_pvs(int m_pvs) {
        this.m_pvs += m_pvs;
    }

    public void setM_force(int m_force) {
        this.m_force += m_force;
    }

    public void setM_dexterite(int m_dexterite) {
        this.m_dexterite += m_dexterite;
    }

    public void setM_initiative(int m_initiative) {
        this.m_initiative += m_initiative;
    }

    public void setM_vitesse(int m_vitesse) {
        this.m_vitesse += m_vitesse;
    }

    public void M_pers_set_Caracteristiques(){
        this.m_pers.setM_caracteristiques(this);
    }
}
