package tw.edu.pu.csim.tcyang.coroutines

import android.content.Context
import android.media.MediaPlayer
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
    var gameoverDialog = false  //遊戲結束對話框
    //背景音樂
    private var mper1 = MediaPlayer.create(context, R.raw.lastletter)
    //結束音樂
    private var mper2 = MediaPlayer.create(context, R.raw.gameover)

    fun Play(){
        score = 0
        boy.Reset()
        virus.Reset()
        isPlaying = true
        gameoverDialog = false

        scope.launch {
            counter = 0
            while (isPlaying) {
                mper1.start()  //播放背景音樂
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

            //遊戲結束處理
            gameoverDialog = true
            mper1.pause()
            mper2.start()
            state.emit(counter + 1) //改變狀態，讓UI更新
        }
    }
}