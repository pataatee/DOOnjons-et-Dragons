package fonctionnement.coordonnees;

import gameContent.personnages.perso.Personnage;

public class CoordonneesPersonnage extends Coordonnees{
    private Personnage m_personnage;

    public CoordonneesPersonnage(int x ,int y, Personnage personnage) {
        super(x,y,personnage.getNom().substring(0, 3).toCharArray());
        this.m_personnage = personnage;
    }
   }
