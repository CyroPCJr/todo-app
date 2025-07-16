package br.com.cpcjrdev.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.cpcjrdev.data.dao.TasksDao
import br.com.cpcjrdev.data.database.DatabaseProvider.Companion.getDatabase
import br.com.cpcjrdev.data.entities.TasksEntity

/**
 * Room Database provider and singleton holder for the application.
 *
 * Use [getDatabase] to retrieve the singleton instance.
 * Uses [context.applicationContext] to avoid memory leaks.
 *
 * @note Migration responsibility is on the developer as fallbackToDestructiveMigration(false) is set.
 */
@Database(
    version = 1,
    entities = [TasksEntity::class],
)
abstract class DatabaseProvider : RoomDatabase() {
    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile
        private var instance: DatabaseProvider? = null

        /**
         * Returns the singleton instance of DatabaseProvider.
         *
         * Uses the [applicationContext] to prevent leaking an Activity or other Context.
         * @param context any context (application context will be used internally)
         * @return database singleton instance
         */

        fun getDatabase(context: Context): DatabaseProvider {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return instance ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context.applicationContext, // safer context
                        DatabaseProvider::class.java,
                        "todo_db",
                    ).fallbackToDestructiveMigration(false) // you must provide migrations for db upgrades
                    .build()
                    .also { instance = it }
            }
        }
    }
}
