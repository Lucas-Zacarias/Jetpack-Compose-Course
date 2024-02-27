package com.jetpackcomposecourse.ui.practice.kotlin

fun main() {
    task1()
    val events = task3()
    task4(events)
    task5(events)
    task6(events)
    task7(events)
}

fun task1() {
    val event = Event("Study Kotlin", "Commit to studying Kotlin at least 15 minutes per day", DayPart.EVENING, 15)
    println(event)
}

fun task3(): List<Event> {
    //Problem
    val event1 = Event(title = "Wake up", description = "Time to get up", dayPart = DayPart.MORNING, duration = 0)
    val event2 = Event(title = "Eat breakfast", dayPart = DayPart.MORNING, duration = 15)
    val event3 = Event(title = "Learn about Kotlin", dayPart = DayPart.AFTERNOON, duration = 30)
    val event4 = Event(title = "Practice Compose", dayPart = DayPart.AFTERNOON, duration = 60)
    val event5 = Event(title = "Watch latest DevBytes video", dayPart = DayPart.AFTERNOON, duration = 10)
    val event6 = Event(title = "Check out latest Android Jetpack library", dayPart = DayPart.EVENING, duration = 45)

    //Solution
    val events = mutableListOf<Event>(
        Event(title = "Wake up", description = "Time to get up", dayPart = DayPart.MORNING, duration = 0),
        Event(title = "Eat breakfast", dayPart = DayPart.MORNING, duration = 15),
        Event(title = "Learn about Kotlin", dayPart = DayPart.AFTERNOON, duration = 30),
        Event(title = "Practice Compose", dayPart = DayPart.AFTERNOON, duration = 60),
        Event(title = "Watch latest DevBytes video", dayPart = DayPart.AFTERNOON, duration = 10),
        Event(title = "Check out latest Android Jetpack library", dayPart = DayPart.EVENING, duration = 45)
    )
    return events
}

fun task4(events: List<Event>) {
    val shortEvents = events.filter {
        it.duration < 60
    }
    println("You have ${shortEvents.size} short events")
}

fun task5(events: List<Event>) {
    val eventsByDayPart = events.groupBy {
        it.dayPart
    }
    eventsByDayPart.forEach { (dayPart, events) ->
        println("$dayPart: ${events.size} events")
    }
}

fun task6(events: List<Event>) {
    print("Last event of the day: ${events.last().title}")
}

fun task7(events: List<Event>) {
    println("Duration of the first event of the day: ${events.first().durationOfEvent}")
}

data class Event(
    val title: String,
    val description: String? = null,
    val dayPart: DayPart,
    val duration: Int
)

val Event.durationOfEvent: String
    get() = if(duration < 60) {
        "short"
    } else {
        "long"
    }
//This enum class is part of task 2
enum class DayPart {
    MORNING,
    AFTERNOON,
    EVENING
}
