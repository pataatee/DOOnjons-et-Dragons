package gameContent.personnages.classe;

import gameContent.items.armes.*;

public class Roublard extends Classe {
    public Roublard() {
        this.m_nom = "Roublard";
        this.m_armurerie.addM_armes(ArmeCourante.Rapiere); //TODO a mettre en private, jpp l'appeler comme ça
        this.m_armurerie.addM_armes(ArmeDistance.Arc_court);
        this.m_pvs=16;
    }
}
