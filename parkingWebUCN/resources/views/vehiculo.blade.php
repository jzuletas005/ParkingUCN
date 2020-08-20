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
    $vehiculo = $rootDir->obtenerVehiculo($patente);

    if($vehiculo){
        $imprimir = "";
        $patente = $vehiculo->patente;
        $codigoLogo = $vehiculo->codigoLogo;
        $marca = $vehiculo->marca;
        $modelo = $vehiculo->modelo;
        $anio = $vehiculo->anio;
        $observacion = $vehiculo->observacion;
        $responsable = $vehiculo->responsable;
        $tipoLogo = $vehiculo->tipoLogo;
    }else{
        $imprimir = "Patente invalida";
        $patente = "";
        $codigoLogo = "";
        $marca = "";
        $modelo = "";
        $anio = "";
        $observacion = "";
        $responsable = "";
        $tipoLogo = "";
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
    Patente:
    <?php echo $patente;?><br>
    Codigo del Logo:
    <?php echo $codigoLogo;?><br>
    Tipo del Logo:
    <?php echo $tipoLogo;?><br>
    Marca:
    <?php echo $marca;?><br>
    Modelo:
    <?php echo $modelo;?><br>
    Año:
    <?php echo $anio;?><br>
    Responsable:
    <?php echo $responsable;?><br>
    Observacion:
    <?php echo $observacion;?><br>
@endsection
