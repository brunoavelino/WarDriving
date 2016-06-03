<%-- 
    Document   : index
    Created on : 16/05/2016, 00:39:45
    Author     : narcelio
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WARDRIVING</title>
        <link rel="stylesheet" href="resources/_css/bootstrap.min.css" />
            <script>
                function redirect(date){
                    location.href = "map.jsp?date="+date; 
                }
            </script>
    </head>
    <body>
        <h1>Seja bem-vindo ao site do War-Driving</h1>
        <br /> <br/> 
        <h3>Escolha abaixo a rota desejada:</h3>		
        <div class="list-group" id="buttonGroup">

        </div>

        <script>
            <%
                warPacket.WifiDAO wifiDao = new warPacket.WifiDAO();
                String list = wifiDao.getListOfStringDateJSP();
            %>;
            var dates = <%=list%>;
            var i;
            var text = '';
            for (i = 0; i < dates.length; i++) {
                text+= '<button type="button" class="list-group-item" onclick="redirect(\''+dates[i]+'\')">'+dates[i]+'</button>';
            }
            document.getElementById('buttonGroup').innerHTML = text;		
        </script>

        <script src="resources/_javascript/jquery.min.js"></script>
        <script src="resources/_javascript/bootstrap.min.js" ></script>
    </body>
</html>
