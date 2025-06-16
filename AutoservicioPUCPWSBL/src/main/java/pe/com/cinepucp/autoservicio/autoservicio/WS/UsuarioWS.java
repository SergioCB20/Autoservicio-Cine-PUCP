/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

/**
 *
 * @author Amira
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
// Eliminar importaciones de DeserializationFeature, SerializationFeature, SimpleModule, StdDateFormat, SimpleDateFormat, ParseException si solo las usas para ObjectMapper
// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.fasterxml.jackson.databind.module.SimpleModule;
// import com.fasterxml.jackson.databind.util.StdDateFormat;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import pe.com.cinepucp.autoservicio.model.auth.Usuario;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

@WebService(serviceName = "UsuarioWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class UsuarioWS {
    
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String USUARIO_RESOURCE = "usuario";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;

    public UsuarioWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        // Inyectamos los mappers desde JsonUtils
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }

    @WebMethod(operationName = "registrarUsuario")
    public void registrarUsuario(@WebParam(name = "usuario") Usuario usuario) throws Exception {
        String json = this.serializationMapper.writeValueAsString(usuario);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.USUARIO_RESOURCE;
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "actualizarUsuario")
    public void actualizarUsuario(@WebParam(name = "pelicula") Usuario usuario) throws Exception {
        String json = this.serializationMapper.writeValueAsString(usuario);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.USUARIO_RESOURCE + "/" + usuario.getId();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "eliminarUsuario")
    public void eliminarUsuario(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + this.USUARIO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "buscarUsuarioPorId")
    public Usuario buscarusarioPorId(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + this.USUARIO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Usuario usuario = this.deserializationMapper.readValue(json, Usuario.class);
        return usuario;
    }

    @WebMethod(operationName = "listarUsuario")
    public List<Usuario> listarUsuario() throws Exception {
        String url = this.urlBase + "/" + this.USUARIO_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        List<Usuario> usuarios = this.deserializationMapper.readValue(json, new TypeReference<List<Usuario>>() {});

        return usuarios;
    }
}
