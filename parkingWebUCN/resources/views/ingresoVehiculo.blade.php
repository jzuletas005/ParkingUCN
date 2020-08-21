@extends('layouts.default')

@section('content')
    <h3>Ingreso Vehiculo</h3>
    <form method="post" action="{{route('ingresoVehiculo')}}">
        @csrf
        <input name="patente" placeholder="Patente">
        Puerta de Ingreso:
        <select name="puertaEntrada">
            <option>Principal</option>
            <option>Sur</option>
            <option>Angamos</option>
        </select><br>
        <textarea name="observacion" placeholder="Observacion"></textarea><br>
        <button>Ingresar</button>
    </form>
@endsection
