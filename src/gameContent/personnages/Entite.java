package gameContent.personnages;

import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;

public abstract class Entite {
    //les attributs communs
    //enft y'en a pas


    // création d'une interface entité pour réunir les méthodes communes à Monstre et Personnage

    //methodes communes :
    public void seDeplacer(){

    }
    public abstract boolean attaquer(Entite entite);
//        //on utilise m_attaque
//        //perso touché -> pv-degats
//        //return true si on a touché, false sinon
//        //dit qqch de diff si oui ou nn a touché sa cible
//        int pvPerso = entite.getPvs();
//        pvPerso -= m_attaque.getDegats();
//        entite.getCaracteristiques().modifyPvs(pvPerso);
//
//        int pvCible = entite.getPvs();
//        //int degats = this.getDegats();
//        //il faut qu'un perso ait une attaque qui a des degats tt ça en fonction de l'arme utilisee + caracteristiques
//
//        return false;
//    }


    //getters abstract

    public abstract Caracteristique getCaracteristiques();
    public abstract int getPvs();
    public abstract int getForce();
    public abstract int getDexterite();
    public abstract int getInitiative();
    public abstract boolean estAttaquePar(Monstre monstre);
    public abstract boolean estAttaquePar(Personnage personnage);
}
