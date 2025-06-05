package gameContent.sorts;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.Coordonnees;
import fonctionnement.coordonnees.CoordonneesMonstre;
import fonctionnement.coordonnees.CoordonneesPersonnage;
import fonctionnement.de.De;
import fonctionnement.mdj.Map;
import gameContent.personnages.Entite;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.classe.Classe;
import gameContent.personnages.perso.classe.Magicien;

import javax.swing.*;
import java.util.List;

public class Sorts {
    private boolean[] sorts = new boolean[]{false, false, false};

    public Sorts(Classe classe) {
        if (classe instanceof Magicien){
            sorts = new boolean[]{true, true, true}; // Le magicien peut lancer tous les sorts
        }
    }

    public boolean[] getSorts() {
        return sorts;
    }


    /*L'utilisation d'un sort se fait au tour de jeu du personnage doté de ce sort. L'utilisation d'un sort compte comme une action.
    Arme magique : le personnage détenteur du pouvoir peut choisir une arme détenue par un personnage (mais pas forcément équipée) à améliorer. L'arme gagne alors un bonus de 1 lors des jets d'attaque et de 1 lors des jets de dégâts (les bonus peuvent se cumuler). Les personnages de classe Clerc peuvent lancer le sort Guérison. Les magiciens peuvent lancer n'importe quel sort.
    */

    public static void Guerison(Personnage pers){
        /*
    le personnage détenteur du sort peut choisir un personnage (y compris lui-même)
    et lance 1d10 pour connaître le nombre de points de vie que le personnage visé regagnera.
    Le personnage soigné ne peut pas dépasser le nombre de points de vie qu'il avait à sa création.
        */
        De de = new De(1,10);
        int pointsDeVieRecuperes = de.lancer_de();
        int pointsDeVieActuels = pers.getPvs();
        int pointsDeVieMax = pers.getPvsMax();
        if (pointsDeVieActuels + pointsDeVieRecuperes > pointsDeVieMax) {
            pers.setPvs(pointsDeVieMax);
        }
        else {
            pers.setPvs(pointsDeVieActuels + pointsDeVieRecuperes);
        }
    }

    public static void BoogieWoogie(Entite perso1, Entite perso2, Map map) {
        /*
le personnage détenteur du sort peut choisir deux personnages (y compris lui-même),
2 monstres ou 1 personnage (y compris lui-même) et 1 monstre
et échanger leur position dans le donjon.
         */
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
        else {
            perso1.setPosition(x2, y2);
            perso2.setPosition(x1, y1);
        }

    }


}
