package com.flights.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Airport::class, Favorite::class],
    version = 1
)
abstract class FlightDatabase : RoomDatabase() {
    abstract fun airportDao(): AirportDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var Instance: FlightDatabase? = null

        fun getDatabase(context: Context): FlightDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FlightDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/flight_search.db")
                    .build()
                Instance = instance
                instance
            }
        }
    }
}