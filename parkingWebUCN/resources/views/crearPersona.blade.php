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
