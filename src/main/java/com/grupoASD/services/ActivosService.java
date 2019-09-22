package com.grupoASD.services;

import com.grupoASD.DAO.ActivoFijoDAO;
import com.grupoASD.entities.ActivoFijo;
import com.grupoASD.utils.TratamientoParams;
import java.text.ParseException;
import java.util.Date;
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
    
    
  /**
  * Busca todos los activos por nombre
  *
  * @param name    El nombre del activo.
  * @return         Respuesta del activo fijo en formato JSON
  */
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerActivoPorNombre(@PathParam("name") String name ){
        List<ActivoFijo> listActivosFijos  = activoFijoDAO.getActivoFijoByName(name);
        
          return Response.status(200)
      .header("Access-Control-Allow-Origin",      "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",     "origin, content-type, accept, authorization")
      .header("Access-Control-Allow-Methods",     "GET") 
      .entity( listActivosFijos ).build();
    }
    
    
  /**
  * Busca un activo por fecha, tipo y serial
  *
  * @param tipo        El número del tipo del activo.
  * @param fechaCompra La fecha de la compra en formato yyyy-MM-dd.
  * @param numSerial   El nuevo título de la descripción.
  * @return            Respuesta del activo fijo en formato JSON
  */
    @GET
    @Path("busquedaActivo/{tipo}/{fecCompra}/{numSerial}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerActivoTipFecSer(@PathParam("tipo") int tipo, 
            @PathParam("fecCompra") String fechaCompra,
            @PathParam("numSerial") String numSerial ){
    
        Date fechaConvertida = new Date();
        
            try{
                fechaConvertida = TratamientoParams.convertirFecha(fechaCompra);
            }catch(ParseException e){
                //exceptionError
            }
 
       List<ActivoFijo> listActivosFijos  = activoFijoDAO.getActivoFijoByTipFecSer(tipo,fechaConvertida,numSerial);
       
                 return Response.status(200)
      .header("Access-Control-Allow-Origin",      "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",     "origin, content-type, accept, authorization")
      .header("Access-Control-Allow-Methods",      "GET") 
      .entity( listActivosFijos ).build(); 
                 
    }
    
    
    
    
}
