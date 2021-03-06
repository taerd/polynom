import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.GroupLayout
import javax.swing.JFrame
import javax.swing.JPanel

class MainWindow : JFrame(){

    private val minSize = Dimension(550, 400)
    private val mainPanel: JPanel
    private val controlPanel: ControlPanel
    private var polynomNewton = Newton(mutableMapOf())
    private var derivedNewton = DerivedNewton(mutableMapOf())
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(minSize.width+200, minSize.height+400)
        mainPanel = GraphicsPanel()
        mainPanel.background=Color.WHITE
        controlPanel = ControlPanel()

        val gl = GroupLayout(contentPane)

        gl.setVerticalGroup(gl.createSequentialGroup()//размещение панели и лагранжаконтрол
                .addGap(4)
                .addComponent(mainPanel, minSize.height, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()//размещение панели и лагранжаконтрол
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(mainPanel, minSize.width, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4))
        layout = gl
        pack()

        val plane=CartesianScreenPlane(
                mainPanel.width,mainPanel.height,
                controlPanel.smXMin.number.toDouble(),
                controlPanel.smXMax.number.toDouble(),
                controlPanel.smYMin.number.toDouble(),
                controlPanel.smYMax.number.toDouble()
        )
        val dp = CartesianPainter(plane)
        val newtonp = NewtonPainter(plane,polynomNewton)
        val derivednp = NewtonPainter(plane,derivedNewton)
        mainPanel.addComponentListener(object: ComponentAdapter(){
            override fun componentResized(e: ComponentEvent?){
                dp.plane.realWidth=mainPanel.width
                newtonp.plane.realWidth=mainPanel.width
                derivednp.plane.realWidth=mainPanel.width

                dp.plane.realHeight=mainPanel.height
                newtonp.plane.realHeight=mainPanel.height
                derivednp.plane.realHeight=mainPanel.height

                mainPanel.repaint()
            }
        })
        mainPanel.addMouseListener(object : MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                super.mouseClicked(e)
                if(e!=null){
                    //проверка на клик по панели mainPanel
                    if(e.point.x < plane.realWidth && e.point.x > 0 && e.point.y > 0 && e.point.y < plane.realHeight ){
                        //добавление в полином точек
                        polynomNewton.addNote(Converter.xScr2Crt(e.point.x,plane),Converter.yScr2Crt(e.point.y,plane))
                        derivedNewton.addNote(Converter.xScr2Crt(e.point.x,plane),Converter.yScr2Crt(e.point.y,plane))
                        mainPanel.repaint()
                    }
                }
            }
        })

        controlPanel.addValChangeListener {
            dp.plane.xMin=controlPanel.smXMin.number.toDouble()
            newtonp.plane.xMin=controlPanel.smXMin.number.toDouble()
            derivednp.plane.xMin=controlPanel.smXMin.number.toDouble()

            dp.plane.xMax=controlPanel.smXMax.number.toDouble()
            newtonp.plane.xMax=controlPanel.smXMax.number.toDouble()
            derivednp.plane.xMax=controlPanel.smXMax.number.toDouble()

            dp.plane.yMin=controlPanel.smYMin.number.toDouble()
            newtonp.plane.yMin=controlPanel.smYMin.number.toDouble()
            derivednp.plane.yMin=controlPanel.smYMin.number.toDouble()

            dp.plane.yMax=controlPanel.smYMax.number.toDouble()
            newtonp.plane.yMax=controlPanel.smYMax.number.toDouble()
            derivednp.plane.yMax=controlPanel.smYMax.number.toDouble()

            mainPanel.repaint()
        }

        controlPanel.addColorListener {
            newtonp.setColor(controlPanel.getColor1())
            derivednp.setColor(controlPanel.getColor2())
            mainPanel.paint(mainPanel.graphics)
        }

        mainPanel.addPainter(dp)
        mainPanel.addPainter(newtonp)
        mainPanel.addPainter(derivednp)
        //mainPanel.painter=polynomp
    }
}