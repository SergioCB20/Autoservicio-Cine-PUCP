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
import pe.com.cinepucp.autoservicio.model.comida.Producto;

/**
 *
 * @author Miguel
 */
@WebService(serviceName = "ComidaWS")
public class ComidaWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String COMIDA_RESOURCE = "comidas";
    
    public ComidaWS(){
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
    }
    
    @WebMethod(operationName = "registrarComida")
    public void registrarComida(@WebParam(name = "comida") Producto prod) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(prod);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.COMIDA_RESOURCE;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    @WebMethod (operationName = "eliminarComida")
    public void eliminarComida(@WebParam(name = "id") int id)throws Exception{
        String url = this.urlBase + "/" + this.COMIDA_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName = "actualizaComida")
    public void actualizarComida(@WebParam(name = "comida")Producto prod)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(prod);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.COMIDA_RESOURCE + "/" + prod.getId();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod (operationName = "buscarComidaPorId")
    public Producto buscarComidaPorId(@WebParam(name = "id") int id)throws Exception{
        String url = this.urlBase + "/" + this.COMIDA_RESOURCE + "/" + id;
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
    
    @WebMethod (operationName = "listarComidas")
    public List<Producto> listarComidas()throws Exception{
        String url = this.urlBase+"/"+this.COMIDA_RESOURCE;
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
