package gameContent.items.armures;

import fonctionnement.grammaire.Feminin;
import fonctionnement.grammaire.Masculin;
import fonctionnement.grammaire.Substantif;

public class ArmureLourde implements Armure {
    private final String m_nom;
    private int m_classeArmure;
    private Substantif m_genre;

    public ArmureLourde(String nom, int classeArmure, Substantif genre) {
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
    public static final ArmureLourde Cotte_de_maille = new ArmureLourde("Cotte de maille", 11, new Substantif("épée longue", new Feminin()));
    public static final ArmureLourde Harnois = new ArmureLourde("Harnois", 12, new Substantif("baton", new Masculin()));
}
