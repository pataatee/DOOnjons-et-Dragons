package gameContent.items.armes;

public abstract class Arme {
    protected String m_nom;
    protected int m_degats;
    protected int m_portee;
    public Arme (String nom, int attaque, int portee){
        this.m_nom = nom;
        this.m_degats = attaque;
        this.m_portee = portee;
    }
    public String getNom() {
        return m_nom;
    }

}
