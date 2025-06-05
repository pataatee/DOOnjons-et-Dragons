package fonctionnement.affichage;

import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.perso.Personnage;
import fonctionnement.mdj.Map;
import fonctionnement.mdj.Tour;

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


        afficherInventaire(pers);


        afficher("");
        afficherMap(map);
        afficher("");
        Tour truc = new Tour();
        afficherActions(truc.getActions());
    }

    public static void afficherInventaire(Personnage pers){
        String inventaire = "";
        for (Arme arme : pers.getInventaire().getArmes()){ //TODO a modif car pas droit a double getteur
            inventaire += arme.getNom() + " - ";
        }
        for (Armure armure : pers.getInventaire().getArmures()){ //TODO a modif car pas droit a double getteur
            inventaire += armure.getNom() + " - ";
        }
        afficher("Inventaire : " + inventaire);
    }

    public static void afficherActions(String actions[]) {
        afficher("Actions possibles : ");
        for (int i = 0; i < actions.length; i++) {
            afficher((i + 1) + " - " + actions[i]);
        }
    }
}