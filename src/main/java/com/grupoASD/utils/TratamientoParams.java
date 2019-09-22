package com.grupoASD.utils;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
* Clase encargada de la validación, conversion, comparación de parámetros de entrada.
*
* @author Daniel Melo Melo
*/
public class TratamientoParams {
    
    private TratamientoParams(){};

   /**
   * Convierte una fecha de formato String a Date
   *
   * @param fechaEntrada La fecha de la compra en formato yyyy-MM-dd.
   * @throws             ParseException en tal caso de no poder convertir la fecha de entrada
   * @return             La fecha convertida en formato Date para asi poder realizar la consulta por medio de JPA Hibernate a la DB de MySQL
   */
    public static Date convertirFecha(String fechaEntrada) throws ParseException{
        Date fechaConvertida = new SimpleDateFormat("yyyy-MM-dd").parse(fechaEntrada);

        return fechaConvertida;
    }
   
   /**
   * Compara 2 fechas para saber cual es menor
   *
   * @param fechaBaja    La fecha de baja en formato yyyy-MM-dd.
   * @param fechaCompra  La fecha de la compra en formato yyyy-MM-dd.
   * @return             Un entero, si la fecha de baja es menor a la de alta retornara un -1 y si las fechas son iguales retorna 0
   */
    public static int compararFechas(Date fechaBaja, Date fechaCompra){
        int enteroRespuestaComparacion;
        enteroRespuestaComparacion = fechaBaja.compareTo(fechaCompra);
        return enteroRespuestaComparacion;
    }
    
    
   /**
   * Convierte de String a entero
   *
   * @param cadena    Cadena de texto que contiene un entero
   * @return          Número convertido en int
   */
    public static int convertirStringAEntero(String cadena){
        int numero = Integer.parseInt(cadena);
        return numero;
    }
    
}
