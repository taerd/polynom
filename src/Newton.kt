open class Newton(xfx:MutableMap<Double,Double>):Polynom() {
    var xfx = xfx.toMutableMap()

    private val divDiff = mutableMapOf<Pair<Int, Int>, Double>()

    var dots = DoubleArray(xfx.size)
    private set

    var values = DoubleArray(xfx.size)
    private set

    init {
        var o = 0
        xfx.forEach {//сохранили значения узлов
            dots[o] = it.key
            o++
        }
        o = 0
        xfx.forEach {//сохранили значения
            values[o] = it.value
            o++
        }
        val p = Polynom()
        var i = 0
        var k = 1
        var r = Polynom(doubleArrayOf(1.0))
        xfx.forEach {//добавить в p f(x0)
            if (k < xfx.size) {
                r *= Polynom(doubleArrayOf(-1*it.key, 1.0))//получаем следущий множитель
                p += r * divDifference(0, i + 1)
                i++
                k++
            }
        }
        if(xfx.isNotEmpty()) {
            p += Polynom(doubleArrayOf(xfx.getValue(dots[0])))
        }
        coef = p.coefficients
    }

    private fun divDifference(first: Int, last: Int): Double {
        return when {
            divDiff.containsKey(Pair(first, last)) -> divDiff[Pair(first, last)] ?: error("")
            else -> {
                if (last - first <= 0) {
                    divDiff[Pair(first, first)] = values[last]
                    return values[last]
                } // return f(x)
                val left = divDifference(first + 1, last)
                val right = divDifference(first, last - 1)
                val difference = (left-right) / (dots[last] - dots[first])//left-right
                divDiff[Pair(first, last)] = difference
                return difference
            }
        }
    }
    val Epsilion = 0.05
    open fun addNote(x:Double,y:Double){
        for(i in 0..dots.size-1){
            if(dots[i]-Epsilion<x && dots[i]+Epsilion>x) {
                println("Невозможно добавить узел ($x,$y) так как он уже записан")
                return
            }
        }

        //изменение самих массивов с точками и значениями
        xfx[x] = y//добавили в mutablemap
        val dots1=DoubleArray(xfx.size)
        val values1=DoubleArray(xfx.size)
        for(i in 0..dots.size-1){
            dots1[i]=dots[i]
            values1[i]=values[i]
        }
        dots1[dots.size]=x
        values1[values.size]=y
        dots=dots1
        values=values1

        //построение самого полинома
        val p = Polynom()
        var i = 0
        var k = 1
        var r = Polynom(doubleArrayOf(1.0))
        xfx.forEach {//добавить в p f(x0)
            if (k < xfx.size) {
                r *= Polynom(doubleArrayOf(-1*it.key, 1.0))//получаем следущий множитель
                p += r * divDifference(0, i + 1)
                i++
                k++
            }
        }
        p += Polynom(doubleArrayOf(xfx.getValue(dots[0])))
        coef = p.coefficients
    }
}













