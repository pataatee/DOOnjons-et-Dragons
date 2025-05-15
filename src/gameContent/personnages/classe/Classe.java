package gameContent.personnages.classe;

import gameContent.items.Armurerie;

import java.util.ArrayList;
import java.util.List;

public abstract class Classe {
    protected String m_nom;
    protected int m_pvs;
    protected List<Armurerie> m_armurerie = new ArrayList<>();
}
