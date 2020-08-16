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
import cl.ucn.disc.pdis.scrapper.zeroice.model.Sexo;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Vehiculo;

public class Actualizar  extends AppCompatActivity {

    public static Logger logger = LoggerFactory.getLogger(Actualizar.class);

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog dialog;

    public Bundle datos;

    private Vehiculo vehiculoActualizar;

    private Integer numeroDelDestino;

    private Persona personaActualizar;

    private EditText rNombre, rRut, rSexo, rCargo, rUnidad, rEmail, rTelefono, rOficina, rDireccion, rCountry;

    private EditText rPatente, rCodigoLogo, rMarca, rModelo, rAnio, rObservacion, rResponsable;

    private TextView tNombre, tRut, tSexo, tCargo, tUnidad, tEmail, tTelefono, tOficina, tDireccion, tCountry;

    private TextView tPatente, tCodigoLogo, tMarca, tModelo, tAnio, tObservacion, tResponsable;

    private Button savePersona, saveVehiculo, cancel;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        datos = getIntent().getExtras();
        personaActualizar = (Persona) datos.getSerializable("persona");
        vehiculoActualizar = (Vehiculo) datos.getSerializable("vehiculo");
        numeroDelDestino = datos.getInt("numero");


        if(numeroDelDestino == 0){
            updatePersonaThing(personaActualizar);
        }

