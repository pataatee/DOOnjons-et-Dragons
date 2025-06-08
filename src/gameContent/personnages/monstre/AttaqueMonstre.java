package gameContent.personnages.monstre;

import fonctionnement.affichage.AffichageCreateMonstre;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.personnages.Attaque;

public class AttaqueMonstre implements Attaque {
    private String m_nom;
    private int m_portee;
    private int m_degats;

    public AttaqueMonstre(String attaque, int portee, int degats) {
        m_nom = attaque;
        m_portee = portee;
        m_degats = degats;
    }

    // TODO un par défaut...?

    public String getAttaque() {
        return m_nom;
    }

    public int getPortee() {
        return m_portee;
    }

    public int getDegats() {
        return m_degats;
    }

    public String toString() {
        return "Nom attaque : " + m_nom + "\nDégâts : " + m_degats + "\nPortée : " + m_portee;
    }

    //ATTAQUES PAR DEFAUT
    public static final AttaqueMonstre Boule_de_feu = new AttaqueMonstre("Boule de feu", 20, 13);
    public static final AttaqueMonstre Coup_de_tete = new AttaqueMonstre("Coup de tête", 1, 17);
    public static final AttaqueMonstre Griffure_dechirante = new AttaqueMonstre("Griffure déchirante", 2, 19);
    public static final AttaqueMonstre Croc_empoisonne = new AttaqueMonstre("Croc empoisonné", 1, 8);
    public static final AttaqueMonstre Attaque_furtive = new AttaqueMonstre("Attaque furtive", 30, 7);
    public static final AttaqueMonstre Poison = new AttaqueMonstre("Poison", 16, 9);

}
