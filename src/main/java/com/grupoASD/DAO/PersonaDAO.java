package com.grupoASD.DAO;

import com.grupoASD.entities.Persona;
import com.grupoASD.utils.TratamientoParams;
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
public class PersonaDAO {
    
    @PersistenceContext(unitName = "ActivosP")
    EntityManager em;
    
    /**
     * Método que obtiene todas las personas de la empresa
     * 
     * @return La lista de personas
     */
    public List<Persona> getAllPersonas(){
        
    List<Persona> personas = new ArrayList<>();
    Query query = em.createNamedQuery("Persona.findAll");
    personas = query.getResultList();
    
    return personas;
    }
    
    /**
     * Método que obtiene una persona por id
     * 
     * @param id   El id de la persona
     * @return     Una persona
     */
    public Persona getPersonaByid(String id) {
        Persona persona = new Persona();
        int idConvertido = TratamientoParams.convertirStringAEntero(id);
                
        idConvertido = Integer.parseInt(id);
        persona = em.find(Persona.class, idConvertido);
        
        return persona;
    }
    
    
    
    
}
