<?php

namespace App\Http\Controllers\Web;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

require_once 'Ice.php';
//require_once 'domain.php';

class PageController extends Controller
{
    public function home(){
        $ic = null;

        try
        {
            // Create a communicator
            //

            $ic = Ice\initialize();

            // Create a proxy for the root directory
            //
            $obj = $ic->stringToProxy("TheSystem:tcp -z -t 15000 -p 8080");

            // Down-cast the proxy to a Directory proxy
            //
            $rootDir = Filesystem\DirectoryPrxHelper::checkedCast($obj);

        }
        catch(Ice\LocalException $ex)
        {
            print_r($ex);
        }
        if($ic)
        {
            $ic->destroy(); // Clean up
        }
    }
}
