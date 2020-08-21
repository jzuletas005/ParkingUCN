@extends('layouts.default')

@section('content')
    <h3>Salida de Vehiculo</h3>
    <form method="post" action="{{route('salidaVehiculo')}}">
        @csrf
        <input name="patente" placeholder="Patente">
        Puerta de Salida:
        <select name="puertaSalida">
            <option>Principal</option>
            <option>Sur</option>
            <option>Angamos</option>
        </select><br>
        <button>Salida</button>
    </form>
@endsection
