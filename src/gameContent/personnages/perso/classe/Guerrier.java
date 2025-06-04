package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;

public class Guerrier extends Classe {
    public Guerrier() {
        super("Guerrier", new Armurerie(), 20);
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeCourante.Epee_longue);
        arm.addArmes(ArmeDistance.Arbalete_legere);
        arm.addArmures(ArmureLegere.Cotte_de_maille);
        return arm;
    }
}

