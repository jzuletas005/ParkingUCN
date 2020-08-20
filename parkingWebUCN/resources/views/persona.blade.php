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
    $imprimir = $ex;
    //print_r($ex);
}
if($ic)
{
    $ic->destroy();
}

if($persona){
    $imprimir = "";
    $nombre = $persona->nombre;
    $rut = $persona->rut;
    $cargo = $persona->wposition;
    $unidad = $persona->unit;
    $direccion = $persona->direccion;
    $telefono = $persona->telefono;
    $oficina = $persona->oficina;
    $email = $persona->email;
    $localidad = $persona->country;
}else{
    $imprimir = "Rut invalido";
    $nombre = "";
    $rut = "";
    $cargo = "";
    $unidad = "";
    $direccion = "";
    $telefono = "";
    $oficina = "";
    $email = "";
    $localidad = "";
}

?>
@section('content')
    <h4><?php echo $imprimir;?></h4><br>
    Nombre:
    <?php echo $nombre;?><br>
    Rut:
    <?php echo $rut;?><br>
    Cargo:
    <?php echo $cargo;?><br>
    Unidad:
    <?php echo $unidad;?><br>
    Direccion:
    <?php echo $direccion;?><br>
    Telefono:
    <?php echo $telefono;?><br>
    Oficina:
    <?php echo $oficina;?><br>
    Email:
    <?php echo $email;?><br>
    Localidad:
    <?php echo $localidad;?><br><br>
    <form method="get" action="{{route('editarPersona')}}">
        @csrf
        <input type="hidden" name="rut" value=<?php echo $rut;?>>
        <button>Editar Persona</button>
    </form>
    <form method="get" action="{{route('eliminarPersona')}}">
        @csrf
        <input type="hidden" name="rut" value=<?php echo $rut;?>>
        <button>Eliminar Persona</button>
    </form>
@endsection
