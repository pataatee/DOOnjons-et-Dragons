package gameContent.items.armures;

import fonctionnement.grammaire.Feminin;
import fonctionnement.grammaire.Substantif;

public class ArmureLegere implements Armure {
    private final String m_nom;
    private int m_classeArmure;
    private Substantif m_genre;

    public ArmureLegere(String nom, int classeArmure, Substantif genre) {
        this.m_nom = nom;
        this.m_classeArmure = classeArmure;
        this.m_genre = genre;
    }
    @Override
    public String getNom() {
        return m_nom;
    }
    @Override
    public int getClasseArmure() {
        return this.m_classeArmure;
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
    public static final ArmureLegere Ecailles = new ArmureLegere("Armure d'écailles", 9, new Substantif("épée longue", new Feminin()));
    public static final ArmureLegere Demi_plates = new ArmureLegere("Demi-plates", 10, new Substantif("épée longue", new Feminin()));
}
