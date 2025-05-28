package gameContent.personnages.perso.classe;

import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;

public class Clerc extends Classe {
    public Clerc() {
        this.m_nom = "Clerc";
        this.m_armurerie.addM_armes(ArmeCourante.Masse_d_armes); //TODO a mettre en private, jpp l'appeler comme ça
        this.m_armurerie.addM_armures(ArmureLegere.Ecailles);
        this.m_armurerie.addM_armes(ArmeDistance.Arbalete_legere);
        this.m_pvs=16;
    }
}
