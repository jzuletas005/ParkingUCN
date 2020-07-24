using ServerParkingUCN.Dao;
using ServerParkingUCN.ZeroIce.model;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
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

        ///Adds a Persona to Database
        public override Persona registrarPersona(Persona persona, Current current = null)
        {
            
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                pc.Personas.Add(persona);
                pc.SaveChanges();
                return persona;
            }
            
            throw new System.NotImplementedException();
        }

        // Adds a Vehiculo to Database
       public override Vehiculo registrarVehiculo(Vehiculo vehiculo, Current current = null)
        {
           
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
                return vehiculo;
            }
            throw new System.NotImplementedException();
        }

        // Given a patente, returns a vehiculo from Database
        public override Vehiculo obtenerVehiculo(string patente, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                pc.SaveChanges();
                return vehiculo;
            }
            throw new System.NotImplementedException();
        }

        // Given a rut, returns a persona from Database
        public override Persona obtenerPersona(string rut, Current current)
        { 
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                Persona persona = pc.Personas.Find(rut);
                pc.SaveChanges();
                return persona;
            }
            throw new System.NotImplementedException();
            
        }

        public override Vehiculo eliminarVehiculo(string patente, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                pc.Vehiculos.Remove(vehiculo);
                pc.SaveChanges();
                return vehiculo;
            }
            throw new System.NotImplementedException();
        }

        public override Persona eliminarPersona(string rut, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                Persona persona = pc.Personas.Find(rut);
                pc.Personas.Remove(persona);
                pc.SaveChanges();
                return persona;
            }
        }
    }
}