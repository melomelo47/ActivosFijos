package com.grupoASD.DAO;

import com.grupoASD.entities.ActivoFijo;
import com.grupoASD.utils.TratamientoParams;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

/**
 * Clase que interactua con la base de datos por medio de consultas en Hibernate
 * 
 * @author Daniel Melo Melo
 */
@Stateless
public class ActivoFijoDAO {
 
    @PersistenceContext(unitName = "ActivosP")
    EntityManager em;
    
  /**
  * Obtiene todos los activos fijos de la empresa por nombre
  *
  * @param name     Nombre del activo fijo a buscar
  * @return Lista de todos los activos fijos
  */
    public List<ActivoFijo> getActivoFijoByName(String name){
        List<ActivoFijo> activosFijosList = new ArrayList<>();
        
        Query query = em.createQuery("select ac from ActivoFijo ac where ac.nombreActivoFijo = :name");
        query.setParameter("name", name);
        activosFijosList = query.getResultList();
        
        return activosFijosList;
    }
    
  /**
  * Obtiene todos los activos por tipo, número serial y fecha de compra del activo fijo
  *
  * @param tipo          Tipo del activo fijo
  * @param fechaCompra   Fecha en la que se compró el activo fijo
  * @param numSerial     Número de serie del activo fijo
  * @return Lista de activos fijos dependiento de parámetros de búsqueda 
  */
    public List<ActivoFijo> getActivoFijoByTipFecSer(String tipo, Date fechaCompra, String numSerial){
       List<ActivoFijo> activosFijosList = new ArrayList<>();
       int tipoNumero = TratamientoParams.convertirStringAEntero(tipo);
       
        Query query =  em.createQuery("select ac from ActivoFijo ac where ac.tipoActivoFijo.idTipoActivoFijo = :tipoActivo  "
               + "AND ac.fechaCompraActivoFijo = :fechaCompra AND ac.serialActivoFijo = :serialActivoFijo ");
       query.setParameter("tipoActivo", tipoNumero);
       query.setParameter("fechaCompra", fechaCompra);
       query.setParameter("serialActivoFijo", numSerial); 
       
       activosFijosList = query.getResultList();

       return activosFijosList;
    }
    
    public void createActivoFijoDAO(ActivoFijo activoFijo) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException{
        em.persist(activoFijo);
    }
    
}
