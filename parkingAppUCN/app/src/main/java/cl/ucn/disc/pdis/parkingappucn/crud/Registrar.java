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
package cl.ucn.disc.pdis.parkingappucn.crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parkingappucn.R;
import cl.ucn.disc.pdis.parkingappucn.connection.ZeroIce;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Persona;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Vehiculo;

public class Registrar extends AppCompatActivity {

    /**
     * Logger
     */
    public static Logger logger = LoggerFactory.getLogger(Registrar.class);

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog dialog;

    private EditText rNombre, rRut, rSexo, rCargo, rUnidad, rEmail, rTelefono, rOficina, rDireccion, rCountry;

    private EditText rPatente, rCodigoLogo, rMarca, rModelo, rAnio, rObservacion, rResponsable, rTipoLogo;

    private Button savePersona, saveVehiculo, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        ImageView people = findViewById(R.id.registroPersona);
        ImageView car = findViewById(R.id.registroVehiculo);

        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerPersonaDialog();
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCarDialog();
            }
        });

    }

    public void registerCarDialog() {
        //we create a dialog and then inflate it with a view called PersonaPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_register, null);

        //the parameters are defined and linked with their respective ids
        rPatente = (EditText) PersonaPopUpView.findViewById(R.id.rPatente);
        rCodigoLogo = (EditText) PersonaPopUpView.findViewById(R.id.rCodigoLogo);
        rMarca = (EditText) PersonaPopUpView.findViewById(R.id.rMarca);
        rModelo = (EditText) PersonaPopUpView.findViewById(R.id.rModelo);
        rAnio = (EditText) PersonaPopUpView.findViewById(R.id.rAnio);
        rObservacion = (EditText) PersonaPopUpView.findViewById(R.id.rObservacion);
        rResponsable = (EditText) PersonaPopUpView.findViewById(R.id.rResponsable);
        rTipoLogo = (EditText) PersonaPopUpView.findViewById(R.id.rTipoLogo);

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

        //when the button saveVehiculo on cliked
        saveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo v;
                //we make sure to validate that we do not receive a null or empty data
                if(!rPatente.getText().toString().equals("") || !rAnio.getText().toString().equals("")){
                     v = new Vehiculo(rPatente.getText().toString(),
                            rCodigoLogo.getText().toString(),
                            rMarca.getText().toString(),
                            rModelo.getText().toString(), Integer.parseInt(rAnio.getText().toString()),
                            rObservacion.getText().toString(), rResponsable.getText().toString(), rTipoLogo.getText().toString());

                    try {
                        //We search if the new vehiculo is in the db
                        if (theSystemPrx.obtenerVehiculo(v.patente) == null) {
                            theSystemPrx.registrarVehiculo(v);
                            Toast.makeText(Registrar.this, "Vehiculo registrado : " + v.patente, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registrar.this, "Vehiculo ya existe : " + v.patente, Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    } catch (Exception ex) {
                        logger.error("Error: " + ex);
                    }
                }else {
                    Toast.makeText(Registrar.this, "Patente o Año del Vehiculo invalido ", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });

        //when the button cancel on cliked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void registerPersonaDialog() {
        //we create a dialog and then inflate it with a view called PersonaPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_persona_register, null);

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

        //we link the buttons with their respective ids
        savePersona = (Button) PersonaPopUpView.findViewById(R.id.savepersona);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        String varSexo = "INDETERMINADO";

        if (rSexo.getText().toString().equalsIgnoreCase("Femenino")) {
            varSexo = "FEMENINO";
        }

        if (rSexo.getText().toString().equalsIgnoreCase("Masculino")) {
            varSexo = "MASCULINO";
        }

        final String finalvarSexo = varSexo;

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        //when the button savePersona on cliked
        savePersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona p;
                //we make sure to validate that we do not receive a null or empty data
                if(!rRut.getText().toString().equals("") || !rNombre.getText().toString().equals("")){

                    p = new Persona(2, rNombre.getText().toString(),
                            rRut.getText().toString(), rCargo.getText().toString(),
                            rUnidad.getText().toString(), rDireccion.getText().toString(),
                            finalvarSexo, rTelefono.getText().toString(),
                            rOficina.getText().toString(), rEmail.getText().toString(),
                            rCountry.getText().toString());

                    try {
                        //We search if the new persona is in the db
                        if (theSystemPrx.obtenerPersona(p.rut) == null) {
                            theSystemPrx.registrarPersona(p);
                            Toast.makeText(Registrar.this, "Persona registrado : " + p.nombre, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Registrar.this, "Persona ya existe : " + p.nombre, Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    } catch (Exception ex) {
                        logger.error("Error: " + ex);
                    }
                }else {
                    Toast.makeText(Registrar.this, "Nombre o Rut de la Persona invalido", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });

        //when the button cancel on cliked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
