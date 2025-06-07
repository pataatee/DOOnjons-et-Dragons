package gameContent.personnages.monstre;
import gameContent.personnages.*;
import gameContent.personnages.perso.Personnage;

import static fonctionnement.affichage.AfficherDsPersonnage.afficherPersonnageVaincu;
import static fonctionnement.affichage.AfficherDsPersonnage.afficherPvRestantsPerso;

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

    @Override
    public boolean attaquer(Entite cible) { // TODO : vérifier si l'attaque est possible (portée)
        if (cible == null) {
            return false;
        }
        return cible.estAttaquePar(this);
    }

    @Override
    public boolean estAttaquePar(Monstre agresseur) {
        return false;
    }

    @Override
    public boolean estAttaquePar(Personnage agresseur) {
        if (agresseur == null) {
            return false;
        }
        int pvCible = this.getPvs();
        pvCible -= agresseur.getDegats(); //TODO prendre en compte les pvs de l'armure (idem pour personnage)
        this.getCaracteristiques().modifyPvs(pvCible);
        if (pvCible <= 0) {
            afficherPersonnageVaincu();
        } else {
            afficherPvRestantsPerso(pvCible);
        }
        return true;
    }

    public int getClasseArmure() {
        return this.m_caracteristique.getClasseArmure();
    }
}
