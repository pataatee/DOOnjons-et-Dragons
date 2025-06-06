package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.*;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.Entite;
import gameContent.personnages.monstre.Monstre;
import gameContent.personnages.perso.Personnage;
import gameContent.sorts.Sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            jouerTour();

        }
    }

    private void initialiserActions() {
        this.m_actions.add("Attaquer");
        this.m_actions.add("S'équiper");
        this.m_actions.add("Se déplacer");
        this.m_actions.add("Détailler l'action précédente ");
        this.m_actions.add("Finir le tour");
        if (!Arrays.equals(m_pers.getSorts(), new boolean[]{false, false, false})) {
            this.m_actions.add("Lancer un sort");
        }
    }

    public void jouerTour() {
        Affichage.afficherTour(this.m_tour, this.m_pers, this.m_map);
        int numaction = Affichage.scanInt();
        if (numaction == 5) {
            m_actionsprecedentes.clear();
            m_finTour = true;
            Affichage.afficher("Fin du tour pour " + m_pers.getNom());
            return;
        }
        if (numaction < 1 || numaction > m_actions.size()) {
            Affichage.afficherErreur("Action invalide. Veuillez choisir une action valide.");
            jouerTour();
        } else {
            String action = m_actions.get(numaction - 1);
            if (numaction == 2) {
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add("2");
                afficherInventaire(this.m_pers);
                choisirEquipement();

            }
            if (numaction == 3) {
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add("3");
                seDeplacer();
            }
            Affichage.afficher("Action choisie : " + action);
            m_nbActions--;
            if (numaction == 6) {
                m_actionsprecedentes.clear();
                m_actionsprecedentes.add("6");
                Affichage.afficher("Sorts disponibles :");
                if (m_pers.getSorts()[0]) {
                    Affichage.afficher("1. Guérison");
                }
                if (m_pers.getSorts()[1]) {
                    Affichage.afficher("2. Boogie Woogie");
                }
                if (m_pers.getSorts()[2]) {
                    Affichage.afficher("3. Autre sort");
                }
                Affichage.afficher("quel sort voulez vous lancer ?");
                int sort = Affichage.scanInt();
                if (sort == 1) {
                    m_actionsprecedentes.add(String.valueOf(sort));
                    Affichage.afficher("Vous avez choisi de lancer le sort Guérison.Sur qui voulez vous lancer le sort ?");
                    String nom = Affichage.scanString();
                    Personnage perso = null;
                    for (Personnage pers : m_map.getJoueurs()) {
                        //if
                    }
                    Guerison(this.m_pers);
                } else if (sort == 2) {
                    m_actionsprecedentes.add(String.valueOf(sort));
                    if (!m_pers.getSorts()[1]) {
                        Affichage.afficherErreur("Vous ne pouvez pas lancer le sort Boogie Woogie, il n'est pas disponible pour votre personnage.");
                        jouerTour();
                        return;
                    }
                    initBoogieWoogie();
                } else {
                    Affichage.afficherErreur("Sort inconnu. Veuillez réessayer.");
                    jouerTour();
                }
            }
            if (numaction == 4) {
                anciennesActions();
            }
        }

    }

    public List<String> getActions() {
        return this.m_actions;
    }

    public int getListActions() {
        return this.m_actions.size();
    }

    public void choisirEquipement() {
        Affichage.afficher("de quel type d'objet voulez-vous vous équiper ?");
        String objet = Affichage.scanString();
        String phrase = "Vous avec choisi de vous équiper";
        if (objet.equalsIgnoreCase("arme")) {

            if (this.m_pers.getInventaire().getArmes().isEmpty()) {
                Affichage.afficherErreur("Vous n'avez pas d'armes dans votre inventaire.");
                m_nbActions++;
            }
            else {
                if (this.m_pers.getArme_equipee() != null) {
                    Affichage.afficher("Vous avez déjà une arme équipée. Voulez-vous la remplacer ? (O/N)");
                    phrase += " et vous avez remplacé votre "+ this.m_pers.getArme_equipee().getNom() + " par un ";
                    char reponse = Affichage.scanOuiNon();
                    if (reponse == 'O') {
                        this.m_pers.getInventaire().addArmes(this.m_pers.getArme_equipee());
                        m_pers.equiperArme();
                        phrase += this.m_pers.getArme_equipee().getNom() + ".";
                        m_actionsprecedentes.add(phrase);
                    }
                    else{
                        Affichage.afficher("Vous avez choisi de ne pas remplacer votre arme équipée.");
                        this.m_nbActions += 1; // On ajoute une action si l'utilisateur ne veut pas remplacer l'item
                    }
                }
                else {
                    m_pers.equiperArme();
                    phrase += " avec un "+ this.m_pers.getArme_equipee().getNom() + ".";
                    m_actionsprecedentes.add(phrase);
                }
            }
        }
        else if (objet.equalsIgnoreCase("armure")) {
            if (this.m_pers.getInventaire().getArmures().isEmpty()) {
                Affichage.afficherErreur("Vous n'avez pas d'armures dans votre inventaire.");
                m_nbActions++;
                jouerTour();
            }
            else {
                if (this.m_pers.getArmure_equipee() != null) {
                    Affichage.afficher("Vous avez déjà une armure équipée. Voulez-vous la remplacer ? (O/N)");
                    char reponse = Affichage.scanOuiNon();
                    if (reponse == 'O') {
                        phrase += " et vous avez remplacé votre "+ this.m_pers.getArme_equipee().getNom() + " par un ";
                        this.m_pers.getInventaire().addArmures(this.m_pers.getArmure_equipee());
                        m_pers.equiperArmure();
                        phrase += this.m_pers.getArme_equipee().getNom() + ".";
                        m_actionsprecedentes.add(phrase);
                    }
                    else {
                        Affichage.afficher("Vous avez choisi de ne pas remplacer votre armure équipée.");
                        this.m_nbActions += 1; // On ajoute une action si l'utilisateur ne veut pas remplacer l'item
                    }
                } else {
                    m_pers.equiperArmure();
                    phrase += " avec un "+ this.m_pers.getArme_equipee().getNom() + ".";
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
        int[] coordAct = Affichage.scanCoord(m_map);

        int x_pers = this.m_pers.getX();
        int y_pers = this.m_pers.getY();
        CoordonneesPersonnage posActuelle = new CoordonneesPersonnage(x_pers, y_pers, this.m_pers);
        Coordonnees posVoulue = new CoordonneesCaseVide(coordAct[0], coordAct[1]);

        if (!Deplacement(posActuelle, posVoulue)) {
            seDeplacer();
        } else {
            if (this.m_map.getCase(coordAct[0], coordAct[1]) instanceof CoordonneesItem) {
                boolean item = CaseTresor(this.m_map.getCase(coordAct[0], coordAct[1]));

                if (!item) {
                    Affichage.afficher("Vous avez choisi de ne pas ramasser l'objet.");
                }
            }
            this.m_map.setCase(coordAct[0], coordAct[1], posActuelle); // On met à jour la position sur la carte
            this.m_map.setCase(x_pers, y_pers, new CoordonneesCaseVide(x_pers, y_pers)); // On vide l'ancienne position
            m_pers.setPosition(coordAct[0], coordAct[1]); // on met à jour la position du personnage
            Affichage.afficherMap(m_map);
        }
    }

    public boolean Deplacement(Coordonnees posActuelle, Coordonnees posVoulue) {
        if (posVoulue instanceof CoordonneesMonstre || posVoulue instanceof CoordonneesObstacle) {
            Affichage.afficherErreur("vous ne pouvez pas vous déplacer sur une case occupée par un monstre ou un obstacle.");
            return false;
        }
        int vitesse = m_pers.getVitesse() / 3;
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
        Affichage.afficher("Vous avez trouvé un " + item.getNom() + " sur cette case ! Voulez-vous le ramasser ? (O/N)");
        char reponse = Affichage.scanOuiNon();
        if (reponse == 'O') {
            if (item instanceof Arme) {
                m_pers.getInventaire().addArmes((Arme) item);
            } else {
                m_pers.getInventaire().addArmures((Armure) item);
            }

            Affichage.afficher("Vous avez ramassé : " + item.getNom());
            return true;
        } else {
            Affichage.afficher("Vous avez choisi de ne pas ramasser l'objet.");
            return false;
        }
    }

    public void initBoogieWoogie() {
        Affichage.afficher("Le Sort Boogie Woogie est lancé !");
        Affichage.afficher("Quel personnage voulez vous échanger ?");
        int[] CoordPerso1 = Affichage.scanCoord(m_map);
        Coordonnees coord1 = m_map.getCase(CoordPerso1[0], CoordPerso1[1]);
        Entite perso1 = null;

        if (coord1 instanceof CoordonneesPersonnage) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso1[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso1[1]));
            m_actionsprecedentes.add("P");
            perso1 = ((CoordonneesPersonnage) coord1).getPersonnage();
            m_actionsprecedentes.add(((Personnage) perso1).getNom());
        } else if (coord1 instanceof CoordonneesMonstre) {
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
        int[] CoordPerso2 = Affichage.scanCoord(m_map);
        Coordonnees coord2 = m_map.getCase(CoordPerso2[0], CoordPerso2[1]);
        Entite perso2 = null;

        if (coord2 instanceof CoordonneesPersonnage) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[1]));
            m_actionsprecedentes.add("P");
            perso2 = ((CoordonneesPersonnage) coord2).getPersonnage();
            m_actionsprecedentes.add(((Personnage) perso2).getNom());
        } else if (coord2 instanceof CoordonneesMonstre) {
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[0]));
            m_actionsprecedentes.add(String.valueOf(CoordPerso2[1]));
            m_actionsprecedentes.add("M");
            perso2 = ((CoordonneesMonstre) coord2).getMonstre();
            m_actionsprecedentes.add(((Monstre) perso2).getEspece().getNomEspece());
        } else {
            Affichage.afficherErreur("Vous ne pouvez pas échanger de plca autre chose qu'un personnage ou un monstre.");
            initBoogieWoogie();
            return;
        }
        Sorts.BoogieWoogie(perso1, perso2, m_map);

    }

    public void anciennesActions(){
        if (m_actionsprecedentes.isEmpty()) {
            Affichage.afficher("Vous n'avez pas encore éffectué d'actions");
        }
        else {
            switch (m_actionsprecedentes.get(0)) {
                case "2":
                    Affichage.afficher(m_actionsprecedentes.get(1));
                    break;
                case "3":
                     Affichage.afficher("Se déplacer");
                    break;
                case "6":
                    switch (m_actionsprecedentes.get(1)) {
                        case "1":
                            Affichage.afficher("Vous avez lancé le sort Guérison.");
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
                        default:
                            Affichage.afficher("Vous avez lancé un autre sort.");
                            break;
                    }
                default:
                    break;
            }
        }
    }
}
