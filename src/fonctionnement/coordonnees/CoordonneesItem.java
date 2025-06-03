package fonctionnement.coordonnees;

import gameContent.items.Item;

public class CoordonneesItem extends Coordonnees{
    private Item m_item;
    public CoordonneesItem(int x ,int y) {
        super(x, y,new char[] {' ', '*', ' '});
    }
}
