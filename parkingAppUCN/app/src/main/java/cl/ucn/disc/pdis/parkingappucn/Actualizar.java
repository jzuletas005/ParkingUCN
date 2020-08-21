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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.scrapper.zeroice.model.Persona;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Vehiculo;

public class Actualizar extends AppCompatActivity {

    /**
     * Logger
     */
    public static Logger logger = LoggerFactory.getLogger(Actualizar.class);

    /**
     * AlertDialog.Builder
     */
    private AlertDialog.Builder dialogBuilder;

    /**
     * AlertDialog
     */
    private AlertDialog dialog;

    /**
     * EditText to Persona
     */
    private EditText rNombre, rRut, rSexo, rCargo, rUnidad, rEmail, rTelefono, rOficina, rDireccion, rCountry;

    /**
     * EditText to Vehiculo
     */
    private EditText rPatente, rCodigoLogo, rMarca, rModelo, rAnio, rObservacion, rResponsable, rTipoLogo;

    /**
     * TextView to Persona
     */
    private TextView tNombre, tRut, tSexo, tCargo, tUnidad, tEmail, tTelefono, tOficina, tDireccion, tCountry;

    /**
     * TextView to Vehiculo
     */
    private TextView tPatente, tCodigoLogo, tMarca, tModelo, tAnio, tObservacion, tResponsable, tTipoLogo;

    /**
     * Button
     */
    private Button savePersona, saveVehiculo, cancel;

    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //data that is received from the Intent
        Bundle datos = getIntent().getExtras();
        Persona personaActualizar = (Persona) datos.getSerializable("persona");
        Vehiculo vehiculoActualizar = (Vehiculo) datos.getSerializable("vehiculo");
        int numeroDelDestino = datos.getInt("numero");

