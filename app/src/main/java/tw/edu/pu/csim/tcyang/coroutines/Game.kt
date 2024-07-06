package tw.edu.pu.csim.tcyang.coroutines

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class Game(context: Context, val scope: CoroutineScope) {
    var counter = 0
    val state = MutableStateFlow(0)

    //取得螢幕寬度與高度像素
    private val displayMetrics = context.resources.displayMetrics
    private val ScreenWidth = displayMetrics.widthPixels
    private val ScreenHeight = displayMetrics.heightPixels
    private val px = displayMetrics.density  //dp轉像素的倍率 (1dp的像素)

    val background = Background(ScreenWidth)
    val boy = Boy(ScreenWidth, ScreenHeight, px)
    var virus = Virus(ScreenWidth, ScreenHeight, px)

    var score = 0
    var isPlaying = false

    fun Play(){
        score = 0
        boy.Reset()
        virus.Reset()
        isPlaying = true

        scope.launch {
            counter = 0
            while (isPlaying) {
                background.Roll()
                if (counter % 3 == 0){
                    boy.Walk()
                    if (boy.ReachRight()){  //小男孩到右邊界，加1分
                        score ++
                    }

                    virus.Fly()  //病毒飛行
                    if (virus.ReachEdge()){  //病毒到邊界，加1分
                        score ++
                    }

                    //判斷是否碰撞，結束遊戲
                    if(boy.getRect().intersect(virus.getRect())) {
                        isPlaying = false
                    }

                }

                counter++
                state.emit(counter)
                delay(25)
            }
        }
    }
}