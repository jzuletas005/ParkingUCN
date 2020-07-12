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

import java.util.ArrayList;
import java.io.*;
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

public class ScrapperSQLite {

    private static final Logger log = LoggerFactory.getLogger(ScrapperSQLite.class);

    public static void main (String[] args) throws IOException {

        //  Create data base
        String databaseUrl = "jdbc:sqlite:personasucn.db";

        //  CSV file path
        FileReader csvFile = new FileReader("./datos.csv");

        //  Check if CSV file exists
        if (csvFile == null){
            log.debug("Error locating CSV file");
        } else {
            try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

                //  Create data table
                TableUtils.createTableIfNotExists(connectionSource, Person.class);

                //  Create Dao
                Dao<Person, Long> personDao = DaoManager.createDao(connectionSource, Person.class);

                //  Buffer that read CSV file
                BufferedReader br = new BufferedReader(csvFile);

                //  String helper for read CSV line by line
                String line;
                while ((line = br.readLine()) != null){

                    //  Vector string for every data in a line
                    String[] data = new String[8];
                    int dataNumber = 0;

                    //  String builder that record char by char
                    StringBuilder charCollector = new StringBuilder();

                    //  Identifying every char in line
                    for (int i = 0; i < line.length(); i++){

                        //  If it's the last data, it read until the last char and save it
                        if (dataNumber == 7){
                            charCollector.append(line.charAt(i));
                            if (i == line.length() - 1){
                                data[dataNumber] = charCollector.toString();
                            }
                        }else{

                            //  If a comma exists in the current char it saves the previous data.
                            //  and also it checks if it is not de last char of the line to prevent
                            //  possible errors
                            if (String.valueOf(line.charAt(i)).equals(",") &&
                                    i != line.length() - 1){

                                //  It checks if the next char is not a whitespace,
                                //  to ensure that is an end of the current data
                                if (!String.valueOf(line.charAt(i + 1)).isBlank()){

                                    //  It checks if a data is empty to prevent record a whitespace
                                    if (charCollector.length() != 0){
                                        data[dataNumber] = charCollector.toString();

                                        //  if a data is complete, then it clears the string builder
                                        charCollector.delete(0, charCollector.length());
                                    }else{
                                        charCollector.delete(0, charCollector.length());
                                    }
                                    dataNumber = dataNumber + 1;
                                }
                            }else{
                                charCollector.append(line.charAt(i));
                            }
                        }
                    }
                    log.debug("Person: "+Integer.parseInt(data[0])+", "+data[1]+", "+data[2]+", "+data[3]
                            +", "+data[4]+", "+data[5]+", "+data[6]+", "+data[7]);

                    //  Add new person to database
                    personDao.createIfNotExists(new Person(Integer.parseInt(data[0]), data[1], data[2], data[3],
                            data[4], data[5], data[6], data[7]));
                    log.debug("Person added !");
                }
                log.debug("Persons Table created !");
            }catch (SQLException e){
                log.error("Error: ", e);
            }
        }
    }
}
