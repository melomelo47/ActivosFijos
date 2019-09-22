package com.grupoASD.services;

import com.grupoASD.DAO.ActivoFijoDAO;
import com.grupoASD.DAO.AreaCiudadDAO;
import com.grupoASD.DAO.PersonaDAO;
import com.grupoASD.entities.ActivoFijo;
import com.grupoASD.entities.AreaCiudad;
import com.grupoASD.entities.Persona;
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
    
    @EJB
    private PersonaDAO personaDAO;
    
    @EJB
    private AreaCiudadDAO areaCiudadDAO;
    
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
        
        Response responseAPI; 
        
        List<ActivoFijo> listActivosFijos  = activoFijoDAO.getActivoFijoByName(name);
        
        responseAPI = corsReponseGET(listActivosFijos, 200, "GET");
        
        return responseAPI;
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
        
        Response responseAPI; 
        
        List<ActivoFijo> listActivosFijos;
        
        Date fechaConvertida = new Date();
        
            try{
                fechaConvertida = TratamientoParams.convertirFecha(fechaCompra);
            }catch(ParseException e){
                //exceptionError
            }
 
        listActivosFijos  = activoFijoDAO.getActivoFijoByTipFecSer(tipo,fechaConvertida,numSerial);
       
        responseAPI = corsReponseGET(listActivosFijos, 200, "GET");
        
        return responseAPI;
                 
    }
    
    /**
     * Lista todas las personas
     *  
     * @return Respuesta la lista de personas en formato JSON
     */
    @GET
    @Path("personas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPesonas(){
        Response responseAPI;
                
        List<Persona> personas = personaDAO.getAllPersonas();
    
        responseAPI = corsReponseGET(personas, 200, "GET");
        
        return responseAPI; 
    
    }
    
     /**
     * Lista todas las areas
     *  
     * @return Respuesta la lista de areas en formato JSON
     */
    @GET
    @Path("areas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerAreas(){
        Response responseAPI;
        
        List<AreaCiudad> areas = areaCiudadDAO.getAllAreas();
        
        responseAPI = corsReponseGET(areas, 200, "GET");
        
        return responseAPI; 
    }
    
    /**
     *
     * @param lista              Lista la cual se mostrará en la respuesta por medio del JSON
     * @param codigoStatus       Código de repuesta HTTP
     * @param metodoPermitido  Método al cual se concedera el acceso al Cross origin resource Sharing
     *
     * @return                   Retorna el response  
     */
    public static Response corsReponseGET(List lista ,int codigoStatus, String metodoPermitido){
        
    Response responseAPI = Response.status(codigoStatus)
      .header("Access-Control-Allow-Origin",      "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",     "origin, content-type, accept, authorization")
      .header("Access-Control-Allow-Methods",      metodoPermitido) 
      .entity( lista ).build(); 
        
      return responseAPI;
    }

}
