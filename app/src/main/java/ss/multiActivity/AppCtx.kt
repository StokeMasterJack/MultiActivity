package ss.multiActivity

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel


fun <T> MutableLiveData<T>.observe(owner: LifecycleOwner, observer: (value: T) -> Unit) {
    observe(owner, Observer<T> {
        observer(it)
    })
}

class MyViewModel : ViewModel() {

    val count: MutableLiveData<Int> = MutableLiveData(0)

    fun observe(owner: LifecycleOwner, listener: (count: Int) -> Unit) {
        count.observe(owner, listener)
    }

    fun incrementCount() {
        count.postValue((count.value ?: 0) + 1)
    }

}

