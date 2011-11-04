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
    protected Player mCurrentPlayer;

    public Game()
    {
        mId = UUID.randomUUID();
        mPieces = new Piece[8][8];
        mPlayers = new ArrayList<Player>();

        initialize();
    }

    /**
     * Initialisation de la partie
     */
    private void initialize()
    {
        mPieces[0][0] = new Piece("black", Piece.Type.ROOK);
        mPieces[0][1] = new Piece("black", Piece.Type.KNIGHT);
        mPieces[0][2] = new Piece("black", Piece.Type.BISHOP);
        mPieces[0][3] = new Piece("black", Piece.Type.QUEEN);
        mPieces[0][4] = new Piece("black", Piece.Type.KING);
        mPieces[0][5] = new Piece("black", Piece.Type.BISHOP);
        mPieces[0][6] = new Piece("black", Piece.Type.KNIGHT);
        mPieces[0][7] = new Piece("black", Piece.Type.ROOK);

        mPieces[1][0] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][1] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][2] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][3] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][4] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][5] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][6] = new Piece("black", Piece.Type.PAWN);
        mPieces[1][7] = new Piece("black", Piece.Type.PAWN);

        mPieces[6][0] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][1] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][2] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][3] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][4] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][5] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][6] = new Piece("white", Piece.Type.PAWN);
        mPieces[6][7] = new Piece("white", Piece.Type.PAWN);

        mPieces[7][0] = new Piece("white", Piece.Type.ROOK);
        mPieces[7][1] = new Piece("white", Piece.Type.KNIGHT);
        mPieces[7][2] = new Piece("white", Piece.Type.BISHOP);
        mPieces[7][3] = new Piece("white", Piece.Type.QUEEN);
        mPieces[7][4] = new Piece("white", Piece.Type.KING);
        mPieces[7][5] = new Piece("white", Piece.Type.BISHOP);
        mPieces[7][6] = new Piece("white", Piece.Type.KNIGHT);
        mPieces[7][7] = new Piece("white", Piece.Type.ROOK);
    }

    public void giveUp(Player player)
    {
    }

    /**
     * Effectue un mouvement.
     *
     * @param src La case de départ
     * @param dst La case d'arrivé
     *
     * @return Vrai si le mouvement a été réalisé
     */
    public boolean move(Case src, Case dst)
    {
        if (!isCorrect(src) || !isCorrect(dst)) {
            return false;
        }

        String currentColor = mCurrentPlayer.getColor();

        Piece pieceSrc = getPiece(src);
        Piece pieceDst = getPiece(dst);

        // La pièce source n'existe pas
        if (pieceSrc == null || !pieceSrc.getColor().equals(currentColor)) {
            return false;
        }

        // Il essaye de prendre un pion à lui ...
        if (pieceDst != null && pieceDst.getColor().equals(currentColor)) {
            return false;
        }

        setPiece(dst, pieceSrc);
        setPiece(src, null);

        return true;
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

    public Player getCurrentPlayer()
    {
        return mCurrentPlayer;
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
