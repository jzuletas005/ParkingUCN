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
        $nombre = "";
    }else{
        $persona = $rootDir->obtenerPersona($rut);
        $nombre = $persona->nombre;
        $imprimir = "";
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
    <h3>Editar Persona</h3>
    <h4><?php echo $imprimir;?></h4><br>
    Rellenar solo los campos a modificar<br><br>
    Rut: <?php echo $rut;?><br>
    Nombre: <?php echo $nombre;?><br>
    <form method="post" action="{{route('editarPersona')}}">
        @csrf
        <input name="cargo" placeholder="Cargo" ><br>
        <input name="unidad" placeholder="Unidad" ><br>
        <input name="email" placeholder="Email" ><br>
        <input name="telefono" placeholder="Telefono" ><br>
        <input name="oficina" placeholder="Oficina" ><br>
        <input name="direccion" placeholder="Direccion" ><br>
        <input name="localidad" placeholder="Localidad" ><br>
        <input name="rut" type="hidden" value=<?php echo $rut;?> >
        <button>Editar</button>
    </form>
@endsection
