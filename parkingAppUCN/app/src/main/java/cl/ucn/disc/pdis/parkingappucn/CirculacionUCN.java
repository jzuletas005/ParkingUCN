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

    /**
     * AlertDialog.Builder
     */
    private AlertDialog.Builder dialogBuilder;

    /**
     * AlertDialog
     */
    private AlertDialog dialog;

    /**
     * EditText to Vehiculo
     */
    private EditText rPatente, rObservacion;

    /**
     * TextView to Vehiculo
     */
    private TextView tTipoLogo, fIngreso, hIngreso, tPuertaIngreso, tPatente, tEstado, tObservacion;

    /**
     * Spinner
     */
    private Spinner spinner;

    /**
     * Button
     */
    private Button saveVehiculo, cancel;

    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circulacion);
        //we linked to buttons on his ids
        Button ingresoCar = findViewById(R.id.buttonIngresoCirculacion);
        Button salidaCar = findViewById(R.id.buttonSalidaCirculacion);
        Button busquedaCar = findViewById(R.id.buttonBusquedaCirculacion);

        String[] puertas = {"Principal", "Sur", "Angamos"};

        //when the image is clicked, call a method
        ingresoCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IngresoVehiculo(puertas);
            }
        });

        //when the image is clicked, call a method
        salidaCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalidaVehiculo(puertas);
            }
        });

        //when the image is clicked, call a method
        busquedaCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusquedaCar();
            }
        });

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        //write parameters
        //the parameters are defined and linked with their respective ids
        TextView globalActivo = (TextView) findViewById(R.id.globalIngreso);
        TextView principal = (TextView) findViewById(R.id.puertaPrincipal);
        TextView sur = (TextView) findViewById(R.id.puertaSur);
        TextView angamos = (TextView) findViewById(R.id.puertaAngamos);

        //we count a vehiculos when pass under the gates
        int globalA = contratosPrx.vehiculosInterior(1);
        int principalG = contratosPrx.vehiculosGate("Principal");
        int surG = contratosPrx.vehiculosGate("Sur");
        int angamosG = contratosPrx.vehiculosGate("Angamos");

        //and set his values to the counts
        globalActivo.setText(String.valueOf(globalA));
        principal.setText(String.valueOf(principalG));
        sur.setText(String.valueOf(surG));
        angamos.setText(String.valueOf(angamosG));
    }

    /**
     * IngresoVehiculo
     *
     * @param puertas
     */
    private void IngresoVehiculo(String[] puertas) {
        //we create a dialog and then inflate it with a view called CarPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View CarPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_entry, null);

        //the parameters are defined and linked with their respective ids
        rPatente = (EditText) CarPopUpView.findViewById(R.id.rPatente);
        rObservacion = (EditText) CarPopUpView.findViewById(R.id.rObservacion);
        tTipoLogo = (TextView) CarPopUpView.findViewById(R.id.existe);
        spinner = (Spinner) CarPopUpView.findViewById(R.id.spinner);

        //we create a spinner using a field puertas[]
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, puertas);
        spinner.setAdapter(adapter);

        //we link the buttons with their respective ids
        saveVehiculo = (Button) CarPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) CarPopUpView.findViewById(R.id.cancel);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(CarPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        try {
            //if the return of obtenerVehiculo is null, we set a mssg
            if (theSystemPrx.obtenerVehiculo(rPatente.getText().toString()) == null) {
                tTipoLogo.setText("No está registrado");
            } else {
                tTipoLogo.setText(theSystemPrx.obtenerVehiculo(rPatente.getText().toString()).codigoLogo); // Entrega el Codigo del Logo
            }
        } catch (Exception exception) {
            logger.error("Error: " + exception);
        }

        //when the button saveVehiculo on cliked
        saveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we chose the selection in the spinner
                String seleccion = spinner.getSelectedItem().toString();
                try {
                    //And create a circulacion and added a vehiculo
                    Circulacion circulacion = contratosPrx.ingresoVehiculo(rPatente.getText().toString(),
                            seleccion, rObservacion.getText().toString());
                    Toast.makeText(CirculacionUCN.this, "Vehiculo Ingresado: " + circulacion.patente, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } catch (Exception exception) {
                    logger.error("Error: " + exception);
                }
            }
        });

        //when the button cancel on clicked, the dialog is closed
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    /**
     * SalidaVehiculo
     *
     * @param puertas
     */
    private void SalidaVehiculo(String[] puertas) {
        //we create a dialog and then inflate it with a view called CarPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View CarPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_entry, null);

        //the parameters are defined and linked with their respective ids
        rPatente = (EditText) CarPopUpView.findViewById(R.id.rPatente);
        rObservacion = (EditText) CarPopUpView.findViewById(R.id.rObservacion);
        tTipoLogo = (TextView) CarPopUpView.findViewById(R.id.existe);
        spinner = (Spinner) CarPopUpView.findViewById(R.id.spinner);

        //we create a spinner using a field puertas[]
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, puertas);
        spinner.setAdapter(adapter);

        //we link the buttons with their respective ids
        saveVehiculo = (Button) CarPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) CarPopUpView.findViewById(R.id.cancel);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(CarPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        try {
            //if the return of obtenerVehiculo is null, we set a mssg
            if (theSystemPrx.obtenerVehiculo(rPatente.getText().toString()) == null) {
                tTipoLogo.setText("No está registrado");
            } else {
                tTipoLogo.setText(theSystemPrx.obtenerVehiculo(rPatente.getText().toString()).codigoLogo); // Entrega el Codigo del Logo
            }
        } catch (Exception exception) {
            logger.error("Error: " + exception);
        }

        //when the button saveVehiculo on cliked
        saveVehiculo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String seleccion = spinner.getSelectedItem().toString();
                try {
                    //And create a circulacion and added a vehiculo
                    Circulacion circulacion = contratosPrx.salidaVehiculo(rPatente.getText().toString(),
                            seleccion);
                    Toast.makeText(CirculacionUCN.this, "Vehiculo Saliendo: " + circulacion.patente, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                } catch (Exception exception) {
                    logger.error("Error: " + exception);
                    Toast.makeText(CirculacionUCN.this, "Vehiculo ya salió", Toast.LENGTH_LONG).show();
                }
            }
        });

        //when the button cancel on clicked, the dialog is closed
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    /**
     * BusquedaCar
     */
    private void BusquedaCar() {
        //we set a view
        setContentView(R.layout.activity_vehiculo_search);
        //we linked to button and edittext on his ids
        EditText patentefind = (EditText) findViewById(R.id.patenteseleccionada);
        Button findbutton = (Button) findViewById(R.id.Buscar);

        //and clicked on button
        findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //called a method
                findvehiculo(patentefind.getText().toString());
            }
        });

    }

    /**
     * findvehiculo
     *
     * @param patentefind
     */
    private void findvehiculo(String patentefind) {

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        //the parameters are defined and linked with their respective ids
        fIngreso = (TextView) findViewById(R.id.fechadato);
        hIngreso = (TextView) findViewById(R.id.horadato);
        tPatente = (TextView) findViewById(R.id.tPatente);
        tPuertaIngreso = (TextView) findViewById(R.id.tPuertaIngreso);
        tObservacion = (TextView) findViewById(R.id.tObservacion);
        tEstado = (TextView) findViewById(R.id.tEstado);

        //if I enter data that is not empty and then, if the circulation that returns
        // "busquedaVehiculoBackend" is distinguished to null. The vehicle you are looking for may be
        // displayed, which must prove that it exists and is at the university
        if (!patentefind.equals("")) {

            Circulacion circulacion = contratosPrx.busquedaVehiculoBackend(patentefind);

            try {

                if (circulacion != null) {

                    fIngreso.setText(circulacion.fechaIngreso);
                    hIngreso.setText(circulacion.horaIngreso);
                    tPatente.setText(circulacion.patente);
                    tPuertaIngreso.setText(circulacion.puertaEntrada);
                    tObservacion.setText(circulacion.observacion);
                    tEstado.setText("SE ENCUENTRA EN LA UCN ");

                } else {
                    fIngreso.setText("");
                    hIngreso.setText("");
                    tPatente.setText("");
                    tObservacion.setText("");
                    tPuertaIngreso.setText("");
                    tEstado.setText("NO SE ENCUENTRA EN LA UCN");
                }

            } catch (Exception exception) {
                logger.error("Error: " + exception);
                Toast.makeText(CirculacionUCN.this, "Vehiculo ya salió", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CirculacionUCN.this, "Patente invalida", Toast.LENGTH_LONG).show();
        }

    }

}
