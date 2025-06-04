package gameContent.personnages;

import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;

public abstract class Entite {

    // création d'une interface entité pour réunir les méthodes communes à Monstre et Personnage

    //methodes communes :
    public void seDeplacer(){

    }
    public abstract boolean attaquer(Entite entite);

    //getters abstract

    public abstract CaracteristiqueMonstre getCaracteristiques();

    //public abstract Caracteristique getCaracteristiques();
    public abstract int getPvs();
    public abstract int getForce();
    public abstract int getDexterite();
    public abstract int getInitiative();
    public abstract boolean estAttaquePar(Monstre monstre);
    public abstract boolean estAttaquePar(Personnage personnage);
}
