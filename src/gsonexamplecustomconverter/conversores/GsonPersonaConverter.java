package gsonexamplecustomconverter.conversores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gsonexamplecustomconverter.utilidades.DateNumberFormatDetector;
import gsonexamplecustomconverter.datos.Persona;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import gsonexamplecustomconverter.datos.Hijo;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Alienware
 */
public class GsonPersonaConverter implements JsonSerializer<Persona>, JsonDeserializer<Persona> {
    private final Gson gson;

    public GsonPersonaConverter() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Hijo.class, new GsonHijoConverter())
                .create();
    }

    @Override
    public JsonElement serialize(Persona persona, Type type, JsonSerializationContext context) {

        JsonObject json = new JsonObject();
        json.addProperty("nombre", persona.getNombre());
        json.addProperty("fechaNac", DateNumberFormatDetector.getSimpleDateFormat().format(persona.getFechaNac()));
        json.addProperty("salario", DateNumberFormatDetector.getDecimalFormat().format(persona.getSalario()));
        json.add("hijos", gson.toJsonTree(persona.getHijos()));

        return json;
    }

    @Override
    public Persona deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Persona persona = new Persona();
        JsonObject json = (JsonObject) je;

        List<Hijo> hijos = gson.fromJson(json.getAsJsonArray("hijos"), new TypeToken<List<Hijo>>(){}.getType());

        persona.setNombre(json.get("nombre").getAsString());
        try {
            persona.setFechaNac(DateNumberFormatDetector.getSimpleDateFormat().parse(json.get("fechaNac").getAsString()));
            persona.setSalario(DateNumberFormatDetector.getDecimalFormat().parse(json.get("salario").getAsString()).doubleValue());
            persona.setHijos(hijos);
        } catch (ParseException ex) {
        }

        return persona;
    }

}
