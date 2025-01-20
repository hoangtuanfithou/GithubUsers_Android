package com.example.githubusers.app

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class App : Application() {
    companion object {
        private var contextRef: WeakReference<Context>? = null

        val context: Context?
            get() = contextRef?.get()
    }

    override fun onCreate() {
        super.onCreate()
        contextRef = WeakReference(applicationContext)
    }
}