        if(numeroDelDestino == 1){
            updateVehiculoThing(vehiculoActualizar);
        }
    }

    public void updateVehiculoThing(Vehiculo vehiculoActualizar) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_vehiculo_update, null);

        rPatente = (EditText) PersonaPopUpView.findViewById(R.id.rPatente);
        rCodigoLogo = (EditText) PersonaPopUpView.findViewById(R.id.rCodigoLogo);
        rMarca = (EditText) PersonaPopUpView.findViewById(R.id.rMarca);
        rModelo = (EditText) PersonaPopUpView.findViewById(R.id.rModelo);
        rAnio = (EditText) PersonaPopUpView.findViewById(R.id.rAnio);
        rObservacion = (EditText) PersonaPopUpView.findViewById(R.id.rObservacion);
        rResponsable = (EditText) PersonaPopUpView.findViewById(R.id.rResponsable);


        tPatente = (TextView) PersonaPopUpView.findViewById(R.id.tPatente);
        tCodigoLogo = (TextView) PersonaPopUpView.findViewById(R.id.tCodigoLogo);
        tMarca = (TextView) PersonaPopUpView.findViewById(R.id.tMarca);
        tModelo = (TextView) PersonaPopUpView.findViewById(R.id.tModelo);
        tAnio = (TextView) PersonaPopUpView.findViewById(R.id.tAnio);
        tObservacion = (TextView) PersonaPopUpView.findViewById(R.id.tObservacion);
        tResponsable = (TextView) PersonaPopUpView.findViewById(R.id.tResponsable);


        tPatente.setText(vehiculoActualizar.patente);
        tCodigoLogo.setText(vehiculoActualizar.codigoLogo);
        tMarca.setText(vehiculoActualizar.marca);
        tModelo.setText(vehiculoActualizar.modelo);
        tAnio.setText(String.valueOf(vehiculoActualizar.anio));
        tObservacion.setText(vehiculoActualizar.observacion);
        tResponsable.setText(vehiculoActualizar.responsable);


        saveVehiculo = (Button) PersonaPopUpView.findViewById(R.id.savevehiculo);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        Vehiculo vtrue = new Vehiculo();

        if(rPatente.getText().toString().matches("")){vtrue.patente = vehiculoActualizar.patente;}
        if(rCodigoLogo.getText().toString().matches("")){vtrue.codigoLogo = vehiculoActualizar.codigoLogo;}
        if(rMarca.getText().toString().matches("")){vtrue.marca = vehiculoActualizar.marca;}
        if(rModelo.getText().toString().matches("")){vtrue.modelo = vehiculoActualizar.modelo;}
        if(rAnio.getText().toString().matches("")){vtrue.anio = vehiculoActualizar.anio;}
        if(rObservacion.getText().toString().matches("")){vtrue.observacion = vehiculoActualizar.observacion;}
        if(rResponsable.getText().toString().matches("")){vtrue.responsable = vehiculoActualizar.responsable;}

        saveVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehiculo v = new Vehiculo(vtrue.patente,
                        vtrue.codigoLogo,vtrue.marca,vtrue.modelo,vtrue.anio,
                        vtrue.observacion,vtrue.responsable);
                try {
                    if(v!= null) {
                        theSystemPrx.editarVehiculo(v);
                        Toast.makeText(Actualizar.this, "Vehiculo modificada : " + vehiculoActualizar.patente, Toast.LENGTH_LONG).show();
                    }
                    dialog.dismiss();
                }catch (Exception exception){
                    logger.error("Error: "+exception);
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

    public void updatePersonaThing(Persona personaActualizar) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View PersonaPopUpView = getLayoutInflater().inflate(R.layout.popup_persona_update, null);

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

        Sexo varSexo = Sexo.INDETERMINADO;

        if (rSexo.getText().toString().equalsIgnoreCase("Femenino")) {
            varSexo = Sexo.FEMENINO;
        }

        if (rSexo.getText().toString().equalsIgnoreCase("Masculino")) {
            varSexo = Sexo.MASCULINO;
        }

        final Sexo finalvarSexo = varSexo;

        tNombre.setText(personaActualizar.nombre);
        tRut.setText(personaActualizar.rut);
        tSexo.setText(finalvarSexo.toString());
        tCargo.setText(personaActualizar.wposition);
        tUnidad.setText(personaActualizar.unit);
        tEmail.setText(personaActualizar.email);
        tTelefono.setText(personaActualizar.telefono);
        tOficina.setText(personaActualizar.oficina);
        tDireccion.setText(personaActualizar.direccion);
        tCountry.setText(personaActualizar.country);

        savePersona = (Button) PersonaPopUpView.findViewById(R.id.savepersona);
        cancel = (Button) PersonaPopUpView.findViewById(R.id.cancel);

        dialogBuilder.setView(PersonaPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();


        Persona ptrue = new Persona();

        if(rNombre.getText().toString().matches("")){ptrue.nombre = personaActualizar.nombre;}
        if(rRut.getText().toString().matches("")){ptrue.rut = personaActualizar.rut;}
        if(rCargo.getText().toString().matches("")){ptrue.wposition = personaActualizar.wposition;}
        if(rUnidad.getText().toString().matches("")){ptrue.unit = personaActualizar.unit;}
        if(rDireccion.getText().toString().matches("")){ptrue.direccion = personaActualizar.direccion;}
        if(rSexo.getText().toString().matches("")){ptrue.sexo = personaActualizar.sexo;}
        if(rTelefono.getText().toString().matches("")){ptrue.telefono = personaActualizar.telefono;}
        if(rOficina.getText().toString().matches("")){ptrue.oficina = personaActualizar.oficina;}
        if(rEmail.getText().toString().matches("")){ptrue.email = personaActualizar.email;}
        if(rCountry.getText().toString().matches("")){ptrue.country = personaActualizar.country;}

        savePersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona p = new Persona(0, ptrue.nombre, ptrue.rut, ptrue.wposition,
                        ptrue.unit, ptrue.direccion, ptrue.sexo, ptrue.telefono, ptrue.oficina,
                        ptrue.email, ptrue.country);

                try {
                    if(p != null){
                        theSystemPrx.editarPersona(p);
                        Toast.makeText(Actualizar.this, "Persona modificada : " + p.nombre, Toast.LENGTH_LONG).show();
                    }
                    dialog.dismiss();
                }catch (Exception ex){
                    logger.error("Error: "+ex);
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
