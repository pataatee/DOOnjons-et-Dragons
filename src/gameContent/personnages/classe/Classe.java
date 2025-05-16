package gameContent.personnages.classe;

import gameContent.items.Armurerie;
import gameContent.personnages.Personnage;

import java.util.ArrayList;
import java.util.List;

public abstract class Classe {
    protected String m_nom;
    protected Armurerie m_armurerie;
    protected int pvs = 0;

    public int getPvs() {
        return pvs;
    }
}
