import fonctionnement.mdj.Mdj;

public class Main {
    public static void main(String args[]) {
        System.out.println("Bienvenue dans DOOnjon et Dragons");

        Mdj mdj = new Mdj();
        while(!mdj.verify_morts()){
            mdj.donjon();
        }
    }
}