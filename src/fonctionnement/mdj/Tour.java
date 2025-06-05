package fonctionnement.mdj;

import fonctionnement.affichage.Affichage;
import fonctionnement.coordonnees.*;
import gameContent.items.Item;
import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;
import gameContent.personnages.Entite;
import gameContent.personnages.perso.Personnage;
import gameContent.sorts.Sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static fonctionnement.affichage.Affichage.*;
import static gameContent.sorts.Sorts.*;

public class Tour {
    private int m_tour;
    private int m_nbActions;
    private Personnage m_pers;
    private List<String> m_actions = new ArrayList<>(); // Actions possibles pour le personnage
    private int m_nbDansListeActions = 0; // Nombre d'actions dans la liste   //TODO a enlever et remplacer avec size()
    private boolean m_finTour; // Indique si le tour est terminé
    private Map m_map; // La carte du jeu, si nécessaire

    public Tour(Personnage pers) {
        m_pers = pers;
        initialiserActions();
    }
    public Tour(Personnage pers, int tour, Map map) {
        this.m_pers = pers;
        initialiserActions();
        this.m_tour = tour+1;
        this.m_nbActions = 3; // Nombre d'actions par tour
        this.m_map = map; // Initialisation de la carte

        this.m_finTour = false;
        while (!m_finTour && m_nbActions > 0) {
            jouerTour();

        }
    }

    private void initialiserActions() {
        this.m_actions.add("Attaquer <Monstre>");
        this.m_actions.add("S'équiper <Objet>");
        this.m_actions.add("Se déplacer <Case>");
        this.m_actions.add("Obtenir des informations <Case>");
        this.m_actions.add("Finir le tour");
        if (!Arrays.equals(m_pers.getSorts(), new boolean[]{false, false, false})) {
            this.m_actions.add("Lancer un sort");
            this.m_nbDansListeActions = 6;
        }
    }

    public void jouerTour(){
        Affichage.afficherTour(this.m_tour, this.m_pers, this.m_map);
        int numaction = Affichage.scanInt();
        if (numaction == 5) {
            m_finTour = true;
            Affichage.afficher("Fin du tour pour " + m_pers.getNom());
            return;
        }
        if (numaction < 1 || numaction > m_nbDansListeActions) {
            Affichage.afficherErreur("Action invalide. Veuillez choisir une action valide.");
            jouerTour();
        }
        else {
            String action = m_actions.get(numaction - 1);
            if (numaction == 2){
                afficherInventaire(this.m_pers);
                choisirEquipement();

            }
            if (numaction == 3){
                seDeplacer();
            }
            Affichage.afficher("Action choisie : " + action);
            m_nbActions--;
            if (numaction == 6) {
                initBoogieWoogie();
            }
        }

    }
    public List<String> getActions(){
        return this.m_actions;
    }
    public int getListActions(){
        return this.m_nbDansListeActions;
    }

