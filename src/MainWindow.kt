import LagrangeControl
import java.awt.Color
import java.awt.Dimension
import javax.swing.GroupLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.border.EtchedBorder

class MainWindow : JFrame(){

    private val minSize = Dimension(550, 400)
    private val mainPanel: JPanel
    private val controlPanel: LagrangeControl
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(minSize.width+200, minSize.height+400)
        //mainPanel = JPanel()
        val dp = CartesianPanter()
        mainPanel = GraphicsPanel(dp)
        mainPanel.background = Color.WHITE
        controlPanel = LagrangeControl()
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
    }
}