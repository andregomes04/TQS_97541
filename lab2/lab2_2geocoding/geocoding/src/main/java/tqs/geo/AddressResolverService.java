package tqs.geo;

import java.util.Locale;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * calls external api to perform the reverse geocode
 *
 * @author ico
 */
public class AddressResolverService {

    private final ISimpleHttpClient httpClient;

    public AddressResolverService(ISimpleHttpClient httpClient) {

        this.httpClient = httpClient;
    }

    public Optional<Address> findAddressForLocation(double latitude, double longitude) {
        // Check if latitude and longitude are within valid ranges
        if (!isValidCoordinates(latitude, longitude)) {
            return Optional.empty();
        }
    
        String url = String.format(Locale.getDefault(), "https://www.mapquestapi.com/geocoding/v1/reverse?key=KEY&location=%.4f,%.4f&includeRoadMetadata=true&includeNearestIntersection=true", latitude, longitude);

        // Perform HTTP GET request
        String jsonResponse = httpClient.doHttpGet(String.format(Locale.getDefault(), url, latitude, longitude));

        JSONObject data;
        String road = "";
        String city = "";
        String zip = "";
        String houseNumber = "";

        try {
            data = new JSONObject(jsonResponse).getJSONArray("results").getJSONObject(0).getJSONArray("locations").getJSONObject(0);
    
            road = data.getString("street".toString());
            city = data.getString("adminArea5".toString());
            zip = data.getString("postalCode".toString());
            houseNumber = data.getString("adminArea6".toString());

        } catch (JSONException e) {
            System.err.println(e);
            
        }

        return Optional.of(new Address(city, zip, road, houseNumber));
        }

    private boolean isValidCoordinates(double latitude, double longitude) {
        // Check if latitude and longitude are within valid ranges
        return latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180;
    }
}
