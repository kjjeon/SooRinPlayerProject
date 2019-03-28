package com.alticast.soorynplayerproject.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by kjjeon on 2018-08-07.
 *
 */

abstract class BaseActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun Disposable.drop(): Disposable = apply { compositeDisposable.add(this) }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}