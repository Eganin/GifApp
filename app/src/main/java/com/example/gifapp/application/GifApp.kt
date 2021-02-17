package com.example.gifapp.application

import android.app.Application
import com.example.gifapp.di.AppComponent

class GifApp : Application() {

    val component : AppComponent by lazy { AppComponent() }
}