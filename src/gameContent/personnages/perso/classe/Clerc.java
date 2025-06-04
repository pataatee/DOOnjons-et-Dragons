package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;

public class Clerc extends Classe {
    public Clerc() {
        super("Clerc", new Armurerie(), 16);
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeCourante.Masse_d_armes);
        arm.addArmes(ArmeDistance.Arbalete_legere);
        arm.addArmures(ArmureLegere.Ecailles);
        return arm;
    }
}
