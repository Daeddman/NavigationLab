package com.example.navigationlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.navigationlab.ui.NavigationLabApp
import com.example.navigationlab.ui.theme.NavigationLabTheme
import com.example.navigationlab.viewmodel.LabViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val model = ViewModelProvider(this)[LabViewModel::class.java]

        setContent {
            NavigationLabTheme {
                NavigationLabApp(model)
            }
        }
    }
}
