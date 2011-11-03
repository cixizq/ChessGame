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

public class JoinGame extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String nick = request.getParameter("nick");

        if (nick == null || nick.isEmpty()) {
            response.sendRedirect("index.html");
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("nick", nick);

        response.sendRedirect("chess.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idGame = request.getParameter("gid");

        if (idGame == null || idGame.isEmpty()) {
            response.sendRedirect("index.html");
            return;
        }

        ServletContext context = getServletContext();
        GameManager manager = (GameManager) context.getAttribute("manager");

        if (manager == null) {
            // Premier passage donc impossible qu'il y ai déjà un jeu ...
            response.sendRedirect("index.html");
            return;
        }

        Game game = manager.getGame(idGame);

        if (game == null || game.isFull()) {
            // Le jeu n'existe pas ou il y a déjà 2 joueurs
            response.sendRedirect("index.html");
            return;
        }

        HttpSession session = request.getSession(true);
        
        session.setAttribute("game", game);
        session.setAttribute("color", game.getOtherColor());

        RequestDispatcher dispatcher = request.getRequestDispatcher("joingame.jsp");
        dispatcher.forward(request, response);
    } 

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
