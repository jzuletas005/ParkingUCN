using Ice;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using ServerParkingUCN.ZeroIce.model;
using ServerParkingUCN.ZeroIce;


namespace serverParkingUCN
{
    /// <summary>
    /// The Fivet Service
    /// </summary>
    internal class ServerParkingUCNService : IHostedService, IDisposable
    {   
        /// <summary>
        /// Logger
        /// </summary>
        private readonly ILogger<ServerParkingUCNService> _logger;

        /// <summary>
        /// The Port
        /// </summary>
        private readonly int _port = 8080;

        /// <summary>
        /// The Communicator
        /// </summary>
        private readonly Communicator _communicator;

        /// <summary>
        /// The implementation of the System
        /// </summary>
        private readonly TheSystemDisp_ _theSystem;

        /// <summary>
        /// The implementation of the Contratos
        /// </summary>
        private readonly ContratosDisp_ _contratos;

        /// <summary>
        /// The Constructor
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="theSystem"></param>
        /// <param name="contratos"></param>
        public ServerParkingUCNService(ILogger<ServerParkingUCNService> logger, TheSystemDisp_ theSystem, ContratosDisp_ contratos)
        {
            _logger = logger;
            _logger.LogDebug("Building ServerParkingUCNService ..");
            _theSystem = theSystem;
            _contratos = contratos;
            _communicator = buildComunicator();
        }

        /// <summary>
        /// Triggered when the application host is performing a graceful shutdown.
        /// </summary>
        /// <param name="cancellationToken"></param>
        /// <returns></returns>
        public Task StartAsync(CancellationToken cancellationToken)
        {

             IPHostEntry host;
            string localIP = "";
            host = Dns.GetHostEntry(Dns.GetHostName());
            foreach (IPAddress ip in host.AddressList)
            {
                if (ip.AddressFamily.ToString() == "InterNetwork")
                {
                    localIP = ip.ToString();
                }
            }

            _logger.LogDebug("Starting the ServerParkingUCNService ..");
            _logger.LogDebug(localIP);
            _logger.LogDebug("dato"+ _port);

            _logger.LogDebug("Starting the ServerParkingUCNService ..");

            // The adapter
            var adapter = _communicator.createObjectAdapterWithEndpoints("ParkingUCN", "tcp -z -t 15000 -p " + _port);

            // Register in the communicator
            adapter.add(_theSystem, Util.stringToIdentity("TheSystem"));

            //Activation
            adapter.activate();

            //_theSystem.getDelay(0);

            return Task.CompletedTask;
        }

        /// <summary>
        /// Triggered when the application host is performing a graceful shutdown.
        /// </summary>
        /// <param name="cancellationToken"></param>
        /// <returns></returns>
        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogDebug("Stoping the ServerParkingUCNService ..");

            _communicator.shutdown();

            _logger.LogDebug("Communication Stoped");

            return Task.CompletedTask;
        }

        private Communicator buildComunicator()
        {
            _logger.LogDebug("Initializing Communicator v{0} ({1}) ..", Ice.Util.stringVersion(), Ice.Util.intVersion());

            Properties properties = Util.createProperties();
            properties.setProperty("Ice.Trace.Network", "3");
            
            InitializationData initializationData = new InitializationData();
            initializationData.properties = properties;

            return Ice.Util.initialize(initializationData);
        }

        /// <summary>
        /// Clear the memory
        /// </summary>
        public void Dispose()
        {
            _communicator.destroy();
        }
    }

}