package gameContent.personnages.classe;

import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;

public class Guerrier extends Classe {
    public Guerrier() {
        this.m_nom = "Guerrier";
        this.m_armurerie.addM_armes(ArmeCourante.Epee_longue); //TODO a mettre en private, jpp l'appeler comme ça
        this.m_armurerie.addM_armures(ArmureLegere.Cotte_de_maille);
        this.m_armurerie.addM_armes(ArmeDistance.Arbalete_legere);
        this.m_pvs=20;
    }
}

