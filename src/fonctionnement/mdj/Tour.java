package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.*;
import fonctionnement.utilisateur.RecupInfos;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.Entite;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;
import gameContent.sorts.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static fonctionnement.affichage.Affichage.*;
import static gameContent.sorts.Sorts.*;

public class Tour {
    private int m_tour;
    private int m_nbActions;
    private final Personnage m_pers;
    private final List<String> m_actions = new ArrayList<>(); // Actions possibles pour le personnage
    private boolean m_finTour; // Indique si le tour est terminé
    private Map m_map; // La carte du jeu, si nécessaire
    private final List<String> m_actionsprecedentes = new ArrayList<>();

    public Tour(Personnage pers) {
        m_pers = pers;
        initialiserActions();
    }

    public Tour(Personnage pers, int tour, Map map) {
        this.m_pers = pers;
        initialiserActions();
        this.m_tour = tour + 1;
        this.m_nbActions = 3; // Nombre d'actions par tour
        this.m_map = map; // Initialisation de la carte

        this.m_finTour = false;
        while (!m_finTour && m_nbActions > 0) {

            jouerTour(4-m_nbActions);

        }
    }
    public int getStatut(){
        if (m_map.getNbMonstre() == 0){
            return 1;
        }
        return 0;
    }

    private void initialiserActions() {
        this.m_actions.add("Attaquer");
        this.m_actions.add("S'équiper");
        this.m_actions.add("Se déplacer");
        this.m_actions.add("Détailler l'action précédente ");
        this.m_actions.add("Finir le tour");
        if (SortDisponibles(this.m_pers.getSorts())) {
            this.m_actions.add("Lancer un sort");
        }
    }
    public static boolean SortDisponibles(Optional<Sorts>[] sorts) {
        for (Optional<Sorts> s : sorts) {
            if (s.isPresent()) return true; // au moins un sort
        }
        return false; // aucun sort
    }

    public void jouerTour(int i) {
        Affichage.afficherTour(this.m_tour, i, this.m_pers, this.m_map, this.m_map.getMonstres());
        int numaction = RecupInfos.scanInt();
        if (numaction == 5) {
            m_actionsprecedentes.clear();
            m_finTour = true;
            Affichage.afficher("Fin du tour pour " + m_pers.getNom());
            return;
        }
        if (numaction < 1 || numaction > m_actions.size()) {
            Affichage.afficherErreur("Action invalide. Veuillez choisir une action valide.");
            jouerTour(i);
        } else {
            String action = m_actions.get(numaction - 1);
            if (numaction == 2) {
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add("2");
                afficherInventaire(this.m_pers);
                choisirEquipement();
                m_nbActions--;

            }
            if (numaction == 3) {
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add("3");
                seDeplacer();
                m_nbActions--;
            }
            if (numaction == 6) {
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add("6");
                Affichage.afficher("Sorts disponibles :");
                if ((m_pers.getSorts()[m_guerison] != null && m_pers.getSorts()[m_guerison].isPresent())) {
                    Affichage.afficher("1. Guérison");
                }
                if ((m_pers.getSorts()[m_boogieWoogie] != null && m_pers.getSorts()[m_boogieWoogie].isPresent())) {
                    Affichage.afficher("2. Boogie Woogie");
                }
                if ((m_pers.getSorts()[m_armeMagique] != null && m_pers.getSorts()[m_armeMagique].isPresent())) {
                    Affichage.afficher("3. Arme Magique");
                }
                Affichage.afficher("quel sort voulez vous lancer ?");
                int sort = RecupInfos.scanInt();
                if (sort == 1) {
                    m_actionsprecedentes.add(String.valueOf(sort));
                    Affichage.afficher("Vous avez choisi de lancer le sort Guérison. Sur qui voulez vous lancer le sort ?");
                    String nom = RecupInfos.scanString();
                    Personnage perso = null;
                    for (Personnage pers : m_map.getJoueurs()) {
                        if (pers.getNom().equalsIgnoreCase(nom)) {
                            perso = pers;
                            break;
                        }
                    }
                    if (perso == null) {
                        Affichage.afficherErreur("Personnage non trouvé. Veuillez réessayer.");
                        jouerTour(i);
                    }
                    else{
                        m_actionsprecedentes.add(perso.getNom());
                        int pvsDeBase = perso.getPvs();
                        Guerison guer = (Guerison) this.m_pers.getSorts()[m_guerison].get();
                        m_nbActions--;
                        guer.lancer(perso);
                        int pvsApres = perso.getPvs()-pvsDeBase;
                        m_actionsprecedentes.add(String.valueOf(pvsApres));
                        Affichage.afficher(perso.getNom() + " a gagné "+pvsApres+" Pvs !");
                    }

                } else if (sort == 2) {
                    m_actionsprecedentes.add(String.valueOf(sort));
                    if ((m_pers.getSorts()[m_armeMagique] == null)||m_pers.getSorts()[m_boogieWoogie].isEmpty()) {
                        Affichage.afficherErreur("Vous ne pouvez pas lancer le sort Boogie Woogie, il n'est pas disponible pour votre personnage.");
                        jouerTour(i);
                    }
                    else{
                        m_nbActions--;
                        initBoogieWoogie();
                    }
                }
                else if (sort == 3){
                    m_actionsprecedentes.add(String.valueOf(sort));
                    if (m_pers.getSorts()[m_armeMagique] == null ||(m_pers.getSorts()[m_armeMagique].isEmpty())) {
                        Affichage.afficherErreur("Vous ne pouvez pas lancer le sort Arme Magique, il n'est pas disponible pour votre personnage.");
                        jouerTour(i);
                    }
                    else {
                        m_nbActions--;
                        initArmeMagique();
                    }
                }

                else {
                    Affichage.afficherErreur("Sort inconnu. Veuillez réessayer.");
                    jouerTour(i);
                }
            }
            if (numaction == 4) {
                m_nbActions--;
                anciennesActions();
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add(String.valueOf(numaction));
            }

            if (numaction == 1) {
                m_actionsprecedentes.clear();
                m_nbActions--;
                m_actionsprecedentes.add(String.valueOf(numaction));
                utiliserAttak();
            }
        }

    }

