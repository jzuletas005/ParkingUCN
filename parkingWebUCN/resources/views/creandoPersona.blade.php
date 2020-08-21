@extends('layouts.default')


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

    if($rut == "" || $nombre == ""){
        $imprimir = "Rut o nombre invalido";
    }else{
        if(!$rootDir->obtenerPersona($rut)){
            $persona = new \model\Persona(0, $nombre, $rut, $cargo, $unidad, $direccion,
                $sexo, $telefono, $oficina, $email, $localidad);
            $rootDir->registrarPersona($persona);
            $imprimir = "Persona agregada";
        }else{
            $imprimir = "La persona ya existe";
        }
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
@section('content')
    <h4><?php echo $imprimir;?></h4><br>
@endsection
