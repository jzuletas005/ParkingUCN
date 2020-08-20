@extends('layouts.default')

@section('content')
    <h3>Crear Vehiculo</h3>
    <form method="post" action="{{route('crearVehiculo')}}">
        @csrf
        <input name="patente" placeholder="Patente"><br>
        <input name="codigoLogo" placeholder="Codigo del Logo">
        Tipo de logo:
        <select name="tipoLogo">
            <option>Rojo</option>
            <option>Azul</option>
        </select><br>
        <input name="marca" placeholder="Marca del Vehiculo"><br>
        <input name="modelo" placeholder="Modelo"><br>
        <input name="anio" placeholder="Anio"><br>
        <input name="responsable" placeholder="Responsable"><br>
        <textarea name="observacion" placeholder="Observacion"></textarea><br>
        <button>Ingresar</button>
    </form>
@endsection
