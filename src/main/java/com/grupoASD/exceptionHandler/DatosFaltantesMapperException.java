/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoASD.exceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que mapea una excepción personalizada referente a un error de datos faltantes
 * JAX-RS lee las excepciones personalizadas en tiempo de ejecucióny las muestra
 * 
 * @author Usuario
 */
@Provider
public class DatosFaltantesMapperException implements ExceptionMapper<DatosFaltantesException>{


    @Override
    public Response toResponse(DatosFaltantesException ex) {
        return Response.status(400).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }
    
    
    
}
