package gameContent.items.armures;

public class ArmureLegere implements Armure {
    private final String m_nom;

    public ArmureLegere(String nom) {
        this.m_nom = nom;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    public static final ArmureLegere Ecailles = new ArmureLegere("Armure d'écailles");
    public static final ArmureLegere Cotte_de_maille = new ArmureLegere("Cotte de maille");
}
