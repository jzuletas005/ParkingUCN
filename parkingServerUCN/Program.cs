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

using ServerParkingUCN.ZeroIce.model;
using ServerParkingUCN.ZeroIce;
using ServerParkingUCN.Dao;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace serverParkingUCN
{
    /// <summary>
    /// The Program
    /// </summary>
    class Program
    {
        /// <summary>
        /// The Main
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }
        
        /// <summary>
        /// The IHostBuilder
        /// </summary>
        /// <returns></returns>
        public static IHostBuilder CreateHostBuilder(string[] args) => 
            Host
            .CreateDefaultBuilder(args)
            //Development, Staging, Production
            .UseEnvironment("Development")
            //Loggin configuration
            .ConfigureLogging(loggin =>
            {
                loggin.ClearProviders();
                loggin.AddConsole(options =>
                {
                    options.IncludeScopes = true;
                    options.TimestampFormat = "[yyyyMMdd.HHmmss.fff]";
                    options.DisableColors = false;
                });
                loggin.SetMinimumLevel(LogLevel.Trace);
            })
            //Enable Control +C listener
            .UseConsoleLifetime()
            //Service inside the DI
            .ConfigureServices((HostBuilderContext, services)=>
            {
                 //The System
                services.AddSingleton<TheSystemDisp_, TheSystemImpl>();
                //Contratos
                services.AddSingleton<ContratosDisp_, ContratosImpl>();
                //The FivetContext
                services.AddDbContext<ServerParkingUCNContext>();
                //The FivetService
                services.AddHostedService<ServerParkingUCNService>();
                /// The logger
                services.AddLogging();
                //the wait for finish
                services.Configure<HostOptions>(option =>
                {
                    option.ShutdownTimeout = System.TimeSpan.FromMinutes(15);
                });
            });
    }
}
