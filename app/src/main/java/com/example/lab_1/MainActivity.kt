package com.example.lab_1

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab_1.ui.theme.Lab_1Theme

class MainActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(Modifier.padding(innerPadding)) { playAudio() }
                }
            }
        }
    }

    private fun playAudio() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.the_audio)
            mediaPlayer?.start()
        } else {
            mediaPlayer?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

@Composable
fun AppContent(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    val message = remember { mutableStateOf("Press the button to play audio") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.screamo1),
            contentDescription = "App Image",
            modifier = Modifier.padding(16.dp).size(400.dp)

        )
        Text(text = message.value, modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                onButtonClick()
                message.value = "Audio is playing..."
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Play Audio")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    Lab_1Theme {
        AppContent(onButtonClick = {})
    }
}

