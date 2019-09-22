package com.grupoASD.exceptionHandler;

import java.io.Serializable;

public class BusquedaNoResultException extends Exception implements Serializable {
    
  public BusquedaNoResultException(String message) {
      super(message);
  }
}
