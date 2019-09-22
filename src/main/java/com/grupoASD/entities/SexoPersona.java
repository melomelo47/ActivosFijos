package com.grupoASD.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="sexo_persona")
@Entity
@NamedQueries({
    @NamedQuery(name = "SexoPersona.findAll", query = "select se from SexoPersona se")
})
public class SexoPersona implements Serializable{
    
    @Id
    @Column(name="id_sexo_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSexoPersona;
    
    @Column(name="tipo_sexo_persona")
    private String tipoSexoPersona;

    public Integer getIdSexoPersona() {
        return idSexoPersona;
    }

    public void setIdSexoPersona(Integer idSexoPersona) {
        this.idSexoPersona = idSexoPersona;
    }

    public String getTipoSexoPersona() {
        return tipoSexoPersona;
    }

    public void setTipoSexoPersona(String tipoSexoPersona) {
        this.tipoSexoPersona = tipoSexoPersona;
    } 
}
