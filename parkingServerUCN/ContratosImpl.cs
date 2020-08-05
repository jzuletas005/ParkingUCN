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

