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

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //TODO crear botones de editar y borrar además de cerrar el dialog
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

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        //TODO crear botones de editar y borrar además de cerrar el dialog
    }
}
