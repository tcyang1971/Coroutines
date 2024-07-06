package tw.edu.pu.csim.tcyang.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import tw.edu.pu.csim.tcyang.coroutines.ui.theme.CoroutinesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val game = Game(this, lifecycleScope)

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

        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.forest),
                contentDescription = "背景2",

                contentScale = ContentScale.FillBounds,  //縮放符合螢幕寬度
                modifier = Modifier
                    .offset { IntOffset(game.background.x2, 0) }
            )
        }

        val boyImage = arrayListOf(R.drawable.boy1, R.drawable.boy2,
            R.drawable.boy3, R.drawable.boy4,
            R.drawable.boy5, R.drawable.boy6,
            R.drawable.boy7, R.drawable.boy8)

        //繪製小男孩
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = boyImage[game.boy.pictNo]),
                contentDescription = "小男孩",
                modifier = Modifier
                    .width(100.dp)
                    .height(220.dp)
                    .offset { IntOffset(game.boy.x, game.boy.y) }
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
            Text(text = "成績：" + game.score.toString() + "分")
        }
    }
}