    public void utiliserAttak() {
        // get le monstre qu'il faut attaquer
        // demander quel mosntre souhaitez-vs attaquer + liste des monstres de la map et leurs co
        Affichage.demanderQuiAttaquer();
        Affichage.afficherMonstres(m_map.getM_carte());
        String nomMonstre = RecupInfos.scanString();
        // ok donc là, on récup le nom du monstre qu'on veut attaquer.
        // ensuite dcp faut qu'on prenne le monstre associé aux co du nom, et qu'on utilise attaquer sur lui !
        Monstre cible = m_map.getMontreByNom(nomMonstre);
        m_actionsprecedentes.add(cible.getEspece().getNomEspece());
        m_pers.attaquer(cible);
        m_actions.add("1");
        Affichage.afficher(this.m_pers.getNom() + " a attaqué " + cible.getEspece().getNomEspece());
    }


    public List<String> getActions() {
        return this.m_actions;
    }

    public int getListActions() {
        return this.m_actions.size();
    }

    public void choisirEquipement() {
        Affichage.afficher("de quel type d'objet voulez-vous vous équiper ?");
        String objet = RecupInfos.scanString();
        String phrase = "Vous avec choisi de vous équiper";
        if (objet.equalsIgnoreCase("arme")) {

            if (this.m_pers.getInventaireArmes().isEmpty()) {
                Affichage.afficherErreur("Vous n'avez pas d'armes dans votre inventaire.");
                m_nbActions++;
            }
            else {
                if (this.m_pers.getArme_equipee() != null) {
                    Affichage.afficher("Vous avez déjà une arme équipée. Voulez-vous la remplacer ? (O/N)");
                    phrase += " et vous avez remplacé votre "+ this.m_pers.getArme_equipee().getNom() + " par ";
                    char reponse = RecupInfos.scanOuiNon();
                    if (reponse == 'O') {
                        this.m_pers.getInventaire().addArmes(this.m_pers.getArme_equipee());
                        m_pers.equiperArme();
                        phrase += this.m_pers.getArme_equipee().avecArticleIndefini() + ".";
                        m_actionsprecedentes.add(phrase);
                    }
                    else{
                        Affichage.afficher("Vous avez choisi de ne pas remplacer votre arme équipée.");
                        this.m_nbActions += 1; // On ajoute une action si l'utilisateur ne veut pas remplacer l'item
                    }
                }
                else {
                    m_pers.equiperArme();
                    phrase += " avec "+ this.m_pers.getArme_equipee().avecArticleIndefini() + ".";
                    m_actionsprecedentes.add(phrase);
                }
            }
        }
        else if (objet.equalsIgnoreCase("armure")) {
            if (this.m_pers.getInventaireArmures().isEmpty()) {
                Affichage.afficherErreur("Vous n'avez pas d'armures dans votre inventaire.");
                m_nbActions++;
            }
            else {
                if (this.m_pers.getArmure_equipee() != null) {
                    Affichage.afficher("Vous avez déjà une armure équipée. Voulez-vous la remplacer ? (O/N)");
                    char reponse = RecupInfos.scanOuiNon();
                    if (reponse == 'O') {
                        phrase += " et vous avez remplacé votre "+ this.m_pers.getArme_equipee().getNom() + " par ";
                        this.m_pers.getInventaire().addArmures(this.m_pers.getArmure_equipee());
                        m_pers.equiperArmure();
                        phrase += this.m_pers.getArmure_equipee().avecArticleIndefini() + ".";
                        m_actionsprecedentes.add(phrase);
                    }
                    else {
                        Affichage.afficher("Vous avez choisi de ne pas remplacer votre armure équipée.");
                        this.m_nbActions += 1; // On ajoute une action si l'utilisateur ne veut pas remplacer l'item
                    }
                } else {
                    m_pers.equiperArmure();
                    phrase += " avec "+ this.m_pers.getArmure_equipee().avecArticleIndefini() + ".";
                    m_actionsprecedentes.add(phrase);
                }
            }
        }
        else{
            Affichage.afficherErreur("Type d'objet inconnu. Veuillez choisir entre 'arme' ou 'armure'.");
            choisirEquipement();
        }
    }

