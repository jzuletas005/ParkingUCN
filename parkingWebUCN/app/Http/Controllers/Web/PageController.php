<?php

namespace App\Http\Controllers\Web;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class PageController extends Controller
{
    public function inicio(){
        return view('inicio');
    }

    public function buscarPersona(){
        return view('buscarPersona');
    }

    public function crearPersona(){
        return view('crearPersona');
    }

    public function editarPersona(Request $request){
        $rut = $request->get('rut');
        return view('editarPersona', ['rut' => $rut]);
    }

    public function persona(Request $request){
        $rut = $request->get('rut');
        return view('persona', ['rut' => $rut]);
    }

    public function creandoPersona(Request $request){
        $rut = $request->get('rut');
        $nombre = $request->get('nombre');
        $cargo = $request->get('cargo');
        $unidad = $request->get('unidad');
        $email = $request->get('email');
        $telefono = $request->get('telefono');
        $oficina = $request->get('oficina');
        $direccion = $request->get('direccion');
        $localidad = $request->get('localidad');
        $sexo = $request->get('sexo');

        return view('creandoPersona', ['rut' => $rut, 'nombre' => $nombre, 'cargo' => $cargo,
            'unidad' => $unidad, 'email' => $email, 'telefono' => $telefono, 'oficina' => $oficina,
            'direccion' => $direccion, 'localidad' => $localidad, 'sexo' => $sexo]);
    }

    public function personaEditada(Request $request){
        $rut = $request->get('rut');
        $cargo = $request->get('cargo');
        $unidad = $request->get('unidad');
        $email = $request->get('email');
        $telefono = $request->get('telefono');
        $oficina = $request->get('oficina');
        $direccion = $request->get('direccion');
        $localidad = $request->get('localidad');

        return view('personaEditada', ['rut' => $rut, 'cargo' => $cargo,
            'unidad' => $unidad, 'email' => $email, 'telefono' => $telefono, 'oficina' => $oficina,
            'direccion' => $direccion, 'localidad' => $localidad]);
    }

    public function eliminarPersona(Request $request){
        $rut = $request->get('rut');
        return view('personaEliminada', ['rut' => $rut]);
    }

    public function crearVehiculo(){
        return view('crearVehiculo');
    }

    public function creandoVehiculo(Request $request){
        $patente = $request->get('patente');
        $codigoLogo = $request->get('codigoLogo');
        $tipoLogo = $request->get('tipoLogo');
        $marca = $request->get('marca');
        $modelo = $request->get('modelo');
        $anio = $request->get('anio');
        $responsable = $request->get('responsable');
        $observacion = $request->get('observacion');

        return view('creandoVehiculo', ['patente' => $patente, 'codigoLogo' => $codigoLogo,
            'tipoLogo' => $tipoLogo, 'marca' => $marca, 'modelo' => $modelo, 'anio' => $anio,
            'responsable' => $responsable, 'observacion' => $observacion]);
    }

    public function buscarVehiculo(){
        return view('buscarVehiculo');
    }

    public function vehiculo(Request $request){
        $patente = $request->get('patente');
        return view('vehiculo', ['patente' => $patente]);
    }

    public function editarVehiculo(Request $request){
        $patente = $request->get('patente');
        return view('editarVehiculo', ['patente' => $patente]);
    }

    public function vehiculoEditado(Request $request){
        $patente = $request->get('patente');
        $codigoLogo = $request->get('codigoLogo');
        $tipoLogo = $request->get('tipoLogo');
        $marca = $request->get('marca');
        $modelo = $request->get('modelo');
        $anio = $request->get('anio');
        $responsable = $request->get('responsable');
        $observacion = $request->get('observacion');

        return view('vehiculoEditado', ['patente' => $patente, 'codigoLogo' => $codigoLogo,
            'tipoLogo' => $tipoLogo, 'marca' => $marca, 'modelo' => $modelo, 'anio' => $anio,
            'responsable' => $responsable, 'observacion' => $observacion]);
    }

    public function eliminarVehiculo(Request $request){
        $patente = $request->get('patente');
        return view('vehiculoEliminado', ['patente' => $patente]);
    }

    public function ingresoVehiculo(){
        return view('ingresoVehiculo');
    }

    public function ingresandoVehiculo(Request $request){
        $patente = $request->get('patente');
        $puertaEntrada = $request->get('puertaEntrada');
        $observacion = $request->get('observacion');
        return view('ingresandoVehiculo', ['patente' => $patente, 'puertaEntrada' => $puertaEntrada,
            'observacion' => $observacion]);
    }

    public function salidaVehiculo(){
        return view('salidaVehiculo');
    }

    public function saliendoVehiculo(Request $request){
        $patente = $request->get('patente');
        $puertaSalida = $request->get('puertaSalida');
        return view('saliendoVehiculo', ['patente' => $patente, 'puertaSalida' => $puertaSalida]);
    }


    public function estadoVehiculo(Request $request){
        $patente = $request->get('patente');
        return view('estadoVehiculo', ['patente' => $patente]);
    }

    public function vehiculosCirculando(){
        return view('vehiculosCirculando');
    }

    public function personasCampus(){
        return view('personasCampus');
    }

    public function contadorPersonasCampus(Request $request){
        $campus = $request->get('campus');
        return view('contadorPersonasCampus', ['campus' => $campus]);
    }
}
?>
<!--
MIT License

Copyright (c) 2020 Javier Zuleta Silva, Beatriz Alvarez Rojas, Gonzalo Nieto Berrios

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
-->
