package gameContent.personnages.monstre;

import fonctionnement.affichage.AffichageCreateMonstre;

public class Espece {
    private String m_nomEspece;
    private int m_numero;

    public Espece(String espece, int num) {
        m_nomEspece = espece;
        m_numero = num;
    }

    public Espece(String espece) {
        m_nomEspece = espece;
    }

    public String getNomEspece() {
        return m_nomEspece;
    }

    public int getNum() {
        return m_numero;
    }

    public String toString() {
        return "Nom : " + this.getNomEspece() + " " + this.getNum();
    }

    //ESPECES PAR DEFAUT
    public static final Espece dragon = new Espece("Dragon");
    public static final Espece rat = new Espece("Rat");
    public static final Espece goblin = new Espece("Goblin");
    public static final Espece troll = new Espece("Troll");
    public static final Espece loupGarou = new Espece("Loup-garou");
    public static final Espece ogre = new Espece("Ogre");



}

//TODO modif pr pvr ecrire + --> +d'infos pr afficher ttes les infos sur les monstres
//TODO implémenter les trucs des montsres ds les map random
//TODO profils de monstres par défaut