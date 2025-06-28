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
import pe.com.cinepucp.autoservicio.model.Peliculas.Funcion;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

@WebService(serviceName = "FuncionWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class FuncionWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String FUNCION_RESOURCE = "funciones";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;

    public FuncionWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }

    @WebMethod(operationName = "registrarFuncion")
    public void registrarFuncion(@WebParam(name = "funcion") Funcion funcion) throws Exception {
        if (funcion == null) {
            throw new IllegalArgumentException("La función no puede ser null");
        }

        String json = this.serializationMapper.writeValueAsString(funcion);
        System.out.println(json);
        String url = this.urlBase + "/" + FUNCION_RESOURCE;
        System.out.println(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new Exception("Error al registrar función. Código: " + response.statusCode());
        }
    }

    @WebMethod(operationName = "actualizarFuncion")
    public void actualizarFuncion(@WebParam(name = "funcion") Funcion funcion) throws Exception {
        if (funcion == null || funcion.getFuncionId() == null) {
            throw new IllegalArgumentException("La función o su ID no puede ser null");
        }

        String json = this.serializationMapper.writeValueAsString(funcion);
        String url = this.urlBase + "/" + FUNCION_RESOURCE + "/" + funcion.getFuncionId();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "eliminarFuncion")
    public void eliminarFuncion(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + FUNCION_RESOURCE + "/" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "buscarFuncionPorId")
    public Funcion buscarFuncionPorId(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + FUNCION_RESOURCE + "/" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return this.deserializationMapper.readValue(json, Funcion.class);
    }

    @WebMethod(operationName = "listarFunciones")
    public List<Funcion> listarFunciones() throws Exception {
        String url = this.urlBase + "/" + FUNCION_RESOURCE;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return this.deserializationMapper.readValue(json, new TypeReference<List<Funcion>>() {});
    }

    @WebMethod(operationName = "listarFuncionesPorPelicula")
    public List<Funcion> listarFuncionesPorPelicula(@WebParam(name = "peliculaId") int peliculaId) throws Exception {
        String url = this.urlBase + "/" + FUNCION_RESOURCE + "/pelicula/" + peliculaId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return this.deserializationMapper.readValue(json, new TypeReference<List<Funcion>>() {});
    }
}
