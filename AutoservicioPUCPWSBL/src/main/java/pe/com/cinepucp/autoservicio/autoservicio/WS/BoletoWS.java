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
import java.util.List;
import java.util.ResourceBundle;
import pe.com.cinepucp.autoservicio.model.venta.Boleto;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

@WebService(serviceName = "BoletoWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class BoletoWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String BOLETO_RESOURCE = "boletos";
    private final ObjectMapper serializationMapper;
    
    public BoletoWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }
    
    @WebMethod(operationName = "listarBoleto")
    public List<Boleto> listar() throws Exception{
        String url = this.urlBase+"/"+this.BOLETO_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        List<Boleto> boletos=mapper.readValue(json, new TypeReference<List<Boleto>>(){});
        return boletos;
    }
    
    @WebMethod(operationName = "registrarBoleto")
    public void registrarBoleto(@WebParam(name = "boleto") Boleto boleto) throws Exception {
        System.out.println("Llamaron al WS con boleto: " + boleto); //comprobar que entra al método
        
        String json = this.serializationMapper.writeValueAsString(boleto);
        
        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.BOLETO_RESOURCE;
        System.out.println("JSON a enviar: " + json); ////////Ver el JSON formado
        System.out.println("URL: " + url);
        
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ////////////mostrar respuesta
        System.out.println("Código respuesta: " + response.statusCode());
        System.out.println("Cuerpo respuesta: " + response.body());
    }
    
    @WebMethod(operationName = "actualizarBoleto")
    public void guardarBoleto(@WebParam(name = "boleto")Boleto boleto) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(boleto);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.BOLETO_RESOURCE;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "obtenerBoleto")
    public Boleto obtener(@WebParam(name = "id") int id)throws Exception{
        String url = this.urlBase + "/" + this.BOLETO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        ObjectMapper mapper= new ObjectMapper();
        Boleto boleto = mapper.readValue(json, Boleto.class);
        
        return boleto;
    }
    
    @WebMethod(operationName = "eliminarBoleto")
    public void eliminar(@WebParam(name = "id") int id) throws Exception{
        String url = this.urlBase + "/" + this.BOLETO_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
}   