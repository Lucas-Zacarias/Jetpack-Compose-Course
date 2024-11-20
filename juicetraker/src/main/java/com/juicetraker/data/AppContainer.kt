package com.juicetraker.data

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val trackerRepository: JuiceRepository
}