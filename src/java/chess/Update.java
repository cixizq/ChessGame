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

public class Update extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session == null) {
            // Il n'y a pas de session .. bizarre ?
            return;
        }

        int state = -1;

        Game game = (Game) session.getAttribute("game");
        Player player = (Player) session.getAttribute("player");

        if (game.isEnded()) {
            state = 3;
        } else {

        }

        request.setAttribute("state", state);

        RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Update servlet";
    }
}
