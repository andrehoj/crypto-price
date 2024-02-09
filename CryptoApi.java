import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class CryptoApi {
    public static final List<String> ALL_COINS_TYPE = Arrays.asList(
            "bitcoin", "ethereum", "dogecoin");
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String COINCAP_ASSETS_BASE_API = "https://api.coincap.io/v2/assets?";

    public String GetCurrentCoinPrice(String coinType) {
        // api.coincap.io/v2/assets/bitcoin
        try {
            String searchQueryParam = "search=" + coinType;
            String limitQueryParam = "limit=1";

            URI uri = new URI(
                    COINCAP_ASSETS_BASE_API + String.join("&", Arrays.asList(searchQueryParam, limitQueryParam)));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
