package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;

public class Magicien extends Classe {
    public Magicien() {
        super("Magicien", new Armurerie(), 12);
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeCourante.Baton);
        arm.addArmes(ArmeCourante.Baton);;
        return arm;
    }
}
