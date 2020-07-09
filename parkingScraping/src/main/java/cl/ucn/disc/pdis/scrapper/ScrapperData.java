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
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrapperData {

    /**
     * Main
     * @param args
     * @throws IOException
     */

    private static final Logger log = LoggerFactory.getLogger(ScrapperData.class);

    public static void main (String[] args) throws IOException{

        //Random number creator for delay use in queries
        Random random = new Random();

        //Academic Id's
        //where "ini" is first on the list and "end" is last on the list
        int ini = 0;
        int end = 30000;
        int counter = 0;

        //Creator of the .csv file
        FileWriter fw = new FileWriter("./datos.csv");

        for (int i = ini; i <= end; i++){
            try {
                //  The Jsoup is used to make a connection to the URL and obtain the JSON from the web page
                Document doc = Jsoup.connect("http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod="+i)
                        .get();
                log.debug("Page number: " + i);
                Element lblNombre =  doc.getElementById("lblNombre");

                //Then we validate that the data is not null or empty
                if (!lblNombre.text().isEmpty()) {
                    counter++;

                    Element lblCargo =  doc.getElementById("lblCargo");
                    Element lblUnidad =  doc.getElementById("lblUnidad");
                    Element lblEmail =  doc.getElementById("lblEmail");
                    Element lblTelefono =  doc.getElementById("lblTelefono");
                    Element lblOficina =  doc.getElementById("lblOficina");
                    Element lblDireccion =  doc.getElementById("lblDireccion");

                    //And we add the desired elements to our .csv
                    fw.append(String.valueOf(i) + ",");
                    fw.append(lblNombre.text() + ",");
                    fw.append(lblCargo.text() + ",");
                    fw.append(lblUnidad.text() + ",");
                    fw.append(lblEmail.text() + ",");
                    fw.append(lblTelefono.text() + ",");
                    fw.append(lblOficina.text() + ",");
                    fw.append(lblDireccion.text());
                    fw.append("\n");
                    fw.flush();

                    log.debug("Person data " + counter + " added!");
                }

                //A deleay is created in order not to overload the server
                try {
                    Thread.sleep(1000+random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (IOException h){
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
        fw.close();
        log.debug("Done!");
    }
}
