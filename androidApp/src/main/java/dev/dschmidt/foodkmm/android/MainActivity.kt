package dev.dschmidt.foodkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.dschmidt.foodkmm.Greeting
import android.widget.TextView
import androidx.activity.compose.setContent
import dev.dschmidt.foodkmm.android.presentation.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }

    }
}

