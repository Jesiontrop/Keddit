package com.jesiontrop.keddit.core_ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class RxBaseFragment() : Fragment() {

    protected var disposables = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        disposables = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
        disposables.clear()
    }
}