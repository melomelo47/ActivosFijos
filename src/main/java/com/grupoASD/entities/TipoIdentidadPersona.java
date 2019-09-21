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


@Table(name="tipo_identidad_persona")
@Entity
@NamedQueries({
    @NamedQuery(name = "TipoIdentidadPersona.findAll", query = "select ti from TipoIdentidadPersona ti")
})
public class TipoIdentidadPersona implements Serializable{
    
    @Id
    @Column(name="id_tipo_identidad_persona")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIdentidadPersona;
    
    @Column(name="nombre_indentidad_persona")
    private String nombreTipoIdentidadPersona;

    public Integer getIdIdentidadPersona() {
        return idIdentidadPersona;
    }

    public void setIdIdentidadPersona(Integer idIdentidadPersona) {
        this.idIdentidadPersona = idIdentidadPersona;
    }

    public String getNombreTipoIdentidadPersona() {
        return nombreTipoIdentidadPersona;
    }

    public void setNombreTipoIdentidadPersona(String nombreTipoIdentidadPersona) {
        this.nombreTipoIdentidadPersona = nombreTipoIdentidadPersona;
    } 
    
}
