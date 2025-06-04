import fonctionnement.mdj.Map;
import gameContent.personnages.perso.Personnage;
import gameContent.personnages.perso.race.*;
import gameContent.personnages.perso.classe.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class MapNonRanomTest {

    @Test
    void testInitialisationMapNonRandom() {
        Personnage[] personnages = new Personnage[]{new Personnage("truc", new Humain(), new Clerc())}; // Création d'un tableau vide de personnages
        Map map = new Map(5, 5, 1, personnages); // Création d'une carte non aléatoire de 5x5 cases
        map.createMap();
    }
}
