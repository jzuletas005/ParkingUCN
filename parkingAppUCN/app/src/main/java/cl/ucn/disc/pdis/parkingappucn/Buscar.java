package cl.ucn.disc.pdis.parkingappucn;

import android.content.Intent;
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

public class Buscar extends AppCompatActivity {

    /**
     * Logger
     */
    public static Logger logger = LoggerFactory.getLogger(Buscar.class);

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog dialog;

    private TextView findnombre, findcargo, findunidad, findoficina, findpatente, findmodelo, findmarca, findcodigoLogo, findresponsable;

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

        Button botonBuscar = findViewById(R.id.botonBuscar);
        RadioGroup seleccion = findViewById(R.id.selection);
        EditText dato = findViewById(R.id.findText);


        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seleccion.getCheckedRadioButtonId() == R.id.botonPatente) {
                    obtenerCar(dato.getText().toString());
                }

                if (seleccion.getCheckedRadioButtonId() == R.id.botonRut) {
                    obtenerPeople(dato.getText().toString());
                }
            }
        });
    }

    /**
     * @param patente
     */
    private void obtenerCar(String patente) {

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        Vehiculo vehiculofind = theSystemPrx.obtenerVehiculo(patente);

        logger.debug("Patente: {} " + vehiculofind.patente);

        showVehiculoDialog(vehiculofind);
    }

    /**
     * @param rutpersona
     */
    public void obtenerPeople(String rutpersona) {

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        Persona personafind = theSystemPrx.obtenerPersona(rutpersona); //TODO agregar que es rut con punto y guión

        logger.debug("Nombre: {} " + personafind.nombre);

        showPersonaDialog(personafind);

    }

    /**
     * @param personafind
     */
    public void showPersonaDialog(Persona personafind) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_persona, null);

        findnombre = (TextView) PersonaPopUpView.findViewById(R.id.findnombre);
        findcargo = (TextView) PersonaPopUpView.findViewById(R.id.findcargo);
        findunidad = (TextView) PersonaPopUpView.findViewById(R.id.findunidad);
        findoficina = (TextView) PersonaPopUpView.findViewById(R.id.findoficina);

        findnombre.setText(personafind.nombre);
        findcargo.setText(personafind.wposition);
        findunidad.setText(personafind.unit);
        findoficina.setText(personafind.oficina);

        updatepersona = (Button) PersonaPopUpView.findViewById(R.id.updatepersona);
        deletepersona = (Button) PersonaPopUpView.findViewById(R.id.deletepersona);

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        updatepersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle personabundle = new Bundle();
                personabundle.putSerializable("persona",personafind);

                Intent intent = new Intent(Buscar.this, Actualizar.class);
                intent.putExtras(personabundle);
                intent.putExtra("numero",0);
                startActivity(intent);
            }
        });

        deletepersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle personabundle = new Bundle();
                personabundle.putSerializable("persona",personafind);

                Intent intent = new Intent(Buscar.this, Eliminar.class);
                intent.putExtras(personabundle);
                intent.putExtra("numero",0);
                startActivity(intent);
            }
        });

    }

    /**
     * @param vehiculofind
     */
    public void showVehiculoDialog(Vehiculo vehiculofind) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo, null);

        findpatente = (TextView) PersonaPopUpView.findViewById(R.id.findpatente);
        findcodigoLogo = (TextView) PersonaPopUpView.findViewById(R.id.findcodigoLogo);
        findmarca = (TextView) PersonaPopUpView.findViewById(R.id.findmarca);
        findmodelo = (TextView) PersonaPopUpView.findViewById(R.id.findmodelo);
        findresponsable = (TextView) PersonaPopUpView.findViewById(R.id.findresponsable);

        findpatente.setText(vehiculofind.patente);
        findcodigoLogo.setText(vehiculofind.codigoLogo);
        findmarca.setText(vehiculofind.marca);
        findmodelo.setText(vehiculofind.modelo);
        findresponsable.setText(vehiculofind.responsable);

        updatevehiculo = (Button) PersonaPopUpView.findViewById(R.id.updatevehiculo);
        deletevehiculo = (Button) PersonaPopUpView.findViewById(R.id.deletevehiculo);

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        updatevehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle personabundle = new Bundle();
                personabundle.putSerializable("vehiculo",vehiculofind);

                Intent intent = new Intent(Buscar.this, Actualizar.class);
                intent.putExtras(personabundle);
                intent.putExtra("numero",1);
                startActivity(intent);
            }
        });

        deletevehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Buscar.this, Eliminar.class);
                intent.putExtra("patente", vehiculofind.patente);
                intent.putExtra("numero",1);
                startActivity(intent);
            }
        });

    }

    /**
     *
     * @param v
     */
    public void close(View v) {
        dialog.dismiss();
    }
}
