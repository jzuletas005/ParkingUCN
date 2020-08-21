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
