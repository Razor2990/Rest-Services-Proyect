package com.proyect.services.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que tiene métodos que nos permite realizar el formato de fechas
 */
public class DateFormat {

    private static final Logger logger = LoggerFactory.getLogger(DateFormat.class);

    public static LocalDate localDateToFormat(LocalDate date) {
        // Define el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // Formatea la fecha directamente y devuelve un LocalDate
        LocalDate formattedDate = LocalDate.parse(date.format(formatter), formatter);

        logger.info("Fecha con formato / / . ".concat(formattedDate.toString()));

        return formattedDate;
    }
}
