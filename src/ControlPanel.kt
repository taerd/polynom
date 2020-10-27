import java.awt.Color
import javax.swing.*
import javax.swing.border.EtchedBorder

class ControlPanel : JPanel(){

    private val lXMin: JLabel
    private val lXMax: JLabel
    private val lYMin: JLabel
    private val lYMax: JLabel

    private val sXMin: JSpinner
    private val sXMax: JSpinner
    private val sYMin: JSpinner
    private val sYMax: JSpinner

    val smXMin: SpinnerNumberModel
    val smXMax: SpinnerNumberModel
    val smYMin: SpinnerNumberModel
    val smYMax: SpinnerNumberModel

    val colorChooser = JColorChooser()
    val chButton1= JButton("Цвет графика")
    val chButton2= JButton("Цвет производной")

    private var color1= Color.BLUE
    fun getColor1():Color {return color1}

    private var color2= Color.RED
    fun getColor2():Color {return color2}

    companion object{
        private val MIN_SZ = GroupLayout.PREFERRED_SIZE
        private val MAX_SZ = GroupLayout.DEFAULT_SIZE
    }

    private val valChangeListeners = mutableListOf<()->Unit>()
    private val colorListeners = mutableListOf<()->Unit>()


    init{
        border = EtchedBorder()

        lXMin = JLabel()
        lXMax = JLabel()
        lYMin = JLabel()
        lYMax = JLabel()
        lXMin.text = "Xmin:"
        lXMax.text = "Xmax:"
        lYMin.text = "Ymin:"
        lYMax.text = "Ymax:"

        smXMin = SpinnerNumberModel (-5.0, -100.0, 4.9, 0.1)
        smXMax = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)
        smYMin = SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1)
        smYMax = SpinnerNumberModel(5.0, -4.9, 100.0, 0.1)

        //меняем границы спиннеров
        smXMin.addChangeListener{
            smXMax.minimum = smXMin.number.toDouble() + 0.1
            valChangeListeners.forEach{it()}
        }
        smXMax.addChangeListener{
            smXMin.maximum = smXMax.number.toDouble() - 0.1
            valChangeListeners.forEach{it()}
        }
        smYMin.addChangeListener{
            smYMax.minimum = smYMin.number.toDouble() + 0.1
            valChangeListeners.forEach{it()}
        }
        smYMax.addChangeListener{
            smYMin.maximum = smYMax.number.toDouble() - 0.1
            valChangeListeners.forEach{it()}
        }

        chButton1.addActionListener {
            run {
                color1 = JColorChooser.showDialog(this, "Select a color", Color.RED)
                colorListeners.forEach { l -> l() }
            }
        }
        chButton2.addActionListener {
            run {
                color2 = JColorChooser.showDialog(this, "Select a color", Color.RED)
                colorListeners.forEach { l -> l() }
            }
        }

        sXMin = JSpinner(smXMin)
        sXMax = JSpinner(smXMax)
        sYMin = JSpinner(smYMin)
        sYMax = JSpinner(smYMax)

        val gl = GroupLayout(this)
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(chButton1,MIN_SZ,MIN_SZ,MIN_SZ)
                                .addComponent(chButton2,MIN_SZ,MIN_SZ,MIN_SZ)
                )
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(sYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
        )

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(4)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(chButton1,MIN_SZ,MIN_SZ,MIN_SZ)
                                        .addComponent(lXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(lYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(2)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(sXMin, MIN_SZ, 100, MAX_SZ)
                                        .addComponent(sYMin, MIN_SZ, 100, MAX_SZ)
                        )
                        .addGap(8, 8, Int.MAX_VALUE)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(chButton2,MIN_SZ,MIN_SZ,MIN_SZ)
                                        .addComponent(lXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(lYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(2)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(sXMax, MIN_SZ, 100, MAX_SZ)
                                        .addComponent(sYMax, MIN_SZ, 100, MAX_SZ)
                        )
                        .addGap(4)
        )
        layout = gl
    }
    fun addValChangeListener(l: ()-> Unit){
        valChangeListeners.add(l)
    }
    fun removeValChangeListener(l:()->Unit){
        valChangeListeners.remove(l)
    }

    fun addColorListener(l: () -> Unit){
        colorListeners.add(l)
    }
    fun removeColorListener(l: () -> Unit){
        colorListeners.remove(l)
    }
}