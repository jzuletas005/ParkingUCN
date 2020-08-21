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
    $obj = $ic->stringToProxy("Contratos:tcp -z -t 15000 -p 4000");

    // Down-cast the proxy to a Directory proxy
    //
    $rootDir = model\ContratosPrxHelper::checkedCast($obj);

    if($patente == ""){
        $imprimir = "Patente invalida";
        $estado = "";
    }else{
        $imprimir = "";
        $circulacion = $rootDir->busquedaVehiculoBackend($patente);
        if($circulacion){
            $estado = "Dentro de la universidad";
        }else{
            $estado = "Fuera de la universidad";
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
    Patente:
    <?php echo $patente;?><br>
    Estado:
    <?php echo $estado;?><br>
@endsection
