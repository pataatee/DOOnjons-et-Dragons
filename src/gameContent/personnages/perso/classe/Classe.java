package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;

public interface Classe {


    public int getPvs();
    public Armurerie getArmurerie();
    public String getNom();
    public Armurerie modifyArmurerie();
}
