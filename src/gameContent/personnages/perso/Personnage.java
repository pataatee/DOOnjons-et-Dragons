package gameContent.personnages.perso;

import fonctionnement.affichage.Affichage;
import fonctionnement.affichage.AfficherDsMonstre;
import fonctionnement.affichage.AfficherDsPersonnage;
import fonctionnement.de.De;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.items.Armurerie;
import gameContent.items.Equipement;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.Entite;
import gameContent.personnages.monstre.AttaqueMonstre;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.classe.Classe;
import gameContent.personnages.perso.race.Race;
import gameContent.sorts.Sorts;

import java.util.List;
import java.util.Optional;


public class Personnage extends Entite {
    private String m_nom;
    private Race m_race;
    private Classe m_classe;
    private CaracteristiquePersonnage m_caracteristiques;
    private Armurerie m_inventaire = new Armurerie();
    private Equipement m_equipements = new Equipement();
    private AttaquePersonnage m_attaque;
    private int m_pvsMax;
    private Optional<Sorts>[] m_sorts = (Optional<Sorts>[]) new Optional[Sorts.m_nbSorts];;

    public Personnage(String nom, Race race, Classe classe){
        this.m_nom = nom;
        this.m_race = race;
        this.m_classe = classe;
        this.setCaracteristiques(this.m_race.getM_caracteristiques());
        this.setInventaire(this.m_classe.getArmurerie());
        this.m_caracteristiques.bonusPvs(this.m_classe.getPvs());
        this.m_pvsMax = this.m_caracteristiques.getPvs();
        this.m_sorts = this.m_classe.definirSorts();
    }

    public void setEquipement_Arme(Arme arme){
        int[] bonus = this.m_equipements.setArme(arme); //bonus[0] = force, bonus[1] = vitesse
        this.m_caracteristiques.bonusForce(bonus[0]);
        this.m_caracteristiques.bonusVitesse(bonus[1]);
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

    public int getVitesse() {
        return this.getCaracteristiquesPerso().getVitesse();
    }

    public int getPvsMax() {
        return this.m_pvsMax;
    }
    public void setPvs(int pvs){
        this.m_caracteristiques.modifyPvs(pvs);
    }
    public Classe getClasse(){
        return this.m_classe;
    }
    public Optional<Sorts>[] getSorts() {
        return this.m_sorts;
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
    public boolean estAttaquePar(Monstre agresseur) {

        if (agresseur == null || agresseur.getAttaque() == null) {
            return false;
        }

        System.out.println("[DEBUG] Personnage est attaqué par monstre " + agresseur.getEspece().getNomEspece());

        // pr gérer selon l'attaque du monste
        AttaqueMonstre atkAgresseur = agresseur.getAttaque();

        // check si distance c bon
        int distance = Math.abs(this.getX() - agresseur.getX()) + Math.abs(this.getY() - agresseur.getY());
        if (distance > atkAgresseur.getPortee()) {
            AfficherDsPersonnage.afficherErreurPortee();
            return false;
        }

        // jet d'attaque
        De de = new De(1, 20);
        int jetAtk = de.lancer_de();

        // si portee > 1 --> dexterite si portee = 1 --> force
        int force = agresseur.getForce();
        int dexterite = agresseur.getDexterite();
        if (agresseur.getAttaque().getPortee() == 1) {
            jetAtk += agresseur.getForce();
        }
        else if (agresseur.getAttaque().getPortee() > 1) {
            jetAtk += agresseur.getDexterite();
        }

        int classeArmure = 0;

        if (this.getArmure_equipee() != null) {
            classeArmure = this.getArmure_equipee().getClasseArmure();
        }


        if (jetAtk > classeArmure) {
            int degats = agresseur.getAttaque().getDegats();
            int pvCible = this.getPvs() - degats;
            this.getCaracteristiques().modifyPvs(Math.max(0, pvCible)); // permet de renvoyer 0 meme si on descend ds les negatifs
            AfficherDsPersonnage.afficherPvRestantsPerso(pvCible);

            if (pvCible <= 0) {
                AfficherDsPersonnage.afficherPersoVaincu();
            }
        }
        else {
            AfficherDsMonstre.afficherAttaqueEchouee();
        }

        return true;

    }

    @Override
    public boolean estAttaquePar(Personnage agresseur) {
        return false; // un monstre ne peut pas etre attaqué par un monstre (et on utilise cette methode sur la cible, donc un monstre)
    }


    @Override
    public boolean attaquer(Entite cible) {
        if (cible == null) {
            return false;
        }
        return cible.estAttaquePar(this); // true si attaque reussie, false sinon
    }

    //TODO euh revoir les bails de caractéristiques partout help
    public CaracteristiqueMonstre getCaracteristiques() {
        return getCaracteristiques();
    }

    public List<Arme> getInventaireArmes(){
        return this.m_inventaire.getArmes();
    }

    public List<Armure> getInventaireArmures(){
        return this.m_inventaire.getArmures();
    }

    public List<Item> getInventaireItem(){
        return this.m_inventaire.getObjets();
    }

    public void equiperArme(){
        Armurerie arm = this.getInventaire();
        List<Arme> armes = arm.getArmes();
        if (!armes.isEmpty()){
            Affichage.afficherArmes(this);
            Affichage.afficher("Selectionnez l'arme à équiper : ");
            String armeChoisie = RecupInfos.scanString();
            if (!armeChoisie.isEmpty()){
                Arme armeAEquiper = null;
                for (Arme item : armes) {
                    if (item.getNom().equalsIgnoreCase(armeChoisie)) {
                        this.setEquipement_Arme(item);
                        armeAEquiper = item;
                    }
                }
                if (armeAEquiper == null) {
                    Affichage.afficherErreur("Arme non trouvée, veuillez réessayer.");
                    this.equiperArme();
                }
                else{
                    this.getInventaire().deleteArme(armeAEquiper);
                }
            }
        }
    }

    public void equiperArmure() {
        Armurerie arm = this.getInventaire();
        List<Armure> armures = arm.getArmures();

        if (!armures.isEmpty()) {
            Affichage.afficher("Armures disponibles pour " + this.getNom() + " :");
            for (Armure item : armures) {
                Affichage.afficher(" - " + item.getNom());
            }
            Affichage.afficher("Selectionnez l'armure à équiper : ");
            String armureChoisie = RecupInfos.scanString();
            if (!armureChoisie.isEmpty()) {
                Armure armureAEquiper = null;
                for (Armure item : armures) {
                    if (item.getNom().equalsIgnoreCase(armureChoisie)) {
                        this.setEquipement_Armure(item);
                        armureAEquiper = item;
                    }
                }
                if (armureAEquiper == null) {
                    Affichage.afficherErreur("Armure non trouvée, veuillez réessayer.");
                    this.equiperArmure();
                } else {
                    this.getInventaire().deleteArmure(armureAEquiper);
                }
            }
        }
    }

    public boolean estPasMort(){
        if (getPvs() <= 0){
            return false;
        }
        return true;
    }

}
