package com.gmail.kaminskysem.PersnalHelper.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.kaminskysem.PersnalHelper.R
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.PlanerActivity

class MainActivity : ComponentActivity() {
    //TODO add orientation to mode 2 apps in one screen
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(
                onPlanerClik = {
                    val mainActPlaner = Intent(this@MainActivity, PlanerActivity::class.java)
                    startActivity(mainActPlaner)
                },
                onTimerCkick = {
                    val mainActTimer = Intent(this@MainActivity, TimerActivity::class.java)
                    startActivity(mainActTimer)
                }
            )
        }
    }

    @Composable
    fun MainScreen(
        onPlanerClik: () -> Unit,
        onTimerCkick: () -> Unit
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img_voda),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp, start = 36.dp, end = 36.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onTimerCkick() }
                ) {
                    Image(
                        modifier = Modifier.size(width = 80.dp, height = 84.dp),
                        painter = painterResource(R.drawable.ic_clock1_foreground),
                        contentDescription = "timer"
                    )
                    Text(
                        modifier = Modifier.background(Color.Yellow),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black,
                        text = stringResource(R.string.clock_icon_name).uppercase()
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onPlanerClik() }
                ) {
                    Image(
                        modifier = Modifier.size(width = 80.dp, height = 84.dp),
                        painter = painterResource(R.drawable.ic_planer_foreground),
                        contentDescription = "planer"
                    )
                    Text(
                        modifier = Modifier.background(Color.Yellow),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        text = stringResource(R.string.planer_ic_name)
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun MainPrew() {
        MainScreen(
            onPlanerClik = { print("click planer") },
            onTimerCkick = { print("click timer") }
        )
    }
}