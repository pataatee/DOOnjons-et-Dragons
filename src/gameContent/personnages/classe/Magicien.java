package gameContent.personnages.classe;

import gameContent.items.armes.*;

public class Magicien extends Classe {
    public Magicien() {
        this.m_nom = "Magicien";
        this.m_armurerie.addM_armes(ArmeCourante.Baton);
        this.m_armurerie.addM_armes(ArmeDistance.Fronde);
        //this.m_personnage.getM_caracteristiques().setM_pvs(1);
    }
}
