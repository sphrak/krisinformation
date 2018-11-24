package fi.kroon.krisinformation.common.extensions

import io.reactivex.Observable

fun <T> T.asObservable(): Observable<T> = Observable.just<T>(this)