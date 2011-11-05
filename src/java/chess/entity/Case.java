package chess.entity;

public class Case
{
    protected int mX;
    protected int mY;

    public Case(int x, int y)
    {
        mX = x;
        mY = y;
    }

    public int getX()
    {
        return mX;
    }

    public int getY()
    {
        return mY;
    }

    /**
     * Transforme une case sous la forme "D1" en une case
     */
    public static Case transform(String format)
    {
        if (format.length() != 2) {
            return null;
        }

        format = format.toLowerCase();

        char x = format.charAt(0);
        char y = format.charAt(1);

        return new Case(Integer.parseInt(String.valueOf(x)), y - 'a');
    }
}
