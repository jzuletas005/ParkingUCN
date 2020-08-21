<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::redirect('/', 'inicio');

Route::get('inicio', 'Web\PageController@inicio')->name('inicio');

Route::get('buscarPersona', 'Web\PageController@buscarPersona')->name('buscarPersona');

Route::get('crearPersona', 'Web\PageController@crearPersona')->name('crearPersona');

Route::get('editarPersona', 'Web\PageController@editarPersona')->name('editarPersona');

Route::get('eliminarPersona', 'Web\PageController@eliminarPersona')->name('eliminarPersona');

Route::get('crearVehiculo', 'Web\PageController@crearVehiculo')->name('crearVehiculo');

Route::get('buscarVehiculo', 'Web\PageController@buscarVehiculo')->name('buscarVehiculo');

Route::get('editarVehiculo', 'Web\PageController@editarVehiculo')->name('editarVehiculo');

Route::get('eliminarVehiculo', 'Web\PageController@eliminarVehiculo')->name('eliminarVehiculo');

Route::get('ingresoVehiculo', 'Web\PageController@ingresoVehiculo')->name('ingresoVehiculo');

Route::get('salidaVehiculo', 'Web\PageController@salidaVehiculo')->name('salidaVehiculo');

Route::get('estadoVehiculo', 'Web\PageController@estadoVehiculo')->name('estadoVehiculo');

Route::get('vehiculosCirculando', 'Web\PageController@vehiculosCirculando')->name('vehiculosCirculando');

Route::get('personasCampus', 'Web\PageController@personasCampus')->name('personasCampus');

Route::post('buscarPersona', 'Web\PageController@persona');

Route::post('crearPersona', 'Web\PageController@creandoPersona');

Route::post('editarPersona', 'Web\PageController@personaEditada');

Route::post('crearVehiculo', 'Web\PageController@creandoVehiculo');

Route::post('buscarVehiculo', 'Web\PageController@vehiculo');

Route::post('editarVehiculo', 'Web\PageController@vehiculoEditado');

Route::post('ingresoVehiculo', 'Web\PageController@ingresandoVehiculo');

Route::post('salidaVehiculo', 'Web\PageController@saliendoVehiculo');

Route::post('personasCampus', 'Web\PageController@contadorPersonasCampus');
?>
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
