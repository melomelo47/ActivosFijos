package com.grupoASD.services;

import com.grupoASD.DAO.ActivoFijoDAO;
import com.grupoASD.entities.ActivoFijo;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/activos")
@RequestScoped
public class ActivosService {
    
    public ActivosService(){
    }
    
    @EJB 
    private ActivoFijoDAO activoFijoDAO;
    
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerActivoPorNombre(@PathParam("name") String name ){
        List<ActivoFijo> listActivosFijos  = activoFijoDAO.getActivoFijoByName(name);
        
          return Response.status(200).
                header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization")
      .header("Access-Control-Allow-Methods", 
        "GET") 
                .entity( listActivosFijos ).build();
    }
    
    
    
}
