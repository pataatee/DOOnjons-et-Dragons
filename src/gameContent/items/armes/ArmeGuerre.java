package gameContent.items.armes;

public class ArmeGuerre implements Arme {
    private final String m_nom;
    private final int m_degats;
    private final int m_portee;

    public ArmeGuerre(String nom, int attaque, int portee){
        this.m_nom = nom;
        this.m_degats = attaque;
        this.m_portee = portee;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    @Override
    public int getDegats() {
        return m_degats;
    }
    @Override
    public int getPortee() {
        return m_portee;
    }
}
