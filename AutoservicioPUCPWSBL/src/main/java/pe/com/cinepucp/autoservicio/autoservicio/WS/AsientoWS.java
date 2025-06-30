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
import pe.com.cinepucp.autoservicio.model.salas.Asiento;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

/**
 *
 * @author gonza
 */
@WebService(serviceName="AsientoWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class AsientoWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String ASIENTO_RESOURCE = "asientos";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;
    
    public AsientoWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }
    @WebMethod(operationName = "registrarAsiento")
    public void registrarAsiento(@WebParam(name = "asiento") Asiento asiento) throws Exception{
        String json = this.serializationMapper.writeValueAsString(asiento);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.ASIENTO_RESOURCE;
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        
    }
    
    @WebMethod(operationName = "actualizarAsiento")
    public void actualizarAsiento(@WebParam(name = "asiento") Asiento asiento) throws Exception{
        String json = this.serializationMapper.writeValueAsString(asiento);
        System.out.println(json);
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.ASIENTO_RESOURCE + "/" + asiento.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "eliminarAsiento")
    public void eliminarAsiento(@WebParam(name = "id") int id,@WebParam(name = "id_mod") int id_mod) throws Exception{
        String url = this.urlBase + "/" + this.ASIENTO_RESOURCE + "/" + id+"-"+id_mod;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "buscarAsientoPorId")
    public Asiento buscarAsientoPorId(@WebParam(name = "id") int id) throws Exception{        
        String url = this.urlBase + "/" + this.ASIENTO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        
        Asiento asiento = this.deserializationMapper.readValue(json, Asiento.class);        
        return asiento;
    }
    
    @WebMethod(operationName = "listarAsientos")
    public List<Asiento> listarAsientos() throws Exception{
        String url = this.urlBase+"/"+this.ASIENTO_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Asiento> asientos=this.deserializationMapper.readValue(json, new TypeReference<List<Asiento>>(){});
        return asientos;
    }
    @WebMethod(operationName = "listarAsientosSala")
    public List<Asiento> listarAsientosSala(@WebParam(name = "salaid") int salaid) throws Exception{
        String url = this.urlBase+"/"+this.ASIENTO_RESOURCE+"/por-sala/"+salaid;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        List<Asiento> asientos=this.deserializationMapper.readValue(json, new TypeReference<List<Asiento>>(){});
        return asientos;
    }
    
}
