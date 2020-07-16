/*
 * MIT License
 *
 *  Copyright (c) 2020 Javier Zuleta Silva, Beatriz Alvarez Rojas, Gonzalo Nieto Berrios
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

// https://doc.zeroc.com/ice/3.7/language-mappings/java-mapping/client-side-slice-to-java-mapping/customizing-the-java-mapping
["java:package:cl.ucn.disc.pdis.scrapper.zeroice"]
module model {

    /**
    * Clase persona
    */

        class Persona{

            /**
            * PK
            */
            int id;

            /**
            * Nombre;
            */
            string nombre;	

	   /    **
            * Apellido;
            */
            string apellido;

            /**
            *Rut: 18124996K
            */
            string rut;

            /**
            * wPosition;
            */
            string wposition;

            /**
            * Unidad;
            */
            string unit;

            /**
            * Direccion
            */
            string direccion;

 	        /**
            * Tipo
            */
            string tipo;

            /**
            * Telefono
            */
            long telefono;

            /**
            *Correo Electronico
            */
            string email;

        }

            /**
            *The Sexo
            */

            enum Sexo {
                 MASCULINO,
                 FEMENINO
            }


     /**
     *Clase vehiculo
     */
        class Vehiculo {

                /**
                *PrimaryKey
                */
                int id;

                /**
                * Patente;
                **/
                string patente;

                /**
                 * Marca del vehiculo.
                 */
                 string marca;

                 /**
                 * Modelo del vehículo.
                 *
                 */
                  string modelo;

                  /**
                  * Año
                  */
                  int año;

                   /**
                   *Observación
                   */
                   string observación;

                   /**
                   * Responsable (asociado con la entidad persona)
                   */
                    Persona responsable;
                }

    /**
     * The base system.
     */
     interface TheSystem {

        /**
         * @return the diference in time between client and server.
         */
        long getDelay(long clientTime);

     }

}

