/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoASD.exceptionHandler;

import java.io.Serializable;


public class DatosFaltantesException extends Exception implements Serializable {
    
    public DatosFaltantesException(String msg){
        super(msg);
    }
    
}
