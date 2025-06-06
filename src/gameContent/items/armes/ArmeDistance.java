package gameContent.items.armes;


import fonctionnement.de.De;
import fonctionnement.grammaire.Feminin;
import fonctionnement.grammaire.Masculin;
import fonctionnement.grammaire.Substantif;

public class ArmeDistance implements Arme{
    private final String m_nom;
    private final De m_degats;
    private final int m_portee;
    private int m_bonus = 0; // Par défaut, l'arme n'a pas de bonus
    private Substantif m_genre;

    public ArmeDistance(String nom, De de, int portee, Substantif genre){
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
    public static final ArmeDistance Fronde = new ArmeDistance("Fronde", new De(1,4), 6, new Substantif("épée longue", new Feminin()));
    public static final ArmeDistance Arbalete_legere = new ArmeDistance("Arbalète légère", new De(1,8), 16, new Substantif("épée longue", new Feminin()));
    public static final ArmeDistance Arc_court = new ArmeDistance("Arc court", new De(1,6), 16, new Substantif("baton", new Masculin()));
}