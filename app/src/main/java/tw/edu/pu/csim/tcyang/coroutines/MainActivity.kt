package tw.edu.pu.csim.tcyang.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.lifecycleScope
import tw.edu.pu.csim.tcyang.coroutines.ui.theme.CoroutinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val game = Game(lifecycleScope)

        enableEdgeToEdge()
        setContent {
            CoroutinesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding), game)
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier, game: Game) {
    val counter by game.state.collectAsState()

    counter.let {
        //繪製背景
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.forest),
                contentDescription = "背景",

                contentScale = ContentScale.FillBounds,  //縮放符合螢幕寬度
                modifier = Modifier
                    .offset { IntOffset(game.background.x1, 0) }
            )
        }

        Column {
            Button(
                modifier = modifier,
                onClick = {
                    game.Play()
                }
            ) {
                Text(text = "遊戲開始")
            }
            Text(text = game.background.x1.toString())
        }
    }
}
