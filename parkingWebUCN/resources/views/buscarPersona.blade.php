@extends('layouts.default')

@section('content')
    <h3>Buscar Persona</h3>
    <form method="post" action="{{route('buscarPersona')}}">
          @csrf
        <input name="rut" placeholder="Rut">
        <button>Buscar</button>
    </form>
@endsection
