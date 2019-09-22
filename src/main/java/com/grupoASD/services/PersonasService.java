package com.grupoASD.services;

import com.grupoASD.DAO.PersonaDAO;
import com.grupoASD.entities.Persona;
import com.grupoASD.exceptionHandler.BusquedaNoResultException;
import com.grupoASD.utils.ResponseUtils;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase referente a las personas
 * 
 * @author Daniel Melo Melo
 */
@Path("/personas")
@RequestScoped
public class PersonasService {
    
    public PersonasService(){}
    
    @EJB
    private PersonaDAO personaDAO;
    
     /**
     * Lista todas las personas
     *  
     * @return Respuesta la lista de personas en formato JSON
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPesonas() throws BusquedaNoResultException{
        Response responseAPI;
                
        List<Persona> personas = personaDAO.getAllPersonas();
    
        if(personas == null){
            throw new BusquedaNoResultException("No hay personas en la base de datos");
        }
        
        responseAPI = ResponseUtils.corsResponseGET(personas, 200, "GET");
        
        return responseAPI;
    }
    
}
