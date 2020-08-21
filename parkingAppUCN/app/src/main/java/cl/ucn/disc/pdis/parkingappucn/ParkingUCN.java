package cl.ucn.disc.pdis.parkingappucn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;

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

        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        TextView globalAnfta =(TextView) findViewById(R.id.AntofaNumber);
        TextView globalCoquimbo =(TextView) findViewById(R.id.CoquimboNumber);
        TextView numb1 = (TextView) findViewById(R.id.Number1);
        TextView numb2 = (TextView) findViewById(R.id.Number2);
        TextView numb3 = (TextView) findViewById(R.id.Number3);
        TextView numb4 = (TextView) findViewById(R.id.Number4);
        TextView numb5 = (TextView) findViewById(R.id.Number5);
        TextView numb6 = (TextView) findViewById(R.id.Number6);

        int datoAfta = contratosPrx.totalRegion("Antofagasta - Chile");
        int datoCoq = contratosPrx.totalRegion("Coquimbo - Chile");
        int n1 =  contratosPrx.datosEstadisticos("Facultal de Ingenieria y Ciencias Geológicas");
        int n2 = contratosPrx.datosEstadisticos("Facultad de Ciencias de Ingeniería y Construcción");
        int n3 =  contratosPrx.datosEstadisticos("Facultad de Ciencias del Mar");
        int n4 = contratosPrx.datosEstadisticos("Facultad de Ciencias");
        int n5 =  contratosPrx.datosEstadisticos("Facultad de Humanidades");
        int n6 = contratosPrx.datosEstadisticos("Facultad de Economía y Administración");


        globalAnfta.setText(String.valueOf(datoAfta));
        globalCoquimbo.setText(String.valueOf(datoCoq));
        numb1.setText(String.valueOf(n1));
        numb2.setText(String.valueOf(n2));
        numb3.setText(String.valueOf(n3));
        numb4.setText(String.valueOf(n4));
        numb5.setText(String.valueOf(n5));
        numb6.setText(String.valueOf(n6));
    }
}