    public void seDeplacer() {
        Affichage.afficher("Où voulez-vous vous déplacer ?");
        int[] coordVoulues = RecupInfos.scanCoord(m_map);
        if (m_map.getCase(coordVoulues[0], coordVoulues[1]).getCaseVide() != null){
            int x_pers = this.m_pers.getX();
            int y_pers = this.m_pers.getY();
            CoordonneesPersonnage posActuelle = new CoordonneesPersonnage(x_pers, y_pers,this.m_pers );
            CoordonneesCaseVide posVoulue = new CoordonneesCaseVide(coordVoulues[0], coordVoulues[1]);
            if (!Deplacement(posActuelle, posVoulue)) {
                seDeplacer();
            }
            else {
                m_actionsprecedentes.add(String.valueOf(x_pers));
                m_actionsprecedentes.add(String.valueOf(y_pers));
                m_actionsprecedentes.add(String.valueOf(coordVoulues[0]));
                m_actionsprecedentes.add(String.valueOf(coordVoulues[1]));
                m_actionsprecedentes.add("none");

                this.m_map.setCase(coordVoulues[0], coordVoulues[1], posActuelle); // On met à jour la position sur la carte
                this.m_map.setCase(x_pers, y_pers, new CoordonneesCaseVide(x_pers, y_pers)); // On vide l'ancienne position
                m_pers.setPosition(coordVoulues[0], coordVoulues[1]); // on met à jour la position du personnage
                Affichage.afficherMap(m_map);
            }
        }
        else if (m_map.getCase(coordVoulues[0], coordVoulues[1]).getItem() != null) {
            int x_pers = this.m_pers.getX();
            int y_pers = this.m_pers.getY();
            CoordonneesPersonnage posActuelle = new CoordonneesPersonnage(x_pers, y_pers,this.m_pers );
            CoordonneesItem posVoulue = new CoordonneesItem(coordVoulues[0], coordVoulues[1]);
            if (!Deplacement(posActuelle, posVoulue)) {
                seDeplacer();
                return;
            }
            m_actionsprecedentes.add(String.valueOf(x_pers));
            m_actionsprecedentes.add(String.valueOf(y_pers));
            m_actionsprecedentes.add(String.valueOf(coordVoulues[0]));
            m_actionsprecedentes.add(String.valueOf(coordVoulues[1]));

            boolean item = CaseTresor(this.m_map.getCase(coordVoulues[0], coordVoulues[1]));
            if (!item) {
                Affichage.afficher("Vous avez choisi de ne pas ramasser l'objet.");
                m_actionsprecedentes.add("N");
            }
            else {
                m_actionsprecedentes.add("O");
            }

            this.m_map.setCase(coordVoulues[0], coordVoulues[1], posActuelle); // On met à jour la position sur la carte
            this.m_map.setCase(x_pers, y_pers, new CoordonneesCaseVide(x_pers, y_pers)); // On vide l'ancienne position
            m_pers.setPosition(coordVoulues[0], coordVoulues[1]); // on met à jour la position du personnage
            Affichage.afficherMap(m_map);

        }
        else {
            Affichage.afficherErreur("vous ne pouvez pas vous déplacer sur une case occupée par un monstre ou un obstacle.");
            seDeplacer();
        }
    }

