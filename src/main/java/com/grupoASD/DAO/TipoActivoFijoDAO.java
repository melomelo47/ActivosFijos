package com.grupoASD.DAO;

import com.grupoASD.entities.TipoActivoFijo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase que interactua con la base de datos por medio de consultas en Hibernate
 * 
 * @author Daniel Melo Melo
 */
@Stateless
public class TipoActivoFijoDAO {
    
    @PersistenceContext(unitName = "ActivosP")
    EntityManager em;
    
    /**
     * Método que el obtiene Area por id
     * 
     * @param id  El id de del estado 
     * @return Area
     */
    public TipoActivoFijo getEstadoById(String id){
        TipoActivoFijo tipoActivo = new TipoActivoFijo();
        int idConvert = -1;
                
        idConvert = Integer.parseInt(id);
        tipoActivo = em.find(TipoActivoFijo.class, idConvert);
        
        return tipoActivo;
    }
}
