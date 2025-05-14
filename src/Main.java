import gameContent.personnages.Personnage;
import gameContent.personnages.race.*;
import gameContent.personnages.classe.*;

public class Main {
    public static void main(String args[]) {
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Personnage pers1 = new Personnage("bidule", new Halfelin(), new Magicien());
        System.out.println(pers1.getM_nom());
    }
}