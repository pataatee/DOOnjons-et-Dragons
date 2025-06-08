package gameContent.sorts;


import fonctionnement.de.De;
import gameContent.personnages.perso.Personnage;

public class Guerison extends Sorts{
    public Guerison() {
        super("Guérison");
    }

    public static void lancer(Personnage pers){
        /*
    le personnage détenteur du sort peut choisir un personnage (y compris lui-même)
    et lance 1d10 pour connaître le nombre de points de vie que le personnage visé regagnera.
    Le personnage soigné ne peut pas dépasser le nombre de points de vie qu'il avait à sa création.
        */
        De de = new De(1,10);
        int pointsDeVieRecuperes = de.lancer_de();
        int pointsDeVieActuels = pers.getPvs();
        int pointsDeVieMax = pers.getPvsMax();
        if (pointsDeVieActuels + pointsDeVieRecuperes > pointsDeVieMax) {
            pers.setPvs(pointsDeVieMax);
        }
        else {
            pers.setPvs(pointsDeVieActuels + pointsDeVieRecuperes);
        }
    }
}
