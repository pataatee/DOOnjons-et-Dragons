import gameContent.items.Armurerie;
import gameContent.personnages.Personnage;
import gameContent.personnages.race.*;
import gameContent.personnages.classe.*;

public class Main {
    public static void main(String args[]) {
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Personnage pers1 = new Personnage("bidule", new Halfelin(), new Magicien());
        /*System.out.println(pers1.getM_nom());
        Armurerie truc = pers1.getM_inventaire();
        for (int i = 0; i < truc.getM_armes().size(); i++) {
            System.out.println(truc.getM_armes().get(i).getNom());
        }
        for (int i = 0; i < truc.getM_armures().size(); i++) {
            System.out.println(truc.getM_armures().get(i).getNom());
        }
        System.out.println(pers1.getM_caracteristiques().getM_dexterite());
        System.out.println(pers1.getM_caracteristiques().getM_pvs());*/
    }
}