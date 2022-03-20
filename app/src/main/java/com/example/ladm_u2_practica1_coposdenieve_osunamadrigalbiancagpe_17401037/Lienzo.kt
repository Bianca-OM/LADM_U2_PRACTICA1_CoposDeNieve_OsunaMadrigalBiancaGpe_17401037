package com.example.ladm_u2_practica1_coposdenieve_osunamadrigalbiancagpe_17401037

import android.graphics.*
import android.view.View
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList
import kotlin.random.Random

class Lienzo(este:MainActivity) : View(este) {
    var este = este
    var thisGlobal = this

    var ini = true
    var change = 0
    var flag = 300

    var copos1 = ArrayList<Circulo>()
    var copos2 = ArrayList<Circulo>()
    var copos3 = ArrayList<Circulo>()

    val corutina = GlobalScope.launch {
        while (true){
            este.runOnUiThread {
                invalidate()
            }
            delay(20L)
        }
    }

    override fun onDraw(c: Canvas) {
        super.onDraw(c)

        este.setTitle("Copos de nieve")

        val p = Paint()

        //Copos iniciales
        if(ini) {
            copos1 = coposList(120, 5f, 2f)
            copos2 = coposList(90, 10f, 4f)
            copos3 = coposList(60, 15f, 8f)
            ini = false
        }

        //Cambio de intensidad
        if(flag==0) {
            if(Random.nextInt(100)<50 && change<3){
                //Incrementar
                coposAdd(copos1, 120, 5f, 2f)
                coposAdd(copos2, 90, 10f, 4f)
                coposAdd(copos3, 60, 15f, 8f)
                change++
            }else{
                //Decrementar
                coposDis(copos1, copos1.size-1)
                coposDis(copos2, copos2.size-1)
                coposDis(copos3, copos3.size-1)
                if(change>0) {
                    change--
                }
            }
            flag = 300
        }
        flag--

        //Fondo
        c.drawColor(Color.rgb(40,28,90))

        //Luna
        p.color = Color.WHITE
        c.drawCircle(540f, 70f, 200f, p)
        p.color = Color.argb(20, 255, 255, 255)
        c.drawCircle(540f, 70f, 250f, p)

        //Copos fondo
        for (circ in copos1){
            circ.mover()
            circ.pintar(c)
        }

        //Árboles fondo
        p.style = Paint.Style.FILL
        p.color = Color.rgb(20,20,20)

        val arbol1: Path = Path()
        val arbol2: Path = Path()
        val arbol3: Path = Path()
        val arbol4: Path = Path()

        arbol1.moveTo(0f,400f)
        arbol1.lineTo(450f,1400f)
        arbol1.lineTo(-450f,1400f)
        arbol1.close()
        c.drawPath(arbol1, p)

        arbol2.moveTo(360f,400f)
        arbol2.lineTo(810f,1400f)
        arbol2.lineTo(-90f,1400f)
        arbol2.close()
        c.drawPath(arbol2, p)

        arbol3.moveTo(720f,400f)
        arbol3.lineTo(1170f,1400f)
        arbol3.lineTo(270f,1400f)
        arbol3.close()
        c.drawPath(arbol3, p)

        arbol4.moveTo(1080f,400f)
        arbol4.lineTo(1530f,1400f)
        arbol4.lineTo(630f,1400f)
        arbol4.close()
        c.drawPath(arbol4, p)

        //Copos detras
        for (circ in copos2){
            circ.mover()
            circ.pintar(c)
        }

        //Árboles enfrente
        p.color = Color.BLACK

        val arbol5: Path = Path()
        val arbol6: Path = Path()
        val arbol7: Path = Path()

        arbol5.moveTo(180f,400f)
        arbol5.lineTo(630f,1400f)
        arbol5.lineTo(-270f,1400f)
        arbol5.close()
        c.drawPath(arbol5, p)

        arbol6.moveTo(540f,400f)
        arbol6.lineTo(990f,1400f)
        arbol6.lineTo(90f,1400f)
        arbol6.close()
        c.drawPath(arbol6, p)

        arbol7.moveTo(900f,400f)
        arbol7.lineTo(1350f,1400f)
        arbol7.lineTo(450f,1400f)
        arbol7.close()
        c.drawPath(arbol7, p)

        c.drawRect(0f, 1400f, 1080f, 1800f, p)

        //Nieve
        p.color = Color.rgb(160, 160, 160)
        c.drawRect(0f, 1600f, 1080f, 1800f, p)
        c.drawOval(RectF(-500f, 1450f, 500f, 1800f), p)
        c.drawOval(RectF(400f, 1550f, 900f, 1800f), p)
        c.drawOval(RectF(800f, 1500f, 1400f, 1800f), p)

        //Copos enfrente
        for (circ in copos3){
            circ.mover()
            circ.pintar(c)
        }
    }

    fun coposList(size: Int, r: Float, m: Float): ArrayList<Circulo>{
        var copos = ArrayList<Circulo>()
        var s = size
        var rad = r
        var mov = m

        (0..s).forEach {
            copos.add(Circulo(thisGlobal, rad, mov))
        }
        return copos
    }

    fun coposAdd(cL: ArrayList<Circulo>, size: Int, r: Float, m: Float){
        var copos = cL
        var s = size
        var rad = r
        var mov = m

        (0..s).forEach {
            copos.add(Circulo(thisGlobal, rad, mov))
        }
    }

    fun coposDis(cL: ArrayList<Circulo>, size: Int){
        var copos = cL
        var s = size

        (0..s).forEach {
            if(copos[it].y<0) {
                copos[it].rad = 0f
            }
        }
    }

}