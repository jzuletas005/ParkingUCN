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


public class Eliminar  extends AppCompatActivity {

    public Bundle datos;

    private String patenteEliminar;

    private Integer numeroDelDestino;

    private Persona personaEliminar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        datos = getIntent().getExtras();
        personaEliminar = (Persona) datos.getSerializable("persona");
        patenteEliminar = datos.getString("patente");
        numeroDelDestino = datos.getInt("numero");


        if(numeroDelDestino == 0){
            deletePersonaThing(personaEliminar);
        }

        if(numeroDelDestino == 1){
            deleteVehiculoThing(patenteEliminar);
        }
    }

    public void deletePersonaThing(Persona persona){

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        theSystemPrx.eliminarVehiculo(persona.rut);

        Toast.makeText(Eliminar.this, "Persona eliminada : " +persona.nombre, Toast.LENGTH_LONG).show();
    }

    public void deleteVehiculoThing(String patente){

        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        theSystemPrx.eliminarVehiculo(patente);

        Toast.makeText(Eliminar.this, "Vehiculo eliminada : " + patente, Toast.LENGTH_LONG).show();
    }
}
