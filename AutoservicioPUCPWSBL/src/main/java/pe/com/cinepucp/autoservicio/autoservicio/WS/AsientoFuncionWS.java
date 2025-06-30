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
import pe.com.cinepucp.autoservicio.model.salas.AsientoFuncion;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

@WebService(serviceName = "AsientoFuncionWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class AsientoFuncionWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String ASIENTO_FUNCION_RESOURCE = "asientofunciones";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;
    
    public AsientoFuncionWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }
    
    @WebMethod(operationName = "registrarAsientoFuncion")
    public void registrarAsientoFuncion(@WebParam(name = "asientofuncion") AsientoFuncion funcion) throws Exception {
        if (funcion == null) {
            throw new IllegalArgumentException("El asiento-función no puede ser null");
        }

        String json = this.serializationMapper.writeValueAsString(funcion);
        System.out.println(json);
        String url = this.urlBase + "/" + ASIENTO_FUNCION_RESOURCE;
        System.out.println(url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new Exception("Error al registrar asientofunción. Código: " + response.statusCode());
        }
    }
    
    @WebMethod(operationName = "listarAsientosPorFunciones")
    public List<AsientoFuncion> listarAsientosPorFunciones(@WebParam(name = "funcionId") int funcionId) throws Exception {
        String url = this.urlBase + "/" + ASIENTO_FUNCION_RESOURCE + "/asientofuncion/" + funcionId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return this.deserializationMapper.readValue(json, new TypeReference<List<AsientoFuncion>>() {});
    }
    
}

