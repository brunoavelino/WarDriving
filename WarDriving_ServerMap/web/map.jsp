<%-- 
    Document   : map
    Created on : 16/05/2016, 01:33:12
    Author     : narcelio
--%>

<%@page import="control.MarkerPlot"%>
<%@page import="control.LatLng"%>
<%@page import="warPacket.WifiDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="warPacket.Wifi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/mapa.css" type="text/css">
        <script type="text/javascript" src="resources/mapa.js"></script>
        <title>Map</title>
    </head>
    <body onload="loadMap()">
        <div id="map"></div>
        <%
            String date = request.getParameter("date");
            MarkerPlot mk = new MarkerPlot(date);
        %>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBdzf46f8w4-o0yGZiFzePzcuEr7VxKxA&callback=initMap">
        </script>
        <script type="text/javascript">
            function loadMap(){
                var Coordinates = <%=mk.getCoordinates()%>;
                var jsonListList = <%=mk.getMarkerList()%>;
                for(var i = 0; i < Coordinates.length; i++){
                    var jsonList = jsonListList[i];
                    addMarker(Coordinates[i],jsonList);
                }
                
                var line = new google.maps.Polyline({
                    path: Coordinates,
                    geodesic: true,
                    strokeColor: '#6600ff',
                    strokeOpacity: 0.3,
                    strokeWeight: 4
                });
                
                line.setMap(map);
            }
        </script>
    </body>
</html>