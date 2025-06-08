package gameContent.personnages.monstre;

import fonctionnement.affichage.AffichageCreateMonstre;
import fonctionnement.de.De;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.personnages.Caracteristique;

public class CaracteristiqueMonstre extends Caracteristique {
    private int m_classeArmure;

    public CaracteristiqueMonstre(int pv, int force, int dexterite, int initiative, int vitesse, int classeArmure) {
        super(pv, force, dexterite, initiative, vitesse);
        m_classeArmure = classeArmure;
    }

    public int getClasseArmure() {
        return m_classeArmure;
    }



    public String toString() {
        return "PVs : " + super.getPvs() + "\nForce : " + super.getForce() + "\nDextérité : " + super.getDexterite() + "\nVitesse : " + super.getVitesse() + "\nClasse d'armure : " + this.getClasseArmure() + "\nInitiative : " + getInitiative();
    }


    //CARAC PAR DEFAUT
    public static final CaracteristiqueMonstre carDragon = new CaracteristiqueMonstre(100, 12, 20, 1, 17, 20);
    public static final CaracteristiqueMonstre c1 = new CaracteristiqueMonstre(50, 10, 2, 10, 10, 3);
    public static final CaracteristiqueMonstre carRat = new CaracteristiqueMonstre(12, 3, 2, 20, 30, 4);
    public static final CaracteristiqueMonstre c2 = new CaracteristiqueMonstre(40, 16, 16, 4, 10, 13);
    public static final CaracteristiqueMonstre c3 = new CaracteristiqueMonstre(60, 15, 13, 10, 10, 9);
    public static final CaracteristiqueMonstre c4 = new CaracteristiqueMonstre(26, 18, 20, 13, 13, 8);
    public static final CaracteristiqueMonstre c5 = new CaracteristiqueMonstre(30, 16, 27, 2, 50, 19);
}
