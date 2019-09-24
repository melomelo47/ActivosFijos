package com.grupoASD.utils;

import java.util.List;
import javax.ws.rs.core.Response;

/**
 * Clase encargada de realizar la construcción de la respuesta
 * 
 * @author Daniel Felipe Melo Melo
 */
public class ResponseUtils {
    
    private ResponseUtils(){}
    
     /**
     *
     * @param lista              Lista la cual se mostrará en la respuesta por medio del JSON
     * @param codigoStatus       Código de repuesta HTTP
     *
     * @return                   Retorna el response  
     */
    public static Response corsResponseGET(List lista ,int codigoStatus){
        
    Response responseAPI = Response.status(codigoStatus).entity( lista ).build(); 
        
      return responseAPI;
    }
       
    /**
     *
     * @param codigoStatus       Código de repuesta HTTP
     *
     * @return                   Retorna el response  
     */
    public static Response corsResponseGen(int codigoStatus){
        
    Response responseAPI = Response.status(codigoStatus).build(); 
        
      return responseAPI;
    }
}
