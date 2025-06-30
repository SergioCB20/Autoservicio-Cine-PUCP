    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.net.http.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jws.WebParam;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import pe.com.cinepucp.autoservicio.model.salas.Sala;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

/**
 *
 * @author gonza
 */

@WebService(serviceName="SalaWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class SalaWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String SALA_RESOURCE = "salas";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;
    
    public SalaWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }
    @WebMethod(operationName = "registrarSala")
    public void registrarSala(@WebParam(name = "sala") Sala sala) throws Exception{
        String json = this.serializationMapper.writeValueAsString(sala);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.SALA_RESOURCE;
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        
    }
    
    @WebMethod(operationName = "actualizarSala")
    public void actualizarSala(@WebParam(name = "sala") Sala sala) throws Exception{
        String json = this.serializationMapper.writeValueAsString(sala);
        System.out.println(json);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.SALA_RESOURCE + "/" + sala.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "eliminarSala")
    public void eliminarSala(@WebParam(name = "id") int id,@WebParam(name = "id_mod") int id_mod) throws Exception{
        String url = this.urlBase + "/" + this.SALA_RESOURCE + "/" + id+"-"+id_mod;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "buscarSalaPorId")
    public Sala buscarSalaPorId(@WebParam(name = "id") int id) throws Exception{        
        String url = this.urlBase + "/" + this.SALA_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        
        Sala sala = this.deserializationMapper.readValue(json, Sala.class);        
        return sala;
    }
    
    @WebMethod(operationName = "listarSalas")
    public List<Sala> listarSalas() throws Exception{
        String url = this.urlBase+"/"+this.SALA_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Sala> salas=this.deserializationMapper.readValue(json, new TypeReference<List<Sala>>(){});
        return salas;
    }
}

