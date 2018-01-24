/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsonexamplecustomconverter.datos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ALIENWARE
 */
public class Hijo implements Serializable{

    private static final long serialVersionUID = 1L;
    
    
    private String nombre;
    private Date fecha;

    public Hijo() {
    }

    public Hijo(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
