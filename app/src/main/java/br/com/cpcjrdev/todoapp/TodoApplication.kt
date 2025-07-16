package br.com.cpcjrdev.todoapp

import android.app.Application
import br.com.cpcjrdev.data.di.AppContainer

class TodoApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer.getInstance(this)
    }
}
