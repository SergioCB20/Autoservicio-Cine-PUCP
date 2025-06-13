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
import pe.com.cinepucp.autoservicio.model.comida.Producto;
/**
 *
 * @author gonza
 */
@WebService(serviceName="ProductoWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class ProductoWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String PRODUCTO_RESOURCE = "productos";
    
    public ProductoWS() {       
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "registrarProducto")
    public void registrarProducto(@WebParam(name = "prod")Producto prod) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(prod);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.PRODUCTO_RESOURCE;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "actualizarProducto")
    public void actualizarProducto(@WebParam(name = "prod")Producto prod) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(prod);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.PRODUCTO_RESOURCE + "/" + prod.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "eliminarProducto")
    public void eliminarProducto(@WebParam(name = "id")int id) throws Exception {
        String url = this.urlBase + "/" + this.PRODUCTO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "buscarProductoPorId")
    public Producto buscarProductoPorId(@WebParam(name = "id")int id) throws Exception {
        String url = this.urlBase + "/" + this.PRODUCTO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        Producto producto = mapper.readValue(json, Producto.class);        
        return producto;
    }
    
    @WebMethod(operationName = "listarProductos")
    public List<Producto> listarProductos() throws Exception {
        String url = this.urlBase+"/"+this.PRODUCTO_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<Producto> productos=mapper.readValue(json, new TypeReference<List<Producto>>(){});
        return productos;
    }
    
}
