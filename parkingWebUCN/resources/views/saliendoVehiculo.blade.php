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
    $obj2 = $ic->stringToProxy("Contratos:tcp -z -t 15000 -p 4000");

    // Down-cast the proxy to a Directory proxy
    //
    $rootDir = model\TheSystemPrxHelper::checkedCast($obj);
    $rootDir2 = model\ContratosPrxHelper::checkedCast($obj2);

    if($patente == ""){
        $imprimir = "Patente invalida";
    }else{
        if(!$rootDir->obtenerVehiculo($patente)){
            $imprimir = "El vehiculo no existe";
        }else{
            $circulacion = $rootDir2->salidaVehiculo($patente, $puertaSalida);
            $imprimir = "Salida exitosa";
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
    Fecha de ingreso:
    <?php echo $circulacion->fechaIngreso;?><br>
    Hora de ingreso:
    <?php echo $circulacion->horaIngreso;?><br>
    Fecha de salida:
    <?php echo $circulacion->fechaIngreso;?><br>
    Hora de salida:
    <?php echo $circulacion->horaIngreso;?><br>
    Patente:
    <?php echo $circulacion->patente;?><br>
    Puerta de entrada:
    <?php echo $circulacion->puertaEntrada;?><br>
    Puerta de salida:
    <?php echo $circulacion->puertaSalida;?><br>
    Observacion:
    <?php echo $circulacion->observacion;?><br>
@endsection
