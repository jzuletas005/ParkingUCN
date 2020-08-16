using ServerParkingUCN.Dao;
using ServerParkingUCN.ZeroIce.model;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using System;

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
            public override Circulacion ingresoVehiculo(string patente, string puertaEntrada, Current current)
            {
                using(var scope = _serviceScopeFactory.CreateScope())
                {
                    ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                    Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                    Circulacion ingreso = new Circulacion(); 
                    ingreso.patente = patente; 
                    ingreso.puertaEntrada =puertaEntrada;
                    ingreso.fechaIngreso = DateTime.Now.ToString("dd-MM-yyyy HH:mm:ss");
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
                    ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                    Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                    Circulacion salida = new Circulacion(); 
                    salida.patente = patente; 
                    salida.puertaSalida = puertaSalida;
                    salida.fechaSalida = DateTime.Now.ToString("dd-MM-yyyy HH:mm:ss");
                    pc.Circulaciones.Add(salida);
                    pc.SaveChanges();
                    return salida;
                    
                }
                throw new System.NotImplementedException();
                
                }

        //Method that checks if the logo and the patent match 
        public override bool verificarPatenteLogo(string patente, string codigoLogo, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                if (vehiculo.codigoLogo == codigoLogo){
                    return true;
                }else{
                    return false;
                }
            }
            throw new System.NotImplementedException();
        }

         //Method that checks if the logo and the patent match 
        public override bool verificarLogoPatente(string patente, string codigoLogo, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                Identificacion identificacion = pc.Identificaciones.Find(codigoLogo);
                if (identificacion.patente == patente){
                    return true;
                }else{
                    return false;
                }
            }

        }
    }
}

