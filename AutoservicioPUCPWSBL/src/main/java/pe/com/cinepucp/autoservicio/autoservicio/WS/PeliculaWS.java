package pe.com.cinepucp.autoservicio.autoservicio.WS;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
// Eliminar importaciones de DeserializationFeature, SerializationFeature, SimpleModule, StdDateFormat, SimpleDateFormat, ParseException si solo las usas para ObjectMapper
// import com.fasterxml.jackson.databind.DeserializationFeature;
// import com.fasterxml.jackson.databind.SerializationFeature;
// import com.fasterxml.jackson.databind.module.SimpleModule;
// import com.fasterxml.jackson.databind.util.StdDateFormat;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ResourceBundle;
import pe.com.cinepucp.autoservicio.model.Peliculas.Pelicula;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

@WebService(serviceName = "PeliculaWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class PeliculaWS {

    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String PELICULA_RESOURCE = "peliculas";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;

    public PeliculaWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        
        // Inyectamos los mappers desde JsonUtils
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }

    @WebMethod(operationName = "registrarPelicula")
    public void registrarPelicula(@WebParam(name = "pelicula") Pelicula pelicula) throws Exception {
        String json = this.serializationMapper.writeValueAsString(pelicula);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.PELICULA_RESOURCE;
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "actualizarPelicula")
    public void actualizarPelicula(@WebParam(name = "pelicula") Pelicula pelicula) throws Exception {
        String json = this.serializationMapper.writeValueAsString(pelicula);

        String url;
        HttpRequest request;
        url = this.urlBase + "/" + this.PELICULA_RESOURCE + "/" + pelicula.getPeliculaId();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "eliminarPelicula")
    public void eliminarPelicula(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + this.PELICULA_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @WebMethod(operationName = "buscarPeliculaPorId")
    public Pelicula buscarPeliculaPorId(@WebParam(name = "id") int id) throws Exception {
        String url = this.urlBase + "/" + this.PELICULA_RESOURCE + "/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Pelicula pelicula = this.deserializationMapper.readValue(json, Pelicula.class);
        return pelicula;
    }

    @WebMethod(operationName = "listarPeliculas")
    public List<Pelicula> listarPeliculas() throws Exception {
        String url = this.urlBase + "/" + this.PELICULA_RESOURCE;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        List<Pelicula> peliculas = this.deserializationMapper.readValue(json, new TypeReference<List<Pelicula>>() {});

        return peliculas;
    }
}