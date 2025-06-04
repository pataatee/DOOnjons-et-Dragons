package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.items.armes.*;
import gameContent.items.armures.ArmureLegere;

public class Roublard extends Classe {
    public Roublard() {
        super("Roublard", new Armurerie(), 16);
        modifyArmurerie();
    }

    @Override
    public Armurerie modifyArmurerie() {
        Armurerie arm = this.getArmurerie();
        arm.addArmes(ArmeCourante.Rapiere);
        arm.addArmes(ArmeDistance.Arc_court);;
        return arm;
    }
}
