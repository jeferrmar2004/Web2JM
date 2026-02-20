package org.fifa.quinielajsf.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Convertidor de JSF para la clase java.time.LocalDateTime.
 * La anotación @FacesConverter(forClass = ...) registra este convertidor para que
 * se aplique automáticamente a cualquier propiedad de bean que sea del tipo LocalDateTime.
 */
@FacesConverter(forClass = LocalDateTime.class)
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    // Define el formato de fecha y hora que se usará en la vista (XHTML)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Convierte un String (proveniente del formulario) a un objeto LocalDateTime.
     */
    @Override
    public LocalDateTime getAsObject(FacesContext context, UIComponent component, String value) {
        // Si el valor es nulo o vacío, no hay nada que convertir.
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            // Intenta parsear el String usando el formato definido.
            return LocalDateTime.parse(value, FORMATTER);
        } catch (DateTimeParseException e) {
            // Si el formato no es válido, lanza una excepción de conversión con un mensaje claro.
            throw new ConverterException("El formato de fecha y hora no es válido. Utilice 'dd/MM/yyyy HH:mm'.", e);
        }
    }

    /**
     * Convierte un objeto LocalDateTime (del bean) a su representación como String.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDateTime value) {
        // Si el objeto es nulo, devuelve un String vacío.
        if (value == null) {
            return "";
        }
        // Formatea el objeto LocalDateTime a un String usando el formato definido.
        return value.format(FORMATTER);
    }
}
