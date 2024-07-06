package tw.edu.pu.csim.tcyang.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
        Column {
            Button(
                modifier = modifier,
                onClick = {
                    game.Play()
                }
            ) {
                Text(text = "每秒加1，計時20秒")
            }
            Text(text = game.temp.toString())
        }
    }
}
