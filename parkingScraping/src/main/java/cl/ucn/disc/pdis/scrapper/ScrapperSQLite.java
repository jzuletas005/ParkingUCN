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
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.sql.SQLException;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import javax.swing.text.html.parser.Parser;

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

        //  CSV file path
        File csvFile = new File("./datosconrut.csv");

        //  Check if CSV file exists
        if (!csvFile.isFile()){
            log.debug("Error locating CSV file");
        } else {
            try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

                //  Create data table
                TableUtils.createTableIfNotExists(connectionSource, Person.class);

                //  Create Dao
                Dao<Person, Long> personDao = DaoManager.createDao(connectionSource, Person.class);

                //  Assign CSV file to reader object and extract the records.
                Reader reader = Files.newBufferedReader(Paths.get(csvFile.getPath()));
                Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);

                //  Insert person information into the database.
                for (CSVRecord record : records){

                    //We collect the .csv data
                    String aux = record.get(0);
                    String name = record.get(1);
                    String rut = record.get(2);
                    String wposition = record.get(3);
                    String unit = record.get(4);
                    String email = record.get(5);
                    String phone = record.get(6);
                    String office = record.get(7);
                    String address = record.get(8);

                    int id = Integer.parseInt(aux);


                    personDao.create(new Person(id, name, rut, wposition, unit, email,
                            phone, office, address));
                }
                log.debug("Done ....");
            }catch (SQLException e){
                log.error("Error: ", e);
            }
        }
    }
}
