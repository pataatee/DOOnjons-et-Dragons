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
}
