package com.kylix.submissionbfaa3.utils

class Resource<out E>(val state: State, val data: E?, val message: String?) {
    companion object {
        fun <E> success(data: E): Resource<E> = Resource(State.SUCCESS, data, null)

        fun <E> error(data: E?, message: String): Resource<E> = Resource(State.ERROR, data, message)

        fun <E> loading(data: E?): Resource<E> = Resource(State.LOADING, data, null)
    }
}