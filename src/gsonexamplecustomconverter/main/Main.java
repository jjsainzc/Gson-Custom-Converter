/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsonexamplecustomconverter.main;

import gsonexamplecustomconverter.datos.Persona;
import gsonexamplecustomconverter.datos.Hijo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import gsonexamplecustomconverter.conversores.GsonPersonaConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alienware
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Persona persona;
            List<Persona> lista;
            List<Hijo> hijos;
            

            // Fuente lista
            lista = new ArrayList();
            
            persona = new Persona("Pepe", new SimpleDateFormat("dd/MM/yyyy").parse("12/01/1980"), 123.5);
            hijos = new ArrayList();
            hijos.add(new Hijo("Hijo 1", new SimpleDateFormat("dd/MM/yyyy").parse("25/12/2000")));
            persona.setHijos(hijos);
            lista.add(persona);
            
            persona = new Persona("Juan", new SimpleDateFormat("dd/MM/yyyy").parse("12/01/1960"), 123.5);
            hijos = new ArrayList();
            hijos.add(new Hijo("Hijo 1", new SimpleDateFormat("dd/MM/yyyy").parse("5/3/1998")));
            hijos.add(new Hijo("Hijo 2", new SimpleDateFormat("dd/MM/yyyy").parse("25/2/2000")));
            persona.setHijos(hijos);
            lista.add(persona);

            // Se convierte a arreglo
            Persona[] personas = lista.toArray(new Persona[lista.size()]);

            // Inicializamos el Gson con el conversor
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Persona.class, new GsonPersonaConverter())
                    .create();

            // Pasamos el arreglo
            String json = gson.toJson(personas, Persona[].class);
            System.out.println(json);

            // Usando un TypeToken para convertir a la lista directamente
            lista = gson.fromJson(json, new TypeToken<List<Persona>>(){}.getType()  );
            for (Persona p : lista) {
                System.out.println(p.toString());
            }

        } catch (ParseException ex) {
        }
    }

}
