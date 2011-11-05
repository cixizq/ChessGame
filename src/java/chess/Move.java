package chess;

import chess.entity.Case;
import chess.entity.Game;
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

        String src = request.getParameter("src");
        String dst = request.getParameter("dst");

        if (src == null || dst == null || src.isEmpty() || dst.isEmpty()) {
            return;
        }

        Case caseSrc = Case.transform(src);
        Case caseDst = Case.transform(dst);

        if (caseSrc == null || caseDst == null) {
            return;
        }

        // On effectue le mouvement
        game.move(caseSrc, caseDst);

        RequestDispatcher dispatcher = request.getRequestDispatcher("move.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Move a piece servlet";
    }
}
