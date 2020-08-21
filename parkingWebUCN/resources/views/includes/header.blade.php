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
<div class="navbar">
    <div class="navbar-inner">
        <a id="logo" href="/"><h1>Parking UCN</h1></a>
        <ul class="nav">
            <p>

                <button type="button" onclick="window.location='{{ route("buscarPersona") }}'"class="btn btn-primary">Buscar Persona</button>
                <button type="button" onclick="window.location='{{ route("crearPersona") }}'"class="btn btn-primary">Crear Persona</button>
                <button type="button" onclick="window.location='{{ route("buscarVehiculo") }}'"class="btn btn-primary">Buscar Vehiculo</button>
                <button type="button" onclick="window.location='{{ route("crearVehiculo") }}'"class="btn btn-primary">Crear Vehiculo</button>
                <button type="button" onclick="window.location='{{ route("personasCampus") }}'"class="btn btn-primary">Personas en campus</button><br><br>
                <button type="button" onclick="window.location='{{ route("ingresoVehiculo") }}'"class="btn btn-primary">Ingreso de Vehiculo</button>
                <button type="button" onclick="window.location='{{ route("salidaVehiculo") }}'"class="btn btn-primary">Salida de Vehiculo</button>
                <button type="button" onclick="window.location='{{ route("vehiculosCirculando") }}'"class="btn btn-primary">Vehiculos en circulacion</button>
            </p>
        </ul>
    </div>
</div>
