using System;
using Ice;
using ServerZeroIce.model;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace ServerParkingUCN
{

    internal class ParkingUCNService : IHostedService, IDisposable
    {

        /// <summary>
        /// The Logger.
        /// </summary>
        private readonly ILogger<ParkingUCNService> _logger;

        /// <summary>
        /// The Port.
        /// </summary>
        private readonly int _port = 8080;

        /// <summary>
        /// The Communicator.
        /// </summary>
        private readonly Communicator _communicator;

        // The Contracts
        //private readonly ContratosDisp_ _contratos;

        /// <summary>
        /// The ParkingUCNService 
        /// </summary>
        /// <param name="logger">Used to print debug message.</param>
        //public ParkingUCNService(ILogger<ParkingUCNService> logger, ContratosDisp_ contratos)
        //{
            //_logger = logger;
            //_logger.LogDebug("Building the Parking UCN Service ..");
            //_contratos = contratos;
            //_communicator = buildCommunicator();
        //}

        /// <summary>
        /// Triggered when the application host is ready to start the service.
        /// </summary>
        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogDebug("Starting the ParkingService ..");

            // The adapter: https://doc.zeroc.com/ice/3.7/client-side-features/proxies/proxy-and-endpoint-syntax
            // tcp (protocol) -z (compression) -t 15000 (timeout in ms) -p 8080 (port to bind)
            var adapter = _communicator.createObjectAdapterWithEndpoints("ContratosAdapter", "tcp -z -t 15000 -p " + _port);

            // Register in the communicator
            //adapter.add(_contratos, Util.stringToIdentity("Contratos"));

            // Activation
            adapter.activate();

            // All ok
            return Task.CompletedTask;
        }

        /// <summary>
        /// Triggered when the application host is performing a graceful shutdown.
        /// </summary>
        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogDebug("Stopping the ParkingService ..");

            _communicator.shutdown();

            _logger.LogDebug("Communicator Stopper!");

            return Task.CompletedTask;
        }

        /// <summary>
        /// Build the communicator.
        /// </summary>
        /// <returns>The Communicator</returns>
        private Communicator buildCommunicator()
        {
            _logger.LogDebug("Initializating Communicator v{0} ({1}) ..", Ice.Util.stringVersion(), Ice.Util.intVersion());

            // ZeroC properties
            Properties properties = Util.createProperties();
            // properties.setProperty("Ice.Trace.Network", "3");

            InitializationData initializationData = new InitializationData();
            initializationData.properties = properties;

            return Ice.Util.initialize(initializationData);
        }

        /// <summary>
        /// Clear the memory.
        /// </summary>
        public void Dispose()
        {
            _communicator.destroy();
        }

    }

}