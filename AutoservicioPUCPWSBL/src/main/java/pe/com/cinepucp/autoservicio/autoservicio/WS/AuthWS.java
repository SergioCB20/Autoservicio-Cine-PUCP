/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

/**
 *
 * @author Sergio
 */

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.MediaType;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.ResourceBundle;
import com.fasterxml.jackson.databind.ObjectMapper;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;

class AuthLoginResponseData { 
    public int id;
    public String nombre;
    public String email;
    public String tipoUsuario;
}

@WebService(serviceName = "AuthWS",
            targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class AuthWS {

    private final ResourceBundle config;
    private final String urlBaseRest;
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;

    public AuthWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBaseRest = this.config.getString("app.services.rest.baseurl");
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }

    @WebMethod(operationName = "loginUsuario")
    public AuthLoginResponseData loginUsuario(@WebParam(name = "email") String email,
                                              @WebParam(name = "password") String password) throws Exception {
        Map<String, String> credentials = Map.of("email", email, "password", password);
        String jsonRequest = this.serializationMapper.writeValueAsString(credentials);

        String url = this.urlBaseRest + "/auth/login"; // Endpoint REST para login
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Si la autenticación es exitosa, devuelve los datos del usuario
            return this.deserializationMapper.readValue(response.body(), AuthLoginResponseData.class);
        } else {
            // Si hay un error, lee el mensaje del REST API y lanza una excepción
            String errorMsg = response.body();
            Map<String, String> errorMap = this.deserializationMapper.readValue(errorMsg, Map.class);
            throw new Exception("Error en el login: " + errorMap.getOrDefault("message", "Error desconocido."));
        }
    }
    
    @WebMethod(operationName = "registrarUsuario")
    public String registrarUsuario(@WebParam(name = "usuario") Usuario usuario) throws Exception {
        String tipo = usuario.isAdmin() ? "ADMIN" : "CLIENTE";
        System.out.println(tipo);
        String jsonRequest = this.serializationMapper.writeValueAsString(usuario);

        String url = this.urlBaseRest + "/auth/signup"; // Endpoint REST para registro
        System.out.println(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 201) { // 201 Created para éxito en el registro
            return "Registro exitoso.";
        } else {
            String errorMsg = response.body();
            System.out.println(errorMsg);
            Map<String, String> errorMap = this.deserializationMapper.readValue(errorMsg, Map.class);
            throw new Exception("Error en el registro: " + errorMap.getOrDefault("message", "Error desconocido."));
        }
    }
}