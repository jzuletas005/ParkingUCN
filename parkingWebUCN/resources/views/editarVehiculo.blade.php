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
        $imprimir = "";
        if($vehiculo->tipoLogo == "Rojo"){
            $tipoLogoPorDefecto = "Rojo";
            $tipoLogoRestante = "Azul";
        }elseif ($vehiculo->tipoLogo == "Azul"){
            $tipoLogoPorDefecto = "Azul";
            $tipoLogoRestante = "Rojo";
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


@extends('layouts.default')

@section('content')
    <h3>Editar Vehiculo</h3>
    <h4><?php echo $imprimir;?></h4><br>
    Rellenar solo los campos a modificar<br><br>
    Patente: <?php echo $patente;?><br>
    <form method="post" action="{{route('editarVehiculo')}}">
        @csrf
        <input name="codigoLogo" placeholder="Codigo del Logo" >
        Tipo de logo:
        <select name="tipoLogo">
            <option selected><?php echo $tipoLogoPorDefecto;?></option>
            <option><?php echo $tipoLogoRestante;?></option>
        </select><br>
        <input name="marca" placeholder="Marca" ><br>
        <input name="modelo" placeholder="Modelo" ><br>
        <input name="anio" placeholder="Año" ><br>
        <input name="responsable" placeholder="Responsable" ><br>
        <textarea name="observacion" placeholder="Observacion"></textarea><br>
        <input name="patente" type="hidden" value=<?php echo $patente;?> >
        <button>Editar</button>
    </form>
@endsection
