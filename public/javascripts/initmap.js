   function initMap()
   {

        var myLatLong = {lat: jsPlaces[0][1], lng: jsPlaces[0][2]};
        var options =
            {
            zoom:10,
            center:myLatLong
            };

        var map = new google.maps.Map(document.getElementById('map'), options);


                for( i = 0; i < jsPlaces.length; i++ )
                {
                    var infoWindowContent = '<div id="content">'+
                                        '<div id="siteNotice">'+
                                        '</div>'+
                                        '<h3 id="firstHeading" class="firstHeading">' + jsPlaces[i][0] + '</h3>' +
                                        '<h5> <a href="' + jsPlaces[i][3] + '">Google Maps Link</a></h5>' +
                                        '<h5> Address: ' + jsPlaces[i][4] + '</h5>' +
                                        '<h5> Rating: ' + jsPlaces[i][5] + '</h5>'
                                        '</div>';

                    var infoWindow = new google.maps.InfoWindow(
                    {
                       content: infoWindowContent
                    });
                    var position = new google.maps.LatLng(jsPlaces[i][1], jsPlaces[i][2]);

                    var marker = new google.maps.Marker(
                    {
                        position: position,
                        map: map,
                        title: jsPlaces[i][0]
                    });

                    bindInfoWindow(marker, map, infoWindow, jsPlaces[5]);
                }

                function bindInfoWindow(marker, map, infoWindow, html)
                {
                    marker.addListener('click', function()
                    {
                        infoWindow.setContent(html);
                        infoWindow.open(map, this)
                    }
                    );
                }

    }

     function initConMap()
       {

            var myLatLong = {lat: 34.7465, lng: -92.2896};
            var options =
                {
                zoom:2,
                center:myLatLong
                };

            var map = new google.maps.Map(document.getElementById('map'), options);

        }

