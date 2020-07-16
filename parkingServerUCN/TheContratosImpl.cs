using Ice;
using ServerZeroIce.model;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Dao; 

namespace ServerParkingUCN
{
    public class TheContratosImpl
    {


 // The Logger
        private readonly ILogger<TheContratosImpl> _logger;

        // The Provider of DbContext
        private readonly IServiceScopeFactory _serviceScopeFactory;

        /// <summary>
        /// The Constructor.
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="serviceScopeFactory"></param>
        public TheContratosImpl(ILogger<TheContratosImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building the ContratosImpl ..");
            _serviceScopeFactory = serviceScopeFactory;

            // Create the database
            _logger.LogInformation("Creating the Database ..");
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingUCNContext>();
                pc.Database.EnsureCreated();
                pc.SaveChanges();
            }
            
            _logger.LogDebug("Done.");            
        }

        // Adds a Persona to Database
        /*
        public override Persona registrarPersona(Persona persona, Current current = null)
        {
            
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingUCNContext>();
                pc.Persona.Add(persona);
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
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingUCNContext>();
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
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingUCNContext>();
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
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingUCNContext>();
                Persona persona = pc.Personas.Find(rut);
                pc.SaveChanges();
                return persona;
            }
            throw new System.NotImplementedException();
            
        }*/
    }
}