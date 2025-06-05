package gameContent.personnages.monstre;

public class Espece {
    private String m_nomEspece;
    private int m_numero;

    public Espece(String espece, int num) {
        m_nomEspece = espece;
        m_numero = num;
    }

    public Espece(String espece) {
        m_nomEspece = espece;
    }

    public String getNomEspece() {
        return m_nomEspece;
    }

    public int getNum() {
        return m_numero;
    }
}
