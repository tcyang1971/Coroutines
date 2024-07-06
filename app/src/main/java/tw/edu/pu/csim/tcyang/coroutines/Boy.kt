package tw.edu.pu.csim.tcyang.coroutines

import android.graphics.Rect

class Boy(val ScreenW:Int, val ScreenH:Int, val px:Float) {
    private var w = (100 * px).toInt()  //小男孩寬度
    private var h = (220 * px).toInt()  //小男孩高度

    var x = 0  //小男孩x軸座標
    var y = ScreenH - h  //小男孩y軸座標
    var pictNo = 0  //切換圖片

    fun Walk() {
        x += 10
        pictNo++
        if (pictNo > 7) {
            pictNo = 0
        }
    }

    //判斷小男孩是否走出右邊
    fun ReachRight():Boolean{
        if (x > ScreenW){
            Reset()
            return true
        }
        else{ return false }
    }

    fun Reset(){  //重設小男孩x軸座標
        x = 0
    }

    //取得小男孩所在矩形區域，用於碰撞偵測
    fun getRect(): Rect {
        //取得圖形範圍 (內縮10像素，比較不會太敏感)
        return Rect(x+10, y+10,x+w-10, y+h-10)
    }
}