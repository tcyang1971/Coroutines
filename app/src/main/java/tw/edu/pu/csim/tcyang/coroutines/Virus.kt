package tw.edu.pu.csim.tcyang.coroutines

import android.graphics.Rect

class Virus (val ScreenW:Int, val ScreenH:Int, val px:Float) {
    private var w = (100 * px).toInt()  //病毒寬度
    private var h = (100 * px).toInt()  //病毒高度

    var x: Int = ScreenW  //病毒x軸座標
    var y: Int = (0..ScreenH - h).random()  //病毒y軸座標
    var pictNo = 0  //切換圖片

    fun Fly(){
        x -= (0..20).random()
        y = y - (0..60).random() + 30
        pictNo++
        if (pictNo > 1){
            pictNo = 0
        }
    }

    //判斷病毒是否到達右邊或上下邊界
    fun ReachEdge():Boolean{
        if (x <= -w || y <= -h || y >= ScreenH-h) {
            Reset()
            return true
        }
        else{  return false  }
    }

    fun Reset(){
        x = ScreenW
        y = (0..ScreenH - h).random()
    }

    //取得病毒所在矩形區域
    fun getRect(): Rect {
        return Rect(x+10, y+10,x+w-10, y+h-10)
    }
}
