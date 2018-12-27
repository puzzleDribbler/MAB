package com.example.mehfu.mab.models

import java.io.Serializable

data class Sport(val id:Int, val name: String, val desc: String, val isNonBall : Boolean, val duration : Double, val image:String) : Serializable