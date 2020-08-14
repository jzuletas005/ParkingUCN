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
    $persona = $rootDir->obtenerPersona($rut);

}
catch(Ice\LocalException $ex)
{
    print_r($ex);
}
if($ic)
{
    $ic->destroy();
}

?>
@section('content')
    Nombre:
    <?php echo $persona->nombre;?><br>
    Rut:
    <?php echo $persona->rut;?><br>
    Cargo:
    <?php echo $persona->wposition;?><br>
    Unidad:
    <?php echo $persona->unit;?><br>
    Direccion:
    <?php echo $persona->direccion;?><br>
    Telefono:
    <?php echo $persona->telefono;?><br>
    Oficina:
    <?php echo $persona->oficina;?><br>
    Email:
    <?php echo $persona->email;?><br>
    Localidad:
    <?php echo $persona->country;?><br>
@endsection
