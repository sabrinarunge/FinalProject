   function initMap(){

        var myLatLong = {lat: jsPlaces[0][1], lng: jsPlaces[0][2]};
        var options = {
            zoom:10,
            center:myLatLong
            };

        var map = new google.maps.Map(document.getElementById('map'), options);


            var markers = [
                ['London Eye, London', 51.503454,-0.119562],
                ['Palace of Westminster, London', 51.499633,-0.124755]
            ];

            var infoWindow = new google.maps.InfoWindow(), marker, i;

                for( i = 0; i < jsPlaces.length; i++ ) {
                    var infoWindowContent = '<div id="content">'+
                                        '<div id="siteNotice">'+
                                        '</div>'+
                                        '<h1 id="firstHeading" class="firstHeading">' + jsPlaces[i][0] + '</h1>' +
                                        '<h2> Opening Hours: ' + jsPlaces[i][3] + '</h2>' +
                                        '<h2> Formmatted Address: ' + jsPlaces[i][4] + '</h2>' +
                                        '<h2> Rating: ' + jsPlaces[i][5] + '</h2>' +
                                        '</div>';
                    var infowindow = new google.maps.InfoWindow({
                               content: infoWindowContent
                             });
                    var position = new google.maps.LatLng(jsPlaces[i][1], jsPlaces[i][2]);
                    //map.bounds.extend(position);
                    marker = new google.maps.Marker({
                        position: position,
                        map: map,
                        title: jsPlaces[i][0]
                    });

                    }
    }

