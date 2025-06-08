package gameContent.sorts;

import fonctionnement.coordonnees.CoordonneesMonstre;
import fonctionnement.coordonnees.CoordonneesPersonnage;
import fonctionnement.mdj.Map;
import gameContent.personnages.Entite;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;

public class BoogieWoogie extends Sorts{
    public BoogieWoogie() {
        super("Boogie-Woogie");

    }

    public static void lancer(Entite perso1, Entite perso2, Map map) {
    int x1 = perso1.getX();
    int y1 = perso1.getY();
    int x2 = perso2.getX();
    int y2 = perso2.getY();

    // Mise à jour de la map et des positions internes
        if  (perso1 instanceof Personnage && perso2 instanceof Monstre) {
        map.setCase(x1, y1, new CoordonneesMonstre(x1, y1, (Monstre) perso2));
        map.setCase(x2, y2, new CoordonneesPersonnage(x2, y2, (Personnage) perso1));
        perso1.setPosition(x2, y2);
        perso2.setPosition(x1, y1);
    }
        else if (perso1 instanceof Monstre && perso2 instanceof Personnage) {
        map.setCase(x1, y1, new CoordonneesPersonnage(x1, y1, (Personnage) perso2));
        map.setCase(x2, y2, new CoordonneesMonstre(x2, y2, (Monstre) perso1));
        perso1.setPosition(x2, y2);
        perso2.setPosition(x1, y1);
    }
        else if (perso1 instanceof Personnage && perso2 instanceof Personnage) {
        map.setCase(x1, y1, new CoordonneesPersonnage(x1, y1, (Personnage) perso2));
        map.setCase(x2, y2, new CoordonneesPersonnage(x2, y2, (Personnage) perso1));
        perso1.setPosition(x2, y2);
        perso2.setPosition(x1, y1);
    }
        else if (perso1 instanceof Monstre && perso2 instanceof Monstre) {
        map.setCase(x1, y1, new CoordonneesMonstre(x1, y1, (Monstre) perso2));
        map.setCase(x2, y2, new CoordonneesMonstre(x2, y2, (Monstre) perso1));
        perso1.setPosition(x2, y2);
        perso2.setPosition(x1, y1);
    }
        else {
        perso1.setPosition(x2, y2);
        perso2.setPosition(x1, y1);
    }

}
}