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
    <form method="get" action="{{route('editarVehiculo')}}">
        @csrf
        <input type="hidden" name="patente" value=<?php echo $patente;?>>
        <button>Editar Vehiculo</button>
    </form>
    <form method="get" action="{{route('eliminarVehiculo')}}">
        @csrf
        <input type="hidden" name="patente" value=<?php echo $patente;?>>
        <button>Eliminar Vehiculo</button>
    </form>
    <form method="get" action="{{route('estadoVehiculo')}}">
        @csrf
        <input type="hidden" name="patente" value=<?php echo $patente;?>>
        <button>Estado Vehiculo</button>
    </form>
@endsection

<!--
MIT License

Copyright (c) 2020 Javier Zuleta Silva, Beatriz Alvarez Rojas, Gonzalo Nieto Berrios

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
-->
