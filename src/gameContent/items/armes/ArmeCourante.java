package gameContent.items.armes;

import fonctionnement.de.De;

public class ArmeCourante implements Arme{
    private final String m_nom;
    private final De m_degats;
    private final int m_portee;

    public ArmeCourante(String nom, De de, int portee){
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
    public static final ArmeCourante Baton = new ArmeCourante("Baton", new De(1,4), 2);
    public static final ArmeCourante Rapiere = new ArmeCourante("Rapière", new De(1,4), 1);
    public static final ArmeCourante Epee_longue = new ArmeCourante("épée longue", new De(1,4), 2);
    public static final ArmeCourante Masse_d_armes = new ArmeCourante("Masse d'armes", new De(1,4), 1);
}


