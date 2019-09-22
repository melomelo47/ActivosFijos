package com.grupoASD.DAO;

import com.grupoASD.entities.AreaCiudad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AreaCiudadDAO {
    
    @PersistenceContext(unitName = "ActivosP")
    EntityManager em;
    
    /**
     * Método que obtiene todas las personas de la empresa
     * 
     * @return La lista de personas
     */
    public List<AreaCiudad> getAllAreas(){
        
    List<AreaCiudad> personas = new ArrayList<>();
    
    Query query = em.createNamedQuery("AreaCiudad.findAll");
    personas = query.getResultList();
    
    return personas;
    }
}
