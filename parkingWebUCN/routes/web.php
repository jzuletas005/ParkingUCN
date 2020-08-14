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

Route::get('buscarPersonas', 'Web\PageController@buscarPersona')->name('buscarPersonas');

Route::post('buscarPersonas', 'Web\PageController@persona');
