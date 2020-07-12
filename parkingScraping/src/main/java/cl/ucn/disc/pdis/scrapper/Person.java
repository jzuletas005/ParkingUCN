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

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Persons")
public final class Person {

    @DatabaseField(id = true, canBeNull = false)
    private int code;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = true)
    private String wposition;

    @DatabaseField(canBeNull = true)
    private String unit;

    @DatabaseField(canBeNull = true)
    private String email;

    @DatabaseField(canBeNull = true)
    private String phone;

    @DatabaseField(canBeNull = true)
    private String office;

    @DatabaseField(canBeNull = true)
    private String address;

    /**
     * ORMLite Constructor
     */
    public Person() {
    }

    /**
     * Constructor
     *
     * @param code
     * @param name
     * @param wposition
     * @param unit
     * @param email
     * @param phone
     * @param office
     * @param address
     */
    public Person(int code, String name, String wposition, String unit, String email, String phone,
                  String office, String address) {
        this.code = code;
        this.name = name;
        this.wposition = wposition;
        this.unit = unit;
        this.email = email;
        this.phone = phone;
        this.office = office;
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getWposition() {
        return wposition;
    }

    public String getUnit() {
        return unit;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOffice() {
        return office;
    }

    public String getAddress() {
        return address;
    }

}
