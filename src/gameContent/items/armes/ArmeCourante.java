package gameContent.items.armes;

import fonctionnement.de.De;
import fonctionnement.grammaire.*;

public class ArmeCourante implements Arme{
    private final String m_nom;
    private final De m_degats;
    private final int m_portee;
    private int m_bonus = 0; // Par défaut, l'arme n'a pas de bonus
    private Substantif m_genre;

    public ArmeCourante(String nom, De de, int portee, Substantif genre){
        this.m_nom = nom;
        this.m_degats = de;
        this.m_portee = portee;
        this.m_genre = genre;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    @Override
    public int getDegats() {
        return m_degats.lancer_de()+m_bonus;
    }
    @Override
    public int getPortee() {
        return m_portee;
    }
    @Override
    public void addBonus() {
        this.m_bonus += 1; // Permet de définir si l'arme a un bonus ou non
    }
    public static final ArmeCourante Baton = new ArmeCourante("Baton", new De(1,6), 1, new Substantif("baton", new Masculin()));
    public static final ArmeCourante Masse_d_armes = new ArmeCourante("Masse d'armes", new De(1,6), 1, new Substantif("masse d'armes", new Feminin()));
}


