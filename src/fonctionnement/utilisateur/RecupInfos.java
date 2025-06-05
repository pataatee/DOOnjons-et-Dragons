package fonctionnement.utilisateur;

import fonctionnement.affichage.Affichage;

import java.util.Scanner;

public class RecupInfos {

    // recup infos

    public static String scanString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static int scanInt() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        try {
            return Integer.parseInt(input.trim()); // Convertit seulement si c’est bien un entier
        } catch (NumberFormatException e) {
            Affichage.afficherErreur("Entrée invalide. Veuillez saisir un nombre entier.");
            return scanInt(); // Redemande l'entrée si ce n'est pas un entier
        }
    }
}
