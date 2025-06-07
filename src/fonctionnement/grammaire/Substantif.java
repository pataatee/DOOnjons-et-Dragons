package fonctionnement.grammaire;

public class Substantif // un nom
{
    private final String m_nom;
    private final Genre m_genre;
    private final boolean m_elision;

    public Substantif(String nom, Genre genre)
    {
        this.m_nom = nom;
        this.m_genre = genre;
        this.m_elision = !nom.isEmpty() && "aeiouy".indexOf(nom.charAt(0)) != -1;;
    }


    public String getNom()
    {
        return this.m_nom;
    }

    public String avecArticleDefini()
    {
        if(this.m_elision)
        {
            return "l'" + this.m_nom;
        }
        else
        {
            return this.m_genre.getArticleDefini() + " " + this.m_nom;
        }
    }

    public String avecArticleIndefini()
    {
        return this.m_genre.getArticleIndefini() + " " + this.m_nom;
    }

    public String avecArticlePartitif()
    {
        if(this.m_elision)
        {
            return "de l'" + this.m_nom;
        }
        else
        {
            return this.m_genre.getArticlePartitif() + " " + this.m_nom;
        }
    }

    @Override
    public boolean equals(Object other)
    {
        if(other.getClass() != getClass())
        {
            return false;
        }
        Substantif otherSubstantif = (Substantif) other;
        return this.m_nom.equals(otherSubstantif.m_nom);
    }

    @Override
    public String toString(){
        return "Substantif("+m_nom+", "+m_genre.toString()+")";
    }
}
