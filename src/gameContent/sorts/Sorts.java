package gameContent.sorts;

public abstract class Sorts {
    public static final int m_nbSorts = 3;
    public static final int m_guerison = 0;
    public static final int m_boogieWoogie = 1;
    public static final int m_armeMagique = 2;

    protected String nom;

    public Sorts(String nom) {
        this.nom = nom;
    }
    //L'utilisation d'un sort se fait au tour de jeu du personnage doté de ce sort. L'utilisation d'un sort compte comme une action.
    // Les personnages de classe Clerc peuvent lancer le sort Guérison. Les magiciens peuvent lancer n'importe quel sort.




}
