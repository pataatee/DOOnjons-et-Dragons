package fonctionnement.affichage;

public class AffichageCarte {
    // classe pour afficher ce qui doit être affiché lors de la création d'une map pas aléatoire
    public static void demanderObstacles() {
        System.out.println("Combien d'obstacles souhaitez-vous placer sur la carte?\n");
    }

    public static void demanderTresors() {
        System.out.println("Combien de trésors souhaitez-vous placer sur la carte ?\n");
    }

    public static void demanderMonstres() {
        System.out.println("Combien de monstres voulez-vous créer ?\n");
    }

    public static void demanderCoordonneeX() {
        System.out.println("Coordonnées de l'élément à placer (chiffre) : \n");
    }

    public static void demanderCoordonneeY() {
        System.out.println("Coordonnées de l'élément à placer (lettre) : \n");
    }

    public static void xInvalide() {
        System.err.println("Erreur : Coordonnée X invalide\n");
    }

    public static void yInvalide() {
        System.err.println("Erreur : coordonnée Y invalide\n");
    }

    public static void caseOccupee() {
        System.err.println("Erreur : la case est déjà occupée\n");
    }
}
