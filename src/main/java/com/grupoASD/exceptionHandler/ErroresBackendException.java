package com.grupoASD.exceptionHandler;

import java.io.Serializable;


public class ErroresBackendException extends Exception implements Serializable{
    
    public ErroresBackendException(String msg){
        super(msg);
    }
    
}
