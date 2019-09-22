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

@Table(name = "tipo_activo_fijo")
@Entity
@NamedQueries({
    @NamedQuery(name = "TipoActivoFijo.findAll", query="select af from TipoActivoFijo af")    
})
public class TipoActivoFijo implements Serializable{
    
    public TipoActivoFijo(){}
    
    @Id
    @Column(name = "id_tipo_activo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoActivoFijo;
    
    @Column(name = "nombre_tipo_activo_fijo")
    private String nombreTipoIdFijo;

    public Integer getIdTipoActivoFijo() {
        return idTipoActivoFijo;
    }

    public void setIdTipoActivoFijo(Integer idTipoActivoFijo) {
        this.idTipoActivoFijo = idTipoActivoFijo;
    }

    public String getNombreTipoIdFijo() {
        return nombreTipoIdFijo;
    }

    public void setNombreTipoIdFijo(String nombreTipoIdFijo) {
        this.nombreTipoIdFijo = nombreTipoIdFijo;
    }
}
