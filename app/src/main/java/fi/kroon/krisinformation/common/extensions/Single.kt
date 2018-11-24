package fi.kroon.krisinformation.common.extensions

import io.reactivex.Single

fun <T> T.asSingle(): Single<T> = Single.just<T>(this)