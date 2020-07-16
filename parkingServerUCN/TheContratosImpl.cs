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
        //public override Persona crear(Persona persona, Current current = null)
        //{
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Personas.Add(persona);
                pc.SaveChanges();
                return persona;
            }
            */
            //throw new System.NotImplementedException();
        //}

        // Adds a Vehiculo to Database
        //public override Vehiculo crearVehiculo(Vehiculo vehiculo, Current current = null)
        //{
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                pc.Vehiculos.Add(vehiculo);
                pc.SaveChanges();
                return vehiculo;
            }
            */
            //throw new System.NotImplementedException();
        //}

        // Given a patente, returns a vehiculo from Database
        //public override Vehiculo obtenerVehiculo(string patente, Current current)
        //{
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingUCNContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Vehiculo vehiculo = pc.Vehiculos.Find(patente);
                pc.SaveChanges();
                return vehiculo;
            }
            */
            //throw new System.NotImplementedException();
        //}

        // Given a rut, returns a persona from Database
        //public override Persona obtenerPersona(string rut, Current current)
        //{
            /*TODO:
            using (var scope = _serviceScopeFactory.CreateScope())
            {
                ParkingContext pc = scope.ServiceProvider.GetService<ParkingContext>();
                Persona persona = pc.Personas.Find(rut);
                pc.SaveChanges();
                return persona;
            }
            */
            //throw new System.NotImplementedException();
        //}
    }
}