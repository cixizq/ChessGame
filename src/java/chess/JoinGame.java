package chess;

import chess.entity.Game;
import chess.entity.GameManager;
import chess.entity.Player;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter out = response.getWriter();

        String nick = request.getParameter("nick");

        if (nick == null || nick.isEmpty()) {
            out.println("Le paramètre 'nick' est manquant.");
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            out.println("La session est à null ... il est nécessaire de recommencer l'étape précédente.");
            return;
        }

        // On ajoute le pseudo au joueur déjà créé
        Player player = (Player) session.getAttribute("player");
        player.setNick(nick);

        // Maintenant la partie commence !
        Game game = (Game) session.getAttribute("game");

        game.addPlayer(player);
        game.start();

        response.sendRedirect("chess.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();

        String idGame = request.getParameter("gid");

        if (idGame == null || idGame.isEmpty()) {
            response.sendRedirect("index.html");
            return;
        }

        ServletContext context = getServletContext();
        GameManager manager = (GameManager) context.getAttribute("manager");

        if (manager == null) {
            // Premier passage donc impossible qu'il y ai déjà un jeu ...
            out.println("Il n'y a aucun jeu en ce moment.");
            return;
        }

        Game game = manager.getGame(idGame);

        if (game == null || game.isFull()) {
            // Le jeu n'existe pas ou il y a déjà 2 joueurs
            out.println("Aucun jeu avec cet id ou alors le jeu est déjà plein.");
            return;
        }

        HttpSession session = request.getSession(true);

        Player player = new Player();
        String color = (game.getFirstPlayer().getColor().equals("black")) ? "white" : "black";

        player.setColor(color);

        session.setAttribute("game", game);
        session.setAttribute("player", player);

        RequestDispatcher dispatcher = request.getRequestDispatcher("joingame.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }
}
