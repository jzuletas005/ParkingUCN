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

    $personaEditada = new \model\Persona(0, $persona->nombre, $rut, $cargo, $unidad, $direccion,
        $persona->sexo, $telefono, $oficina, $email, $localidad);

    $rootDir->editarPersona($personaEditada);
    $imprimir = "Persona editada";

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
