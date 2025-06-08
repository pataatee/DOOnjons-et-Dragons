
package fonctionnement.coordonnees;

import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;

public abstract class Coordonnees {
    private int m_x;
    private int m_y;
    private char[] m_affichage = new char[3];

    public Coordonnees(int x, int y, char[] affichage) {
        this.m_x = x;
        this.m_y = y;
        this.m_affichage = affichage;
    }

    public int getX() {
        return m_x;
    }
    public void seX(int m_x) {
        this.m_x = m_x;
    }
    public int getY() {
        return m_y;
    }
    public void setY(int m_y) {
        this.m_y = m_y;
    }
    public String getAffichage(){
        return new String(m_affichage);
    }

    public String toString() {
        return "x : " + m_x + " ; y : " + m_y + " ; affichage : " + getAffichage();
    }

    public Monstre getMonstre() {
        return null;
    }
    public String getCaseVide(){return null;}
    public Personnage getPersonnage() {
        return null;
    }
}
