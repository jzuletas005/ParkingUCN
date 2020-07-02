/*
 * Copyright (c) 2020 .
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package cl.ucn.disc.pdis.scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;

public class ScrapperData {

    /**
     * Main
     * @param args
     * @throws IOException
     */
    public static void main (String[] args) throws IOException{

        /**
         * Random number creator for delay use in queries
         */
        Random random = new Random();

        /**
         * Academic Id's
         * where "ini" is first on the list and "end" is last on the list
         */
        int ini = 0;
        int end = 29730;

        /**
         * Creator of the .csv file
         */
        FileWriter fw = new FileWriter("./src/main/resources/datos.csv");

        for(int i = ini; i <= end; i++){

            //The Jsoup is used to make a connection to the URL and obtain the JSON from the web page
            Document doc = Jsoup.connect("http://online.ucn.cl/directoriotelefonicoemail/fichaGenerica/?cod="+i).get();

            //We name as elements the parameters that we want to search within the JSON to use them
            Element lblNombre =  doc.getElementById("lblNombre");
            Element lblCargo =  doc.getElementById("lblCargo");
            Element lblUnidad =  doc.getElementById("lblUnidad");
            Element lblEmail =  doc.getElementById("lblEmail");
            Element lblTelefono =  doc.getElementById("lblTelefono");
            Element lblOficina =  doc.getElementById("lblOficina");
            Element lblDireccion =  doc.getElementById("lblDireccion");

            //System.out.println(lblNombre.text());

            try{
                //Then we validate that the data is not null or empty
                if(!lblNombre.text().isEmpty()) {

                    //And we add the desired elements to our .csv
                    //fw.append(String.valueOf(i) + ",");
                    fw.append(lblNombre.text() + ",");
                    fw.append(lblCargo.text() + ",");
                    fw.append(lblUnidad.text() + ",");
                    fw.append(lblEmail.text() + ",");
                    fw.append(lblTelefono.text() + ",");
                    fw.append(lblOficina.text() + ",");
                    fw.append(lblDireccion.text());
                    fw.append("\n");
                }

                //A deleay is created in order not to overload the server
                Thread.sleep(1000+random.nextInt(1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //Finally we paste the data to the file and close it
        fw.flush();
        fw.close();
        System.out.println("Done....");
    }
}
