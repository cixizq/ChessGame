<?xml version="1.0" encoding="utf-8"?>
<%@page import="chess.entity.Piece"%>
<%@page contentType="application/xml" pageEncoding="UTF-8"%>
<move>
    <state><%= ((Boolean) request.getAttribute("result")).booleanValue() ? "ok" : "nok" %></state>
    <%
        Piece piece = (Piece) request.getAttribute("piece");
        if (piece != null) {
    %>
    <piece>
        <color><%= piece.getColor() %></color>
        <type><%= piece.getType() %></type>
    </piece>
    <%
        }
    %>
</move>