<div class="navbar">
    <div class="navbar-inner">
        <a id="logo" href="/"><h1>Parking UCN</h1></a>
        <ul class="nav">
            <p>
                <button type="button" onclick="window.location='{{ route("buscarPersona") }}'"class="btn btn-primary">Buscar Persona</button>
                <button type="button" onclick="window.location='{{ route("crearPersona") }}'"class="btn btn-primary">Crear Persona</button>
                <button type="button" onclick="window.location='{{ route("buscarVehiculo") }}'"class="btn btn-primary">Buscar Vehiculo</button>
                <button type="button" onclick="window.location='{{ route("crearVehiculo") }}'"class="btn btn-primary">Crear Vehiculo</button><br><br>
                <button type="button" onclick="window.location='{{ route("ingresoVehiculo") }}'"class="btn btn-primary">Ingreso de Vehiculo</button>
                <button type="button" onclick="window.location='{{ route("salidaVehiculo") }}'"class="btn btn-primary">Salida de Vehiculo</button>
            </p>
        </ul>
    </div>
</div>
