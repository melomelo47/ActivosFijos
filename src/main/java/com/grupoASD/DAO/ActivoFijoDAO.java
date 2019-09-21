package com.grupoASD.DAO;

import com.grupoASD.entities.ActivoFijo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ActivoFijoDAO {
 
    @PersistenceContext(unitName = "ActivosP")
    EntityManager em;
            
    public List<ActivoFijo> getAllActivoFijos(){
        return em.createNamedQuery("ActivoFijo.findAll").getResultList();
    }
    
    public List<ActivoFijo> getActivoFijoByName(String name){
        Query query = em.createQuery("select ac from ActivoFijo ac where ac.nombreActivoFijo = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }
}
