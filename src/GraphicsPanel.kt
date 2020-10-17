import Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel: JPanel(){
    var painters = mutableListOf<Painter>()
    //var painter:Painter?=null
    set(value){
        field = value
        repaint()//вызываем paint еще раз
    }
    override fun paint(g: Graphics?) {
        super.paint(g)//отрисовывается графика
        painters.forEach{
            it.paint(g)
        }
        //painter?.paint(g)//рисуются элементы paintera
        //this.graphics
    }
    fun addPainter(painter: Painter){
        painters.add(painter)
        repaint()
    }
    fun removePainter(painter: Painter){
        painters.remove(painter)
        repaint()
    }
    /*
    fun updatePainters(){
        painters.forEach {painter->
            painter.up

        }
    }
     */
}