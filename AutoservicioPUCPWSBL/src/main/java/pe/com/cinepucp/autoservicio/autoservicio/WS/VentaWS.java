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
import pe.com.cinepucp.autoservicio.model.venta.Venta;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

/**
 *
 * @author gonza
 */
@WebService(serviceName = "VentaWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class VentaWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String VENTA_RESOURCE = "ventas";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;
    
    public VentaWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        // Inyectamos los mappers desde JsonUtils
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();        
    }
    @WebMethod(operationName = "registrarVenta")
    public void registrarVenta(@WebParam(name = "venta") Venta venta) throws Exception {
        String json = this.serializationMapper.writeValueAsString(venta);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.VENTA_RESOURCE;
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod(operationName = "actualizarVenta")
    public void actualizarVenta(@WebParam(name = "venta") Venta venta) throws Exception {
        String json = this.serializationMapper.writeValueAsString(venta);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.VENTA_RESOURCE + "/" + venta.getVentaId();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "eliminarVenta")
    public void eliminarVenta(@WebParam(name = "id") int id,@WebParam(name = "id_mod") int id_mod) throws Exception {
        String url = this.urlBase + "/" + this.VENTA_RESOURCE + "/" + id+"-"+id_mod;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "buscarVentaPorId")
    public Venta buscarVentaPorId(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + this.VENTA_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Venta venta = this.deserializationMapper.readValue(json, Venta.class);
        return venta;
    }

    @WebMethod(operationName = "listarVentasReporte")
    public List<Venta> listarVentasReporte(@WebParam(name = "fechaini") String fechaini,@WebParam(name = "fechafin") String fechafin) throws Exception {
        //String url = this.urlBase + "/" + this.VENTA_RESOURCE;
        String url = String.format("%s/%s/%s/%s", 
                                  this.urlBase, 
                                  this.VENTA_RESOURCE, // debería ser "ventas" 
                                  fechaini, 
                                  fechafin);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        List<Venta> ventas = this.deserializationMapper.readValue(json, new TypeReference<List<Venta>>() {});

        return ventas;
    }
}
