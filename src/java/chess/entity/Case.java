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
}
