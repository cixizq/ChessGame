package chess;

import chess.entity.Game;
import chess.entity.Player;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Move extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        Game game = (Game) session.getAttribute("game");
        Player player = (Player) session.getAttribute("player");

        if (game.getCurrentPlayer() != player) {
            // Ce n'est pas à lui de joueur
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("move.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Move a piece servlet";
    }
}
