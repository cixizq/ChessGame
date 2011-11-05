package chess.entity;

public class Movement
{
    protected Case mSource;
    protected Case mDestination;
    protected String mColor;

    public Movement(Case src, Case dst, String color)
    {
        mSource = src;
        mDestination = dst;
        mColor = color;
    }

    public Movement()
    {
    }

    public Case getSource()
    {
        return mSource;
    }

    public void setSource(Case src)
    {
        mSource = src;
    }

    public Case getDestination()
    {
        return mDestination;
    }

    public void setDestination(Case dst)
    {
        mDestination = dst;
    }

    public String getColor()
    {
        return mColor;
    }

    public void setColor(String color)
    {
        mColor = color;
    }
}
