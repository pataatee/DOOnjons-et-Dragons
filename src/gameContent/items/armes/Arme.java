package gameContent.items.armes;
import fonctionnement.de.De;
import gameContent.items.Item;

public interface Arme extends Item {
    public String getNom();
    public int getDegats();
    public int getPortee();
    public void addBonus(); // Permet de définir si l'arme a un bonus ou non
    public String avecArticleDefini();
    public String avecArticleIndefini();
    public String avecArticlePartitif();
}
