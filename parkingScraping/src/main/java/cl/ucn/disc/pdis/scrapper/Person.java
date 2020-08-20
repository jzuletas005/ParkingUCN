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

@DatabaseTable(tableName = "Personas")
public final class Person {

    @DatabaseField(canBeNull = true)
    private int uid;

    @DatabaseField(canBeNull = false)
    private String nombre;

    @DatabaseField(id = true, canBeNull = true)
    private String rut;

    @DatabaseField(canBeNull = true)
    private String sexo;

    @DatabaseField(canBeNull = true)
    private String wposition;

    @DatabaseField(canBeNull = true)
    private String unit;

    @DatabaseField(canBeNull = true)
    private String email;

    @DatabaseField(canBeNull = true)
    private String telefono;

    @DatabaseField(canBeNull = true)
    private String oficina;

    @DatabaseField(canBeNull = true)
    private String direccion;

    @DatabaseField(canBeNull = true)
    private String country;


    /**
     * ORMLite Constructor
     */
    public Person() {
    }

    /**
     * Constructor
     *
     * @param uid
     * @param nombre
     * @param rut
     * @param sexo
     * @param wposition
     * @param unit
     * @param email
     * @param telefono
     * @param oficina
     * @param direccion
     * @param country
     */
    public Person(int uid, String nombre,String rut, String sexo, String wposition, String unit, String email, String telefono,
                  String oficina, String direccion, String country) {
        this.uid = uid;
        this.nombre = nombre;
        this.rut = rut;
        this.sexo = sexo;
        this.wposition = wposition;
        this.unit = unit;
        this.email = email;
        this.telefono = telefono;
        this.oficina = oficina;
        this.direccion = direccion;
        this.country = country;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return nombre;
    }

    public String getRut() { return rut; }

    public String getSex() { return sexo; }

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
        return telefono;
    }

    public String getOffice() {
        return oficina;
    }

    public String getAddress() {
        return direccion;
    }

    public String getCountry() {
        return country;
    }
}
