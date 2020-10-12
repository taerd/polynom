import Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(private val painter: Painter) : JPanel(){
    override fun paint(g: Graphics?) {
        super.paint(g)//буферизованная графика
        painter.paint(g)//
        //this.graphics
    }
}