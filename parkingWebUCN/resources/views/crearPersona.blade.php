@extends('layouts.default')

@section('content')
    <h3>Crear Persona</h3>
    <form method="post" action="{{route('crearPersona')}}">
        @csrf
        <input name="rut" placeholder="Rut">
        Sexo: <br>
        <input name="nombre" placeholder="Nombre">
        <select name="sexo">
            <option>INDETERMINADO</option>
            <option>FEMENINO</option>
            <option>MASCULINO</option>
        </select><br>
        <input name="cargo" placeholder="Cargo"><br>
        <input name="unidad" placeholder="Unidad"><br>
        <input name="email" placeholder="Email"><br>
        <input name="telefono" placeholder="Telefono"><br>
        <input name="oficina" placeholder="Oficina"><br>
        <input name="direccion" placeholder="Direccion"><br>
        <input name="localidad" placeholder="Localidad"><br>
        <button>Ingresar</button>
    </form>
@endsection
