package com.jetpackcomposecourse.ui.practice.kotlin

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val smartTvDevice = SmartTvDevice("Android TV", "Entertainment")
    //smartTvDevice.turnOn()

    val smartLightDevice = SmartLightDevice("Google Light", "Utility")
    //smartLightDevice.turnOn()

    val smartHome = SmartHome(smartTvDevice, smartLightDevice)
    smartHome.turnOnTv()
    smartHome.increaseTvVolume()
    smartHome.printSmartTvInfo()
    smartHome.printSmartLightInfo()
}

open class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "online"
    open val deviceType = "unknown"

    /*constructor(name: String, category: String, statusCode: Int) : this(name, category) {
        deviceStatus = when (statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }*/

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {
    // Smart TV IS-A smart device.
    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun decreaseSpeakerVolume() {
        speakerVolume--
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber.")
    }

    fun previousChannel() {
        channelNumber--
        println("Channel number increased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }

}

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {
    // Smart Light IS-A smart device.
    override val deviceType = "Smart Light"

    private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness increased to $brightnessLevel.")
    }

    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }

}

class SmartHome(
    private val smartTvDevice: SmartTvDevice,
    private val smartLightDevice: SmartLightDevice
) {
    // The SmartHome class HAS-A smart TV device and smart light.
    var deviceTurnOnCount by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        if(verifyIfDeviceStatusIsOn(smartTvDevice.deviceStatus)) {
            deviceTurnOnCount--
            smartTvDevice.turnOff()
        }
    }

    fun increaseTvVolume() {
        if(verifyIfDeviceStatusIsOn(smartTvDevice.deviceStatus)) {
            smartTvDevice.increaseSpeakerVolume()
        }
    }

    fun decreaseTvVolume() {
        if(verifyIfDeviceStatusIsOn(smartTvDevice.deviceStatus)) {
            smartTvDevice.decreaseSpeakerVolume()
        }
    }

    fun changeTvChannelToNext() {
        if(verifyIfDeviceStatusIsOn(smartTvDevice.deviceStatus)) {
            smartTvDevice.nextChannel()
        }
    }

    fun changeTvChannelToPrevious() {
        if(verifyIfDeviceStatusIsOn(smartTvDevice.deviceStatus)) {
            smartTvDevice.previousChannel()
        }
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        if(verifyIfDeviceStatusIsOn(smartLightDevice.deviceStatus)) {
            deviceTurnOnCount--
            smartLightDevice.turnOff()
        }
    }

    fun increaseLightBrightness() {
        if(verifyIfDeviceStatusIsOn(smartLightDevice.deviceStatus)) {
            smartLightDevice.increaseBrightness()
        }
    }

    fun decreaseLightBrighness() {
        if(verifyIfDeviceStatusIsOn(smartLightDevice.deviceStatus)) {
            smartLightDevice.decreaseBrightness()
        }
    }

    fun turnOffAllDevices() {
        if(verifyIfDeviceStatusIsOn(smartLightDevice.deviceStatus)) {
            turnOffLight()
        }

        if(verifyIfDeviceStatusIsOn(smartTvDevice.deviceStatus)) {
            turnOffTv()
        }
    }

    fun verifyIfDeviceStatusIsOn(deviceStatus: String): Boolean {
        return deviceStatus.lowercase() == "on"
    }

    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }

    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }

}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {
    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }

}