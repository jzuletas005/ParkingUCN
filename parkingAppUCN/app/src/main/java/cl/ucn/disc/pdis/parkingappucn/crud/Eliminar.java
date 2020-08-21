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
package cl.ucn.disc.pdis.parkingappucn.crud;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cl.ucn.disc.pdis.parkingappucn.R;
import cl.ucn.disc.pdis.parkingappucn.connection.ZeroIce;
import cl.ucn.disc.pdis.scrapper.zeroice.model.Persona;
import cl.ucn.disc.pdis.scrapper.zeroice.model.TheSystemPrx;


public class Eliminar  extends AppCompatActivity {

    /**
     * OnCreate
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //data that is received from the Intent
        Bundle datos = getIntent().getExtras();
        Persona personaEliminar = (Persona) datos.getSerializable("persona");
        String patenteEliminar = datos.getString("patente");
        int numeroDelDestino = datos.getInt("numero");

        //if numeroDelDestina is 0, we move to delete a persona
        if(numeroDelDestino == 0){
            deletePersonaThing(personaEliminar);
        }

        //if numeroDelDestina is 1, we move to delete a vehiculo
        if(numeroDelDestino == 1){
            deleteVehiculoThing(patenteEliminar);
        }
    }

    /**
     * deletePersonaThing
     * @param persona
     */
    public void deletePersonaThing(Persona persona){

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        //eliminated a persona
        theSystemPrx.eliminarPersona(persona.rut);
        Toast.makeText(Eliminar.this, "Persona eliminada : " +persona.nombre, Toast.LENGTH_LONG).show();
    }

    /**
     * deleteVehiculoThing
     * @param patente
     */
    public void deleteVehiculoThing(String patente){

        //call Zero Ice connections
        ZeroIce ice = new ZeroIce();
        ice.start();
        TheSystemPrx theSystemPrx = ice.getTheSystem();

        //eliminated a persona
        theSystemPrx.eliminarVehiculo(patente);
        Toast.makeText(Eliminar.this, "Vehiculo eliminada : " + patente, Toast.LENGTH_LONG).show();
    }
}
