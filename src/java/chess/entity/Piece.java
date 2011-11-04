package chess.entity;

public class Piece
{
    protected String mColor;
    protected Piece.Type mType;

    public Piece(String color, Piece.Type type)
    {
        mColor = color;
        mType = type;
    }

    /**
     * Retourne le type de la pièce
     */
    public Piece.Type getType()
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

    public enum Type
    {
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING,
        PAWN
    }
}
