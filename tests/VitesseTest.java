
import gameContent.personnages.perso.CaracteristiquePersonnage;
import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.race.*;
import gameContent.personnages.perso.classe.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class VitesseTest {

    @Test
    void InitVitesse() {
        CaracteristiquePersonnage caracteristique = new CaracteristiquePersonnage(100, 10, 15, 5, 20);

        // Test initial speed
        Assertions.assertEquals(20, caracteristique.getVitesse());

        // Modify speed
        caracteristique.modifyVitesse(30);
        Assertions.assertEquals(30, caracteristique.getVitesse());

        // Bonus to speed
        caracteristique.bonusVitesse(10);
        Assertions.assertEquals(40, caracteristique.getVitesse());
    }

    @Test
    void RaceVitesse() {
        Halfelin halfelin = new Halfelin();
        Assertions.assertEquals(5, halfelin.getM_caracteristiques().getVitesse());
    }

    @Test
    void PersonnageVitesse(){
        Personnage pers = new Personnage("Test", new Halfelin(), new Clerc());
        Assertions.assertEquals(5, pers.getVitesse());
    }

}
