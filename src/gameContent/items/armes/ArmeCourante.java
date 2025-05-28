package gameContent.items.armes;

public class ArmeCourante implements Arme{
    private final String m_nom;
    private final int m_degats;
    private final int m_portee;

    public ArmeCourante(String nom, int attaque, int portee){
        this.m_nom = nom;
        this.m_degats = attaque; // TODO modifier tout ça pour que les dégats de arme cac varient en fonction de force
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
    public static final ArmeCourante Baton = new ArmeCourante("Baton", 2, 2);
    public static final ArmeCourante Rapiere = new ArmeCourante("Rapière", 3, 1);
    public static final ArmeCourante Epee_longue = new ArmeCourante("épée longue", 4, 2);
    public static final ArmeCourante Masse_d_armes = new ArmeCourante("Masse d'armes", 4, 1);
}


