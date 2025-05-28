package affichage;

public class AfficherDsPersonnage {
    public static void afficherPvRestants (int pvRestants) {
        System.out.println("Il reste " + pvRestants + "points de vie au personnage."); // modif w nom du perso
    }

    public static void afficherPersonnageVaincu() {
        System.out.println("Le personnage a été vaincu !"); // à modicier avec nom du perso ig
    }
}
