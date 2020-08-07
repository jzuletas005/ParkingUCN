package cl.ucn.disc.pdis.parkingappucn;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ConnectionRefusedException;
import com.zeroc.Ice.InitializationData;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Properties;
import com.zeroc.Ice.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.String;

import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystem;
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
     * The ZeroIce Communicator
     */
    private Communicator _theCommunicator;

    /**
     * The Contratos Implementation
     */
    private ContratosPrx _theContratos;

    /**
     * The System Implementation
     */
    private TheSystemPrx _theSystem;

    /**
     * The Constructor.
     */
    public ZeroIce() {} //Nothing here ...

    /**
     * Get Instance.
     * @return the ZeroIce.
     */
    public static ZeroIce getInstance()  {return ZERO_ICE;}

    /**
     * Get Contratos.
     * @return the Contratos.
     */
    public ContratosPrx getContratos() { return this._theContratos; }

    /**
     * Get TheSystem
     * @return theSystem
     */
    public TheSystemPrx getTheSystem() { return this._theSystem; }

    /**
     * Initialization of ZeroIce.
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

        try{
            this._theCommunicator = Util.initialize(getInitializationData());

            log.debug("Proxying <TheSystem> ..");

            ObjectPrx theProxy = this._theCommunicator.stringToProxy("TheSystem:tcp -z -t 15000 -p 8080");

            this._theSystem = TheSystemPrx.checkedCast(theProxy);

        }catch (ConnectionRefusedException ex){
            log.warn("Backend error", ex);
        }
    }

    /**
     * Stop the Communications.
     */
    public void stop() {
        //If the communicator is null, it is destroyed
        if (this._theCommunicator == null) {
            log.warn("The Communicator was already stopped?");
            return;
        }
        this._theSystem = null;
        this._theCommunicator.destroy();
    }

}
