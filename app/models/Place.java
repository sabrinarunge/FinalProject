package models;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import services.GoogleAPI;

import java.io.IOException;

public class Place
{

    public void getPlace() throws InterruptedException, ApiException, IOException
    {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(GoogleAPI.getAPIKEY())
                .build();

        PlacesSearchResponse response = PlacesApi.textSearchQuery(context, "New York").await();

        PlacesSearchResult[] result = response.results;

        System.out.println(result[0]);

    }


}
