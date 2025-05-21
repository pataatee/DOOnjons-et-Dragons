package gameContent.personnages.monstre;
import gameContent.*;
import gameContent.personnages.Caracteristique;
import gameContent.personnages.CaracteristiqueMonstre;
import gameContent.personnages.Personnage;

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
        this("dragon", numero, caracteristique, attaque);
    }

    public Monstre(int numero, CaracteristiqueMonstre caracteristique) {
        this(numero, caracteristique, new Attaque("rafale de feu", 5, 50));
    }

    public boolean attaquerPersonnage(Personnage perso) {
        //on utilise m_attaque
        //perso touché -> pv-degats
        //return true si on a touché, false sinon
        //dit qqch de diff si oui ou nn a touché sa cible
        int pvPerso = perso.getCaracteriques().getPv();
        pvPerso -= m_attaque.getDegats();
        perso.getCaracteriques().modifyPvs(pvPerso);
        return true;
    }

    public void seDeplacer() {
        //
    }
}
