package gameContent.sorts;

import fonctionnement.affichage.Affichage;
import gameContent.items.armes.Arme;
import gameContent.personnages.perso.Personnage;

public class ArmeMagique extends Sorts{
    public ArmeMagique() {
        super("Arme Magique");
    }

    public static void lancer(Personnage pers, Arme arme){
        //Arme magique : le personnage détenteur du pouvoir peut choisir une arme détenue par un personnage (mais pas forcément équipée) à améliorer.
        // L'arme gagne alors un bonus de 1 lors des jets d'attaque et de 1 lors des jets de dégâts (les bonus peuvent se cumuler).
        //TODO : a ajouter 1 lors du jet d'attaque
        boolean armeTrouvee = false;
        for (Arme a : pers.getInventaireArmes()) {
            if (a.getNom().equals(arme.getNom())) {
                a.addBonus();
                armeTrouvee = true;
                Affichage.afficher(arme.avecArticleDefini()+ " a été améliorée !");
            }
        }
        if (!armeTrouvee){
            Arme armeequipee = pers.getArme_equipee();
            armeequipee.addBonus();
            Affichage.afficher(armeequipee.avecArticleDefini()+ " a été améliorée !");
        }
    }
}