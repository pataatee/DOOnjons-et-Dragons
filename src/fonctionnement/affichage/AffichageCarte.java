package fonctionnement.affichage;

public class AffichageCarte {
    // classe pour afficher ce qui doit être affiché lors de la création d'une map pas aléatoire
    public static void demanderObstacles() {
        System.out.println("Combien d'obstacles souhaitez-vous placer sur la carte?");
    }

    public static void demanderTresors() {
        System.out.println("Combien de trésors souhaitez-vous placer sur la carte ?");
    }

    public static void demanderMonstres() {
        System.out.println("Combien de monstres voulez-vous créer ?");
    }

    public static void CoordInvalide() {
        System.err.println("Erreur : Coordonnées invalides");
    }

    public static void caseOccupee() {
        System.err.println("Erreur : la case est déjà occupée");
    }
}
