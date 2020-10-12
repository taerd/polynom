fun main(){
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
    println("Ньютон вычисление")
    val n1 = Newton(mutableMapOf(
            -1.0 to 4.0,
            0.0 to 2.0,
            1.0 to 0.0,
            2.0 to 1.0
    ))
    println(n1)
    val n2 = Newton(mutableMapOf(
            0.0 to -1.0,
            1.0 to 1.0,
            0.5 to 1.0
    ))
    println(n2)

}