package gameContent.items.armes;


import fonctionnement.de.De;

public class ArmeDistance implements Arme{
    private final String m_nom;
    private final De m_degats;
    private final int m_portee;

    public ArmeDistance(String nom, De de, int portee){
        this.m_nom = nom;
        this.m_degats = de;
        this.m_portee = portee;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    @Override
    public int getDegats() {
        return m_degats.lancer_de();
    }
    @Override
    public int getPortee() {
        return m_portee;
    }
    public static final ArmeDistance Fronde = new ArmeDistance("Fronde", new De(1,4), 5);
    public static final ArmeDistance Arbalete_legere = new ArmeDistance("Arbalète légère", new De(1,4), 10);
    public static final ArmeDistance Arc_court = new ArmeDistance("Arc court", new De(1,4), 9);
}