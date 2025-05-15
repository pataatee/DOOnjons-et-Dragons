package gameContent.personnages.classe;

import gameContent.items.armes.*;

public class Magicien extends Classe {
    public Magicien() {
        this.m_nom = "Magicien";
        this.m_pvs = 1;
        this.m_armurerie.add(ArmeCourante.Baton);
        this.m_armurerie.add(ArmeDistance.Fronde);
    }
}
