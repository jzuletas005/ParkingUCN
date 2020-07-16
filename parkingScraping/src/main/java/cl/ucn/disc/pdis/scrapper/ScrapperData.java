/*
 * MIT License
 *
 * Copyright (c) 2020 Javier Zuleta Silva, Beatriz Alvarez Rojas, Gonzalo Nieto Berrios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cl.ucn.disc.pdis.scrapper;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;

import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrapperData {

    /**
     * Logger
     *
     */
    private static final Logger log = LoggerFactory.getLogger(ScrapperData.class);

    /**
     * Main
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //Random number creator for delay use in queries
        Random random = new Random();

        //Academic Id's
        //where "ini" is first on the list and "end" is last on the list
        int ini = 0;
        int end = 30000;
        int counter = 0;

        //Creator of the .csv file
        CSVWriter writer = new CSVWriter(new FileWriter("./datos.csv"),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

        for (int i = ini; i <= end; i++) {
            try {
                //  The Jsoup is used to make a connection to the URL and obtain the JSON from the web page
                Document doc = Jsoup.connect("http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod=" + i)
                        .get();
                log.debug("Page number: " + i);
                Element lblNombre = doc.getElementById("lblNombre");

                //Then we validate that the data is not null or empty
                if (!lblNombre.text().isEmpty()) {
                    counter++;

                    Element lblCargo = doc.getElementById("lblCargo");
                    Element lblUnidad = doc.getElementById("lblUnidad");
                    Element lblEmail = doc.getElementById("lblEmail");
                    Element lblTelefono = doc.getElementById("lblTelefono");
                    Element lblOficina = doc.getElementById("lblOficina");
                    Element lblDireccion = doc.getElementById("lblDireccion");

                    //And we add the desired elements to our .csv

                    //The name is separated into an array to proceed to find your rut
                    String[] word = lblNombre.text().split(" ");

                    //The country is separated into an array to proceed to find your rut
                    String[] littleword = lblDireccion.text().split(" ");

                    //call the method that the rut and sex finds
                    String auxiliar[] = Rutificador(word, littleword);

                    //initialized sex variable
                    String sex="";

                    if(auxiliar[1].equalsIgnoreCase("VAR")){
                        sex="MASCULINO";

                    }else{
                        if(auxiliar[1].equalsIgnoreCase("MUJ")){ sex="FEMENINO"; }
                    }

                    //And we add the desired elements to our .csv
                    writer.writeNext(new String[]{
                            String.valueOf(i), lblNombre.text(), auxiliar[0], sex, lblCargo.text(), lblUnidad.text(),
                            lblEmail.text(), lblTelefono.text(), lblOficina.text(), lblDireccion.text()
                    });


                    log.debug("Person data " + counter + " added!");
                }

            } catch (IOException h) {
                i = i - 1;
                log.debug("Error: " + h);
                log.debug("Connecting again...");
                try {
                    Thread.sleep(7500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        writer.close();
        log.debug("Done!");
    }

    /**
     * Rut Extractor
     * @param word
     * @param littleword
     * @return
     */
    private static String[] Rutificador(String[] word, String[] littleword) {

        //I modify the name so that it matches the search variable of the web page
        String wordFinal = String.join("+", word);

        //Full name rearranged
        String reveresName = ReverseWord(word);

        //list of the regions covered by the university and then find the people from the university
        String[] region = {
                "Antofagasta",
                "Antofagasta Norte",
                "Antofagasta Sur",
                "Calama",
                "Iquique",
                "Arica",
                "San Pedro de Atacama",
                "La Serena",
                "Coquimbo",
                "Parque Almagro",
                "Cañete",
                "Las Americas",
                "Providencia"
        };

        //select the region
        String country;
        if(littleword.length == 1){
            country = littleword[0];
        }else {
            country = littleword[littleword.length - 3];
        }
        // Array Auxiliar
        String aux[];

        try{

            //The Jsoup is used to make a connection to the URL and obtain the JSON from the web page
            Document doc = Jsoup.connect("https://www.nombrerutyfirma.com/buscar?term=" + wordFinal).get();
            Element tbody = doc.select("table").select("tbody").get(0);
            Elements rows = tbody.select("tr");

            //Read the HTML and create a for loop that loops through the HTML tables
            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                //The fields necessary to find the person's rut ​​are captured.
                Element Nombre = cols.get(0);
                Element Rut = cols.get(1);
                Element Sex = cols.get(2);
                Element Region = cols.get(4);

                //TODO in spanish, revisar el codigo de la linea 182 dado que es posible optimizarlo en esta clase
                // o incorporarlo en la clase ScrapperData

                //And then conditions are created that allow selecting the rut of the corresponding person

                //if you are the only person in the results table, it is understood that you are the person to find
                if(rows.size() == 1){

                    return aux= new String[]{Rut.text(), Sex.text()};

                }else {

                    for (String reg : region) { //For Each of region[]

                        //the first filter is by name,
                        // since the rut finder has the name of the person with a different orientation the variable
                        // "reversename" is called to be compared
                        if(Nombre.text().compareToIgnoreCase(reveresName) == 0){

                            //In this step compare if the address or region that provides the page is equal to that
                            // of the person to find, returns your rut
                            if (Region.text().compareToIgnoreCase(reg) == 0) {

                                return aux= new String[]{Rut.text(), Sex.text()};
                            }else {

                                //If you did not find the previous form, the comparison is made with the address provided by the university
                                if(Region.text().compareToIgnoreCase(country) == 0){

                                    return aux= new String[]{Rut.text(), Sex.text()};
                                }
                            }
                        }else {

                            //In this step compare if the address or region that provides the page is equal to that
                            // of the person to find, returns your rut
                            if (Region.text().compareToIgnoreCase(reg) == 0) {

                                return aux= new String[]{Rut.text(), Sex.text()};

                            }else {

                                //If you did not find the previous form, the comparison is made with the address provided by the university
                                if(Region.text().compareToIgnoreCase(country) == 0){

                                    return aux= new String[]{Rut.text(), Sex.text()};
                                }
                            }
                        }
                    }
                }
            }

        }catch (IOException h){
            log.debug("Error: " + h);
            log.debug("Connecting again...");
            try {
                Thread.sleep(7500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return aux= new String[]{"",""};
        }

        return aux= new String[]{"",""};
    }

    /**
     * Name Backwards
     * @param word
     * @return
     */
    private static String ReverseWord(String[] word) {

        //index to take last names
        int limit = word.length - 2;

        //variable to store last names
        String LastName = String.join(" ", word[limit], word[limit + 1]);

        //initialization of the person's names
        String FirstName = "";

        //loop to store names
        for (int x = 0; x < limit; x++) {
            FirstName = FirstName + " " + word[x];
        }

        //concatenation auxiliary variable
        String aux = LastName + FirstName;

        //return last names first and then first names
        return aux;
    }

}