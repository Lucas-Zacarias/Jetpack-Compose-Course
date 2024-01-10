package com.jetpackcomposecourse.ui.practice.kotlin

fun main() {
    //planetsArray()
    /*val solarSystem = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    solarSystem.add("Pluto")
    solarSystem.add(3, "Theia")
    solarSystem[3] = "Future Moon"
    println(solarSystem)
    solarSystem.removeAt(9)
    solarSystem.remove("Future Moon")
    println(solarSystem.contains("Pluto"))
    println("Future Moon" in solarSystem)*/

    /*val solarSystemSet = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    println(solarSystemSet.size)
    solarSystemSet.add("Pluto")
    println(solarSystemSet.size)
    solarSystemSet.add("Pluto")
    println(solarSystemSet.size)
    solarSystemSet.remove("Pluto")
    println(solarSystemSet.size)
    println("Pluto" in solarSystemSet)*/

    val solarSystemMap = mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
        "Jupiter" to 79,
        "Saturn" to 82,
        "Uranus" to 27,
        "Neptune" to 14
    )

    solarSystemMap["Pluto"] = 5
    solarSystemMap.remove("Uranus")
    solarSystemMap["Jupiter"] = 78
    println(solarSystemMap["Jupiter"])
}

private fun planetsArray() {
    val rockPlanets = arrayOf("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")
    val solarSystem = rockPlanets + gasPlanets
    println(solarSystem[0])
    println(solarSystem[1])
    println(solarSystem[2])
    println(solarSystem[3])
    println(solarSystem[4])
    println(solarSystem[5])
    println(solarSystem[6])
    println(solarSystem[7])
    solarSystem[3] = "Little Earth"
    println(solarSystem[3])
    val newSolarSystem = arrayOf(
        "Mercury",
        "Venus",
        "Earth",
        "Mars",
        "Jupiter",
        "Saturn",
        "Uranus",
        "Neptune",
        "Pluto"
    )
    println(newSolarSystem[8])
}