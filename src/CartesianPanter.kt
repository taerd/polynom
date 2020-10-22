import java.awt.Color
import java.awt.Graphics

class CartesianPainter(val plane:CartesianScreenPlane) : Painter{
    override fun paint(g: Graphics?) {
        drawAxes(g)
        drawTicks(g)
        drawLabels(g)
        /*
        if (g!=null) {
            g.color = Color.BLUE
            g.fillOval(10, 10, 300, 300)
        }
         */
    }
    private fun drawAxes(g:Graphics?){
        if(g!=null){
            g.color = Color.BLACK
            //рисуем вертикальную
            g.drawLine(
                    0,
                    Converter.yCrt2Scr(0.0,plane),
                    plane.width,
                    Converter.yCrt2Scr(0.0,plane)
            )
            g.drawLine(
                    Converter.xCrt2Scr(0.0,plane),
                    0,
                    Converter.xCrt2Scr(0.0,plane),
                    plane.height
            )
        }
    }
    private fun drawTicks(g:Graphics?) {
        if (g != null) {
            val dt = 3 // Половина длины черточки
            g.color = Color.DARK_GRAY // Цвет черточек

            val x0 = Converter.xCrt2Scr(0.0, plane)
            val y0 = Converter.yCrt2Scr(0.0, plane)

            var coefficientX = 1.0
            var coefficientY=1.0

            val differenceX = (plane.xMax - plane.xMin) * 10.toInt()
            val differenceY = (plane.yMax - plane.yMin) * 10.toInt()

            if (differenceX <= 60) coefficientX = 10.0 else if (differenceX <= 230) coefficientX = 2.0 else if (differenceX <= 510) coefficientX = 1.0 else if (differenceX <= 1000) coefficientX = 0.2 //else coefficientX = 0.5
            if (differenceY <= 60) coefficientY = 10.0 else if (differenceY <= 230) coefficientY = 2.0 else if (differenceY <= 500) coefficientY = 1.0 else if (differenceY <= 1000) coefficientY = 0.2 //else coefficientY = 0.5

            if(0.0<=plane.xMin || 0.0>=plane.xMax){
                for (i in (plane.yMin * coefficientY).toInt()..(plane.yMax * coefficientY).toInt()) {
                    val gap = if (i % 10 == 0) 4 else if (i % 5 == 0) 2 else 0
                    val y = Converter.yCrt2Scr(i / coefficientY, plane)
                    g.drawLine(Converter.xCrt2Scr(plane.xMin,plane), y, Converter.xCrt2Scr(plane.xMin,plane)+dt+gap, y)
                    g.drawLine(Converter.xCrt2Scr(plane.xMax,plane)-dt-gap, y, Converter.xCrt2Scr(plane.xMax,plane), y)
                }
            }else{
                for (i in (plane.yMin * coefficientY).toInt()..(plane.yMax * coefficientY).toInt()) {
                    val gap = if (i % 10 == 0) 4 else if (i % 5 == 0) 2 else 0
                    val y = Converter.yCrt2Scr(i / coefficientY, plane)
                    g.drawLine(x0-dt-gap, y, x0+dt+gap, y)
                }
            }
            if(0.0<=plane.yMin || 0.0>=plane.yMax){
                for (i in (plane.xMin * coefficientX).toInt()..(plane.xMax * coefficientX).toInt()) {
                    val gap = if (i % 10 == 0) 4 else if (i % 5 == 0) 2 else 0
                    val x = Converter.xCrt2Scr(i / coefficientX, plane)
                    g.drawLine(x, Converter.yCrt2Scr(plane.yMin,plane), x, Converter.yCrt2Scr(plane.yMin,plane)+dt+gap)
                    g.drawLine(x, Converter.yCrt2Scr(plane.yMax,plane)-dt-gap, x, Converter.yCrt2Scr(plane.yMax,plane))
                }
            }else{
                for (i in (plane.xMin * coefficientX).toInt()..(plane.xMax * coefficientX).toInt()) {
                    val gap = if (i % 10 == 0) 4 else if (i % 5 == 0) 2 else 0
                    val x = Converter.xCrt2Scr(i / coefficientX, plane)
                    g.drawLine(x, y0 - dt - gap, x, y0 + dt + gap)
                }
            }
        }
    }


