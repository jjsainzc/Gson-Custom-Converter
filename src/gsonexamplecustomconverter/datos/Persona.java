package gsonexamplecustomconverter.datos;

import com.google.gson.GsonBuilder;
import gsonexamplecustomconverter.conversores.GsonPersonaConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Persona implements Serializable{

    private static final long serialVersionUID = -672683720769627236L;
    
    private String nombre;
    private Date fechaNac;
    private Double salario;
    private List<Hijo> hijos;

    public Persona() {
    }

    public Persona(String nombre, Date fechaNac, Double salario) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public List<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Hijo> hijos) {
        this.hijos = hijos;
    }
    
    

    @Override
    public String toString() {
        return new GsonBuilder()
                .registerTypeAdapter(Persona.class, new GsonPersonaConverter())
                .create()
                .toJson(this, Persona.class);

    }

}
