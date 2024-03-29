<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="game" scope="session" class="chess.entity.Game" />
<jsp:useBean id="player" scope="session" class="chess.entity.Player" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Chess game</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/reset.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/chess.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/board.css" />

        <script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.16.min.js"></script>
        <script type="text/javascript" src="js/board.js"></script>
        <script type="text/javascript" src="js/chess.js"></script>
        <script type="text/javascript">
            board.userColor = '<%= player.getColor() %>';
        </script>
    </head>
    <body>
        <%
            if (!game.isInitialized()) {
                // L'utilisateur n'a pas créé une partie
                response.sendRedirect("index.html");
            }
        %>
        <div id="header">
            <span></span>
        </div>

        <div id="informations">
            <a href="javascript:;" onclick="chess.giveup()" class="button red large">Abandonner</a>

            <div id="message">
            </div>
        </div>

        <div id="board">
            <div id="_8A" class="white"></div>
            <div id="_8B" class="black"></div>
            <div id="_8C" class="white"></div>
            <div id="_8D" class="black"></div>
            <div id="_8E" class="white"></div>
            <div id="_8F" class="black"></div>
            <div id="_8G" class="white"></div>
            <div id="_8H" class="black"></div>
            <div id="_7A" class="black"></div>
            <div id="_7B" class="white"></div>
            <div id="_7C" class="black"></div>
            <div id="_7D" class="white"></div>
            <div id="_7E" class="black"></div>
            <div id="_7F" class="white"></div>
            <div id="_7G" class="black"></div>
            <div id="_7H" class="white"></div>
            <div id="_6A" class="white"></div>
            <div id="_6B" class="black"></div>
            <div id="_6C" class="white"></div>
            <div id="_6D" class="black"></div>
            <div id="_6E" class="white"></div>
            <div id="_6F" class="black"></div>
            <div id="_6G" class="white"></div>
            <div id="_6H" class="black"></div>
            <div id="_5A" class="black"></div>
            <div id="_5B" class="white"></div>
            <div id="_5C" class="black"></div>
            <div id="_5D" class="white"></div>
            <div id="_5E" class="black"></div>
            <div id="_5F" class="white"></div>
            <div id="_5G" class="black"></div>
            <div id="_5H" class="white"></div>
            <div id="_4A" class="white"></div>
            <div id="_4B" class="black"></div>
            <div id="_4C" class="white"></div>
            <div id="_4D" class="black"></div>
            <div id="_4E" class="white"></div>
            <div id="_4F" class="black"></div>
            <div id="_4G" class="white"></div>
            <div id="_4H" class="black"></div>
            <div id="_3A" class="black"></div>
            <div id="_3B" class="white"></div>
            <div id="_3C" class="black"></div>
            <div id="_3D" class="white"></div>
            <div id="_3E" class="black"></div>
            <div id="_3F" class="white"></div>
            <div id="_3G" class="black"></div>
            <div id="_3H" class="white"></div>
            <div id="_2A" class="white"></div>
            <div id="_2B" class="black"></div>
            <div id="_2C" class="white"></div>
            <div id="_2D" class="black"></div>
            <div id="_2E" class="white"></div>
            <div id="_2F" class="black"></div>
            <div id="_2G" class="white"></div>
            <div id="_2H" class="black"></div>
            <div id="_1A" class="black"></div>
            <div id="_1B" class="white"></div>
            <div id="_1C" class="black"></div>
            <div id="_1D" class="white"></div>
            <div id="_1E" class="black"></div>
            <div id="_1F" class="white"></div>
            <div id="_1G" class="black"></div>
            <div id="_1H" class="white"></div>
        </div>
        <% if (!game.isFull()) { %>
        <div id="game_link">
            <p>Invitez votre ami en lui donnant le lien suivant : </p><input type="text" value="<%= game.generateUrl(request) %>"/>
        </div>
        <% } %>
    </body>
</html>