package com.kotlin.ui.kotlin

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }
    /*val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
            println(getWeatherReport())
            println("Have a good day!")
            *//*launch {
                printForecast()
            }
            launch {
                printTemperature()
            }*//*
        }
    }
    println("Execution time: ${time / 1000.0} seconds")*/
}

private suspend fun getWeatherReport() = coroutineScope {
    val forecast: Deferred<String> = async {
        getForecast()
    }
    val temperature = async { getTemperature() }

    delay(200)
    temperature.cancel()

    forecast.await()

    /*val temperature: Deferred<String> = async {
        try {
            getTemperature()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }
    "${forecast.await()} ${temperature.await()}"*/
}

private suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

private suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}

private suspend fun printForecast() {
    delay(1000)
    println("Sunny")
}

private suspend fun printTemperature() {
    delay(1000)
    println("30\u00b0C")
}