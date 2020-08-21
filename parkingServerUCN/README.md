
# ParkingUCN
- **Course**: Proyecto Desarrollo e Integración de Soluciones.
- **Degree**: Ingeniería en Computación e Informática.

## Description
- Este modulo crea y mantiene la base de datos que dará soporte a todo el proyecto

## Java Libraries
- .Net Core 3.1

#### Microsoft
- Microsoft.EntityFrameworkCore Version 3.1.4
- Microsoft.Extensions.Hosting Version 3.1.4
- Microsoft.EntityFrameworkCore.Sqlite Version 3.1.4
- Microsoft.Extensions.DependencyInjection Version 3.1.4
- Microsoft.Extensions.Logging Version 3.1.4
- Microsoft.Extensions.Logging.Console Version 3.1.4

#### ZeroIce
- zeroc.ice.net Version 3.7.3.2
- zeroc.icebuilder.msbuild Version 5.0.4

## UML

![Screenshot](../img/server.png)

<!--
@startuml
interface TheSystem{
-getDelay(long clientTime): long
+Persona registrarPersona(Persona persona);
+Vehiculo registrarVehiculo(Vehiculo vehiculo);
+Persona obtenerPersona(string rut);
+Vehiculo obtenerVehiculo(string patente);
+Vehiculo eliminarVehiculo(string patente);
+Persona eliminarPersona(string rut);
+Vehiculo editarVehiculo(Vehiculo vehiculo);
+Persona editarPersona(Persona persona);
}
interface Contratos{
+Circulacion ingresoVehiculo(string patente, string puertaEntrada, string observacion);
+Circulacion salidaVehiculo(string patente, string puertaSalida);
+Circulacion busquedaVehiculoBackend(string patente);
+int vehiculosGate(string puerta);
+int vehiculosInterior(int estadoVehiculo);
+int totalRegion(string region);
+int datosEstadisticos(string busquedaDato);
+string formatearRut(string rut);
}
class Circulacion {
- int uid;
- string fechaIngreso;
- string horaIngreso;
- string fechaSalida;
- string horaSalida;
- string patente;
- string puertaEntrada;
- string puertaSalida;
- string observacion;
- int estadoVehiculo;
}
class Vehiculo{ 
-string patente;
-string codigoLogo;
-string marca;
-string modelo;
-int anio;
-string observacion;
-string responsable;
-TipoLogo tipoLogo;
}
class Person{
- int uid;
- string name; 
- string rut; 
- string sexo; 
- string wposition;
- string unit;
- string email; 
- string phone; 
- string office; 
- string address;
- string country;
- TipoLogo tipoLogo;
}
class ContratosImpl{
}
class TheSystemImpl{
}
class Program{
+Main();
+IHostBuilder CreateHostBuilder(string[] args):IHostBuilder
}
Program--|>TheSystemImpl: use
Program--|>ContratosImpl: use
TheSystemImpl--|>Person:use
TheSystemImpl--|>Vehiculo:use
ContratosImpl--|>Circulacion:use
TheSystem..|>TheSystemImpl:implement
Contratos..|>ContratosImpl:implement
@enduml
-->
