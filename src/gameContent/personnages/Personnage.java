package gameContent.personnages;

import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.classe.Classe;
import gameContent.personnages.race.Race;

public class Personnage extends Entite {
    protected String m_nom;
    protected Race m_race;
    protected Classe m_classe;
    protected CaracteristiquePersonnage m_caracteristiques;
    protected Armurerie m_inventaire = new Armurerie();
    protected Equipement m_equipements = new Equipement();

    public Personnage(String nom, Race race, Classe classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
        this.setCaracteristiques(this.m_race.getM_caracteristiques());
        this.setInventaire(this.m_classe.getM_armurerie());
        this.m_caracteristiques.bonusPvs(this.m_classe.getM_Pvs());
    }
    public String getM_nom(){
        return m_nom;
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

    public int getForce() {
        return m_caracteristiques.getForce();
    }

    public int getDexterite() {
        return m_caracteristiques.getDexterite();
    }

    public int getInitiative() {
        return m_caracteristiques.getInitiative();
    }

    public CaracteristiquePersonnage getCaracteristiques(){
        return m_caracteristiques;
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

    @Override
    public void seDeplacer() {

    }

    @Override
    public boolean attaquer(Entite entite) {
        return false;
    }
}
