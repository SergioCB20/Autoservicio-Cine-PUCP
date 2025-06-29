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
import pe.com.cinepucp.autoservicio.model.comida.ComboItem;
/**
 *
 * @author gonza
 */
@WebService(serviceName="ComboItemWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class ComboItemWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String COMBO_RESOURCE = "combos";
    
    public ComboItemWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    @WebMethod(operationName = "registrarComboItem")
    public void registrarComboItem(@WebParam(name = "combo")ComboItem combo) throws Exception {
        System.out.println("--- Java ComboItem object received from Web Service ---");
        System.out.println("ComboItemId: " + combo.getCombo().getId());
        System.out.println("Nombre: " + combo.getProducto().getNombre_es());
        System.out.println("Cantidad: " + combo.getCantidad());
        System.out.println("-----------------------------------------------------");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(combo);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.COMBO_RESOURCE;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "actualizarComboItem")
    public void actualizarComboItem(@WebParam(name = "combo") ComboItem combo) throws Exception{
        System.out.println("--- Java ComboItem object received from Web Service ---");
        System.out.println("ComboItemId: " + combo.getCombo().getId());
        System.out.println("Nombre: " + combo.getProducto().getNombre_es());
        System.out.println("Cantidad: " + combo.getCantidad());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(combo);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.COMBO_RESOURCE + "/" + combo.getCombo().getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "eliminarComboItem")
    public void eliminarComboItem(@WebParam(name = "id") int id,@WebParam(name = "id_mod") int id_mod) throws Exception{
        String url = this.urlBase + "/" + this.COMBO_RESOURCE + "/" + id+"-"+id_mod;;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "buscarComboItemPorId")
    public ComboItem buscarComboItemPorId(@WebParam(name = "id") int id)throws Exception {
        String url = this.urlBase + "/" + this.COMBO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        ComboItem combo = mapper.readValue(json, ComboItem.class);
        
        return combo;
    }
    
    @WebMethod(operationName = "listarComboItems")
    public List<ComboItem> listarComboItems() throws Exception{
        String url = this.urlBase+"/"+this.COMBO_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<ComboItem> combos=mapper.readValue(json, new TypeReference<List<ComboItem>>(){});
        return combos;
    }    
    
}
