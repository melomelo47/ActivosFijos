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

@Table(name = "ciudad")
@Entity
@NamedQueries({
    @NamedQuery(name="Ciudad.findAll", query="select c from Ciudad c") })
public class Ciudad implements Serializable {
    public Ciudad(){}
    
    @Id
    @Column(name="id_ciudad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCiudad;
    
    @Column(name="nombre_ciudad")
    private String nombreCiudad;

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

}
