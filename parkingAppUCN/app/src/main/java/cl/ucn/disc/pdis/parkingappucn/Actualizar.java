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

public class Actualizar  extends AppCompatActivity {

    public Bundle datos;

    private Vehiculo vehiculoActualizar;

    private Integer numeroDelDestino;

    private Persona personaActualizar;


    private EditText rNombre, rRut, rSexo, rCargo, rUnidad, rEmail, rTelefono, rOficina, rDireccion, rCountry;

    private EditText rPatente, rCodigoLogo, rMarca, rModelo, rAnio, rObservacion, rResponsable;

    private TextView tNombre, tRut, tSexo, tCargo, tUnidad, tEmail, tTelefono, tOficina, tDireccion, tCountry;

    private TextView tPatente, tCodigoLogo, tMarca, tModelo, tAnio, tObservacion, tResponsable;

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
        Toast.makeText(Actualizar.this, "Vehiculo modificada : " +vehiculoActualizar.patente, Toast.LENGTH_LONG).show();

    }

    public void updatePersonaThing(Persona personaActualizar) {
        Toast.makeText(Actualizar.this, "Persona modificada : " +personaActualizar.nombre, Toast.LENGTH_LONG).show();
    }
}
