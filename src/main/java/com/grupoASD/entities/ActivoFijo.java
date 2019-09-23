package com.grupoASD.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table( name= "activo_fijo")
@Entity
@NamedQueries({
    @NamedQuery(name="ActivoFijo.findAll", query="select ac from ActivoFijo ac")
})
public class ActivoFijo implements Serializable{
    
    public ActivoFijo(){}
    
    @Id
    @Column(name="serial_activo_fijo")
    private String serialActivoFijo;
    
    @Column(name="nombre_activo_fijo")
    private String nombreActivoFijo;
    
    @Column(name="descripcion_activo_fijo")
    private String descripcionActivoFijo;
    
    @Column(name="n_inventario_activo_fijo")
    private String numeroInvetarioActivoFijo;
    
    @Column(name="peso_activo_fijo")
    private double pesoActivoFijo;
    
    @Column(name="alto_activo_fijo")
    private double altoActivoFijo;
    
    @Column(name="ancho_activo_fijo")
    private double anchoActivoFijo;
    
    @Column(name="largo_activo_fijo")
    private double largoActivoFijo;
    
    @Column(name="valor_compra_activo_fijo")
    private double valorCompraActivoFijo;
    
    @Column(name="fecha_compra_activo_fijo")
    private Date fechaCompraActivoFijo;
    
    @Column(name="fecha_baja_activo_fijo")
    private Date fechaBajaActivoFijo;
    
    @Column(name="color_activo_fijo")
    private String colorActivoFijo;
    
    @ManyToOne
    @JoinColumn(name="estado_actual_fk_activo_fijo", referencedColumnName="id_estado", nullable = true)
    private Estado estadoActual;
    
    @ManyToOne
    @JoinColumn(name="persona_fk_activo_fijo", referencedColumnName="id_persona", nullable = true)
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name="area_ciudad_fk_activo_fijo", referencedColumnName="id_area_ciudad", nullable = true)
    private AreaCiudad areaCiudad;
    
    @ManyToOne
    @JoinColumn(name="tipo_activo_fijo_fk", referencedColumnName="id_tipo_activo", nullable = true)
    private TipoActivoFijo tipoActivoFijo;

    public String getNombreActivoFijo() {
        return nombreActivoFijo;
    }

    public void setNombreActivoFijo(String nombreActivoFijo) {
        this.nombreActivoFijo = nombreActivoFijo;
    }

    public String getDescripcionActivoFijo() {
        return descripcionActivoFijo;
    }

    public void setDescripcionActivoFijo(String descripcionActivoFijo) {
        this.descripcionActivoFijo = descripcionActivoFijo;
    }
    
    public String getSerialActivoFijo() {
        return serialActivoFijo;
    }

    public void setSerialActivoFijo(String serialActivoFijo) {
        this.serialActivoFijo = serialActivoFijo;
    }

    public String getNumeroInvetarioActivoFijo() {
        return numeroInvetarioActivoFijo;
    }

    public void setNumeroInvetarioActivoFijo(String numeroInvetarioActivoFijo) {
        this.numeroInvetarioActivoFijo = numeroInvetarioActivoFijo;
    }

    public double getPesoActivoFijo() {
        return pesoActivoFijo;
    }

    public void setPesoActivoFijo(double pesoActivoFijo) {
        this.pesoActivoFijo = pesoActivoFijo;
    }

    public double getAltoActivoFijo() {
        return altoActivoFijo;
    }

    public void setAltoActivoFijo(double altoActivoFijo) {
        this.altoActivoFijo = altoActivoFijo;
    }

    public double getAnchoActivoFijo() {
        return anchoActivoFijo;
    }

    public void setAnchoActivoFijo(double anchoActivoFijo) {
        this.anchoActivoFijo = anchoActivoFijo;
    }

    public double getLargoActivoFijo() {
        return largoActivoFijo;
    }

    public void setLargoActivoFijo(double largoActivoFijo) {
        this.largoActivoFijo = largoActivoFijo;
    }

    public double getValorCompraActivoFijo() {
        return valorCompraActivoFijo;
    }

    public void setValorCompraActivoFijo(double valorCompraActivoFijo) {
        this.valorCompraActivoFijo = valorCompraActivoFijo;
    }

    public Date getFechaCompraActivoFijo() {
        return fechaCompraActivoFijo;
    }

    public void setFechaCompraActivoFijo(Date fechaCompraActivoFijo) {
        this.fechaCompraActivoFijo = fechaCompraActivoFijo;
    }

    public Date getFechaBajaActivoFijo() {
        return fechaBajaActivoFijo;
    }

    public void setFechaBajaActivoFijo(Date fechaBajaActivoFijo) {
        this.fechaBajaActivoFijo = fechaBajaActivoFijo;
    }

    public String getColorActivoFijo() {
        return colorActivoFijo;
    }

    public void setColorActivoFijo(String colorActivoFijo) {
        this.colorActivoFijo = colorActivoFijo;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public AreaCiudad getAreaCiudad() {
        return areaCiudad;
    }

    public void setAreaCiudad(AreaCiudad areaCiudad) {
        this.areaCiudad = areaCiudad;
    }

    public TipoActivoFijo getTipoActivoFijo() {
        return tipoActivoFijo;
    }

    public void setTipoActivoFijo(TipoActivoFijo tipoActivoFijo) {
        this.tipoActivoFijo = tipoActivoFijo;
    }
    
    
    
    
    
}
