package com.jetpackcomposecourse.ui.practice.kotlin

fun main() {
    /*val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    println("Full menu:")
    fullMenu.forEach {
        println(it)
    }*/

    val groupedMenu = cookies.groupBy {
        it.softBaked
    }
    val softBakedMenu = groupedMenu[true] ?: listOf()
    val crunchyMenu = groupedMenu[false] ?: listOf()

    /*println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }*/

    /*
    //Alternative a group by example, using partition:
    Splits the original collection into pair of lists,
    where first list contains elements for which predicate yielded true,
    while second list contains elements for which predicate yielded false.

    val (softBakedMenu, crunchyMenu) = cookies.partition {
        it.softBaked
    }

    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }*/

    //The fold() function is used to generate a single value from a collection.
    /*fold() is sometimes called reduce().
    The fold() function in Kotlin works the same as the reduce() function found in JavaScript, Swift, Python, etc.
    Note that Kotlin also has its own function called reduce(),
    where the accumulator starts with the first element in the collection,
    rather than an initial value passed as an argument.
    Kotlin collections also have a sum() function for numeric types, as well as a higher-order sumOf() function.*/
    val totalPrice = cookies.fold(0.0) {total, cookie ->
        total + cookie.price
    }
    //println("Total price: $$totalPrice")

    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }
    println("Alphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }
}

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)