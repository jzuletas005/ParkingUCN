package cl.ucn.disc.pdis.parkingappucn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Registrar extends AppCompatActivity {

    /**
     * Logger
     */
    public static Logger logger = LoggerFactory.getLogger(Registrar.class);

    private AlertDialog.Builder dialogBuilder;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

    }

    public void imgpeople(View v) {
        Toast notificacion2 = Toast.makeText(Registrar.this, "Esto es el PeopleButton", Toast.LENGTH_SHORT);
        notificacion2.show();
        //registerPersonaDialog();
    }

    public void imgcar(View v) {

        Toast notificacion2 = Toast.makeText(Registrar.this, "Esto es el VehiculoButton", Toast.LENGTH_SHORT);
        notificacion2.show();
        //registerCarDialog();
    }

    public void registerCarDialog() {

    }

    public void registerPersonaDialog() {

    }
}
