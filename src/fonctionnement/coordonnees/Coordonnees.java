package fonctionnement.coordonnees;

public abstract class Coordonnees {
    private int m_x;
    private int m_y;
    private char[] m_affichage = new char[3];

    public Coordonnees(int x, int y, char[] affichage) {
        this.m_x = x;
        this.m_y = y;
        this.m_affichage = affichage;
    }

    public int get_x() {
        return m_x;
    }
    public void set_x(int m_x) {
        this.m_x = m_x;
    }
    public int get_y() {
        return m_y;
    }
    public void set_y(int m_y) {
        this.m_y = m_y;
    }
}
