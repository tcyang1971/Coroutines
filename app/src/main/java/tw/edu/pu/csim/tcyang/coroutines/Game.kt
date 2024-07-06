package tw.edu.pu.csim.tcyang.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class Game(val scope: CoroutineScope) {
    var counter = 0
    val state = MutableStateFlow(0)
    var temp = 0

    fun Play(){
        scope.launch {
            counter = 0
            while (counter < 20) {
                counter++
                temp++
                delay(1000)
                state.emit(counter)
            }
        }
    }
}