        //if numeroDel Destino is 0 go to update Persona
        if (numeroDelDestino == 0) {
            updatePersonaThing(personaActualizar);
        }
        //if numeroDel Destino is 0 go to update Vehiculo
        if (numeroDelDestino == 1) {
            updateVehiculoThing(vehiculoActualizar);
        }
    }

    /**
     * updateVehiculoThing
     *
     * @param vehiculoActualizar
     */
    public void updateVehiculoThing(Vehiculo vehiculoActualizar) {
        //we create a dialog and then inflate it with a view called PersonaPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_update, null);

        //Reading parameters
        //the parameters are defined and linked with their respective ids
        rPatente = (EditText) PersonaPopUpView.findViewById(R.id.rPatente);
        rCodigoLogo = (EditText) PersonaPopUpView.findViewById(R.id.rCodigoLogo);
        rMarca = (EditText) PersonaPopUpView.findViewById(R.id.rMarca);
        rModelo = (EditText) PersonaPopUpView.findViewById(R.id.rModelo);
        rAnio = (EditText) PersonaPopUpView.findViewById(R.id.rAnio);
        rObservacion = (EditText) PersonaPopUpView.findViewById(R.id.rObservacion);
        rResponsable = (EditText) PersonaPopUpView.findViewById(R.id.rResponsable);
        rTipoLogo = (EditText) PersonaPopUpView.findViewById(R.id.rTipoLogo);

        //write parameters
        //the parameters are defined and linked with their respective ids
        tPatente = (TextView) PersonaPopUpView.findViewById(R.id.tPatente);
        tCodigoLogo = (TextView) PersonaPopUpView.findViewById(R.id.tCodigoLogo);
        tMarca = (TextView) PersonaPopUpView.findViewById(R.id.tMarca);
        tModelo = (TextView) PersonaPopUpView.findViewById(R.id.tModelo);
        tAnio = (TextView) PersonaPopUpView.findViewById(R.id.tAnio);
        tObservacion = (TextView) PersonaPopUpView.findViewById(R.id.tObservacion);
        tResponsable = (TextView) PersonaPopUpView.findViewById(R.id.tResponsable);
        tTipoLogo = (TextView) PersonaPopUpView.findViewById(R.id.tTipoLogo);

        //using the write parameters, we change their values and give them the data that vehiculoActualizar provides us
        tPatente.setText(vehiculoActualizar.patente);
        tCodigoLogo.setText(vehiculoActualizar.codigoLogo);
        tMarca.setText(vehiculoActualizar.marca);
        tModelo.setText(vehiculoActualizar.modelo);
        tAnio.setText(String.valueOf(vehiculoActualizar.anio));
        tObservacion.setText(vehiculoActualizar.observacion);
        tResponsable.setText(vehiculoActualizar.responsable);
        tTipoLogo.setText(vehiculoActualizar.tipoLogo);

        //we link the buttons with their respective ids
        saveVehiculo = (Button) PersonaPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        Vehiculo vtrue = new Vehiculo();
        //if a new data is empty or black, it is thought that you do not need to change that data
        if (rPatente.getText().toString().matches("")) {
            vtrue.patente = vehiculoActualizar.patente;
        }
        if (rCodigoLogo.getText().toString().matches("")) {
            vtrue.codigoLogo = vehiculoActualizar.codigoLogo;
        }
        if (rMarca.getText().toString().matches("")) {
            vtrue.marca = vehiculoActualizar.marca;
        }
        if (rModelo.getText().toString().matches("")) {
            vtrue.modelo = vehiculoActualizar.modelo;
        }
        if (rAnio.getText().toString().matches("")) {
            vtrue.anio = vehiculoActualizar.anio;
        }
        if (rObservacion.getText().toString().matches("")) {
            vtrue.observacion = vehiculoActualizar.observacion;
        }
        if (rResponsable.getText().toString().matches("")) {
            vtrue.responsable = vehiculoActualizar.responsable;
        }
        if (rTipoLogo.getText().toString().matches("")) {
            vtrue.tipoLogo = vehiculoActualizar.tipoLogo;
        }

        //when the button saveVehiculo on cliked
        saveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //is create a vehiculo
                Vehiculo v = new Vehiculo(vtrue.patente,
                        vtrue.codigoLogo, vtrue.marca, vtrue.modelo, vtrue.anio,
                        vtrue.observacion, vtrue.responsable, vtrue.tipoLogo);
                try {
                    //and if the new vhicule isn't null, we update successfully
                    if (v != null) {
                        theSystemPrx.editarVehiculo(v);
                        Toast.makeText(Actualizar.this, "Vehiculo modificada : " + vehiculoActualizar.patente, Toast.LENGTH_LONG).show();
                    }
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
                dialog.dismiss();
            }
        });
    }

    /**
     * updatePersonaThing
     *
     * @param personaActualizar
     */
    public void updatePersonaThing(Persona personaActualizar) {
        //we create a dialog and then inflate it with a view called PersonaPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_persona_update, null);

        //Reading parameters
        //the parameters are defined and linked with their respective ids
        rNombre = (EditText) PersonaPopUpView.findViewById(R.id.rNombre);
        rRut = (EditText) PersonaPopUpView.findViewById(R.id.rRut);
        rSexo = (EditText) PersonaPopUpView.findViewById(R.id.rSexo);
        rCargo = (EditText) PersonaPopUpView.findViewById(R.id.rCargo);
        rUnidad = (EditText) PersonaPopUpView.findViewById(R.id.rUnidad);
        rEmail = (EditText) PersonaPopUpView.findViewById(R.id.rEmail);
        rTelefono = (EditText) PersonaPopUpView.findViewById(R.id.rTelefono);
        rOficina = (EditText) PersonaPopUpView.findViewById(R.id.rOficina);
        rDireccion = (EditText) PersonaPopUpView.findViewById(R.id.rDireccion);
        rCountry = (EditText) PersonaPopUpView.findViewById(R.id.rCountry);

        //write parameters
        //the parameters are defined and linked with their respective ids
        tNombre = (TextView) PersonaPopUpView.findViewById(R.id.tNombre);
        tRut = (TextView) PersonaPopUpView.findViewById(R.id.tRut);
        tSexo = (TextView) PersonaPopUpView.findViewById(R.id.tSexo);
        tCargo = (TextView) PersonaPopUpView.findViewById(R.id.tCargo);
        tUnidad = (TextView) PersonaPopUpView.findViewById(R.id.tUnidad);
        tEmail = (TextView) PersonaPopUpView.findViewById(R.id.tEmail);
        tTelefono = (TextView) PersonaPopUpView.findViewById(R.id.tTelefono);
        tOficina = (TextView) PersonaPopUpView.findViewById(R.id.tOficina);
        tDireccion = (TextView) PersonaPopUpView.findViewById(R.id.tDireccion);
        tCountry = (TextView) PersonaPopUpView.findViewById(R.id.tCountry);

        //we define the sex
        String varSexo = "INDETERMINADO";

        if (rSexo.getText().toString().equalsIgnoreCase("Femenino")) {
            varSexo = "FEMENINO";
        }

        if (rSexo.getText().toString().equalsIgnoreCase("Masculino")) {
            varSexo = "MASCULINO";
        }

        final String finalvarSexo = varSexo;

        //using the write parameters, we change their values and give them the data that personaActualizar provides us
        tNombre.setText(personaActualizar.nombre);
        tRut.setText(personaActualizar.rut);
        tSexo.setText(finalvarSexo);
        tCargo.setText(personaActualizar.wposition);
        tUnidad.setText(personaActualizar.unit);
        tEmail.setText(personaActualizar.email);
        tTelefono.setText(personaActualizar.telefono);
        tOficina.setText(personaActualizar.oficina);
        tDireccion.setText(personaActualizar.direccion);
        tCountry.setText(personaActualizar.country);

        //we link the buttons with their respective ids
        savePersona = (Button) PersonaPopUpView.findViewById(R.id.savepersona);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();


        Persona ptrue = new Persona();

        //if a new data is empty or black, it is thought that you do not need to change that data
        if (rNombre.getText().toString().matches("")) {
            ptrue.nombre = personaActualizar.nombre;
        }
        if (rRut.getText().toString().matches("")) {
            ptrue.rut = personaActualizar.rut;
        }
        if (rCargo.getText().toString().matches("")) {
            ptrue.wposition = personaActualizar.wposition;
        }
        if (rUnidad.getText().toString().matches("")) {
            ptrue.unit = personaActualizar.unit;
        }
        if (rDireccion.getText().toString().matches("")) {
            ptrue.direccion = personaActualizar.direccion;
        }
        if (rSexo.getText().toString().matches("")) {
            ptrue.sexo = personaActualizar.sexo;
        }
        if (rTelefono.getText().toString().matches("")) {
            ptrue.telefono = personaActualizar.telefono;
        }
        if (rOficina.getText().toString().matches("")) {
            ptrue.oficina = personaActualizar.oficina;
        }
        if (rEmail.getText().toString().matches("")) {
            ptrue.email = personaActualizar.email;
        }
        if (rCountry.getText().toString().matches("")) {
            ptrue.country = personaActualizar.country;
        }

        //when the button saveVehiculo on cliked
        savePersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a persona
                Persona p = new Persona(0, ptrue.nombre, ptrue.rut, ptrue.wposition,
                        ptrue.unit, ptrue.direccion, ptrue.sexo, ptrue.telefono, ptrue.oficina,
                        ptrue.email, ptrue.country);

                try {
                    //and if the new persona isn't null, we update successfully
                    if (p != null) {
                        theSystemPrx.editarPersona(p);
                        Toast.makeText(Actualizar.this, "Persona modificada : " + p.nombre, Toast.LENGTH_LONG).show();
                    }
                    dialog.dismiss();
                } catch (Exception ex) {
                    logger.error("Error: " + ex);
                }
            }
        });
        //when the button cancel on clicked, the dialog is closed
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
