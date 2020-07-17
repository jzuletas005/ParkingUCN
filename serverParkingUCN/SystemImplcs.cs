using System;
using Ice;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using ServerParkingUCN.ZeroIce.model;


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

        ///<sumary>
        /// The Constructor
        ///</sumary>
        ///<param name="logger">The Logger</param>
        ///<param name="serviceScopeFactory">The Scope</param>
        public TheSystemImpl(ILogger<TheSystemImpl> logger, IServiceScopeFactory serviceScopeFactory)
        {
            _logger = logger;
            _logger.LogDebug("Building TheSystemImpl ..");
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