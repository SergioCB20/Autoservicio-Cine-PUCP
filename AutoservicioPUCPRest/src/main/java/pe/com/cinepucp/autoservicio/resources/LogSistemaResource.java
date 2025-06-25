/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.bo.ILogSistemaBO;
import pe.com.cinepucp.autoservicio.autoserviciocinepucpbusiness.boimpl.LogSistemaBOImpl;
import pe.com.cinepucp.autoservicio.model.auth.LogSistema;

/**
 *
 * @author Sergio
 */
@Path("logs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@SuppressWarnings("UnnecessaryReturnStatement")
public class LogSistemaResource {
    private final ILogSistemaBO logBO;
    public LogSistemaResource() {
        logBO = new LogSistemaBOImpl();
    }
    
    @GET
    public List<LogSistema> listar(){
        return this.logBO.listar();
    }
    
    @POST
    public Response crear(LogSistema log) throws Exception {
        if (log == null ) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Log no es valido")
                    .build();
        }
        
        this.logBO.registrar(log);
        URI location = URI.create("/AutoservicioCine/api/logs/" + log.getId());
        
        return Response.created(location)
                .entity(log)
                .build();
    }
}
