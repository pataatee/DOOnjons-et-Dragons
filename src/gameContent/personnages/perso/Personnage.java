package gameContent.personnages.perso;

import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.Caracteristique;
import gameContent.personnages.Entite;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.classe.Classe;
import gameContent.personnages.perso.race.Race;

import static fonctionnement.affichage.AfficherDsMonstre.afficherMonstreVaincu;
import static fonctionnement.affichage.AfficherDsMonstre.afficherPvRestantsMonstre;

public class Personnage extends Entite {
    private String m_nom;
    private Race m_race;
    private Classe m_classe;
    private CaracteristiquePersonnage m_caracteristiques;
    private Armurerie m_inventaire = new Armurerie();
    private Equipement m_equipements = new Equipement();
    private AttaquePersonnage m_attaque;

    public Personnage(String nom, Race race, Classe classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
        this.setCaracteristiques(this.m_race.getM_caracteristiques());
        this.setInventaire(this.m_classe.getArmurerie());
        this.m_caracteristiques.bonusPvs(this.m_classe.getM_Pvs());
    }

    public void setEquipement_Arme(Arme arme){
        int vitessebonus = this.m_equipements.setArme(arme);
        this.m_caracteristiques.bonusVitesse(vitessebonus);
    }

    public void setEquipement_Armure(Armure armure){
        int vitessebonus = this.m_equipements.setArmure(armure);
        this.m_caracteristiques.bonusVitesse(vitessebonus);
    }

    public void setInventaire(Armurerie inventaire) {
        this.m_inventaire = inventaire;
    }

    public Armurerie getInventaire(){
        return this.m_inventaire;
    }

    public void setCaracteristiques(CaracteristiquePersonnage caracteristiques){
        this.m_caracteristiques = caracteristiques;

    }
    public CaracteristiquePersonnage getCaracteristiquesPerso(){
        return this.m_caracteristiques;
    }

    public int getPvs(){
        return this.m_caracteristiques.getPvs(); //TODO faire le reste
    }

    @Override
    public int getForce() {
        return m_caracteristiques.getForce();
    }

    @Override
    public int getDexterite() {
        return m_caracteristiques.getDexterite();
    }

    @Override
    public int getInitiative() {
        return m_caracteristiques.getInitiative();
    }



    public String getNom() {
        return this.m_nom;
    }

    public Arme getArme_equipee() {
        return this.m_equipements.getM_arme();
    }
    public Armure getArmure_equipee() {
        return this.m_equipements.getM_armure();
    }

    public int getDegats() {
        return this.m_attaque.getDegats();
    }

    @Override
    public boolean estAttaquePar(Monstre monstre) {
        return false; // un monstre ne peut pas etre attaqué par un monstre (et on utilise cette methode sur la cible, donc un monstre)
    }

    @Override
    public boolean estAttaquePar(Personnage agresseur) {
        if (agresseur == null) {
            return false;
        }
        int pvCible = this.getPvs();
        pvCible -= agresseur.getDegats();
        this.getCaracteristiques().modifyPvs(pvCible);
        if (pvCible <= 0) {
            afficherMonstreVaincu();
        } else {
            afficherPvRestantsMonstre(pvCible);
        }
        return true;
    }


    @Override
    public boolean attaquer(Entite cible) {
        if (cible == null) {
            return false;
        }
        return cible.estAttaquePar(this); // true si attaque reussie, false sinon
    }

    //TODO euh revoir les bails de caractéristiques partout help
    @Override
    public CaracteristiqueMonstre getCaracteristiques() {
        return getCaracteristiques();
    }

}
