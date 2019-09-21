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

@Entity
@Table(name = "persona")
@NamedQueries({
    @NamedQuery(name="Persona.findAll", query="select p from Persona p")
})
public class Persona implements Serializable{

    public Persona(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;
    
    @Column(name = "nombre_persona")
    private String nombrePersona;

    @Column(name = "apll1_persona")
    private String apellido1Persona;
        
    @Column(name = "apll2_persona")
    private String apellido2Persona;     
    
    @Column(name = "numero_id_persona")
    private String NumeroIdPersona;
    
    @Column(name="edad_persona")
    private Integer edadPersona;
    
    @Column(name="cargo_persona")
    private String cargoPersona;

    @ManyToOne
    @JoinColumn(name="sexo_persona_fk", referencedColumnName="id_sexo_persona")
    private SexoPersona sexoPersona;
    
    @ManyToOne
    @JoinColumn(name="tipo_id_persona_fk", referencedColumnName="id_tipo_identidad_persona")
    private TipoIdentidadPersona tipoIdPersona;
    
    
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellido1Persona() {
        return apellido1Persona;
    }

    public void setApellido1Persona(String apellido1Persona) {
        this.apellido1Persona = apellido1Persona;
    }

    public String getApellido2Persona() {
        return apellido2Persona;
    }

    public void setApellido2Persona(String apellido2Persona) {
        this.apellido2Persona = apellido2Persona;
    }

    public String getNumeroIdPersona() {
        return NumeroIdPersona;
    }

    public void setNumeroIdPersona(String NumeroIdPersona) {
        this.NumeroIdPersona = NumeroIdPersona;
    }

    public Integer getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(Integer edadPersona) {
        this.edadPersona = edadPersona;
    }

    public String getCargoPersona() {
        return cargoPersona;
    }

    public void setCargoPersona(String cargoPersona) {
        this.cargoPersona = cargoPersona;
    }

    public SexoPersona getSexoPersona() {
        return sexoPersona;
    }

    public void setSexoPersona(SexoPersona sexoPersona) {
        this.sexoPersona = sexoPersona;
    }

    public TipoIdentidadPersona getTipoIdPersona() {
        return tipoIdPersona;
    }

    public void setTipoIdPersona(TipoIdentidadPersona tipoIdPersona) {
        this.tipoIdPersona = tipoIdPersona;
    }
    
}
