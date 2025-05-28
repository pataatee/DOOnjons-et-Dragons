package fonctionnement.affichage;

import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.perso.Personnage;
import fonctionnement.mdj.Map;

import java.util.Scanner;

public class Affichage {

    public static void afficher(String message) {
        System.out.println(message);
    }

    public static void afficherErreur(String message) {
        System.err.println("Erreur : " + message);
    }

    public static void afficherMap(Map map) {
        afficherlignelettremap(map.getM_largeur());
        affichelignemap(map.getM_largeur());

        for (int i = 0; i< map.getM_longueur();i++){
            if (i<9){
                System.out.print(((i+1)+"  "));
            }
            else{
                System.out.print(i+1+" ");
            }
            System.out.print("| ");
            for (int j = 0; j< map.getM_largeur();j++){
                if (map.getM_carte()[i][j]==0){ // Case vide
                    System.out.print(" . ");
                }
                else if (map.getM_carte()[i][j]==-1){ // Case mur
                    System.out.print("[ ]");
                }
                else if (map.getM_carte()[i][j] == 1){ // case Monstre
                    System.out.print(" X(");
                }
                else if (map.getM_carte()[i][j] == 2){ //case Trésor
                    System.out.print(" * ");
                }
                else{
                    System.out.print(" "+ map.getM_carte()[i][j]+ " ");
                }

            }
            System.out.print(" |");
            System.out.println();
        }
        affichelignemap(map.getM_largeur());
    }
    public static void affichelignemap(int largeur){
        System.out.print("   *-");
        for (int i = 0; i< largeur;i++) {
            System.out.print("---");
        }
        System.out.print("-*");
        System.out.println();
    }
    public static void afficherlignelettremap(int largeur){
        System.out.print("      ");
        for (int i = 65; i <= (64+largeur); i++) {
            System.out.print((char) i + "  ");
        }
        System.out.println();
    }

    public static String ScanString() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    public static int ScanInt() {
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static void afficherTour(int tour, Personnage pers, Map map){
        afficher("---------------------------------------------------");
        afficher("                Tour n°" + tour);
        afficher("             Personnage : " + pers.getNom());
        afficher("---------------------------------------------------");
        afficher("");
        afficher("Vie : " +pers.getPvs()); //TODO creer un maxPVS, on a zappé et on en a besoin

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

        String inventaire = "";
        for (Arme arme : pers.getInventaire().getM_armes()){ //TODO a modif car pas droit a double getteur
            inventaire += arme.getNom() + " - ";
        }
        for (Armure armure : pers.getInventaire().getM_armures()){ //TODO a modif car pas droit a double getteur
            inventaire += armure.getNom() + " - ";
        }
        afficher("Inventaire : " + inventaire);

        afficher("");
        afficherMap(map);
    }

    public void afficherActions(String actions[]) {
        afficher("Actions possibles : ");
        for (int i = 0; i < actions.length; i++) {
            afficher((i + 1) + " - " + actions[i]);
        }
    }
}