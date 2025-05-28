package gameContent.personnages.monstre;
import gameContent.personnages.*;

public class Monstre extends Entite {
    private Espece m_espece;
    private CaracteristiqueMonstre m_caracteristique;
    private AttaqueMonstre m_attaque;

    public Monstre(Espece espece, CaracteristiqueMonstre caracteristique, AttaqueMonstre attaque) {
        m_espece = espece;
        m_caracteristique = caracteristique;
        m_attaque = attaque;
    }

    public Monstre(CaracteristiqueMonstre caracteristique, AttaqueMonstre attaque) {
        this(new Espece("dragon", 1), caracteristique, attaque);
    }

    public Monstre(CaracteristiqueMonstre caracteristique) {
        this(caracteristique, new AttaqueMonstre("rafale de feu", 5, 50));
    }


    @Override
    public CaracteristiqueMonstre getCaracteristiques() {
        return m_caracteristique;
    }

    @Override
    public int getPvs() {
        return 0;
    }

    @Override
    public int getForce() {
        return m_caracteristique.getForce();
    }

    @Override
    public int getDexterite() {
        return m_caracteristique.getDexterite();
    }

    @Override
    public int getInitiative() {
        return m_caracteristique.getInitiative();
    }

    public Espece getEspece() {
        return m_espece;
    }

    public AttaqueMonstre getAttaque() {
        return m_attaque;
    }

    public int getDegats() {
        return m_attaque.getDegats();
    }
}
