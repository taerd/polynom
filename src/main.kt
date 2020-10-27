fun main(){
/*
    println("Составление полиномов")
    val p1 = Polynom(doubleArrayOf(1.0, 0.0, 3.0, 0.0, 0.0))
    val p2 = Polynom(doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0))
    val p3 = Polynom(doubleArrayOf(3.0,1.0))
    val p4 = Polynom(doubleArrayOf(7.0, 0.0, 3.0, 0.0, 0.0))
    val p5 = Polynom(doubleArrayOf(-1.0, 0.0, 0.0, 0.0, 0.0))
    val p6 = Polynom(doubleArrayOf(1.0, 0.0, 0.0, 0.0, 0.0))

    println(p1)
    println(p2)
    println(p3)
    println(p4)
    println(p5)
    println(p6)
    println(p1(2.0))
    println("Лагранж вычисление")
    val l1 = Lagrange(mutableMapOf(
        -1.0 to 1.0,
        0.0 to 0.0,
        1.0 to 1.0
    ))
    println(l1)
 */

    println("Ньютон вычисление")
    val n1 = Newton(mutableMapOf(
            -1.0 to 7.5,
            1.0 to 1.0,
            2.0 to 12.0,
            3.0 to 6.0
    ))
    println(n1)
    val n2 = Newton(mutableMapOf(
            -1.0 to 7.5,
            1.0 to 1.0,
            2.0 to 12.0
    ))
    println(n2)
    println("Добавляем точку последнюю")
    n2.addNote(3.0,6.0)
    println(n2)
    val n3 = DerivedNewton(mutableMapOf(
            -1.0 to 7.5,
            1.0 to 1.0,
            2.0 to 12.0
    ))
    println("производный от n2 полином")
    println(n3)
    println("добавим точку в производный класс последнюю")
    n3.addNote(3.0, 6.0)
    println(n3)
    /*
    val p11 = Polynom(doubleArrayOf(-3.0,1.0))
    val p7 = Polynom(doubleArrayOf(-3.25,1.0))
    val p8 = Polynom(doubleArrayOf(-3.5,1.0))
    val p9 = Polynom(doubleArrayOf(-3.75,1.0))
    val p10 = Polynom(doubleArrayOf(-4.0,1.0))

    println(p7*p8*p9*p10*p11)
    println("Панель")
     */
    val w = MainWindow()
    w.isVisible =true

}