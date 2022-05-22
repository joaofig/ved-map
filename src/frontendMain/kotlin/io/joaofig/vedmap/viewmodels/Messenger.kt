package io.joaofig.vedmap.viewmodels

open class Messenger<T> {
    private val observers = mutableListOf<(T) -> Unit>()

    fun send(msg: T) {
        observers.forEach { it(msg) }
    }

    fun subscribe(observer: (T) -> Unit): () -> Unit {
        observers += observer
        return {
            observers -= observer
        }
    }
}