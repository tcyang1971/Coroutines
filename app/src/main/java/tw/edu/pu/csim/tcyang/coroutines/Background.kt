package tw.edu.pu.csim.tcyang.coroutines

import kotlin.math.abs

class Background(val ScreenW:Int) {
    var x1 = 0  //背景1_x軸
    var x2 = ScreenW  //背景圖2_x軸

    fun Roll(){
        x1 --

        if (abs(x1) > ScreenW){ //已經移動整張
            x1 = 0
        }
        x2 = x1 + ScreenW
    }
}