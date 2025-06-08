package fonctionnement.grammaire;

public class Masculin implements Genre
{
    @Override
    public String getArticleDefini()
    {
        return "le ";
    }

    @Override
    public String getArticleIndefini()
    {
        return "un ";
    }

    @Override
    public String getArticlePartitif()
    {
        return "du ";
    }

    @Override
    public String getArticleDemonstratif()
    {
        return "ce ";
    }

    @Override
    public String toString(){
        return "Masculin";
    }
}
