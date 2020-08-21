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
    *
    * Clase persona
    */
        ["cs:property"]
        class Persona{

            /**
            *PrimaryKey
            */
            int uid;

            /**
            *Nombre de la persona;
            */
            string nombre;

            /**
            *PrimaryKey
            *Rut:11.111.111-1
            */  
            string rut;

            /**
            *Sexo
            */
            string sexo; 


            /**
            *wPosition corresponde al cargo del trabajor
            */
            string wposition;

            /**
            * Unidad corresponde a qué unidad pertenece el funcionario
            */
            string unit; 

            /**
            *Correo Electronico
             */
            string email;

            /**
            *Telefono 
            */
            string telefono;

            /**
            *Oficina
            */
            string oficina;

            /**
            *Direccion
            */
            string direccion;

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
        *codigo del LogoUCN.
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
        string tipoLogo;

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
         *Hora de salida del vehiculo
         */
         string horaSalida;

         /**
         *Patente
         */
         string patente;

         /**
         *Acceso a la universidad
         */
         string puertaEntrada;

         /**
         *Corresponde a por donde sale el vehículo registrado.
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
         * Interface de los contratos
         */
        interface Contratos{

        /**
        *Método que registra el ingreso del vehículo.
        *
        *@param patente del vehículo
        *@param puerta de acceso a la univerisdad 
        *@param Observaciones
        *@return Ingreso del vehículo previamente registrado y autorizado por el guardia de seguridad.
        */
        Circulacion ingresoVehiculo(string patente, string puertaEntrada, string observacion);

        /**
        *Método que registra la Salida del Vehículo. 
        *
        *@param patente del vehículo
        *@param Puerta por dónde sale el vehículo
        *@return La salida del vehículo previamente autorizada y registrada por el guardia de seguridad. 
        */
        Circulacion salidaVehiculo(string patente, string puertaSalida);

         /**
         *Método que busca el estado del vehículo. 
         *
         *@param patente del vehículo
         *@return Los datos del Vehoculo ingresado y su estado.
        */
        Circulacion busquedaVehiculoBackend(string patente);

        /**
        *Método que entrega la cantidad de vehiculos estacionados dentro de la universidad. 
        *@param estado del vehiculo
        *@return la cantidad de vehiculo que han ingresado al recinto
        */     
        int vehiculosInterior(int estadoVehiculo);

        /**
        *Método que retorna la puerta por la cuál el vehículo entra o sale de la universidad. 
        *@param Puerta
        *@return la puerta de acceso o salida del Vehículo.
        */
       int vehiculosGate(string puerta);

        /**
        *Método que contabiliza a las personas en determinada región 
        *@param region del pais
        *@return el total personas que existen la región que se marca. 
        */
        int totalRegion(string region);

        /**
        *Método que contabiliza y retornas las estadisticas de la aplicacion
        *@param datos a buscar
        *@return Datos estadisticos. 
        */
        int datosEstadisticos(string busquedaDato);

        /**
        *
        *Método que da formato al rut, con punto y guión. 
        *@param rut de la persona
        *@return el rut en el formato "1.111.111-1"
        */ 
        string formatearRut(string rut);
    }

     /**
     * The base system.
     */
    interface TheSystem {

        /**
         *
         * @return la diferencia de tiempo entre el cliente y el servidor.
         */
        long getDelay(long clientTime);

         /**
         * registrar una Persona con una instancia de persona.
         * 
         * @param persona a crear
         * @return Persona creada
         */
        Persona registrarPersona(Persona persona);

		/**
         * Registrar un vehículo con una instancia de vehículo 
         *
         * @param vehiculo a 
         * @return Vehiculo creado
         */
        Vehiculo registrarVehiculo(Vehiculo vehiculo);
      
        /**
         * Busca a una Persona por el rut.
         *
         * @param rut de la persona a buscar.
         * @return Persona buscada.
         */
        Persona obtenerPersona(string rut);

		/**
         * Busca un vehiculo por la patente
		 *
         * @param patente del vehiculo a buscar.
         * @return Vehiculo buscado.
         */
        Vehiculo obtenerVehiculo(string patente);

        /**
        * Elimina a una persona por medio del rut;
        *
        * @param rut del vehiculo a buscar.
        * @return persona buscada a eliminar.
        */
        Persona eliminarPersona(string rut);
        

        /**
        *Elimina un Vehiculo por medio de la patente
        *
        *@param patente del vehiculo a buscar.
        *@return Vehiculo buscado a eliminar.
        */
        Vehiculo eliminarVehiculo(string patente);


        /**
        *Edita a una persona
        *
        * @param persona a editar.
        * @return persona editada.
        */
        Persona editarPersona(Persona persona);

        /**
        *
        *Edita a un vehículo. 
        *@param vehiculo a editar 
        *@return vehiculo editado
        */
        Vehiculo editarVehiculo(Vehiculo vehiculo);

    }
}
