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

            if (differenceX <= 20) coefficientX = 10.0 else if (differenceX <= 100) coefficientX = 10.0 else if (differenceX <= 200) coefficientX = 5.0 else if (differenceX <= 1000) coefficientX = 1.0 else coefficientX = 0.5
            if (differenceY <= 20) coefficientY = 50.0 else if (differenceY <= 100) coefficientY = 10.0 else if (differenceY <= 200) coefficientY = 5.0 else if (differenceY <= 1000) coefficientY = 1.0 else coefficientY = 0.5

            for (i in (plane.xMin * coefficientX).toInt()..(plane.xMax * coefficientX).toInt()) {
                val gap = if (i % 10 == 0) 3 else if (i % 5 == 0) 2 else 1
                val x = Converter.xCrt2Scr(i / coefficientX, plane)
                g.drawLine(x, y0 - dt - gap, x, y0 + dt + gap)
            }

            for (i in (plane.yMin * coefficientY).toInt()..(plane.yMax * coefficientY).toInt()) {
                val gap = if (i % 10 == 0) 3 else if (i % 5 == 0) 2 else 1
                val y = Converter.yCrt2Scr(i / coefficientY, plane)
                g.drawLine(x0-dt-gap, y, x0+dt+gap, y)
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

            val differenceX = (plane.xMax - plane.xMin) * 10.toInt()
            val differenceY = (plane.yMax - plane.yMin) * 10.toInt()

            if (differenceX <= 20) coefficientX = 10.0 else if (differenceX <= 100) coefficientX = 10.0 else if (differenceX <= 200) coefficientX = 5.0 else if (differenceX <= 1000) coefficientX = 1.0 else coefficientX = 0.5
            if (differenceY <= 20) coefficientY = 50.0 else if (differenceY <= 100) coefficientY = 10.0 else if (differenceY <= 200) coefficientY = 5.0 else if (differenceY <= 1000) coefficientY = 1.0 else coefficientY = 0.5

            for (i in (plane.xMin*coefficientX).toInt()..(plane.xMax*coefficientX).toInt()) {//горизонтальные числа
                //val x = Converter.xCrt2Scr(plane.xMin + (((plane.xMax-plane.xMin)*i)/20),plane)
                val x = Converter.xCrt2Scr(i / coefficientX, plane) //место на котором надо рисовать!!!!!!!!!!!
                //val x = Converter.xCrt2Scr((i / 10.0), plane)
                //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                //val x1=(((plane.xMin+(plane.xMax-plane.xMin)*i/20))*100).toInt()
                var x1 = Converter.xScr2Crt(x,plane)
                val number2digits=Math.round(x1*100.0)/100.0
                x1=Math.round(number2digits*10.0)/10.0
                g.drawString(x1.toString(),x-8,y0-5)
            }

            /*
            for (i in 0..20) {//горизонтальные числа
                val y = Converter.yCrt2Scr(plane.yMin + (((plane.yMax-plane.yMin)*i)/20),plane)
                //val x = Converter.xCrt2Scr((i / 10.0), plane)
                //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                val y1=(((plane.yMin+(plane.yMax-plane.yMin)*i/20))*100).toInt()
                g.drawString(((y1.toDouble())/100).toString(),x0-8,y+3)
            }


             */
        }
    }

}