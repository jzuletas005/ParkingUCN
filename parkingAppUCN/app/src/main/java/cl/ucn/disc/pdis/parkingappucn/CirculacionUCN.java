package cl.ucn.disc.pdis.parkingappucn;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.scrapper.zeroice.model.Circulacion;
import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Vehiculo;

public class CirculacionUCN extends AppCompatActivity {

    /**
     * Logger
     */
    public static Logger logger = LoggerFactory.getLogger(CirculacionUCN.class);

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog dialog;

    private EditText rPatente, rObservacion;

    private TextView tTipoLogo, fIngreso, hIngreso, tPuertaIngreso, tPatente, tEstado, tObservacion;

    private Spinner spinner;

    private Button saveVehiculo, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulacion);

        Button ingresoCar = findViewById(R.id.buttonIngresoCirculacion);
        Button salidaCar = findViewById(R.id.buttonSalidaCirculacion);
        Button busquedaCar = findViewById(R.id.buttonBusquedaCirculacion);

        String[] puertas = {"Principal", "Sur", "Angamos"};

        ingresoCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IngresoVehiculo(puertas);
            }
        });

        salidaCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalidaVehiculo(puertas);
            }
        });

        busquedaCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusquedaCar();
            }
        });

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        TextView globalActivo = (TextView) findViewById(R.id.globalIngreso);
        TextView principal = (TextView) findViewById(R.id.puertaPrincipal);
        TextView sur = (TextView) findViewById(R.id.puertaSur);
        TextView angamos = (TextView) findViewById(R.id.puertaAngamos);

        int globalA = contratosPrx.vehiculosInterior(1);
        int principalG = contratosPrx.vehiculosGate("Principal");
        int surG = contratosPrx.vehiculosGate("Sur");
        int angamosG = contratosPrx.vehiculosGate("Angamos");

        globalActivo.setText(String.valueOf(globalA));
        principal.setText(String.valueOf(principalG));
        sur.setText(String.valueOf(surG));
        angamos.setText(String.valueOf(angamosG));

    }

    private void IngresoVehiculo(String[] puertas) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View CarPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_entry, null);

        rPatente = (EditText) CarPopUpView.findViewById(R.id.rPatente);
        rObservacion = (EditText) CarPopUpView.findViewById(R.id.rObservacion);
        tTipoLogo =  (TextView) CarPopUpView.findViewById(R.id.existe);
        spinner = (Spinner) CarPopUpView.findViewById(R.id.spinner);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, puertas);
        spinner.setAdapter(adapter);

        saveVehiculo = (Button) CarPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) CarPopUpView.findViewById(R.id.cancel);

        dialogBuilder.setView(CarPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        try {
            if(theSystemPrx.obtenerVehiculo(rPatente.getText().toString())== null){
                tTipoLogo.setText("No está registrado");
            }else {
                tTipoLogo.setText(theSystemPrx.obtenerVehiculo(rPatente.getText().toString()).codigoLogo); // Entrega el Codigo del Logo
            }
        }catch (Exception exception){
            logger.error("Error: "+exception);
        }

        saveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seleccion = spinner.getSelectedItem().toString();
                try{
                    Circulacion circulacion = contratosPrx.ingresoVehiculo(rPatente.getText().toString(),
                            seleccion, rObservacion.getText().toString());
                    Toast.makeText(CirculacionUCN.this, "Vehiculo Ingresado: " + circulacion.patente, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }catch (Exception exception){
                    logger.error("Error: "+exception);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    private void SalidaVehiculo(String[] puertas) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View CarPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_entry, null);

        rPatente = (EditText) CarPopUpView.findViewById(R.id.rPatente);
        rObservacion = (EditText) CarPopUpView.findViewById(R.id.rObservacion);
        tTipoLogo =  (TextView) CarPopUpView.findViewById(R.id.existe);
        spinner = (Spinner) CarPopUpView.findViewById(R.id.spinner);

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, puertas);
        spinner.setAdapter(adapter);

        saveVehiculo = (Button) CarPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) CarPopUpView.findViewById(R.id.cancel);

        dialogBuilder.setView(CarPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        try {
            if(theSystemPrx.obtenerVehiculo(rPatente.getText().toString())== null){
                tTipoLogo.setText("No está registrado");
            }else {
                tTipoLogo.setText(theSystemPrx.obtenerVehiculo(rPatente.getText().toString()).codigoLogo); // Entrega el Codigo del Logo
            }
        }catch (Exception exception){
            logger.error("Error: "+exception);
        }

        saveVehiculo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String seleccion = spinner.getSelectedItem().toString();
                try{
                    Circulacion circulacion = contratosPrx.salidaVehiculo(rPatente.getText().toString(),
                            seleccion);
                    Toast.makeText(CirculacionUCN.this, "Vehiculo Saliendo: " + circulacion.patente, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }catch (Exception exception){
                    logger.error("Error: "+exception);
                    Toast.makeText(CirculacionUCN.this, "Vehiculo ya salió", Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    private void BusquedaCar() {
        setContentView(R.layout.activity_vehiculo_search);

        EditText patentefind = (EditText) findViewById(R.id.patenteseleccionada);
        Button findbutton = (Button) findViewById(R.id.Buscar);

        findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               findvehiculo(patentefind.getText().toString());
            }
        });

    }

    private void findvehiculo(String patentefind) {

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        fIngreso = (TextView) findViewById(R.id.fechadato);
        hIngreso = (TextView) findViewById(R.id.horadato);
        tPatente = (TextView) findViewById(R.id.tPatente);
        tPuertaIngreso = (TextView) findViewById(R.id.tPuertaIngreso);
        tObservacion = (TextView) findViewById(R.id.tObservacion);
        tEstado = (TextView) findViewById(R.id.tEstado);

        if(!patentefind.equals("")){

            Circulacion circulacion = contratosPrx.busquedaVehiculoBackend(patentefind, 1);

            try{

                if(circulacion != null){

                    fIngreso.setText(circulacion.fechaIngreso);
                    hIngreso.setText(circulacion.horaIngreso);
                    tPatente.setText(circulacion.patente);
                    tPuertaIngreso.setText(circulacion.puertaEntrada);
                    tObservacion.setText(circulacion.observacion);
                    tEstado.setText("SE ENCUENTRA EN LA UCN ");

                }else {
                    fIngreso.setText("");
                    hIngreso.setText("");
                    tPatente.setText("");
                    tObservacion.setText("");
                    tPuertaIngreso.setText("");
                    tEstado.setText("NO SE ENCUENTRA EN LA UCN");
                }

            }catch (Exception exception){
                logger.error("Error: "+exception);
                Toast.makeText(CirculacionUCN.this, "Vehiculo ya salió", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(CirculacionUCN.this, "Patente invalida", Toast.LENGTH_LONG).show();
        }

    }

}
