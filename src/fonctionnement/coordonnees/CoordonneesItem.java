package fonctionnement.coordonnees;

import fonctionnement.de.De;
import fonctionnement.grammaire.Feminin;
import fonctionnement.grammaire.Masculin;
import fonctionnement.grammaire.Substantif;
import gameContent.items.Item;
import gameContent.items.armes.*;
import gameContent.items.armures.*;

import java.util.List;
import java.util.Random;

public class CoordonneesItem extends Coordonnees{
    private Item m_item;
    private static final List<Item> m_items = List.of(
            new ArmeCourante("Baton", new De(1,6), 1, new Substantif("baton", new Masculin())),
            new ArmeGuerre("Rapière", new De(1,4), 1, new Substantif("Rapière", new Feminin())),
            new ArmeGuerre("épée longue", new De(1,4), 2, new Substantif("épée longue", new Feminin())),
            new ArmeCourante("Masse d'armes", new De(1,6), 1, new Substantif("Masse d'armes", new Feminin())),
            new ArmeDistance("Fronde", new De(1,4), 6, new Substantif("Fronde", new Feminin())),
            new ArmeDistance("Arbalète légère", new De(1,8), 16, new Substantif("Arbalète légère", new Feminin())),
            new ArmeDistance("Arc court", new De(1,6), 16, new Substantif("Arc court", new Masculin())),
            new ArmeGuerre("épée à deux mains",  new De (2,6), 1, new Substantif("épée a deux mains", new Feminin())),
            new ArmureLegere("Armure d'écailles", 9, new Substantif("épée longue", new Feminin())),
            new ArmureLegere("Demi-plates", 10, new Substantif("épée longue", new Feminin())),
            new ArmureLourde("Cotte de maille", 11, new Substantif("épée longue", new Feminin())),
            new ArmureLourde("Harnois", 12, new Substantif("baton", new Masculin()))
    );
    public CoordonneesItem(int x ,int y) {
        super(x, y,new char[] {' ', '*', ' '});
        Random rand = new Random();
        int index = rand.nextInt(m_items.size()); // entre 0 et size - 1
        this.m_item = m_items.get(index);
    }
    public CoordonneesItem(int x ,int y, int i) {
        super(x, y,new char[] {' ', '*', ' '});
        this.m_item = m_items.get(i);

    }

    public Item getItem() {
        return m_item;
    }
}
