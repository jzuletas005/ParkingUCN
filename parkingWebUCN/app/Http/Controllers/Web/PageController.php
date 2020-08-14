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

    public function persona(Request $request){
        $rut = $request->get('rut');
        return view('persona', ['rut' => $rut]);
    }
}
