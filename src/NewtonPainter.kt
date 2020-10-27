import java.awt.*

class NewtonPainter(val plane:CartesianScreenPlane, val newton:Newton):Painter {

    private var color = Color.GREEN
    public fun setColor(newColor: Color){color = newColor}

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
            val g2 = g as Graphics2D
            g2.stroke=BasicStroke(2.0f)
            g2.color=color
            val rh = mapOf(
                    RenderingHints.KEY_ANTIALIASING to RenderingHints.VALUE_ANTIALIAS_ON,
                    RenderingHints.KEY_INTERPOLATION to RenderingHints.VALUE_INTERPOLATION_BILINEAR,
                    RenderingHints.KEY_RENDERING to RenderingHints.VALUE_RENDER_QUALITY,
                    RenderingHints.KEY_DITHERING to RenderingHints.VALUE_DITHER_ENABLE
            )
            g2.setRenderingHints(rh)
            val n = (plane.realWidth)//количество разбиений
            //val n = ((plane.xMax-plane.xMin)*10).toInt()//количество разбиений
            val pointsArray = mutableMapOf<Double,Double>()//точек на 1 больше чем разбиений
            if(newton.dots.size!=0){
                for(i in 0..n){
                    //добавление точек в карту с его значениями
                    pointsArray[plane.xMin + ((plane.xMax-plane.xMin)*i)/n] = newton.invoke(plane.xMin + ((plane.xMax-plane.xMin)*i)/n)
                }
                //отрисовываем линии между двумя соседними точками
                for(i in 0..pointsArray.size-2){
                    g2.drawLine(
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