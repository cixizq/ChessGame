package chess.entity;

public class Piece
{
    protected Piece.Color mColor;
    protected Piece.Type mType;

    public Piece(Piece.Color color, Piece.Type type)
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
    public Piece.Color getColor()
    {
        return mColor;
    }

    public enum Color
    {
        WHITE,
        BLACK
    }

    public enum Type
    {
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }
}
