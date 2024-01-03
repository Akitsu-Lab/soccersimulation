package com.akitsulab.soccersimulation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akitsulab.soccersimulation.ui.theme.SoccersimulationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoccersimulationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        PlayerOverView(name = "織田 信長", position = "FW")
        PlayerOverView(name = "豊臣 秀吉", position = "FW")
        PlayerOverView(name = "徳川 家康", position = "FW")
        PlayerOverView(name = "前田 利家", position = "FW")
        PlayerOverView(name = "香川 真司", position = "MF")
        PlayerOverView(name = "本田 圭佑", position = "MF")
        PlayerOverView(name = "岡崎 慎二", position = "FW")
        PlayerOverView(name = "長友 佑都", position = "DF")
    }
}

@Composable
fun PlayerOverView(
    name: String,
    position: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_user),
            contentDescription = "ユーザーアイコン",
            modifier = Modifier
                .size(100.dp)
        )
        Column {
            Text(
                text = name,
            )
            Text(
                text = "ポジション: $position",
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoccersimulationTheme {
        MainContent()
    }
}