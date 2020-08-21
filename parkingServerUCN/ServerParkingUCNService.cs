/*
 * MIT License
 *
 *  Copyright (c) 2020 Javier Zuleta Silva, Beatriz Alvarez Rojas, Gonzalo Nieto Berrios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
            _logger = logger;
            _logger.LogDebug("Building ServerParkingUCNService ..");
            _logger.LogDebug(localIP);
            _logger.LogDebug("dato"+ _port);
            _theSystem = theSystem;
            _contratos = contratos;
            _communicator = buildComunicator();

        }

        /// <summary>
        /// Triggered when the application host is performing a graceful shutdown.
        /// </summary>
        /// <param name="cancellationToken"></param>
        /// <returns></returns>

        //Start the comunication of server
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
            var adapterC = _communicator.createObjectAdapterWithEndpoints("ParkingUCNA", "tcp -z -t 15000 -p 4000");

            // Register in the communicator
            adapter.add(_theSystem, Util.stringToIdentity("TheSystem"));
            adapterC.add(_contratos, Util.stringToIdentity("Contratos"));

            //Activation
            adapter.activate();
            adapterC.activate();

            //_theSystem.getDelay(0);

            return Task.CompletedTask;
        }
        //Stopping conextion of server
        public Task StopAsync(CancellationToken cancellationToken)
            {

                _logger.LogDebug("Stoping the ServerParkingUCNService ..");

            _communicator.shutdown();

            _logger.LogDebug("Communication Stoped");

            return Task.CompletedTask;

             throw new NotImplementedException();
                }

        //build the comunicator
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