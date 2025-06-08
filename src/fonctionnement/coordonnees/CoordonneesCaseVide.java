package fonctionnement.coordonnees;

public class CoordonneesCaseVide extends Coordonnees{
    public CoordonneesCaseVide(int x ,int y) {
        super(x, y,new char[] {' ', '.', ' '});
    }

    @Override
    public String getCaseVide() {
        return "blabla";
    }
}
