/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final String urlBaseRest;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String USUARIO_RESOURCE = "usuarios"; 
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;

    public UsuarioWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBaseRest = this.config.getString("app.services.rest.baseurl");
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }

    @WebMethod(operationName = "registrarUsuario")
    public void registrarUsuario(@WebParam(name = "usuario") Usuario usuario) throws Exception {
        String json = this.serializationMapper.writeValueAsString(usuario);

        String url = this.urlBaseRest + "/" + this.USUARIO_RESOURCE; // Asume que el endpoint es /api/usuarios
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Envía la solicitud y verifica la respuesta. Podrías querer manejar errores específicos aquí.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 201) { // 201 Created es el código de éxito para POST
            String errorMsg = response.body();
            // Podrías deserializar el error JSON para obtener un mensaje más específico
            throw new Exception("Error al registrar usuario: " + errorMsg);
        }
    }
    
    @WebMethod(operationName = "actualizarUsuario")
    public void actualizarUsuario(@WebParam(name = "usuario") Usuario usuario) throws Exception {
        String json = this.serializationMapper.writeValueAsString(usuario);

        // Asume que tu API REST usa el ID del usuario en la URL para PUT, ej: /api/usuarios/{id}
        String url = this.urlBaseRest + "/"+this.USUARIO_RESOURCE + "/" + usuario.getId();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) { // 200 OK es el código de éxito para PUT
            String errorMsg = response.body();
            throw new Exception("Error al actualizar usuario: " + errorMsg);
        }
    }

    @WebMethod(operationName = "eliminarUsuario")
    public void eliminarUsuario(@WebParam(name = "id") int id,@WebParam(name = "id_mod") int id_mod) throws Exception {
        String url = this.urlBaseRest + "/"+ this.USUARIO_RESOURCE + "/" + id+"-"+id_mod; // Asume /api/usuarios/{id}
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 204) { // 204 No Content es común para DELETE exitoso
            String errorMsg = response.body();
            throw new Exception("Error al eliminar usuario: " + errorMsg);
        }
    }
    
    @WebMethod(operationName = "buscarUsuarioPorId")
    public Usuario buscarUsuarioPorId(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBaseRest + "/"+ this.USUARIO_RESOURCE + "/" + id; // Asume /api/usuarios/{id}
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respuesta del servidor: " + response.body()); // Debug del JSON
        if (response.statusCode() == 200) {
            String json = response.body();
            // Deserializa el JSON a un objeto Usuario
            Usuario usuario = this.deserializationMapper.readValue(json, Usuario.class);
            return usuario;
        } else if (response.statusCode() == 404) {
            return null;
        } else {
            String errorMsg = response.body();
            throw new Exception("Error al buscar usuario: " + errorMsg);
        }
    }
    
    @WebMethod(operationName = "listarUsuarios")
    public List<Usuario> listarUsuarios() throws Exception {
        String url = this.urlBaseRest + "/" + this.USUARIO_RESOURCE; // Asume /api/usuarios
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String json = response.body();
            // Deserializa el JSON a una lista de objetos Usuario
            List<Usuario> usuarios = this.deserializationMapper.readValue(json, new TypeReference<List<Usuario>>() {});
            return usuarios;
        } else {
            String errorMsg = response.body();
            throw new Exception("Error al listar usuarios: " + errorMsg);
        }
    }
}
