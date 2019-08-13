package nkanyiso.hlela.sportsnews.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){
    val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        super.onCleared()
    }
}