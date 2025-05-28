package fonctionnement.de;

import java.util.Random; //-->Random rand = new Random(); int entier = rand.nextInt(10); donne un entier entre 0 et 9

public class De {
    private int m_faces;
    private int m_nbdedes;

    public De (int nbdedes, int faces){
        this.m_faces = faces;
        this.m_nbdedes = nbdedes;
    }

    public int lancer_de(){
        int total = 0;
        Random rand = new Random();
        for (int i = 0; i<m_nbdedes; i++){
            total += rand.nextInt(m_faces+1);
        }
        return total;
    }
    public String toString(){
        return m_nbdedes+"d"+m_faces;
    }
}

