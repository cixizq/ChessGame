<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Iterator"%>
<%@page import="chess.entity.Game"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="manager" scope="application" class="chess.entity.GameManager" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Rejoindre une partie</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <link rel="stylesheet" type="text/css" media="screen" href="css/reset.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/chess.css" />

        <script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
        <script type="text/javascript" src="js/chess.js"></script>
    </head>
    <body>
        <div id="header">
        </div>
        <div id="wrap">
            <a id="button_header" class="button magenta" href="index.html">Retour</a>
            <h1>Liste des parties</h1>
            <ul>
                <%
                    Collection<Game> games = manager.getGames();
                    Iterator<Game> it = games.iterator();
                    while (it.hasNext()) {
                        Game game = it.next();
                %>
                <li>
                    Partie de <%= game.getFirstPlayer().getNick() %>
                    <%
                        if (game.isFull()) {
                            %>
                            contre <%= game.getSecondPlayer().getNick() %>
                            <%
                        } else {
                            %>
                            - <a href="<%= game.generateUrl(request) %>">Rejoindre</a>
                            <%
                        }
                    %>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </body>
</html>