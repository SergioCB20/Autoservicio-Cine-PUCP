/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.cinepucp.autoservicio.model.adapters;

/**
 *
 * @author Sergio
 */

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    
    @Override
    public LocalDate unmarshal(String dateString) throws Exception {
        return dateString != null ? LocalDate.parse(dateString) : null;
    }
    
    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate != null ? localDate.toString() : null;
    }
}