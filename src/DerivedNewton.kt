class DerivedNewton(xfx:MutableMap<Double,Double>):Newton(xfx) {
    var multipliers= this.coefficients
    var newCoef= DoubleArray(if(this.coefficients.size == 1) 1 else this.coefficients.size-1,{0.0})

    init{
        if(this.coefficients.size < 2) newCoef[0]=0.0
        for (i in 1.. this.coefficients.size-1){
            newCoef[i-1]=i*multipliers[i]
        }
        this.coef= newCoef
    }
    override fun addNote(x:Double,y:Double){
        super.addNote(x,y)

        if(this.coefficients.size<2) newCoef
        newCoef=DoubleArray(if(this.coefficients.size-1 < 1) 1 else this.coefficients.size-1)
        multipliers=this.coefficients
        if(this.coefficients.size < 2) newCoef[0]=0.0
        for (i in 1.. this.coefficients.size-1){
            newCoef[i-1]=i*multipliers[i]
        }
        this.coef= newCoef
    }
}