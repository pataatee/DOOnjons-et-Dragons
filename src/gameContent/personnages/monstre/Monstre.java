package gameContent.personnages.monstre;
import gameContent.*;
import gameContent.personnages.Caracteristique;
import gameContent.personnages.CaracteristiqueMonstre;

public class Monstre {
    private String m_espece;
    private int m_numero;
    private Caracteristique m_caracteristique;
    private Attaque m_attaque;

    public Monstre(String espece, int numero, CaracteristiqueMonstre caracteristique, Attaque attaque) {
        m_espece = espece;
        m_numero = numero;
        m_caracteristique = caracteristique;
        m_attaque = attaque;
    }

    public Monstre(int numero, CaracteristiqueMonstre caracteristique, Attaque attaque) {
        m_espece = "dragon";
        m_numero = numero;
        m_caracteristique = caracteristique;
        m_attaque = attaque;
    }

    public Monstre(int numero, CaracteristiqueMonstre caracteristique) {
        m_espece = "dragon";
        m_numero = numero;
        m_caracteristique = caracteristique;
        Attaque attaque = new Attaque("rafale de feu", 5, 50); //degats a modif bcs c sense etre selon les des
        m_attaque = attaque;
    }

    public boolean attaquerPersonnage() {
        //on utilise m_attaque
        //perso touché -> pv-degats
        //return true si on a touché, false sinon
        //dit qqch de diff si oui ou nn a touché sa cible
        return false;
    }

    public void seDeplacer() {
        //
    }
}
