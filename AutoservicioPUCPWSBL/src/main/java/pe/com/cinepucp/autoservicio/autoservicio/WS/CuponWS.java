/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.com.cinepucp.autoservicio.autoservicio.WS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import pe.com.cinepucp.autoservicio.model.venta.Cupon;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

/**
 *
 * @author Amira
 */
@WebService(serviceName = "CuponWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class CuponWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String CUPON_RESOURCE = "cupones";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;
    
    
    public CuponWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        // Inyectamos los mappers desde JsonUtils
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }
    
    @WebMethod(operationName = "registrarCupon")
    public void registrarCupon(@WebParam(name = "cupon") Cupon cupon) throws Exception{
         
    if (cupon == null) {
        System.out.println("ERROR: Cupon es null");
        throw new IllegalArgumentException("El cupón no puede ser null");
    }
    
        /*LocalDate fechaIni = LocalDate.parse(stringIni);
        LocalDate fechaFin = LocalDate.parse(stringFin);
    
        cupon.setFechaInicio(fechaIni);
        cupon.setFechaFin(fechaFin);*/
        cupon.setFechaModificacion(LocalDate.now());
    try {
        String json = this.serializationMapper.writeValueAsString(cupon);
        
        String url = this.urlBase + "/" + this.CUPON_RESOURCE;
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new Exception("Error al registrar cupón. Código: " + response.statusCode());
        }
        
        
    } catch (Exception e) {
        System.out.println("ERROR en registrarCupon: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }    
    }
    
    @WebMethod(operationName = "actualizarCupon")
    public void actualizarCupon(@WebParam(name = "cupon") Cupon cupon) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cupon);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.CUPON_RESOURCE + "/" + cupon.getCuponId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "eliminarCupon")
    public void eliminarCupon(@WebParam(name = "id")int id,@WebParam(name = "id_mod") int id_mod) throws Exception{
        String url = this.urlBase + "/" + this.CUPON_RESOURCE + "/" + id+"-"+id_mod;;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "buscarCuponPorId")
    public Cupon buscarCuponPorId(@WebParam(name = "id")int id) throws Exception{
        String url = this.urlBase + "/" + this.CUPON_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        mapper = JsonUtils.getDeserializationMapper();
        Cupon cupon =mapper.readValue(json,Cupon.class);
        return cupon;
    }
    
    
    @WebMethod(operationName = "listarCupones")
    public List<Cupon> listarCupones()throws Exception {
        String url = this.urlBase+"/"+this.CUPON_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        mapper = JsonUtils.getDeserializationMapper();
        List<Cupon> cupones=mapper.readValue(json, new TypeReference<List<Cupon>>(){});
        return cupones;
    }
}
