/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.com.cinepucp.autoservicio.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JsonUtils {

    private final static ObjectMapper deserializationMapper;
    private final static ObjectMapper serializationMapper;

    static {
        deserializationMapper = new ObjectMapper();
        deserializationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final List<SimpleDateFormat> dateFormats = Arrays.asList(
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"),      // Formato original
            new SimpleDateFormat("yyyy-MM-dd")               // Solo fecha
        );

        SimpleModule customDateModule = new SimpleModule();
        customDateModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser p, DeserializationContext ctxt) throws java.io.IOException {
                String dateStr = p.getText();
                if (dateStr == null || dateStr.trim().isEmpty()) {
                    return null;
                }
                // Limpiar el sufijo [UTC] si existe
                if (dateStr.endsWith("[UTC]")) {
                    dateStr = dateStr.substring(0, dateStr.length() - "[UTC]".length());
                }
                for(SimpleDateFormat format : dateFormats){
                    try {
                        SimpleDateFormat clonedFormat = (SimpleDateFormat) format.clone();
                        return clonedFormat.parse(dateStr);
                    } catch (ParseException e) {
                        throw new java.io.IOException("Failed to parse Date value: " + dateStr, e);
                    }
                }
                throw new java.io.IOException("Failed to parse Date value with any supported format: " + dateStr);
            }
        });
        deserializationMapper.registerModule(customDateModule);
        deserializationMapper.registerModule(new JavaTimeModule());

        serializationMapper = new ObjectMapper();
        serializationMapper.registerModule(new JavaTimeModule());
        serializationMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        serializationMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    }

    public static ObjectMapper getDeserializationMapper() {
        return deserializationMapper;
    }

    public static ObjectMapper getSerializationMapper() {
        return serializationMapper;
    }
}
