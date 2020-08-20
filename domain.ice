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
            *
            */
            int uid;

            /**
            * Nombre;
            */
            string nombre;

            /**
            *PrimaryKey
            *Rut:11.111.111-1
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

             /**
             *Tipo Logo
            */
            TipoLogo tipoLogo;
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
        * codigo LogoUCN.
        */
        string codigoLogo;

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

        /**
        *Tipo de Logo
        *
        */
        TipoLogo tipoLogo;


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
        *Hora de ingreso del vehiculo
        */

        string horaIngreso;

         /**
         * Fecha de salida del vehiculo
         *Format: ISO_ZONED_DATE_TIME
         */
         string fechaSalida;

         /**
         * Hora de salida del vehiculo
         */
         string horaSalida;

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

        /**
        *Observaciones
        */
          
        string observacion;


         /**
        *Estado del Vehiculo
        */

        int estadoVehiculo;

     }

        exception DBnoEncontrada { 

            string mensaje = "No se encuentra en la base de datos"; 
        }

        exception ErrorDeServidor {

            string mensaje = "No se puede conectar con el servidor"; 
        
        }

        /**
         * The Contratos
         */
        interface Contratos{

        /**
        *Enter vehicle
        *
        *@param codigo del logo
        *@return Circulacion del vehiculo
        */
        Circulacion ingresoVehiculo(string patente, string puertaEntrada, string observacion);

        /**
        *Exit vehicle
        *
        *@param codigo del logo
        *@return Circulacion del vehiculo
        */
        Circulacion salidaVehiculo(string patente, string puertaSalida);

         /**
        *Exit vehicle
         *
         *@param Fecha de Busqueda
         *@return busqueda del vehiculo
        */

        Circulacion busquedaVehiculo(string fechadeBusqueda);

        /**
        *
        *@param Estado del Vehiculo
        *@return interior del vehiculo 
        */
              
        int vehiculosInterior(int estadoVehiculo, string fecha);

        /**
        *
        *@param Estado del vehiculo
        *@return
        */

        int vehiculosGatePrincipal(int estadoVehiculo, string fecha);

        /**
        *@param
        *@return
        */

        int vehiculosGateSur(int estadoVehiculo, string fecha);

        /**
        *@param
        *@return
        */
        int vehiculosGateAngamos(int estadoVehiculo, string fecha);

        /**
        *@param
        *@return
        */

        int totalRegion(string region);

        /**
        *@param
        *@return
        */

        int datosEstadisticos(string busquedaDato);
    }

     /**
     * The base system.
     */
    interface TheSystem {

        /**
         * @return the diference in time between client and server.
         */
        long getDelay(long clientTime);

         /**
         * register a Persona with a persona instance.
         * 
         * @param persona to create
         * @return Persona created
         */
        Persona registrarPersona(Persona persona);

		/**
         * Register a Vehiculo with a vehiculo instanc
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
        *@param patente del vehiculo a buscar.
        *@return Vehiculo eliminar.
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

    }
}
