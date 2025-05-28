package gameContent.personnages.perso;

import de.De;
import gameContent.items.armes.Arme;
import gameContent.items.armes.ArmeCourante;
import gameContent.items.armes.ArmeDistance;
import gameContent.personnages.Attaque;

public class AttaquePersonnage implements Attaque {
    private int m_degats;

    public AttaquePersonnage(int degats) {
        m_degats = degats;
    }
    public int getDegats() {
        return m_degats;
    }

    public void setDegats(Personnage perso) {
        int degats = 0;
        De de = new De(20, 1);
        int resultatDe = de.lancer_de();
        degats += resultatDe;
        // faire un test : si arme utilisée distance, ajouter dextérité ; si corps a corps, ajouter force
        degats += perso.getDegats(); // TODO aller voir si le getDegats prend en compte l'arme utilisée
        this.m_degats = degats;
    }
}
