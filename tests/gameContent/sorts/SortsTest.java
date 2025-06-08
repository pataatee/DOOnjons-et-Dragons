package gameContent.sorts;

import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.classe.Classe;
import gameContent.personnages.perso.classe.Clerc;
import gameContent.personnages.perso.race.Nain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortsTest {

    /*@Test
    void testInitClercSorts() {
        // Arrange
        Personnage personnage = new Personnage("Test", new Nain(), new Clerc());
        Sorts sorts = new Sorts(personnage.getClasse());
        assertEquals(true, sorts.getSorts()[0]); // Le clerc peut lancer le sort Guérison
        assertEquals(false, sorts.getSorts()[1]); // Le clerc ne peut pas lancer le sort Boogie Woogie
        assertEquals(false, sorts.getSorts()[2]); // Le clerc ne peut pas lancer le sort Arme Magique
    }*/

    @Test

    void testGuerison(){
        Personnage personnage = new Personnage("Test", new Nain(), new Clerc());
        personnage.setPvs(12); // Points de vie actuels
        int pvsMax = personnage.getPvsMax();
        int pvsDeBase = personnage.getPvs();

        Guerison.lancer(personnage);

        assertTrue(personnage.getPvs()<= personnage.getPvsMax());
        assertNotEquals(personnage.getPvs(),pvsDeBase );
        //si c vrai c que la guérison fonctionne
    }

}