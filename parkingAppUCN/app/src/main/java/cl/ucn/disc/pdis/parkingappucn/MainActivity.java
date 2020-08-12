package cl.ucn.disc.pdis.parkingappucn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button parking = findViewById(R.id.buttonParking);
        Button circulacion = findViewById(R.id.buttonCirculacion);
        
        uiTransition(parking, circulacion);
    }

    private void uiTransition(Button parking, Button circulacion) {

        parking.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ParkingUCN.class));
            }
        });

        circulacion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CirculacionUCN.class));
            }
        });
    }
}