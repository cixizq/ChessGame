package chess.entity;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class Game
{
    protected UUID mId;
    protected boolean mInitialized;

    protected String mFirstPlayerColor;

    public Game()
    {
        // Génération d'un identifiant unique
        mId = UUID.randomUUID();
    }

    public void giveUp(String color)
    {
    }

    /**
     * Regarde si le jeu comporte déjà deux joueurs
     */
    public boolean isFull()
    {
        return false;
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

    public void setFirstPlayerColor(String color)
    {
        mFirstPlayerColor = color;
    }

    public String getOtherColor()
    {
        return (mFirstPlayerColor.equals("black")) ? "white" : "black";
    }

    public boolean isInitialized()
    {
        return mInitialized;
    }

    public String generateUrl(HttpServletRequest request)
    {
        return "/joingame?gid=" + getId();
    }
}
