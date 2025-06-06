package fonctionnement.affichage;

import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.perso.Personnage;
import fonctionnement.mdj.Map;
import fonctionnement.mdj.Tour;

import java.util.Dictionary;
import java.util.List;
import java.util.Scanner;

public class Affichage {

    public static void afficher(String message) {
        System.out.println(message);
    }

    public static void afficherErreur(String message) {
        System.err.println("Erreur : " + message);
    }

    public static void afficherMap(Map map) {
        afficherLigneLettreMap(map.getM_largeur());
        afficheLigneMap(map.getM_largeur());

        for (int i = 0; i< map.getM_longueur();i++){
            if (i<9){
                System.out.print(((i+1)+"  "));
            }
            else{
                System.out.print(i+1+" ");
            }
            System.out.print("| ");
            for (int j = 0; j< map.getM_largeur();j++){
                System.out.print(map.getM_carte()[i][j].getAffichage());
            }
            System.out.print(" |");
            System.out.println();
        }
        afficheLigneMap(map.getM_largeur());
    }
    public static void afficheLigneMap(int largeur){
        System.out.print("   *-");
        for (int i = 0; i< largeur;i++) {
            System.out.print("---");
        }
        System.out.print("-*");
        System.out.println();
    }
    public static void afficherLigneLettreMap(int largeur){
        System.out.print("      ");
        for (int i = 65; i <= (64+largeur); i++) {
            System.out.print((char) i + "  ");
        }
        System.out.println();
    }

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
            afficherErreur("Entrée invalide. Veuillez saisir un nombre entier.");
            return scanInt(); // Redemande l'entrée si ce n'est pas un entier
        }
    }

    public static void afficherTour(int tour, Personnage pers, Map map){
        afficher("---------------------------------------------------");
        afficher("                Tour n°" + tour);
        afficher("             Personnage : " + pers.getNom());
        afficher("---------------------------------------------------");
        afficher("");
        afficher("Vie : " +pers.getPvs() +" / " + pers.getPvsMax());
        afficher("Force : " + pers.getForce());
        afficher("Dexterité : " + pers.getDexterite());
        afficher("Initiative : " + pers.getInitiative());
        afficher("Vitesse : " + pers.getVitesse()/3);

        if (pers.getArme_equipee()!= null){
            afficher("Arme : " + pers.getArme_equipee().getNom());
        }
        else {
            afficher("Arme : Non Equipé");
        }

        if (pers.getArmure_equipee() != null){
            afficher("Armure : " + pers.getArmure_equipee().getNom());
        }
        else {
            afficher("Armure : Non Equipé");
        }


        afficherInventaire(pers);


        afficher("");
        afficherMap(map);
        afficher("");
        Tour truc = new Tour(pers);
        afficherActions(truc.getActions(), truc.getListActions());
    }

    public static void afficherInventaire(Personnage pers){
        String inventaire = "";
        for (Arme arme : pers.getInventaire().getArmes()){ //TODO a modif car pas droit a double getteur
            inventaire += arme.getNom() + " - ";
        }
        for (Armure armure : pers.getInventaire().getArmures()){ //TODO a modif car pas droit a double getteur
            inventaire += armure.getNom() + " - ";
        }
        // enlever le dernier tiret
        if (inventaire.length() > 0) {
            inventaire = inventaire.substring(0, inventaire.length() - 3);
        }
        else {
            inventaire = "Vide";
        }
        afficher("Inventaire : " + inventaire);
    }

    public static void afficherActions(List<String> actions, int nombreActions) {
        afficher("Actions possibles : ");
        for (int i = 0; i < nombreActions ; i++) {
            afficher((i + 1) + " - " + actions.get(i));
        }
    }
    public static int[] scanCoord(Map map){
        afficher("Entrez les coordonnées (ligne, colonne) séparées par une virgule (ex: 12,C) :");
        String input = scanString();
        String[] bidule = input.split(",");
        if (bidule.length != 2) {
            afficherErreur("Format invalide. Veuillez entrer un entier et un caractère séparés par une virgule.");
            return scanCoord(map); // Redemande l'entrée si le format est incorrect
        }
        try {
            int x = Integer.parseInt(bidule[0].trim()) - 1; // Convertit en entier et ajuste pour l'indexation
            int y = Character.toUpperCase(bidule[1].trim().charAt(0)) - 'A'; // Convertit le caractère en entier (A=0, B=1, etc.)
            if (x < 0 || x >= map.getM_longueur() || y < 0 || y >= map.getM_largeur()) {
                afficherErreur("Coordonnées en dehors des limites de la carte. Veuillez réessayer.");
                return scanCoord(map); // Redemande l'entrée si les coordonnées sont hors limites
            }
            return new int[]{x, y};
        }
        catch (NumberFormatException e) {
            afficherErreur("Entrée invalide. Veuillez saisir des nombres entiers.");
            return scanCoord(map); // Redemande l'entrée si ce n'est pas un entier
        }


    }

    public static char scanOuiNon(){
        // la phrase doit etre écrite avant car ce n'est pas une phrase commune
        String reponse = Affichage.scanString().toUpperCase();
        if (reponse.equals("O") || reponse.equals("OUI")) {
            return 'O';
        }
        else if (reponse.equals("N") || reponse.equals("NON")) {
            return 'N';
        }
        else {
            afficherErreur("Réponse invalide. Veuillez répondre par 'O' pour oui ou 'N' pour non.");
            return scanOuiNon(); // Redemande l'entrée si ce n'est pas 'O' ou 'N'
        }
    }
}