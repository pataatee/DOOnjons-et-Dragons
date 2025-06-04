package gameContent.items;

import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;

import java.util.ArrayList;
import java.util.List;
public class Armurerie {
    private List<Arme> m_armes;
    private List<Armure> m_armures;

    public Armurerie(){
        this.m_armures = new ArrayList<Armure>();
        this.m_armes = new ArrayList<Arme>();
    }

    public List<Arme> getArmes() {
        return m_armes;
    }
    public void addArmes(Arme armes) {
        this.m_armes.add(armes);
    }
    public List<Armure> getArmures() {
        return m_armures;
    }
    public void addArmures(Armure armures) {
        this.m_armures.add(armures);
    }
    public void deleteArme(Arme arme) {
        this.m_armes.remove(arme);
    }
    public void deleteArmure(Armure armure) {
        this.m_armures.remove(armure);
    }
    public List<Item> getObjets() {
        List<Item> liste = new ArrayList<>();
        for (Arme a : m_armes) liste.add(a);
        for (Armure ar : m_armures) liste.add(ar);
        return liste; // ou autre nom de ta liste interne
    }
}

