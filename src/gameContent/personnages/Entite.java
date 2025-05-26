package gameContent.personnages;

public abstract class Entite {
    //les attributs communs


    // création d'une interface entité pour réunir les méthodes communes à Monstre et Personnage

    //methodes communes :
    public void seDeplacer(){

    }
    public boolean attaquer(Entite entite) {
        return false;
    }

    public abstract Caracteristique getCaracteristiques();
    public abstract int getPvs();

    //getters


}
