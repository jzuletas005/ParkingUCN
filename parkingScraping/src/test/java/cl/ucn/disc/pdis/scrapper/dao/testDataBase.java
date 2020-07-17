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

package cl.ucn.disc.pdis.scrapper.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import cl.ucn.disc.pdis.scrapper.Person;
import cl.ucn.disc.pdis.scrapper.ScrapperSQLite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import cl.ucn.disc.pdis.scrapper.Person;

public final class testDataBase {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(testDataBase.class);

    /**
     * Testing
     */
    @Test
    public void testDB (String[] args) throws SQLException  {

        // The database to use (in RAM memory)
        String databaseUrl = "jdbc:h2:mem:testPersona";

            try (ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl)){

                //  Create data table
                TableUtils.createTableIfNotExists(connectionSource, Person.class);

                //  Create Dao
                Dao<Person, Long> personDao = DaoManager.createDao(connectionSource, Person.class);

                //  Add new person to database
                Person p = new Person(1, "Juan Perez Gonzalez","123456789", "FEMENINO",
                            "DIRECTOR SUPREMO", "Departamento del Dominio Global", "iamtheboss@yahoo.com", "771153399", "Pab XYZ Ofi. 0", "Portal el Viento 86", "Antofagasta - Shile");

                personDao.create(p);

                log.debug("Person added !");

                log.debug("Persons Table created !");

            }catch (SQLException | IOException e){
                log.error("Error: ", e);
            }
        }

}
