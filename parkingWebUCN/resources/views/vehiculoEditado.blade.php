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

    if($patente == ""){
        $imprimir = "Patente invalida";
    }else{
        $vehiculo = $rootDir->obtenerVehiculo($patente);

        if($codigoLogo == ""){
            $codigoLogo = $vehiculo->codigoLogo;
        }
        if($marca == ""){
            $marca = $vehiculo->marca;
        }
        if($modelo == ""){
            $modelo = $vehiculo->modelo;
        }
        if($anio == ""){
            $anio = $vehiculo->anio;
        }
        if($responsable == ""){
            $responsable = $vehiculo->responsable;
        }
        if($tipoLogo == ""){
            $tipoLogo = $vehiculo->tipoLogo;
        }
        if($observacion == ""){
            $observacion = $vehiculo->observacion;
        }

        $vehiculoEditado = new \model\Vehiculo($vehiculo->patente, $codigoLogo, $marca, $modelo, intval($anio), $responsable,
            $observacion, $tipoLogo);

        $rootDir->editarVehiculo($vehiculoEditado);
        $imprimir = "Vehiculo editado";
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
