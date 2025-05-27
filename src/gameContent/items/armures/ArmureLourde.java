package gameContent.items.armures;

public class ArmureLourde implements Armure {
    private final String m_nom;

    public ArmureLourde(String nom) {
        this.m_nom = nom;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
}
