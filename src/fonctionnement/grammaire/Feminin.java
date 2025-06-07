package fonctionnement.grammaire;

public class Feminin implements Genre
{
    @Override
    public String getArticleDefini()
    {
        return "la";
    }

    @Override
    public String getArticleIndefini()
    {
        return "une";
    }

    @Override
    public String getArticlePartitif()
    {
        return "de la";
    }

    @Override
    public String getArticleDemonstratif()
    {
        return "cette";
    }

    @Override
    public String toString(){
        return "Feminin";
    }

}
