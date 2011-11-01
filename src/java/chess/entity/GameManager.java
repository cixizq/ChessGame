package chess.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * S'occupe de la gestion de l'ensemble des parties
 */
public class GameManager
{
    protected Set<Game> mGames;

    public GameManager()
    {
        mGames = new HashSet<Game>();
    }

    public Game createGame()
    {
        Game game = new Game();
        mGames.add(game);

        return game;
    }

    /**
     * Retourne la liste des parties
     */
    public Set<Game> getGames()
    {
        return mGames;
    }
}
