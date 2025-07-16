package br.com.cpcjrdev.data.di

import android.content.Context
import br.com.cpcjrdev.data.database.DatabaseProvider
import br.com.cpcjrdev.data.repository.TasksDataRepository

/**
 * Dependency injection container for application-wide singletons.
 */
interface DependencyContainer {
    val tasksDataRepository: TasksDataRepository
}

class AppContainer(
    context: Context,
    private val database: DatabaseProvider = DatabaseProvider.getDatabase(context.applicationContext),
) : DependencyContainer {
    override val tasksDataRepository: TasksDataRepository by lazy {
        TasksDataRepository(database.tasksDao())
    }

    companion object {
        @Volatile
        private var instance: AppContainer? = null

        /**
         * Returns the singleton instance of [AppContainer].
         * Uses [applicationContext] to prevent Context leaks.
         */
        fun getInstance(context: Context): AppContainer =
            instance ?: synchronized(this) {
                instance ?: AppContainer(context.applicationContext).also { instance = it }
            }
    }
}
