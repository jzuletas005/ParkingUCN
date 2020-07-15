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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.opencsv.CSVReader;




public class ScrapperSQLite {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ScrapperSQLite.class);

    /**
     * Main
     * @param args
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {

        String databaseUrl = "jdbc:sqlite:personasucn.db";

        // Create an object of file reader class with CSV file as a parameter.
        FileReader filereader = new FileReader("./datos.csv");

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        //  CSV file path
        CSVReader reader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        //  Check if CSV file exists
        if (!reader.verifyReader()){
            log.debug("Error locating CSV file");
        } else {
            try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

                //  Create data table
                TableUtils.createTableIfNotExists(connectionSource, Person.class);

                //  Create Dao
                Dao<Person, Long> personDao = DaoManager.createDao(connectionSource, Person.class);

                String[] nextRecord;

                //  Assign CSV file to reader object and extract the records.
                while ((nextRecord = reader.readNext()) != null) {


                    //  Insert person information into the database.

                    //We collect the .csv data
                    String aux = nextRecord[0];
                    String name = nextRecord[1];
                    String rut = nextRecord[2];
                    String sex = nextRecord[3];
                    String wposition = nextRecord[4];
                    String unit = nextRecord[5];
                    String email = nextRecord[6];
                    String phone = nextRecord[7];
                    String office = nextRecord[8];
                    String address = nextRecord[9];
                    String country = "";

                    if(nextRecord.length == 10){
                        country="";
                    }else {
                        if(nextRecord.length == 11){
                            country = nextRecord[10];
                        }

                    }

                    int id = Integer.parseInt(aux);

                    personDao.create(new Person(id, name, rut, sex, wposition, unit, email, phone, office, address, country));
                    log.debug(aux);
                }
                log.debug("Done ....");
            }catch (SQLException | CsvValidationException e){

                log.error("Error: ", e);
            }
        }
    }
}
