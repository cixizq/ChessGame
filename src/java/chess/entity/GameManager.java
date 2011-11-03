package chess.entity;

import java.util.Collection;
import java.util.HashMap;

/**
 * S'occupe de la gestion de l'ensemble des parties
 */
public class GameManager
{
    protected HashMap<String, Game> mGames;

    public GameManager()
    {
        mGames = new HashMap<String, Game>();
    }

    public Game createGame()
    {
        Game game = new Game();
        game.setInitialized(true);

        mGames.put(game.getId(), game);

        return game;
    }

    /**
     * Retourne la liste des parties
     */
    public Collection<Game> getGames()
    {
        return mGames.values();
    }

    /**
     * Retourne une partie en fonction de son identifiant
     *
     * @param id L'identifiant de la partie
     */
    public Game getGame(String id)
    {
        return mGames.get(id);
    }
}
