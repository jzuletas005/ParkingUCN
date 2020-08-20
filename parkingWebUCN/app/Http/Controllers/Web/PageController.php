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
}
