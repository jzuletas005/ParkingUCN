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
["java:package:cl.ucn.disc.pdis.scrapper.zeroice", "cs:namespace:ServerParkingUCN.ZeroIce"]
module model 
{

    /**
    *The Sexo
    */
        enum Sexo {
        MASCULINO,
        FEMENINO,
	    INDETERMINADO
        }
     /**
     *The Tipo Logo
     */
        enum TipoLogo{
             ROJO,
             AZUL
         }

    /**
    * Clase persona
    */
        ["cs:property"]
        class Persona{

            /**
            * PK
            */
            int uid;

            /**
            * Nombre;
            */
            string nombre;

            /**
            *Rut: 18124996K
            */  
            string rut;

            /**
            * wPosition;
            */
            string wposition; // corresponde al cargo del trabajor

            /**
            * Unidad;
            */
            string unit; //En qué unidad trabaja.

            /**
            * Direccion
            */
            string direccion;

            /**
            *Sexo
            */
            Sexo sexo; 

            /**
            * Telefono
            */
            string telefono;

	    /**
	    * Oficina
	    */
            string oficina;

            /**
            *Correo Electronico
            */
            string email;

             /**
            *País
            */
            string country;
        }


     /**
     *Clase vehiculo
     */
      ["cs:property"]
      class Vehiculo {

        /**
        * PrimaryKey
        * Patente;
        */
        string patente;

        /**
        * Marca del vehiculo.
        */
        string marca;

        /**
        * Modelo del vehículo.
        */
        string modelo;

         /**
         * Año
         */
         int anio;

         /**
         *Observación
         */
         string observacion;

         /**
         * Responsable (asociado con la entidad persona)
         */
         string responsable;
        }

     /**
     * Clase circulacion
     */
     ["cs:property"]
     class Circulacion {

         /**
         *PrimaryKey
         */
         int uid;

         /**
          *Fecha de Ingreso del vehiculo
          *Format: ISO_ZONED_DATE_TIME
          */
           string fechaIngreso;

         /**
         * Fecha de salida del vehiculo
         *Format: ISO_ZONED_DATE_TIME
         */
         string fechaSalida;

         /**
         *Patente
         */
         string patente;

         /**
         *Entrada
         */
         string puertaEntrada;

         /**
          *Salida
          */
          string puertaSalida;

     }

      /**
      * Clase identificacion
      */
      ["cs:property"]
      class Identificacion{

          /**
          *Codigo del Logo
          *PrimaryKey
          */
          string codigoLogo;

          /**
          *Patente
          */
          string patente;

          /**
          *Rut de persona
          */
          string rut;

          /**
          *Tipo Logo
          */
          TipoLogo tipoLogo;
      }

        /**
         * The Contratos
         */
        interface Contratos{

         /**
         * register a Persona with a persona instance.
         * 
         * @param persona to create
         * @return Persona created
         */
        Persona registrarPersona(Persona persona);

		/**
         * Register a Vehiculo with a vehiculo instance.
         *
         * @param vehiculo to create
         * @return Vehiculo created
         */
        Vehiculo registrarVehiculo(Vehiculo vehiculo);
      
        /**
         * Search a Persona with a rut.
         *
         * @param rut de la persona a buscar.
         * @return Persona buscado.
         */
        Persona obtenerPersona(string rut);

		/**
         * Search a Vehiculo with a patente.
		 *
         * @param patente del vehiculo a buscar.
         * @return Vehiculo buscado.
         */
        Vehiculo obtenerVehiculo(string patente);

        /**
        * Erase a persona with a rut
        *
        * @param rut del vehiculo a buscar.
        * @return persona buscada a eliminar.
        */
        Persona eliminarPersona(string rut);
        

        /**
         *Search a Vehiculo with a patente.
         *
         * @param patente del vehiculo a buscar.
        * @return Vehiculo eliminar.
        */
        Vehiculo eliminarVehiculo(string patente);


        /**
         *editing a persona with a persona instance.
        *
        * @param persona to editing
        * @return persona edited.
        */
        Persona editarPersona(Persona persona);

        /**
        *
        *editing a vehiculo
        *@param vehiculo to editing
        *@return vehiculo edited.
        */
        Vehiculo editarVehiculo(Vehiculo vehiculo);

        /**
        *Enter vehicle
        *
        *@param codigo del logo
        *@return Circulacion del vehiculo
        */
        //Circulacion ingresoVehiculo(string codigoLogo);

        /**
        *Exit vehicle
        *
        *@param codigo del logo
        *@return Circulacion del vehiculo
        */
        //Circulacion salidaVehiculo(string codigoLogo);

        /**
        *Verify logo of an existing patent
        *
        *@param codigo logo
        *@param patente del vehiculo
        *@return true or false
        */
        //bool verificarPatenteLogo(string patente, string codigoLogo);

        /**
        *Created Official ID
        *
        *@param identificacion
        *@return Identificacion del vehiculo en Universidad
        */
        //Identificacion registrarOID(Identificacion oid);

        /**
        *Search Official ID
        *
        *@param identificacion
        *@return Identificacion del vehiculo en Universidad
        */
        //Identificacion obtenerOID(Identificacion oid);

        /**
        *Updated Official ID
        *
        *@param identificacion
        *@return Identificacion del vehiculo en Universidad
        */
        //Identificacion editarOID(Identificacion oid);

        /**
        *Deleted Official ID
        *
        *@param identificacion
        *@return Identificacion del vehiculo en Universidad
        */
        //Identificacion eliminarOID(Identificacion oid);
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
