package gameContent.items.armes;
import fonctionnement.de.De;
import gameContent.items.Item;

public interface Arme extends Item {
    public String getNom();
    public int getDegats();
    public int getPortee();
}
