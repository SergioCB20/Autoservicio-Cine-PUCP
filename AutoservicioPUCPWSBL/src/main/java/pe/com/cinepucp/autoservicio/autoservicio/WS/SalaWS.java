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
    
    public SalaWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    @WebMethod(operationName = "registrarSala")
    public void registrarSala(@WebParam(name = "sala") Sala sala) throws Exception{
        System.out.println("--- Java Sala object received from Web Service ---");
        System.out.println("SalaId: " + sala.getId());
        System.out.println("Nombre: " + sala.getNombre());
        System.out.println("Tipo de sala: " + sala.getTipoSala().getDescripcion());
        System.out.println("Capacidad: " + sala.getCapacidad());
        System.out.println("estaActiva: " + sala.isActiva()); 
        System.out.println("-----------------------------------------------------");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sala);

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
        System.out.println("--- Java Sala object received from Web Service ---");
        System.out.println("SalaId: " + sala.getId());
        System.out.println("Nombre: " + sala.getNombre());
        System.out.println("Tipo de sala: " + sala.getTipoSala().name());
        System.out.println("Capacidad: " + sala.getCapacidad());
        System.out.println("estaActiva: " + sala.isActiva()); 
        System.out.println("-----------------------------------------------------");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sala);

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
    public void eliminarSala(@WebParam(name = "id") int id) throws Exception{
        String url = this.urlBase + "/" + this.SALA_RESOURCE + "/" + id;
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
        ObjectMapper mapper= new ObjectMapper();
        Sala sala = mapper.readValue(json, Sala.class);
        
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
        ObjectMapper mapper= new ObjectMapper();
        List<Sala> salas=mapper.readValue(json, new TypeReference<List<Sala>>(){});
        return salas;
    }
}

