import java.awt.Color
import java.awt.Graphics

class NewtonPainter(val plane:CartesianScreenPlane, val newton:Newton):Painter {

    override fun paint(g:Graphics?){
        drawPoints(g)
        drawLines(g)
    }

    private fun drawPoints(g:Graphics?){//лучше вынести это в отдельный класс pointsPainter
        if(g!=null){
            g.color=Color.GREEN
            val pointsArray = mutableMapOf<Double,Double>()
            for(i in 0..newton.dots.size-1){
                pointsArray[newton.dots[i]]=newton.values[i]
            }

            for(i in 0..pointsArray.size-1){
                g.fillOval(
                        Converter.xCrt2Scr(pointsArray.keys.elementAt(i),plane)-2,
                        Converter.yCrt2Scr(pointsArray.values.elementAt(i),plane)-2,
                        5,
                        5
                )
            }
        }
    }

    private fun drawLines(g:Graphics?){
        if(g!=null){
            g.color= Color.BLUE
            val n = ((plane.xMax-plane.xMin)*10).toInt()//количество разбиений
            val pointsArray = mutableMapOf<Double,Double>()//точек на 1 больше чем разбиений
            if(newton.dots.size!=0){
                for(i in 0..n){
                    //добавление точек в карту с его значениями
                    pointsArray[plane.xMin + ((plane.xMax-plane.xMin)*i)/n] = newton.invoke(plane.xMin + ((plane.xMax-plane.xMin)*i)/n)
                }
                //отрисовываем линии между двумя соседними точками
                for(i in 0..pointsArray.size-2){
                    g.drawLine(
                            Converter.xCrt2Scr(pointsArray.keys.elementAt(i),plane),//x1
                            Converter.yCrt2Scr(pointsArray.values.elementAt(i),plane),//y1
                            Converter.xCrt2Scr(pointsArray.keys.elementAt(i+1),plane),//x2
                            Converter.yCrt2Scr(pointsArray.values.elementAt(i+1),plane)//y2
                    )
                }
            }

        }
    }
}