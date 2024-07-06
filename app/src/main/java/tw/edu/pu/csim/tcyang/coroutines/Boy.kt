package tw.edu.pu.csim.tcyang.coroutines

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
}