package gameContent.items.armes;

public class ArmeDistance implements Arme{
    private final String m_nom;
    private final int m_degats;
    private final int m_portee;

    public ArmeDistance(String nom, int attaque, int portee){
        this.m_nom = nom;
        this.m_degats = attaque; // TODO modifier tout ça pour que les degats d'arme distance varient en fonction de la dextérité
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
    public static final ArmeDistance Fronde = new ArmeDistance("Fronde", 3, 5);
    public static final ArmeDistance Arbalete_legere = new ArmeDistance("Arbalète légère", 4, 10);
    public static final ArmeDistance Arc_court = new ArmeDistance("Arc court", 3, 9);
}