    public boolean Deplacement(Coordonnees posActuelle, Coordonnees posVoulue) {
        int vitesse = this.m_pers.getVitesse() / 3;
        //distance = racine carre((x1 - x2)2 + (y1 - y2)2)
        int distance = (int) Math.sqrt(Math.pow(posActuelle.getX() - posVoulue.getX(), 2) + Math.pow(posActuelle.getY() - posVoulue.getY(), 2));
        if (distance > vitesse) {
            Affichage.afficherErreur("Vous ne pouvez pas vous déplacer aussi loin, votre vitesse est de " + vitesse + ".");
            return false;
        }
        return true;
    }

    public boolean CaseTresor(Coordonnees coord) {

        Item item = ((CoordonneesItem) coord).getItem();
        Affichage.afficher("Vous avez trouvé " + item.avecArticleIndefini() + " sur cette case ! Voulez-vous le ramasser ? (O/N)");
        char reponse = RecupInfos.scanOuiNon();
        if (reponse == 'O') {
            if (item.getArme() != null) {
                m_pers.getInventaire().addArmes((Arme) item);
            } else {
                m_pers.getInventaire().addArmures((Armure) item);
            }

            Affichage.afficher("Vous avez ramassé : " + item.avecArticlePartitif());
            return true;
        } else {
            Affichage.afficher("Vous avez choisi de ne pas ramasser l'objet.");
            return false;
        }
    }

