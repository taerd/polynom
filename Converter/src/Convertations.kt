data class CartesianPlane(
        var xMin: Double,
        var xMax: Double,
        var yMin: Double,
        var yMax: Double
)

data class CartesianScreenPlane(
        var realWidth: Int,
        var realHeight: Int,
        var xMin: Double,
        var xMax: Double,
        var yMin: Double,
        var yMax: Double
){
    val width: Int
        get() = realWidth - 1
    val height: Int
        get() = realHeight - 1
    val xDen : Double//длина пикселей на отрезок
        get() = width.toDouble() / (xMax - xMin)
    val yDen : Double//длина отрезка на пиксель
        get() = height.toDouble() / (yMax - yMin)
}