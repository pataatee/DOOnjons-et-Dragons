package gameContent.items.armes;

public class ArmeDistance extends Arme{
    public ArmeDistance(String nom, int attaque, int portee){
        super(nom, attaque, portee);
    }
    public static final ArmeDistance Fronde = new ArmeDistance("Fronde", 3, 5);
}