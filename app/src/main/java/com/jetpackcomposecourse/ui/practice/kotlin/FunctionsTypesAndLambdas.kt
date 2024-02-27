package com.jetpackcomposecourse.ui.practice.kotlin

fun main() {
    //Option 1
    /*val trickFunction = trick()
    trickFunction*/

    //Option 2
    /*val trickFunction = ::trick
    trickFunction()*/

    //Option 3
    /*val trickFunctionLambda = trick
    trick()
    trickFunctionLambda()
    treat()*/

    val treatFunction = trickOrTreat(false) { "$it quarters" }
    val trickFunction = trickOrTreat(true, null)

    repeat(4) {
        treatFunction()
    }
    trickFunction()
}

val trick = {
    println("No treats!")
}

val treat: () -> Unit = {
    println("Have a treat!")
}
/*
fun trick() {
    println("No treats!")
}*/

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    return if (isTrick) {
        trick
    } else {
        if(extraTreat != null) {
            println(extraTreat(5))
        }
        treat
    }
}