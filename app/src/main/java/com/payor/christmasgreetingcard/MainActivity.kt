package com.payor.christmasgreetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.payor.christmasgreetingcard.R
import com.payor.christmasgreetingcard.ui.theme.ChristmasGreetingCardTheme
import com.payor.christmasgreetingcard.ui.theme.Brown
import com.payor.christmasgreetingcard.ui.theme.Yellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChristmasGreetingCardTheme(dynamicColor = false) {
                val context = LocalContext.current
                val exoPlayer = remember {
                    ExoPlayer.Builder(context).build().apply { 
                        val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.merry_christmas_derven}")
                        setMediaItem(mediaItem)
                        repeatMode = Player.REPEAT_MODE_ONE
                        playWhenReady = true
                        prepare()
                    }
                }
                DisposableEffect(Unit) {
                    onDispose { 
                        exoPlayer.release()
                    }
                }
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppRoutes.MAIN_SCREEN,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(AppRoutes.MAIN_SCREEN) {
                            Box(modifier = Modifier.fillMaxSize()){

                                Greeting(
                                    exoPlayer = exoPlayer,
                                    onNavigate = { navController.navigate(AppRoutes.DONATION_SCREEN) },
                                    merry = "Merry",
                                    christmas = "Christmas!",
                                    to = "Sir Leopoldo",
                                    from = "From Matthew",
                                )
                            }
                        }
                        composable(AppRoutes.DONATION_SCREEN) {
                            DonationScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(exoPlayer: ExoPlayer, onNavigate: () -> Unit, merry: String, christmas: String, to: String, from: String, modifier: Modifier = Modifier) {
    FallingSnowflakes()
    var buttonColor by remember { mutableStateOf(Color(0xFFFFFFFF)) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VideoPlayer(
            exoPlayer = exoPlayer,
            modifier = Modifier.height(250.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.aaron_santa),
                contentDescription = "Aaron Santa",
                modifier = Modifier.size(220.dp)
            )
            Image(
                painter = painterResource(R.drawable.jamie_elf),
                contentDescription = "Jamie Elf",
                modifier = Modifier.size(200.dp).offset(y = (15).dp)
            )
        }
        Text(
            text = merry,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Yellow
        )
        Text(
            text = christmas,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Green
        )
        Text(
            text = to,
            style = MaterialTheme.typography.bodyLarge,
            color = Brown,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = from,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = onNavigate,
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = Color.Black),
            modifier = Modifier.size(width = 200.dp, height = 50.dp)

        ){
            Text("Click for a surprise!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChristmasGreetingCardTheme {
        // Preview can't create a media player, so we show placeholder text instead.
        Text("Greeting screen preview is disabled.")
    }
}
object AppRoutes {
    const val MAIN_SCREEN = "main_screen"
    const val DONATION_SCREEN = "donation_screen"
}
@Composable
fun DonationScreen(modifier: Modifier = Modifier) {
    FallingSnowflakes()
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.derven_christmas_elf),
                contentDescription = "Derven Christmas Elf",
                modifier = Modifier.size(150.dp)
            )
            Image(
                painter = painterResource(R.drawable.derven_greeting),
                contentDescription = "Derven Greeting",
                modifier = Modifier.size(150.dp)
            )
        }
        Text(
            text = ("Share your blessings"),
            style = MaterialTheme.typography.bodyLarge,
            color = Yellow,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = ("Sir Leopoldo!"),
            style = MaterialTheme.typography.bodyLarge,
            color = Yellow,
            modifier = Modifier.padding(top = 16.dp)
        )
        Image(
            painter = painterResource(R.drawable.matthew_maya_qr_code),
            contentDescription = "Matthew Maya QR Code",
            modifier = Modifier.size(400.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.ian_reindeer),
                contentDescription = "Ian Reindeer",
                modifier = Modifier.size(130.dp)
            )
            Image(
                painter = painterResource(R.drawable.shaun_reindeer),
                contentDescription = "Shaun Reindeer",
                modifier = Modifier.size(130.dp)
            )
            Image(
                painter = painterResource(R.drawable.eze_reindeer),
                contentDescription = "Eze Reindeer",
                modifier = Modifier.size(140.dp)
            )
        }
    }
}
