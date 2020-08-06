package cl.ucn.disc.pdis.parkingappucn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Persona;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Vehiculo;

public class TestContratos {

    public static Logger logger = LoggerFactory.getLogger(TestContratos.class);

    public void registrarCar() {

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        Vehiculo vehiculo1 = new Vehiculo();

        vehiculo1.patente = "GG-WPN";
        vehiculo1.codigoLogo = "s0123456789";
        vehiculo1.marca = "Ferrari";
        vehiculo1.modelo = "Berlini";
        vehiculo1.anio = 1895;
        vehiculo1.observacion = "No tiene el logo pegado";
        vehiculo1.responsable = "123456789";

        logger.debug("Vehiculo : {} {} {}",
                vehiculo1.patente,
                vehiculo1.marca,
                vehiculo1.responsable);


        Vehiculo vehiculo2 = contratosPrx.registrarVehiculo(vehiculo1);
        logger.debug("DONE: Vehicle added succefully on database!");

    }

    public void obtenerCar(String patente) {

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        Vehiculo vehiculofind = contratosPrx.obtenerVehiculo(patente);

        logger.debug("Responsable : {}", vehiculofind.responsable);

        logger.debug("DONE: Vehicles returned susccefully!");

    }

    public void obtenerPeople(String rutpersona){

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        Persona personafind = contratosPrx.obtenerPersona(rutpersona);

        logger.debug("Nombre: {} " +
                        "Cargo: {} " +
                        "Unidad: {} " +
                        "Región: {} ",
                personafind.nombre,
                personafind.wposition,
                personafind.unit,
                personafind.country);

    }

    public void editarCar(){

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        Vehiculo vehiculo1 = new Vehiculo();

        vehiculo1.patente = "GG-WPNG";
        vehiculo1.codigoLogo = "s0123456789";
        vehiculo1.marca = "Ferrari";
        vehiculo1.modelo = "Berlini";
        vehiculo1.anio = 1895;
        vehiculo1.observacion = "Ya pego su logo";
        vehiculo1.responsable = "123456789";

        Vehiculo vehiculoupdate = contratosPrx.editarVehiculo(vehiculo1);

        logger.debug("Responsable : {}", vehiculo1.responsable);
        logger.debug("DONE: Vehicles updated susccefully!");

    }
}
