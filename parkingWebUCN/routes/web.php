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

Route::post('buscarPersona', 'Web\PageController@persona');

Route::post('crearPersona', 'Web\PageController@creandoPersona');

Route::post('editarPersona', 'Web\PageController@personaEditada');

Route::post('crearVehiculo', 'Web\PageController@creandoVehiculo');

Route::post('buscarVehiculo', 'Web\PageController@vehiculo');

Route::post('editarVehiculo', 'Web\PageController@vehiculoEditado');

Route::post('ingresoVehiculo', 'Web\PageController@ingresandoVehiculo');

Route::post('salidaVehiculo', 'Web\PageController@saliendoVehiculo');
