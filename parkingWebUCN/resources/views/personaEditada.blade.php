<?php
require_once 'Ice.php';
require_once(__DIR__ . '\..\..\..\domain.php');

try
{
    // Create a communicator
    //
    $ic = Ice\initialize();

    // Create a proxy
    //
    $obj = $ic->stringToProxy("TheSystem:tcp -z -t 15000 -p 8080");

    // Down-cast the proxy to a Directory proxy
    //
    $rootDir = model\TheSystemPrxHelper::checkedCast($obj);

    if($rut == ""){
        $imprimir = "Rut invalido";
    }else{
        $persona = $rootDir->obtenerPersona($rut);

        if($cargo == ""){
            $cargo = $persona->wposition;
        }
        if($unidad == ""){
            $unidad = $persona->unit;
        }
        if($email == ""){
            $email = $persona->email;
        }
        if($telefono == ""){
            $telefono = $persona->telefono;
        }
        if($oficina == ""){
            $oficina = $persona->oficina;
        }
        if($direccion == ""){
            $direccion = $persona->direccion;
        }
        if($localidad == ""){
            $localidad = $persona->country;
        }

        $personaEditada = new \model\Persona($persona->uid, $persona->nombre, $rut, $cargo, $unidad, $direccion,
            $persona->sexo, $telefono, $oficina, $email, $localidad);

        $rootDir->editarPersona($personaEditada);
        $imprimir = "Persona editada";
    }
}
catch(Ice\LocalException $ex)
{
    $imprimir = $ex;
    //print_r($ex);
}
if($ic)
{
    $ic->destroy();
}


?>
@extends('layouts.default')
@section('content')
    <h4><?php echo $imprimir;?></h4><br>
@endsection

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
