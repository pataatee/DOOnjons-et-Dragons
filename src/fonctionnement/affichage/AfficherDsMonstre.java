package fonctionnement.affichage;

import gameContent.personnages.monstre.Monstre;

public class AfficherDsMonstre {
    public static void afficherMonstreVaincu() {
        System.out.println("Le monstre est vaincu !");
    }
    public static void afficherPvRestantsMonstre(int pvRestants) {
        System.out.println("Il reste " + pvRestants + " points de vie au monstre.");
    }

    public static void afficherAttaqueEchouee() {
        System.out.println("L'attaque a échoué !!");
    }

    public static void afficherErreurPortee() {
        System.err.println("Cible hors de portée.");
    }

    public static void afficherMonstre(Monstre m) {
        System.out.println(m.toString());
    }
}
