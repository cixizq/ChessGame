<?xml version="1.0" encoding="utf-8"?>
<%@page import="chess.entity.Movement"%>
<%@page contentType="application/xml" pageEncoding="UTF-8"%>
<%
    int state = ((Integer)request.getAttribute("state")).intValue();
%>
<update>
    <state><%= state %></state>
    <%
        if (state == 2) {
            Movement mvt = (Movement) request.getAttribute("movement");
            %>
    <movement>
        <src><%= mvt.getSource().reverseTransform() %></src>
        <dst><%= mvt.getDestination().reverseTransform() %></dst>
    </movement>
            <%
        }
    %>
</update>
