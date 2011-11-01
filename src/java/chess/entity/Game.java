package chess.entity;

import java.util.UUID;

public class Game
{
    protected UUID mId;
    protected boolean mInitialized;

    public Game()
    {
        // Génération d'un identifiant unique
        mId = UUID.randomUUID();
    }

    /**
     * Retourne l'identifiant de la partie
     */
    public String getId()
    {
        return mId.toString();
    }

    public void setInitialized(boolean initialized)
    {
        mInitialized = initialized;
    }

    public boolean isInitialized()
    {
        return mInitialized;
    }
}
