/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var map;
var image = "resources/_images/wi-fi-2.png";
var markerList = [];
var count = 0;

function initMap(){
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -22.9558322, lng: -43.1661116},
        zoom: 19
    });
    
    
}

function addMarker(position,jsonInfo){

    markerList.push(new google.maps.Marker({
        position: position,
        animation: google.maps.Animation.DROP,
        icon: image
    }));

    markerList[count].setMap(map);
    
    var contentString = "<h1>Redes</h1>"+
                        "<div><p><strong>SSID</strong> | <strong>Intensidade</strong></p></div>";
    for(var i = 0; i < jsonInfo.length ; i++){
        contentString = contentString + "<div><p>"+jsonInfo[i].SSID + " | " +jsonInfo[i].Intensidade+"</p></div>";
    }
    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });
    
    markerList[count].addListener('click', function() {
        infowindow.open(map, this);
    });
    
    count++;
}