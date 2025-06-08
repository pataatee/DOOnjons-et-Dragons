package fonctionnement.affichage;

public class AffichageCreateMonstre {
    public static void demanderEspece() {
        System.out.println("Espece de votre monstre : ");
    }

    public static void demanderAttaque() {
        System.out.println("Quel est le nom de l'attaque de votre monstre ?"); // TODO faire en sorte que ça affiche direct le nom du monstre hehe // rajouter attribut nom à monstre ??? rigolo
    }

    public static void demanderPorteeAttaque() {
        System.out.println("Quelle est la portée de cette attaque ?");
    }

    public static void demanderDegatsAttaque() {
        System.out.println("Combien de dégâts cette attaque peut-elle infliger ?");
    }

    public static void demanderPvMonstre() {
        System.out.println("PV du monstre ?");
    }

    public static void demanderForce() {
        System.out.println("Force du monstre ? \n0 : Très faible \n1 : Faible \n2 : Normal \n3 : Un peu fort \n4 : Fort \n5 : Très fort \n6 : SURPUISSANT");
    }

    public static void demanderDexterite() {
        System.out.println("Dextérité du monstre ?");
    }

    public static void demanderInitiative() {
        System.out.println("Initiative du monstre ?");
    }

    public static void demanderClasseArmure() {
        System.out.println("Classe d'armure du monstre ?");
    }

    public static void demanderVitesse() {
        System.out.println("Vitesse du monstre ?");
    }
}
