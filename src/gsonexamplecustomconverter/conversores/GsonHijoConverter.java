/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsonexamplecustomconverter.conversores;

import gsonexamplecustomconverter.utilidades.DateNumberFormatDetector;
import gsonexamplecustomconverter.datos.Hijo;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.ParseException;

/**
 *
 * @author ALIENWARE
 */
public class GsonHijoConverter implements JsonSerializer<Hijo>, JsonDeserializer<Hijo>{

    @Override
    public JsonElement serialize(Hijo hijo, Type type, JsonSerializationContext jsc) {
        JsonObject json = new JsonObject();
        json.addProperty("nombre", hijo.getNombre());
        json.addProperty("fecha", DateNumberFormatDetector.getSimpleDateFormat().format(hijo.getFecha()));

        return json;
    }

    @Override
    public Hijo deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Hijo hijo = new Hijo();
        JsonObject json = (JsonObject) je;

        hijo.setNombre(json.get("nombre").getAsString());
        try {
            hijo.setFecha(DateNumberFormatDetector.getSimpleDateFormat().parse(json.get("fecha").getAsString()));
        } catch (ParseException ex) {
        }

        return hijo;
    }
    
}
