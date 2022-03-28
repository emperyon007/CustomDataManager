package CustomDataManager;

import java.io.*;
import java.util.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class JSONReader
{
    public static final String JSON_FILE="libri.json";

    /**
     * Method: main
     * Read json file, parse it and print elements
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList <Libro> libri = new ArrayList <Libro>();

        InputStream input = new FileInputStream(JSON_FILE);

        JsonReader jsonReader = Json.createReader(input);

        JsonObject jsonObject = jsonReader.readObject();

        jsonReader.close();

        JsonObject innerJsonObject = jsonObject.getJsonObject("libreria");

        JsonArray jsonArray = innerJsonObject.getJsonArray("libri");


        int index = 0;

        for (JsonValue element : jsonArray) {
            Libro libro = new Libro();

            libro.setGenere(element.asJsonObject().getString("genere"));
            libro.setTitolo(element.asJsonObject().getString("titolo"));
            libro.setAutore(element.asJsonObject().getString("autore"));
            libro.setPrezzo((float) element.asJsonObject().getJsonNumber("prezzo").doubleValue());

            libri.add(libro);
        }

        for (Libro l : libri){
            System.out.println(l);
        }

    }

}
