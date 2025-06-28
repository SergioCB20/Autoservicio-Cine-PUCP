package pe.com.cinepucp.autoservicio.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper deserializationMapper;
    private static final ObjectMapper serializationMapper;

    static {
        deserializationMapper = new ObjectMapper();
        deserializationMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Soporte para múltiples formatos de fecha en java.util.Date
        final List<SimpleDateFormat> dateFormats = Arrays.asList(
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"),
            new SimpleDateFormat("yyyy-MM-dd HH:mm"),
            new SimpleDateFormat("yyyy-MM-dd")
        );

        // Deserializador para Date
        SimpleModule dateModule = new SimpleModule();
        dateModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                String dateStr = p.getText();
                if (dateStr == null || dateStr.trim().isEmpty()) {
                    return null;
                }
                if (dateStr.endsWith("[UTC]")) {
                    dateStr = dateStr.replace("[UTC]", "");
                }
                for (SimpleDateFormat format : dateFormats) {
                    try {
                        return ((SimpleDateFormat) format.clone()).parse(dateStr);
                    } catch (ParseException e) {
                        // probar siguiente formato
                    }
                }
                throw new IOException("No se pudo parsear la fecha: " + dateStr);
            }
        });

        // ✅ Deserializador para LocalDateTime
        SimpleModule localDateTimeModule = new SimpleModule();
        localDateTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                String text = p.getText();
                if (text == null || text.trim().isEmpty()) return null;
                try {
                    return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME); // "2025-06-28T18:30:00"
                } catch (DateTimeParseException e) {
                    try {
                        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // "2025-06-28 18:30"
                    } catch (DateTimeParseException e2) {
                        throw new IOException("Error al parsear LocalDateTime: " + text, e2);
                    }
                }
            }
        });

        deserializationMapper.registerModule(dateModule);
        deserializationMapper.registerModule(localDateTimeModule);
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
