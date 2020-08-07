<?php
//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `domain.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//


namespace model
{
    global $model__t_Sexo;
    class Sexo
    {
        const MASCULINO = 0;
        const FEMENINO = 1;
        const INDETERMINADO = 2;
    }

    $model__t_Sexo = IcePHP_defineEnum('::model::Sexo', array('MASCULINO', 0, 'FEMENINO', 1, 'INDETERMINADO', 2));
}

namespace model
{
    global $model__t_TipoLogo;
    class TipoLogo
    {
        const ROJO = 0;
        const AZUL = 1;
    }

    $model__t_TipoLogo = IcePHP_defineEnum('::model::TipoLogo', array('ROJO', 0, 'AZUL', 1));
}

namespace model
{
    global $model__t_Persona;
    class Persona extends \Ice\Value
    {
        public function __construct($uid=0, $nombre='', $rut='', $wposition='', $unit='', $direccion='', $sexo=\model\Sexo::MASCULINO, $telefono='', $oficina='', $email='', $country='')
        {
            $this->uid = $uid;
            $this->nombre = $nombre;
            $this->rut = $rut;
            $this->wposition = $wposition;
            $this->unit = $unit;
            $this->direccion = $direccion;
            $this->sexo = $sexo;
            $this->telefono = $telefono;
            $this->oficina = $oficina;
            $this->email = $email;
            $this->country = $country;
        }

        public function ice_id()
        {
            return '::model::Persona';
        }

        public static function ice_staticId()
        {
            return '::model::Persona';
        }

        public function __toString()
        {
            global $model__t_Persona;
            return IcePHP_stringify($this, $model__t_Persona);
        }

        public $uid;
        public $nombre;
        public $rut;
        public $wposition;
        public $unit;
        public $direccion;
        public $sexo;
        public $telefono;
        public $oficina;
        public $email;
        public $country;
    }

    global $Ice__t_Value;
    global $IcePHP__t_int;
    global $IcePHP__t_string;
    global $model__t_Sexo;
    $model__t_Persona = IcePHP_defineClass('::model::Persona', '\\model\\Persona', -1, false, false, $Ice__t_Value, array(
        array('uid', $IcePHP__t_int, false, 0),
        array('nombre', $IcePHP__t_string, false, 0),
        array('rut', $IcePHP__t_string, false, 0),
        array('wposition', $IcePHP__t_string, false, 0),
        array('unit', $IcePHP__t_string, false, 0),
        array('direccion', $IcePHP__t_string, false, 0),
        array('sexo', $model__t_Sexo, false, 0),
        array('telefono', $IcePHP__t_string, false, 0),
        array('oficina', $IcePHP__t_string, false, 0),
        array('email', $IcePHP__t_string, false, 0),
        array('country', $IcePHP__t_string, false, 0)));
}

namespace model
{
    global $model__t_Vehiculo;
    class Vehiculo extends \Ice\Value
    {
        public function __construct($patente='', $codigoLogo='', $marca='', $modelo='', $anio=0, $observacion='', $responsable='')
        {
            $this->patente = $patente;
            $this->codigoLogo = $codigoLogo;
            $this->marca = $marca;
            $this->modelo = $modelo;
            $this->anio = $anio;
            $this->observacion = $observacion;
            $this->responsable = $responsable;
        }

        public function ice_id()
        {
            return '::model::Vehiculo';
        }

        public static function ice_staticId()
        {
            return '::model::Vehiculo';
        }

        public function __toString()
        {
            global $model__t_Vehiculo;
            return IcePHP_stringify($this, $model__t_Vehiculo);
        }

        public $patente;
        public $codigoLogo;
        public $marca;
        public $modelo;
        public $anio;
        public $observacion;
        public $responsable;
    }

    global $Ice__t_Value;
    global $IcePHP__t_string;
    global $IcePHP__t_int;
    $model__t_Vehiculo = IcePHP_defineClass('::model::Vehiculo', '\\model\\Vehiculo', -1, false, false, $Ice__t_Value, array(
        array('patente', $IcePHP__t_string, false, 0),
        array('codigoLogo', $IcePHP__t_string, false, 0),
        array('marca', $IcePHP__t_string, false, 0),
        array('modelo', $IcePHP__t_string, false, 0),
        array('anio', $IcePHP__t_int, false, 0),
        array('observacion', $IcePHP__t_string, false, 0),
        array('responsable', $IcePHP__t_string, false, 0)));
}

namespace model
{
    global $model__t_Circulacion;
    class Circulacion extends \Ice\Value
    {
        public function __construct($uid=0, $fechaIngreso='', $fechaSalida='', $patente='', $puertaEntrada='', $puertaSalida='')
        {
            $this->uid = $uid;
            $this->fechaIngreso = $fechaIngreso;
            $this->fechaSalida = $fechaSalida;
            $this->patente = $patente;
            $this->puertaEntrada = $puertaEntrada;
            $this->puertaSalida = $puertaSalida;
        }

        public function ice_id()
        {
            return '::model::Circulacion';
        }

        public static function ice_staticId()
        {
            return '::model::Circulacion';
        }

        public function __toString()
        {
            global $model__t_Circulacion;
            return IcePHP_stringify($this, $model__t_Circulacion);
        }

        public $uid;
        public $fechaIngreso;
        public $fechaSalida;
        public $patente;
        public $puertaEntrada;
        public $puertaSalida;
    }

