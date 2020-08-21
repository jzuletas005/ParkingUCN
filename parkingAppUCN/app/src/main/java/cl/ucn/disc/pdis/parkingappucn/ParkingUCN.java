/*
 * MIT License
 *
 *  Copyright (c) 2020 Javier Zuleta Silva, Beatriz Alvarez Rojas, Gonzalo Nieto Berrios
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cl.ucn.disc.pdis.parkingappucn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cl.ucn.disc.pdis.parkingappucn.connection.ZeroIce;
import cl.ucn.disc.pdis.parkingappucn.crud.Buscar;
import cl.ucn.disc.pdis.parkingappucn.crud.Registrar;
import cl.ucn.disc.pdis.scrapper.zeroice.model.ContratosPrx;

public class ParkingUCN extends AppCompatActivity {

    /**
     * onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        //we link the buttons with their respective ids
        Button bucarbtn = findViewById(R.id.Buscar);
        Button registrarbtn = findViewById(R.id.Registrar);

        //transition on search or register
        uiTransition(bucarbtn,registrarbtn);

    }

    /**
     * uiTransition
     * @param bucarbtn
     * @param registrarbtn
     */
    private void uiTransition(Button bucarbtn, Button registrarbtn) {

        //when clicked buscarbtn we move on Buscar.class
        bucarbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingUCN.this, Buscar.class));
            }
        });

        //when clicked registrarbtn we move on Registrar.class
        registrarbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingUCN.this, Registrar.class));
            }
        });

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        ContratosPrx contratosPrx = ice.getContratos();

        //this provides a communication with their respective ids
        TextView globalAnfta =(TextView) findViewById(R.id.AntofaNumber);
        TextView globalCoquimbo =(TextView) findViewById(R.id.CoquimboNumber);
        TextView numb1 = (TextView) findViewById(R.id.Number1);
        TextView numb2 = (TextView) findViewById(R.id.Number2);
        TextView numb3 = (TextView) findViewById(R.id.Number3);
        TextView numb4 = (TextView) findViewById(R.id.Number4);
        TextView numb5 = (TextView) findViewById(R.id.Number5);
        TextView numb6 = (TextView) findViewById(R.id.Number6);

        //perform a count as needed
        int datoAfta = contratosPrx.totalRegion("Antofagasta - Chile");
        int datoCoq = contratosPrx.totalRegion("Coquimbo - Chile");
        int n1 =  contratosPrx.datosEstadisticos("Facultad de Ingeniería y Ciencias Geológicas");
        int n2 = contratosPrx.datosEstadisticos("Facultad de Ciencias de Ingeniería y Construcción");
        int n3 =  contratosPrx.datosEstadisticos("Facultad de Ciencias del Mar");
        int n4 = contratosPrx.datosEstadisticos("Facultad de Ciencias");
        int n5 =  contratosPrx.datosEstadisticos("Facultad de Humanidades");
        int n6 = contratosPrx.datosEstadisticos("Facultad de Economía y Administración");

        //shows and orders the data obtained
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
