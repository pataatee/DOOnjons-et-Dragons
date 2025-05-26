package gameContent.personnages.monstre;
import gameContent.personnages.Caracteristique;
import gameContent.personnages.CaracteristiqueMonstre;
import gameContent.personnages.Entite;
import gameContent.personnages.Personnage;

public class Monstre extends Entite {
    private Espece m_espece;
    private Caracteristique m_caracteristique;
    private Attaque m_attaque;

    public Monstre(Espece espece, CaracteristiqueMonstre caracteristique, Attaque attaque) {
        m_espece = espece;
        m_caracteristique = caracteristique;
        m_attaque = attaque;
    }

    public Monstre(CaracteristiqueMonstre caracteristique, Attaque attaque) {
        this(new Espece("dragon", 1), caracteristique, attaque);
    }

    public Monstre(CaracteristiqueMonstre caracteristique) {
        this(caracteristique, new Attaque("rafale de feu", 5, 50));
    }

    @Override
    public boolean attaquer(Entite entite) {
        //on utilise m_attaque
        //perso touché -> pv-degats
        //return true si on a touché, false sinon
        //dit qqch de diff si oui ou nn a touché sa cible
        int pvPerso = entite.getPvs();
        pvPerso -= m_attaque.getDegats();
        entite.getCaracteristiques().modifyPvs(pvPerso);
        return true;
    }

    @Override
    public Caracteristique getCaracteristiques() {
        return null;
    }

    @Override
    public int getPvs() {
        return 0;
    }

    @Override
    public void seDeplacer() {
        //
    }
}
