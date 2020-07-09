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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.sql.SQLException;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScrapperRut {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ScrapperRut.class);

    /**
     * Main
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //Random number creator for delay use in queries
        Random random = new Random();

        //  CSV file path
        File csvFile = new File("./datos.csv");

        // CSV new file path
        FileWriter fw = new FileWriter("./datosconrut.csv");

        //  Check if CSV file exists
        if (!csvFile.isFile()) {
            log.debug("Error locating CSV file");
        } else {

            try {

                //  Assign CSV file to reader object and extract the records.
                Reader reader = Files.newBufferedReader(Paths.get(csvFile.getPath()));
                Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);

                //  Insert person information into the database.
                for (CSVRecord record : records) {

                    //We collect the .csv data
                    String id = record.get(0);
                    String name = record.get(1);
                    String wposition = record.get(2);
                    String unit = record.get(3);
                    String email = record.get(4);
                    String phone = record.get(5);
                    String office = record.get(6);
                    String address = record.get(7);
                    String country = record.get(8);

                    //The name is separated into an array to proceed to find your rut
                    String[] word = name.split(" ");

                    //The country is separated into an array to proceed to find your rut
                    String[] littleword = country.split(" ");
                    //call the method that the rut finds
                    String rut = Rutificador(word, littleword);

                    //And we add the desired elements to our .csv
                    fw.append(id + ",");
                    fw.append(name + ",");
                    fw.append(rut + ",");
                    fw.append(wposition + ",");
                    fw.append(unit + ",");
                    fw.append(email+ ",");
                    fw.append(phone + ",");
                    fw.append(office + ",");
                    fw.append(address + ",");
                    fw.append(country);
                    fw.append("\n");
                    fw.flush();

                    log.debug("Persona encontrada:" +id);
                }
            } catch (Exception e) {
                log.error("Error: ", e);
            }
        }
        log.debug("Done!");
    }

    /**
     * Rut Extractor
     * @param word
     * @param littleword
     * @return
     */
    private static String Rutificador(String[] word, String[] littleword) {

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
        String country = littleword[0];

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
               Element Region = cols.get(4);

               //TODO in spanish, revisar el codigo de la linea 182 dado que es posible optimizarlo en esta clase
               // o incorporarlo en la clase ScrapperData

                //And then conditions are created that allow selecting the rut of the corresponding person

               //if you are the only person in the results table, it is understood that you are the person to find
               if(rows.size() == 1){

                   return Rut.text(); //return the rut

               }else {

                   for (String reg : region) { //For Each of region[]

                       //the first filter is by name,
                       // since the rut finder has the name of the person with a different orientation the variable
                       // "reversename" is called to be compared
                       if(Nombre.text().compareToIgnoreCase(reveresName) == 0){

                           //In this step compare if the address or region that provides the page is equal to that
                           // of the person to find, returns your rut
                           if (Region.text().compareToIgnoreCase(reg) == 0) {

                               return Rut.text();
                           }else {

                               //If you did not find the previous form, the comparison is made with the address provided by the university
                               if(Region.text().compareToIgnoreCase(country) == 0){

                                   return Rut.text();
                               }
                           }
                       }else {

                           //In this step compare if the address or region that provides the page is equal to that
                           // of the person to find, returns your rut
                           if (Region.text().compareToIgnoreCase(reg) == 0) {

                               return Rut.text();
                           }else {

                               //If you did not find the previous form, the comparison is made with the address provided by the university
                               if(Region.text().compareToIgnoreCase(country) == 0){

                                   return Rut.text();
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
       }

        return null;
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


