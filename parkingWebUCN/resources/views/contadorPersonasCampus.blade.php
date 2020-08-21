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
    $contador = $rootDir->totalRegion($campus);
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
    <?php echo $campus;?>:
    <?php echo $contador;?> personas
@endsection
