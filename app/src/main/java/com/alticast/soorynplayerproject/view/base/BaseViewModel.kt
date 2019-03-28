package com.alticast.soorynplayerproject.view.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by kjjeon on 2018-05-10.
 *
 */

abstract class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    init {
    }

    open fun stop() {
        compositeDisposable.clear()
    }

    fun Disposable.drop(): Disposable = apply { compositeDisposable.add(this) }
}