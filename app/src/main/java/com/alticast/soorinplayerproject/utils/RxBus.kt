package com.alticast.soorinplayerproject.utils

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * Created by kjjeon on 2018-04-26.
 *
 */

// Use object so we have a singleton instance
object RxBus {

    private val publisher = PublishRelay.create<Any>()

    fun publish(event: Any) {
        publisher.accept(event)
    }

    // Listen should return an Observable and not the publisher
    // Using ofType we filter only events that match that class type
    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

}