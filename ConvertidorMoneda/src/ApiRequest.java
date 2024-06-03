
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class ApiRequest {
    private  String direccion = "https://v6.exchangerate-api.com/v6/d24093969f674434ea73c429/latest/USD";
    private HashMap<String, Double> tasas;

    public  String getDireccion(){
        return direccion;
    }
    public HashMap<String, Double> getTasas(){
        return tasas;
    }
    public void apiconversion() {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            //
            // System.out.println(json);

            JsonParser parser = new JsonParser();// clase parse para maper directamente
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            // Mapeo a objeto
            tasas = new Gson().fromJson(conversionRates, HashMap.class);



        } catch (NumberFormatException e) {
            System.out.println("Tenemos un Error: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la URL, verifique la direccion");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finalizo el programa");





    }

}