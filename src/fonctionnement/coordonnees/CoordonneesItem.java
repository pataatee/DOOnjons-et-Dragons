package fonctionnement.coordonnees;

import fonctionnement.de.De;
import gameContent.items.Item;
import gameContent.items.armes.ArmeCourante;
import gameContent.items.armes.ArmeDistance;
import gameContent.items.armes.ArmeGuerre;
import gameContent.items.armures.ArmureLegere;
import gameContent.items.armures.ArmureLourde;

import java.util.List;
import java.util.Random;

public class CoordonneesItem extends Coordonnees{
    private Item m_item;
    private static final List<Item> m_items = List.of(
            new ArmeCourante("Baton", new De(1,4), 2),
            new ArmeCourante("Rapière", new De(1,4), 1),
            new ArmeCourante("épée longue", new De(1,4), 2),
            new ArmeCourante("Masse d'armes", new De(1,4), 1),
            new ArmeDistance("Fronde", new De(1,4), 5),
            new ArmeDistance("Arbalète légère", new De(1,4), 10),
            new ArmeDistance("Arc court", new De(1,4), 9),
            new ArmeGuerre("épée à deux mains",  new De (2,6), 1),
            new ArmureLegere("Armure d'écailles", 9),
            new ArmureLegere("Demi-plates", 10),
            new ArmureLourde("Cotte de maille", 11),
            new ArmureLourde("Harnois", 12)
    );
    public CoordonneesItem(int x ,int y) {
        super(x, y,new char[] {' ', '*', ' '});
        Random rand = new Random();
        int index = rand.nextInt(m_items.size()); // entre 0 et size - 1
        this.m_item = m_items.get(index);
    }

    public Item getItem() {
        return m_item;
    }
}
