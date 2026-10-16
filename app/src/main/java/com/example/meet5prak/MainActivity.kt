package com.example.meet5prak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.meet5prak.ui.theme.Meet5PrakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Meet5PrakTheme {
                FormBiodataScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormBiodataPreview() {
    Meet5PrakTheme {
        FormBiodataScreen()
    }
}