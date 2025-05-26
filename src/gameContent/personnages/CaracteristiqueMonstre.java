package gameContent.personnages;

public class CaracteristiqueMonstre extends Caracteristique {
    private int m_classeArmure;

    public CaracteristiqueMonstre(int pv, int force, int dexterite, int initiative, int classeArmure) {
        super(pv, force, dexterite, initiative);
        m_classeArmure = classeArmure;
    }

    public int getClasseArmure() {
        return m_classeArmure;
    }
}
