package br.com.cpcjrdev.data.di

import android.content.Context
import br.com.cpcjrdev.data.database.DatabaseProvider
import br.com.cpcjrdev.data.repository.TasksDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTasksDataRepository(
        @ApplicationContext context: Context,
    ): TasksDataRepository {
        val database = DatabaseProvider.getDatabase(context)
        return TasksDataRepository(database.tasksDao())
    }
}
