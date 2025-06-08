package fonctionnement.affichage;

import fonctionnement.coordonnees.Coordonnees;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.monstre.Monstre;
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



    public static void afficherTour(int tour,int action, Personnage pers, Map map, Monstre[] monstres){
        afficher("---------------------------------------------------");
        afficher("                Tour n°" + tour);
        afficher("                Action n°"+action);
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
        afficher("");
        afficherAllMonstres(monstres);

        afficher("");
        afficherMap(map);
        afficher("");
        Tour truc = new Tour(pers);
        afficherActions(truc.getActions(), truc.getListActions());
    }

    private static void afficherAllMonstres(Monstre[] monstres) {
        afficher("Monstres :");
        for (Monstre monstre:monstres){
            char lettre = (char) (monstre.getY()+'A');
            afficher("Nom : " + monstre.getEspece().getNomEspece()+monstre.getEspece().getNum() + " | Pvs : "+monstre.getPvs() +" | Coordonnées : (" + (monstre.getX()+1) + "," + lettre + ")");
        }

    }

    public static void afficherTour(int tour, Map map, String[] actions){
        afficher("---------------------------------------------------");
        afficher("                Tour n°" + (tour+1));
        afficher("               le maître du jeu joue               ");
        afficher("---------------------------------------------------");
        afficher("");
        afficherMap(map);
        afficher("");
        afficherActions(actions);

    }

    public static void afficherInventaire(Personnage pers){
        String inventaire = "";
        for (Item item : pers.getInventaireItem()){
            inventaire += item.avecArticleIndefini() + " - ";
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

    public static void afficherActions(String[] actions) {
        afficher("Actions possibles : ");
        for (int i = 0; i < actions.length ; i++) {
            afficher((i + 1) + " - " + actions[i]);
        }
    }

    public static void afficherDetailsMonstres(Coordonnees[][] map) {
        System.out.println("Monstres : ");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Monstre m = map[i][j].getMonstre();
                if (m != null) {
                    String aAfficher = "\n\n" + m.toString();
                    System.out.println(aAfficher);
                }
            }
        }
    }

    public static void afficherMonstres(Coordonnees[][] map) {
        System.out.println("Monstres :\n");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Monstre m = map[i][j].getMonstre();
                if (m != null) {
                    String aAfficher = "Nom : " + m.getEspece().getNomEspece() + " | Coordonnées : (" + i + "," + j + ")";
                    System.out.println(aAfficher);
                }
            }
        }
    }


    public static void demanderQuiAttaquer() {
        System.out.println("Qui souhaitez vous attaquer ?");
    }

    public static void afficherArmes(Personnage pers){
        List<Arme> armes = pers.getInventaireArmes();
        Affichage.afficher("Armes disponibles pour " + pers.getNom() + " :");
        for (Arme item : armes) {
            Affichage.afficher(" - " + item.getNom());
        }
    }
    public static void plusDInfos() {
        System.out.println("Plus d'infos (+)");
    }

    public static void afficherLstItems(String[][] lst ){
        for (String[] ligne : lst) {
            if (ligne[0].equalsIgnoreCase("Arme")) {
                System.out.println("Type : "+ligne[0]+"\t\t\tNom : " + ligne[1]+"\t\tDégats : "+ligne[2]+"\t\tPortée : " + ligne[3]);
            } else if (ligne[0].equalsIgnoreCase("Armure")) {
                System.out.println("Type : " + ligne[0] + "\t\tNom : " + ligne[1] + "\t\tClasse d'armure : " + ligne[2]);
            }
        }
    }

    //TODO possibilite de selectionner un profil par defaut de monstre meme qd pas random
    //TODO modif euh pour que les co s'affichent correctement qd on montre les co des montres p ex
}