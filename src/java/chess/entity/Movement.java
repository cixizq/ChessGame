package chess.entity;

public class Movement
{
    protected Case mSource;
    protected Case mDestination;

    public Movement(Case src, Case dst)
    {
        mSource = src;
        mDestination = dst;
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
}
