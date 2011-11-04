package chess;

import chess.entity.Game;
import chess.entity.Player;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GiveUp extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }

        Player player = (Player) session.getAttribute("player");
        Game game = (Game) session.getAttribute("game");

        game.giveUp(player);
    }

    @Override
    public String getServletInfo()
    {
        return "Give up servlet";
    }
}
