/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.dao;

import java.util.List;
import pe.com.cinepucp.autoservicio.model.auth.LogSistema;


/**
 *
 * @author Sergio
 */
public interface ILogSistemaDAO extends ICrud<LogSistema>{
    public List<LogSistema> listarReporteLogs(String fechaini,String fechafin);
}
