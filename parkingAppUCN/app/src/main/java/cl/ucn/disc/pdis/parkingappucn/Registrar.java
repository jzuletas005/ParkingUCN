package cl.ucn.disc.pdis.parkingappucn;

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

import cl.ucn.disc.pdis.scrapper.zeroice.model.Persona;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Sexo;
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

    private EditText rPatente, rCodigoLogo, rMarca, rModelo, rAnio, rObservacion, rResponsable;

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
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_register, null);

        rPatente = (EditText) PersonaPopUpView.findViewById(R.id.rPatente);
        rCodigoLogo = (EditText) PersonaPopUpView.findViewById(R.id.rCodigoLogo);
        rMarca = (EditText) PersonaPopUpView.findViewById(R.id.rMarca);
        rModelo = (EditText) PersonaPopUpView.findViewById(R.id.rModelo);
        rAnio = (EditText) PersonaPopUpView.findViewById(R.id.rAnio);
        rObservacion = (EditText) PersonaPopUpView.findViewById(R.id.rObservacion);
        rResponsable = (EditText) PersonaPopUpView.findViewById(R.id.rResponsable);


        saveVehiculo = (Button) PersonaPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        saveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo v = new Vehiculo(rPatente.getText().toString(),
                        rCodigoLogo.getText().toString(),
                        rMarca.getText().toString(),
                        rModelo.getText().toString(), Integer.parseInt(rAnio.getText().toString()),
                        rObservacion.getText().toString(), rResponsable.getText().toString());

                //Busca si existe el vehiculo
                try {
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
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void registerPersonaDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_persona_register, null);

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

        savePersona = (Button) PersonaPopUpView.findViewById(R.id.savepersona);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        Sexo varSexo = Sexo.INDETERMINADO;

        if (rSexo.getText().toString().equalsIgnoreCase("Femenino")) {
            varSexo = Sexo.FEMENINO;
        }

        if (rSexo.getText().toString().equalsIgnoreCase("Masculino")) {
            varSexo = Sexo.MASCULINO;
        }

        final Sexo finalvarSexo = varSexo;

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        savePersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona p = new Persona(2, rNombre.getText().toString(),
                        rRut.getText().toString(), rCargo.getText().toString(),
                        rUnidad.getText().toString(), rDireccion.getText().toString(),
                        finalvarSexo, rTelefono.getText().toString(),
                        rOficina.getText().toString(), rEmail.getText().toString(),
                        rCountry.getText().toString());

                try {
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
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
