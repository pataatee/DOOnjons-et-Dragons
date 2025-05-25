package gameContent.monstres;

import gameContent.personnages.Caracteristiques;

public class Monstre {
    private String m_espece;
    private int m_numero;
    private Attaque m_attaque;
    private Caracteristiques m_caracteristiques;

    public Monstre(String espece, int numero) {
        this.m_espece = espece;
        this.m_numero = numero;
    }
}
