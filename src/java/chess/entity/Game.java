package chess.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class Game
{
    protected UUID mId;
    protected boolean mInitialized;
    protected Piece[][] mPieces;
    protected List<Player> mPlayers;
    protected Movement mLastMovement;
    protected boolean mRunning;
    protected boolean mEnded;
    protected Player mCurrentPlayer;

    public Game()
    {
        mId = UUID.randomUUID();
        mPieces = new Piece[8][8];
        mPlayers = new ArrayList<Player>();
        mRunning = false;
        mCurrentPlayer = null;

        initialize();
    }

    /**
     * Initialisation de la partie
     */
    private void initialize()
    {
        mPieces[0][0] = new Piece("rook", "black");
        mPieces[0][1] = new Piece("knight", "black");
        mPieces[0][2] = new Piece("bishop", "black");
        mPieces[0][3] = new Piece("queen", "black");
        mPieces[0][4] = new Piece("king", "black");
        mPieces[0][5] = new Piece("bishop", "black");
        mPieces[0][6] = new Piece("knight", "black");
        mPieces[0][7] = new Piece("rook", "black");

        mPieces[1][0] = new Piece("pawn", "black");
        mPieces[1][1] = new Piece("pawn", "black");
        mPieces[1][2] = new Piece("pawn", "black");
        mPieces[1][3] = new Piece("pawn", "black");
        mPieces[1][4] = new Piece("pawn", "black");
        mPieces[1][5] = new Piece("pawn", "black");
        mPieces[1][6] = new Piece("pawn", "black");
        mPieces[1][7] = new Piece("pawn", "black");

        mPieces[6][0] = new Piece("pawn", "white");
        mPieces[6][1] = new Piece("pawn", "white");
        mPieces[6][2] = new Piece("pawn", "white");
        mPieces[6][3] = new Piece("pawn", "white");
        mPieces[6][4] = new Piece("pawn", "white");
        mPieces[6][5] = new Piece("pawn", "white");
        mPieces[6][6] = new Piece("pawn", "white");
        mPieces[6][7] = new Piece("pawn", "white");

        mPieces[7][0] = new Piece("rook", "white");
        mPieces[7][1] = new Piece("knight", "white");
        mPieces[7][2] = new Piece("bishop", "white");
        mPieces[7][3] = new Piece("queen", "white");
        mPieces[7][4] = new Piece("king", "white");
        mPieces[7][5] = new Piece("bishop", "white");
        mPieces[7][6] = new Piece("knight", "white");
        mPieces[7][7] = new Piece("rook", "white");
    }

    public void giveUp(Player player)
    {
        mEnded = true;
    }

    /**
     * Effectue un mouvement.
     *
     * @param src La case de départ
     * @param dst La case d'arrivé
     * @param player Le joueur qui souhaite effectuer le mouvement
     *
     * @return Vrai si le mouvement a été réalisé
     */
    public boolean move(Case src, Case dst, Player player)
    {
        if (!mRunning || mCurrentPlayer != player) {
            // Le jeu n'est pas lancé
            return false;
        }

        if (!isCorrect(src) || !isCorrect(dst)) {
            // Une des cases n'est pas sur le plateau
            return false;
        }

        Piece pieceSrc = getPiece(src);
        Piece pieceDst = getPiece(dst);

        if (pieceSrc == null) {
            // Il essaye de jouer une pièce qui n'existe pas ?
            return false;
        }

        // Il essaye de placer une pièce sur une case qui est déjà à lui
        if (pieceDst != null && pieceSrc.getColor().equals(pieceDst.getColor())) {
            return false;
        }

        setPiece(dst, pieceSrc);
        setPiece(src, null);

        // On sauvegarde le dernier mouvement
        mLastMovement = new Movement(src, dst, mCurrentPlayer.getColor());

        return true;
    }

    /**
     * Démarrer le jeu
     */
    public void start()
    {
        // Si le jeu n'a pas deux joueurs, impossible de le lancer
        if (!isFull()) {
            return;
        }

        mCurrentPlayer = (getFirstPlayer().getColor().equals("white")) ? getFirstPlayer() : getSecondPlayer();
        mRunning = true;
    }

    /**
     * Retourne si la partie est pleine (c'est-à-dire qu'il y
     * a deux joueurs)
     */
    public boolean isFull()
    {
        return mPlayers.size() == 2;
    }

    /**
     * Retourne l'identifiant de la partie
     */
    public String getId()
    {
        return mId.toString();
    }

    public boolean isRunning()
    {
        return mRunning;
    }

    public boolean isEnded()
    {
        return mEnded;
    }

    public void setEnded(boolean ended)
    {
        mEnded = ended;
    }

    public void switchPlayer()
    {
        mCurrentPlayer = (mCurrentPlayer == getFirstPlayer()) ? getSecondPlayer() : getFirstPlayer();
    }

    /**
     * Retourne le premier joueur de la partie
     */
    public Player getFirstPlayer()
    {
        if (mPlayers.isEmpty()) {
            return null;
        }

        return mPlayers.get(0);
    }

    /**
     * Retourne le second joueur de la partie
     */
    public Player getSecondPlayer()
    {
        if (!isFull()) {
            return null;
        }

        return mPlayers.get(1);
    }

    public Player getCurrentPlayer()
    {
        return mCurrentPlayer;
    }

    /**
     * Place une pièce sur une case précise
     *
     * @param location La case
     * @param piece    La pièce à placer
     */
    public void setPiece(Case location, Piece piece)
    {
        if (!isCorrect(location)) {
            return;
        }

        mPieces[location.getX()][location.getY()] = piece;
    }

    /**
     * Retourne la pièce présente à la case
     *
     * @param location La position de la case
     */
    public Piece getPiece(Case location)
    {
        if (!isCorrect(location)) {
            return null;
        }

        return mPieces[location.getX()][location.getY()];
    }

    /**
     * Ajoute un joueur à la partie en cours
     *
     * @param player Le joueur
     *
     * @return Retourne faux si la partie est complète
     */
    public boolean addPlayer(Player player)
    {
        if (isFull()) {
            return false;
        }

        mPlayers.add(player);

        return true;
    }

    public void removeLastMovement()
    {
        mLastMovement = null;
    }

    public Movement getLastMovement()
    {
        return mLastMovement;
    }

    public boolean isInitialized()
    {
        return mInitialized;
    }

    public void setInitialized(boolean initialized)
    {
        mInitialized = initialized;
    }

    public String generateUrl(HttpServletRequest request)
    {
        String url = request.getRequestURL().toString();

        return url.substring(0, url.lastIndexOf('/')) + "/joingame?gid=" + getId();
    }

    private boolean isCorrect(Case position)
    {
        return position.getX() < 8 && position.getX() >= 0 && position.getY() < 8 && position.getY() >= 0;
    }
}
