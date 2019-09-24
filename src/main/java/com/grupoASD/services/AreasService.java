package com.grupoASD.services;

import com.grupoASD.DAO.AreaCiudadDAO;
import com.grupoASD.entities.AreaCiudad;
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
 *
 * Clase referente a las áreas
 * 
 * @author Daniel Melo Melo
 */
@Path("areas")
@RequestScoped
public class AreasService {

    
    @EJB
    private AreaCiudadDAO areaCiudadDAO;
        
     /**
     * Lista todas las areas
     *  
     * @return Respuesta la lista de areas en formato JSON
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAreas() throws BusquedaNoResultException{
        Response responseAPI;
        
        List<AreaCiudad> areas = areaCiudadDAO.getAllAreas();
        
        
        if(areas == null){
            throw new BusquedaNoResultException("No hay áreas en la base de datos");
        }
        
        responseAPI = ResponseUtils.corsResponseGET(areas, 200);
        
        return responseAPI; 
    }
    
}