    public void choisirEquipement(){
        Affichage.afficher("de quel objet voulez-vous vous équiper ?");
        String objet = Affichage.scanString();
        boolean objetTrouve = false;
        for (Item item : this.m_pers.getInventaire().getObjets()) {
            if (item.getNom().equals(objet)) {
                if (item instanceof Arme){
                    if (this.m_pers.getArme_equipee() != null) {
                        Affichage.afficher("Vous avez déjà une arme équipée. Voulez-vous la remplacer ? (O/N)");
                        String reponse = Affichage.scanString().toUpperCase();
                        if (reponse.equals("O")) {
                            this.m_pers.getInventaire().addArmes(this.m_pers.getArme_equipee());
                            this.m_pers.setEquipement_Arme((Arme)item);
                            this.m_pers.getInventaire().deleteArme((Arme)item);
                        }
                        if (reponse.equals("N")) {
                            Affichage.afficher("Vous avez choisi de ne pas remplacer votre arme équipée.");
                            return; // On quitte la méthode si l'utilisateur ne veut pas remplacer l'arme
                        }
                        //TODO Vérif que l'utilisateur a bien répondu O ou N et est pas con !
                    }
                    else{
                        this.m_pers.setEquipement_Arme((Arme)item);
                        this.m_pers.getInventaire().deleteArme((Arme)item);
                    }
                }
                else {
                    if (this.m_pers.getArmure_equipee() != null) {
                        Affichage.afficher("Vous avez déjà une armure équipée. Voulez-vous la remplacer ? (O/N)");
                        String reponse = Affichage.scanString();
                        if (!reponse.equals("0")) {
                            this.m_pers.getInventaire().addArmures(this.m_pers.getArmure_equipee());
                            this.m_pers.setEquipement_Armure((Armure)item);
                            this.m_pers.getInventaire().deleteArmure((Armure)item);
                        }
                    }
                    else{
                        this.m_pers.setEquipement_Armure((Armure)item);
                        this.m_pers.getInventaire().deleteArmure((Armure)item);
                    }

                }
                Affichage.afficher("Vous vous êtes équipé de : " + item.getNom());
                objetTrouve = true;
                break;
            }
        }

        if (!objetTrouve) {
            Affichage.afficherErreur("Objet introuvable dans l'inventaire. Veuillez réessayer.");
            choisirEquipement();
        }


    }

    public void seDeplacer(){
        Affichage.afficher("Où voulez-vous vous déplacer ? (coordonée x)");
        int x = Affichage.scanInt() - 1;
        char caractere = demandeCaractere();
        int y = caractere - 'A';

        int x_pers = this.m_pers.getX();
        int y_pers = this.m_pers.getY();
        CoordonneesPersonnage posActuelle = new CoordonneesPersonnage(x_pers, y_pers, this.m_pers);
        Coordonnees posVoulue = new CoordonneesCaseVide(x, y);

        if (!Deplacement(posActuelle, posVoulue)) {
            seDeplacer();
        }
        else {
            if (this.m_map.getCase(x,y) instanceof CoordonneesItem){
                boolean item = CaseTresor(this.m_map.getCase(x,y));

                if (!item) {
                    Affichage.afficher("Vous avez choisi de ne pas ramasser l'objet.");
                }
            }
            this.m_map.setCase(x, y, posActuelle); // On met à jour la position sur la carte
            this.m_map.setCase(x_pers, y_pers, new CoordonneesCaseVide(x_pers, y_pers)); // On vide l'ancienne position
            m_pers.setPosition(x, y); // on met à jour la position du personnage
            Affichage.afficherMap(m_map);
        }
    }

    public boolean Deplacement(Coordonnees posActuelle, Coordonnees posVoulue){
        if (posVoulue instanceof CoordonneesMonstre || posVoulue instanceof CoordonneesObstacle){
            Affichage.afficherErreur("vous ne pouvez pas vous déplacer sur une case occupée par un monstre ou un obstacle.");
            return false;
        }
        int vitesse = m_pers.getVitesse()/3;
        //distance = racine carre((x1 - x2)2 + (y1 - y2)2)
        int distance = (int) Math.sqrt(Math.pow(posActuelle.getX() - posVoulue.getX(), 2) + Math.pow(posActuelle.getY() - posVoulue.getY(), 2));
        if (distance > vitesse){
            Affichage.afficherErreur("Vous ne pouvez pas vous déplacer aussi loin, votre vitesse est de " + vitesse + ".");
            return false;
        }
        return true;
    }

