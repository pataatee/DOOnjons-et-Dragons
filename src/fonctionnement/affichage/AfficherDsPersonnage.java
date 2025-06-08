package fonctionnement.affichage;

public class AfficherDsPersonnage {
    public static void afficherPvRestantsPerso (int pvRestants) {
        System.out.println("Il reste " + pvRestants + "points de vie au personnage."); // modif w nom du perso
    }

    public static void afficherPersonnageVaincu() {
        System.out.println("Le personnage a été vaincu !"); // à modifier avec nom du perso ig
    }

    public static void afficherErreurPortee() {
        System.err.println("Cible hors de portée !");
    }

    public static void afficherPersoVaincu() {
        System.out.println("Le personnage a été vaincu !");
    }
}
