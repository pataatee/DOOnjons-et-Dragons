package gameContent.items;

import gameContent.items.armes.Arme;
import gameContent.items.armes.ArmeGuerre;
import gameContent.items.armures.Armure;
import gameContent.items.armures.ArmureLourde;

public class Equipement {

    private Arme m_arme;
    private Armure m_armure;


    public void Equipement(Arme arme, Armure armure){
        this.m_arme = arme;
        this.m_armure = armure;
    }
    public void Equipement(){
        this.m_arme = null;
        this.m_armure = null;
    }


    public Arme getM_arme() {
        return m_arme;
    }

    public Armure getM_armure() {
        return m_armure;
    }

    public int setArme(Arme arme) {
        //si le personnage portait une arme de guerre, on modifie la vitesse du perso
        int valretour = 0;
        if (this.m_arme != null){
            if (this.m_arme instanceof ArmeGuerre){
                valretour += 2;
            }
        }

        //si la nouvelle arme est une arme de guerre, on modifie la vitesse du perso
        this.m_arme = arme;
        if (this.m_arme instanceof ArmeGuerre){
            valretour -= 2;
        }
        return valretour;
    }

    public int setArmure(Armure armure) {

        int valretour = 0;
        if (this.m_armure != null){
            if (this.m_armure instanceof ArmureLourde){
                valretour += 4;
            }
        }

        //si la nouvelle arme est une arme de guerre, on modifie la vitesse du perso
        this.m_armure = armure;
        if (this.m_armure instanceof ArmureLourde){
            valretour -= 4;
        }
        return valretour;
    }
}
