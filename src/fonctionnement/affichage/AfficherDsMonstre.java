package fonctionnement.affichage;

import gameContent.personnages.monstre.AttaqueMonstre;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Espece;
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


    public static void possibilitesMonstre() {
        System.out.println("Profils existants : \n");
        Monstre[] monstres = {
                Monstre.troll, Monstre.rat, Monstre.goblin, Monstre.dragon, Monstre.loupGarou
        };

        for (Monstre monstre : monstres) {
            System.out.println(monstre.toStringSansCo());
            System.out.println();
        }
    }

    public static void possibilitesEspece() {
        System.out.println("Espèces préféfinies : \n");
        Espece[] especes = {
            Espece.dragon,
            Espece.goblin,
            Espece.troll,
            Espece.ogre,
            Espece.loupGarou,
            Espece.rat
        };

        for (Espece e : especes) {
            System.out.println(e);
            System.out.println();
        }
    }

    public static void possibilitesAttaque() {
        System.out.println("Attaques préféfinies : \n");
        AttaqueMonstre[] atks = {
                AttaqueMonstre.Attaque_furtive, AttaqueMonstre.Boule_de_feu, AttaqueMonstre.Coup_de_tete, AttaqueMonstre.Griffure_dechirante,
                AttaqueMonstre.Croc_empoisonne, AttaqueMonstre.Poison
        };

        for (AttaqueMonstre a : atks) {
            System.out.println(a);
            System.out.println();
        }
    }

    public static void possibilitesCarac() {
        System.out.println("Caractéristiques prédéfinies : \n");
        Object[][] caracs = {
                {"carDragon", CaracteristiqueMonstre.carDragon},
                {"c1", CaracteristiqueMonstre.c1},
                {"carRat", CaracteristiqueMonstre.carRat},
                {"c2", CaracteristiqueMonstre.c2},
                {"c3", CaracteristiqueMonstre.c3},
                {"c4", CaracteristiqueMonstre.c4},
                {"c5", CaracteristiqueMonstre.c5}
        };

        for (Object[] c : caracs) {
            String nom = (String) c[0];
            CaracteristiqueMonstre ca = (CaracteristiqueMonstre) c[1];
            System.out.println(nom + " : \n" + ca);
            System.out.println();
        }
    }
}
