package gameContent.items.armes;

import fonctionnement.de.De;
import fonctionnement.grammaire.*;

public class ArmeGuerre implements Arme {
    private final String m_nom;
    private final De m_degats;
    private final int m_portee;
    private int m_bonus = 0; // Par défaut, l'arme n'a pas de bonus
    private Substantif m_genre;

    public ArmeGuerre(String nom, De de, int portee, Substantif genre){
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
        return m_degats.lancer_de()+ m_bonus;
    }
    @Override
    public int getPortee() {
        return m_portee;
    }
    @Override
    public void addBonus() {
        this.m_bonus += 1; // Permet de définir si l'arme a un bonus ou non
    }
    @Override
    public String avecArticleDefini(){
        return m_genre.avecArticleDefini();
    }
    @Override
    public String avecArticleIndefini(){
        return m_genre.avecArticleIndefini();
    }
    @Override
    public String avecArticlePartitif(){
        return m_genre.avecArticleIndefini();
    }
    public static final ArmeGuerre Epee_longue = new ArmeGuerre("épée longue", new De(1,4), 2, new Substantif("épée longue", new Feminin()));
    public static final ArmeGuerre Rapiere = new ArmeGuerre("Rapière", new De(1,4), 1, new Substantif("épée longue", new Feminin()));
    public static final ArmeGuerre eppe2Mains = new ArmeGuerre("épée à deux mains",  new De (2,6), 1, new Substantif("épée longue", new Feminin()));
}
