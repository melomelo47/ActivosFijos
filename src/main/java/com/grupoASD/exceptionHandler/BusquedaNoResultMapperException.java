
package com.grupoASD.exceptionHandler;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Clase que mapea una excepción personalizada referente a que la busqueda no arrojó datos
 * JAX-RS lee las excepciones personalizadas en tiempo de ejecución y las muestra
 * 
 * @author Usuario
 */
@Provider
public class BusquedaNoResultMapperException implements ExceptionMapper<BusquedaNoResultException> {

    @Override
    public Response toResponse(BusquedaNoResultException ex){
        
        return Response.status(404).entity(ex.getMessage()).type(MediaType.APPLICATION_JSON).build();
        
    } 
}
