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
    $rut = $rootDir2->formatearRut($rut);
    $persona = $rootDir->obtenerPersona($rut);

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
