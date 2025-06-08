package gameContent.items;


import gameContent.items.armes.Arme;
import gameContent.items.armures.Armure;

public interface Item //extends fonctionnement.coordonnees.Coordonnees {
{
    public String getNom();
    public String avecArticleDefini();
    public String avecArticleIndefini();
    public String avecArticlePartitif();
    public default Arme getArme(){return null;};
    public default Armure getArmure(){return null;};
}
