using System;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using ServerParkingUCN.ZeroIce.model;
using ServerParkingUCN.Dao;


namespace ServerParkingUCN.ZeroIce
{
    ///<sumary>
    /// The Implementation of The System Interface
    ///</sumary>
    public class TheSystemImpl : TheSystemDisp_
    {
        ///<sumary>
        /// The Logger
        ///</sumary>
        private readonly ILogger<TheSystemImpl> _logger;

        private readonly IServiceScopeFactory _serviceScopeFactory;

        ///<sumary>
        /// The Constructor
        ///</sumary>
        ///<param name="logger">The Logger</param>
        ///<param name="serviceScopeFactory">The Scope</param>
        public TheSystemImpl(ILogger<TheSystemImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building TheSystemImpl ..");
            _serviceScopeFactory = serviceScopeFactory;
            
             // Create the database
            _logger.LogInformation("Creating the Database ..");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                pc.Database.EnsureCreated();
                pc.SaveChanges();
            }
        }

        public TheSystemImpl()
        {
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
        //Given a patente, deleting a vehiculo from Database
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
        //Given a rut, deleting a persona from Database
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
            throw new System.NotImplementedException();
        }
        //Editing a persona from database. 
        public override Persona editarPersona(Persona persona, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                pc.Personas.Update(persona);
                pc.SaveChanges();
                return persona;
            }
            throw new System.NotImplementedException();
        }

        //Editing a Vehiculo from de database.
        public override Vehiculo editarVehiculo(Vehiculo vehiculo, Current current)
        {
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ServerParkingUCNContext pc = scope.ServiceProvider.GetService<ServerParkingUCNContext>();
                pc.Vehiculos.Update(vehiculo);
                pc.SaveChanges();
                return vehiculo;
            }
            throw new System.NotImplementedException();
        }

        ///<sumary>
        /// Return the difference in time
        ///</sumary>
        ///<param name="clienTime"></param>
        ///<param name="current"></param>
        ///<returns>The Delay</returns>
        public override long getDelay(long clientTime, Current current = null)
        {
            return DateTimeOffset.UtcNow.ToUnixTimeMilliseconds() - clientTime;
        }
    }
}