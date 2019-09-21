package com.grupoASD.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name = "area_ciudad")
@Entity
@NamedQueries({
    @NamedQuery(name="AreaCiudad.findAll" ,query="select ai from AreaCiudad ai")
})
public class AreaCiudad implements Serializable{
    
    public AreaCiudad(){}
    
    @Id
    @Column(name="id_area_ciudad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaCiudad;
    
    @ManyToOne
    @JoinColumn(name="id_area_fk", referencedColumnName="id_area")
    private Area area;
    
    @ManyToOne
    @JoinColumn(name="id_ciudad_fk", referencedColumnName="id_ciudad")
    private Ciudad ciudad;        

    public Integer getIdAreaCiudad() {
        return idAreaCiudad;
    }

    public void setIdAreaCiudad(Integer idAreaCiudad) {
        this.idAreaCiudad = idAreaCiudad;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    
    
}
