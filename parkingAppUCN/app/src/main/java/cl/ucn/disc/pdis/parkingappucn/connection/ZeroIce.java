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
package cl.ucn.disc.pdis.parkingappucn.connection;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.InitializationData;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Properties;
import com.zeroc.Ice.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;

@SuppressWarnings("Singleton")
public class ZeroIce {

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(ZeroIce.class);

    /**
     * The Singleton
     */
    private static final ZeroIce ZERO_ICE = new ZeroIce();
    
    /**
    * Server IP (MUST BE SETTED BY ADMINISTRATOR)
    */
    private String serverIP = "192.168.0.22";

    /**
     * The ZeroIce Communicator
     */
    private Communicator theCommunicator;

    /**
     * The Contratos Implementation
     */
    private ContratosPrx theContratos;

    /**
     * The System Implementation
     */
    private TheSystemPrx theSystem;

    /**
     * The Constructor.
     */
    public ZeroIce() {
    } //Nothing here ...

    /**
     * Get Instance.
     *
     * @return the ZeroIce.
     */
    public static ZeroIce getInstance() {
        return ZERO_ICE;
    }

    /**
     * Get Contratos.
     *
     * @return the Contratos.
     */
    public ContratosPrx getContratos() {
        return this.theContratos;
    }

    /**
     * Get TheSystem
     *
     * @return theSystem
     */
    public TheSystemPrx getTheSystem() {
        return this.theSystem;
    }

    /**
     * Initialization of ZeroIce.
     *
     * @return
     */
    private static InitializationData getInitializationData() {

        // Properties
        final Properties properties = Util.createProperties();
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.scrapper.zeroice");

        // https://doc.zeroc.com/ice/latest/property-reference/ice-trace
        // properties.setProperty("Ice.Trace.Admin.Properties", "1");
        // properties.setProperty("Ice.Trace.Locator", "2");
        properties.setProperty("Ice.Trace.Network", "3");
        // properties.setProperty("Ice.Trace.Protocol", "1");
        // properties.setProperty("Ice.Trace.Slicing", "1");
        // properties.setProperty("Ice.Trace.ThreadPool", "1");
        // properties.setProperty("Ice.Compression.Level", "9");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

    /**
     * Start the Communications.
     */
    public void start() {

        try {
            this.theCommunicator = Util.initialize(getInitializationData());

            log.debug("Proxying <TheSystem> ..");
            log.debug("Proxying <Contratos> ..");

            //to connect to the server we use your ip
            ObjectPrx theProxy = this.theCommunicator.stringToProxy("TheSystem:tcp -h "+serverIP+" -t 15000 -p 8080");
            ObjectPrx theProxyC = this.theCommunicator.stringToProxy("Contratos:tcp -h "+serverIP+" -t 15000  -p 4000");

            this.theSystem = TheSystemPrx.checkedCast(theProxy);
            this.theContratos = ContratosPrx.checkedCast(theProxyC);

        } catch (ConnectionRefusedException ex) {
            log.warn("Backend error", ex);
        }
    }

    /**
     * Stop the Communications.
     */
    public void stop() {
        //If the communicator is null, it is destroyed
        if (this.theCommunicator == null) {
            log.warn("The Communicator was already stopped?");
            return;
        }
        this.theSystem = null;
        this.theContratos = null;
        this.theCommunicator.destroy();
    }

}
