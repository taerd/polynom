class Newton(xfx:MutableMap<Double,Double>):Polynom() {
    val xfx = xfx.toMutableMap()

    private val divDiff = mutableMapOf<Pair<Int, Int>, Double>()

    private var dots = DoubleArray(xfx.size)

    private var values = DoubleArray(xfx.size)

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
        p += Polynom(doubleArrayOf(xfx.getValue(dots[0])))
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


}













