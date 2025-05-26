package gameContent.personnages;

public abstract class Entite {
    //les attributs communs
    //enft y'en a pas


    // création d'une interface entité pour réunir les méthodes communes à Monstre et Personnage

    //methodes communes :
    public void seDeplacer(){

    }
    public boolean attaquer(Entite entite) {
        return false;
    }


    //getters abstract

    public abstract Caracteristique getCaracteristiques();
    public abstract int getPvs();
    public abstract int getForce();
    public abstract int getDexterite();
    public abstract int getInitiative();


}
