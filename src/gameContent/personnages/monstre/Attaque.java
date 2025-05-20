package gameContent.personnages.monstre;

public class Attaque {
    private String m_nom;
    private int m_portee;
    private int m_degats;

    public Attaque (String attaque, int portee, int degats) {
        m_nom = attaque;
        m_portee = portee;
        m_degats = degats;
    }

    public String getAttaque() {
        return m_nom;
    }

    public int getPortee() {
        return m_portee;
    }

    public int getDegats() {
        return m_degats;
    }

}
