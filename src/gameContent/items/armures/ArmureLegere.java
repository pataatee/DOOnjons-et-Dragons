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
}
