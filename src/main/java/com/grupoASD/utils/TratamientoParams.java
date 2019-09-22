package com.grupoASD.utils;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TratamientoParams {
    
    private TratamientoParams(){};

  /**
  * Convierte una fecha en formato String a Date
  *
  * @param fechaEntrada La fecha de la compra en formato yyyy-MM-dd.
  * @throws             ParseException en tal caso de no poder convertir la fecha de entrada
  * @return             La fecha convertida en formato Date para asi poder realizar la consulta por medio de JPA Hibernate a la DB de MySQL
  */
    public static Date convertirFecha(String fechaEntrada) throws ParseException{
        Date fechaConvertida = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEntrada);

        return fechaConvertida;
    }
    
}
