package gameContent.items.armures;

public class ArmureLourde implements Armure {
    private final String m_nom;
    private int m_classeArmure;

    public ArmureLourde(String nom, int classeArmure) {
        this.m_nom = nom;
        this.m_classeArmure = classeArmure;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    @Override
    public int getClasseArmure() {
        return this.m_classeArmure;
    }
    public static final ArmureLourde Cotte_de_maille = new ArmureLourde("Cotte de maille", 11);
    public static final ArmureLourde Harnois = new ArmureLourde("Harnois", 12);
}
