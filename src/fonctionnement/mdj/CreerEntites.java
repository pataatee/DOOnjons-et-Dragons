package fonctionnement.mdj;

import fonctionnement.affichage.AffichageCreateMonstre;
import fonctionnement.de.De;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.personnages.monstre.AttaqueMonstre;
import gameContent.personnages.monstre.CaracteristiqueMonstre;
import gameContent.personnages.monstre.Espece;

public class CreerEntites {

    // monstre


    
    public static CaracteristiqueMonstre createCaracMonstre() {
        De de = new De(4, 4);

        int pvMonstre;
        AffichageCreateMonstre.demanderPvMonstre();
        pvMonstre = RecupInfos.scanInt();

        // TODO midif si on veut des pv min ou max pr encore une x que ce soit pas trop cheaté

        int forceMonstre; // 0 si attaque a distance
        AffichageCreateMonstre.demanderForce();
        forceMonstre = RecupInfos.scanInt();

        int dexteriteMonstre; // 0 si attaque au cac
        AffichageCreateMonstre.demanderDexterite();
        dexteriteMonstre = RecupInfos.scanInt();

        int initiativeMonstre;
        initiativeMonstre = de.lancer_de();
        // caracteristiques monstre determinees par un dé ?
        // estce que ya le +3 aussi ?

        int classeArmureMonstre = de.lancer_de();

        int vitesseMonstre = de.lancer_de();

        return new CaracteristiqueMonstre(pvMonstre, forceMonstre, dexteriteMonstre, initiativeMonstre, vitesseMonstre, classeArmureMonstre);
    }
    public static AttaqueMonstre createAttaqueMonstre() {
        String nomAttaque;
        AffichageCreateMonstre.demanderAttaque();
        nomAttaque = RecupInfos.scanString();

        int porteeAttaque;
        AffichageCreateMonstre.demanderPorteeAttaque();
        porteeAttaque = RecupInfos.scanInt();

        // TODO do while si on veut limiter la portée max

        int degatsAttaque;
        AffichageCreateMonstre.demanderDegatsAttaque();
        degatsAttaque = RecupInfos.scanInt();

        // TODO idem si on veut limiter degats max de l'attaque bcs oui eviter que ce soit trop cheaté

        return new AttaqueMonstre(nomAttaque, porteeAttaque, degatsAttaque);

    }

    public static Espece createEspece(int num) {
        String especeMonstre;

        AffichageCreateMonstre.demanderEspece();
        especeMonstre = RecupInfos.scanString();

        Espece espece = new Espece(especeMonstre, num); // i parce qu'on veut que le num du monstre soit jms le mm
        // TODO vérif si espece egales, si ya des especes egales oui on utilise constructeur w numero sinon no
    }

}
