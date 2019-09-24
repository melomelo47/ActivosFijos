package com.grupoASD.services;

import com.grupoASD.DAO.ActivoFijoDAO;
import com.grupoASD.DAO.AreaCiudadDAO;
import com.grupoASD.DAO.EstadoDAO;
import com.grupoASD.DAO.PersonaDAO;
import com.grupoASD.DAO.TipoActivoFijoDAO;
import com.grupoASD.entities.ActivoFijo;
import com.grupoASD.entities.AreaCiudad;
import com.grupoASD.entities.Estado;
import com.grupoASD.entities.Persona;
import com.grupoASD.entities.TipoActivoFijo;
import com.grupoASD.exceptionHandler.BusquedaNoResultException;
import com.grupoASD.exceptionHandler.DatosFaltantesException;
import com.grupoASD.exceptionHandler.ErroresBackendException;
import com.grupoASD.utils.ResponseUtils;
import com.grupoASD.utils.TratamientoParams;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase referente a los activos
 *
 * @author Daniel Felipe Melo Melo
 */
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
    
    @EJB
    private TipoActivoFijoDAO tipoActivoDAO;
    
    @EJB
    private EstadoDAO estadoDAO;
    
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
    public Response obtenerActivoPorNombre(@PathParam("name") String name ) throws BusquedaNoResultException{
        
        Response responseAPI; 
        
        List<ActivoFijo> listActivosFijos  = activoFijoDAO.getActivoFijoByName(name);
        
        if(listActivosFijos == null){
            throw new BusquedaNoResultException("No hay activos fijos con el nombre pasado por parámetro"); 
        }
        
        responseAPI = ResponseUtils.corsResponseGET(listActivosFijos, 200);
        
        return responseAPI;
    }
    
    
  /**
  * Busca un activo por fecha, tipo y serial
  *
  * @param tipo                      El número del tipo del activo.
  * @param fechaCompra               La fecha de la compra en formato yyyy-MM-dd.
  * @param numSerial                 El nuevo título de la descripción.
  * @throws ErroresBackendException  En tal caso de que la fecha de compra no este en el formato yyyy-mm-dd
  * @throws DatosFaltantesException  En tal caso de que falte algún parámetro de entrada para realizar la búsqueda
  * @return Respuesta del activo fijo en formato JSON
  */
    @GET
    @Path("tipoActivo/{tipo: .*}/fechaCompra/{fecCompra: .*}/numeroSerial/{numSerial: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerActivoTipFecSer(@PathParam("tipo") String tipo, 
            @PathParam("fecCompra") String fechaCompra,
            @PathParam("numSerial") String numSerial ) throws ErroresBackendException, 
                                                              DatosFaltantesException{
        if(tipo.equals("") && fechaCompra.equals("") && numSerial.equals("")){
            throw new DatosFaltantesException("Los parámetros fecha de compra del activo, el tipo del activo y el número del serial son obligatorios");
        }
        
        Response responseAPI; 
        
        List<ActivoFijo> listActivosFijos;
        
        Date fechaConvertida = new Date();
        
            try{
                fechaConvertida = TratamientoParams.convertirFecha(fechaCompra);
            }catch(ParseException e){
                throw new ErroresBackendException("La fecha de compra debe ser en formato YYYY-MM-DD");
            }
 
        listActivosFijos  = activoFijoDAO.getActivoFijoByTipFecSer(tipo,fechaConvertida,numSerial);
       
        responseAPI = ResponseUtils.corsResponseGET(listActivosFijos, 200);
        
        return responseAPI;
                 
    }
    
    /**
     * Inserta un nuevo activo
     * 
     * @param activoFijo           Objeto activo fijo que se persistirá en la DB
     * @param estadoActual         Estado en el que se encuentra el activo fijo
     * @param personaActivo        Persona a la cual se le asignará el activo fijo
     * @param areaCiudadActivoFijo Area a la cual se le asignará el activo fijo
     * @param tipoActivo           Tipo de activo fijo
     * @throws BusquedaNoResultException
     * @throws ErroresBackendException
     * @throws DatosFaltantesException 
     * @return Retorna estado correcto cuando se hace la inserción de manera satisfactoria 
     */
    @POST
    @Path("estadoActual/{estadoActual: .*}/personaAsignada/{personaActivo: .*}/areaCiudadActivo/{areaCiudadActivo: .*}/tipoActivoFijo/{tipoActivo: .*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearActivo(ActivoFijo activoFijo, @PathParam("estadoActual") String estadoActual, 
                                @PathParam("personaActivo") String personaActivo, @PathParam("areaCiudadActivo") String areaCiudadActivoFijo,
                                @PathParam("tipoActivo") String tipoActivo
 ) throws BusquedaNoResultException, ErroresBackendException, DatosFaltantesException{
        Response responseAPI;
        
        if( activoFijo.getSerialActivoFijo() == null  ||  activoFijoDAO.getActivoFijoBySerial(activoFijo.getSerialActivoFijo() ) != null   ){
            throw new ErroresBackendException("La llave del serial es igual a una llave de serial ya insertada en la base de datos o la llave del serial está vacia");
        }
        
        validarFechas(activoFijo);
        validarEstadoActual(estadoActual, activoFijo);
        validarTipoActivoFijo(tipoActivo, activoFijo);
        valdiarPersonasEstados(personaActivo, areaCiudadActivoFijo, activoFijo);
        
        
        try{
            activoFijoDAO.createActivoFijoDAO(activoFijo);
        } catch(Exception e){
            throw new ErroresBackendException("Hubo un error a la hora de realizar la inserción");
        }
        
        responseAPI = ResponseUtils.corsResponseGen(200);
        
        return responseAPI;
    }
    
    /**
     * Permite solo la actualziación del activo fijo como su fecha, serial etc.
     * 
     * @param activoFijo
     * @param serialActivoBusqueda
     * @return
     * @throws ErroresBackendException
     * @throws DatosFaltantesException
     * @throws BusquedaNoResultException 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("ActualizarActivo/{serialActivoBusqueda: .*}")
    public Response actualizarActivo(ActivoFijo activoFijo, 
                                     @PathParam("serialActivoBusqueda") String serialActivoBusqueda) throws ErroresBackendException, DatosFaltantesException, BusquedaNoResultException, Exception{
        Response responseAPI;
        ActivoFijo activoFijoActualizable = new ActivoFijo();
        
        
        if( activoFijoDAO.getActivoFijoBySerial(activoFijo.getSerialActivoFijo() ) != null   ){
            throw new ErroresBackendException("La llave del serial es  que intenta actualizar es igual a una llave de serial ya insertada en la base de datos");
        }
        
        activoFijoActualizable = activoFijoDAO.getActivoFijoBySerial(serialActivoBusqueda);
        
        if(activoFijoActualizable == null){
            throw new BusquedaNoResultException("El recurso que intenta actualizar no existe");
        }
        
        validarFechas(activoFijo, activoFijoActualizable);
        setParams(activoFijo, activoFijoActualizable);

        
        activoFijoDAO.updateActivoFijoDAO(activoFijoActualizable);
            
        responseAPI = ResponseUtils.corsResponseGen(200);
                
        return responseAPI;
    }
    
    
    
    /**
     * Valida las fechas de baja y de compra
     * @param activoFijo                Objeto activo fijo
     * @throws ErroresBackendException  En caso de que la fecha de compra sea mayor o igual a la fecha de baja
     * @throws DatosFaltantesException  En caso de que la fecha de compra no esté o sea null
     */
    private void validarFechas( ActivoFijo activoFijo ) throws ErroresBackendException, DatosFaltantesException{
            if( ((activoFijo.getFechaCompraActivoFijo() != null ) && (activoFijo.getFechaBajaActivoFijo() != null))   ){
            
            
            if(  TratamientoParams.compararFechas(activoFijo.getFechaBajaActivoFijo(), activoFijo.getFechaCompraActivoFijo()) <= 0  ){
                
                throw new ErroresBackendException("Las fechas de baja y de compra son iguales o la fecha de compra es mayor a la de baja");
                
            }
        } else if( activoFijo.getFechaCompraActivoFijo() == null){

             throw new DatosFaltantesException("La fecha de compra es obligatoria");
        }
    }
    

     /**
     * Valida las fechas de baja y de compra
     * @param activoFijo                Objeto activo fijo
     * @throws ErroresBackendException  En caso de que la fecha de compra sea mayor o igual a la fecha de baja
     * @throws DatosFaltantesException  En caso de que la fecha de compra no esté o sea null
     */
    private void validarFechas( ActivoFijo activoFijo, ActivoFijo activoFijoActualizable ) throws ErroresBackendException, DatosFaltantesException {   
        
        if(activoFijo.getFechaBajaActivoFijo() != null && activoFijo.getFechaCompraActivoFijo() != null){
            validarFechas(activoFijo);
        }else if(activoFijo.getFechaBajaActivoFijo() != null && activoFijo.getFechaCompraActivoFijo() == null){
            
            if(TratamientoParams.compararFechas(activoFijo.getFechaBajaActivoFijo(), activoFijoActualizable.getFechaCompraActivoFijo()) <= 0){
                
                throw new ErroresBackendException("Las fechas de baja y de compra son iguales o la fecha de compra es mayor a la de baja");
                
            }             
        } else if(activoFijo.getFechaBajaActivoFijo() == null && activoFijo.getFechaCompraActivoFijo() != null) {
            
            if(activoFijoActualizable.getFechaBajaActivoFijo() != null){
                if(TratamientoParams.compararFechas(activoFijoActualizable.getFechaBajaActivoFijo(), activoFijo.getFechaCompraActivoFijo()) <= 0){

                    throw new ErroresBackendException("Las fechas de baja y de compra son iguales o la fecha de compra es mayor a la de baja");

                }
            }
        }
    }
          
    /**
     * Valida el estado actual, este debe existir en la base de datos
     * @param estadoActual                Número del estado acual en que se encuentra el activo
     * @param activoFijo                  Objeto activo fijo
     * @throws BusquedaNoResultException  En caso de que el estado pasado por parámetro no exista
     * @throws DatosFaltantesException    Encaso de que el estado sea null
     */
    private void validarEstadoActual( String estadoActual, ActivoFijo activoFijo ) throws BusquedaNoResultException, DatosFaltantesException{
            if(!estadoActual.equals("")){
            
            Estado estado = new Estado();
            
            estado = estadoDAO.getEstadoById(estadoActual);
            
            if(estado == null){
                throw new BusquedaNoResultException("El estado pasado por parámetro no es válido");
            }
            
            activoFijo.setEstadoActual(estado);
            
        } else {
            throw new DatosFaltantesException("El estado del activo fijo pasado por parámetro es obligatorio");       
        }
    }
    
    /**
     * Valida el tipo de activo fijo
     * @param tipoActivo                 Número del tipo del activo fijo
     * @param activoFijo                 Objeto activo fijo
     * @throws BusquedaNoResultException En caso de que el tipo de activo fijo pasado no se encuentre en la base
     * @throws DatosFaltantesException   En caso de que el tipo de activo fijo pasado por parámetro sea null
     */
    private void validarTipoActivoFijo( String tipoActivo, ActivoFijo activoFijo ) throws BusquedaNoResultException, DatosFaltantesException{
     if(!tipoActivo.equals("")){
            TipoActivoFijo tipoActivoFijo = new TipoActivoFijo();
            
            tipoActivoFijo = tipoActivoDAO.getEstadoById(tipoActivo);
            
            if(tipoActivoFijo == null){
                throw new BusquedaNoResultException("El tipo del activo fijo no es válido");
            }
            
            activoFijo.setTipoActivoFijo(tipoActivoFijo);
        } else {
            throw new DatosFaltantesException("El tipo del activo fijo pasado por parámetro es obligatorio");
        }
    }
    
    /**
     * Valida la asgignación de un activo 
     * 
     * @param personaActivo               Número de la persona (id)
     * @param areaCiudadActivoFijo        Numero del área
     * @param activoFijo                  Objeto activo fijo
     * @throws BusquedaNoResultException  En caso de que el area/persona no existan
     * @throws ErroresBackendException    En caso de que exista un error de asignación.
     */
    private void valdiarPersonasEstados(String personaActivo, String areaCiudadActivoFijo, ActivoFijo activoFijo) throws BusquedaNoResultException, ErroresBackendException{
        
            if( ((((personaActivo.equals("")) && (!areaCiudadActivoFijo.equals(""))) 
                || ((!personaActivo.equals("")) && (areaCiudadActivoFijo.equals("")))) 
                && activoFijo.getEstadoActual().getNombreEstado().equalsIgnoreCase("asignado")) 
                || ((personaActivo.equals("")) && (areaCiudadActivoFijo.equals("")) &&  !activoFijo.getEstadoActual().getNombreEstado().equalsIgnoreCase("asignado")) ){
            
            if(!personaActivo.equals("")){
                Persona persona = new Persona();
            
                persona = personaDAO.getPersonaByid(personaActivo);
            
                if(persona == null){
                    throw new BusquedaNoResultException("La persona a la cual se intenta asignar el activo fijo es inválida, no existe");
                }
                
                activoFijo.setPersona(persona);

                
            } else if (!areaCiudadActivoFijo.equals("")) {
                
                AreaCiudad areaCiudad = new AreaCiudad();
                
                areaCiudad = areaCiudadDAO.getAreaCiudadById(areaCiudadActivoFijo);
                

                if(areaCiudad == null){
                    throw new BusquedaNoResultException("El área a la cual se intenta asignar el activo fijo es inválida, no existe");
                }
                
                activoFijo.setAreaCiudad(areaCiudad);
                
            }
            
        } else {
            
            throw new ErroresBackendException("El estado debe ser de tipo 'asignado' cuando este es asignado a una persona o ciudad, "
                    + "en caso de no ser asginado a nadie debe estar es un estado diferente a 'asginado'");
        }
            
    }
   
    /**
     * Settea los parámetros para poder realizar la actualización
     * @param activoFijo
     * @param activoFijoActualizable 
     */
    private void setParams(ActivoFijo activoFijo, ActivoFijo activoFijoActualizable){
        
        if(activoFijo.getAltoActivoFijo() != 0){
            activoFijoActualizable.setAltoActivoFijo(activoFijo.getAltoActivoFijo());
        }
        if(activoFijo.getAnchoActivoFijo() != 0){
            activoFijoActualizable.setAnchoActivoFijo(activoFijo.getAnchoActivoFijo());
        }
        if(activoFijo.getColorActivoFijo() != null
                || !activoFijo.getColorActivoFijo().equalsIgnoreCase("")){
            activoFijoActualizable.setColorActivoFijo(activoFijo.getColorActivoFijo());
        }
        if(activoFijo.getNombreActivoFijo() != null
                || !activoFijo.getNombreActivoFijo().equalsIgnoreCase("")){
            activoFijoActualizable.setNombreActivoFijo(activoFijo.getNombreActivoFijo());
        }
        if(activoFijo.getDescripcionActivoFijo() != null
                || !activoFijo.getDescripcionActivoFijo().equalsIgnoreCase("")){
            activoFijo.setDescripcionActivoFijo(activoFijo.getDescripcionActivoFijo());
        }
        if(activoFijo.getPesoActivoFijo() != 0){
            activoFijoActualizable.setPesoActivoFijo(activoFijo.getPesoActivoFijo());
        }
        if(activoFijo.getValorCompraActivoFijo() != 0){
            activoFijoActualizable.setAltoActivoFijo(activoFijo.getValorCompraActivoFijo());
        }
        if(activoFijo.getSerialActivoFijo() != null){
            activoFijoActualizable.setSerialActivoFijo(activoFijo.getSerialActivoFijo());
        }
        if(activoFijo.getFechaBajaActivoFijo() != null){
            activoFijoActualizable.setFechaBajaActivoFijo(activoFijo.getFechaBajaActivoFijo());
        }
        if(activoFijo.getFechaCompraActivoFijo() != null){
            activoFijoActualizable.setFechaCompraActivoFijo(activoFijo.getFechaCompraActivoFijo());
        }
    }
}
