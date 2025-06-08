package fonctionnement.utilisateur;

import fonctionnement.affichage.Affichage;
import fonctionnement.mdj.Map;

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

    public static int[] scanCoord(Map map){
        Affichage.afficher("Entrez les coordonnées (ligne, colonne) séparées par une virgule (ex: 12,C) :");
        String input = scanString();
        String[] bidule = input.split(",");
        if (bidule.length != 2) {
            Affichage.afficherErreur("Format invalide. Veuillez entrer un entier et un caractère séparés par une virgule.");
            return scanCoord(map); // Redemande l'entrée si le format est incorrect
        }
        try {
            int x = Integer.parseInt(bidule[0].trim()) - 1; // Convertit en entier et ajuste pour l'indexation
            int y = Character.toUpperCase(bidule[1].trim().charAt(0)) - 'A'; // Convertit le caractère en entier (A=0, B=1, etc.)
            if (x < 0 || x >= map.getM_longueur() || y < 0 || y >= map.getM_largeur()) {
                Affichage.afficherErreur("Coordonnées en dehors des limites de la carte. Veuillez réessayer.");
                return scanCoord(map); // Redemande l'entrée si les coordonnées sont hors limites
            }
            return new int[]{x, y};
        }
        catch (NumberFormatException e) {
            Affichage.afficherErreur("Entrée invalide. Veuillez saisir des nombres entiers.");
            return scanCoord(map); // Redemande l'entrée si ce n'est pas un entier
        }


    }

    public static char scanOuiNon(){
        // la phrase doit etre écrite avant car ce n'est pas une phrase commune
        String reponse = RecupInfos.scanString().toUpperCase();
        if (reponse.equals("O") || reponse.equals("OUI")) {
            return 'O';
        }
        else if (reponse.equals("N") || reponse.equals("NON")) {
            return 'N';
        }
        else {
            Affichage.afficherErreur("Réponse invalide. Veuillez répondre par 'O' pour oui ou 'N' pour non.");
            return scanOuiNon(); // Redemande l'entrée si ce n'est pas 'O' ou 'N'
        }
    }
}
