<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="chess.entity.Game"%>
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
            <h1>Rejoindre une partie</h1>

            <form id="joingame" action="joingame" method="post" class="form">
                <p>
                    <label for="nick">Prénom</label>
                    <input type="text" name="nick" id="nick" value="" />
                </p>
                <p>
                    <label for="color">Couleur</label>
                    <input type="radio" name="color" value="white" disabled="disabled" <%= session.getAttribute("color").equals("white") ? "checked='checked'" : "" %>/> <span>Blanc</span>
                    <input type="radio" name="color" value="black" disabled="disabled" <%= session.getAttribute("color").equals("black") ? "checked='checked'" : "" %>/> <span>Noir</span>
                </p>
                <p class="buttons">
                    <a class="button" href="#">Rejoindre la partie !</a>
                </p>
            </form>
        </div>
    </body>
</html>