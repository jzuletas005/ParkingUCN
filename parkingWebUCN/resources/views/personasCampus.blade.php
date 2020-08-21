@extends('layouts.default')

@section('content')
    <h3>Crear Persona</h3>
    <form method="post" action="{{route('personasCampus')}}">
        @csrf
        Campus: <br>
        <select name="campus">
            <option>Antofagasta - Chile</option>
            <option>Coquimbo - Chile</option>
        </select><br>
        <button>Buscar</button>
    </form>
@endsection
