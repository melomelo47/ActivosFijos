package com.grupoASD.utils;

import java.util.List;
import javax.ws.rs.core.Response;

/**
 * Clase encargada de realizar la construcción de la respueta
 * 
 * @author Daniel Felipe Melo Melo
 */
public class ResponseUtils {
    
    private ResponseUtils(){}
    
     /**
     *
     * @param lista              Lista la cual se mostrará en la respuesta por medio del JSON
     * @param codigoStatus       Código de repuesta HTTP
     * @param metodoPermitido    Método al cual se concedera el acceso al Cross origin resource Sharing
     *
     * @return                   Retorna el response  
     */
    public static Response corsResponseGET(List lista ,int codigoStatus, String metodoPermitido){
        
    Response responseAPI = Response.status(codigoStatus)
      .header("Access-Control-Allow-Origin",      "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",     "origin, content-type, accept, authorization")
      .header("Access-Control-Allow-Methods",      metodoPermitido) 
      .entity( lista ).build(); 
        
      return responseAPI;
    }
       
    /**
     *
     * @param codigoStatus       Código de repuesta HTTP
     * @param metodoPermitido    Método al cual se concedera el acceso al Cross origin resource Sharing
     *
     * @return                   Retorna el response  
     */
    public static Response corsResponsePOST(int codigoStatus, String metodoPermitido){
        
    Response responseAPI = Response.status(codigoStatus)
      .header("Access-Control-Allow-Origin",      "*")
      .header("Access-Control-Allow-Credentials", "true")
      .header("Access-Control-Allow-Headers",     "origin, content-type, accept, authorization")
      .header("Access-Control-Allow-Methods",      metodoPermitido) 
      .build(); 
        
      return responseAPI;
    }
}
