import gameContent.items.Armurerie;
import gameContent.personnages.Personnage;
import gameContent.personnages.race.*;
import gameContent.personnages.classe.*;
import mdj.Map;

public class Main {
    public static void main(String args[]) {
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        /*Personnage pers1 = new Personnage("bidule", new Halfelin(), new Magicien());
        System.out.println(pers1.getM_nom());
        Armurerie truc = pers1.getM_inventaire();
        for (int i = 0; i < truc.getM_armes().size(); i++) {
            System.out.println(truc.getM_armes().get(i).getNom());
        }
        for (int i = 0; i < truc.getM_armures().size(); i++) {
            System.out.println(truc.getM_armures().get(i).getNom());
        }
        System.out.println(pers1.getM_caracteristiques().getM_dexterite());
        System.out.println(pers1.getM_caracteristiques().getM_pvs());*/

        Map map = new Map();
        affichage.Affichage.afficherMap(map);
        Map map2 = new Map(15,20);
        affichage.Affichage.afficherMap(map2);
        Map map3 = new Map(10,12);
        affichage.Affichage.afficherMap(map3);
    }
}