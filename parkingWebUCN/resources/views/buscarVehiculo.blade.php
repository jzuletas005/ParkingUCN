@extends('layouts.default')

@section('content')
    <h3>Buscar Vehiculo</h3>
    <form method="post" action="{{route('buscarVehiculo')}}">
        @csrf
        <input name="patente" placeholder="Patente">
        <button>Buscar</button>
    </form>
@endsection
