package com.grupoASD.DAO;

import com.grupoASD.entities.AreaCiudad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Clase que interactua con la base de datos por medio de consultas en Hibernate
 * 
 * @author Daniel Melo Melo
 */
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
    
    /**
     * Método que el obtiene Area por id
     *
     * @param id El id del a ciudad
     * @return El área
     */
    public AreaCiudad getAreaCiudadById(String id){
        AreaCiudad areaCiudad = new AreaCiudad();
        int idConvert = -1;
                
        idConvert = Integer.parseInt(id);
        areaCiudad = em.find(AreaCiudad.class, idConvert);
        
        return areaCiudad;
    }
    
}
