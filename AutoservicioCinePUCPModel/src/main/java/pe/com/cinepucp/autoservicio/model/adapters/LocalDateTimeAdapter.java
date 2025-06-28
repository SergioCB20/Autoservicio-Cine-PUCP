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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Override
    public LocalDateTime unmarshal(String dateString) throws Exception {
        return dateString != null ? LocalDateTime.parse(dateString,formatter) : null;
    }

    @Override
    public String marshal(LocalDateTime localDatetime) throws Exception {
        return localDatetime != null ? localDatetime.format(formatter): null;
    }
}

