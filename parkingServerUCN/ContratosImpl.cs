using ServerParkingUCN.Dao;
using ServerParkingUCN.ZeroIce.model;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using System;
using System.Linq;
using System.Collections.Generic;

namespace ServerParkingUCN.ZeroIce
{
    ///<sumary>
    /// The Implementation of the Contratos
    ///</sumary>
    public class ContratosImpl : ContratosDisp_
    {

        // The Logger
        private readonly ILogger<ContratosImpl> _logger;

        // The Provider of DbContext
        private readonly IServiceScopeFactory _serviceScopeFactory;

        /// <summary>
        /// The Constructor.
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="serviceScopeFactory"></param>
        public ContratosImpl(ILogger<ContratosImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building the ContratosImpl ..");
            _serviceScopeFactory = serviceScopeFactory;

            // Create the database
            _logger.LogInformation("Creating the Database ..");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                pc.Database.EnsureCreated();
                pc.SaveChanges();
            }

            _logger.LogDebug("Done.");
        }
                   //Method that records the entry of the vehicle 
            public override Circulacion ingresoVehiculo(string patente, string puertaEntrada,string observacion, Current current)
            {
                using(var scope = _serviceScopeFactory.CreateScope())
                {

                    var dato = 1;
                    ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                    Circulacion ingreso = new Circulacion(); 
                    ingreso.patente = patente; 
                    ingreso.puertaEntrada = puertaEntrada;
                    ingreso.estadoVehiculo = dato;
                    ingreso.observacion = observacion;
                    ingreso.fechaIngreso = DateTime.Now.ToString("dd-M-yyyy");
                    ingreso.horaIngreso = DateTime.Now.ToString("HH:mm:ss");
                    pc.Circulaciones.Add(ingreso);
                    pc.SaveChanges();
                    return ingreso ;
            }
               throw new System.NotImplementedException();
            }

                //Method that records the departure of the vehicle 
            public override Circulacion salidaVehiculo(string patente,string puertaSalida, Current current)
            {
                 using(var scope = _serviceScopeFactory.CreateScope())
                {
                     var dato = 0;
                    ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                    Circulacion salida = new Circulacion();
                    salida = pc.Circulaciones.Where(w => w.patente == patente).Where(a => a.estadoVehiculo == 1).FirstOrDefault();
                    salida.puertaSalida = puertaSalida;
                    salida.estadoVehiculo = dato;
                    salida.fechaSalida = DateTime.Now.ToString("dd-M-yyyy");
                    salida.horaSalida = DateTime.Now.ToString("HH:mm:ss");
                    pc.SaveChanges();
                    return salida;
                    
                }
                throw new System.NotImplementedException();
                
                }

            public override Circulacion busquedaVehiculoBackend(string patente, int estado, Current current)
            {
                 using(var scope = _serviceScopeFactory.CreateScope())
                {
                    ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                    Circulacion salida = new Circulacion();
                    salida = pc.Circulaciones.Where(w => w.patente == patente).Where(a => a.estadoVehiculo == estado).LastOrDefault();
                    pc.SaveChanges();
                    return salida;

                }
                throw new System.NotImplementedException();

            }
       
        
        // Method count cars into the university
        public override int  vehiculosInterior(int estadoVehiculo ,string fecha, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                int dato = pc.Circulaciones.Where(w => w.estadoVehiculo == 1).Where(o=> o.fechaIngreso == fecha).Count();  
                pc.SaveChanges();

                return dato;
            }
            throw new System.NotImplementedException();
        }

        public override int  vehiculosGatePrincipal(int estadoVehiculo,string fecha, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                int dato = pc.Circulaciones.Where(w => w.estadoVehiculo == 1).Where(p=> p.puertaEntrada == "Principal").Count();
                pc.SaveChanges();

                return dato;
            }
            throw new System.NotImplementedException();
        }

        public override int  vehiculosGateSur(int estadoVehiculo,string fecha ,Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                int dato = pc.Circulaciones.Where(w => w.estadoVehiculo == 1).Where(p=> p.puertaEntrada == "Sur").Count();
                pc.SaveChanges();

                return dato;
            }
            throw new System.NotImplementedException();
        }

        public override int  vehiculosGateAngamos(int estadoVehiculo,string fecha, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                int dato = pc.Circulaciones.Where(w => w.estadoVehiculo == 1).Where(p=> p.puertaEntrada == "Angamos").Count();
                pc.SaveChanges();

                return dato;
            }
            throw new System.NotImplementedException();
        }


        public override int totalRegion(string region, Current current)
        { 
             using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                int dato = pc.Personas.Where(w => w.country == region).Count();
                pc.SaveChanges();

                return dato;
            }
            throw new System.NotImplementedException();
        } 

        public override int datosEstadisticos(string busquedaDato, Current current)
        {

             using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                int dato = pc.Personas.Where(w => w.unit == busquedaDato).Count();
                pc.SaveChanges();

                return dato;
            }
            throw new System.NotImplementedException();
        }
    }
}



