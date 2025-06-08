package fonctionnement.affichage;

import gameContent.personnages.monstre.AttaqueMonstre;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Espece;
import gameContent.personnages.monstre.Monstre;

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

    public static void creerMonstreExistantOuPas() {
        System.out.println("Voulez vous créer votre monstre à partir de profils existants ? ([+] Voir plus / Oui / Non");
    }



    public static void afficherMonstresPossibles() {
        System.out.println("Veuillez sélectionner un monstre parmi les monstres suivants : ");
        AfficherDsMonstre.possibilitesMonstre();
    }

    public static void afficherEspecesPossibles() {
        System.out.println("Veuillez sélectionner une espèce parmi les suivantes : ");
        AfficherDsMonstre.possibilitesEspece();
    }

    public static void afficherAttaquesPossibles() {
        System.out.println("Veuillez sélectionner une attaque parmi les suivantes : ");
        AfficherDsMonstre.possibilitesAttaque();
    }

    public static void afficherCaracPossibles() {
        System.out.println("Veuillez sélectionner des caractéristiques parmi les suivantes : ");
        AfficherDsMonstre.possibilitesCarac();
    }
}
