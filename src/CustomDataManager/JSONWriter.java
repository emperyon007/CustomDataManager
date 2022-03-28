package CustomDataManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class JSONWriter
{
    public static final String JSON_FILE="libri.json";

    /**
     * Method: main
     * Creates some instances, encode in json and print to json file
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        ArrayList <Libro> libri = new ArrayList <Libro>();

        Libro libroL1 = new Libro();

        libroL1.setGenere("fantasy");
        libroL1.setTitolo("Lo Hobbit");
        libroL1.setAutore("J. R. R. Tolkien");
        libroL1.setPrezzo(9.9f);


        Libro libroL2 = new Libro();

        libroL2.setGenere("fantasy");
        libroL2.setTitolo("Il signore degli anelli");
        libroL2.setAutore("J. R. R. Tolkien");
        libroL2.setPrezzo(30.00f);

        libri.add(libroL1);
        libri.add(libroL2);

        JsonObjectBuilder rootObject = Json.createObjectBuilder();
        JsonObjectBuilder booksObject = Json.createObjectBuilder();
        JsonArrayBuilder bookArray = Json.createArrayBuilder();

        for (Libro libro : libri){
            JsonObjectBuilder bookObject =Json.createObjectBuilder();
            bookObject.add("genere", libro.getGenere());
            bookObject.add("titolo", libro.getTitolo());
            bookObject.add("autore", libro.getAutore());
            bookObject.add("prezzo", libro.getPrezzo());
            bookArray.add(bookObject.build());
        }

        booksObject.add("libri", bookArray.build());
        rootObject.add("libreria", booksObject.build());

        OutputStream output = new FileOutputStream(JSON_FILE);

        JsonWriter jsonWriter = Json.createWriter(output);

        jsonWriter.writeObject(rootObject.build());

        jsonWriter.close();

        output.close();

    }
}
