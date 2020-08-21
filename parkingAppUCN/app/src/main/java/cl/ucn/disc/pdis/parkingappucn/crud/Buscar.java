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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.ucn.disc.pdis.parkingappucn.R;
import cl.ucn.disc.pdis.parkingappucn.connection.ZeroIce;
import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Persona;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Vehiculo;

public class Buscar extends AppCompatActivity {

    /**
     * Logger
     */
    public static Logger logger = LoggerFactory.getLogger(Buscar.class);

    /**
     * AlertDialog.Builder
     */
    private AlertDialog.Builder dialogBuilder;

    /**
     * AlertDialog
     */
    private AlertDialog dialog;

    /**
     * TextView to Persona and Vehiculo
     */
    private TextView findnombre, findcargo, findunidad, findoficina, findpatente, findmodelo, findmarca, findcodigoLogo, findresponsable;

    /**
     * Button
     */
    private Button updatepersona, deletepersona, updatevehiculo, deletevehiculo;

    /**
     * OnCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //we link the EditText with their respective ids
        EditText find = findViewById(R.id.findText);

        //we link the image with their respective ids
        ImageView people = findViewById(R.id.busquedaPersona);
        ImageView car = findViewById(R.id.busquedaVehiculo);

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        //when the image is clicked, call a method
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //format the rut an search the persona
                String rutverificar = contratosPrx.formatearRut(find.getText().toString());
                logger.debug(rutverificar);
                obtenerPeople(rutverificar);
            }
        });

        //when the image is clicked, call a method
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerCar(find.getText().toString());
            }
        });

    }

    /**
     * obtenerCar
     * @param patente
     */
    private void obtenerCar(String patente) {

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        Vehiculo vehiculofind = theSystemPrx.obtenerVehiculo(patente);

        try {
            if(vehiculofind == null){

                Toast.makeText(Buscar.this, "Vehiculo no existe  ", Toast.LENGTH_LONG).show();

            }else {

                logger.debug("Patente: " + vehiculofind.patente);
                showVehiculoDialog(vehiculofind);
                Toast.makeText(Buscar.this, "Vehiculo encontrado: " + vehiculofind.patente, Toast.LENGTH_LONG).show();
            }

        }catch (Exception exception){
            logger.error("Error: "+exception);
        }
    }

    /**
     * obtenerPeople
     * @param rutpersona
     */
    public void obtenerPeople(String rutpersona) {

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        Persona personafind = theSystemPrx.obtenerPersona(rutpersona);

        try {
            if(personafind == null){

                Toast.makeText(Buscar.this, "Persona no existe  ", Toast.LENGTH_LONG).show();

            }else {

                logger.debug("Nombre: " + personafind.nombre);
                showPersonaDialog(personafind);
                Toast.makeText(Buscar.this, "Persona encontrado: " + personafind.nombre, Toast.LENGTH_LONG).show();
            }

        }catch (Exception exception){
            logger.error("Error: "+exception);
        }

    }

    /**
     * showPersonaDialog
     * @param personafind
     */
    public void showPersonaDialog(Persona personafind) {
        //we create a dialog and then inflate it with a view called PersonaPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_persona, null);

        //the parameters are defined and linked with their respective ids
        findnombre = (TextView) PersonaPopUpView.findViewById(R.id.findnombre);
        findcargo = (TextView) PersonaPopUpView.findViewById(R.id.findcargo);
        findunidad = (TextView) PersonaPopUpView.findViewById(R.id.findunidad);
        findoficina = (TextView) PersonaPopUpView.findViewById(R.id.findoficina);

        //we set a parameters on personafind
        findnombre.setText(personafind.nombre);
        findcargo.setText(personafind.wposition);
        findunidad.setText(personafind.unit);
        findoficina.setText(personafind.oficina);

        //we link the buttons with their respective ids
        updatepersona = (Button) PersonaPopUpView.findViewById(R.id.updatepersona);
        deletepersona = (Button) PersonaPopUpView.findViewById(R.id.deletepersona);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //when the button updatepersona on cliked
        updatepersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //We create a bundle to send a persona
                Bundle personabundle = new Bundle();
                personabundle.putSerializable("persona",personafind);

                //we initialize a Intent to send parameters and move to another activity
                Intent intent = new Intent(Buscar.this, Actualizar.class);
                intent.putExtras(personabundle);
                intent.putExtra("numero",0);
                startActivity(intent);
            }
        });

        //when the button deletepersona on cliked
        deletepersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //We create a bundle to send a persona
                Bundle personabundle = new Bundle();
                personabundle.putSerializable("persona",personafind);

                //we initialize a Intent to send parameters and move to another activity
                Intent intent = new Intent(Buscar.this, Eliminar.class);
                intent.putExtras(personabundle);
                intent.putExtra("numero",0);
                startActivity(intent);
            }
        });

    }

    /**
     * showVehiculoDialog
     * @param vehiculofind
     */
    public void showVehiculoDialog(Vehiculo vehiculofind) {

        //we create a dialog and then inflate it with a view called PersonaPopUpView
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo, null);

        //the parameters are defined and linked with their respective ids
        findpatente = (TextView) PersonaPopUpView.findViewById(R.id.findpatente);
        findcodigoLogo = (TextView) PersonaPopUpView.findViewById(R.id.findcodigoLogo);
        findmarca = (TextView) PersonaPopUpView.findViewById(R.id.findmarca);
        findmodelo = (TextView) PersonaPopUpView.findViewById(R.id.findmodelo);
        findresponsable = (TextView) PersonaPopUpView.findViewById(R.id.findresponsable);

        //we set a parameters on personafind
        findpatente.setText(vehiculofind.patente);
        findcodigoLogo.setText(vehiculofind.codigoLogo);
        findmarca.setText(vehiculofind.marca);
        findmodelo.setText(vehiculofind.modelo);
        findresponsable.setText(vehiculofind.responsable);

        //we link the buttons with their respective ids
        updatevehiculo = (Button) PersonaPopUpView.findViewById(R.id.updatevehiculo);
        deletevehiculo = (Button) PersonaPopUpView.findViewById(R.id.deletevehiculo);

        //using dialogBuilder we set the view on a new layout
        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //when the button updatepersona on cliked
        updatevehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //We create a bundle to send a persona
                Bundle personabundle = new Bundle();
                personabundle.putSerializable("vehiculo",vehiculofind);

                //we initialize a Intent to send parameters and move to another activity
                Intent intent = new Intent(Buscar.this, Actualizar.class);
                intent.putExtras(personabundle);
                intent.putExtra("numero",1);
                startActivity(intent);
            }
        });

        //when the button deletepersona on cliked
        deletevehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //we initialize a Intent to send parameters and move to another activity
                Intent intent = new Intent(Buscar.this, Eliminar.class);
                intent.putExtra("patente", vehiculofind.patente);
                intent.putExtra("numero",1);
                startActivity(intent);
            }
        });

    }

    /**
     * close
     * @param v
     */
    public void close(View v) {
        dialog.dismiss();
    }
}