    public void initBoogieWoogie() {
        Affichage.afficher("Le Sort Boogie Woogie est lancé !");
        Affichage.afficher("Quel personnage voulez vous échanger ?");
        int[] CoordPerso1 = RecupInfos.scanCoord(m_map);
        Coordonnees coord1 = m_map.getCase(CoordPerso1[0], CoordPerso1[1]);
        Entite perso1 = null;

        if (coord1.getPersonnage() != null) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso1[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso1[1]));
            m_actionsprecedentes.add("P");
            perso1 = ((CoordonneesPersonnage) coord1).getPersonnage();
            m_actionsprecedentes.add(((Personnage) perso1).getNom());
        } else if (coord1.getMonstre() != null) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso1[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso1[1]));
            m_actionsprecedentes.add("M");
            perso1 = ((CoordonneesMonstre) coord1).getMonstre();
            m_actionsprecedentes.add(((Monstre) perso1).getEspece().getNomEspece());
        } else {
            Affichage.afficherErreur("Vous ne pouvez pas échanger de place autre chose qu'un personnage ou un monstre.");
            initBoogieWoogie();
            return;
        }

        Affichage.afficher("Quel personnage voulez-vous échanger avec ?");
        int[] CoordPerso2 = RecupInfos.scanCoord(m_map);
        Coordonnees coord2 = m_map.getCase(CoordPerso2[0], CoordPerso2[1]);
        Entite perso2 = null;

        if (coord2.getPersonnage()!=null) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[1]));
            m_actionsprecedentes.add("P");
            perso2 = ((CoordonneesPersonnage) coord2).getPersonnage();
            m_actionsprecedentes.add(((Personnage) perso2).getNom());
        } else if (coord2.getMonstre()!=null) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[1]));
            m_actionsprecedentes.add("M");
            perso2 = ((CoordonneesMonstre) coord2).getMonstre();
            m_actionsprecedentes.add(((Monstre) perso2).getEspece().getNomEspece());
        } else {
            Affichage.afficherErreur("Vous ne pouvez pas échanger de place autre chose qu'un personnage ou un monstre.");
            initBoogieWoogie();
            return;
        }
        BoogieWoogie bw = (BoogieWoogie) this.m_pers.getSorts()[m_boogieWoogie].get();
        bw.lancer(perso1, perso2, m_map);

    }

    public void initArmeMagique(){
        Affichage.afficher("Sur qui voulez-vous lancer le sort Arme Magique ?");
        String nom = RecupInfos.scanString();
        Personnage pers = null;
        for (Personnage p : m_map.getJoueurs()) {
            if (p.getNom().equalsIgnoreCase(nom)) {
                pers = p;
                break;
            }
        }
        if (pers == null) {
            Affichage.afficherErreur("Personnage non trouvé. Veuillez réessayer.");
            initArmeMagique();
        }
        else {
            Affichage.afficherArmes(pers);
            Affichage.afficher("Quel arme voulez-vous rendre plus puissante ?");
            String armeNom = RecupInfos.scanString();
            Arme arme = null;
            boolean armeTrouvee = false;
            for (Arme a : pers.getInventaireArmes()) {
                if (a.getNom().equalsIgnoreCase(armeNom)) {
                    arme = a;
                    armeTrouvee = true;
                    break;
                }
            }
            if (!armeTrouvee){
                if (pers.getArme_equipee() != null && pers.getArme_equipee().getNom().equalsIgnoreCase(armeNom)) {
                    arme = pers.getArme_equipee();
                    armeTrouvee = true;
                }
            }
            if (!armeTrouvee) {
                Affichage.afficherErreur("Arme non trouvée. Veuillez réessayer.");
                initArmeMagique();
            }
            else{
                m_actionsprecedentes.add(pers.getNom());
                m_actionsprecedentes.add(arme.avecArticleDefini());
                //Guerison guer = (Guerison) perso.getSorts()[m_guerison].get();
                //guer.lancer(perso);
                ArmeMagique am = (ArmeMagique) this.m_pers.getSorts()[m_armeMagique].get();
                am.lancer(pers, arme);
            }
        }
    }

    public void anciennesActions(){
        if (m_actionsprecedentes.isEmpty()) {
            Affichage.afficher("Vous n'avez pas encore éffectué d'actions");
        }
        else {
            switch (m_actionsprecedentes.get(0)) {
                case "1":
                    Affichage.afficher("vous avez attaqué un " +m_actionsprecedentes.get(1));
                case "2":
                    Affichage.afficher(m_actionsprecedentes.get(1));
                    break;
                case "3":
                    int Coordx = Integer.parseInt(m_actionsprecedentes.get(1))+1;
                    char CoordlettreY = (char) ('A' + Integer.parseInt(m_actionsprecedentes.get(2)));
                    String coords = Coordx + "," + CoordlettreY +" à la case ";
                    Coordx = Integer.parseInt(m_actionsprecedentes.get(3))+1;
                    CoordlettreY = (char) ('A' + Integer.parseInt(m_actionsprecedentes.get(4)));
                    coords += Coordx + "," + CoordlettreY +".";
                     Affichage.afficher("Vous vous êtes déplacé de la case "+ coords);
                     if (m_actionsprecedentes.get(5).equals("O")){
                         Affichage.afficher("et vous avez ramassé un objet");
                     }
                     else if (m_actionsprecedentes.get(5).equals("N")){
                         Affichage.afficher("et vous avez n'avez pas ramassé l'objet sur la case");
                     }
                    break;
                case "4":
                    Affichage.afficher("Vous avez demandé a pouvoir voir les actions précédentes");
                    break;
                case "6":
                    switch (m_actionsprecedentes.get(1)) {
                        case "1":
                            Affichage.afficher("Vous avez lancé le sort Guérison et "+m_actionsprecedentes.get(2)+ " a gagné "+m_actionsprecedentes.get(3)+" Pvs.");
                            break;
                        case "2":
                            Affichage.afficher("Vous avez lancé le sort Boogie Woogie.");
                            String phrase = "Vous avez échangé les places ";
                            if (m_actionsprecedentes.get(4).equals("P")){
                                phrase += "de " +m_actionsprecedentes.get(5);
                            }
                            else{
                                phrase += "du monstre " + m_actionsprecedentes.get(5);
                            }
                            // convertir la coordonée en lettre
                            int x = Integer.parseInt(m_actionsprecedentes.get(2))+1;
                            char lettreY = (char) ('A' + Integer.parseInt(m_actionsprecedentes.get(3)));
                            phrase += " a la coordonnée " + x + "," + lettreY +" avec ";
                            if (m_actionsprecedentes.get(8).equals("P")){
                                phrase += m_actionsprecedentes.get(9);
                            }
                            else{
                                phrase += "le monstre " + m_actionsprecedentes.get(9);
                            }
                            lettreY = (char) ('A' + Integer.parseInt(m_actionsprecedentes.get(7)));
                            x = Integer.parseInt(m_actionsprecedentes.get(6))+1;
                            phrase += " a la coordonnée " + x + "," + lettreY +".";
                            Affichage.afficher(phrase);
                            break;
                        case "3":
                            Affichage.afficher("Vous avez lancé le sort Arme Magique.");
                            Affichage.afficher("Vous avez rendu " + m_actionsprecedentes.get(3) + " de " + m_actionsprecedentes.get(2) + " plus puissant.");
                            break;
                        default:
                            Affichage.afficher("En théorie ce cas n'existe pas");
                            break;
                    }
                default:
                    break;
            }
        }
    }
}
