   function initMap(){

        var myLatLong = {lat: 51.503454, lng: -0.119562};
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
                    var position = new google.maps.LatLng(jsPlaces[i][1], jsPlaces[i][2]);
                    //map.bounds.extend(position);
                    marker = new google.maps.Marker({
                        position: position,
                        map: map,
                        title: jsPlaces[i][0]
                    });
                    }
    }

