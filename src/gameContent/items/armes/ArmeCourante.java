package gameContent.items.armes;

public class ArmeCourante extends Arme{
    public ArmeCourante(String nom, int attaque, int portee){
        super(nom, attaque, portee);
    }
    public static final ArmeCourante Baton = new ArmeCourante("Baton", 2, 2);
}


