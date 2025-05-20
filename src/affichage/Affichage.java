package affichage;

import mdj.Map;

public class Affichage {

    public static void afficher(String message) {
        System.out.println(message);
    }

    public static void afficherErreur(String message) {
        System.err.println("Erreur : " + message);
    }

    public static void afficherMap(Map map) {
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
                if (map.getM_carte()[i][j]==0){
                    System.out.print(" . ");
                }
                else{
                    System.out.print(map.getM_carte()[i][j]);
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
}
