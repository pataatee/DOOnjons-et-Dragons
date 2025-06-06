package gameContent.items.armures;

import gameContent.items.Item;
import gameContent.items.armes.Arme;

public interface Armure extends Item {
    public String getNom();
    public int getClasseArmure();
    public String avecArticleDefini();
    public String avecArticleIndefini();
    public String avecArticlePartitif();
}