    private fun drawLabels(g: Graphics?) {//числа
        if(g!=null){
            val x0 = Converter.xCrt2Scr(0.0, plane)
            val y0 = Converter.yCrt2Scr(0.0, plane)

            g.color = Color.darkGray

            var coefficientX = 1.0
            var coefficientY=1.0

            val differenceX = (plane.xMax - plane.xMin) * 100.toInt()
            val differenceY = (plane.yMax - plane.yMin) * 100.toInt()

            if (differenceX <= 600) coefficientX = 10.0 else if (differenceX <= 2300) coefficientX = 2.0 else if (differenceX <= 5100) coefficientX = 1.0 else if (differenceX <= 10000) coefficientX = 0.2 // else coefficientX = 0.5
            if (differenceY <= 600) coefficientY = 10.0 else if (differenceY <= 2300) coefficientY = 2.0 else if (differenceY <= 5100) coefficientY = 1.0 else if (differenceY <= 10000) coefficientY = 0.2 // else coefficientY = 0.5

            if(0.0<=plane.yMin || 0.0>=plane.yMax){
                for (i in (plane.xMin*coefficientX).toInt()..(plane.xMax*coefficientX).toInt()) {//горизонтальные числа
                    //val x = Converter.xCrt2Scr(plane.xMin + (((plane.xMax-plane.xMin)*i)/20),plane)
                    val x = Converter.xCrt2Scr(i / coefficientX, plane) //место на котором надо рисовать!!!!!!!!!!!
                    //val x = Converter.xCrt2Scr((i / 10.0), plane)
                    //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                    //val x1=(((plane.xMin+(plane.xMax-plane.xMin)*i/20))*100).toInt()
                    var x1 = Converter.xScr2Crt(x,plane)
                    //x1=Math.round(x1*100000.0)/100000.0
                    //для более точных значений
                    x1=Math.round(x1*10000.0)/10000.0
                    x1=Math.round(x1*1000.0)/1000.0
                    x1=Math.round(x1*100.0)/100.0
                    x1=Math.round(x1*10.0)/10.0
                    //x1=Math.round(x1*1.0)/1.0
                    g.drawString(x1.toString(),x-8,Converter.yCrt2Scr(plane.yMin,plane)-6)
                    g.drawString(x1.toString(),x-8,Converter.yCrt2Scr(plane.yMax,plane)+15)
                }
            }else{
                for (i in (plane.xMin*coefficientX).toInt()..(plane.xMax*coefficientX).toInt()) {//горизонтальные числа
                    //val x = Converter.xCrt2Scr(plane.xMin + (((plane.xMax-plane.xMin)*i)/20),plane)
                    val x = Converter.xCrt2Scr(i / coefficientX, plane) //место на котором надо рисовать!!!!!!!!!!!
                    //val x = Converter.xCrt2Scr((i / 10.0), plane)
                    //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                    //val x1=(((plane.xMin+(plane.xMax-plane.xMin)*i/20))*100).toInt()
                    var x1 = Converter.xScr2Crt(x,plane)
                    //x1=Math.round(x1*100000.0)/100000.0
                    //для более точных значений
                    x1=Math.round(x1*10000.0)/10000.0
                    x1=Math.round(x1*1000.0)/1000.0
                    x1=Math.round(x1*100.0)/100.0
                    x1=Math.round(x1*10.0)/10.0
                    //x1=Math.round(x1*1.0)/1.0
                    g.drawString(x1.toString(),x-8,y0-5)
                }
            }
            if(0.0<=plane.xMin || 0.0>=plane.xMax){
                for (i in (plane.yMin*coefficientX).toInt()..(plane.yMax*coefficientX).toInt()) {//горизонтальные числа
                    val y = Converter.yCrt2Scr(i/coefficientY,plane)
                    //val x = Converter.xCrt2Scr((i / 10.0), plane)
                    //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                    var y1=Converter.yScr2Crt(y,plane)
                    y1=Math.round(y1*10000.0)/10000.0
                    y1=Math.round(y1*1000.0)/1000.0
                    y1=Math.round(y1*100.0)/100.0
                    y1=Math.round(y1*10.0)/10.0
                    //y1=Math.round(y1*1.0)/1.0
                    if(y1 != 0.0) {
                        g.drawString(y1.toString(),Converter.xCrt2Scr(plane.xMin,plane)+3,y+3)
                        g.drawString(y1.toString(),Converter.xCrt2Scr(plane.xMax,plane)-20,y+3)
                    }
                }
            }else{
                for (i in (plane.yMin*coefficientX).toInt()..(plane.yMax*coefficientX).toInt()) {//горизонтальные числа
                    val y = Converter.yCrt2Scr(i/coefficientY,plane)
                    //val x = Converter.xCrt2Scr((i / 10.0), plane)
                    //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                    var y1=Converter.yScr2Crt(y,plane)
                    y1=Math.round(y1*10000.0)/10000.0
                    y1=Math.round(y1*1000.0)/1000.0
                    y1=Math.round(y1*100.0)/100.0
                    y1=Math.round(y1*10.0)/10.0
                    //y1=Math.round(y1*1.0)/1.0
                    if(y1 != 0.0) {
                        g.drawString(y1.toString(),x0-8,y+3)
                    }
                }
            }
        }
    }
}