package gameContent.items.armures;

public class ArmureLegere implements Armure {
    private final String m_nom;
    private int m_classeArmure;

    public ArmureLegere(String nom, int classeArmure) {
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
    public static final ArmureLegere Ecailles = new ArmureLegere("Armure d'écailles", 9);
    public static final ArmureLegere Demi_plates = new ArmureLegere("Demi-plates", 10);
}
