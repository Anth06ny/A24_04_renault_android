package com.amonteiro.a24_04_renault_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.amonteiro.a24_04_renault_android.ui.screens.SearchScreen
import com.amonteiro.a24_04_renault_android.ui.theme.A24_04_renault_androidTheme
import com.amonteiro.a24_04_renault_android.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            A24_04_renault_androidTheme {

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SearchScreen(MainViewModel())
                }
            }
        }
    }
}