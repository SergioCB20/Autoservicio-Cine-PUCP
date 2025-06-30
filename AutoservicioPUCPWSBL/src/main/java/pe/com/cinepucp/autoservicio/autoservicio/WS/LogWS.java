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
import pe.com.cinepucp.autoservicio.model.auth.LogSistema;
import pe.com.cinepucp.autoservicio.utils.JsonUtils;

/**
 *
 * @author Sergio
 */
@WebService(serviceName = "LogWS",
        targetNamespace = "http://services.AutoCine.pucp.edu.pe/")
public class LogWS {
    private final ResourceBundle config;
    private final String urlBase;
    private final HttpClient client = HttpClient.newHttpClient();
    private final String LOG_RESOURCE = "/logs";
    private final ObjectMapper deserializationMapper;
    private final ObjectMapper serializationMapper;

    public LogWS() {
        this.config = ResourceBundle.getBundle("app");
        this.urlBase = this.config.getString("app.services.rest.baseurl");
        this.deserializationMapper = JsonUtils.getDeserializationMapper();
        this.serializationMapper = JsonUtils.getSerializationMapper();
    }

    @WebMethod(operationName = "registrarLog")
    public void registrarLog(@WebParam(name = "log") LogSistema log) throws Exception {
        String json = this.serializationMapper.writeValueAsString(log);

        String url;
        HttpRequest request;
        url = this.urlBase + this.LOG_RESOURCE;
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    
    @WebMethod(operationName = "listarLogs")
    public List<LogSistema> listarLogs(@WebParam(name = "fechaini") String fechaini,@WebParam(name = "fechafin") String fechafin) throws Exception {
        //String url = this.urlBase + this.LOG_RESOURCE;
        String url = String.format("%s%s/%s/%s", 
                                  this.urlBase, 
                                  this.LOG_RESOURCE, // debería ser "/logs" 
                                  fechaini, 
                                  fechafin);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);
        List<LogSistema> logs = this.deserializationMapper.readValue(json, new TypeReference<List<LogSistema>>(){});

        return logs;
    }
    
}