    public char demandeCaractere(){
        char lettreMax = (char)('A' + this.m_map.getM_largeur() - 1);
        String lettre = Affichage.scanString().toUpperCase();
        if (lettre.length() != 1 || !Character.isLetter(lettre.charAt(0))) {
            Affichage.afficher("Veuillez entrer une lettre pour la coordonnée y (a/A, b/B, c/C, ...): ");
            return demandeCaractere(); // Redemande si l'entrée n'est pas valide
        }
        // Convertit la lettre en un entier correspondant à la coordonnée y
        char caractere = lettre.charAt(0);
        if (caractere < 'A' || caractere > lettreMax) {
            Affichage.afficherErreur("Veuillez entre une lettre comprise entre A et " + lettreMax + ".");
            return demandeCaractere(); // Redemande si la lettre n'est pas dans l'intervalle
        }
        return caractere; // Retourne le caractère valide
    }

    public boolean CaseTresor(Coordonnees coord){

        Item item = ((CoordonneesItem) coord).getItem();
        Affichage.afficher("Vous avez trouvé un "+item.getNom()+" sur cette case ! Voulez-vous le ramasser ? (O/N)");
        String reponse = Affichage.scanString().toUpperCase();
        if (reponse.equals("O")) {
            if (item instanceof Arme){
                m_pers.getInventaire().addArmes((Arme)item);
            }
            else {
                m_pers.getInventaire().addArmures((Armure)item);
            }

            Affichage.afficher("Vous avez ramassé : " + item.getNom());
            return true;
        }
        else if (reponse.equals("N")) {
            Affichage.afficher("Vous avez choisi de ne pas ramasser l'objet.");
            return false;
        }
        else {
            Affichage.afficherErreur("Réponse invalide. Veuillez répondre par O ou N.");
            return CaseTresor(coord); // Redemande si la réponse n'est pas valide
        }
    }

    public void initBoogieWoogie() {
        Affichage.afficher("quel sort voulez vous lancer ?");
        String sort = Affichage.scanString().toUpperCase();
        if (sort.equals("GUERISON")) {
            Affichage.afficher("Vous avez choisi de lancer le sort Guérison.");
            Guerison(this.m_pers);
        }
        else if (sort.equals("BOOGIEWOOGIE")) {
            Affichage.afficher("Vous avez choisi de lancer le sort Boogie Woogie.");
            Affichage.afficher("Quel personnage voulez vous échanger ?");
            int xperso1 = Affichage.scanInt() - 1;
            char caractere = demandeCaractere();
            int yperso1 = caractere - 'A';
            Coordonnees coord1 = m_map.getCase(xperso1, yperso1);
            Entite perso1 = null;
            if (coord1 instanceof CoordonneesPersonnage) {
                perso1 = ((CoordonneesPersonnage) coord1).getPersonnage();

                Affichage.afficher("hehee j'ai trouvé un personnage");
            }
            else if (coord1 instanceof CoordonneesMonstre) {
                perso1 = ((CoordonneesMonstre) coord1).getMonstre();

                Affichage.afficher("hehee j'ai trouvé un monstre");
            }
            Affichage.afficher("Quel personnage voulez-vous échanger avec ?");
            int xperso2 = Affichage.scanInt() - 1;
            char caractere2 = demandeCaractere();
            int yperso2 = caractere2 - 'A';
            Coordonnees coord2 = m_map.getCase(xperso2, yperso2);
            Entite perso2 = null;
            if (coord2 instanceof CoordonneesPersonnage) {
                perso2 = ((CoordonneesPersonnage) coord2).getPersonnage();

                Affichage.afficher("hehee j'ai trouvé un personnage");
            }
            else if (coord2 instanceof CoordonneesMonstre) {
                perso2 = ((CoordonneesMonstre) coord2).getMonstre();

                Affichage.afficher("hehee j'ai trouvé un monstre");
            }
            if (perso1 == null || perso2 == null) {
                Affichage.afficherErreur("Un des personnages n'existe pas ou n'est pas valide. Veuillez réessayer.");
                jouerTour();
                return;
            }
            Sorts.BoogieWoogie(perso1, perso2, m_map);
        }
        else {
            Affichage.afficherErreur("Sort inconnu. Veuillez réessayer.");
            jouerTour();
        }

    }




}
