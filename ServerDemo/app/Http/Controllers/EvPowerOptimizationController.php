<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Ev_poweroptimization_vss;

class EvPowerOptimizationController extends Controller
{
    //

    public function fetch(){
            $signal = Ev_poweroptimization_vss::all();
            return response()->json(['signals'=>$signal],200);
    }

    public function show($id){
        $signal = Ev_poweroptimization_vss::find($id);
        if($signal){
        return response()->json(['signals'=>$signal],200);
        }
        else{
            return response()->json([
                'message' => 'Not Found',
                'status' => 404
    
        ]);
        }
}
    public function store(Request $request){

        $request->validate([
            'signal'=>'required',
            'value'=> 'required'
        ]
        );

        $ev_poweroptimization_vss = new Ev_poweroptimization_vss;

        $ev_poweroptimization_vss->signal=$request->signal;
        $ev_poweroptimization_vss->value=$request->value;
        $ev_poweroptimization_vss->save();

        return response()->json([
            'message' => 'Inserted Signal',
            'status' => 200

    ]);
    }


    public function update(Request $request,$id){
        $request->validate([
            'signal'=>'required',
            'value'=> 'required'
        ]
        );

        $ev_poweroptimization_vss = Ev_poweroptimization_vss::find($id);
if($ev_poweroptimization_vss){
    $ev_poweroptimization_vss->signal=$request->signal;
    $ev_poweroptimization_vss->value=$request->value;
    $ev_poweroptimization_vss->update();

    return response()->json([
        'message' => 'updated Signal',
        'status' => 200

]);
}
else{
    return response()->json([
        'message' => 'Not Found',
        'status' => 404

]);
}
        


    }


    public function destroy($id){
        $signal = Ev_poweroptimization_vss::find($id);
        if($signal){
        $signal->delete();

        return response()->json([
            'message' => 'deleted Signal',
            'status' => 200
    
    ]);
        }
        else{
            return response()->json([
                'message' => 'Not Found',
                'status' => 404
    
        ]);
        }
}

public function getValue($signalw){
    $signal = Ev_poweroptimization_vss::where('signal','=',$signalw)->get();
    if($signal){
    return response()->json(['signals'=>$signal],200);
    }
    else{
        return response()->json([
            'message' => 'Not Found',
            'status' => 404

    ]);
    }
}

public function setValue(Request $request,$signalw){

$ev_poweroptimization_vss = Ev_poweroptimization_vss::where('signal','=',$signalw)->get();
if($ev_poweroptimization_vss){



Ev_poweroptimization_vss::where('signal','=',$signalw)->update($request->all());
return response()->json([
    'message' => 'updated Signal',
    'status' => 200

]);
}
else{
return response()->json([
    'message' => 'Not Found',
    'status' => 404

]);
}
    


}

public function getMediabrightnessvalue(){
    $signalw='Media.brightness';
    $signal = Ev_poweroptimization_vss::where('signal','=',$signalw)->get();
    if($signal){
    return response()->json(['signals'=>$signal],200);
    }
    else{
        return response()->json([
            'message' => 'Not Found',
            'status' => 404

    ]);
    }
}

}
