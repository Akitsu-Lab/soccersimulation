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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = SoccerScreen.PlayerList.name) {
                        composable(route = SoccerScreen.PlayerList.name) {
                            PlayerListScreen(onClickButton = { name, position ->
                                navController.navigate("${SoccerScreen.PlayerDetailedInfo.name}/$name/$position")})
                        }
                        composable(
                            route = "${SoccerScreen.PlayerDetailedInfo.name}/{name}/{position}",
                            arguments = listOf(
                                navArgument("name") { type = NavType.StringType },
                                navArgument("position") { type = NavType.StringType }
                            )
                        ){ backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name") ?: ""
                            val position = backStackEntry.arguments?.getString("position") ?: ""
                            PlayerDetailedInfoScreen(name = name, position = position, onClickButton = {navController.navigateUp()})
                        }
                    }
                }
            }
        }
    }
}

enum class SoccerScreen(){
    PlayerList,
    PlayerDetailedInfo
}

@Composable
fun PlayerDetailedInfoScreen(name: String, position: String, onClickButton: () -> Unit, modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column {
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
            TextButton(onClick = onClickButton) {
                Text(text = "戻る")
            }
        }

    }
}

@Composable
fun PlayerListScreen(onClickButton: (String, String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        PlayerOverView(name = "織田 信長", position = "FW", onClickButton = {onClickButton("織田 信長", "FW")})
        PlayerOverView(name = "豊臣 秀吉", position = "FW", onClickButton = {onClickButton("豊臣 秀吉", "FW")})
        PlayerOverView(name = "徳川 家康", position = "FW", onClickButton = {onClickButton("徳川 家康", "FW")})
        PlayerOverView(name = "前田 利家", position = "FW", onClickButton = {onClickButton("前田 利家", "FW")})
        PlayerOverView(name = "香川 真司", position = "MF", onClickButton = {onClickButton("香川 真司", "MF")})
        PlayerOverView(name = "本田 圭佑", position = "MF", onClickButton = {onClickButton("本田 圭佑", "MF")})
        PlayerOverView(name = "岡崎 慎二", position = "FW", onClickButton = {onClickButton("岡崎 慎二", "FW")})
        PlayerOverView(name = "長友 佑都", position = "DF", onClickButton = {onClickButton("長友 佑都", "DF")})
    }
}

@Composable
fun PlayerOverView(
    name: String,
    position: String,
    onClickButton: () -> Unit
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
            TextButton(onClick = onClickButton) {
                Text(
                    text = name,
                )
            }
            Text(
                text = "ポジション: $position",
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerListPreview() {
    SoccersimulationTheme {
        PlayerListScreen(onClickButton = {_,_ ->})
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerInfoPreview() {
    SoccersimulationTheme {
        PlayerDetailedInfoScreen(name = "長岡 慶太", position = "CEO", onClickButton = {})
    }
}