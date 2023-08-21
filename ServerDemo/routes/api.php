<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EvPowerOptimizationController;


Route::get('/signal/getvalue/{signalw}', [EvPowerOptimizationController::class,'getvalue']);
Route::post('/signal/setvalue/{signalw}', [EvPowerOptimizationController::class,'setvalue']);

Route::post('/signal/Media.brightness', [EvPowerOptimizationController::class,'getMediabrightnessvalue']);


Route::get('/message',function(){
    return response()->json([
            'message' => 'hello',
            'status' => 200

    ]);
});

Route::post('/signal/add',[EvPowerOptimizationController::class,'store']);
Route::get('/signal',[EvPowerOptimizationController::class,'fetch']);
Route::get('/signal/{id}/show',[EvPowerOptimizationController::class,'show']);
//Route::put('/signal/{id}/update',[EvPowerOptimizationController::class,'update']);
Route::post('/signal/{id}/update',[EvPowerOptimizationController::class,'update']);
Route::delete('/signal/{id}/delete',[EvPowerOptimizationController::class,'destroy']);