<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Ev_poweroptimization_vss extends Model
{
    use HasFactory;
    protected $table = "ev_poweroptimization_vss";
    protected $primaryKey = "signal_id";
    protected $fillable = [
'signal',
'value'
    ];

}
