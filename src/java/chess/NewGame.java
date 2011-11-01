package chess;

import chess.entity.Game;
import chess.entity.GameManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Julien
 */
public class NewGame extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Cette page ne sert à rien en GET, hop on redirige !
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String nick  = request.getParameter("nick");
        String color = request.getParameter("color");

        // Vérification
        if (nick == null || nick.isEmpty() || color == null) {
            response.sendRedirect("index.html");
            return;
        }

        ServletContext context = getServletContext();
        GameManager manager = (GameManager) context.getAttribute("manager");

        if (manager == null) {
            // Premier passage, on s'occupe de créer le manager
            manager = new GameManager();
            context.setAttribute("manager", manager);
        }

        HttpSession session = request.getSession(true);

        Game game = manager.createGame();
        game.setInitialized(true);

        session.setAttribute("nick", nick);
        session.setAttribute("color", color);
        session.setAttribute("game", game);

        response.sendRedirect("chess.jsp");
    }

    @Override
    public String getServletInfo()
    {
        return "Start a new game";
    }
}
