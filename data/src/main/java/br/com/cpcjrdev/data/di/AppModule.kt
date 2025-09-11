package br.com.cpcjrdev.data.di

import android.content.Context
import br.com.cpcjrdev.data.database.DatabaseProvider
import br.com.cpcjrdev.data.repository.TasksDataRepositoryImpl
import br.com.cpcjrdev.domain.repository.TaskRepository
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
    fun provideTasksRepository(
        @ApplicationContext context: Context,
    ): TaskRepository {
        val database = DatabaseProvider.getDatabase(context)
        return TasksDataRepositoryImpl(database.tasksDao())
    }
}
