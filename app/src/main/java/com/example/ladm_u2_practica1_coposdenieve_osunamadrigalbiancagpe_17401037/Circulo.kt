package com.example.ladm_u2_practica1_coposdenieve_osunamadrigalbiancagpe_17401037

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Circulo(l:Lienzo,rad:Float,movY:Float) {
    val l = l
    var x = 0f
    var y = 0f
    var movY = movY
    var rad = rad
    var color = Color.BLACK
    val colVar = 120+rand(80).toInt()

    init {
        x = 15+rand(1050)
        y = -15-rand(l.height)

        color = Color.rgb(colVar, colVar, colVar)
    }

    private fun rand (hasta: Int) : Float{
        return Random.nextInt(hasta).toFloat()
    }

    fun mover (){
        y += movY
        if (y>l.height){
            y = -15-rand(l.height)
        }
    }

    fun pintar(can: Canvas){
        var p = Paint()
        p.color = color
        can.drawCircle(x, y, rad, p)
    }
}