    global $Ice__t_Value;
    global $IcePHP__t_int;
    global $IcePHP__t_string;
    $model__t_Circulacion = IcePHP_defineClass('::model::Circulacion', '\\model\\Circulacion', -1, false, false, $Ice__t_Value, array(
        array('uid', $IcePHP__t_int, false, 0),
        array('fechaIngreso', $IcePHP__t_string, false, 0),
        array('fechaSalida', $IcePHP__t_string, false, 0),
        array('patente', $IcePHP__t_string, false, 0),
        array('puertaEntrada', $IcePHP__t_string, false, 0),
        array('puertaSalida', $IcePHP__t_string, false, 0)));
}

namespace model
{
    global $model__t_Identificacion;
    class Identificacion extends \Ice\Value
    {
        public function __construct($codigoLogo='', $patente='', $rut='', $tipoLogo=\model\TipoLogo::ROJO)
        {
            $this->codigoLogo = $codigoLogo;
            $this->patente = $patente;
            $this->rut = $rut;
            $this->tipoLogo = $tipoLogo;
        }

        public function ice_id()
        {
            return '::model::Identificacion';
        }

        public static function ice_staticId()
        {
            return '::model::Identificacion';
        }

        public function __toString()
        {
            global $model__t_Identificacion;
            return IcePHP_stringify($this, $model__t_Identificacion);
        }

        public $codigoLogo;
        public $patente;
        public $rut;
        public $tipoLogo;
    }

    global $Ice__t_Value;
    global $IcePHP__t_string;
    global $model__t_TipoLogo;
    $model__t_Identificacion = IcePHP_defineClass('::model::Identificacion', '\\model\\Identificacion', -1, false, false, $Ice__t_Value, array(
        array('codigoLogo', $IcePHP__t_string, false, 0),
        array('patente', $IcePHP__t_string, false, 0),
        array('rut', $IcePHP__t_string, false, 0),
        array('tipoLogo', $model__t_TipoLogo, false, 0)));
}

namespace model
{
    global $model__t_Contratos;
    global $model__t_ContratosPrx;

    class ContratosPrxHelper
    {
        public static function checkedCast($proxy, $facetOrContext=null, $context=null)
        {
            return $proxy->ice_checkedCast('::model::Contratos', $facetOrContext, $context);
        }

        public static function uncheckedCast($proxy, $facet=null)
        {
            return $proxy->ice_uncheckedCast('::model::Contratos', $facet);
        }

        public static function ice_staticId()
        {
            return '::model::Contratos';
        }
    }
    $model__t_Contratos = IcePHP_defineClass('::model::Contratos', '\\model\\Contratos', -1, false, true, null, null);

    global $Ice__t_ObjectPrx;
    $model__t_ContratosPrx = IcePHP_defineProxy('::model::Contratos', $Ice__t_ObjectPrx, null);

    global $IcePHP__t_string;
    global $IcePHP__t_bool;
    IcePHP_defineOperation($model__t_ContratosPrx, 'verificarPatenteLogo', 0, 0, 0, array(array($IcePHP__t_string), array($IcePHP__t_string)), null, array($IcePHP__t_bool), null);
    IcePHP_defineOperation($model__t_ContratosPrx, 'verificarLogoPatente', 0, 0, 0, array(array($IcePHP__t_string), array($IcePHP__t_string)), null, array($IcePHP__t_bool), null);
}

namespace model
{
    global $model__t_TheSystem;
    global $model__t_TheSystemPrx;

    class TheSystemPrxHelper
    {
        public static function checkedCast($proxy, $facetOrContext=null, $context=null)
        {
            return $proxy->ice_checkedCast('::model::TheSystem', $facetOrContext, $context);
        }

        public static function uncheckedCast($proxy, $facet=null)
        {
            return $proxy->ice_uncheckedCast('::model::TheSystem', $facet);
        }

        public static function ice_staticId()
        {
            return '::model::TheSystem';
        }
    }
    $model__t_TheSystem = IcePHP_defineClass('::model::TheSystem', '\\model\\TheSystem', -1, false, true, null, null);

    global $Ice__t_ObjectPrx;
    $model__t_TheSystemPrx = IcePHP_defineProxy('::model::TheSystem', $Ice__t_ObjectPrx, null);

    global $IcePHP__t_long;
    global $model__t_Persona;
    global $model__t_Vehiculo;
    global $IcePHP__t_string;
    IcePHP_defineOperation($model__t_TheSystemPrx, 'getDelay', 0, 0, 0, array(array($IcePHP__t_long)), null, array($IcePHP__t_long), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'registrarPersona', 0, 0, 0, array(array($model__t_Persona)), null, array($model__t_Persona), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'registrarVehiculo', 0, 0, 0, array(array($model__t_Vehiculo)), null, array($model__t_Vehiculo), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'obtenerPersona', 0, 0, 0, array(array($IcePHP__t_string)), null, array($model__t_Persona), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'obtenerVehiculo', 0, 0, 0, array(array($IcePHP__t_string)), null, array($model__t_Vehiculo), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'eliminarPersona', 0, 0, 0, array(array($IcePHP__t_string)), null, array($model__t_Persona), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'eliminarVehiculo', 0, 0, 0, array(array($IcePHP__t_string)), null, array($model__t_Vehiculo), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'editarPersona', 0, 0, 0, array(array($model__t_Persona)), null, array($model__t_Persona), null);
    IcePHP_defineOperation($model__t_TheSystemPrx, 'editarVehiculo', 0, 0, 0, array(array($model__t_Vehiculo)), null, array($model__t_Vehiculo), null);
}
?>
