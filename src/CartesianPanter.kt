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
    private fun drawTicks(g:Graphics?){
        if(g!=null){
            val dt = 2//половина длины черточки
            g.color = Color.BLACK
            val y0=Converter.yCrt2Scr(0.0,plane)
            val x0=Converter.xCrt2Scr(0.0,plane)

            for (i in 0..20) {//горизонтальные черточки
                //val gap = if (i % 5 == 0) 2 else 0
                val x = Converter.xCrt2Scr(plane.xMin + (((plane.xMax-plane.xMin)*i)/20),plane)
                //val x = Converter.xCrt2Scr((i / 10.0), plane)
                g.drawLine(x, y0 - dt , x, y0 + dt )
            }

            for (i in 0..20) {//вертикальные черточки
                //val gap = if (i % 5 == 0) 2 else 0
                val y = Converter.yCrt2Scr(plane.yMin + (((plane.yMax-plane.yMin)*i)/20),plane)
                //val x = Converter.xCrt2Scr((i / 10.0), plane)
                g.drawLine(x0-dt, y, x0+dt, y)
            }

        }
    }

    private fun drawLabels(g: Graphics?) {//числа
        if(g!=null){
            val x0 = Converter.xCrt2Scr(0.0, plane)
            val y0 = Converter.yCrt2Scr(0.0, plane)
            g.color = Color.darkGray
            for (i in 0..20) {//горизонтальные числа
                val x = Converter.xCrt2Scr(plane.xMin + (((plane.xMax-plane.xMin)*i)/20),plane)
                //val x = Converter.xCrt2Scr((i / 10.0), plane)
                //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                val x1=(((plane.xMin+(plane.xMax-plane.xMin)*i/20))*100).toInt()
                g.drawString(((x1.toDouble())/100).toString(),x-8,y0-3)
            }

            for (i in 0..20) {//горизонтальные числа
                val y = Converter.yCrt2Scr(plane.yMin + (((plane.yMax-plane.yMin)*i)/20),plane)
                //val x = Converter.xCrt2Scr((i / 10.0), plane)
                //g.drawString((((plane.xMin+(plane.xMax-plane.xMin)*i/20)*10).toInt()*0.1).toString(),x,y0)
                val y1=(((plane.yMin+(plane.yMax-plane.yMin)*i/20))*100).toInt()
                g.drawString(((y1.toDouble())/100).toString(),x0-8,y+3)
            }

        }
    }

}