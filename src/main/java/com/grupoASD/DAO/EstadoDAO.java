package com.grupoASD.DAO;

import com.grupoASD.entities.Estado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase que interactua con la base de datos por medio de consultas en Hibernate
 * 
 * @author Daniel Melo Melo
 */
@Stateless
public class EstadoDAO {
    
    
    @PersistenceContext(unitName = "ActivosP")
    EntityManager em;
        
    /**
     * Método que el obtiene Area por id
     * 
     * @return Area
     */
    public Estado getEstadoById(String id){
        Estado estado = new Estado();
        int idConvert = -1;
                
        idConvert = Integer.parseInt(id);
        estado = em.find(Estado.class, idConvert);
        
        return estado;
    }
    
}
