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

@Entity
@Table(name="estado")
@NamedQueries({
    @NamedQuery(name = "Estado.findAll" , query= "select e from Estado e")
})
public class Estado implements Serializable{
    
    @Id
    @Column(name="id_estado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estadoId;
    
    @Column(name="nombre_estado")
    private String nombreEstado;

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
    
    
}
