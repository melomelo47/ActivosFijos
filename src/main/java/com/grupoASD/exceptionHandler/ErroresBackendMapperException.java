package com.grupoASD.exceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que mapea una excepción personalizada referente a un error en el backend
 * JAX-RS lee las excepciones personalizadas en tiempo de ejecucióny las muestra
 * 
 * @author Usuario
 */
@Provider
public class ErroresBackendMapperException implements ExceptionMapper<ErroresBackendException> {

    @Override
    public Response toResponse(ErroresBackendException ex) {
        
        return Response.status(500).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
    }   
}
