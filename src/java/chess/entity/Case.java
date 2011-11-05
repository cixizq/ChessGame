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

    public String reverseTransform()
    {
        int x = 8 - mX;
        char y = (char)(mY + 'A');

        return "" + x + y;
    }

    /**
     * Transforme une case sous la forme "D1" en une case
     */
    public static Case transform(String format)
    {
        if (format.length() != 2) {
            return null;
        }

        char x = format.charAt(0);
        char y = format.charAt(1);

        /**
         * x => 8
         * y => A
         */

        return new Case(8 - Integer.parseInt(String.valueOf(x)), y - 'A');
    }
}
