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
        return view('buscarPersonas');
    }

    public function crearPersona(){
        return view('crearPersonas');
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
}
