package chess.entity;

public class Piece
{
    protected String mColor;
    protected String mType;

    public Piece(String type, String color)
    {
        mColor = color;
        mType = type;
    }

    /**
     * Retourne le type de la pièce
     */
    public String getType()
    {
        return mType;
    }

    /**
     * Retourne la couleur de la pièce
     */
    public String getColor()
    {
        return mColor;
    }
}
