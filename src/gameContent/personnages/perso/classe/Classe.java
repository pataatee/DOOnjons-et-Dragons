package gameContent.personnages.perso.classe;

import gameContent.items.Armurerie;
import gameContent.sorts.*;

import java.util.Optional;

public interface Classe {


    public int getPvs();
    public Armurerie getArmurerie();
    public String getNom();
    public Armurerie modifyArmurerie();
    default Optional<Sorts>[] definirSorts() {
        Optional<Sorts>[] sorts = (Optional<Sorts>[]) new Optional[Sorts.m_nbSorts];
        for (int i = 0; i < sorts.length; i++) {
            sorts[i] = Optional.empty();
        }
        return sorts;
    }
}
