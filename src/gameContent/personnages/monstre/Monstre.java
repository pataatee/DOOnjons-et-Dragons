package gameContent.personnages.monstre;
import fonctionnement.affichage.AfficherDsMonstre;
import fonctionnement.de.De;
import gameContent.items.armes.Arme;
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
        return m_caracteristique.getPvs();
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

        if (agresseur == null || agresseur.getArme_equipee() == null) {
            return false;
        }

        System.out.println("[DEBUG] Monstre est attaqué par perso " + agresseur.getNom());

        Arme armePerso = agresseur.getArme_equipee();

        // verif la range
        int range = Math.abs(this.getX() - agresseur.getX()) + Math.abs(this.getY() - agresseur.getY());
        if (range > armePerso.getPortee()) {
            AfficherDsMonstre.afficherErreurPortee();
            return false;
        }

        // jet d'attaque
        De de = new De(1, 20);
        int jetAtk = de.lancer_de();

        // bonus d'attak
        if (armePerso.getPortee() == 1) {
            jetAtk += agresseur.getForce();
        }
        else if (armePerso.getPortee() > 1) {
            jetAtk += agresseur.getDexterite();
        }

        int classeArmure = this.getClasseArmure();

        if (jetAtk > classeArmure) {
            int degats = armePerso.getDegats();
            int pvRestants = this.getPvs() - degats;


            System.out.println("[DEBUG] PV avant = " + (pvRestants + degats));
            System.out.println("[DEBUG] Dégâts infligés = " + degats);
            System.out.println("[DEBUG] PV après = " + pvRestants);


            pvRestants = (Math.max(0, pvRestants));
            this.m_caracteristique.modifyPvs(pvRestants);
            AfficherDsMonstre.afficherPvRestantsMonstre(pvRestants);

            if (pvRestants <= 0) {
                AfficherDsMonstre.afficherMonstreVaincu();
            }

            System.out.println("[DEBUG] PV avant = " + (pvRestants + degats));
            System.out.println("[DEBUG] Dégâts infligés = " + degats);
            System.out.println("[DEBUG] PV après = " + pvRestants);

        }
        else {
            AfficherDsMonstre.afficherAttaqueEchouee();
        }



        return true;
    }

    public int getClasseArmure() {
        return this.m_caracteristique.getClasseArmure();
    }
}
