class Newton(xfx:MutableMap<Double,Double>):Polynom() {
    val xfx = xfx.toMutableMap()

    private val divDiff = mutableMapOf<Pair<Int, Int>, Double>()

    /*
    private val fundamemtal = Polynom(doubleArrayOf(1.0))
    private val dots = mutableMapOf<Double,Double>()
    init{
        var i = 0
        dots.forEach {x,y->//находим значения функции в точке и добавляем в dots
            divDiff.putIfAbsent(Pair(i,i),y)
            i++
            add(x,y)
        }
    }
     */
    private var dots: DoubleArray
        get() = dots.clone()
        set(value) {
            dots = value
        }
    private var values: DoubleArray
        get() = values.clone()
        set(value) {
            values = value
        }

    init {
        var o = 0
        xfx.forEach {//сохранили значения узлов
            dots[o] = it.key
            o++
        }
        o = 0
        xfx.forEach {//сохранили значения узлов
            values[o] = it.value
            o++
        }
        val p = Polynom()
        var i = 0
        var k = 1
        var r = Polynom(doubleArrayOf(1.0))
        xfx.forEach {//добавить в p f(x0)
            if (k < xfx.size) {
                r *= Polynom(doubleArrayOf(it.key, 1.0))//получаем следущий множитель
                p += r * divDifference(0, i + 1)
                i++
                k++
            }
        }
        p += Polynom(doubleArrayOf(xfx.getValue(dots[0])))

        /*
        var l = 0//добавить в p f(x0)
        xfx.forEach{
            if(l == 0) p+= Polynom(doubleArrayOf(it.value))
            l++
        }
         */
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
                val left = divDiff[Pair(first + 1, last)]
                val right = divDiff[Pair(first, last - 1)]
                if(last!=null && right!=null){
                    val difference = (left.toDouble() - right.toDouble()) / (dots[last] - dots[first])
                    divDiff[Pair(first, last)] = difference
                    return difference
                }
            }
        }
    }


}













