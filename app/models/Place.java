package models;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import services.GoogleAPI;

import java.io.IOException;

public class Place
{

    public static PlacesSearchResult[] getPlace(String place) throws InterruptedException, ApiException, IOException
    {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(GoogleAPI.getAPIKEY())
                .build();

        PlacesSearchResponse response = PlacesApi.textSearchQuery(context, place).await();

        PlacesSearchResult[] result = response.results;

        return result;

    }

    public static PlaceDetails getPlaceDetails(String placeId) throws InterruptedException, ApiException, IOException
    {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(GoogleAPI.getAPIKEY())
                .build();

        PlaceDetails response = PlacesApi.placeDetails(context, placeId).await();

        return response;
    }


}
