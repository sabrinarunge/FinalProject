   function initMap(){
        var myLatLong = new google.maps.LatLng(40.71277530, -74.00597280)
        var options = {
            zoom:10,
            center:myLatLong
            }

        var map = new google.maps.Map(document.getElementById('map'), options);

        var marker = new google.maps.Marker({
            position: myLatLong,
            map: map,
            title: 'Hello World!'
            });

        marker.setMap(map);
    }