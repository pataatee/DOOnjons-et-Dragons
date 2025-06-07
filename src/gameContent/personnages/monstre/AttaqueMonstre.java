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



}
