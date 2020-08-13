package cl.ucn.disc.pdis.parkingappucn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingUCN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        Button bucarbtn = findViewById(R.id.Buscar);
        Button registrarbtn = findViewById(R.id.Registrar);

        uiTransition(bucarbtn,registrarbtn);

    }

    private void uiTransition(Button bucarbtn, Button registrarbtn) {

        bucarbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingUCN.this, Buscar.class));
            }
        });

        registrarbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingUCN.this, Registrar.class));
            }
        });
    }
}
