/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.net.http.*;
import java.net.URI;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author JUANDA
 */
public class HttpClientUtil {
    
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static <T, R> R post(String url, T requestObj, Class<R> responseClass){

        try{
        String requestBody = mapper.writeValueAsString(requestObj);
        
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), responseClass);
        }catch(IOException | InterruptedException | URISyntaxException e){
            throw new RuntimeException("Error en petición HTTP: " + e.getMessage(), e);
        }
    }
}
