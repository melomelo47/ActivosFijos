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

@Table(name = "area")
@Entity
@NamedQueries({
    @NamedQuery(name="Area.FindAll", query="select a from Area a")
})
public class Area implements Serializable {
    public Area(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id_area")
    private Integer idArea;
    
    @Column(name="nombre_area")
    private String nombreArea;

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
    
    
        
}
