package tqs.geo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressResolverTest {

    final String response = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"\u00A9 2022 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"\u00A9 2022 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"thumbMaps\":true,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.6318,\"lng\":-8.658}},\"locations\":[{\"street\":\"Parque Estacionamento da Reitoria - Univerisdade de Aveiro\",\"adminArea6\":\"\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Gl\u00F3ria e Vera Cruz\",\"adminArea5Type\":\"City\",\"adminArea4\":\"\",\"adminArea4Type\":\"County\",\"adminArea3\":\"Centro\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3810-193\",\"geocodeQualityCode\":\"P1AAA\",\"geocodeQuality\":\"POINT\",\"dragPoint\":false,\"sideOfStreet\":\"N\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"displayLatLng\":{\"lat\":40.631803,\"lng\":-8.657881},\"mapUrl\":\"http://open.mapquestapi.com/staticmap/v5/map?key=KEY&type=map&size=225,160&locations=40.62688, -8.6458|marker-sm-50318A-1&scalebar=true&zoom=15&rand=70663981\",\"roadMetadata\":null}]}]}";
   
    @Mock
    ISimpleHttpClient httpClient;

    @InjectMocks
    AddressResolverService addrResolver;

    @Test
    void testFindLocation() {
        when(httpClient.doHttpGet(anyString())).thenReturn(response);

        Optional<Address> result = addrResolver.findAddressForLocation(40.626894, -8.645807);
        Optional<Address> expected = Optional.of(new Address( "Glória e Vera Cruz","3810-193", "Parque Estacionamento da Reitoria - Univerisdade de Aveiro", ""));

        assertTrue(result.isPresent());
        assertEquals( result, expected);
    }

    @Test
    void testBadCoordinates() throws ParseException, URISyntaxException, IOException, org.json.simple.parser.ParseException{
        Optional<Address> invalidLatitude = addrResolver.findAddressForLocation(-98, -8.645807);
        Optional<Address> invalidLongitude = addrResolver.findAddressForLocation(40.626894, -190);

        assertFalse(invalidLatitude.isPresent());
        assertFalse(invalidLongitude.isPresent());
    }
    
}